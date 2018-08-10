package com.project.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.beans.StudentCourse;
import com.project.dao.StudentCourseMapper;
import com.project.dao.StudentMapper;
import com.project.dto.ClassExcellentFailDistribution;
import com.project.dto.ClassFailDistribution;
import com.project.dto.GradeDepartmentAverageScoreCompare;
import com.project.dto.GradeDepartmentFailDistribution;
import com.project.dto.DepartmentDistribution;
import com.project.dto.DepartmentFailDistribution;
import com.project.dto.GradeAbsenceDistribution;
import com.project.dto.GradeFailDistribution;
import com.project.dto.OverallDistribution;

@Service
public class StudentCourseService {

	@Autowired
	private StudentCourseMapper studentCourseMapper;

	/**
	 * 通过 studentCourse 属性查询选课信息
	 * 
	 * @param studentCourse
	 * @return
	 */
	public StudentCourse getStudentCourseByEntity(StudentCourse studentCourse) {
		return studentCourseMapper.getStudentCourseByEntity(studentCourse);
	}

	/**
	 * 添加学生课程信息并返回主键值
	 * 
	 * @param studentCourse
	 * @return
	 */
	public void insertStudentCourse(StudentCourse studentCourse) {
		studentCourseMapper.addStudentCourse(studentCourse);
		// studentCourse = getStudentCourseByEntity(studentCourse); // 注释了节省导入数据库的时间
		// if (studentCourse != null) {
		// return studentCourse.getScId();
		// } else {
		// return -1; // 插入异常
		// }
	}

	/**
	 * 通过 studentCourse 属性查询学生课程信息列表
	 * 
	 * @param studentcourse
	 * @return
	 */
	public List<StudentCourse> getStudentCourseListByEntityForLike(StudentCourse studentcourse) {
		return studentCourseMapper.getStudentCourseListByEntityForLike(studentcourse);
	}

	/**
	 * 下面为前端展示数据需要的接口
	 */

	/*
	 * ======通过 grade 属性获得AC的考试成绩的分布，AC指所有课程，包括：必修课、专业选修课、通识选修课======
	 */

	/**
	 * 通过 grade 获得该年级该学期AC的加权总分
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public double getACTotalScoreByGrade(Integer grade, String year, Integer term) {
		Integer totalScoreRecordNumber = studentCourseMapper.getACTotalSocreRecordNumberByGrade(grade, year, term);
		if (totalScoreRecordNumber != 0) {
			return studentCourseMapper.getACTotalScoreByGrade(grade, year, term);
		} else {
			return 0; // 成绩记录数为0，总分也是0
		}
	}

	/**
	 * 通过 grade 获得该年级该学期AC的总学分
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public double getACTotalCreditsByGrade(Integer grade, String year, Integer term) {
		Integer totalScoreRecordNumber = studentCourseMapper.getACTotalSocreRecordNumberByGrade(grade, year, term);
		if (totalScoreRecordNumber != 0) {
			return studentCourseMapper.getACTotalCreditsByGrade(grade, year, term);
		} else {
			return 0; // 成绩记录数为0，总学分也是0
		}
	}

	/**
	 * 通过 grade 查询该年级该学期AC的平均分
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public double getACAverageScoreByGrade(Integer grade, String year, Integer term) {
		double totalScore = getACTotalScoreByGrade(grade, year, term); // 加权总分
		double totalCredits = getACTotalCreditsByGrade(grade, year, term); // 总学分
		if (totalCredits != 0) {
			double averageScore = totalScore / totalCredits;
			return averageScore;
		} else {
			return -1; // 除数异常
		}
	}

	/**
	 * 查询全校该学期AC的平均分
	 * 
	 * @param year
	 * @param term
	 * @return
	 */
	public double getUniversityACAverageScore(String year, Integer term) {
		String strGradeOne = year.substring(0, 4);
		Integer gradeOne = Integer.parseInt(strGradeOne);
		double totalScore = 0;
		double totalCredits = 0;
		for (Integer gradeI = gradeOne - 3; gradeI <= gradeOne; gradeI++) {
			totalScore += getACTotalScoreByGrade(gradeI, year, term);
			totalCredits += getACTotalCreditsByGrade(gradeI, year, term);
		}
		if (totalCredits != 0) {
			double averageScore = totalScore / totalCredits;
			return averageScore;
		} else {
			return -1; // 除数异常
		}
	}

	/**
	 * 通过 grade 属性查询该年级该学期AC的分布
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public OverallDistribution getACScoreDistributionByGrade(Integer grade, String year, Integer term) {
		Integer totalNumber = studentCourseMapper.getACTotalSocreRecordNumberByGrade(grade, year, term); // 成绩记录总数
		Integer excellentNumber = studentCourseMapper.getACExcellentScoreRecordNumberByGrade(grade, year, term); // 优秀成绩记录数
		Integer goodNumber = studentCourseMapper.getACGoodScoreRecordNumberByGrade(grade, year, term); // 良好成绩记录数
		Integer mediumNumber = studentCourseMapper.getACMediumScoreRecordNumberByGrade(grade, year, term); // 中等成绩记录数
		Integer passNumber = studentCourseMapper.getACPassScoreRecordNumberByGrade(grade, year, term); // 及格成绩记录数
		Integer failNumber = totalNumber - excellentNumber - goodNumber - mediumNumber - passNumber; // 不及格成绩记录数
		OverallDistribution overallDistribution = new OverallDistribution();
		String strGrade = String.valueOf(grade);
		overallDistribution.setGrade(strGrade);
		if (totalNumber != 0) {
			double excellentRate = (double) excellentNumber / totalNumber; // 优秀率
			double goodRate = (double) goodNumber / totalNumber; // 良好率
			double mediumRate = (double) mediumNumber / totalNumber; // 中等率
			double passRate = (double) passNumber / totalNumber; // 及格率
			double failRate = (double) failNumber / totalNumber; // 不及格率
			double averageScore = getACAverageScoreByGrade(grade, year, term); // 平均分

			DecimalFormat rateDF = new DecimalFormat("0.00%");
			DecimalFormat scoreDF = new DecimalFormat("0.00");
			String strExcellentRate = rateDF.format(excellentRate);
			String strGoodRate = rateDF.format(goodRate);
			String strMediumRate = rateDF.format(mediumRate);
			String strPassRate = rateDF.format(passRate);
			String strFailRate = rateDF.format(failRate);
			String strAverageScore = scoreDF.format(averageScore);

			overallDistribution.setTotalNumber(totalNumber);
			overallDistribution.setExcellentNumber(excellentNumber);
			overallDistribution.setGoodNumber(goodNumber);
			overallDistribution.setMediumNumber(mediumNumber);
			overallDistribution.setPassNumber(passNumber);
			overallDistribution.setFailNumber(failNumber);
			overallDistribution.setAverageScore(strAverageScore);
			overallDistribution.setExcellentRate(strExcellentRate);
			overallDistribution.setGoodRate(strGoodRate);
			overallDistribution.setMediumRate(strMediumRate);
			overallDistribution.setPassRate(strPassRate);
			overallDistribution.setFailRate(strFailRate);
		} else {
			overallDistribution.initValue();
		}
		return overallDistribution;
	}

	/**
	 * 查询全校该学期AC的成绩分布
	 * 
	 * @param year
	 * @param term
	 * @return
	 */
	public List<OverallDistribution> getUniversityACScoreDistributionList(String year, Integer term) {
		String strGradeOne = year.substring(0, 4);
		Integer gradeOne = Integer.parseInt(strGradeOne);
		List<OverallDistribution> odList = new ArrayList<>();
		Integer totalNumber = 0; // 成绩记录总数
		Integer excellentNumber = 0; // 优秀成绩记录数
		Integer goodNumber = 0; // 良好成绩记录数
		Integer mediumNumber = 0; // 中等成绩记录数
		Integer passNumber = 0; // 及格成绩记录数
		for (Integer gradeI = gradeOne - 3; gradeI <= gradeOne; gradeI++) {
			OverallDistribution od = getACScoreDistributionByGrade(gradeI, year, term);
			odList.add(od);
			totalNumber += od.getTotalNumber();
			excellentNumber += od.getExcellentNumber();
			goodNumber += od.getGoodNumber();
			mediumNumber += od.getMediumNumber();
			passNumber += od.getPassNumber();
		}
		Integer failNumber = totalNumber - excellentNumber - goodNumber - mediumNumber - passNumber; // 不及格成绩记录数
		OverallDistribution overallDistribution = new OverallDistribution();
		overallDistribution.setGrade("全校");
		if (totalNumber != 0) {
			double excellentRate = (double) excellentNumber / totalNumber; // 优秀率
			double goodRate = (double) goodNumber / totalNumber; // 良好率
			double mediumRate = (double) mediumNumber / totalNumber; // 中等率
			double passRate = (double) passNumber / totalNumber; // 及格率
			double failRate = (double) failNumber / totalNumber; // 不及格率
			double averageScore = getUniversityACAverageScore(year, term); // 平均分

			DecimalFormat rateDF = new DecimalFormat("0.00%");
			DecimalFormat scoreDF = new DecimalFormat("0.00");
			String strExcellentRate = rateDF.format(excellentRate);
			String strGoodRate = rateDF.format(goodRate);
			String strMediumRate = rateDF.format(mediumRate);
			String strPassRate = rateDF.format(passRate);
			String strFailRate = rateDF.format(failRate);
			String strAverageScore = scoreDF.format(averageScore);

			overallDistribution.setTotalNumber(totalNumber);
			overallDistribution.setExcellentNumber(excellentNumber);
			overallDistribution.setGoodNumber(goodNumber);
			overallDistribution.setMediumNumber(mediumNumber);
			overallDistribution.setPassNumber(passNumber);
			overallDistribution.setFailNumber(failNumber);
			overallDistribution.setAverageScore(strAverageScore);
			overallDistribution.setExcellentRate(strExcellentRate);
			overallDistribution.setGoodRate(strGoodRate);
			overallDistribution.setMediumRate(strMediumRate);
			overallDistribution.setPassRate(strPassRate);
			overallDistribution.setFailRate(strFailRate);
		} else {
			overallDistribution.initValue();
		}
		odList.add(overallDistribution);
		return odList;
	}

