package com.project.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.project.beans.Course;
import com.project.beans.Department;
import com.project.beans.Student;
import com.project.service.StudentService;

public class TestStudent {

	@Autowired
	private StudentService studentService;

	@Before
	public void before() {
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

		studentService = (StudentService) applicationContext.getBean("studentService");
	}

	@Test
	public void getStudentByStudentNumberTest() {
		String studentNumber = "2014061525";
		Student student = studentService.getStudentByStudentNumber(studentNumber);
		System.out.println(student);
	}

	@Test
	public void getStudentListByCourseIdTest() {
		Integer courseId = 1;
		String year = null;
		Integer term = 1;
		List<Student> studentList = studentService.getStudentListByCourseId(courseId, year, term);
		System.out.println("studentList.size():" + studentList.size());
		for (Integer index = 0; index < studentList.size(); index++) {
			System.out.println(studentList.get(index));
		}
	}

	@Test
	public void getStudentByEntityTest() {
		Student student = new Student();
		student.setStudentName("夏朋");
		student = studentService.getStudentByEntity(student);
		System.out.println(student);
	}

	@Test
	public void getStudentDetailsByEntityForLikeTest() {
		Student student = new Student();
		student.setStudentId(1);
		String year = "2017-2018";
		Integer term = 1;
		List<Student> studentList = studentService.getStudentDetailsByEntityForLike(student, year, term);
		System.out.println("studentList.size():" + studentList.size());
		for (Integer index = 0; index < studentList.size(); index++) {
			System.out.println(studentList.get(index));
			List<Course> courseList = studentList.get(index).getCourseList();
			System.out.println("courseList.size():" + courseList.size());
			for (Integer index2 = 0; index2 < courseList.size(); index2++) {
				System.out.println(courseList.get(index2));
			}
		}
	}

	@Test
	public void strStudentBirthTest() { // studentBirth为空的时候，不能转换为strStudentBirth，故getStudentByEntity里要把strStudentBirth注释掉
		Student student = new Student();
		student.setStudentName("范祥杰");
		Department department = new Department();
		department.setDepartmentId(1);
		student.setsDepartment(department);
		student = studentService.getStudentByEntity(student);
		System.out.println(student);
	}

	@Test
	public void hasStudentTest() {
		String studentNumber = "2013011602";
		Integer studentFlag = studentService.hasStudent(studentNumber);
		System.out.println(studentFlag);
	}

}
