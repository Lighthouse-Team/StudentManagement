package com.project.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.beans.Student;
import com.project.dao.StudentMapper;

@Service
public class StudentService {

	@Autowired
	private StudentMapper studentMapper;

	/**
	 * 通过 studentId 号获取指定学生信息
	 * 
	 * @param studentId
	 * @return
	 */
	public Student getStudentByStudentId(Integer studentId) {
		Student student = studentMapper.getStudentByStudentId(studentId);
		if (student != null) {
			// 日期从 Date 类型转换为 String 类型在前台展示
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// String strStudentBirth = sdf.format(student.getStudentBirth());
			String strEnrollment = sdf.format(student.getEnrollment());
			// student.setStrStudentBirth(studentBirthTime);
			student.setStrEnrollment(strEnrollment);
		}
		return student;
	}

	/**
	 * 通过 studentNumber 获取指定学生信息
	 * 
	 * @param studentNumber
	 * @return
	 */
	public Student getStudentByStudentNumber(String studentNumber) {
		Student student = studentMapper.getStudentByStudentNumber(studentNumber);
		if (student != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// String strStudentBirth = sdf.format(student.getStudentBirth());
			String strEnrollment = sdf.format(student.getEnrollment());
			// student.setStrStudentBirth(studentBirthTime);
			student.setStrEnrollment(strEnrollment);
		}
		return student;
	}

	/**
	 * 通过 courseId 获得选修该课程的所有学生列表
	 * 
	 * @param courseId
	 * @param year
	 * @param term
	 * @return
	 */
	public List<Student> getStudentListByCourseId(Integer courseId, String year, Integer term) {
		return studentMapper.getStudentListByCourseId(courseId, year, term);
	}

	/**
	 * 添加学生信息并返回主键值
	 * 
	 * @param student
	 * @return
	 */
	public void insertStudent(Student student) {
		studentMapper.addStudent(student);
		// student = getStudentByEntity(student); // 注释了节省导入数据库的时间
		// if (student != null) {
		// return student.getStudentId();
		// } else {
		// return -1; // 插入异常
		// }
	}

	/**
	 * 获取所有学生信息
	 * 
	 * @param
	 * @return
	 */
	public List<Student> getAllStudent() {
		List<Student> tmpList = studentMapper.getAllStudent();
		List<Student> resultList = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (Student student : tmpList) {
			if (student != null) {
				// String studentBirthTime = sdf.format(student.getStudentBirth());
				String enrollmentTime = sdf.format(student.getEnrollment());
				// student.setStrStudentBirth(studentBirthTime);
				student.setStrEnrollment(enrollmentTime);
				resultList.add(student);
			}
		}
		return resultList;
	}

	/**
	 * 通过 student 属性查询学生信息
	 * 
	 * @param student
	 * @return
	 */
	public Student getStudentByEntity(Student student) {
		Student resultStudent = studentMapper.getStudentByEntity(student);
		if (resultStudent != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// String strStudentBirth = sdf.format(resultStudent.getStudentBirth());
			String strEnrollment = sdf.format(resultStudent.getEnrollment());
			// resultStudent.setStrStudentBirth(strStudentBirth);
			resultStudent.setStrEnrollment(strEnrollment);
		}
		return resultStudent;
	}

	/**
	 * 通过 student 属性查询学生信息列表
	 * 
	 * @param student
	 * @return
	 */
	public List<Student> getStudentListByEntityForLike(Student student) {
		List<Student> tmpList = studentMapper.getStudentListByEntityForLike(student);
		List<Student> resultList = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (Student resultStudent : tmpList) {
			// String strStudentBirth = sdf.format(resultStudent.getStudentBirth());
			String strEnrollment = sdf.format(resultStudent.getEnrollment());
			// resultStudent.setStrStudentBirth(strStudentBirth);
			resultStudent.setStrEnrollment(strEnrollment);
			resultList.add(resultStudent);
		}
		return resultList;
	}

	/**
	 * 根据学生属性, 查询学生选课情况
	 * 
	 * @param student
	 * @return
	 */
	public List<Student> getStudentDetailsByEntityForLike(Student student, String year, Integer term) {
		List<Student> tmpList = studentMapper.getStudentDetailsByEntityForLike(student, year, term);
		List<Student> resultList = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (Student resultStudent : tmpList) {
			// String strStudentBirth = sdf.format(resultStudent.getStudentBirth());
			String strEnrollment = sdf.format(resultStudent.getEnrollment());
			// resultStudent.setStrStudentBirth(strStudentBirth);
			resultStudent.setStrEnrollment(strEnrollment);
			resultList.add(resultStudent);
		}
		return resultList;
	}

}