	/*
	 * ======通过 courseType 属性获得AG该课程的考试成绩分布，AG指所有年级======
	 */

	/**
	 * 通过 courseType 获得AG该学期该课程的加权总分
	 * 
	 * @param courseType
	 * @param year
	 * @param term
	 * @return
	 */
	public double getAGTotalScoreByCourseType(Integer courseType, String year, Integer term) {
		Integer totalScoreRecordNumber = studentCourseMapper.getAGTotalSocreRecordNumberByCourseType(courseType, year,
				term);
		if (totalScoreRecordNumber != 0) {
			return studentCourseMapper.getAGTotalScoreByCourseType(courseType, year, term);
		} else {
			return 0; // 成绩记录数为0，总分也是0
		}
	}

	/**
	 * 通过 courseType 获得AG该学期该课程的总学分
	 * 
	 * @param courseType
	 * @param year
	 * @param term
	 * @return
	 */
	public double getAGTotalCreditsByCourseType(Integer courseType, String year, Integer term) {
		Integer totalScoreRecordNumber = studentCourseMapper.getAGTotalSocreRecordNumberByCourseType(courseType, year,
				term);
		if (totalScoreRecordNumber != 0) {
			return studentCourseMapper.getAGTotalCreditsByCourseType(courseType, year, term);
		} else {
			return 0; // 成绩记录数为0，总学分也是0
		}
	}

	/**
	 * 通过 courseType 获得AG该学期该课程的平均分
	 * 
	 * @param courseType
	 * @param year
	 * @param term
	 * @return
	 */
	public double getAGAverageScoreByCourseType(Integer courseType, String year, Integer term) {
		double totalScore = studentCourseMapper.getAGTotalScoreByCourseType(courseType, year, term);
		double totalCredits = studentCourseMapper.getAGTotalCreditsByCourseType(courseType, year, term);
		if (totalCredits != 0) {
			double averageScore = totalScore / totalCredits;
			return averageScore;
		} else {
			return -1; // 除数异常
		}
	}

	/**
	 * 通过 courseType 获得AG该学期该课程的成绩分布
	 * 
	 * @param courseType
	 * @param year
	 * @param term
	 * @return
	 */
	public OverallDistribution getAGScoreDistributionByCourseType(Integer courseType, String year, Integer term) {
		Integer totalNumber = studentCourseMapper.getAGTotalSocreRecordNumberByCourseType(courseType, year, term);
		Integer excellentNumber = studentCourseMapper.getAGExcellentScoreRecordNumberByCourseType(courseType, year,
				term);
		Integer goodNumber = studentCourseMapper.getAGGoodScoreRecordNumberByCourseType(courseType, year, term);
		Integer mediumNumber = studentCourseMapper.getAGMediumScoreRecordNumberByCourseType(courseType, year, term);
		Integer passNumber = studentCourseMapper.getAGPassScoreRecordNumberByCourseType(courseType, year, term);
		Integer failNumber = totalNumber - excellentNumber - goodNumber - mediumNumber - passNumber;
		String strCourseType = null;
		switch (courseType) {
		case 1:
			strCourseType = "必修";
			break;
		case 2:
			strCourseType = "专业选修";
			break;
		case 3:
			strCourseType = "通识选修";
		}
		OverallDistribution overallDistribution = new OverallDistribution();
		overallDistribution.setCourseType(strCourseType);
		if (totalNumber != 0) {
			double excellentRate = (double) excellentNumber / totalNumber;
			double goodRate = (double) goodNumber / totalNumber;
			double mediumRate = (double) mediumNumber / totalNumber;
			double passRate = (double) passNumber / totalNumber;
			double failRate = (double) failNumber / totalNumber;
			double averageScore = getAGAverageScoreByCourseType(courseType, year, term);

			DecimalFormat rateDF = new DecimalFormat("0.00%");
			DecimalFormat scoreDF = new DecimalFormat("0.00");
			String strExcellentRate = rateDF.format(excellentRate);
			String strGoodRate = rateDF.format(goodRate);
			String strMediumRate = rateDF.format(mediumRate);
			String strPassRate = rateDF.format(passRate);
			String strFailRate = rateDF.format(failRate);
			String strAverageScore = scoreDF.format(averageScore);

			overallDistribution.setTotalNumber(totalNumber);
			overallDistribution.setExcellentNumber(excellentNumber);
			overallDistribution.setGoodNumber(goodNumber);
			overallDistribution.setMediumNumber(mediumNumber);
			overallDistribution.setPassNumber(passNumber);
			overallDistribution.setFailNumber(failNumber);
			overallDistribution.setAverageScore(strAverageScore);
			overallDistribution.setExcellentRate(strExcellentRate);
			overallDistribution.setGoodRate(strGoodRate);
			overallDistribution.setMediumRate(strMediumRate);
			overallDistribution.setPassRate(strPassRate);
			overallDistribution.setFailRate(strFailRate);
		} else {
			overallDistribution.initValue();
		}
		return overallDistribution;
	}

	/**
	 * 查询全校该学期AC的的成绩分布
	 * 
	 * @param year
	 * @param term
	 * @return
	 */
	public List<OverallDistribution> getUniversityAGScoreDistributionList(String year, Integer term) {
		List<OverallDistribution> odList = new ArrayList<>();
		Integer totalNumber = 0;
		Integer excellentNumber = 0;
		Integer goodNumber = 0;
		Integer mediumNumber = 0;
		Integer passNumber = 0;
		for (Integer courseType = 1; courseType <= 3; courseType++) {
			OverallDistribution od = getAGScoreDistributionByCourseType(courseType, year, term);
			odList.add(od);
			totalNumber += od.getTotalNumber();
			excellentNumber += od.getExcellentNumber();
			goodNumber += od.getGoodNumber();
			mediumNumber += od.getMediumNumber();
			passNumber += od.getPassNumber();
		}
		Integer failNumber = totalNumber - excellentNumber - goodNumber - mediumNumber - passNumber;
		OverallDistribution overallDistribution = new OverallDistribution();
		overallDistribution.setCourseType("全校");
		if (totalNumber != 0) {
			double excellentRate = (double) excellentNumber / totalNumber;
			double goodRate = (double) goodNumber / totalNumber;
			double mediumRate = (double) mediumNumber / totalNumber;
			double passRate = (double) passNumber / totalNumber;
			double failRate = (double) failNumber / totalNumber;
			double averageScore = getUniversityACAverageScore(year, term);

			DecimalFormat rateDF = new DecimalFormat("0.00%");
			DecimalFormat scoreDF = new DecimalFormat("0.00");
			String strExcellentRate = rateDF.format(excellentRate);
			String strGoodRate = rateDF.format(goodRate);
			String strMediumRate = rateDF.format(mediumRate);
			String strPassRate = rateDF.format(passRate);
			String strFailRate = rateDF.format(failRate);
			String strAverageScore = scoreDF.format(averageScore);

			overallDistribution.setTotalNumber(totalNumber);
			overallDistribution.setExcellentNumber(excellentNumber);
			overallDistribution.setGoodNumber(goodNumber);
			overallDistribution.setMediumNumber(mediumNumber);
			overallDistribution.setPassNumber(passNumber);
			overallDistribution.setFailNumber(failNumber);
			overallDistribution.setAverageScore(strAverageScore);
			overallDistribution.setExcellentRate(strExcellentRate);
			overallDistribution.setGoodRate(strGoodRate);
			overallDistribution.setMediumRate(strMediumRate);
			overallDistribution.setPassRate(strPassRate);
			overallDistribution.setFailRate(strFailRate);
		} else {
			overallDistribution.initValue();
		}
		odList.add(overallDistribution);
		return odList;
	}

	/*
	 * ======通过 grade 属性获得AC的考试成绩的分布，AC指所有课程，包括：必修课、专业选修课、通识选修课======
	 */

