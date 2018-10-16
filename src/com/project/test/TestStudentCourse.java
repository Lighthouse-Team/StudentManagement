package com.project.test;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
import com.project.dto.BasicCourseNormalDistribution;
import com.project.dto.BasicCourseOverallDistribution;
import com.project.dto.ClassExcellentFailDistribution;
import com.project.dto.ClassFailDistribution;
import com.project.dto.DepartmentAllGradeAverageScoreCompare;
import com.project.dto.DepartmentAllGradeFailDistribution;
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

	/*
	 * 测试第1章第1个功能
	 */

	@Test
	public void getACOverallDistributionByGradeTest() {
		Integer grade = 2015;
		String year = "2017-2018";
		Integer term = 2;
		OverallDistribution overallDistribution = studentCourseService.getACOverallDistributionByGrade(grade, year,
				term);
		System.out.println(overallDistribution);
	}

	@Test
	public void getACOverallDistributionListTest() {
		// 12秒
		String year = "2017-2018";
		Integer term = 2;
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
		String year = "2017-2018";
		Integer term = 1;
		OverallDistribution overallDistribution = studentCourseService.getAGOverallDistributionByCourseType(courseType,
				year, term);
		System.out.println(overallDistribution);
	}

	@Test
	public void getAGOverallDistributionListtTest() {
		// 8秒
		String year = "2017-2018";
		Integer term = 2;
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
		// 13秒
		String year = "2017-2018";
		Integer term = 2;
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
		Integer departmentId = 6;
		String year = "2017-2018";
		Integer term = 1;
		double averageScore = studentCourseService.getRPECAverageScoreByGradeAndDepartmentId(departmentId, grade, year,
				term);
		System.out.println(averageScore);
	}

	@Test
	public void getRPECDepartmentDistributionByGradeAndDepartmentIdTest() {
		Integer grade = 2015;
		Integer departmentId = 1;
		String year = "2017-2018";
		Integer term = 1;
		DepartmentDistribution departmentDistribution = studentCourseService
				.getRPECDepartmentDistributionByGradeAndDepartmentId(departmentId, grade, year, term);
		System.out.println(departmentDistribution);
	}

	@Test
	public void getRPECDepartmentDistributionListByGradeTest() {
		// 38秒，需要改善！！！
		Integer grade = 2015;
		String year = "2017-2018";
		Integer term = 2;
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
		for (int i = 0; i < classNumberList.size(); i++) {
			System.out.println(classNumberList.get(i));
		}
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
		// 95秒，需要改善！！！
		Integer grade = 2016;
		String year = "2017-2018";
		Integer term = 2;
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
	public void getRPECDepartmentAverageScoreCompareListByGradeTest() {
		// 34秒，一个年级所有院系的情况，需要改善！！！
		Integer grade = 2016;
		String year = "2017-2018";
		Integer term = 2;
		List<GradeDepartmentAverageScoreCompare> dascList = studentCourseService
				.getRPECGradeDepartmentAverageScoreCompareListByGrade(grade, year, term);
		for (GradeDepartmentAverageScoreCompare departmentAverageScoreCompare : dascList) {
			System.out.println(departmentAverageScoreCompare);
		}
	}

	@Test
	public void getRPECDepartmentAverageScoreCompareListListTest() {
		// 136秒
		String year = "2017-2018";
		Integer term = 2;
		List<List<GradeDepartmentAverageScoreCompare>> gdascListList = studentCourseService
				.getRPECGradeDepartmentAverageScoreCompareListList(year, term);
		for (List<GradeDepartmentAverageScoreCompare> gdascList : gdascListList) {
			for (GradeDepartmentAverageScoreCompare gdasc : gdascList) {
				System.out.println(gdasc);
			}
		}
	}

	@Test
	public void getRPECDepartmentAllGradeAverageScoreCompareListTest() {
		// 将ListList变成一个横向List
		// 94秒
		String year = "2017-2018";
		Integer term = 2;
		List<DepartmentAllGradeAverageScoreCompare> dagascList = studentCourseService
				.getRPECDepartmentAllGradeAverageScoreCompareList(year, term);
		for (DepartmentAllGradeAverageScoreCompare departmentAllGradeAverageScoreCompare : dagascList) {
			System.out.println(departmentAllGradeAverageScoreCompare);
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
		Integer term = 2;
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
	public void getRCDepartmentFailDistributionByDepartmentIdTest() {
		// 1.9秒
		Integer departmentId = 6;
		String year = "2017-2018";
		Integer term = 2;
		DepartmentFailDistribution departmentFailDistribution = studentCourseService
				.getRCDepartmentFailDistributionByDepartmentId(departmentId, year, term);
		System.out.println(departmentFailDistribution);
	}

	@Test
	public void testGetRCDepartmentFailDistributionByDepartmentIdTest() {
		// 183秒
		// 验证结果和原方法一致
		// 这个用于验证上面写的方法的正确性，不用于处理正式数据传给前端
		Integer departmentId = 6;
		String year = "2017-2018";
		Integer term = 2;
		DepartmentFailDistribution departmentFailDistribution = new DepartmentFailDistribution();
		departmentFailDistribution = studentCourseService
				.testGetRCDepartmentFailDistributionByDepartmentId(departmentId, year, term);
		System.out.println(departmentFailDistribution);
	}

	@Test
	public void getRCDepartmentFailDistributionListTest() {
		// 4.8秒
		String year = "2017-2018";
		Integer term = 2;
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
	public void getRCGradeDepartmentFailDistributionListByGradeTest() {
		Integer grade = 2015;
		String year = "2017-2018";
		Integer term = 1;
		List<GradeDepartmentFailDistribution> gdfdList = studentCourseService
				.getRCGradeDepartmentFailDistributionListByGrade(grade, year, term);
		for (GradeDepartmentFailDistribution gradeDepartmentFailDistribution : gdfdList) {
			System.out.println(gradeDepartmentFailDistribution);
		}
	}

	@Test
	public void getRCGradeDepartmentFailDistributionListListTest() {
		// 2.7秒
		String year = "2017-2018";
		Integer term = 2;
		List<List<GradeDepartmentFailDistribution>> gdfdListList = studentCourseService
				.getRCGradeDepartmentFailDistributionListList(year, term);
		for (List<GradeDepartmentFailDistribution> gdfdList : gdfdListList) {
			for (GradeDepartmentFailDistribution gradeDepartmentFailDistribution : gdfdList) {
				System.out.println(gradeDepartmentFailDistribution);
			}
		}
	}

	@Test
	public void getRCDepartmentAllGradeFailDistributionListTest() {
		// 将ListList变成了横向的List
		// 10秒
		String year = "2017-2018";
		Integer term = 2;
		List<DepartmentAllGradeFailDistribution> dagfdList = studentCourseService
				.getRCDepartmentAllGradeFailDistributionList(year, term);
		for (DepartmentAllGradeFailDistribution departmentAllGradeFailDistribution : dagfdList) {
			System.out.println(departmentAllGradeFailDistribution);
		}
	}

	/*
	 * 测试第3章第4个功能
	 */

	@Test
	public void getRCClassFailDistributionByClassNumberTest() {
		String classNumber = "20150111";
		String year = "2017-2018";
		Integer term = 1;
		ClassFailDistribution classFailDistribution = new ClassFailDistribution();
		classFailDistribution = studentCourseService.getRCClassFailDistributionByClassNumber(classNumber, year, term);
		System.out.println(classFailDistribution);
	}

	@Test
	public void getRCClassFailDistributionListByGradeTest() {
		// 23秒
		Integer grade = 2016;
		String year = "2017-2018";
		Integer term = 2;
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
		Integer term = 2;
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
		// 18秒
		String year = "2017-2018";
		Integer term = 2;
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
	public void getBasicCourseDetailDistributionListByCourseNameTest() {
		// 13秒
		String courseName = "工程实践A";
		String year = "2017-2018";
		Integer term = 2;
		List<BasicCourseDetailDistribution> bcddList = new ArrayList<>();
		bcddList = studentCourseService.getBasicCourseDetailDistributionListByCourseName(courseName, year, term);
		for (BasicCourseDetailDistribution basicCourseDetailDistribution : bcddList) {
			System.out.println(basicCourseDetailDistribution);
		}
	}

	@Test
	public void getBasicCourseDetailDistributionListListTest() {
		// 97秒，在导入2017-2018-2学期的所有数据，这个只有在打印时才会用到
		String year = "2017-2018";
		Integer term = 2;
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
	public void getCourseNormalDistributionByCourseNameTest() {
		String courseName = "大学英语（五）";
		String year = "2017-2018";
		Integer term = 1;
		BasicCourseNormalDistribution basicCourseNormalDistribution = new BasicCourseNormalDistribution();
		basicCourseNormalDistribution = studentCourseService.getBasicCourseNormalDistributionByCourseName(courseName,
				year, term);
		System.out.println(basicCourseNormalDistribution);
		System.out.println(basicCourseNormalDistribution.getOrdinateList());
		System.out.println(basicCourseNormalDistribution.getStudentNumberMap());
	}

	/*
	 * 测试第4章第4章的功能
	 */

	@Test
	public void getBasicCourseClassDistributionByCourseNameAndClassNumberTest() {
		String courseName = "大学英语（二）";
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
		// 78秒，148个班
		String courseName = "大学英语（六）";
		String year = "2017-2018";
		Integer term = 2;
		List<BasicCourseClassDistribution> bccdList = new ArrayList<>();
		bccdList = studentCourseService.getBasicCourseClassDistributionListByCourseName(courseName, year, term);
		for (BasicCourseClassDistribution basicCourseClassDistribution : bccdList) {
			System.out.println(basicCourseClassDistribution);
		}
	}

	/*
	 * 测试成绩文件是否被重复导入
	 */
	@Test
	public void fileIsInsertedTest() {
		String filePath = "E:/实验室/学生管理系统/2017-2018-1成绩/2017级.xls";
		boolean flag = studentCourseService.isFileInsertedByFilePath(filePath);
		System.out.println(flag);
	}

	@Test
	public void deleteFileTest() {
		String deleteFilePath = "D:\\Program Files\\eclipse\\uploadFile\\2014级.xls";
		File deleteFile = new File(deleteFilePath);
		boolean deleteFlag = deleteFile.delete();
		if (deleteFlag) {
			System.out.println("删除成功");
		} else {
			System.out.println("删除失败");
		}
	}

}
