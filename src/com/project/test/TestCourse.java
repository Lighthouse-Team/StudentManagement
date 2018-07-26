package com.project.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.project.beans.Course;
import com.project.service.CourseService;

public class TestCourse {
	@Autowired
	private CourseService courseService;
	
	@Before
	public void before() {
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		courseService = (CourseService) applicationContext.getBean("courseService");
	}
	
	@Test
	public void courseTest() {
		String courseNumber = "201401138";
		Course course = courseService.getCourseByCourseNumber(courseNumber);
		System.out.println(course);
	}
	
	@Test
	public void getCourseByEntityTest() {
		Course course = new Course();
		course.setCourseName("数据结构");
		course.setCourseCredits(6);
		course = courseService.getcourseByEntity(course);
		System.out.println(course);
	}
	
	@Test
	public void integerVoidTest() {
		Course course = new Course();
		course.setCourseName("数据结构");
		Integer courseId = courseService.insertCourse(course);
		System.out.println(courseId);
		System.out.println(course);
	}
	
	@Test
	public void integerDoubleInitializeTest() {
		Course course = new Course();
		System.out.println(course);
		course.setCourseCredits(10);
//		course.setCourseId(10);
		System.out.println(course);
		List<Course> courseList = courseService.getCourseListByEntityForLike(course);
		System.out.println(courseList);
	}
	
	@Test
	public void setCourseTypeTest() {
		courseService.setCourseType();
	}
}