	/**
	 * 通过 grade 获得该年级该学期RPEC的加权总分
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public double getRPECTotalScoreByGrade(Integer grade, String year, Integer term) {
		Integer totalScoreRecordNumber = studentCourseMapper.getRPECTotalSocreRecordNumberByGrade(grade, year, term);
		if (totalScoreRecordNumber != 0) {
			return studentCourseMapper.getRPECTotalScoreByGrade(grade, year, term);
		} else {
			return 0; // 成绩记录数为0，总分也是0
		}
	}

	/**
	 * 通过 grade 获得该年级该学期RPEC的总学分
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public double getRPECTotalCreditsByGrade(Integer grade, String year, Integer term) {
		Integer totalScoreRecordNumber = studentCourseMapper.getRPECTotalSocreRecordNumberByGrade(grade, year, term);
		if (totalScoreRecordNumber != 0) {
			return studentCourseMapper.getRPECTotalCreditsByGrade(grade, year, term);
		} else {
			return 0; // 成绩记录数为0，总学分也是0
		}
	}

	/**
	 * 通过 grade 查询该年级该学期RPEC的平均分
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public double getRPECAverageScoreByGrade(Integer grade, String year, Integer term) {
		double totalScore = getRPECTotalScoreByGrade(grade, year, term); // 加权总分
		double totalCredits = getRPECTotalCreditsByGrade(grade, year, term); // 总学分
		if (totalCredits != 0) {
			double averageScore = totalScore / totalCredits;
			return averageScore;
		} else {
			return -1; // 除数异常
		}
	}

	/**
	 * 查询全校该学期RPEC的平均分
	 * 
	 * @param year
	 * @param term
	 * @return
	 */
	public double getUniversityRPECAverageScore(String year, Integer term) {
		String strGradeOne = year.substring(0, 4);
		Integer gradeOne = Integer.parseInt(strGradeOne);
		double totalScore = 0;
		double totalCredits = 0;
		for (Integer gradeI = gradeOne - 3; gradeI <= gradeOne; gradeI++) {
			totalScore += getRPECTotalScoreByGrade(gradeI, year, term);
			totalCredits += getRPECTotalCreditsByGrade(gradeI, year, term);
		}
		if (totalCredits != 0) {
			double averageScore = totalScore / totalCredits;
			return averageScore;
		} else {
			return -1; // 除数异常
		}
	}

	/**
	 * 通过 grade 属性查询该年级该学期RPEC的分布
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public OverallDistribution getRPECScoreDistributionByGrade(Integer grade, String year, Integer term) {
		Integer totalNumber = studentCourseMapper.getRPECTotalSocreRecordNumberByGrade(grade, year, term); // 成绩记录总数
		Integer excellentNumber = studentCourseMapper.getRPECExcellentScoreRecordNumberByGrade(grade, year, term); // 优秀成绩记录数
		Integer goodNumber = studentCourseMapper.getRPECGoodScoreRecordNumberByGrade(grade, year, term); // 良好成绩记录数
		Integer mediumNumber = studentCourseMapper.getRPECMediumScoreRecordNumberByGrade(grade, year, term); // 中等成绩记录数
		Integer passNumber = studentCourseMapper.getRPECPassScoreRecordNumberByGrade(grade, year, term); // 及格成绩记录数
		Integer failNumber = totalNumber - excellentNumber - goodNumber - mediumNumber - passNumber; // 不及格成绩记录数
		OverallDistribution overallDistribution = new OverallDistribution();
		String strGrade = String.valueOf(grade);
		overallDistribution.setGrade(strGrade);
		if (totalNumber != 0) {
			double excellentRate = (double) excellentNumber / totalNumber; // 优秀率
			double goodRate = (double) goodNumber / totalNumber; // 良好率
			double mediumRate = (double) mediumNumber / totalNumber; // 中等率
			double passRate = (double) passNumber / totalNumber; // 及格率
			double failRate = (double) failNumber / totalNumber; // 不及格率
			double averageScore = getRPECAverageScoreByGrade(grade, year, term); // 平均分

			DecimalFormat rateDF = new DecimalFormat("0.00%");
			DecimalFormat scoreDF = new DecimalFormat("0.00");
			String strExcellentRate = rateDF.format(excellentRate);
			String strGoodRate = rateDF.format(goodRate);
			String strMediumRate = rateDF.format(mediumRate);
			String strPassRate = rateDF.format(passRate);
			String strFailRate = rateDF.format(failRate);
			String strAverageScore = scoreDF.format(averageScore);

			overallDistribution.setTotalNumber(totalNumber);
			overallDistribution.setExcellentNumber(excellentNumber);
			overallDistribution.setGoodNumber(goodNumber);
			overallDistribution.setMediumNumber(mediumNumber);
			overallDistribution.setPassNumber(passNumber);
			overallDistribution.setFailNumber(failNumber);
			overallDistribution.setAverageScore(strAverageScore);
			overallDistribution.setExcellentRate(strExcellentRate);
			overallDistribution.setGoodRate(strGoodRate);
			overallDistribution.setMediumRate(strMediumRate);
			overallDistribution.setPassRate(strPassRate);
			overallDistribution.setFailRate(strFailRate);
		} else {
			overallDistribution.initValue();
		}
		return overallDistribution;
	}

	/**
	 * 查询全校该学期RPEC的成绩分布
	 * 
	 * @param year
	 * @param term
	 * @return
	 */
	public List<OverallDistribution> getUniversityRPECScoreDistributionList(String year, Integer term) {
		String strGradeOne = year.substring(0, 4);
		Integer gradeOne = Integer.parseInt(strGradeOne);
		List<OverallDistribution> odList = new ArrayList<>();
		Integer totalNumber = 0; // 成绩记录总数
		Integer excellentNumber = 0; // 优秀成绩记录数
		Integer goodNumber = 0; // 良好成绩记录数
		Integer mediumNumber = 0; // 中等成绩记录数
		Integer passNumber = 0; // 及格成绩记录数
		for (Integer gradeI = gradeOne - 3; gradeI <= gradeOne; gradeI++) {
			OverallDistribution od = getRPECScoreDistributionByGrade(gradeI, year, term);
			odList.add(od);
			totalNumber += od.getTotalNumber();
			excellentNumber += od.getExcellentNumber();
			goodNumber += od.getGoodNumber();
			mediumNumber += od.getMediumNumber();
			passNumber += od.getPassNumber();
		}
		Integer failNumber = totalNumber - excellentNumber - goodNumber - mediumNumber - passNumber; // 不及格成绩记录数
		OverallDistribution overallDistribution = new OverallDistribution();
		overallDistribution.setGrade("全校");
		if (totalNumber != 0) {
			double excellentRate = (double) excellentNumber / totalNumber; // 优秀率
			double goodRate = (double) goodNumber / totalNumber; // 良好率
			double mediumRate = (double) mediumNumber / totalNumber; // 中等率
			double passRate = (double) passNumber / totalNumber; // 及格率
			double failRate = (double) failNumber / totalNumber; // 不及格率
			double averageScore = getUniversityRPECAverageScore(year, term); // 平均分

			DecimalFormat rateDF = new DecimalFormat("0.00%");
			DecimalFormat scoreDF = new DecimalFormat("0.00");
			String strExcellentRate = rateDF.format(excellentRate);
			String strGoodRate = rateDF.format(goodRate);
			String strMediumRate = rateDF.format(mediumRate);
			String strPassRate = rateDF.format(passRate);
			String strFailRate = rateDF.format(failRate);
			String strAverageScore = scoreDF.format(averageScore);

			overallDistribution.setTotalNumber(totalNumber);
			overallDistribution.setExcellentNumber(excellentNumber);
			overallDistribution.setGoodNumber(goodNumber);
			overallDistribution.setMediumNumber(mediumNumber);
			overallDistribution.setPassNumber(passNumber);
			overallDistribution.setFailNumber(failNumber);
			overallDistribution.setAverageScore(strAverageScore);
			overallDistribution.setExcellentRate(strExcellentRate);
			overallDistribution.setGoodRate(strGoodRate);
			overallDistribution.setMediumRate(strMediumRate);
			overallDistribution.setPassRate(strPassRate);
			overallDistribution.setFailRate(strFailRate);
		} else {
			overallDistribution.initValue();
		}
		odList.add(overallDistribution);
		return odList;
	}

	/*
	 * ======通过 grade和departmentName 属性获得RPEC的考试成绩的分布，RPEC指必修课、专业选修课======
	 */

	/**
	 * 通过 departmentId 获得 departmentName
	 * 
	 * @param departmentId
	 * @return
	 */
	public String getDepartmentNameByDepartmentId(Integer departmentId) {
		if (departmentId == 0) {
			return "陈赓班";
		} else if (departmentId == 1) {
			return "船舶学院";
		} else if (departmentId == 2) {
			return "航建学院";
		} else if (departmentId == 3) {
			return "动力学院";
		} else if (departmentId == 4) {
			return "自动化学院";
		} else if (departmentId == 5) {
			return "水声学院";
		} else if (departmentId == 6) {
			return "计、软、保学院";
		} else if (departmentId == 7) {
			return "机电学院";
		} else if (departmentId == 8) {
			return "信通学院";
		} else if (departmentId == 9) {
			return "经管学院";
		} else if (departmentId == 10) {
			return "材化学院";
		} else if (departmentId == 11) {
			return "理学院";
		} else if (departmentId == 12) {
			return "外语系";
		} else if (departmentId == 13) {
			return "人文学院";
		} else if (departmentId == 14) {
			return "国际学院";
		} else if (departmentId == 15) {
			return "核学院";
		} else if (departmentId == 16) {
			return "体育部";
		} else if (departmentId == 17) {
			return "马克思学院";
		} else if (departmentId == 18) {
			return "国防学院";
		} else {
			return null; // 学院异常
		}
	}

