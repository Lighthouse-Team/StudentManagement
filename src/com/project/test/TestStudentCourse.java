package com.project.test;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.project.beans.Student;
import com.project.beans.StudentCourse;
import com.project.dto.ClassExcellentFailDistribution;
import com.project.dto.GradeDepartmentAverageScoreCompare;
import com.project.dto.GradeDepartmentFailDistribution;
import com.project.dto.DepartmentDistribution;
import com.project.dto.DepartmentFailDistribution;
import com.project.dto.GradeFailDistribution;
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
		// 查询19个院系，16.7秒，速度有点慢
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
		List<String> classNumberList = studentCourseService.getAllClassNumberListByGrade(grade);
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
		// 通识查询143个班，30.4秒，速度有点慢，看是否可以改善
		Integer grade = 2015;
		String year = "2017-2018";
		Integer term = 1;
		List<ClassExcellentFailDistribution> cefdList = studentCourseService
				.getClassRPECScoreDistributionListByGrade(grade, year, term);
		for (ClassExcellentFailDistribution cefd : cefdList) {
			System.out.println(cefd);
		}
	}

	/*
	 * 测试第2章第4个功能
	 */

	@Test
	public void getDepartmentAverageScoreCompareByGradeTest() {
		// 12.6秒，速度有点慢
		Integer grade = 2015;
		String year = "2017-2018";
		Integer term = 1;
		List<GradeDepartmentAverageScoreCompare> dascList = studentCourseService
				.getRPECGradeDepartmentAverageScoreCompareListByGrade(grade, year, term);
		for (GradeDepartmentAverageScoreCompare departmentAverageScoreCompare : dascList) {
			System.out.println(departmentAverageScoreCompare);
		}
	}

	@Test
	public void getDepartmentAverageScoreCompareListListTest() {
		// 在只计算一届的情况下17.1秒，计算四届的情况估计60秒，速度有点慢，需改善
		String year = "2017-2018";
		Integer term = 1;
		List<List<GradeDepartmentAverageScoreCompare>> dascListList = studentCourseService
				.getRPECGradeDepartmentAverageScoreCompareListList(year, term);
		for (List<GradeDepartmentAverageScoreCompare> dascList : dascListList) {
			for (GradeDepartmentAverageScoreCompare dasc : dascList) {
				System.out.println(dasc);
			}
		}
	}

	/*
	 * 测试第3章第1个功能
	 */

	@Test
	public void constructorTest() {
		GradeDepartmentAverageScoreCompare departmentAverageScoreCompare = new GradeDepartmentAverageScoreCompare();
		System.out.println(departmentAverageScoreCompare);
		GradeFailDistribution gradeFailDistribution = new GradeFailDistribution();
		System.out.println(gradeFailDistribution);
	}

	@Test
	public void getGradeFailDistributionTest() {
		Integer grade = 2015;
		String year = "2017-2018";
		Integer term = 1;
		GradeFailDistribution gradeFailDistribution = new GradeFailDistribution();
		gradeFailDistribution = studentCourseService.getRCGradeFailDistributionByGrade(grade, year, term);
		System.out.println(gradeFailDistribution);
	}

	@Test
	public void listSetTest() {
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("3");
		list.add("1");
		list.add("2");
		list.add("6");
		list.add("4");
		list.add("5");
		Set uniqueSet = new HashSet<>(list);
		System.out.println(list); // 1,3,1,2,6,4,5
		System.out.println(uniqueSet); // 1,2,3,4,5,6
	}

	@Test
	public void getUniversityRCGradeFailDistributionListTest() {
		String year = "2017-2018";
		Integer term = 1;
		List<GradeFailDistribution> gfdList = new ArrayList<>();
		gfdList = studentCourseService.getUniversityRCGradeFailDistributionList(year, term);
		for (GradeFailDistribution gradeFailDistribution : gfdList) {
			System.out.println(gradeFailDistribution);
		}

	}

	/*
	 * 测试第3章第2个功能
	 */

	@Test
	public void getUniversityRCDepartmentFailDistributionListTest() {
		String year = "2017-2018";
		Integer term = 1;
		List<DepartmentFailDistribution> dfdList = new ArrayList<>();
		dfdList = studentCourseService.getUniversityRCDepartmentFailDistributionList(year, term);
		for (DepartmentFailDistribution departmentFailDistribution : dfdList) {
			System.out.println(departmentFailDistribution);
		}
	}

	@Test
	public void getDepartmentFailDistributionByDepartmentIdTest() {
		// 测试结果和原方法一致，除了6系
		Integer departmentId = 1;
		String year = "2017-2018";
		Integer term = 1;
		DepartmentFailDistribution departmentFailDistribution = new DepartmentFailDistribution();
		departmentFailDistribution = studentCourseService
				.testGetRCDepartmentFailDistributionByDepartmentId(departmentId, year, term);
		System.out.println(departmentFailDistribution);
	}

	/*
	 * 测试第3章第3个功能
	 */

	@Test
	public void getRCGradeDepartmentFailDistributionListListTest() {
		// 2.7秒，速度还行
		String year = "2017-2018";
		Integer term = 1;
		List<List<GradeDepartmentFailDistribution>> gdfdListList = studentCourseService
				.getRCGradeDepartmentFailDistributionListList(year, term);
		for (List<GradeDepartmentFailDistribution> gdfdList : gdfdListList) {
			for (GradeDepartmentFailDistribution gradeDepartmentFailDistribution : gdfdList) {
				System.out.println(gradeDepartmentFailDistribution);
			}
		}
	}
}
