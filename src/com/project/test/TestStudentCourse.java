package com.project.test;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.project.beans.Student;
import com.project.beans.StudentCourse;
import com.project.dto.OverallDistribution;
import com.project.service.CourseService;
import com.project.service.StudentCourseService;
import com.project.service.StudentService;

public class TestStudentCourse {

	@Autowired
	private StudentCourseService studentCourseService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private CourseService courseService;

	@Before
	public void before() {
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

		studentCourseService = (StudentCourseService) applicationContext.getBean("studentCourseService");
	}

	@Test
	public void test() {
		Student student = new Student();
		// Course course = new Course();
		StudentCourse sc = new StudentCourse();

		student.setStudentId(57545);
		sc.setScStudent(student);

		List<StudentCourse> scList = studentCourseService.getStudentCourseListByEntityForLike(sc);
		System.out.println(scList);
	}

	@Test
	public void doubleIntegerTest() {
		double d = 2.1;
		Integer i = 3;
		double d2 = d / i;
		System.out.println(d2);
	}

	@Test
	public void decimalTest() {
		double d = 1.212;
		NumberFormat nf = NumberFormat.getPercentInstance();
		System.out.println(nf.format(d)); // 121%

		DecimalFormat df = new DecimalFormat("0.00%"); // 系统可以识别这里的%
		System.out.println(df.format(d)); // 121.20%
		String str = df.format(d);

		DecimalFormat df2 = new DecimalFormat("0%");
		System.out.println(df2.format(d)); // 121%
	}

	@Test
	public void getACScoreDistributionByGradeTest() {
		Integer grade = 2015;
		Integer term = 1;
		OverallDistribution overallDistribution = studentCourseService.getACScoreDistributionByGrade(grade, term);
		System.out.println(overallDistribution);
	}

	@Test
	public void getACScoreDistributionListByYearTest() {
		String year = "2015-2016";
		Integer term = 1;
		List<OverallDistribution> odList = studentCourseService.getACScoreDistributionListByYear(year, term);
		for (OverallDistribution od : odList) {
			System.out.println(od);
		}
	}
	
	

}