	/**
	 * 通过 grade和departmentId 获得该学院该年级该学期RPEC的加权总分
	 * 
	 * @param departmentId
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public double getRPECTotalScoreByGradeAndDepartmentId(Integer departmentId, Integer grade, String year,
			Integer term) {
		Integer totalScoreRecordNumber = studentCourseMapper
				.getRPECTotalScoreRecordNumberByGradeAndDepartmentId(departmentId, grade, year, term);
		if (totalScoreRecordNumber != 0) {
			return studentCourseMapper.getRPECTotalScoreByGradeAndDepartmentId(departmentId, grade, year, term);
		} else {
			return 0; // 成绩记录数为0，总分也是0
		}
	}

	/**
	 * 通过 grade和departmentId 获得该学院该年级该学期RPEC的总学分
	 * 
	 * @param departmentId
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public double getRPECTotalCreditsByGradeAndDepartmentId(Integer departmentId, Integer grade, String year,
			Integer term) {
		Integer totalScoreRecordNumber = studentCourseMapper
				.getRPECTotalScoreRecordNumberByGradeAndDepartmentId(departmentId, grade, year, term);
		if (totalScoreRecordNumber != 0) {
			return studentCourseMapper.getRPECTotalCreditsByGradeAndDepartmentId(departmentId, grade, year, term);
		} else {
			return 0; // 成绩记录数为0，总分也是0
		}
	}

	/**
	 * 通过 grade和departmentId 获得该学院该年级该学期RPEC的平均分
	 * 
	 * @param departmentId
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public double getRPECAverageScoreByGradeAndDepartmentId(Integer departmentId, Integer grade, String year,
			Integer term) {
		double totalScore, totalCredits;
		if (departmentId != 6) {
			totalScore = getRPECTotalScoreByGradeAndDepartmentId(departmentId, grade, year, term); // 加权总分
			totalCredits = getRPECTotalCreditsByGradeAndDepartmentId(departmentId, grade, year, term); // 总学分
		} else {
			totalScore = totalCredits = 0;
			List<Integer> departmentIdList = new ArrayList<>(Arrays.asList(6, 20, 21));
			for (Integer id : departmentIdList) {
				totalScore += getRPECTotalScoreByGradeAndDepartmentId(id, grade, year, term);
				totalCredits += getRPECTotalCreditsByGradeAndDepartmentId(id, grade, year, term);
			}
		}
		if (totalCredits != 0) {
			double averageScore = totalScore / totalCredits;
			return averageScore;
		} else {
			return -1; // 除数异常
		}
	}

	/**
	 * 通过 grade和departmentId 获得该学院该年级该学期RPEC的分布
	 * 
	 * @param departmentId
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public DepartmentDistribution getRPECScoreDistributionByGradeAndDepartmentId(Integer departmentId, Integer grade,
			String year, Integer term) {
		Integer totalNumber, excellentNumber, goodNumber, mediumNumber, passNumber;
		if (departmentId != 6) {
			totalNumber = studentCourseMapper.getRPECTotalScoreRecordNumberByGradeAndDepartmentId(departmentId, grade,
					year, term); // 成绩记录总数
			excellentNumber = studentCourseMapper.getRPECExcellentScoreRecordNumberByGradeAndDepartmentId(departmentId,
					grade, year, term); // 优秀成绩记录数
			goodNumber = studentCourseMapper.getRPECGoodScoreRecordNumberByGradeAndDepartmentId(departmentId, grade,
					year, term); // 良好成绩记录数
			mediumNumber = studentCourseMapper.getRPECMediumScoreRecordNumberByGradeAndDepartmentId(departmentId, grade,
					year, term); // 中等成绩记录数
			passNumber = studentCourseMapper.getRPECPassScoreRecordNumberByGradeAndDepartmentId(departmentId, grade,
					year, term); // 及格成绩记录数
		} else {
			List<Integer> departmentIdList = Arrays.asList(6, 20, 21);
			totalNumber = excellentNumber = goodNumber = mediumNumber = passNumber = 0;
			for (Integer id : departmentIdList) {
				totalNumber += studentCourseMapper.getRPECTotalScoreRecordNumberByGradeAndDepartmentId(id, grade, year,
						term);
				excellentNumber += studentCourseMapper.getRPECExcellentScoreRecordNumberByGradeAndDepartmentId(id,
						grade, year, term);
				goodNumber += studentCourseMapper.getRPECGoodScoreRecordNumberByGradeAndDepartmentId(id, grade, year,
						term);
				mediumNumber += studentCourseMapper.getRPECMediumScoreRecordNumberByGradeAndDepartmentId(id, grade,
						year, term);
				passNumber += studentCourseMapper.getRPECPassScoreRecordNumberByGradeAndDepartmentId(id, grade, year,
						term);
			}
		}
		Integer failNumber = totalNumber - excellentNumber - goodNumber - mediumNumber - passNumber; // 不及格成绩记录数
		DepartmentDistribution departmentDistribution = new DepartmentDistribution();
		String departmentName = getDepartmentNameByDepartmentId(departmentId);
		String strGrade = String.valueOf(grade);
		departmentDistribution.setDepartmentName(departmentName);
		departmentDistribution.setGrade(strGrade);
		if (totalNumber != 0) {
			double excellentRate = (double) excellentNumber / totalNumber; // 优秀率
			double goodRate = (double) goodNumber / totalNumber; // 良好率
			double mediumRate = (double) mediumNumber / totalNumber; // 中等率
			double passRate = (double) passNumber / totalNumber; // 及格率
			double failRate = (double) failNumber / totalNumber; // 不及格率
			double averageScore = getRPECAverageScoreByGradeAndDepartmentId(departmentId, grade, year, term); // 平均分

			DecimalFormat rateDF = new DecimalFormat("0.00%");
			DecimalFormat scoreDF = new DecimalFormat("0.00");
			String strExcellentRate = rateDF.format(excellentRate);
			String strGoodRate = rateDF.format(goodRate);
			String strMediumRate = rateDF.format(mediumRate);
			String strPassRate = rateDF.format(passRate);
			String strFailRate = rateDF.format(failRate);
			String strAverageScore = scoreDF.format(averageScore);

			departmentDistribution.setTotalNumber(totalNumber);
			departmentDistribution.setExcellentNumber(excellentNumber);
			departmentDistribution.setGoodNumber(goodNumber);
			departmentDistribution.setMediumNumber(mediumNumber);
			departmentDistribution.setPassNumber(passNumber);
			departmentDistribution.setFailNumber(failNumber);
			departmentDistribution.setAverageScore(strAverageScore);
			departmentDistribution.setExcellentRate(strExcellentRate);
			departmentDistribution.setGoodRate(strGoodRate);
			departmentDistribution.setMediumRate(strMediumRate);
			departmentDistribution.setPassRate(strPassRate);
			departmentDistribution.setFailRate(strFailRate);
		} else {
			departmentDistribution.initValue();
		}
		return departmentDistribution;
	}

	/**
	 * 查询学院该年级该学期RPEC的成绩分布
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public List<DepartmentDistribution> getDepartmentRPECScoreDistributionListByGrade(Integer grade, String year,
			Integer term) {
		List<DepartmentDistribution> ddList = new ArrayList<>();
		Integer totalNumber = 0; // 成绩记录总数
		Integer excellentNumber = 0; // 优秀成绩记录数
		Integer goodNumber = 0; // 良好成绩记录数
		Integer mediumNumber = 0; // 中等成绩记录数
		Integer passNumber = 0; // 及格成绩记录数
		for (Integer departmentId = 0; departmentId <= 18; departmentId++) { // 总共18个学院，把20，21学院归为6学院
			DepartmentDistribution dd = getRPECScoreDistributionByGradeAndDepartmentId(departmentId, grade, year, term);
			ddList.add(dd);
			totalNumber += dd.getTotalNumber();
			excellentNumber += dd.getExcellentNumber();
			goodNumber += dd.getGoodNumber();
			mediumNumber += dd.getMediumNumber();
			passNumber += dd.getPassNumber();
		}
		Integer failNumber = totalNumber - excellentNumber - goodNumber - mediumNumber - passNumber; // 不及格成绩记录数
		DepartmentDistribution departmentDistribution = new DepartmentDistribution();
		String strGrade = String.valueOf(grade);
		departmentDistribution.setGrade(strGrade);
		departmentDistribution.setDepartmentName(strGrade + "级全校");
		if (totalNumber != 0) {
			double excellentRate = (double) excellentNumber / totalNumber; // 优秀率
			double goodRate = (double) goodNumber / totalNumber; // 良好率
			double mediumRate = (double) mediumNumber / totalNumber; // 中等率
			double passRate = (double) passNumber / totalNumber; // 及格率
			double failRate = (double) failNumber / totalNumber; // 不及格率
			double averageScore = getRPECAverageScoreByGrade(grade, year, term); // 平均分

			DecimalFormat rateDF = new DecimalFormat("0.00%");
			DecimalFormat scoreDF = new DecimalFormat("0.00");
			String strExcellentRate = rateDF.format(excellentRate);
			String strGoodRate = rateDF.format(goodRate);
			String strMediumRate = rateDF.format(mediumRate);
			String strPassRate = rateDF.format(passRate);
			String strFailRate = rateDF.format(failRate);
			String strAverageScore = scoreDF.format(averageScore);

			departmentDistribution.setTotalNumber(totalNumber);
			departmentDistribution.setExcellentNumber(excellentNumber);
			departmentDistribution.setGoodNumber(goodNumber);
			departmentDistribution.setMediumNumber(mediumNumber);
			departmentDistribution.setPassNumber(passNumber);
			departmentDistribution.setFailNumber(failNumber);
			departmentDistribution.setAverageScore(strAverageScore);
			departmentDistribution.setExcellentRate(strExcellentRate);
			departmentDistribution.setGoodRate(strGoodRate);
			departmentDistribution.setMediumRate(strMediumRate);
			departmentDistribution.setPassRate(strPassRate);
			departmentDistribution.setFailRate(strFailRate);
		} else {
			departmentDistribution.initValue();
		}
		ddList.add(departmentDistribution);
		return ddList;
	}

	/*
	 * ======通过 classNumber 获得该班级RPEC的优秀率、不及格情况，RPEC指必修课和专业选修课======
	 */

