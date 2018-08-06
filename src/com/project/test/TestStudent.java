package com.project.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
	public void getStudentByStudentNumberTest() { // 这里和下面找到学生对象必须要求数据库中该学生具有departmentId
		// String studentNumber = "2015061524";
		// String studentNumber = "20171101JX";
		String studentNumber = "2014061525";
		Student student = studentService.getStudentByStudentNumber(studentNumber);
		System.out.println(student);
	}

	@Test
	public void getStudentByEntityTest() {
		Student student = new Student();
		student.setStudentName("夏朋");
		student = studentService.getStudentByEntity(student);
		System.out.println(student);
	}

	@Test
	public void getStudentDetailsTest() {
		Student student = new Student();
		student.setStudentId(57545);

		List<Student> studentList = studentService.getStudentDetailsByEntityForLike(student);

		System.out.println(studentList);
	}

	@Test
	public void getAllStudentTest() {
		List<Student> studentList = studentService.getAllStudent();
		for (int i = 0; i < studentList.size(); i++) {
			System.out.println(studentList.get(i));
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

}
