package com.project.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.beans.StudentCourse;
import com.project.dao.StudentCourseMapper;
import com.project.dao.StudentMapper;
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
	public Integer insertStudentCourse(StudentCourse studentCourse) {
		studentCourseMapper.addStudentCourse(studentCourse);
		studentCourse = getStudentCourseByEntity(studentCourse);
		if (studentCourse != null) {
			return studentCourse.getScId();
		} else {
			return -1; // 插入异常
		}
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
	 * 下面为前端展示数据需要的方法
	 * 
	 * @author xiapeng 2018年7月25日17:05:52
	 */

	/**
	 * 通过 grade 获得该年级该学期所有课程的加权总分
	 * 
	 * @param grade
	 * @param term
	 * @return
	 */
	public double getACTotalScoreByGrade(Integer grade, String year, Integer term) {
		Integer totalScoreRecordNumber = studentCourseMapper.getACTotalSocreRecordNumberByGrade(grade, year, term);
		if (totalScoreRecordNumber != 0) {
			return studentCourseMapper.getACTotalScoreByGrade(grade, year, term);
		} else {
			return 0;
		}
	}

	/**
	 * 通过 grade 获得该年级该学期所有课程的总学分
	 * 
	 * @param grade
	 * @param term
	 * @return
	 */
	public double getACTotalCreditsByGrade(Integer grade, String year, Integer term) {
		Integer totalScoreRecordNumber = studentCourseMapper.getACTotalSocreRecordNumberByGrade(grade, year, term);
		if (totalScoreRecordNumber != 0) {
			return studentCourseMapper.getACTotalCreditsByGrade(grade, year, term);
		} else {
			return 0;
		}
	}

	/**
	 * 通过 grade 属性查询该年级该学期所有课程（必修课、专业选修课、通识选修课）的平均分
	 * 
	 * @param grade
	 * @param term
	 * @return
	 */
	public double getACAverageScoreByGrade(Integer grade, String year, Integer term) {
		double acTotalScore = getACTotalScoreByGrade(grade, year, term); // 加权总分
		double acTotalCredits = getACTotalCreditsByGrade(grade, year, term); // 总学分
		if (acTotalCredits != 0) {
			double acAverageScore = acTotalScore / acTotalCredits;
			return acAverageScore;
		} else {
			return -1; // 除数异常
		}
	}

	/**
	 * 通过 gradeOne 属性查询全校该学期所有课程（必修课、专业选修课、通识选修课）的平均分
	 * 
	 * @param gradeOne
	 * @param term
	 * @return
	 */
	public double getUniversityACAverageScore(Integer gradeOne, String year, Integer term) {
		double acTotalScore = 0;
		double acTotalCredits = 0;
		for (Integer gradeI = gradeOne -3; gradeI <= gradeOne; gradeI++) {
			acTotalScore += getACTotalScoreByGrade(gradeI, year, term);
			acTotalCredits += getACTotalCreditsByGrade(gradeI, year, term);
		}
		if (acTotalCredits != 0) {
			double acAverageScore = acTotalScore / acTotalCredits;
			return acAverageScore;
		} else {
			return -1; // 除数异常
		}
	}

	/**
	 * 通过 grade 属性查询该年级该学期所有课程（必修课、专业选修课、通识选修课)的分布
	 * 
	 * @param grade
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
	 * 通过 grade 属性查询全校该学期所有课程（必修课、专业选修课、通识选修课)的分布
	 * 
	 * @param gradeOne
	 * @param term
	 * @return
	 */
	public List<OverallDistribution> getUniversityACScoreDistribution(String year, Integer term) {
		String strGradeOne = year.substring(0, 4);
		Integer gradeOne = Integer.parseInt(strGradeOne);
		List<OverallDistribution> odList = new ArrayList<>();
		Integer totalNumber = 0; // 成绩记录总数
		Integer excellentNumber = 0; // 优秀成绩记录数
		Integer goodNumber = 0; // 良好成绩记录数
		Integer mediumNumber = 0; // 中等成绩记录数
		Integer passNumber = 0; // 及格成绩记录数
		for (Integer gradeI = gradeOne -3; gradeI <= gradeOne; gradeI++) {
			OverallDistribution ov = getACScoreDistributionByGrade(gradeI, year, term);
			odList.add(ov);
			totalNumber += studentCourseMapper.getACTotalSocreRecordNumberByGrade(gradeI, year, term);
			excellentNumber += studentCourseMapper.getACExcellentScoreRecordNumberByGrade(gradeI, year, term);
			goodNumber += studentCourseMapper.getACGoodScoreRecordNumberByGrade(gradeI, year, term);
			mediumNumber += studentCourseMapper.getACMediumScoreRecordNumberByGrade(gradeI, year, term);
			passNumber += studentCourseMapper.getACPassScoreRecordNumberByGrade(gradeI, year, term);
		}
		Integer failNumber = totalNumber - excellentNumber - goodNumber - mediumNumber - passNumber; // 不及格成绩记录数
		OverallDistribution overallDistribution = new OverallDistribution();
		if (totalNumber != 0) {
			double excellentRate = (double) excellentNumber / totalNumber; // 优秀率
			double goodRate = (double) goodNumber / totalNumber; // 良好率
			double mediumRate = (double) mediumNumber / totalNumber; // 中等率
			double passRate = (double) passNumber / totalNumber; // 及格率
			double failRate = (double) failNumber / totalNumber; // 不及格率
			double averageScore = getUniversityACAverageScore(gradeOne, year, term); // 平均分

			DecimalFormat rateDF = new DecimalFormat("0.00%");
			DecimalFormat scoreDF = new DecimalFormat("0.00");
			String strExcellentRate = rateDF.format(excellentRate);
			String strGoodRate = rateDF.format(goodRate);
			String strMediumRate = rateDF.format(mediumRate);
			String strPassRate = rateDF.format(passRate);
			String strFailRate = rateDF.format(failRate);
			String strAverageScore = scoreDF.format(averageScore);

			overallDistribution.setGrade("全校");
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

}