	/**
	 * 通过 grade 获得该年级的所有班级
	 * 
	 * @param grade
	 * @return
	 */
	public List<String> getAllClassNumberListByGrade(Integer grade) {
		List<String> classNumberList = studentCourseMapper.getAllClassNumberListByGrade(grade);
		List<String> classNumberUniqueList = new ArrayList<>();
		Map<String, Integer> flagMap = new HashedMap<>();
		for (String classNumber : classNumberList) {
			if (flagMap.get(classNumber) == null) {
				flagMap.put(classNumber, 1);
				classNumberUniqueList.add(classNumber);
			}
		}
		Collections.sort(classNumberUniqueList); // 对某个年级的所有班级进行排序，从小到大
		return classNumberUniqueList;
	}

	/**
	 * 通过 classNumber 获得该班级RPEC的优秀率、不及格情况
	 * 
	 * @param classNumber
	 * @param year
	 * @param term
	 * @return
	 */
	public ClassExcellentFailDistribution getRPECExcellentFailDistributionByClassNumber(String classNumber, String year,
			Integer term) {
		Integer totalNumber = studentCourseMapper.getRPECTotalScoreRecordNumberByClassNumber(classNumber, year, term);
		Integer excellentNumber = studentCourseMapper.getRPECExcellentScoreRecordNumberByClassNumber(classNumber, year,
				term);
		Integer failNumber = studentCourseMapper.getRPECFailScoreRecordNumberByClassNumber(classNumber, year, term);
		ClassExcellentFailDistribution classExcellentFailDistribution = new ClassExcellentFailDistribution();
		classExcellentFailDistribution.setClassNumber(classNumber);
		if (totalNumber != 0) {
			double excellentRate = (double) excellentNumber / totalNumber;
			double failRate = (double) failNumber / totalNumber;

			DecimalFormat rateDF = new DecimalFormat("0.00%");
			String strExcellentRate = rateDF.format(excellentRate);
			String strFailRate = rateDF.format(failRate);

			classExcellentFailDistribution.setTotalNumber(totalNumber);
			classExcellentFailDistribution.setExcellentNumber(excellentNumber);
			classExcellentFailDistribution.setFailNumber(failNumber);
			classExcellentFailDistribution.setExcellentRate(strExcellentRate);
			classExcellentFailDistribution.setFailRate(strFailRate);
		} else {
			classExcellentFailDistribution.InitValue();
		}
		return classExcellentFailDistribution;
	}

	/**
	 * 获得某个年级所有班级RPEC的优秀率和不及格率
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 */
	public List<ClassExcellentFailDistribution> getRPECClassExcellentFailDistributionListByGrade(Integer grade,
			String year, Integer term) {
		List<String> classNumberList = getAllClassNumberListByGrade(grade);
		List<ClassExcellentFailDistribution> cefdList = new ArrayList<>();
		for (String classNumber : classNumberList) {
			ClassExcellentFailDistribution classExcellentFailDistribution = getRPECExcellentFailDistributionByClassNumber(
					classNumber, year, term);
			classExcellentFailDistribution.setClassNumber(classNumber);
			cefdList.add(classExcellentFailDistribution);
		}
		return cefdList;
	}

	/*
	 * ======获得所有专业所有年级的平均成绩和差值======
	 */

	/**
	 * 通过 grade departmentId 获得该年级该学院RPEC的成绩记录总数
	 * 
	 * @param grade
	 * @param departmentId
	 * @param year
	 * @param term
	 * @return
	 */
	public Integer getRPECTotalScoreRecordNumberByGradeAndDepartmentId(Integer grade, Integer departmentId, String year,
			Integer term) {
		return studentCourseMapper.getRPECTotalScoreRecordNumberByGradeAndDepartmentId(departmentId, grade, year, term);
	}

	/**
	 * 通过 grade 和 deparementId 获得该年级该学院的平均成绩和差值
	 * 
	 * @param grade
	 * @param gradeAverageScore
	 * @param departmentId
	 * @param year
	 * @param term
	 * @return
	 */
	public GradeDepartmentAverageScoreCompare getRPECDepartmentAverageScoreCompareByGradeAndDepartmentId(Integer grade,
			double gradeAverageScore, Integer departmentId, String year, Integer term) {
		String departmentName = getDepartmentNameByDepartmentId(departmentId);
		String strGrade = String.valueOf(grade);
		GradeDepartmentAverageScoreCompare departmentAverageScoreCompare = new GradeDepartmentAverageScoreCompare();
		departmentAverageScoreCompare.setGrade(strGrade);
		departmentAverageScoreCompare.setDepartmentName(departmentName);
		Integer totalNumber = getRPECTotalScoreRecordNumberByGradeAndDepartmentId(grade, departmentId, year, term);
		if (totalNumber != 0) {
			double averageScore = getRPECAverageScoreByGradeAndDepartmentId(departmentId, grade, year, term);
			double difference = averageScore - gradeAverageScore;

			DecimalFormat scoreDF = new DecimalFormat("0.00");
			String strAverageScore = scoreDF.format(averageScore);
			String strDifference = scoreDF.format(difference);

			departmentAverageScoreCompare.setAverageScore(strAverageScore);
			departmentAverageScoreCompare.setDifference(strDifference);
		} else {
			departmentAverageScoreCompare.initValue();
		}
		return departmentAverageScoreCompare;
	}

	/**
	 * 通过 grade 获得该年级所有学院RPEC的平均成绩和差值
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public List<GradeDepartmentAverageScoreCompare> getRPECGradeDepartmentAverageScoreCompareListByGrade(Integer grade,
			String year, Integer term) {
		List<GradeDepartmentAverageScoreCompare> dascList = new ArrayList<>();
		double gradeAverageScore = getRPECAverageScoreByGrade(grade, year, term);
		for (Integer departmentId = 0; departmentId <= 18; departmentId++) {
			GradeDepartmentAverageScoreCompare departmentAverageScoreCompare = getRPECDepartmentAverageScoreCompareByGradeAndDepartmentId(
					grade, gradeAverageScore, departmentId, year, term);
			dascList.add(departmentAverageScoreCompare);
		}
		return dascList;
	}

	/**
	 * 获得所有年级所有学院RPEC的平均成绩和差值
	 * 
	 * @param year
	 * @param term
	 * @return
	 */
	public List<List<GradeDepartmentAverageScoreCompare>> getRPECGradeDepartmentAverageScoreCompareListList(String year,
			Integer term) {
		List<List<GradeDepartmentAverageScoreCompare>> dascListList = new ArrayList<>();
		String strGradeOne = year.substring(0, 4);
		Integer gradeOne = Integer.parseInt(strGradeOne);
		for (Integer gradeI = gradeOne - 3; gradeI <= gradeOne; gradeI++) {
			List<GradeDepartmentAverageScoreCompare> dascList = getRPECGradeDepartmentAverageScoreCompareListByGrade(
					gradeI, year, term);
			dascListList.add(dascList);
		}
		return dascListList;
	}

	/*
	 * ======获得所有专业所有年级的平均成绩和差值====== 这里没有定义新的接口，使用之前定义的接口可以实现预期功能
	 */

	/*
	 * ======获得全校各年级本科生RC的不及格整体情况，RC指必修课======
	 */

