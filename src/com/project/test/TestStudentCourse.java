package com.project.test;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.project.dto.BasicCourseClassDistribution;
import com.project.dto.BasicCourseDetailDistribution;
import com.project.dto.BasicCourseOverallDistribution;
import com.project.dto.ClassExcellentFailDistribution;
import com.project.dto.ClassFailDistribution;
import com.project.dto.GradeDepartmentAverageScoreCompare;
import com.project.dto.GradeDepartmentFailDistribution;
import com.project.dto.DepartmentDistribution;
import com.project.dto.DepartmentFailDistribution;
import com.project.dto.GradeAbsenceDistribution;
import com.project.dto.GradeFailDistribution;
import com.project.dto.OverallDistribution;
import com.project.service.StudentCourseService;
//import com.project.service.StudentService;
import com.project.service.StudentService;

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

	@Test
	public void integerDoubleTest() {
		Integer b = 3;
		double a = 4;
		double c = a / b;
		System.out.println(c); // 1.3333333333
		Integer a2 = 4;
		double c2 = a2 / b;
		System.out.println(c2); // 1.0
		c2 = (double) a2 / b;
		System.out.println(c2); // 1.3333333333
	}

	/*
	 * 测试第1章第1个功能
	 */

	@Test
	public void getACOverallDistributionByGradeTest() {
		Integer grade = 2015;
		// String year = "2016-2017"; // 结果为0
		String year = "2017-2018";
		Integer term = 1;
		OverallDistribution overallDistribution = studentCourseService.getACOverallDistributionByGrade(grade, year,
				term);
		System.out.println(overallDistribution);
	}

	@Test
	public void getACOverallDistributionListTest() {
		// String year = "2016-2017"; // 结果为0
		String year = "2017-2018";
		Integer term = 1;
		List<OverallDistribution> odList = studentCourseService.getACOverallDistributionList(year, term);
		for (OverallDistribution od : odList) {
			System.out.println(od);
		}
	}

	/*
	 * 测试第1章第2个功能
	 */

	@Test
	public void getAGOverallDistributionByCourseTypeTest() {
		Integer courseType = 1;
		// String year = "2016-2017"; // 结果为0
		String year = "2017-2018";
		Integer term = 1;
		OverallDistribution overallDistribution = studentCourseService.getAGOverallDistributionByCourseType(courseType,
				year, term);
		System.out.println(overallDistribution);
	}

	@Test
	public void getAGOverallDistributionListtTest() {
		// String year = "2016-2017"; // 结果为0
		String year = "2017-2018";
		Integer term = 1;
		List<OverallDistribution> odList = studentCourseService.getAGOverallDistributionList(year, term);
		for (OverallDistribution od : odList) {
			System.out.println(od);
		}
	}

	/*
	 * 测试第2章第1个功能
	 */

	@Test
	public void getRPECOverallDistributionByGradeTest() {
		Integer grade = 2015;
		String year = "2017-2018";
		Integer term = 1;
		OverallDistribution overallDistribution = studentCourseService.getRPECOverallDistributionByGrade(grade, year,
				term);
		System.out.println(overallDistribution);
	}

	@Test
	public void getRPECOverallDistributionListTest() {
		String year = "2017-2018";
		Integer term = 1;
		List<OverallDistribution> odList = studentCourseService.getRPECOverallDistributionList(year, term);
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
		System.out.println(averageScore);
	}

	@Test
	public void getRPECDepartmentDistributionByGradeAndDepartmentIdTest() {
		Integer grade = 2015;
		Integer departmentId = 1;
		// String year = "2016-2017"; // 结果为0
		String year = "2017-2018";
		Integer term = 1;
		DepartmentDistribution departmentDistribution = studentCourseService
				.getRPECDepartmentDistributionByGradeAndDepartmentId(departmentId, grade, year, term);
		System.out.println(departmentDistribution);
	}

	@Test
	public void getRPECDepartmentDistributionListByGradeTest() {
		// 查询19个院系，16.7秒，速度有点慢
		Integer grade = 2015;
		// String year = "2016-2017"; // 结果为0
		String year = "2017-2018";
		Integer term = 1;
		List<DepartmentDistribution> ddList = studentCourseService.getRPECDepartmentDistributionListByGrade(grade, year,
				term);
		for (DepartmentDistribution dd : ddList) {
			System.out.println(dd);
		}
	}

	/*
	 * 测试第2章第3个功能
	 */

	@Test
	public void getClassNumberListByGradeTest() {
		Integer grade = 2015;
		List<String> classNumberList = studentCourseService.getClassNumberListByGrade(grade);
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
	public void getRPECClassExcellentFailDistributionListByGradeTest() {
		// 通识查询143个班，30.4秒，速度有点慢，看是否可以改善
		Integer grade = 2015;
		String year = "2017-2018";
		Integer term = 1;
		List<ClassExcellentFailDistribution> cefdList = studentCourseService
				.getRPECClassExcellentFailDistributionListByGrade(grade, year, term);
		for (ClassExcellentFailDistribution cefd : cefdList) {
			System.out.println(cefd);
		}
	}

	/*
	 * 测试第2章第4个功能
	 */

	@Test
	public void getDepartmentAverageScoreCompareListByGradeTest() {
		// 11.3秒，速度有点慢
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
	public void getRCGradeFailDistributionByGradeTest() {
		Integer grade = 2015;
		String year = "2017-2018";
		Integer term = 1;
		GradeFailDistribution gradeFailDistribution = new GradeFailDistribution();
		gradeFailDistribution = studentCourseService.getRCGradeFailDistributionByGrade(grade, year, term);
		System.out.println(gradeFailDistribution);
	}

	@Test
	public void getRCGradeFailDistributionListTest() {
		// 1.7秒
		String year = "2017-2018";
		Integer term = 1;
		List<GradeFailDistribution> gfdList = new ArrayList<>();
		gfdList = studentCourseService.getRCGradeFailDistributionList(year, term);
		for (GradeFailDistribution gradeFailDistribution : gfdList) {
			System.out.println(gradeFailDistribution);
		}
	}

	/*
	 * 测试第3章第2个功能
	 */

	@Test
	public void getDepartmentFailDistributionByDepartmentIdTest() {
		// 1.5秒
		Integer departmentId = 6;
		String year = "2017-2018";
		Integer term = 1;
		DepartmentFailDistribution departmentFailDistribution = studentCourseService
				.getRCDepartmentFailDistributionByDepartmentId(departmentId, year, term);
		System.out.println(departmentFailDistribution);
	}

	@Test
	public void testDepartmentFailDistributionByDepartmentIdTest() {
		// 10.4秒
		// 验证结果和原方法一致
		// 这个用于验证上面写的方法的正确性，不用于处理正式数据传给前端
		// 按理说，不及格门数为3的学生数应该多于不及格门数>=4的学生人数，但有部分不符合，可能计算误差或者所给数据不完整
		Integer departmentId = 6;
		String year = "2017-2018";
		Integer term = 1;
		DepartmentFailDistribution departmentFailDistribution = new DepartmentFailDistribution();
		departmentFailDistribution = studentCourseService
				.testGetRCDepartmentFailDistributionByDepartmentId(departmentId, year, term);
		System.out.println(departmentFailDistribution);
	}

	@Test
	public void getRCDepartmentFailDistributionListTest() {
		// 2.3秒
		String year = "2017-2018";
		Integer term = 1;
		List<DepartmentFailDistribution> dfdList = new ArrayList<>();
		dfdList = studentCourseService.getRCDepartmentFailDistributionList(year, term);
		for (DepartmentFailDistribution departmentFailDistribution : dfdList) {
			System.out.println(departmentFailDistribution);
		}
	}

	/*
	 * 测试第3章第3个功能
	 */

	@Test
	public void getRCGradeDepartmentFailDistributionListListTest() {
		// 2.7秒
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

	/*
	 * 测试第3章第4个功能
	 */

	@Test
	public void getRCClassFailDistributionByClassNumberTest() {
		// 1.5秒
		String classNumber = "20150111";
		String year = "2017-2018";
		Integer term = 1;
		ClassFailDistribution classFailDistribution = new ClassFailDistribution();
		classFailDistribution = studentCourseService.getRCClassFailDistributionByClassNumber(classNumber, year, term);
		System.out.println(classFailDistribution);
	}

	@Test
	public void getRCClassFailDistributionListByGradeTest() {
		// 6.4秒
		Integer grade = 2015;
		String year = "2017-2018";
		Integer term = 1;
		List<ClassFailDistribution> cfdList = new ArrayList<>();
		cfdList = studentCourseService.getRCClassFailDistributionListByGrade(grade, year, term);
		for (ClassFailDistribution classFailDistribution : cfdList) {
			System.out.println(classFailDistribution);
		}
	}

	/*
	 * 测试第3章第5个功能
	 */

	@Test
	public void getGradeAbsenceDistributionListTest() {
		// 2秒
		String year = "2017-2018";
		Integer term = 1;
		List<GradeAbsenceDistribution> gadList = new ArrayList<>();
		gadList = studentCourseService.getGradeAbsenceDistributionList(year, term);
		for (GradeAbsenceDistribution gradeAbsenceDistribution : gadList) {
			System.out.println(gradeAbsenceDistribution);
		}
	}

	/*
	 * 测试第4章第1个功能
	 */

	@Test
	public void getGradeByCourseNameTest() {
		String courseName = "大学英语（三）";
		String year = "2017-2018";
		Integer term = 1;
		String grade = studentCourseService.getGradeByCourseName(courseName, year, term);
		System.out.println(grade); // 2015
	}

	@Test
	public void getBasicCourseOverallDistributionListTest() {
		// 5.6秒
		String year = "2017-2018";
		Integer term = 1;
		List<BasicCourseOverallDistribution> bcodList = new ArrayList<>();
		bcodList = studentCourseService.getBasicCourseOverallDistributionList(year, term);
		for (BasicCourseOverallDistribution basicCourseOverallDistribution : bcodList) {
			System.out.println(basicCourseOverallDistribution);
		}
	}

	/*
	 * 测试第4章第2个功能
	 */

	@Test
	public void getBasicCourseDetailDistributionListTest() {
		// 5.3秒
		String courseName = "操作系统";
		String year = "2017-2018";
		Integer term = 1;
		List<BasicCourseDetailDistribution> bcddList = new ArrayList<>();
		bcddList = studentCourseService.getBasicCourseDetailDistributionListByCourseName(courseName, year, term);
		for (BasicCourseDetailDistribution basicCourseDetailDistribution : bcddList) {
			System.out.println(basicCourseDetailDistribution);
		}
	}

	@Test
	public void getBasicCourseDetailDistributionListListTest() {
		// 34秒，还是在多数课程学生人数为0的情况，太慢了
		String year = "2017-2018";
		Integer term = 1;
		List<List<BasicCourseDetailDistribution>> bcddListList = new ArrayList<>();
		bcddListList = studentCourseService.getBasicCourseDetailDistributionListList(year, term);
		for (List<BasicCourseDetailDistribution> bcddList : bcddListList) {
			for (BasicCourseDetailDistribution basicCourseDetailDistribution : bcddList) {
				System.out.println(basicCourseDetailDistribution);
			}
		}
	}

	/*
	 * 测试第4章第3章的功能
	 */

	@Test
	public void getBasicCourseClassDistributionByCourseNameAndClassNumberTest() {
		String courseName = "操作系统";
		String classNumber = "20150615";
		String year = "2017-2018";
		Integer term = 1;
		BasicCourseClassDistribution basicCourseClassDistribution = new BasicCourseClassDistribution();
		basicCourseClassDistribution = studentCourseService
				.getBasicCourseClassDistributionByCourseNameAndClassNumber(courseName, classNumber, year, term);
		System.out.println(basicCourseClassDistribution);
	}

	@Test
	public void getBasicCourseClassDistributionListByCourseNameTest() {
		// 16.7秒
		String courseName = "操作系统";
		String year = "2017-2018";
		Integer term = 1;
		List<BasicCourseClassDistribution> bccdList = new ArrayList<>();
		bccdList = studentCourseService.getBasicCourseClassDistributionListByCourseName(courseName, year, term);
		for (BasicCourseClassDistribution basicCourseClassDistribution : bccdList) {
			System.out.println(basicCourseClassDistribution);
		}
	}

	@Test
	public void getBasicCourseClassDistributionListListTest() {
		// 一个课程140+个班，10个课目前只有3个有成绩，共400+班，35.8秒
		String year = "2017-2018";
		Integer term = 1;
		List<List<BasicCourseClassDistribution>> bccdListList = new ArrayList<>();
		bccdListList = studentCourseService.getBasicCourseClassDistributionListList(year, term);
		for (List<BasicCourseClassDistribution> bccdList : bccdListList) {
			for (BasicCourseClassDistribution basicCourseClassDistribution : bccdList) {
				System.out.println(basicCourseClassDistribution);
			}
		}
	}
}
