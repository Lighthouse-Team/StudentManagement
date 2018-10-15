package com.project.tools;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.jdt.internal.compiler.ast.DoubleLiteral;
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

	public void Insert(String filePath) {
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

		File file = new File(filePath);
		try {
			InputStream input = new FileInputStream(file);
			Workbook readxls = Workbook.getWorkbook(input);
			Sheet readsheet = readxls.getSheet(0); // 表索引从0开始,取xls的第一张表
			int rsRows = readsheet.getRows(); // 获得表格的行数
			int startRow = 0; // 智能识别数据从第几行开始
			while (!readsheet.getCell(0, startRow++).getContents().equals("学号"))
				;
			System.out.println("本次导入记录总数：" + (rsRows - startRow));
			System.out.println("Inserting, please wait...");

			// 学生插入标识，以学号为键，值默认为null，插入学生之后设置为1
			Map<String, Integer> studentFlagMap = new HashMap<String, Integer>();
			// 课程插入标识，以课程编号为键，值默认为null，插入课程之后设置为1
			Map<String, Integer> courseFlagMap = new HashMap<String, Integer>();

			for (int i = startRow; i < rsRows; i++) {
				Student student = new Student();
				Course course = new Course();
				StudentCourse sc = new StudentCourse();
				Department department = new Department();

				// 插入学生信息
				String studentNumber = readsheet.getCell(0, i).getContents(); // 学号
				if (studentFlagMap.get(studentNumber) == null) {
					studentFlagMap.put(studentNumber, 1); // 设置学生标识
					Integer studentFlag = studentService.hasStudent(studentNumber); // 学生标识
					if (studentFlag == 0) {
						student.setStudentNumber(studentNumber);
						String studentName = readsheet.getCell(1, i).getContents(); // 姓名
						student.setStudentName(studentName);
						String studentClass = readsheet.getCell(6, i).getContents(); // 班级名称
						student.setStudentClass(studentClass);
						String departmentName = readsheet.getCell(4, i).getContents(); // 上课院系，学生所属院系
						department.setDepartmentName(departmentName);
						List<Department> departmentList = departmentService
								.getDepartmentListByEntityForLike(department);
						if (departmentList.size() > 0) {
							Department sDepartment = departmentService.getDepartmentListByEntityForLike(department)
									.get(0);
							student.setsDepartment(sDepartment); // 插入学生所属院系
						}
						try {
							DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
							String strEnrollment = studentNumber.substring(0, 4) + "-9-1";
							Date enrollment = df.parse(strEnrollment);
							student.setEnrollment(enrollment);
						} catch (Exception e) {
							System.out.println("日期导入出错");
						}
						studentService.insertStudent(student);
					}
				}

				// 插入课程信息
				String courseNumber = readsheet.getCell(7, i).getContents(); // 课程编号
				if (courseFlagMap.get(courseNumber) == null) {
					courseFlagMap.put(courseNumber, 1); // 设置课程标识
					Integer courseFlag = courseService.hasCourse(courseNumber);
					if (courseFlag == 0) {
						course.setCourseNumber(courseNumber);
						String courseName = readsheet.getCell(8, i).getContents(); // 课程名称
						course.setCourseName(courseName);
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
						courseService.insertCourse(course);
					}
				}

				// 插入选课信息
				Student scStudent = studentService.getStudentByStudentNumber(studentNumber);
				Course scCourse = courseService.getCourseByCourseNumber(courseNumber);
				sc.setScStudent(scStudent);
				sc.setScCourse(scCourse);
				String examTerm = readsheet.getCell(3, i).getContents(); // 选课学期
				sc.setExamTerm(examTerm);
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
					try {
						double numScore = Double.parseDouble(score);
						sc.setScore(numScore);
					} catch (NumberFormatException e) {
						sc.setScLevel(score);
						if (score.equals("中等")) {
							sc.setScore(75);
						} else if (score.equals("良好")) {
							sc.setScore(85);
						} else if (score.equals("及格")) {
							sc.setScore(65);
						} else if (score.equals("优秀")) {
							sc.setScore(95);
						} else if (score.equals("不及格")) {
							sc.setScore(35);
						} else {
							sc.setScore(-1); // 成绩异常
						}
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

			courseService.setCourseType(); // 所有成绩记录插入完毕之后，设置课程的courseType

			System.out.println("Insert Complete!");

			input.close();
		} catch (BiffException | IOException e) {
			// e.printStackTrace();
			System.out.println("文件不存在或上传的文件有错误，请确认上传！");
		}
	}

	public String getExamTermByFilePath(String filePath) {
		// 如果一个excele文件中只有一个学期的成绩，直接取第一条记录的学期即可
		// 但是如果有多个学期，选择取占多数的学期作为该成绩文件的学期，这个功能被注释了，因为只考虑一个成绩文件只有一个学期的情况
//		List<String> examTermList = new ArrayList<>();
//		List<String> examTermUniqueList = new ArrayList<>();
		String examTermResult = "null";
		File file = new File(filePath);
		try {
			InputStream input = new FileInputStream(file);
			Workbook readxls = Workbook.getWorkbook(input);
			Sheet readsheet = readxls.getSheet(0); // 表索引从0开始,取xls的第一张表
			int rsRows = readsheet.getRows(); // 获得表格的行数
			int startRow = 0; // 智能识别数据从第几行开始
			while (!readsheet.getCell(0, startRow++).getContents().equals("学号"))
				;
			System.out.println("startRow:" + (startRow + 1));
			System.out.println("Excel中记录总数:" + (rsRows - startRow));
			
			examTermResult = readsheet.getCell(3,startRow).getContents().toString();

//			for (int i = startRow; i < rsRows; i++) {
//				examTermList.add(readsheet.getCell(3, i).getContents());
//			}
//
//			// Set<String> examTermUniqueSet = new HashSet<>(examTermList);
//			Map<String, Integer> flagMap = new HashMap<>();
//			for (String examTerm : examTermList) {
//				if (flagMap.get(examTerm) == null) {
//					flagMap.put(examTerm, 1);
//					examTermUniqueList.add(examTerm);
//				}
//			}
//			// Iterator examTermIt = examTermUniqueSet.iterator();
//			Iterator<String> examTermIt = examTermUniqueList.iterator();
//			if (examTermIt.hasNext()) {
//				examTermResult = examTermIt.next().toString();
//				while (examTermIt.hasNext()) {
//					String examTermTmp = examTermIt.next().toString();
//					if (Collections.frequency(examTermList, examTermResult) < Collections.frequency(examTermList,
//							examTermTmp)) {
//						examTermResult = examTermTmp;
//					}
//				}
//			}
			// 关闭InputStream，否则删除这里用到的文件会出错
			input.close();
		} catch (BiffException | IOException e) {
			// e.printStackTrace();
			System.out.println("文件不存在或上传的文件有错误，请确认上传！");
		}
		return examTermResult;
	}

}