	/**
	 * 通过 studentIdList 获得该列表中的学生数量
	 * 
	 * @param studentIdList
	 * @return
	 */
	public Integer getTotalStudentNumberByStudentIdList(List<Integer> studentIdList) {
		Set<Integer> studentIdSet = new HashSet<>(studentIdList);
		return studentIdSet.size();
	}

	/**
	 * 通过 grade 获得该年级RC不及格情况
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public GradeFailDistribution getRCGradeFailDistributionByGrade(Integer grade, String year, Integer term) {
		GradeFailDistribution gradeFailDistribution = new GradeFailDistribution();
		String strGrade = String.valueOf(grade);
		gradeFailDistribution.setGrade(strGrade);
		List<Integer> failStudentIdList = new ArrayList<>();
		failStudentIdList = studentCourseMapper.getRCFailStudentIdListByGrade(grade, year, term);
		Set<Integer> failStudentIdSet = new HashSet<>(failStudentIdList);
		Integer oneFailNumber = 0;
		Integer twoFailNumber = 0;
		Integer threeFailNumber = 0;
		Integer fourFailNumber = 0;
		Integer fiveFailNumber = 0;
		Integer sixFailNumber = 0;
		Integer sevenFailNumber = 0;
		Integer eightFailNumber = 0;
		Integer totalFailNumber = 0;
		Integer totalStudentNumber = studentCourseMapper.getTotalStudentNumberByGrade(grade);
		if (totalStudentNumber != 0) {
			for (Integer studentId : failStudentIdSet) {
				Integer failCourseNumber = Collections.frequency(failStudentIdList, studentId);
				// failStudentIdList里所有的studentId都是有不及格记录的，所以failCourseNumber至少为1
				switch (failCourseNumber) {
				case 1:
					oneFailNumber++;
					break;
				case 2:
					twoFailNumber++;
					break;
				case 3:
					threeFailNumber++;
					break;
				case 4:
					fourFailNumber++;
					break;
				case 5:
					fiveFailNumber++;
					break;
				case 6:
					sixFailNumber++;
					break;
				case 7:
					sevenFailNumber++;
					break;
				default:
					eightFailNumber++;
				}
			}
			totalFailNumber = oneFailNumber + twoFailNumber + threeFailNumber + fourFailNumber + fiveFailNumber
					+ sixFailNumber + sevenFailNumber + eightFailNumber;
			double failRate = (double) totalFailNumber / totalStudentNumber;

			DecimalFormat rateDF = new DecimalFormat("0.00%");
			String strFailRate = rateDF.format(failRate);

			gradeFailDistribution.setOneFailNumber(oneFailNumber);
			gradeFailDistribution.setTwoFailNumber(twoFailNumber);
			gradeFailDistribution.setThreeFailNumber(threeFailNumber);
			gradeFailDistribution.setFourFailNumber(fourFailNumber);
			gradeFailDistribution.setFiveFailNumber(fiveFailNumber);
			gradeFailDistribution.setSixFailNumber(sixFailNumber);
			gradeFailDistribution.setSevenFailNumber(sevenFailNumber);
			gradeFailDistribution.setEightFailNumber(eightFailNumber);
			gradeFailDistribution.setTotalFailNumber(totalFailNumber);
			gradeFailDistribution.setTotalStudentNumber(totalStudentNumber);
			gradeFailDistribution.setFailRate(strFailRate);
		}
		return gradeFailDistribution;
	}

	/**
	 * 获得全校所有年级RC的不及格情况
	 * 
	 * @param year
	 * @param term
	 * @return
	 */
	public List<GradeFailDistribution> getUniversityRCGradeFailDistributionList(String year, Integer term) {
		String strGradeOne = year.substring(0, 4);
		Integer gradeOne = Integer.parseInt(strGradeOne);
		List<GradeFailDistribution> gfdList = new ArrayList<>();
		Integer oneFailNumber = 0;
		Integer twoFailNumber = 0;
		Integer threeFailNumber = 0;
		Integer fourFailNumber = 0;
		Integer fiveFailNumber = 0;
		Integer sixFailNumber = 0;
		Integer sevenFailNumber = 0;
		Integer eightFailNumber = 0;
		Integer totalFailNumber = 0;
		Integer totalStudentNumber = 0;
		for (Integer gradeI = gradeOne - 3; gradeI <= gradeOne; gradeI++) {
			GradeFailDistribution gradeFailDistribution = new GradeFailDistribution();
			gradeFailDistribution = getRCGradeFailDistributionByGrade(gradeI, year, term);
			gfdList.add(gradeFailDistribution);
			oneFailNumber += gradeFailDistribution.getOneFailNumber();
			twoFailNumber += gradeFailDistribution.getTwoFailNumber();
			threeFailNumber += gradeFailDistribution.getThreeFailNumber();
			fourFailNumber += gradeFailDistribution.getFourFailNumber();
			fiveFailNumber += gradeFailDistribution.getFiveFailNumber();
			sixFailNumber += gradeFailDistribution.getSixFailNumber();
			sevenFailNumber += gradeFailDistribution.getSevenFailNumber();
			eightFailNumber += gradeFailDistribution.getEightFailNumber();
			totalFailNumber += gradeFailDistribution.getTotalFailNumber();
			totalStudentNumber += gradeFailDistribution.getTotalStudentNumber();
		}
		GradeFailDistribution gradeFailDistribution = new GradeFailDistribution();
		gradeFailDistribution.setGrade("合计");
		if (totalStudentNumber != 0) {
			double failRate = (double) totalFailNumber / totalStudentNumber;

			DecimalFormat rateDF = new DecimalFormat("0.00%");
			String strFailRate = rateDF.format(failRate);

			gradeFailDistribution.setOneFailNumber(oneFailNumber);
			gradeFailDistribution.setTwoFailNumber(twoFailNumber);
			gradeFailDistribution.setThreeFailNumber(threeFailNumber);
			gradeFailDistribution.setFourFailNumber(fourFailNumber);
			gradeFailDistribution.setFiveFailNumber(fiveFailNumber);
			gradeFailDistribution.setSixFailNumber(sixFailNumber);
			gradeFailDistribution.setSevenFailNumber(sevenFailNumber);
			gradeFailDistribution.setEightFailNumber(eightFailNumber);
			gradeFailDistribution.setTotalFailNumber(totalFailNumber);
			gradeFailDistribution.setTotalStudentNumber(totalStudentNumber);
			gradeFailDistribution.setFailRate(strFailRate);
			gfdList.add(gradeFailDistribution);
		}
		return gfdList;
	}

	/*
	 * ======获得各院系本科生RC不及格学生整体情况，RC指必修课======
	 */

	/**
	 * 通过 departmentId 获得该学院RC的不及格情况
	 * 
	 * @param departmentId
	 * @param year
	 * @param term
	 * @return
	 */
	public DepartmentFailDistribution getRCDepartmentFailDistributionByDepartmentId(Integer departmentId, String year,
			Integer term) {
		String departmentName = getDepartmentNameByDepartmentId(departmentId);
		DepartmentFailDistribution departmentFailDistribution = new DepartmentFailDistribution();
		departmentFailDistribution.setDepartmentName(departmentName);
		Integer oneFailNumber = 0;
		Integer twoFailNumber = 0;
		Integer threeFailNumber = 0;
		Integer geFourFailNumber = 0;
		Integer totalFailNumber = 0;
		Integer totalStudentNumber = 0;
		String strGradeOne = year.substring(0, 4);
		Integer gradeOne = Integer.parseInt(strGradeOne);
		if (departmentId != 6) {
			for (Integer gradeI = gradeOne - 3; gradeI <= gradeOne; gradeI++) {
				totalStudentNumber += studentCourseMapper.getTotalStudentNumberByGradeAndDepartmentId(gradeI,
						departmentId);
			} // 这里totalStudentNumber的计算需要加上4个年级的才能包括该学院所有在读本科生，下面failStudentIdList的计算不需要这样做是因为有year做限制，在指定year有考试成绩就是当时的在读本科生
			List<Integer> failStudentIdList = new ArrayList<>();
			failStudentIdList = studentCourseMapper.getRCFailStudentIdListByDepartmentId(departmentId, year, term);
			Set<Integer> failStudentIdSet = new HashSet<>(failStudentIdList);
			for (Integer failStudentId : failStudentIdSet) {
				Integer failCourseNumber = Collections.frequency(failStudentIdList, failStudentId);
				switch (failCourseNumber) {
				case 1:
					oneFailNumber++;
					break;
				case 2:
					twoFailNumber++;
					break;
				case 3:
					threeFailNumber++;
					break;
				default:
					geFourFailNumber++;
				} // switch
			} // for
		} else {
			List<Integer> departmentIdList = Arrays.asList(6, 20, 21);
			for (Integer id : departmentIdList) {
				for (Integer gradeI = gradeOne - 3; gradeI <= gradeOne; gradeI++) {
					totalStudentNumber += studentCourseMapper.getTotalStudentNumberByGradeAndDepartmentId(gradeI, id);
				}
				List<Integer> failStudentIdList = new ArrayList<>();
				failStudentIdList = studentCourseMapper.getRCFailStudentIdListByDepartmentId(id, year, term);
				Set<Integer> failStudentIdSet = new HashSet<>(failStudentIdList);
				for (Integer failStudentId : failStudentIdSet) {
					Integer failCourseNumber = Collections.frequency(failStudentIdList, failStudentId);
					switch (failCourseNumber) {
					case 1:
						oneFailNumber++;
						break;
					case 2:
						twoFailNumber++;
						break;
					case 3:
						threeFailNumber++;
						break;
					default:
						geFourFailNumber++;
					} // switch
				} // for
			}
		}
		if (totalStudentNumber != 0) {
			totalFailNumber = oneFailNumber + twoFailNumber + threeFailNumber + geFourFailNumber;

			double failRate = (double) totalFailNumber / totalStudentNumber;

			DecimalFormat rateDF = new DecimalFormat("0.00%");
			String strFailRate = rateDF.format(failRate);

			departmentFailDistribution.setOneFailNumber(oneFailNumber);
			departmentFailDistribution.setTwoFailNumber(twoFailNumber);
			departmentFailDistribution.setThreeFailNumber(threeFailNumber);
			departmentFailDistribution.setGeFourFailNumber(geFourFailNumber);
			departmentFailDistribution.setTotalFailNumber(totalFailNumber);
			departmentFailDistribution.setTotalStudentNumber(totalStudentNumber);
			departmentFailDistribution.setFailRate(strFailRate);
		}
		return departmentFailDistribution;
	}

