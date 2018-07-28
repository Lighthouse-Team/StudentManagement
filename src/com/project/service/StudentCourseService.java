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
	 * 下面为前端展示数据需要的接口
	 */

	/**
	 * 通过 grade 属性获得AC的考试成绩的分布，AC指所有课程，包括：必修课、专业选修课、通识选修课
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

	/**
	 * 通过 courseType 属性获得AG该课程的考试成绩分布，AG指所有年级
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
			strCourseType = "必修课";
			break;
		case 2:
			strCourseType = "专业选修课";
			break;
		case 3:
			strCourseType = "通识选修课";
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

	/**
	 * 通过 grade 属性获得AC的考试成绩的分布，AC指所有课程，包括：必修课、专业选修课、通识选修课
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
		if(totalScoreRecordNumber != 0) {
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
			OverallDistribution ov = getRPECScoreDistributionByGrade(gradeI, year, term);
			odList.add(ov);
			totalNumber += studentCourseMapper.getRPECTotalSocreRecordNumberByGrade(gradeI, year, term);
			excellentNumber += studentCourseMapper.getRPECExcellentScoreRecordNumberByGrade(gradeI, year, term);
			goodNumber += studentCourseMapper.getRPECGoodScoreRecordNumberByGrade(gradeI, year, term);
			mediumNumber += studentCourseMapper.getRPECMediumScoreRecordNumberByGrade(gradeI, year, term);
			passNumber += studentCourseMapper.getRPECPassScoreRecordNumberByGrade(gradeI, year, term);
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

}
