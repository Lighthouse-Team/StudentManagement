package com.project.test;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.project.beans.Student;
import com.project.beans.StudentCourse;
import com.project.dto.ClassExcellentFailDistribution;
import com.project.dto.DepartmentAverageScoreCompare;
import com.project.dto.DepartmentDistribution;
import com.project.dto.OverallDistribution;
import com.project.service.StudentCourseService;
//import com.project.service.StudentService;

public class TestStudentCourse {

	@Autowired
	private StudentCourseService studentCourseService;

	@Before
	public void before() {
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		studentCourseService = (StudentCourseService) applicationContext.getBean("studentCourseService");
	}

	@Test
	public void decimalTest() {
		double d = 1.212;
		NumberFormat nf = NumberFormat.getPercentInstance();
		System.out.println(nf.format(d)); // 121%

		DecimalFormat df = new DecimalFormat("0.00%"); // 系统可以识别这里的%
		System.out.println(df.format(d)); // 121.20%

		DecimalFormat df2 = new DecimalFormat("0%");
		System.out.println(df2.format(d)); // 121%
	}

	/*
	 * 测试第1章第1个功能
	 */

	@Test
	public void getACScoreDistributionByGradeTest() {
		Integer grade = 2015;
		// String year = "2016-2017"; // 结果为0
		String year = "2017-2018";
		Integer term = 1;
		OverallDistribution overallDistribution = studentCourseService.getACScoreDistributionByGrade(grade, year, term);
		System.out.println(overallDistribution);
	}

	@Test
	public void getUniversityACScoreDistributionListTest() {
		// String year = "2016-2017"; // 结果为0
		String year = "2017-2018";
		Integer term = 1;
		List<OverallDistribution> odList = studentCourseService.getUniversityACScoreDistributionList(year, term);
		for (OverallDistribution od : odList) {
			System.out.println(od);
		}
	}

	/*
	 * 测试第1章第2个功能
	 */

	@Test
	public void getAGScoreDistributionByCourseTypeTest() {
		Integer courseType = 1;
		// String year = "2016-2017"; // 结果为0
		String year = "2017-2018";
		Integer term = 1;
		OverallDistribution overallDistribution = studentCourseService.getAGScoreDistributionByCourseType(courseType,
				year, term);
		System.out.println(overallDistribution);
	}

	@Test
	public void getUniversityAGScoreDistributionListTest() {
		// String year = "2016-2017"; // 结果为0
		String year = "2017-2018";
		Integer term = 1;
		List<OverallDistribution> odList = studentCourseService.getUniversityAGScoreDistributionList(year, term);
		for (OverallDistribution od : odList) {
			System.out.println(od);
		}
	}

	/*
	 * 测试第2章第1个功能
	 */

	@Test
	public void getRPECScoreDistributionByGradeTest() {
		Integer grade = 2015;
		String year = "2017-2018";
		Integer term = 1;
		OverallDistribution overallDistribution = studentCourseService.getRPECScoreDistributionByGrade(grade, year,
				term);
		System.out.println(overallDistribution);
	}

	@Test
	public void getUniversityRPECScoreDistributionListTest() {
		String year = "2017-2018";
		Integer term = 1;
		List<OverallDistribution> odList = studentCourseService.getUniversityRPECScoreDistributionList(year, term);
		for (OverallDistribution od : odList) {
			System.out.println(od);
		}
	}

	/*
	 * 测试第2章第2个功能
	 */

	@Test
	public void getRPECAverageScoreByGradeAndDepartmentIdTest() {
		Integer grade = 2015;
		Integer departmentId = 3;
		// String year = "2016-2017"; // 结果为-1，表示成绩异常
		String year = "2017-2018";
		Integer term = 1;
		double averageScore = studentCourseService.getRPECAverageScoreByGradeAndDepartmentId(departmentId, grade, year,
				term);
		System.out.println(averageScore);
	}

	@Test
	public void getRPECAverageScoreByGradeTest() { // 第2章第1个功能里定义的方法，可以用在这里
		Integer grade = 2015;
		// String year = "2016-2017"; // 结果为-1，表示成绩异常
		String year = "2017-2018";
		Integer term = 1;
		double averageScore = studentCourseService.getRPECAverageScoreByGrade(grade, year, term);
		System.out.println(averageScore); // 76.21->75.45（真实）
	}

	@Test
	public void getRPECScoreDistributionByGradeAndDepartmentIdTest() {
		Integer grade = 2015;
		Integer departmentId = 1;
		// String year = "2016-2017"; // 结果为0
		String year = "2017-2018";
		Integer term = 1;
		DepartmentDistribution departmentDistribution = studentCourseService
				.getRPECScoreDistributionByGradeAndDepartmentId(departmentId, grade, year, term);
		System.out.println(departmentDistribution);
	}

	@Test
	public void getDepartmentRPECScoreDistributionByGradeTest() {
		Integer grade = 2015;
		// String year = "2016-2017"; // 结果为0
		String year = "2017-2018";
		Integer term = 1;
		List<DepartmentDistribution> ddList = studentCourseService.getDepartmentRPECScoreDistributionListByGrade(grade,
				year, term);
		for (DepartmentDistribution dd : ddList) {
			System.out.println(dd);
		}
	}

	/*
	 * 测试第2章第3个功能
	 */

	@Test
	public void getAllClassNumberByGradeTest() {
		Integer grade = 2015;
		List<String> classNumberList = studentCourseService.getAllClassNumberByGrade(grade);
		System.out.println(classNumberList.size());
		System.out.println(classNumberList);
	}

	@Test
	public void getRPECExcellentFailDistributionByClassNumber() {
		String classNumber = "20150002";
		String year = "2017-2018";
		Integer term = 1;
		ClassExcellentFailDistribution classExcellentFailDistribution = studentCourseService
				.getRPECExcellentFailDistributionByClassNumber(classNumber, year, term);
		System.out.println(classExcellentFailDistribution);
	}

	@Test
	public void getClassRPECScoreDistributionByGradeTest() {
		Integer grade = 2015;
		String year = "2017-2018";
		Integer term = 1;
		List<ClassExcellentFailDistribution> cefdList = studentCourseService.getClassRPECScoreDistributionByGrade(grade,
				year, term);
		for (ClassExcellentFailDistribution cefd : cefdList) {
			System.out.println(cefd);
		}
	}

	/*
	 * 测试第2章第4个功能
	 */

	@Test
	public void getDepartmentAverageScoreCompareByGradeTest() {
		Integer grade = 2015;
		String year = "2017-2018";
		Integer term = 1;
		List<DepartmentAverageScoreCompare> dascList = studentCourseService
				.getDepartmentRPECAverageScoreCompareByGrade(grade, year, term);
		for (DepartmentAverageScoreCompare departmentAverageScoreCompare : dascList) {
			System.out.println(departmentAverageScoreCompare);
		}
	}

	@Test
	public void getDepartmentAverageScoreCompareTest() {
		String year = "2017-2018";
		Integer term = 1;
		List<List<DepartmentAverageScoreCompare>> dascListList = studentCourseService
				.getDepartmentRPECAverageScoreCompare(year, term);
		for (List<DepartmentAverageScoreCompare> dascList : dascListList) {
			for (DepartmentAverageScoreCompare dasc : dascList) {
				System.out.println(dasc);
			}
		}
	}

}