	/**
	 * 获得全校各学院RC的不及格情况
	 * 
	 * @param year
	 * @param term
	 * @return
	 */
	public List<DepartmentFailDistribution> getUniversityRCDepartmentFailDistributionList(String year, Integer term) {
		List<DepartmentFailDistribution> dfdList = new ArrayList<>();
		Integer oneFailNumber = 0;
		Integer twoFailNumber = 0;
		Integer threeFailNumber = 0;
		Integer geFourFailNumber = 0;
		Integer totalFailNumber = 0;
		Integer totalStudentNumber = 0;
		for (Integer departmentId = 0; departmentId <= 18; departmentId++) {
			DepartmentFailDistribution departmentFailDistribution = new DepartmentFailDistribution();
			departmentFailDistribution = getRCDepartmentFailDistributionByDepartmentId(departmentId, year, term);
			dfdList.add(departmentFailDistribution);
			oneFailNumber += departmentFailDistribution.getOneFailNumber();
			twoFailNumber += departmentFailDistribution.getTwoFailNumber();
			threeFailNumber += departmentFailDistribution.getThreeFailNumber();
			geFourFailNumber += departmentFailDistribution.getGeFourFailNumber();
			totalFailNumber += departmentFailDistribution.getTotalFailNumber();
			totalStudentNumber += departmentFailDistribution.getTotalStudentNumber();
		}
		DepartmentFailDistribution departmentFailDistribution = new DepartmentFailDistribution();
		departmentFailDistribution.setDepartmentName("全校");
		if (totalStudentNumber != 0) {
			totalFailNumber = oneFailNumber + twoFailNumber + threeFailNumber + geFourFailNumber;

			double failRate = (double) totalFailNumber / totalStudentNumber;

			DecimalFormat rateDF = new DecimalFormat("0.00%");
			String strFailRate = rateDF.format(failRate);

			departmentFailDistribution.setOneFailNumber(oneFailNumber);
			departmentFailDistribution.setTwoFailNumber(twoFailNumber);
			departmentFailDistribution.setThreeFailNumber(threeFailNumber);
			departmentFailDistribution.setGeFourFailNumber(geFourFailNumber);
			departmentFailDistribution.setTotalFailNumber(totalFailNumber);
			departmentFailDistribution.setTotalStudentNumber(totalStudentNumber);
			departmentFailDistribution.setFailRate(strFailRate);
		}
		dfdList.add(departmentFailDistribution);
		return dfdList;
	}

	/*
	 * 上个功能测试用
	 */

	/**
	 * 测试 public DepartmentFailDistribution
	 * getRCDepartmentFailDistributionByDepartmentId(Integer departmentId, String
	 * year, Integer term);
	 * 
	 * @param departmentId
	 * @param year
	 * @param term
	 * @return
	 */
	public DepartmentFailDistribution testGetRCDepartmentFailDistributionByDepartmentId(Integer departmentId,
			String year, Integer term) {
		String departmentName = getDepartmentNameByDepartmentId(departmentId);
		DepartmentFailDistribution departmentFailDistribution = new DepartmentFailDistribution();
		departmentFailDistribution.setDepartmentName(departmentName);
		Integer oneFailNumber = 0;
		Integer twoFailNumber = 0;
		Integer threeFailNumber = 0;
		Integer geFourFailNumber = 0;
		Integer totalFailNumber = 0;
		Integer totalStudentNumber = 0;
		String strGradeOne = year.substring(0, 4);
		Integer gradeOne = Integer.parseInt(strGradeOne);
		for (Integer gradeI = gradeOne - 3; gradeI <= gradeOne; gradeI++) {
			totalStudentNumber += studentCourseMapper.getTotalStudentNumberByGradeAndDepartmentId(gradeI, departmentId);
			List<Integer> studentIdList = new ArrayList<>();
			studentIdList = studentCourseMapper.getStudentIdListByGradeAndDepartmentId(gradeI, departmentId);
			for (Integer studentId : studentIdList) {
				Integer failCourseNumber = studentCourseMapper.getRCFailCourseNumberByStudentId(studentId, year, term);
				if (failCourseNumber != 0) {
					switch (failCourseNumber) {
					case 1:
						oneFailNumber++;
						break;
					case 2:
						twoFailNumber++;
						break;
					case 3:
						threeFailNumber++;
						break;
					default:
						geFourFailNumber++;
					}
				}
			}
		}
		if (totalStudentNumber != 0) {
			totalFailNumber = oneFailNumber + twoFailNumber + threeFailNumber + geFourFailNumber;

			double failRate = (double) totalFailNumber / totalStudentNumber;

			DecimalFormat rateDF = new DecimalFormat("0.00%");
			String strFailRate = rateDF.format(failRate);

			departmentFailDistribution.setOneFailNumber(oneFailNumber);
			departmentFailDistribution.setTwoFailNumber(twoFailNumber);
			departmentFailDistribution.setThreeFailNumber(threeFailNumber);
			departmentFailDistribution.setGeFourFailNumber(geFourFailNumber);
			departmentFailDistribution.setTotalFailNumber(totalFailNumber);
			departmentFailDistribution.setTotalStudentNumber(totalStudentNumber);
			departmentFailDistribution.setFailRate(strFailRate);
		}
		return departmentFailDistribution;
	}

	/*
	 * ======获得各院系分年级本科生RC的不及格情况，RC指必修课程======
	 */

	/**
	 * 通过 grade和departmentId 获得该年级该学院RC的不及格情况
	 * 
	 * @param grade
	 * @param departmentId
	 * @param year
	 * @param term
	 * @return
	 */
	public GradeDepartmentFailDistribution getRCGradeDepartmentFailDistributionByGradeAndDepartmentId(Integer grade,
			Integer departmentId, String year, Integer term) {
		String departmentName = getDepartmentNameByDepartmentId(departmentId);
		String strGrade = String.valueOf(grade);
		GradeDepartmentFailDistribution gradeDepartmentFailDistribution = new GradeDepartmentFailDistribution();
		gradeDepartmentFailDistribution.setGrade(strGrade);
		gradeDepartmentFailDistribution.setDepartmentName(departmentName);
		Integer totalFailNumber = 0;
		Integer totalStudentNumber = 0;
		if (departmentId != 6) {
			totalStudentNumber = studentCourseMapper.getTotalStudentNumberByGradeAndDepartmentId(grade, departmentId);
			if (totalStudentNumber != 0) {
				List<Integer> failStudentIdList = new ArrayList<>();
				failStudentIdList = studentCourseMapper.getRCFailStudentIdListByDepartmentId(departmentId, year, term);
				totalFailNumber = getTotalStudentNumberByStudentIdList(failStudentIdList);
				double failRate = (double) totalFailNumber / totalStudentNumber;

				DecimalFormat rateDF = new DecimalFormat("0.00%");
				String strFailRate = rateDF.format(failRate);

				gradeDepartmentFailDistribution.setTotalFailNumber(totalFailNumber);
				gradeDepartmentFailDistribution.setTotalStudentNumber(totalStudentNumber);
				gradeDepartmentFailDistribution.setFailRate(strFailRate);
			}
		} else {
			List<Integer> departmentIdList = Arrays.asList(6, 20, 21);
			for (Integer id : departmentIdList) {
				totalStudentNumber += studentCourseMapper.getTotalStudentNumberByGradeAndDepartmentId(grade, id);
				List<Integer> failStudentIdList = new ArrayList<>();
				failStudentIdList = studentCourseMapper.getRCFailStudentIdListByDepartmentId(id, year, term);
				totalFailNumber += getTotalStudentNumberByStudentIdList(failStudentIdList);
			}
			if (totalStudentNumber != 0) {
				double failRate = (double) totalFailNumber / totalStudentNumber;

				DecimalFormat rateDF = new DecimalFormat("0.00%");
				String strFailRate = rateDF.format(failRate);
				gradeDepartmentFailDistribution.setTotalFailNumber(totalFailNumber);
				gradeDepartmentFailDistribution.setTotalStudentNumber(totalStudentNumber);
				gradeDepartmentFailDistribution.setFailRate(strFailRate);
			}
		}
		return gradeDepartmentFailDistribution;
	}

