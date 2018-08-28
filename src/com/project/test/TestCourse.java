package com.project.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.project.beans.Course;
import com.project.beans.Student;
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
		System.out.println(course);
		// Integer courseId = courseService.insertCourse(course);
		// System.out.println(courseId);
	}

	@Test
	public void setCourseTypeTest() {
		courseService.setCourseType();
	}

	@Test
	public void getCourseDetailByEntityForLikeTest() {
		Course course = new Course();
		course.setCourseId(1);
		String year = "2017-2018";
		Integer term = 1;
		List<Course> courseList = courseService.getCourseDetailsByEntityForLike(course, year, term);
		System.out.println("courseList.size():" + courseList.size());
		for (Integer index = 0; index < courseList.size(); index++) {
			System.out.println(courseList.get(index));
			List<Student> studentList = courseList.get(index).getStudentList();
			System.out.println("studentList.size():" + studentList.size());
			for (Integer index2 = 0; index2 < studentList.size(); index2++) {
				System.out.println(studentList.get(index2));
			}
		}
	}

	@Test
	public void getCourseListByStudentId() {
		Integer studentId = 1;
		String year = "2017-2018";
		Integer term = 1;
		List<Course> courseList = courseService.getCourseListByStudentId(studentId, year, term);
		System.out.println("courseList.size():" + courseList.size());
		for (Integer index = 0; index < courseList.size(); index++) {
			System.out.println(courseList.get(index));
		}
	}

	@Test
	public void hasCourse() {
		String courseNumber = "123456";
		Integer courseFlag = courseService.hasCourse(courseNumber);
		System.out.println(courseFlag);
	}

}
