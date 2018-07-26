package com.project.tools;

import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.project.beans.Course;
import com.project.beans.Department;
import com.project.beans.Student;
import com.project.beans.StudentCourse;
import com.project.service.CourseService;
import com.project.service.DepartmentService;
import com.project.service.StudentCourseService;
import com.project.service.StudentService;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class InsertIntoDB {
	
	public void Insert() {
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		StudentService studentService;
		studentService = (StudentService) applicationContext.getBean("studentService");
		CourseService courseService;
		courseService = (CourseService) applicationContext.getBean("courseService");
		StudentCourseService studentCourseService;
		studentCourseService = (StudentCourseService) applicationContext.getBean("studentCourseService");
		DepartmentService departmentService;
		departmentService = (DepartmentService) applicationContext.getBean("departmentService");

		File file = new File("E:/实验室/学生管理系统/删除重复项的成绩列表.xls");
		try {
			InputStream input = new FileInputStream(file);
			Workbook readxls = Workbook.getWorkbook(input);
			Sheet readsheet = readxls.getSheet(0); // 表索引从0开始,取xls的第一张表
			int rsRows = readsheet.getRows(); // 获得表格的行数
			System.out.println(rsRows);

			// 学生插入标识，以学号为键，以姓名为值，默认为null，插入学生之后不为null
			Map<String, String> studentFlagMap = new HashMap<>();
			// 课程插入标识，以课程编号为键，以课程名称为值，默认为null，插入课程之后不为null
			Map<String, String> courseFlagMap = new HashMap<String, String>();

			for (int i = 1; i < rsRows; i++) { // 标题为第0行，数据从第1行开始
				Student student = new Student();
				Course course = new Course();
				StudentCourse sc = new StudentCourse();
				Department department = new Department();

				// 插入学生信息
				String studentNumber = readsheet.getCell(0, i).getContents(); // 学号
				student.setStudentNumber(studentNumber);
				if (studentFlagMap.get(studentNumber) == null) {
					String studentName = readsheet.getCell(1, i).getContents(); // 姓名
					student.setStudentName(studentName);
					studentFlagMap.put(studentNumber, studentName); // 设置学生标志位
					String studentClass = readsheet.getCell(6, i).getContents(); // 班级名称
					student.setStudentClass(studentClass);
					String departmentName = readsheet.getCell(4, i).getContents(); // 上课院系，学生所属院系
					department.setDepartmentName(departmentName);
					List<Department> departmentList = departmentService.getDepartmentListByEntityForLike(department);
					if (departmentList.size() > 0) {
						Department sDepartment = departmentService.getDepartmentListByEntityForLike(department).get(0);
						student.setsDepartment(sDepartment); // 插入学生所属院系
					}
					try {
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						String strEnrollment = studentNumber.substring(0, 4) + "-9-1";
						Date dateEnrollment = df.parse(strEnrollment);
						student.setEnrollment(dateEnrollment);
					} catch (Exception e) {
						System.out.println("日期导入出错");
					}
					studentService.insertStudent(student);
				}

				// 插入课程信息
				String courseNumber = readsheet.getCell(7, i).getContents(); // 课程编号
				course.setCourseNumber(courseNumber);
				if (courseFlagMap.get(courseNumber) == null) {
					String courseName = readsheet.getCell(8, i).getContents(); // 课程名称
					course.setCourseName(courseName);
					courseFlagMap.put(courseNumber, courseName); // 设置课程标志位
					String courseProperty = readsheet.getCell(11, i).getContents(); // 课程性质
					course.setCourseProperty(courseProperty);
					String courseAttribute = readsheet.getCell(12, i).getContents(); // 课程属性
					course.setCourseAttribute(courseAttribute);
					String coursePeriod = readsheet.getCell(13, i).getContents(); // 学时
					course.setCoursePeriod(coursePeriod);
					String strCourseCredits = readsheet.getCell(14, i).getContents(); // 学分
					double courseCredits = Double.parseDouble(strCourseCredits);
					course.setCourseCredits(courseCredits);
					String courseDepartment = readsheet.getCell(15, i).getContents(); // 开课单位
					course.setCourseDepartment(courseDepartment);
					String strCourseTerm = readsheet.getCell(3, i).getContents();
					Integer courseTerm = Integer // 开课学期:1、2
							.parseInt(strCourseTerm.substring(strCourseTerm.length() - 1, strCourseTerm.length()));
					course.setCourseTerm(courseTerm);
					courseService.insertCourse(course);
				}

				// 插入选课信息
				Student scStudent = studentService.getStudentByStudentNumber(studentNumber);
				Course scCourse = courseService.getCourseByCourseNumber(courseNumber);
				sc.setScStudent(scStudent);
				sc.setScCourse(scCourse);
				String scTerm = readsheet.getCell(3, i).getContents(); // 开课学期
				sc.setScTerm(scTerm);
				String examProperty = readsheet.getCell(17, i).getContents(); // 考试性质
				if (examProperty.equals("正常考试")) {
					sc.setExamProperty(1);
				} else if (examProperty.equals("补考")) {
					sc.setExamProperty(2);
				} else if (examProperty.equals("补考二")) {
					sc.setExamProperty(3);
				} else if (examProperty.equals("自主考试")) {
					sc.setExamProperty(4);
				} else {
					sc.setExamProperty(-1); // 考试性质异常
				}
				String scoreMark = readsheet.getCell(10, i).getContents(); // 成绩标识
				if (scoreMark.isEmpty()) { // 参加考试，有成绩，导入成绩
					sc.setScoreMark(1);
					String score = readsheet.getCell(9, i).getContents(); // 总成绩
					if (score.equals("优秀")) {
						sc.setScLevel("优秀");
						sc.setScore(95);
					} else if (score.equals("良好")) {
						sc.setScLevel("良好");
						sc.setScore(85);
					} else if (score.equals("中等")) {
						sc.setScLevel("中等");
						sc.setScore(75);
					} else if (score.equals("及格")) {
						sc.setScLevel("及格");
						sc.setScore(65);
					} else if (score.equals("不及格")) {
						sc.setScLevel("不及格");
						sc.setScore(35);
					} else {
						sc.setScore(Integer.parseInt(score));
					}
				} else if (scoreMark.equals("缺考")) {
					sc.setScoreMark(2);
				} else if (scoreMark.equals("缓考")) {
					sc.setScoreMark(3);
				} else if (scoreMark.equals("违纪")) {
					sc.setScoreMark(4);
				} else if (scoreMark.equals("作弊")) {
					sc.setScoreMark(5);
				} else {
					sc.setScoreMark(-1); // 成绩标识异常
				}
				studentCourseService.insertStudentCourse(sc);
			}

			System.out.println("Insert Complete!");

		} catch (BiffException | IOException e) {
			e.printStackTrace();
		}

	}
	
	public void setCourseType() {
		
	}

}