	/**
	 * 通过 grade 获得该年级RC的不及格情况
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public List<GradeDepartmentFailDistribution> getRCGradeDepartmentFailDistributionListByGrade(Integer grade,
			String year, Integer term) {
		List<GradeDepartmentFailDistribution> gdfdList = new ArrayList<>();
		Integer totalFailNumber = 0;
		Integer totalStudentNumber = 0;
		for (Integer departmentId = 0; departmentId <= 18; departmentId++) {
			GradeDepartmentFailDistribution gradeDepartmentFailDistribution = new GradeDepartmentFailDistribution();
			gradeDepartmentFailDistribution = getRCGradeDepartmentFailDistributionByGradeAndDepartmentId(grade,
					departmentId, year, term);
			gdfdList.add(gradeDepartmentFailDistribution);
			totalFailNumber += gradeDepartmentFailDistribution.getTotalFailNumber();
			totalStudentNumber += gradeDepartmentFailDistribution.getTotalStudentNumber();
		}
		String strGrade = String.valueOf(grade);
		GradeDepartmentFailDistribution gradeDepartmentFailDistribution = new GradeDepartmentFailDistribution();
		gradeDepartmentFailDistribution.setGrade(strGrade);
		gradeDepartmentFailDistribution.setDepartmentName("全校");
		if (totalStudentNumber != 0) {
			double failRate = (double) totalFailNumber / totalStudentNumber;

			DecimalFormat rateDF = new DecimalFormat("0.00%");
			String strFailRate = rateDF.format(failRate);

			gradeDepartmentFailDistribution.setTotalFailNumber(totalFailNumber);
			gradeDepartmentFailDistribution.setTotalStudentNumber(totalStudentNumber);
			gradeDepartmentFailDistribution.setFailRate(strFailRate);
		}
		gdfdList.add(gradeDepartmentFailDistribution);
		return gdfdList;
	}

	/**
	 * 获得各院系分年级学生RC的不及格情况
	 * 
	 * @param year
	 * @param term
	 * @return
	 */
	public List<List<GradeDepartmentFailDistribution>> getRCGradeDepartmentFailDistributionListList(String year,
			Integer term) {
		List<List<GradeDepartmentFailDistribution>> gdfdListList = new ArrayList<>();
		String strGradeOne = year.substring(0, 4);
		Integer gradeOne = Integer.parseInt(strGradeOne);
		for (Integer gradeI = gradeOne - 3; gradeI <= gradeOne; gradeI++) {
			List<GradeDepartmentFailDistribution> gdfdList = new ArrayList<>();
			gdfdList = getRCGradeDepartmentFailDistributionListByGrade(gradeI, year, term);
			gdfdListList.add(gdfdList);
		}
		return gdfdListList;
	}

	/*
	 * ======获得各班级不及格情况======
	 */

	/**
	 * 通过 classNumber 获得该班级RC的不及格情况
	 * 
	 * @param classNumber
	 * @param year
	 * @param term
	 * @return
	 */
	public ClassFailDistribution getRCClassFailDistributionByClassNumber(String classNumber, String year,
			Integer term) {
		ClassFailDistribution classFailDistribution = new ClassFailDistribution();
		classFailDistribution.setClassNumber(classNumber);
		List<Integer> failStudentIdList = new ArrayList<>();
		failStudentIdList = studentCourseMapper.getRCFailStudentIdListByClassNumber(classNumber, year, term);
		Integer totalFailNumber = getTotalStudentNumberByStudentIdList(failStudentIdList);
		Integer totalStudentNumber = studentCourseMapper.getTotalStudentNumberByClassNumber(classNumber);
		if (totalStudentNumber != 0) {
			double failRate = (double) totalFailNumber / totalStudentNumber;

			DecimalFormat rateDF = new DecimalFormat("0.00%");
			String strFailRate = rateDF.format(failRate);

			classFailDistribution.setTotalFailNumber(totalFailNumber);
			classFailDistribution.setTotalStudentNumber(totalStudentNumber);
			classFailDistribution.setFailRate(strFailRate);
		}
		return classFailDistribution;
	}

	/**
	 * 通过 grade 获得该年级各班级RC的不及格情况
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public List<ClassFailDistribution> getRCClassFailDistributionListByGrade(Integer grade, String year, Integer term) {
		List<ClassFailDistribution> cfdList = new ArrayList<>();
		List<String> classNumberList = getAllClassNumberListByGrade(grade);
		for (String classNumber : classNumberList) {
			ClassFailDistribution classFailDistribution = new ClassFailDistribution();
			classFailDistribution = getRCClassFailDistributionByClassNumber(classNumber, year, term);
			cfdList.add(classFailDistribution);
		}
		return cfdList;
	}

	/*
	 * ======获得各年级各类课程的缺考情况======
	 */

	/**
	 * 通过 grade 获得该年级各类课程的缺考情况
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public GradeAbsenceDistribution getGradeAbsenceDistributionByGrade(Integer grade, String year, Integer term) {
		GradeAbsenceDistribution gradeAbsenceDistribution = new GradeAbsenceDistribution();
		String strGrade = String.valueOf(grade);
		gradeAbsenceDistribution.setGrade(strGrade);
		List<Integer> rcAbsenceStudentIdList = studentCourseMapper.getRCAbsenceStudentIdListByGrade(grade, year, term);
		List<Integer> pecAbsenceStudentIdList = studentCourseMapper.getPECAbsenceStudentIdListByGrade(grade, year,
				term);
		List<Integer> gecAbsenceStudentIdList = studentCourseMapper.getGECAbsenceStudentIdListByGrade(grade, year,
				term);
		Integer rcAbsenceNumber = getTotalStudentNumberByStudentIdList(rcAbsenceStudentIdList);
		Integer pecAbsenceNumber = getTotalStudentNumberByStudentIdList(pecAbsenceStudentIdList);
		Integer gecAbsenceNumber = getTotalStudentNumberByStudentIdList(gecAbsenceStudentIdList);
		Integer totalAbsenceNumber = rcAbsenceNumber + pecAbsenceNumber + gecAbsenceNumber;
		gradeAbsenceDistribution.setRcAbsenceNumber(rcAbsenceNumber);
		gradeAbsenceDistribution.setPecAbsenceNumber(pecAbsenceNumber);
		gradeAbsenceDistribution.setGecAbsenceNumber(gecAbsenceNumber);
		gradeAbsenceDistribution.setTotalAbsenceNumber(totalAbsenceNumber);
		return gradeAbsenceDistribution;
	}

	/**
	 * 获得各年级各类课程的缺考情况
	 * 
	 * @param year
	 * @param term
	 * @return
	 */
	public List<GradeAbsenceDistribution> getGradeAbsenceDistributionList(String year, Integer term) {
		List<GradeAbsenceDistribution> gadList = new ArrayList<>();
		String strGradeOne = year.substring(0, 4);
		Integer gradeOne = Integer.parseInt(strGradeOne);
		Integer rcAbsenceNumber = 0;
		Integer pecAbsenceNumber = 0;
		Integer gecAbsenceNumber = 0;
		for (Integer gradeI = gradeOne - 3; gradeI <= gradeOne; gradeI++) {
			GradeAbsenceDistribution gradeAbsenceDistribution = new GradeAbsenceDistribution();
			gradeAbsenceDistribution = getGradeAbsenceDistributionByGrade(gradeI, year, term);
			gadList.add(gradeAbsenceDistribution);
			rcAbsenceNumber += gradeAbsenceDistribution.getRcAbsenceNumber();
			pecAbsenceNumber += gradeAbsenceDistribution.getPecAbsenceNumber();
			gecAbsenceNumber += gradeAbsenceDistribution.getGecAbsenceNumber();
		}
		Integer totalAbsenceNumber = rcAbsenceNumber + pecAbsenceNumber + gecAbsenceNumber;
		GradeAbsenceDistribution gradeAbsenceDistribution = new GradeAbsenceDistribution();
		gradeAbsenceDistribution.setGrade("全校");
		gradeAbsenceDistribution.setRcAbsenceNumber(rcAbsenceNumber);
		gradeAbsenceDistribution.setPecAbsenceNumber(pecAbsenceNumber);
		gradeAbsenceDistribution.setGecAbsenceNumber(gecAbsenceNumber);
		gradeAbsenceDistribution.setTotalAbsenceNumber(totalAbsenceNumber);
		gadList.add(gradeAbsenceDistribution);
		return gadList;
	}
}
