package com.project.service;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.stream.Collectors;

import javax.websocket.RemoteEndpoint.Basic;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.ibatis.annotations.Param;
import org.apache.tomcat.util.codec.binary.BaseNCodec;
import org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.stereotype.Service;

import com.mysql.fabric.xmlrpc.base.Array;
import com.mysql.jdbc.StringUtils;
import com.project.beans.StudentCourse;
import com.project.dao.StudentCourseMapper;
import com.project.dao.StudentMapper;
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
import com.project.tools.InsertIntoDB;
import com.sun.glass.ui.Size;
import com.sun.org.apache.bcel.internal.generic.I2F;
import com.sun.swing.internal.plaf.basic.resources.basic;
import com.sun.xml.internal.messaging.saaj.soap.AttachmentPartImpl;

@Service
public class StudentCourseService {

	@Autowired
	private StudentCourseMapper studentCourseMapper;

	/*
	 * ===========================下面为插入数据库需要的接口==================================
	 */

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

	/*
	 * ===========================下面为前端展示数据需要的接口==================================
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
		Integer totalScoreRecordNumber = studentCourseMapper.getACTotalScoreRecordNumberByGrade(grade, year, term);
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
		Integer totalScoreRecordNumber = studentCourseMapper.getACTotalScoreRecordNumberByGrade(grade, year, term);
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
		double averageScore = 0;
		if (totalCredits != 0) {
			averageScore = totalScore / totalCredits;
		} else {
			averageScore = -1; // 除数异常
		}
		return averageScore;
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
			Integer totalScoreRecordNumber = studentCourseMapper.getACTotalScoreRecordNumberByGrade(gradeI, year, term);
			if (totalScoreRecordNumber != 0) {
				totalScore += studentCourseMapper.getACTotalScoreByGrade(gradeI, year, term);
				totalCredits += studentCourseMapper.getACTotalCreditsByGrade(gradeI, year, term);
			}
		}
		double averageScore = 0;
		if (totalCredits != 0) {
			averageScore = totalScore / totalCredits;
		} else {
			averageScore = -1; // 除数异常
		}
		return averageScore;
	}

	/**
	 * 通过 grade 属性查询该年级该学期AC的分布
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public OverallDistribution getACOverallDistributionByGrade(Integer grade, String year, Integer term) {
		Integer totalNumber = studentCourseMapper.getACTotalScoreRecordNumberByGrade(grade, year, term); // 成绩记录总数
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
	public List<OverallDistribution> getACOverallDistributionList(String year, Integer term) {
		String termStr = "";// 学期
		double excellentRateHighest = 0.00;// 最高优秀率
		double excellentRateHigher = 0.00;// 较高优秀率
		double failRateLowest = 100.00;// 最低不及格率
		double failRateLower = 100.00;// 较低不及格率
		int excellentRateHighestGrade = 0;// 最高优秀率的年级
		int excellentRateHigherGrade = 0;// 较高优秀率班级
		int failRateLowestGrade = 0;// 最低不及格率的年级
		int failRateLowerGrade = 0;// 较低不及格率的年级
		double averageScoreHighest = 0.00;// 最高平均分
		double averageScoreLowest = 100.00;// 最低平均分
		int averageScoreHighestGrade = 0;// 最高平均分的年级
		int averageScoreLowestGrade = 0;// 最低平均分的年级
		ArrayList<Double> overMediumRates = new ArrayList<Double>();// 所有年级70分以上的概率列表
		ArrayList<Integer> overMediumRateGrades = new ArrayList<Integer>();// 70分以上概率的所有年级列表
		ArrayList<Integer> overAllGrades = new ArrayList<Integer>();// 70分以上（含70）的成绩记录高于全校平均水平的班级列表

		String strGradeOne = year.substring(0, 4);
		Integer gradeOne = Integer.parseInt(strGradeOne);
		List<OverallDistribution> odList = new ArrayList<>();
		Integer totalNumber = 0; // 成绩记录总数
		Integer excellentNumber = 0; // 优秀成绩记录数
		Integer goodNumber = 0; // 良好成绩记录数
		Integer mediumNumber = 0; // 中等成绩记录数
		Integer passNumber = 0; // 及格成绩记录数
		if (term == 1) {
			termStr = "第一学期";
		} else if (term == 2) {
			termStr = "第二学期";
		}
		Integer id = 1; // 序号
		for (Integer gradeI = gradeOne - 3; gradeI <= gradeOne; gradeI++) {
			OverallDistribution od = getACOverallDistributionByGrade(gradeI, year, term);
			od.setId(id++);
			odList.add(od);
			totalNumber += od.getTotalNumber();
			excellentNumber += od.getExcellentNumber();
			goodNumber += od.getGoodNumber();
			mediumNumber += od.getMediumNumber();
			passNumber += od.getPassNumber();

			double currentExcellentRate = Double
					.parseDouble(od.getExcellentRate().substring(0, od.getExcellentRate().length() - 1));
			double currrentFailRate = Double.parseDouble(od.getFailRate().substring(0, od.getFailRate().length() - 1));
			double currentAverageScore = Double.parseDouble(od.getAverageScore());
			double currentMediumRates = Double
					.parseDouble(od.getMediumRate().substring(0, od.getMediumRate().length() - 1))
					+ Double.parseDouble(od.getGoodRate().substring(0, od.getGoodRate().length() - 1))
					+ Double.parseDouble(od.getExcellentRate().substring(0, od.getExcellentRate().length() - 1));// 当前年级70分以上的概率
			// System.out.println("111223"+currentMediumRates);
			overMediumRates.add(currentMediumRates);
			overMediumRateGrades.add(Integer.parseInt(od.getGrade()));

			// 获取最高优秀率及最高优秀率的班级
			if (excellentRateHighest < currentExcellentRate) {
				if (excellentRateHighest != 0.00) {
					excellentRateHigher = excellentRateHighest;
					excellentRateHigherGrade = excellentRateHighestGrade;
				}
				excellentRateHighest = currentExcellentRate;
				excellentRateHighestGrade = Integer.parseInt(od.getGrade());
			}
			if (excellentRateHighest > currentExcellentRate && excellentRateHigher < currentExcellentRate) {
				excellentRateHigher = currentExcellentRate;
				excellentRateHigherGrade = Integer.parseInt(od.getGrade());
			}
			// 获取最低不及格率和最低不及格率的班级
			if (failRateLowest > currrentFailRate) {
				if (failRateLowest != 100.00) {
					failRateLower = failRateLowest;
					failRateLowerGrade = failRateLowestGrade;
				}
				failRateLowest = currrentFailRate;
				failRateLowestGrade = Integer.parseInt(od.getGrade());
			}
			if (failRateLowest < currrentFailRate && failRateLower > currrentFailRate) {
				failRateLower = currrentFailRate;
				failRateLowerGrade = Integer.parseInt(od.getGrade());
			}
			// 获取最高平均分和最高平均分的班级
			if (currentAverageScore > averageScoreHighest) {
				averageScoreHighest = currentAverageScore;
				averageScoreHighestGrade = Integer.parseInt(od.getGrade());
			}
			// 获取最低平均分和最低平均分的班级
			if (currentAverageScore < averageScoreLowest) {
				averageScoreLowest = currentAverageScore;
				averageScoreLowestGrade = Integer.parseInt(od.getGrade());
			}
		}
		Integer failNumber = totalNumber - excellentNumber - goodNumber - mediumNumber - passNumber; // 不及格成绩记录数
		OverallDistribution overallDistribution = new OverallDistribution();
		overallDistribution.setId(id);
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
		}
		double alloverMediumRate = Double.parseDouble(
				overallDistribution.getMediumRate().substring(0, overallDistribution.getMediumRate().length() - 1))
				+ Double.parseDouble(
						overallDistribution.getGoodRate().substring(0, overallDistribution.getGoodRate().length() - 1))
				+ Double.parseDouble(overallDistribution.getExcellentRate().substring(0,
						overallDistribution.getExcellentRate().length() - 1));// 全年级70分以上的概率
		if (overMediumRates.size() != 0) {
			for (int i = 0; i < overMediumRates.size(); i++) {
				if (alloverMediumRate < overMediumRates.get(i)) {
					overAllGrades.add(overMediumRateGrades.get(i));
				}
			}
		}
		// System.out.println("222222222"+overAllGrades.size());
		String overAllGradeStr = "";
		if (overAllGrades.size() != 0) {
			for (int i = 0; i < overAllGrades.size(); i++) {
				overAllGradeStr = overAllGradeStr + overAllGrades.get(i) + "级、";
			}
			overAllGradeStr = overAllGradeStr.substring(0, overAllGradeStr.length() - 1);
		}
		if (overallDistribution.getGrade().equals("全校")) {
			overallDistribution.setAnalysis(
					"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;表1.1、图1.1-1.2统计结果显示：\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（1）全校有效总成绩记录数为"
							+ overallDistribution.getTotalNumber() + "条。优秀成绩记录数"
							+ overallDistribution.getExcellentNumber() + "条，占总成绩记录数的"
							+ overallDistribution.getExcellentRate() + "；不及格成绩记录数为"
							+ overallDistribution.getFailNumber() + "条，占总成绩记录数的" + overallDistribution.getFailRate()
							+ "。" + "\n" + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（2）优秀率方面，最高的年级为"
							+ excellentRateHighestGrade + "级，达到" + excellentRateHighest + "%，其次为"
							+ excellentRateHigherGrade + "级，达到" + excellentRateHigher + "%，高于全校平均值（"
							+ overallDistribution.getExcellentRate() + "）。" + "\n"
							+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（3）不及格率方面，最低年级是" + failRateLowestGrade
							+ "级，仅为" + failRateLowest + "%，其次为" + failRateLowerGrade + "级，为" + failRateLower
							+ "%，均低于全校平均值（" + overallDistribution.getFailRate() + "）。" + "\n"
							+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（4）在平均分方面，全校平均分为"
							+ overallDistribution.getAverageScore() + "，" + averageScoreHighestGrade + "级最高，为"
							+ averageScoreHighest + "，" + averageScoreLowestGrade + "级最低，为" + averageScoreLowest + "。"
							+ "\n" + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（5）总体上，" + overAllGradeStr
							+ "成绩较好，70分以上（含70）的成绩记录高于全校平均水平。" + "#" + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
							+ year + "学年" + termStr + "本科生期末考试有效成绩记录" + overallDistribution.getTotalNumber() + "条，其中，"
							+ odList.get(0).getGrade() + "级有效成绩数据" + odList.get(0).getTotalNumber() + "条，"
							+ odList.get(1).getGrade() + "级有效成绩数据" + odList.get(1).getTotalNumber() + "条，"
							+ odList.get(2).getGrade() + "级有效成绩数据" + odList.get(2).getTotalNumber() + "条，"
							+ odList.get(3).getGrade() + "级有效成绩数据" + odList.get(3).getTotalNumber() + "条。"
							+ "#&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;表1.1和图1.1为我校本科生" + year + "学年" + termStr
							+ "所有课程成绩分布情况。主要统计了全校各年级各分数段成绩记录数，成绩优良率、不及格率等情况。");
		}
		odList.add(overallDistribution);
		return odList;
	}

	// /**
	// * 查询全校该学期AC的成绩分布
	// *
	// * @param year
	// * @param term
	// * @return
	// */
	// public List<OverallDistribution> getACOverallDistributionList(String year,
	// Integer term) {
	// String strGradeOne = year.substring(0, 4);
	// Integer gradeOne = Integer.parseInt(strGradeOne);
	// List<OverallDistribution> odList = new ArrayList<>();
	// Integer totalNumber = 0; // 成绩记录总数
	// Integer excellentNumber = 0; // 优秀成绩记录数
	// Integer goodNumber = 0; // 良好成绩记录数
	// Integer mediumNumber = 0; // 中等成绩记录数
	// Integer passNumber = 0; // 及格成绩记录数
	// Integer id = 1; // 序号
	// for (Integer gradeI = gradeOne - 3; gradeI <= gradeOne; gradeI++) {
	// OverallDistribution od = getACOverallDistributionByGrade(gradeI, year, term);
	// od.setId(id++);
	// odList.add(od);
	// totalNumber += od.getTotalNumber();
	// excellentNumber += od.getExcellentNumber();
	// goodNumber += od.getGoodNumber();
	// mediumNumber += od.getMediumNumber();
	// passNumber += od.getPassNumber();
	// }
	// Integer failNumber = totalNumber - excellentNumber - goodNumber -
	// mediumNumber - passNumber; // 不及格成绩记录数
	// OverallDistribution overallDistribution = new OverallDistribution();
	// overallDistribution.setId(id);
	// overallDistribution.setGrade("全校");
	// if (totalNumber != 0) {
	// double excellentRate = (double) excellentNumber / totalNumber; // 优秀率
	// double goodRate = (double) goodNumber / totalNumber; // 良好率
	// double mediumRate = (double) mediumNumber / totalNumber; // 中等率
	// double passRate = (double) passNumber / totalNumber; // 及格率
	// double failRate = (double) failNumber / totalNumber; // 不及格率
	// double averageScore = getUniversityACAverageScore(year, term); // 平均分
	//
	// DecimalFormat rateDF = new DecimalFormat("0.00%");
	// DecimalFormat scoreDF = new DecimalFormat("0.00");
	// String strExcellentRate = rateDF.format(excellentRate);
	// String strGoodRate = rateDF.format(goodRate);
	// String strMediumRate = rateDF.format(mediumRate);
	// String strPassRate = rateDF.format(passRate);
	// String strFailRate = rateDF.format(failRate);
	// String strAverageScore = scoreDF.format(averageScore);
	//
	// overallDistribution.setTotalNumber(totalNumber);
	// overallDistribution.setExcellentNumber(excellentNumber);
	// overallDistribution.setGoodNumber(goodNumber);
	// overallDistribution.setMediumNumber(mediumNumber);
	// overallDistribution.setPassNumber(passNumber);
	// overallDistribution.setFailNumber(failNumber);
	// overallDistribution.setAverageScore(strAverageScore);
	// overallDistribution.setExcellentRate(strExcellentRate);
	// overallDistribution.setGoodRate(strGoodRate);
	// overallDistribution.setMediumRate(strMediumRate);
	// overallDistribution.setPassRate(strPassRate);
	// overallDistribution.setFailRate(strFailRate);
	// }
	// odList.add(overallDistribution);
	// return odList;
	// }

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
		double averageScore = 0;
		if (totalCredits != 0) {
			averageScore = totalScore / totalCredits;
		} else {
			averageScore = -1; // 除数异常
		}
		return averageScore;
	}

	/**
	 * 通过 courseType 获得AG该学期该课程的成绩分布
	 * 
	 * @param courseType
	 * @param year
	 * @param term
	 * @return
	 */
	public OverallDistribution getAGOverallDistributionByCourseType(Integer courseType, String year, Integer term) {
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
			strCourseType = "专业<br>选修";
			break;
		case 3:
			strCourseType = "通识<br>教育";
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
	public List<OverallDistribution> getAGOverallDistributionList(String year, Integer term) {
		double excellentRateHighest = 0.00;// 优秀率最高课程的概率
		double excellentRateLowest = 100.00;// 优秀率最低课程的概率
		String excellentRateHighestCourse = "";// 优秀率最高的课程
		String excellentRateLowestCourse = "";// 优秀率最低的课程
		double failRateLowest = 100.00;// 最低的不及格率
		String failRateLowestCourse = "";// 不及格率最低的课程
		double averageScoreHighest = 0.00;// 最高的平均分
		String averageScoreHighestCourse = "";// 最高平均分的课程
		ArrayList<Double> failRates = new ArrayList<Double>();// 不及格率列表
		ArrayList<String> failRateCourses = new ArrayList<String>();// 不及格率课程列表
		ArrayList<Double> overAllFailRates = new ArrayList<Double>();// 不及格率高于全校水平的概率
		ArrayList<String> overAllFailRateCourses = new ArrayList<String>();// 不及格率高于全校平均水平的课程
		String termStr = "";
		if (term == 1) {
			termStr = "第一学期";
		} else if (term == 2) {
			termStr = "第二学期";
		}
		List<OverallDistribution> odList = new ArrayList<>();
		Integer totalNumber = 0;
		Integer excellentNumber = 0;
		Integer goodNumber = 0;
		Integer mediumNumber = 0;
		Integer passNumber = 0;
		Integer id = 1;
		for (Integer courseType = 1; courseType <= 3; courseType++) {
			OverallDistribution od = getAGOverallDistributionByCourseType(courseType, year, term);
			od.setId(id++);
			odList.add(od);
			totalNumber += od.getTotalNumber();
			excellentNumber += od.getExcellentNumber();
			goodNumber += od.getGoodNumber();
			mediumNumber += od.getMediumNumber();
			passNumber += od.getPassNumber();

			double currentAverageScore = Double.parseDouble(od.getAverageScore());
			double currentExcellentRate = Double
					.parseDouble(od.getExcellentRate().substring(0, od.getExcellentRate().length() - 1));
			double currrentFailRate = Double.parseDouble(od.getFailRate().substring(0, od.getFailRate().length() - 1));
			failRates.add(currrentFailRate);
			failRateCourses.add(od.getCourseType());
			if (excellentRateHighest < currentExcellentRate) {
				excellentRateHighest = currentExcellentRate;
				excellentRateHighestCourse = od.getCourseType();
			}
			if (excellentRateLowest > currentExcellentRate) {
				excellentRateLowest = currentExcellentRate;
				excellentRateLowestCourse = od.getCourseType();
			}

			if (failRateLowest > currrentFailRate) {
				failRateLowest = currrentFailRate;
				failRateLowestCourse = od.getCourseType();
			}
			if (averageScoreHighest < currentAverageScore) {
				averageScoreHighest = currentAverageScore;
				averageScoreHighestCourse = od.getCourseType();
			}
		}
		Integer failNumber = totalNumber - excellentNumber - goodNumber - mediumNumber - passNumber;
		OverallDistribution overallDistribution = new OverallDistribution();
		overallDistribution.setId(id);
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
		}
		double allFailRate = Double.parseDouble(
				overallDistribution.getFailRate().substring(0, overallDistribution.getFailRate().length() - 1));
		for (int i = 0; i < failRates.size(); i++) {
			if (failRates.get(i) > allFailRate) {
				// overAllFailRates.add(failRates.get(i));
				overAllFailRateCourses.add(failRateCourses.get(i));
			}
		}

		String overAllCourseStr = "";
		for (int i = 0; i < overAllFailRateCourses.size(); i++) {
			overAllCourseStr = overAllCourseStr + overAllFailRateCourses.get(i) + "、";
		}
		if (overallDistribution.getCourseType().equals("全校")) {
			String analysisHaveBr = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;表1.2、图1.3-1.4统计结果显示：\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（1）优秀率方面，"
					+ excellentRateHighestCourse + "最高，达到" + excellentRateHighest + "%，" + excellentRateLowestCourse
					+ "最低，为" + excellentRateLowest + "%，低于全校平均水平（" + overallDistribution.getExcellentRate() + "）；\n"
					+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（2）不及格率方面，" + failRateLowestCourse + "最低，仅为"
					+ failRateLowest + "%," + overAllCourseStr.substring(0, overAllCourseStr.length() - 1)
					+ "基本均高于全校平均水平（" + overallDistribution.getFailRate() + "）；\n"
					+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（3）在平均分方面，全校平均分为"
					+ overallDistribution.getAverageScore() + "，其中，" + averageScoreHighestCourse + "平均分最高，为"
					+ averageScoreHighest + "。" + "#其中必修课成绩" + odList.get(0).getTotalNumber() + "条，选修课成绩"
					+ odList.get(1).getTotalNumber() + "条，通识教育选修课" + odList.get(2).getTotalNumber()
					+ "条。#&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;表1.2为我校本科生" + year + "学年" + termStr
					+ "必修、专业选修、通识选修课程成绩分布情况，有效成绩数、优秀（90-100）、良好（80-89）、中等（70-79）、及格（60-69）、不及格（0-59）、成绩平均值情况如图1.3-1.4：";
			String anaylsisNoBr = analysisHaveBr.replace("<br>", "");
			overallDistribution.setAnalysis(anaylsisNoBr);
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
		double totalScore = 0; // 加权总分
		double totalCredits = 0; // 总学分
		Integer totalScoreRecordNumber = studentCourseMapper.getRPECTotalSocreRecordNumberByGrade(grade, year, term);
		if (totalScoreRecordNumber != 0) {
			totalScore = studentCourseMapper.getRPECTotalScoreByGrade(grade, year, term);
			totalCredits = studentCourseMapper.getRPECTotalCreditsByGrade(grade, year, term);
		}
		double averageScore = 0;
		if (totalCredits != 0) {
			averageScore = totalScore / totalCredits;
		} else {
			averageScore = -1; // 除数异常
		}
		return averageScore;
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
			Integer totalScoreRecordNumber = studentCourseMapper.getRPECTotalSocreRecordNumberByGrade(gradeI, year,
					term);
			if (totalScoreRecordNumber != 0) {
				totalScore += studentCourseMapper.getRPECTotalScoreByGrade(gradeI, year, term);
				totalCredits += studentCourseMapper.getRPECTotalCreditsByGrade(gradeI, year, term);
			}
		}
		double averageScore = 0;
		if (totalCredits != 0) {
			averageScore = totalScore / totalCredits;
		} else {
			averageScore = -1; // 除数异常
		}
		return averageScore;
	}

	/**
	 * 通过 grade 属性查询该年级该学期RPEC的分布
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public OverallDistribution getRPECOverallDistributionByGrade(Integer grade, String year, Integer term) {
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
	public List<OverallDistribution> getRPECOverallDistributionList(String year, Integer term) {
		String termStr = "";
		double excellentRateHighest = 0.00;// 最高优秀率
		double excellentRateHigher = 0.00;// 较高优秀率
		double failRateLowest = 100.00;// 最低不及格率
		double failRateLower = 100.00;// 较低不及格率
		int excellentRateHighestGrade = 0;// 最高优秀率的年级
		int excellentRateHigherGrade = 0;// 较高优秀率年级
		int failRateLowestGrade = 0;// 最低不及格率的年级
		int failRateLowerGrade = 0;// 较低不及格率的年级
		double averageScoreHighest = 0.00;// 最高平均分
		double averageScoreLowest = 100.00;// 最低平均分
		int averageScoreHighestGrade = 0;// 最高平均分的年级
		int averageScoreLowestGrade = 0;// 最低平均分的年级

		String strGradeOne = year.substring(0, 4);
		Integer gradeOne = Integer.parseInt(strGradeOne);
		List<OverallDistribution> odList = new ArrayList<>();
		Integer totalNumber = 0; // 成绩记录总数
		Integer excellentNumber = 0; // 优秀成绩记录数
		Integer goodNumber = 0; // 良好成绩记录数
		Integer mediumNumber = 0; // 中等成绩记录数
		Integer passNumber = 0; // 及格成绩记录数
		Integer id = 1; // 序号
		for (Integer gradeI = gradeOne - 3; gradeI <= gradeOne; gradeI++) {
			OverallDistribution od = getRPECOverallDistributionByGrade(gradeI, year, term);
			od.setId(id++);
			odList.add(od);
			totalNumber += od.getTotalNumber();
			excellentNumber += od.getExcellentNumber();
			goodNumber += od.getGoodNumber();
			mediumNumber += od.getMediumNumber();
			passNumber += od.getPassNumber();

			double currentExcellentRate = Double
					.parseDouble(od.getExcellentRate().substring(0, od.getExcellentRate().length() - 1));
			double currrentFailRate = Double.parseDouble(od.getFailRate().substring(0, od.getFailRate().length() - 1));
			double currentAverageScore = Double.parseDouble(od.getAverageScore());
			// 获取最高优秀率及最高优秀率的班级
			if (excellentRateHighest < currentExcellentRate) {
				excellentRateHighest = currentExcellentRate;
				excellentRateHighestGrade = Integer.parseInt(od.getGrade());
			}
			if (excellentRateHighest > currentExcellentRate && excellentRateHigher < currentExcellentRate) {
				excellentRateHigher = currentExcellentRate;
				excellentRateHigherGrade = Integer.parseInt(od.getGrade());
			}
			// 获取最低不及格率和最低不及格率的班级
			if (failRateLowest > currrentFailRate) {
				failRateLowest = currrentFailRate;
				failRateLowestGrade = Integer.parseInt(od.getGrade());
			}
			if (failRateLowest < currrentFailRate && failRateLower > currrentFailRate) {
				failRateLower = currrentFailRate;
				failRateLowerGrade = Integer.parseInt(od.getGrade());
			}
			// 获取最高平均分和最高平均分的班级
			if (currentAverageScore > averageScoreHighest) {
				averageScoreHighest = currentAverageScore;
				averageScoreHighestGrade = Integer.parseInt(od.getGrade());
			}
			// 获取最低平均分和最低平均分的班级
			if (currentAverageScore < averageScoreLowest) {
				averageScoreLowest = currentAverageScore;
				averageScoreLowestGrade = Integer.parseInt(od.getGrade());
			}
		}
		if (term == 1) {
			termStr = "第一学期";
		} else if (term == 2) {
			termStr = "第二学期";
		}
		Integer failNumber = totalNumber - excellentNumber - goodNumber - mediumNumber - passNumber; // 不及格成绩记录数
		OverallDistribution overallDistribution = new OverallDistribution();
		overallDistribution.setId(id);
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
			overallDistribution.setAnalysis(
					"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;表2.1、图2.1-2.2统计结果显示：\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（1）优秀率方面，全校必修、专业选修课程优秀率最高的年级为"
							+ excellentRateHighestGrade + "级，达到" + excellentRateHighest + "%，其次为"
							+ excellentRateHigherGrade + "级，达到" + excellentRateHigher + "%，均高于全校平均值（"
							+ overallDistribution.getExcellentRate() + "）。\n"
							+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（2）不及格率方面，全校必修、专业选修课程不及格率最低年级是"
							+ failRateLowestGrade + "级，仅为" + failRateLowest + "%，其次为" + failRateLowerGrade + "级，为"
							+ failRateLower + "%，均低于全校平均值（" + overallDistribution.getFailRate() + "）\n"
							+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（3）在平均分方面，全校平均分为"
							+ overallDistribution.getAverageScore() + "，" + averageScoreHighestGrade + "级最高，为"
							+ averageScoreHighest + "," + averageScoreLowestGrade + "级最低，为" + averageScoreLowest + "。"
							+ "#&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本章对全校的必修、专业选修课程成绩进行分析，在第1章的数据基础上，剔除通识教育选修课成绩，其中，"
							+ odList.get(0).getGrade() + "级有效成绩数据" + odList.get(0).getTotalNumber() + "条，"
							+ odList.get(1).getGrade() + "级有效成绩数据" + odList.get(1).getTotalNumber() + "条，"
							+ odList.get(2).getGrade() + "级有效成绩数据" + odList.get(2).getTotalNumber() + "条，"
							+ odList.get(3).getGrade() + "级有效成绩数据" + odList.get(3).getTotalNumber() + "条。"
							+ "#&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;表2.1为我校本科生" + year + "学年" + termStr
							+ "必修、专业选修课程成绩分布情况，各年级有效成绩数、优秀（90-100）、良好（80-89）、中等（70-79）、及格（60-69）、不及格（0-59）、成绩平均值情况如图2.1-2.2：");
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
		if (departmentId == 1) {
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
		} else if (departmentId == 19) {
			return "陈赓班";
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
		double totalScore = 0;
		double totalCredits = 0;
		if (departmentId != 6) {
			Integer totalScoreRecordNumber = studentCourseMapper
					.getRPECTotalScoreRecordNumberByGradeAndDepartmentId(departmentId, grade, year, term);
			if (totalScoreRecordNumber != 0) {
				totalScore = studentCourseMapper.getRPECTotalScoreByGradeAndDepartmentId(departmentId, grade, year,
						term); // 加权总分
				totalCredits = studentCourseMapper.getRPECTotalCreditsByGradeAndDepartmentId(departmentId, grade, year,
						term); // 总学分
			}
		} else {
			List<Integer> departmentIdList = new ArrayList<>(Arrays.asList(6, 20, 21));
			for (Integer id : departmentIdList) {
				Integer totalScoreRecordNumber = studentCourseMapper
						.getRPECTotalScoreRecordNumberByGradeAndDepartmentId(id, grade, year, term);
				if (totalScoreRecordNumber != 0) {
					totalScore += studentCourseMapper.getRPECTotalScoreByGradeAndDepartmentId(id, grade, year, term); // 加权总分
					totalCredits += studentCourseMapper.getRPECTotalCreditsByGradeAndDepartmentId(id, grade, year,
							term); // 总学分
				}
			}
		}
		double averageScore = 0;
		if (totalCredits != 0) {
			averageScore = totalScore / totalCredits;
		} else {
			averageScore = -1; // 除数异常
		}
		return averageScore;
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
	public DepartmentDistribution getRPECDepartmentDistributionByGradeAndDepartmentId(Integer departmentId,
			Integer grade, String year, Integer term) {
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
	public List<DepartmentDistribution> getRPECDepartmentDistributionListByGrade(Integer grade, String year,
			Integer term) {
		// 优秀率列表
		ArrayList<Double> excellentRates = new ArrayList<Double>();
		// 院系列表
		ArrayList<String> departments = new ArrayList<>();
		// 高于全校平均水平的院系的优秀率
		ArrayList<Double> overAllExcellentRates = new ArrayList<>();
		// 优秀率高于全校平均水平的院系
		ArrayList<String> overAllExcellentRateDepartments = new ArrayList<>();
		// 最高优秀率
		double excellentRateHighest = 0.00;
		// 最高优秀率的院系
		String excellentRateHighestDepartment = "";
		// 不及格率列表
		ArrayList<Double> failRates = new ArrayList<>();
		// 高于全校平均水平的院系的不及格率
		ArrayList<Double> overAllFailRates = new ArrayList<>();
		// 不及格率高于全校平均水平的院系
		ArrayList<String> overAllFailRatesDepartments = new ArrayList<>();
		// 最高不及格率
		double failRateHighest = 0.00;
		// 最高不及格率的院系
		String failRateHighestDepartment = "";
		// 平均分列表
		ArrayList<Double> averageScores = new ArrayList<>();
		// 平均分高于全校的院系
		ArrayList<String> overAllaverageScoresDepartments = new ArrayList<>();

		List<DepartmentDistribution> ddList = new ArrayList<>();
		Integer totalNumber = 0; // 成绩记录总数
		Integer excellentNumber = 0; // 优秀成绩记录数
		Integer goodNumber = 0; // 良好成绩记录数
		Integer mediumNumber = 0; // 中等成绩记录数
		Integer passNumber = 0; // 及格成绩记录数
		Integer id = 1; // 序号
		for (Integer departmentId = 1; departmentId <= 19; departmentId++) { // 总共18个学院，另加陈赓班，把20，21学院归为6学院
			DepartmentDistribution dd = getRPECDepartmentDistributionByGradeAndDepartmentId(departmentId, grade, year,
					term);
			if (dd.getTotalNumber() != 0) {
				dd.setId(id++);
				ddList.add(dd);
				totalNumber += dd.getTotalNumber();
				excellentNumber += dd.getExcellentNumber();
				goodNumber += dd.getGoodNumber();
				mediumNumber += dd.getMediumNumber();
				passNumber += dd.getPassNumber();

				double currentExcellentRate = Double
						.parseDouble(dd.getExcellentRate().substring(0, dd.getExcellentRate().length() - 1));
				double currentFailRate = Double
						.parseDouble(dd.getFailRate().substring(0, dd.getFailRate().length() - 1));
				double currentAverageScore = Double.parseDouble(dd.getAverageScore());
				excellentRates.add(currentExcellentRate);
				departments.add(dd.getDepartmentName());
				failRates.add(currentFailRate);
				averageScores.add(currentAverageScore);

				// 获取最高优秀率及最高优秀率的院系
				if (excellentRateHighest < currentExcellentRate) {
					excellentRateHighest = currentExcellentRate;
					excellentRateHighestDepartment = dd.getDepartmentName();
				}
				// 获取最高不及格率及最高不及格率的院系
				if (failRateHighest < currentFailRate) {
					failRateHighest = currentFailRate;
					failRateHighestDepartment = dd.getDepartmentName();
				}
			}
		}
		Integer failNumber = totalNumber - excellentNumber - goodNumber - mediumNumber - passNumber; // 不及格成绩记录数
		DepartmentDistribution departmentDistribution = new DepartmentDistribution();
		String strGrade = String.valueOf(grade);
		departmentDistribution.setId(id);
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

			double allExcellentRate = Double.parseDouble(departmentDistribution.getExcellentRate().substring(0,
					departmentDistribution.getExcellentRate().length() - 1));
			double allFailRate = Double.parseDouble(departmentDistribution.getFailRate().substring(0,
					departmentDistribution.getFailRate().length() - 1));
			double allAverageScore = Double.parseDouble(departmentDistribution.getAverageScore());
			for (int i = 0; i < excellentRates.size(); i++) {
				if (excellentRates.get(i) > allExcellentRate) {
					overAllExcellentRates.add(excellentRates.get(i));
					overAllExcellentRateDepartments.add(departments.get(i));
				}
			}
			String departmentExStr = "";
			for (int i = 0; i < overAllExcellentRateDepartments.size(); i++) {
				departmentExStr = departmentExStr + overAllExcellentRateDepartments.get(i) + "、";
			}
			for (int i = 0; i < failRates.size(); i++) {
				if (failRates.get(i) > allFailRate) {
					overAllFailRates.add(failRates.get(i));
					overAllFailRatesDepartments.add(departments.get(i));
				}
			}
			String departmentFaStr = "";
			for (int i = 0; i < overAllFailRatesDepartments.size(); i++) {
				departmentFaStr = departmentFaStr + overAllFailRatesDepartments.get(i) + "、";
			}

			for (int i = 0; i < averageScores.size(); i++) {
				if (averageScores.get(i) > allAverageScore) {
					overAllaverageScoresDepartments.add(departments.get(i));
				}
			}
			String departmentScoStr = "";
			for (int i = 0; i < overAllaverageScoresDepartments.size(); i++) {
				departmentScoStr = departmentScoStr + overAllaverageScoresDepartments.get(i) + "、";
			}

			departmentDistribution.setAnalysis("统计结果显示：\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（1）"
					+ departmentDistribution.getGrade() + "级全校必修、专业选修课程平均优秀率为"
					+ departmentDistribution.getExcellentRate() + "，高于全校平均水平的院系有："
					+ departmentExStr.substring(0, departmentExStr.length() - 1) + "，其中"
					+ excellentRateHighestDepartment + "最高，为" + excellentRateHighest + "%；\n"
					+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（2）" + departmentDistribution.getGrade()
					+ "级全校必修、专业选修课程平均不及格率为" + departmentDistribution.getFailRate() + "，高于全校平均水平的院系有："
					+ departmentFaStr.substring(0, departmentFaStr.length() - 1) + "，其中" + failRateHighestDepartment
					+ "最高，为" + failRateHighest + "%；\n" + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（3）"
					+ departmentDistribution.getGrade() + "级全校必修、专业选修课程平均分为" + departmentDistribution.getAverageScore()
					+ "，高于全校平均水平的院系有：" + departmentScoStr.substring(0, departmentScoStr.length() - 1) + "。##");
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
	public List<String> getClassNumberListByGrade(Integer grade) {
		List<String> classNumberList = studentCourseMapper.getClassNumberListByGrade(grade);
		List<String> classNumberUniqueList = new ArrayList<>();
		Map<String, Integer> flagMap = new HashedMap<>();
		for (String classNumber : classNumberList) {
			if (flagMap.get(classNumber) == null) {
				flagMap.put(classNumber, 1);
				if (!classNumber.contains("W") && !classNumber.contains("w")) { // 不统计包括班级号带w的班级
					classNumberUniqueList.add(classNumber);
				}
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
		ClassExcellentFailDistribution classExcellentFailDistribution = new ClassExcellentFailDistribution();
		classExcellentFailDistribution.setClassNumber(classNumber);
		Integer totalNumber = studentCourseMapper.getRPECTotalScoreRecordNumberByClassNumber(classNumber, year, term);
		if (totalNumber != 0) {
			Integer excellentNumber = studentCourseMapper.getRPECExcellentScoreRecordNumberByClassNumber(classNumber,
					year, term);
			Integer failNumber = studentCourseMapper.getRPECFailScoreRecordNumberByClassNumber(classNumber, year, term);
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
		List<String> classNumberList = getClassNumberListByGrade(grade);
		List<ClassExcellentFailDistribution> cefdList = new ArrayList<>();
		Integer id = 1; // 序号
		for (String classNumber : classNumberList) {
			ClassExcellentFailDistribution classExcellentFailDistribution = getRPECExcellentFailDistributionByClassNumber(
					classNumber, year, term);
			if (classExcellentFailDistribution.getTotalNumber() >= 10) {
				classExcellentFailDistribution.setId(id++);
				cefdList.add(classExcellentFailDistribution);
			}
		}
		return cefdList;
	}

	/*
	 * ======获得所有专业所有年级的平均成绩和差值======
	 */

	/**
	 * 通过 grade和deparementId 获得该年级该学院的平均成绩和差值
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
		// String departmentName = getDepartmentNameByDepartmentId(departmentId);
		// String strGrade = String.valueOf(grade);
		// GradeDepartmentAverageScoreCompare departmentAverageScoreCompare = new
		// GradeDepartmentAverageScoreCompare();
		// departmentAverageScoreCompare.setGrade(strGrade);
		// departmentAverageScoreCompare.setDepartmentName(departmentName);
		//
		// double averageScore = getRPECAverageScoreByGradeAndDepartmentId(departmentId,
		// grade, year, term);
		// double difference = averageScore - gradeAverageScore;
		//
		// DecimalFormat scoreDF = new DecimalFormat("0.00");
		// String strAverageScore = scoreDF.format(averageScore);
		// String strDifference = scoreDF.format(difference);
		//
		// departmentAverageScoreCompare.setAverageScore(strAverageScore);
		// departmentAverageScoreCompare.setDifference(strDifference);
		// return departmentAverageScoreCompare;
		String departmentName = getDepartmentNameByDepartmentId(departmentId);
		String strGrade = String.valueOf(grade);
		GradeDepartmentAverageScoreCompare departmentAverageScoreCompare = new GradeDepartmentAverageScoreCompare();
		departmentAverageScoreCompare.setGrade(strGrade);
		departmentAverageScoreCompare.setDepartmentName(departmentName);

		double averageScore = getRPECAverageScoreByGradeAndDepartmentId(departmentId, grade, year, term);
		double difference = averageScore - gradeAverageScore;

		String strAverageScore;
		String strDifference;
		if (averageScore != -1) {
			DecimalFormat scoreDF = new DecimalFormat("0.00");
			strAverageScore = scoreDF.format(averageScore);
			strDifference = scoreDF.format(difference);
		} else {
			strAverageScore = "---";
			strDifference = "---";
		}

		departmentAverageScoreCompare.setAverageScore(strAverageScore);
		departmentAverageScoreCompare.setDifference(strDifference);
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
		double gradeAverageScore = getRPECAverageScoreByGrade(grade, year, term); // 19个院系共用一个平均分
		for (Integer departmentId = 1; departmentId <= 19; departmentId++) {
			GradeDepartmentAverageScoreCompare departmentAverageScoreCompare = getRPECDepartmentAverageScoreCompareByGradeAndDepartmentId(
					grade, gradeAverageScore, departmentId, year, term);
			dascList.add(departmentAverageScoreCompare);
		}
		return dascList;
	}

	/**
	 * 获得各学院各年级RPEC的平均成绩和差值
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
		for (Integer gradeI = gradeOne; gradeI >= gradeOne - 3; gradeI--) { // 几乎所有的List都是按大四->大三->大二->大一存储的，这里因为是中间步骤，而且为了最后的显示操作，将顺序反过来了
			List<GradeDepartmentAverageScoreCompare> dascList = getRPECGradeDepartmentAverageScoreCompareListByGrade(
					gradeI, year, term);
			dascListList.add(dascList);
		}
		return dascListList;
	}

	/**
	 * 通过 GradeDepartmentAverageScoreCompareListList 获得
	 * DepartmentAverageScoreCompareList
	 * 
	 * @param gdascListList
	 * @param year
	 * @param term
	 * @return
	 */
	public List<DepartmentAllGradeAverageScoreCompare> getRPECDepartmentAllGradeAverageScoreCompareListByGradeDepartmentAverageScoreCompareListList(
			List<List<GradeDepartmentAverageScoreCompare>> gdascListList, String year) {
		// 大一高于全校平均成绩的院系
		ArrayList<String> gradeOneOverDepartmentNames = new ArrayList<>();
		// 大二高于全校平均成绩的院系
		ArrayList<String> gradeTwoOverDepartmentNames = new ArrayList<>();
		// 大三高于全校平均成绩的院系
		ArrayList<String> gradeThreeOverDepartmentNames = new ArrayList<>();
		// 大四高于全校平均成绩的院系
		ArrayList<String> gradeFourOverDepartmentNames = new ArrayList<>();
		// 大一低于全校平均成绩列表
		ArrayList<Double> gradeOneDownScores = new ArrayList<>();
		// 大一低于全校平均成绩的院系
		ArrayList<String> gradeOneDownDepartmentNames = new ArrayList<>();
		// 大二低于全校平均成绩列表
		ArrayList<Double> gradeTwoDownScores = new ArrayList<>();
		// 大二低于全校平均成绩的院系
		ArrayList<String> gradeTwoDownDepartmentNames = new ArrayList<>();
		// 大三低于全校平均成绩列表
		ArrayList<Double> gradeThreeDownScores = new ArrayList<>();
		// 大三低于全校平均成绩的院系
		ArrayList<String> gradeThreeDownDepartmentNames = new ArrayList<>();
		// 大四低于全校平均成绩列表
		ArrayList<Double> gradeFourDownScores = new ArrayList<>();
		// 大四低于全校平均成绩的院系
		ArrayList<String> gradeFourDownDepartmentNames = new ArrayList<>();
		// 四个年级的平均成绩均在全校平均成绩之上的院系（差值都大于0）
		ArrayList<String> gradeAllOverDepartments = new ArrayList<>();
		// 四个年级的平均成绩均在全校平均成绩之下的院系（差值都小于0）
		ArrayList<String> gradeAllDownDepartments = new ArrayList<>();
		// 将大一、大二等分别与年级联系起来
		String strGradeOneTemp = year.substring(0, 4);
		Integer gradeOneTemp = Integer.parseInt(strGradeOneTemp);
		Integer gradeOn = gradeOneTemp - 3;
		Integer gradeTw = gradeOn + 1;
		Integer gradeTh = gradeOn + 2;
		Integer gradeFo = gradeOn + 3;

		Map<Integer, DepartmentAllGradeAverageScoreCompare> dagascMap = new HashMap<>();
		for (Integer departmentId = 1; departmentId <= 19; departmentId++) {
			DepartmentAllGradeAverageScoreCompare departmentAllGradeAverageScoreCompare = new DepartmentAllGradeAverageScoreCompare();
			String departmentName = getDepartmentNameByDepartmentId(departmentId);
			departmentAllGradeAverageScoreCompare.setId(departmentId);
			departmentAllGradeAverageScoreCompare.setDepartmentName(departmentName);
			dagascMap.put(departmentId, departmentAllGradeAverageScoreCompare);
		}
		String strGradeOne = year.substring(0, 4);
		Integer gradeOne = Integer.parseInt(strGradeOne);
		for (Integer gradeI = gradeOne - 3; gradeI <= gradeOne; gradeI++) {
			for (Integer departmentId = 1; departmentId <= 19; departmentId++) {
				Integer grade = gradeOne - gradeI + 1; // grade为本科的年级，取值1，2，3，4
				GradeDepartmentAverageScoreCompare gradeDepartmentAverageScoreCompare = gdascListList.get(grade - 1)
						.get(departmentId - 1);
				DepartmentAllGradeAverageScoreCompare departmentAllGradeAverageScoreCompare = dagascMap
						.get(departmentId);
				String averageScore = gradeDepartmentAverageScoreCompare.getAverageScore();
				String difference = gradeDepartmentAverageScoreCompare.getDifference();
				switch (grade) {
				case 1:
					departmentAllGradeAverageScoreCompare.setGradeOneAverageScore(averageScore);
					departmentAllGradeAverageScoreCompare.setGradeOneDifference(difference);
					break;
				case 2:
					departmentAllGradeAverageScoreCompare.setGradeTwoAverageScore(averageScore);
					departmentAllGradeAverageScoreCompare.setGradeTwoDifference(difference);
					break;
				case 3:
					departmentAllGradeAverageScoreCompare.setGradeThreeAverageScore(averageScore);
					departmentAllGradeAverageScoreCompare.setGradeThreeDifference(difference);
					break;
				case 4:
					departmentAllGradeAverageScoreCompare.setGradeFourAverageScore(averageScore);
					departmentAllGradeAverageScoreCompare.setGradeFourDifference(difference);
					break;
				} // switch
				dagascMap.put(departmentId, departmentAllGradeAverageScoreCompare);
			} // for
		} // for
		for (Integer departmentId = 1; departmentId <= 19; departmentId++) {
			DepartmentAllGradeAverageScoreCompare departmentAllGradeAverageScoreCompare = dagascMap.get(departmentId);
			if (!departmentAllGradeAverageScoreCompare.getGradeOneDifference().equals("---")) {
				if (Double.parseDouble(departmentAllGradeAverageScoreCompare.getGradeOneDifference()) >= 5) {
					gradeOneOverDepartmentNames.add(departmentAllGradeAverageScoreCompare.getDepartmentName());
				}
				if (Double.parseDouble(departmentAllGradeAverageScoreCompare.getGradeOneDifference()) <= -5) {
					gradeOneDownScores
							.add(Double.parseDouble(departmentAllGradeAverageScoreCompare.getGradeOneDifference()));
					gradeOneDownDepartmentNames.add(departmentAllGradeAverageScoreCompare.getDepartmentName());
				}
			}

			if (!departmentAllGradeAverageScoreCompare.getGradeTwoDifference().equals("---")) {
				if (Double.parseDouble(departmentAllGradeAverageScoreCompare.getGradeTwoDifference()) >= 5) {
					gradeTwoOverDepartmentNames.add(departmentAllGradeAverageScoreCompare.getDepartmentName());
				}
				if (Double.parseDouble(departmentAllGradeAverageScoreCompare.getGradeTwoDifference()) <= -5) {
					gradeTwoDownScores
							.add(Double.parseDouble(departmentAllGradeAverageScoreCompare.getGradeTwoDifference()));
					gradeTwoDownDepartmentNames.add(departmentAllGradeAverageScoreCompare.getDepartmentName());
				}
			}

			if (!departmentAllGradeAverageScoreCompare.getGradeThreeDifference().equals("---")) {
				if (Double.parseDouble(departmentAllGradeAverageScoreCompare.getGradeThreeDifference()) >= 5) {
					gradeThreeOverDepartmentNames.add(departmentAllGradeAverageScoreCompare.getDepartmentName());
				}
				if (Double.parseDouble(departmentAllGradeAverageScoreCompare.getGradeThreeDifference()) <= -5) {
					gradeThreeDownScores
							.add(Double.parseDouble(departmentAllGradeAverageScoreCompare.getGradeThreeDifference()));
					gradeThreeDownDepartmentNames.add(departmentAllGradeAverageScoreCompare.getDepartmentName());
				}
			}

			if (!departmentAllGradeAverageScoreCompare.getGradeFourDifference().equals("---")) {
				if (Double.parseDouble(departmentAllGradeAverageScoreCompare.getGradeFourDifference()) >= 5) {
					gradeFourOverDepartmentNames.add(departmentAllGradeAverageScoreCompare.getDepartmentName());
				}
				if (Double.parseDouble(departmentAllGradeAverageScoreCompare.getGradeFourDifference()) <= -5) {
					gradeFourDownScores
							.add(Double.parseDouble(departmentAllGradeAverageScoreCompare.getGradeFourDifference()));
					gradeFourDownDepartmentNames.add(departmentAllGradeAverageScoreCompare.getDepartmentName());
				}
			}

			boolean gradeOneOverDifference = true;
			boolean gradeTwoOverDifference = true;
			boolean gradeThreeOverDifference = true;
			boolean gradeFourOverDifference = true;
			boolean gradeOneDownDifference = true;
			boolean gradeTwoDownDifference = true;
			boolean gradeThreeDownDifference = true;
			boolean gradeFourDownDifference = true;
			if (!departmentAllGradeAverageScoreCompare.getGradeOneDifference().equals("---")) {
				if (Double.parseDouble(departmentAllGradeAverageScoreCompare.getGradeOneDifference()) <= 0) {
					gradeOneOverDifference = false;
				}
				if (Double.parseDouble(departmentAllGradeAverageScoreCompare.getGradeOneDifference()) >= 0) {
					gradeOneDownDifference = false;
				}
			}
			if (!departmentAllGradeAverageScoreCompare.getGradeTwoDifference().equals("---")) {
				if (Double.parseDouble(departmentAllGradeAverageScoreCompare.getGradeTwoDifference()) <= 0) {
					gradeTwoOverDifference = false;
				}
				if (Double.parseDouble(departmentAllGradeAverageScoreCompare.getGradeTwoDifference()) >= 0) {
					gradeTwoDownDifference = false;
				}
			}
			if (!departmentAllGradeAverageScoreCompare.getGradeThreeDifference().equals("---")) {
				if (Double.parseDouble(departmentAllGradeAverageScoreCompare.getGradeThreeDifference()) <= 0) {
					gradeThreeOverDifference = false;
				}
				if (Double.parseDouble(departmentAllGradeAverageScoreCompare.getGradeThreeDifference()) >= 0) {
					gradeThreeDownDifference = false;
				}
			}
			if (!departmentAllGradeAverageScoreCompare.getGradeFourDifference().equals("---")) {
				if (Double.parseDouble(departmentAllGradeAverageScoreCompare.getGradeFourDifference()) <= 0) {
					gradeFourOverDifference = false;
				}
				if (Double.parseDouble(departmentAllGradeAverageScoreCompare.getGradeFourDifference()) >= 0) {
					gradeFourDownDifference = false;
				}
			}

			if (gradeOneOverDifference && gradeTwoOverDifference && gradeThreeOverDifference
					&& gradeFourOverDifference) {
				gradeAllOverDepartments.add(departmentAllGradeAverageScoreCompare.getDepartmentName());
			}
			if (gradeOneDownDifference && gradeTwoDownDifference && gradeThreeDownDifference
					&& gradeFourDownDifference) {
				gradeAllDownDepartments.add(departmentAllGradeAverageScoreCompare.getDepartmentName());
			}
		}
		String gradeOneOverDepartmentNamesStr = "无院系 ";
		String gradeTwoOverDepartmentNamesStr = "无院系 ";
		String gradeThreeOverDepartmentNamesStr = "无院系 ";
		String gradeFourOverDepartmentNamesStr = "无院系 ";

		String gradeOneDownDepartDiffStr = "无院系 ";
		String gradeTwoDownDepartDiffStr = "无院系 ";
		String gradeThreeDownDepartDiffStr = "无院系 ";
		String gradeFourDownDepartDiffStr = "无院系 ";
		String gradeAllOverDepartmentsStr = "无院系 ";
		String gradeAllDownDepartmentsStr = "无院系 ";
		if (gradeOneOverDepartmentNames.size() > 0) {
			gradeOneOverDepartmentNamesStr = "";
			for (int i = 0; i < gradeOneOverDepartmentNames.size(); i++) {
				gradeOneOverDepartmentNamesStr += gradeOneOverDepartmentNames.get(i) + "、";
			}
		}
		if (gradeTwoOverDepartmentNames.size() > 0) {
			gradeTwoOverDepartmentNamesStr = "";
			for (int i = 0; i < gradeTwoOverDepartmentNames.size(); i++) {
				gradeTwoOverDepartmentNamesStr += gradeTwoOverDepartmentNames.get(i) + "、";
			}
		}

		if (gradeThreeOverDepartmentNames.size() > 0) {
			gradeThreeOverDepartmentNamesStr = "";
			for (int i = 0; i < gradeThreeOverDepartmentNames.size(); i++) {
				gradeThreeOverDepartmentNamesStr += gradeThreeOverDepartmentNames.get(i) + "、";
			}
		}
		if (gradeFourOverDepartmentNames.size() > 0) {
			gradeFourOverDepartmentNamesStr = "";
			for (int i = 0; i < gradeFourOverDepartmentNames.size(); i++) {
				gradeFourOverDepartmentNamesStr += gradeFourOverDepartmentNames.get(i) + "、";
			}
		}

		if (gradeOneDownDepartmentNames.size() > 0) {
			gradeOneDownDepartDiffStr = "";
			for (int i = 0; i < gradeOneDownDepartmentNames.size(); i++) {
				gradeOneDownDepartDiffStr += gradeOneDownDepartmentNames.get(i) + "(" + gradeOneDownScores.get(i)
						+ ")、";
			}
		}
		if (gradeTwoDownDepartmentNames.size() > 0) {
			gradeTwoDownDepartDiffStr = "";
			for (int i = 0; i < gradeTwoDownDepartmentNames.size(); i++) {
				gradeTwoDownDepartDiffStr += gradeTwoDownDepartmentNames.get(i) + "(" + gradeTwoDownScores.get(i)
						+ ")、";
			}
		}
		if (gradeThreeDownDepartmentNames.size() > 0) {
			gradeThreeDownDepartDiffStr = "";
			for (int i = 0; i < gradeThreeDownDepartmentNames.size(); i++) {
				gradeThreeDownDepartDiffStr += gradeThreeDownDepartmentNames.get(i) + "(" + gradeThreeDownScores.get(i)
						+ ")、";
			}
		}
		if (gradeFourDownDepartmentNames.size() > 0) {
			gradeFourDownDepartDiffStr = "";
			for (int i = 0; i < gradeFourDownDepartmentNames.size(); i++) {
				gradeFourDownDepartDiffStr += gradeFourDownDepartmentNames.get(i) + "(" + gradeFourDownScores.get(i)
						+ ")、";
			}
		}
		if (gradeAllOverDepartments.size() > 0) {
			gradeAllOverDepartmentsStr = "";
			for (int i = 0; i < gradeAllOverDepartments.size(); i++) {
				gradeAllOverDepartmentsStr += gradeAllOverDepartments.get(i) + "、";
			}
		}
		if (gradeAllDownDepartments.size() > 0) {
			gradeAllDownDepartmentsStr = "";
			for (int i = 0; i < gradeAllDownDepartments.size(); i++) {
				gradeAllDownDepartmentsStr += gradeAllDownDepartments.get(i) + "、";
			}
		}
		List<DepartmentAllGradeAverageScoreCompare> dagascList = new ArrayList<>();
		for (Integer departmentId = 1; departmentId <= 19; departmentId++) {
			DepartmentAllGradeAverageScoreCompare departmentAllGradeAverageScoreCompare = dagascMap.get(departmentId);
			departmentAllGradeAverageScoreCompare.setAnalysis(
					"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;表2.6、图2.7统计结果显示：\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（1）"
							+ gradeOn + "级平均成绩大幅度（差值≥5）高于全校平均成绩的院系有："
							+ gradeOneOverDepartmentNamesStr.substring(0, gradeOneOverDepartmentNamesStr.length() - 1)
							+ "，平均成绩大幅度（差值≤5）低于全校平均成绩的院系有："
							+ gradeOneDownDepartDiffStr.substring(0, gradeOneDownDepartDiffStr.length() - 1) + "；\n"
							+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（2）" + gradeTw
							+ "级平均成绩大幅度（差值≥5）高于全校平均成绩的院系有："
							+ gradeTwoOverDepartmentNamesStr.substring(0, gradeTwoOverDepartmentNamesStr.length() - 1)
							+ "，平均成绩大幅度（差值≤5）低于全校平均成绩的院系有："
							+ gradeTwoDownDepartDiffStr.substring(0, gradeTwoDownDepartDiffStr.length() - 1) + "；\n"
							+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（3）" + gradeTh
							+ "级平均成绩大幅度（差值≥5）高于全校平均成绩的院系有："
							+ gradeThreeOverDepartmentNamesStr.substring(0,
									gradeThreeOverDepartmentNamesStr.length() - 1)
							+ "，平均成绩大幅度（差值≤5）低于全校平均成绩的院系有："
							+ gradeThreeDownDepartDiffStr.substring(0, gradeThreeDownDepartDiffStr.length() - 1) + "；\n"
							+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（4）" + gradeFo
							+ "级平均成绩大幅度（差值≥5）高于全校平均成绩的院系有："
							+ gradeFourOverDepartmentNamesStr.substring(0, gradeFourOverDepartmentNamesStr.length() - 1)
							+ "，平均成绩大幅度（差值≤5）低于全校平均成绩的院系有："
							+ gradeFourDownDepartDiffStr.substring(0, gradeFourDownDepartDiffStr.length() - 1) + "；\n"
							+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（5）四个年级的平均成绩均在全校平均成绩之上的院系有："
							+ gradeAllOverDepartmentsStr.substring(0, gradeAllOverDepartmentsStr.length() - 1)
							+ "；四个年级的平均成绩均在全校平均成绩之下的院系有："
							+ gradeAllDownDepartmentsStr.substring(0, gradeAllDownDepartmentsStr.length() - 1) + "。\n"
							+ "##&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;将各院系所有年级的必修、专业选修平均成绩与全校平均分对比，差值为院系各年级的平均分减该年级的全校平均分，见下表：");
			dagascList.add(departmentAllGradeAverageScoreCompare);
		}
		return dagascList;
	}

	/**
	 * 获得各学院所有年级的平均成绩和差值
	 * 
	 * @param year
	 * @param term
	 * @return
	 */
	public List<DepartmentAllGradeAverageScoreCompare> getRPECDepartmentAllGradeAverageScoreCompareList(String year,
			Integer term) {
		List<List<GradeDepartmentAverageScoreCompare>> gdascListList = getRPECGradeDepartmentAverageScoreCompareListList(
				year, term);
		List<DepartmentAllGradeAverageScoreCompare> dagascList = getRPECDepartmentAllGradeAverageScoreCompareListByGradeDepartmentAverageScoreCompareListList(
				gdascListList, year);
		return dagascList;
	}

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
	public List<GradeFailDistribution> getRCGradeFailDistributionList(String year, Integer term) {
		// 学生不及格率列表
		ArrayList<Double> allFailRates = new ArrayList<>();
		// 不及格年级
		ArrayList<String> allFailRateGrades = new ArrayList<>();
		// 高于全校平均水平的学生不及格率列表
		ArrayList<Double> overAllFailRates = new ArrayList<>();
		// 高于全校平均水平的年级
		ArrayList<String> overAllFailRateGrades = new ArrayList<>();
		// 低于全校平均水平的学生不及格率列表
		ArrayList<Double> downAllFailRates = new ArrayList<>();
		// 低于全校平均水平的年级
		ArrayList<String> downAllFailRateGrades = new ArrayList<>();
		// 不及格成绩门数为1的学生人数
		int failRateOneStudents = 0;
		// 不及格成绩门数≥4的学生人数
		int failRateOverFourStudents = 0;
		// 不及格成绩门数为1的学生占有不及格成绩学生总数概率
		String termStr = "";
		if (term == 1) {
			termStr = "第一学期";
		} else if (term == 2) {
			termStr = "第二学期";
		}
		Double rate = 0.00;
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
		Integer id = 1;
		for (Integer gradeI = gradeOne - 3; gradeI <= gradeOne; gradeI++) {
			GradeFailDistribution gradeFailDistribution = new GradeFailDistribution();
			gradeFailDistribution = getRCGradeFailDistributionByGrade(gradeI, year, term);
			gradeFailDistribution.setId(id++);
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
			// System.out.println("9999"+gradeFailDistribution.getFailRate());
			allFailRates.add(Double.parseDouble(gradeFailDistribution.getFailRate().substring(0,
					gradeFailDistribution.getFailRate().length() - 1)));
			allFailRateGrades.add(gradeFailDistribution.getGrade());
		}
		GradeFailDistribution gradeFailDistribution = new GradeFailDistribution();
		gradeFailDistribution.setId(id);
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

			failRateOneStudents = gradeFailDistribution.getOneFailNumber();
			failRateOverFourStudents = gradeFailDistribution.getFourFailNumber()
					+ gradeFailDistribution.getFiveFailNumber() + gradeFailDistribution.getSixFailNumber()
					+ gradeFailDistribution.getSevenFailNumber() + gradeFailDistribution.getEightFailNumber();
			// 不及格人数合计
			rate = (double) failRateOneStudents / gradeFailDistribution.getTotalFailNumber();
			DecimalFormat rateDF1 = new DecimalFormat("0.00%");
			String strFailRate1 = rateDF.format(rate);
			// 全校平均水平的学生不及格率
			Double averageFailRate = Double.parseDouble(
					gradeFailDistribution.getFailRate().substring(0, gradeFailDistribution.getFailRate().length() - 1));
			for (int i = 0; i < allFailRates.size(); i++) {
				if (allFailRates.get(i) > averageFailRate) {
					overAllFailRates.add(allFailRates.get(i));
					overAllFailRateGrades.add(allFailRateGrades.get(i));
				}

				if (allFailRates.get(i) < averageFailRate) {
					downAllFailRates.add(allFailRates.get(i));
					downAllFailRateGrades.add(allFailRateGrades.get(i));
				}
			}
			String overAllFailRateStr = "";
			String downAllFailRateStr = "";
			if (overAllFailRates.size() > 0) {
				for (int i = 0; i < overAllFailRates.size(); i++) {
					overAllFailRateStr += overAllFailRateGrades.get(i) + "级（" + overAllFailRates.get(i) + "%）、";
				}
			}
			if (downAllFailRates.size() > 0) {
				for (int i = 0; i < downAllFailRates.size(); i++) {
					downAllFailRateStr += downAllFailRateGrades.get(i) + "级（" + downAllFailRates.get(i) + "%）、";
				}
			}
			gradeFailDistribution.setAnalysis(
					"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;表3.1、图3.1-3.2统计结果显示：\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（1）全校必修课有不及格成绩的学生共"
							+ gradeFailDistribution.getTotalFailNumber() + "人，学生不及格率为"
							+ gradeFailDistribution.getFailRate() + "，四个年级中，学生不及格率高于全校平均水平的年级是"
							+ overAllFailRateStr.substring(0, overAllFailRateStr.length() - 1) + "；学生不及格率低于全校平均水平的年级是"
							+ downAllFailRateStr.substring(0, downAllFailRateStr.length() - 1) + "；\n"
							+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（2）不及格成绩门数为1的学生有" + failRateOneStudents
							+ "人，占有不及格成绩学生总数的" + strFailRate1 + "；不及格成绩门数≥4的学生有" + failRateOverFourStudents
							+ "人，按照学籍管理规定，这部分学生已处于留降级、退学的边缘，应给予重点关注。"
							+ "#&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;课程考核成绩不及格将会对学生留降级、退学、毕业、就业、出国留学等多方面带来影响，是学生、家长、老师都十分关注的问题。同时，课程不及格情况也能反映学生的学习态度和教师的授课质量，本部分将对"
							+ year + "学年度" + termStr + "期末考试不及格情况进行分析。本章统计数据只涵盖全校各年级必修课期末考试成绩。#"
							+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + year + "学年" + termStr
							+ "全校各年级学生必修课不及格门数及各年级学生不及格率统计如下:");
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

			double oneFailRate = (double) oneFailNumber / totalStudentNumber;
			double twoFailRate = (double) twoFailNumber / totalStudentNumber;
			double threeFailRate = (double) threeFailNumber / totalStudentNumber;
			double geFourFailRate = (double) geFourFailNumber / totalStudentNumber;
			double totalFailRate = (double) totalFailNumber / totalStudentNumber;

			DecimalFormat rateDF = new DecimalFormat("0.00%");
			String strOneFailRate = rateDF.format(oneFailRate);
			String strTwoFailRate = rateDF.format(twoFailRate);
			String strThreeFailRate = rateDF.format(threeFailRate);
			String strGeFourFailRate = rateDF.format(geFourFailRate);
			String strTotalFailRate = rateDF.format(totalFailRate);

			departmentFailDistribution.setOneFailNumber(oneFailNumber);
			departmentFailDistribution.setTwoFailNumber(twoFailNumber);
			departmentFailDistribution.setThreeFailNumber(threeFailNumber);
			departmentFailDistribution.setGeFourFailNumber(geFourFailNumber);
			departmentFailDistribution.setOneFailRate(strOneFailRate);
			departmentFailDistribution.setTwoFailRate(strTwoFailRate);
			departmentFailDistribution.setThreeFailRate(strThreeFailRate);
			departmentFailDistribution.setGeFourFailRate(strGeFourFailRate);
			departmentFailDistribution.setTotalFailNumber(totalFailNumber);
			departmentFailDistribution.setTotalStudentNumber(totalStudentNumber);
			departmentFailDistribution.setTotalFailRate(strTotalFailRate);
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
	public List<DepartmentFailDistribution> getRCDepartmentFailDistributionList(String year, Integer term) {
		// 不及格率列表
		ArrayList<Double> failRates = new ArrayList<>();
		// 不及格院系
		ArrayList<String> failRateDepartments = new ArrayList<>();
		// 学生不及格率高于全校平均水平的院系
		ArrayList<String> overAllFailRateDepartments = new ArrayList<>();
		// 最高的不及格率
		Double failRateHighest = 0.00;
		// 最高不及格率的院系
		String failRateHighestDepartment = "";
		// 不及格率低于10%的院系
		ArrayList<String> downTenfailRateDepartments = new ArrayList<>();
		// 全校最低的不及格率
		Double failRateLowest = 100.00;
		// 不及格率全校最低的院系
		String failRateLowestDepartment = "";
		List<DepartmentFailDistribution> dfdList = new ArrayList<>();
		Integer oneFailNumber = 0;
		Integer twoFailNumber = 0;
		Integer threeFailNumber = 0;
		Integer geFourFailNumber = 0;
		Integer totalFailNumber = 0;
		Integer totalStudentNumber = 0;
		Integer id = 1;
		for (Integer departmentId = 1; departmentId <= 19; departmentId++) {
			DepartmentFailDistribution departmentFailDistribution = getRCDepartmentFailDistributionByDepartmentId(
					departmentId, year, term);
			departmentFailDistribution.setId(id++);
			dfdList.add(departmentFailDistribution);
			oneFailNumber += departmentFailDistribution.getOneFailNumber();
			twoFailNumber += departmentFailDistribution.getTwoFailNumber();
			threeFailNumber += departmentFailDistribution.getThreeFailNumber();
			geFourFailNumber += departmentFailDistribution.getGeFourFailNumber();
			totalFailNumber += departmentFailDistribution.getTotalFailNumber();
			totalStudentNumber += departmentFailDistribution.getTotalStudentNumber();
			failRates.add(Double.parseDouble(departmentFailDistribution.getTotalFailRate().substring(0,
					departmentFailDistribution.getTotalFailRate().length() - 1)));
			failRateDepartments.add(departmentFailDistribution.getDepartmentName());
		}
		DepartmentFailDistribution departmentFailDistribution = new DepartmentFailDistribution();
		departmentFailDistribution.setId(id);
		departmentFailDistribution.setDepartmentName("全校");
		if (totalStudentNumber != 0) {
			totalFailNumber = oneFailNumber + twoFailNumber + threeFailNumber + geFourFailNumber;

			double oneFailRate = (double) oneFailNumber / totalStudentNumber;
			double twoFailRate = (double) twoFailNumber / totalStudentNumber;
			double threeFailRate = (double) threeFailNumber / totalStudentNumber;
			double geFourFailRate = (double) geFourFailNumber / totalStudentNumber;
			double totalFailRate = (double) totalFailNumber / totalStudentNumber;

			DecimalFormat rateDF = new DecimalFormat("0.00%");
			String strOneFailRate = rateDF.format(oneFailRate);
			String strTwoFailRate = rateDF.format(twoFailRate);
			String strThreeFailRate = rateDF.format(threeFailRate);
			String strGeFourFailRate = rateDF.format(geFourFailRate);
			String strTotalFailRate = rateDF.format(totalFailRate);

			departmentFailDistribution.setOneFailNumber(oneFailNumber);
			departmentFailDistribution.setTwoFailNumber(twoFailNumber);
			departmentFailDistribution.setThreeFailNumber(threeFailNumber);
			departmentFailDistribution.setGeFourFailNumber(geFourFailNumber);
			departmentFailDistribution.setOneFailRate(strOneFailRate);
			departmentFailDistribution.setTwoFailRate(strTwoFailRate);
			departmentFailDistribution.setThreeFailRate(strThreeFailRate);
			departmentFailDistribution.setGeFourFailRate(strGeFourFailRate);
			departmentFailDistribution.setTotalFailNumber(totalFailNumber);
			departmentFailDistribution.setTotalStudentNumber(totalStudentNumber);
			departmentFailDistribution.setTotalFailRate(strTotalFailRate);
			double averageFailRate = Double.parseDouble(departmentFailDistribution.getTotalFailRate().substring(0,
					departmentFailDistribution.getTotalFailRate().length() - 1));
			if (failRates.size() > 0) {
				for (int i = 0; i < failRates.size(); i++) {
					if (failRates.get(i) > averageFailRate) {
						overAllFailRateDepartments.add(failRateDepartments.get(i));
					}
					if (failRateHighest < failRates.get(i)) {
						failRateHighest = failRates.get(i);
						failRateHighestDepartment = failRateDepartments.get(i);
					}
					if (failRates.get(i) < 10) {
						downTenfailRateDepartments.add(failRateDepartments.get(i));
					}
					if (failRateLowest > failRates.get(i)) {
						failRateLowest = failRates.get(i);
						failRateLowestDepartment = failRateDepartments.get(i);
					}
				}
			}
			String overAllFailRateDepartmentsStr = "";
			String downTenfailRateDepartmentsStr = "";
			for (int i = 0; i < overAllFailRateDepartments.size(); i++) {
				overAllFailRateDepartmentsStr += overAllFailRateDepartments.get(i) + "、";
			}
			for (int i = 0; i < downTenfailRateDepartments.size(); i++) {
				downTenfailRateDepartmentsStr += downTenfailRateDepartments.get(i) + "、";
			}
			departmentFailDistribution.setAnalysis(
					"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;表3.2、图3.3统计结果显示：\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;全校学生不及格率为"
							+ departmentFailDistribution.getTotalFailRate() + "，学生不及格率高于全校平均水平的院系有："
							+ overAllFailRateDepartmentsStr.substring(0, overAllFailRateDepartmentsStr.length() - 1)
							+ "，其中，" + failRateHighestDepartment + "学生不及格率最高，为" + failRateHighest + "%；"
							+ downTenfailRateDepartmentsStr.substring(0, downTenfailRateDepartmentsStr.length() - 1)
							+ "学生不及格率低于10%，其中" + failRateLowestDepartment + "学生不及格率全校最低，仅为" + failRateLowest
							+ "%。##&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;对不及格学生情况进行院系间的对比，统计如下：");
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
		if (departmentId != 6) {
			for (Integer gradeI = gradeOne - 3; gradeI <= gradeOne; gradeI++) {
				totalStudentNumber += studentCourseMapper.getTotalStudentNumberByGradeAndDepartmentId(gradeI,
						departmentId);
				List<Integer> studentIdList = new ArrayList<>();
				studentIdList = studentCourseMapper.getStudentIdListByGradeAndDepartmentId(gradeI, departmentId);
				for (Integer studentId : studentIdList) {
					Integer failCourseNumber = studentCourseMapper.getRCFailCourseNumberByStudentId(studentId, year,
							term);
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
				} // for(studentId)
			} // for(departmentId)
		} else {
			List<Integer> departmentIdList = Arrays.asList(6, 20, 21);
			for (Integer id : departmentIdList) {
				for (Integer gradeI = gradeOne - 3; gradeI <= gradeOne; gradeI++) {
					totalStudentNumber += studentCourseMapper.getTotalStudentNumberByGradeAndDepartmentId(gradeI, id);
					List<Integer> studentIdList = new ArrayList<>();
					studentIdList = studentCourseMapper.getStudentIdListByGradeAndDepartmentId(gradeI, id);
					for (Integer studentId : studentIdList) {
						Integer failCourseNumber = studentCourseMapper.getRCFailCourseNumberByStudentId(studentId, year,
								term);
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
					} // for(studentId)
				} // for(grade)
			} // for(departmentId)
		} // else
		if (totalStudentNumber != 0) {
			totalFailNumber = oneFailNumber + twoFailNumber + threeFailNumber + geFourFailNumber;

			double oneFailRate = (double) oneFailNumber / totalStudentNumber;
			double twoFailRate = (double) twoFailNumber / totalStudentNumber;
			double threeFailRate = (double) threeFailNumber / totalStudentNumber;
			double geFourFailRate = (double) geFourFailNumber / totalStudentNumber;
			double totalFailRate = (double) totalFailNumber / totalStudentNumber;

			DecimalFormat rateDF = new DecimalFormat("0.00%");
			String strOneFailRate = rateDF.format(oneFailRate);
			String strTwoFailRate = rateDF.format(twoFailRate);
			String strThreeFailRate = rateDF.format(threeFailRate);
			String strGeFourFailRate = rateDF.format(geFourFailRate);
			String strTotalFailRate = rateDF.format(totalFailRate);

			departmentFailDistribution.setOneFailNumber(oneFailNumber);
			departmentFailDistribution.setTwoFailNumber(twoFailNumber);
			departmentFailDistribution.setThreeFailNumber(threeFailNumber);
			departmentFailDistribution.setGeFourFailNumber(geFourFailNumber);
			departmentFailDistribution.setOneFailRate(strOneFailRate);
			departmentFailDistribution.setTwoFailRate(strTwoFailRate);
			departmentFailDistribution.setThreeFailRate(strThreeFailRate);
			departmentFailDistribution.setGeFourFailRate(strGeFourFailRate);
			departmentFailDistribution.setTotalFailNumber(totalFailNumber);
			departmentFailDistribution.setTotalStudentNumber(totalStudentNumber);
			departmentFailDistribution.setTotalFailRate(strTotalFailRate);
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
				failStudentIdList = studentCourseMapper.getRCFailStudentIdListByGradeAndDepartmentId(grade,
						departmentId, year, term);
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
				failStudentIdList = studentCourseMapper.getRCFailStudentIdListByGradeAndDepartmentId(grade, id, year,
						term);
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
		for (Integer departmentId = 1; departmentId <= 19; departmentId++) {
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
		for (Integer gradeI = gradeOne; gradeI >= gradeOne - 3; gradeI--) { // 几乎所有的List都是按大四->大三->大二->大一存储的，这里因为是中间步骤，而且为了最后的显示操作，将顺序反过来了
			List<GradeDepartmentFailDistribution> gdfdList = new ArrayList<>();
			gdfdList = getRCGradeDepartmentFailDistributionListByGrade(gradeI, year, term);
			gdfdListList.add(gdfdList);
		}
		return gdfdListList;
	}

	/**
	 * 通过 GradeDepartmentFailDistributionListList 获得
	 * DepartmentAllGradeFailDistributionList
	 * 
	 * @param gdfdListList
	 * @param year
	 * @return
	 */
	public List<DepartmentAllGradeFailDistribution> getRCDepartmentAllGradeFailDistributionListByGradeDepartmentFailDistributionListList(
			List<List<GradeDepartmentFailDistribution>> gdfdListList, String year) {
		// 全校人数，列表顺序代表年级
		ArrayList<Integer> allStudentNumbers = new ArrayList<>();
		// 全校不及格人数，列表顺序代表年级
		ArrayList<Integer> allFailRateStudentNumbers = new ArrayList<>();
		// 全校各年级不及格率，列表顺序代表年级
		ArrayList<String> allFailRates = new ArrayList<>();
		// 大一学生不及格率高于全校平均水平的院系
		ArrayList<String> overAllOneFailRateDepartments = new ArrayList<>();
		// 大二学生不及格率高于全校平均水平的院系
		ArrayList<String> overAllTwoFailRateDepartments = new ArrayList<>();
		// 大三学生不及格率高于全校平均水平的院系
		ArrayList<String> overAllThreeFailRateDepartments = new ArrayList<>();
		// 大四学生不及格率高于全校平均水平的院系
		ArrayList<String> overAllFourFailRateDepartments = new ArrayList<>();
		// 大一不及格率最高的院系
		String oneFailRateDepartment = "";
		// 大一最高的不及格率
		double oneFailRate = 0.00;
		// 大二不及格率最高的院系
		String twoFailRateDepartment = "";
		// 大二最高的不及格率
		double twoFailRate = 0.00;
		// 大三不及格率最高的院系
		String threeFailRateDepartment = "";
		// 大三最高的不及格率
		double threeFailRate = 0.00;
		// 大四不及格率最高的院系
		String fourFailRateDepartment = "";
		// 大四最高的不及格率
		double fourFailRate = 0.00;
		// 全校各院系不及格率列表
		ArrayList<Double> allDeprtmentFailRates = new ArrayList<>();
		// 全校各院系不及格率对应的年级
		ArrayList<String> allFailRateDepartments = new ArrayList<>();
		// 全校最高的不及格率
		double allFailRateHigest = 0.00;
		// 全校最高不及格率的院系
		String allFailRateHigestDepartment = "";
		// 全校较高的不及格率
		double allFailRateHiger = 0.00;
		// 全校较高不及格率的院系
		String allFailRateHigerDepartment = "";
		String strGradeOn = year.substring(0, 4);
		Integer gradeOn = Integer.parseInt(strGradeOn) - 3;// 大一
		Integer gradeTw = gradeOn + 1;// 大二
		Integer gradeTh = gradeOn + 2;
		Integer gradeFo = gradeOn + 3;
		Map<Integer, DepartmentAllGradeFailDistribution> dagfdMap = new HashMap<>();
		for (Integer departmentId = 1; departmentId <= 19; departmentId++) {
			DepartmentAllGradeFailDistribution departmentAllGradeFailDistribution = new DepartmentAllGradeFailDistribution();
			String departmentName = getDepartmentNameByDepartmentId(departmentId);
			departmentAllGradeFailDistribution.setId(departmentId);
			departmentAllGradeFailDistribution.setDepartmentName(departmentName);
			dagfdMap.put(departmentId, departmentAllGradeFailDistribution);
		}
		String strGradeOne = year.substring(0, 4);
		Integer gradeOne = Integer.parseInt(strGradeOne);
		for (Integer gradeI = gradeOne - 3; gradeI <= gradeOne; gradeI++) {
			Integer grade = gradeOne - gradeI + 1; // grade为本科的年级，取值1，2，3，4
			for (Integer departmentId = 1; departmentId <= 19; departmentId++) {
				GradeDepartmentFailDistribution gradeDepartmentFailDistribution = gdfdListList.get(grade - 1)
						.get(departmentId - 1);
				DepartmentAllGradeFailDistribution departmentAllGradeFailDistribution = dagfdMap.get(departmentId);
				Integer totalStudentNumber = gradeDepartmentFailDistribution.getTotalStudentNumber();
				Integer totalFailNumber = gradeDepartmentFailDistribution.getTotalFailNumber();
				String failRate = gradeDepartmentFailDistribution.getFailRate();
				switch (grade) {
				case 1:
					departmentAllGradeFailDistribution.setGradeOneStudentNumber(totalStudentNumber);
					departmentAllGradeFailDistribution.setGradeOneFailNumber(totalFailNumber);
					departmentAllGradeFailDistribution.setGradeOneFailRate(failRate);
					break;
				case 2:
					departmentAllGradeFailDistribution.setGradeTwoStudentNumber(totalStudentNumber);
					departmentAllGradeFailDistribution.setGradeTwoFailNumber(totalFailNumber);
					departmentAllGradeFailDistribution.setGradeTwoFailRate(failRate);
					break;
				case 3:
					departmentAllGradeFailDistribution.setGradeThreeStudentNumber(totalStudentNumber);
					departmentAllGradeFailDistribution.setGradeThreeFailNumber(totalFailNumber);
					departmentAllGradeFailDistribution.setGradeThreeFailRate(failRate);
					break;
				case 4:
					departmentAllGradeFailDistribution.setGradeFourStudentNumber(totalStudentNumber);
					departmentAllGradeFailDistribution.setGradeFourFailNumber(totalFailNumber);
					departmentAllGradeFailDistribution.setGradeFourFailRate(failRate);
					break;
				} // switch
				dagfdMap.put(departmentId, departmentAllGradeFailDistribution);
			} // for
		} // for
		int oneStudentNumber = 0;// 大一学生数（所有院系）
		int twoStudentNumber = 0;// 大二学生数（所有院系）
		int threeStudentNumber = 0;// 大三学生数（所有院系）
		int fourStudentNumber = 0;// 大四学生数（所有院系）
		int oneFailRateStudentNumber = 0;// 大一不及格学生数（所有院系）
		int twoFailRateStudentNumber = 0;// 大二不及格学生数（所有院系）
		int threeFailRateStudentNumber = 0;// 大三不及格学生数（所有院系）
		int fourFailRateStudentNumber = 0;// 大四不及格学生数（所有院系）
		for (Integer departmentId = 1; departmentId <= 19; departmentId++) {
			DepartmentAllGradeFailDistribution departmentAllGradeFailDistribution = dagfdMap.get(departmentId);
			oneStudentNumber += departmentAllGradeFailDistribution.getGradeOneStudentNumber();
			twoStudentNumber += departmentAllGradeFailDistribution.getGradeTwoStudentNumber();
			threeStudentNumber += departmentAllGradeFailDistribution.getGradeThreeStudentNumber();
			fourStudentNumber += departmentAllGradeFailDistribution.getGradeFourStudentNumber();
			oneFailRateStudentNumber += departmentAllGradeFailDistribution.getGradeOneFailNumber();
			twoFailRateStudentNumber += departmentAllGradeFailDistribution.getGradeTwoFailNumber();
			threeFailRateStudentNumber += departmentAllGradeFailDistribution.getGradeThreeFailNumber();
			fourFailRateStudentNumber += departmentAllGradeFailDistribution.getGradeFourFailNumber();
			double one = Double.parseDouble(departmentAllGradeFailDistribution.getGradeOneFailRate().substring(0,
					departmentAllGradeFailDistribution.getGradeOneFailRate().length() - 1));
			double two = Double.parseDouble(departmentAllGradeFailDistribution.getGradeTwoFailRate().substring(0,
					departmentAllGradeFailDistribution.getGradeTwoFailRate().length() - 1));
			double three = Double.parseDouble(departmentAllGradeFailDistribution.getGradeThreeFailRate().substring(0,
					departmentAllGradeFailDistribution.getGradeThreeFailRate().length() - 1));
			double four = Double.parseDouble(departmentAllGradeFailDistribution.getGradeFourFailRate().substring(0,
					departmentAllGradeFailDistribution.getGradeFourFailRate().length() - 1));

			if (oneFailRate < one) {
				oneFailRate = one;
				oneFailRateDepartment = departmentAllGradeFailDistribution.getDepartmentName();
			}
			if (twoFailRate < two) {
				twoFailRate = two;
				twoFailRateDepartment = departmentAllGradeFailDistribution.getDepartmentName();
			}
			if (threeFailRate < three) {
				threeFailRate = three;
				threeFailRateDepartment = departmentAllGradeFailDistribution.getDepartmentName();
			}
			if (fourFailRate < four) {
				fourFailRate = four;
				fourFailRateDepartment = departmentAllGradeFailDistribution.getDepartmentName();
			}

		}
		allStudentNumbers.add(oneStudentNumber);
		allStudentNumbers.add(twoStudentNumber);
		allStudentNumbers.add(threeStudentNumber);
		allStudentNumbers.add(fourStudentNumber);
		allFailRateStudentNumbers.add(oneFailRateStudentNumber);
		allFailRateStudentNumbers.add(twoFailRateStudentNumber);
		allFailRateStudentNumbers.add(threeFailRateStudentNumber);
		allFailRateStudentNumbers.add(fourFailRateStudentNumber);
		for (int i = 0; i < 4; i++) {
			if (allStudentNumbers.get(i) != 0) {
				double failRate = (double) allFailRateStudentNumbers.get(i) / allStudentNumbers.get(i);
				DecimalFormat rateDF = new DecimalFormat("0.00%");
				String strFailRate = rateDF.format(failRate);
				// System.out.println(allFailRateStudentNumbers.get(i)+"
				// "+allStudentNumbers.get(i));
				allFailRates.add(strFailRate);
			} else {
				allFailRates.add("0.00%");
			}

		}
		for (int i = 0; i < allFailRates.size(); i++) {

			double currentRate = Double.parseDouble(allFailRates.get(i).substring(0, allFailRates.get(i).length() - 1));

			if (allFailRateHigest < currentRate) {
				if (allFailRateHigest != 0.00) {
					allFailRateHiger = allFailRateHigest;
					allFailRateHigerDepartment = allFailRateHigestDepartment;
				}
				allFailRateHigest = currentRate;

				switch (i) {
				case 0:
					allFailRateHigestDepartment = gradeOn.toString();
					break;
				case 1:
					allFailRateHigestDepartment = gradeTw.toString();
					break;
				case 2:
					allFailRateHigestDepartment = gradeTh.toString();
					break;
				case 3:
					allFailRateHigestDepartment = gradeFo.toString();
					break;
				default:
					break;
				}
			}
			if (allFailRateHigest > currentRate && allFailRateHiger < currentRate) {
				allFailRateHiger = currentRate;
				switch (i) {
				case 0:
					allFailRateHigerDepartment = gradeOn.toString();
					break;
				case 1:
					allFailRateHigerDepartment = gradeTw.toString();
					break;
				case 2:
					allFailRateHigerDepartment = gradeTh.toString();
					break;
				case 3:
					allFailRateHigerDepartment = gradeFo.toString();
					break;
				default:
					break;
				}
			}
		}
		for (Integer departmentId = 1; departmentId <= 19; departmentId++) {
			DepartmentAllGradeFailDistribution departmentAllGradeFailDistribution = dagfdMap.get(departmentId);
			double one = Double.parseDouble(allFailRates.get(0).substring(0, allFailRates.get(0).length() - 1));
			double two = Double.parseDouble(allFailRates.get(1).substring(0, allFailRates.get(1).length() - 1));
			double three = Double.parseDouble(allFailRates.get(2).substring(0, allFailRates.get(2).length() - 1));
			double four = Double.parseDouble(allFailRates.get(3).substring(0, allFailRates.get(3).length() - 1));

			if (one < Double.parseDouble(departmentAllGradeFailDistribution.getGradeOneFailRate().substring(0,
					departmentAllGradeFailDistribution.getGradeOneFailRate().length() - 1))) {
				overAllOneFailRateDepartments.add(departmentAllGradeFailDistribution.getDepartmentName());

			}
			if (two < Double.parseDouble(departmentAllGradeFailDistribution.getGradeTwoFailRate().substring(0,
					departmentAllGradeFailDistribution.getGradeTwoFailRate().length() - 1))) {
				overAllTwoFailRateDepartments.add(departmentAllGradeFailDistribution.getDepartmentName());
			}
			if (three < Double.parseDouble(departmentAllGradeFailDistribution.getGradeThreeFailRate().substring(0,
					departmentAllGradeFailDistribution.getGradeThreeFailRate().length() - 1))) {
				overAllThreeFailRateDepartments.add(departmentAllGradeFailDistribution.getDepartmentName());
			}
			if (four < Double.parseDouble(departmentAllGradeFailDistribution.getGradeFourFailRate().substring(0,
					departmentAllGradeFailDistribution.getGradeFourFailRate().length() - 1))) {
				overAllFourFailRateDepartments.add(departmentAllGradeFailDistribution.getDepartmentName());
			}

		}
		String overAllOneFailRateDepartmentsStr = "无院系 ";
		String overAllTwoFailRateDepartmentsStr = "无院系 ";
		String overAllThreeFailRateDepartmentsStr = "无院系 ";
		String overAllFourFailRateDepartmentsStr = "无院系 ";
		if (overAllOneFailRateDepartments.size() > 0) {
			overAllOneFailRateDepartmentsStr = "";
			for (int i = 0; i < overAllOneFailRateDepartments.size(); i++) {
				overAllOneFailRateDepartmentsStr += overAllOneFailRateDepartments.get(i) + "、";
			}
		}
		// System.out.println("999999:"+overAllTwoFailRateDepartments.size());
		if (overAllTwoFailRateDepartments.size() > 0) {
			overAllTwoFailRateDepartmentsStr = "";
			for (int i = 0; i < overAllTwoFailRateDepartments.size(); i++) {
				overAllTwoFailRateDepartmentsStr += overAllTwoFailRateDepartments.get(i) + "、";
			}
		}
		if (overAllThreeFailRateDepartments.size() > 0) {
			overAllThreeFailRateDepartmentsStr = "";
			for (int i = 0; i < overAllThreeFailRateDepartments.size(); i++) {
				overAllThreeFailRateDepartmentsStr += overAllThreeFailRateDepartments.get(i) + "、";
			}
		}
		if (overAllFourFailRateDepartments.size() > 0) {
			overAllFourFailRateDepartmentsStr = "";
			for (int i = 0; i < overAllFourFailRateDepartments.size(); i++) {
				overAllFourFailRateDepartmentsStr += overAllFourFailRateDepartments.get(i) + "、";
			}
		}

		List<DepartmentAllGradeFailDistribution> dagfdList = new ArrayList<>();
		for (Integer departmentId = 1; departmentId <= 19; departmentId++) {
			DepartmentAllGradeFailDistribution departmentAllGradeFailDistribution = dagfdMap.get(departmentId);
			departmentAllGradeFailDistribution.setAnalysis(
					"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;表3.3、图3.4统计数据显示：\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（1）"
							+ gradeOn + "级全校学生不及格率为" + allFailRates.get(0) + "，学生不及格率高于全校平均水平的院系有："
							+ overAllOneFailRateDepartmentsStr.substring(0,
									overAllOneFailRateDepartmentsStr.length() - 1)
							+ "，其中" + oneFailRateDepartment + "学生不及格率最高，为" + oneFailRate + "%；\n"
							+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（2）" + gradeTw + "级全校学生不及格率为"
							+ allFailRates.get(1) + "，学生不及格率高于全校平均水平的院系有："
							+ overAllTwoFailRateDepartmentsStr.substring(0,
									overAllTwoFailRateDepartmentsStr.length() - 1)
							+ "，其中" + twoFailRateDepartment + "学生不及格率最高，为" + twoFailRate + "%；\n"
							+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（3）" + gradeTh + "级全校学生不及格率为"
							+ allFailRates.get(2) + "，学生不及格率高于全校平均水平的院系有："
							+ overAllThreeFailRateDepartmentsStr.substring(0,
									overAllThreeFailRateDepartmentsStr.length() - 1)
							+ "，其中" + threeFailRateDepartment + "学生不及格率最高，为" + threeFailRate + "%；\n"
							+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（4）" + gradeFo + "级全校学生不及格率为"
							+ allFailRates.get(3) + "，学生不及格率高于全校平均水平的院系有："
							+ overAllFourFailRateDepartmentsStr.substring(0,
									overAllFourFailRateDepartmentsStr.length() - 1)
							+ "，其中" + fourFailRateDepartment + "学生不及格率最高，为" + fourFailRate + "%。\n"
							+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（5）所有年级中，" + allFailRateHigestDepartment
							+ "级不及格率最高，为" + allFailRateHigest + "%，其次为" + allFailRateHigerDepartment + "级，为"
							+ allFailRateHiger + "%；"
							+ "##&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;对各院系学生不及格情况进行分年级统计，" + gradeOn + "级、"
							+ gradeTw + "级、" + gradeTh + "级、" + gradeFo + "级学生不及格情况如下表所示。");
			dagfdList.add(departmentAllGradeFailDistribution);
		}
		return dagfdList;
	}

	/**
	 * 获得各院系所有年级RC的不及格情况
	 * 
	 * @param year
	 * @param term
	 * @return
	 */
	public List<DepartmentAllGradeFailDistribution> getRCDepartmentAllGradeFailDistributionList(String year,
			Integer term) {
		List<List<GradeDepartmentFailDistribution>> gdfdListList = getRCGradeDepartmentFailDistributionListList(year,
				term);
		List<DepartmentAllGradeFailDistribution> dagfdList = getRCDepartmentAllGradeFailDistributionListByGradeDepartmentFailDistributionListList(
				gdfdListList, year);
		return dagfdList;
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
		List<String> classNumberList = getClassNumberListByGrade(grade);
		Integer id = 1;
		for (String classNumber : classNumberList) {
			ClassFailDistribution classFailDistribution = new ClassFailDistribution();
			classFailDistribution = getRCClassFailDistributionByClassNumber(classNumber, year, term);
			if (classFailDistribution.getTotalStudentNumber() >= 10) {
				classFailDistribution.setId(id++);
				cfdList.add(classFailDistribution);
			}
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
		int absenceGradeHighest = 0;// 缺考最多的年级
		int absenceNumberHighest = 0;// 缺考最多的学生数量
		String absenceSubjectHighest = "";// 缺考最多的科目
		int absenceGradeHigher = 0;// 缺考较多的年级
		int absenceNumberHigher = 0;// 缺考较多的学生数量
		String absenceNumberSubjece = "";// 某一个 年级缺考人次最多的课
		String absenceNumberSubjectAll = "";// 全校缺考人次最多的课
		List<GradeAbsenceDistribution> gadList = new ArrayList<>();
		String strGradeOne = year.substring(0, 4);
		Integer gradeOne = Integer.parseInt(strGradeOne);
		Integer rcAbsenceNumber = 0;
		Integer pecAbsenceNumber = 0;
		Integer gecAbsenceNumber = 0;
		Integer id = 1;
		for (Integer gradeI = gradeOne - 3; gradeI <= gradeOne; gradeI++) {
			GradeAbsenceDistribution gradeAbsenceDistribution = new GradeAbsenceDistribution();
			gradeAbsenceDistribution = getGradeAbsenceDistributionByGrade(gradeI, year, term);
			gradeAbsenceDistribution.setId(id++);
			gadList.add(gradeAbsenceDistribution);
			rcAbsenceNumber += gradeAbsenceDistribution.getRcAbsenceNumber();
			pecAbsenceNumber += gradeAbsenceDistribution.getPecAbsenceNumber();
			gecAbsenceNumber += gradeAbsenceDistribution.getGecAbsenceNumber();
			int rcAbsenceNumbertemp = gradeAbsenceDistribution.getRcAbsenceNumber();// 必修课缺考人次
			int pecAbsenceNumbertemp = gradeAbsenceDistribution.getPecAbsenceNumber();// 专业选修课缺考人次
			int gecAbsenceNumbertemp = gradeAbsenceDistribution.getGecAbsenceNumber();// 通识选修课缺考人次
			if (absenceNumberHighest < gradeAbsenceDistribution.getTotalAbsenceNumber()) {
				if (absenceNumberHighest != 0) {
					absenceNumberHigher = absenceNumberHighest;
					absenceGradeHigher = absenceGradeHighest;
				}
				absenceNumberHighest = gradeAbsenceDistribution.getTotalAbsenceNumber();// 缺考人数
				absenceGradeHighest = Integer.parseInt(gradeAbsenceDistribution.getGrade());// 缺考年级
				int temp = (rcAbsenceNumbertemp > pecAbsenceNumbertemp) ? rcAbsenceNumbertemp : pecAbsenceNumbertemp;
				int result = (temp > gecAbsenceNumbertemp) ? temp : gecAbsenceNumbertemp;
				if (result == rcAbsenceNumbertemp) {
					absenceSubjectHighest = "必修课";
				} else if (result == pecAbsenceNumbertemp) {
					absenceSubjectHighest = "专业选修课";
				} else {
					absenceSubjectHighest = "通识教育选修课";
				}
			}
			if (absenceNumberHighest > gradeAbsenceDistribution.getTotalAbsenceNumber()
					&& absenceNumberHigher < gradeAbsenceDistribution.getTotalAbsenceNumber()) {
				absenceNumberHigher = gradeAbsenceDistribution.getTotalAbsenceNumber();// 缺考人数
				absenceGradeHigher = Integer.parseInt(gradeAbsenceDistribution.getGrade());// 缺考年级
			}

		}

		Integer totalAbsenceNumber = rcAbsenceNumber + pecAbsenceNumber + gecAbsenceNumber;
		GradeAbsenceDistribution gradeAbsenceDistribution = new GradeAbsenceDistribution();
		gradeAbsenceDistribution.setId(id);
		gradeAbsenceDistribution.setGrade("全校");
		gradeAbsenceDistribution.setRcAbsenceNumber(rcAbsenceNumber);
		gradeAbsenceDistribution.setPecAbsenceNumber(pecAbsenceNumber);
		gradeAbsenceDistribution.setGecAbsenceNumber(gecAbsenceNumber);
		gradeAbsenceDistribution.setTotalAbsenceNumber(totalAbsenceNumber);
		int rcAbsenceNum = gradeAbsenceDistribution.getRcAbsenceNumber();// 必修课缺考人次
		int pecAbsenceNum = gradeAbsenceDistribution.getPecAbsenceNumber();// 专业选修课缺考人次
		int gecAbsenceNum = gradeAbsenceDistribution.getGecAbsenceNumber();// 通识选修课缺考人次
		int temp = (rcAbsenceNum > pecAbsenceNum) ? rcAbsenceNum : pecAbsenceNum;
		int result = (temp > gecAbsenceNum) ? temp : gecAbsenceNum;
		if (result == rcAbsenceNum) {
			absenceNumberSubjectAll = "必修课";
		} else if (result == pecAbsenceNum) {
			absenceNumberSubjectAll = "专业选修课";
		} else {
			absenceNumberSubjectAll = "通识教育选修课";
		}
		gradeAbsenceDistribution.setAnalysis(
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;表3.4、图3.5统计数据表明：\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（1）"
						+ absenceGradeHighest + "级缺考人次最多，为" + absenceNumberHighest + "人次，主要为" + absenceSubjectHighest
						+ "，" + absenceGradeHigher + "级紧随其后，为" + absenceNumberHigher + "人次；\n"
						+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（2）在必修课、专业选修课、通识教育选修课中，缺考人次最多的为"
						+ absenceNumberSubjectAll + "。"
						+ "##&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;根据学籍管理规定，学生缺考成绩以不及格记录。本次统计缺考数据共"
						+ gradeAbsenceDistribution.getTotalAbsenceNumber() + "条。统计情况如下：");
		gadList.add(gradeAbsenceDistribution);
		return gadList;
	}

	/*
	 * ======大一、大二、大三具有代表性的14门主要基础课程成绩基本情况======
	 */

	/**
	 * 通过 courseName 获得该课程的年级编号
	 * 
	 * @param courseName
	 * @param year
	 * @param term
	 * @return
	 */
	public String getGradeByCourseName(String courseName, String year, Integer term) {
		List<String> classNumberList = studentCourseMapper.getCourseClassNumberListBycourseName(courseName, year, term);
		List<String> gradeList = new ArrayList<>();
		for (String classNumber : classNumberList) {
			gradeList.add(classNumber.substring(0, 4));
		}
		Set<String> gradeSet = new HashSet<>(gradeList);
		Iterator<String> gradeIt = gradeSet.iterator();
		// 取具有修该课程最多班级个数所在的年级
		String resultGrade = "--";
		if (gradeIt.hasNext()) {
			resultGrade = gradeIt.next();
			while (gradeIt.hasNext()) {
				String tmpGrade = gradeIt.next();
				if (Collections.frequency(gradeList, resultGrade) < Collections.frequency(gradeList, tmpGrade)) {
					resultGrade = tmpGrade;
				}
			}
		}
		return resultGrade;
	}

	/**
	 * 通过 courseName 获得该课程的总分（非加权）
	 * 
	 * @param courseName
	 * @param year
	 * @param term
	 * @return
	 */
	public double getCourseTotalScoreByCourseName(String courseName, String year, Integer term) {
		Integer totalNumber = studentCourseMapper.getCourseTotalStudentNumberByCourseName(courseName, year, term);
		if (totalNumber != 0) {
			return studentCourseMapper.getCourseTotalScoreByCourseName(courseName, year, term);
		} else {
			return 0; // 学生数为0，加权总分也为0
		}
	}

	/**
	 * 通过 courseName 获得该课程的平均分
	 * 
	 * @param courseName
	 * @param year
	 * @param term
	 * @return
	 */
	public double getCourseAverageScoreByCourseName(String courseName, String year, Integer term) {
		Integer totalNumber = studentCourseMapper.getCourseTotalStudentNumberByCourseName(courseName, year, term);
		double averageScore = 0;
		if (totalNumber != 0) {
			double totalScore = studentCourseMapper.getCourseTotalScoreByCourseName(courseName, year, term);
			averageScore = totalScore / totalNumber;
		} else {
			averageScore = -1; // 除数异常
		}
		return averageScore;
	}

	/**
	 * 通过 courseName 获得该课程的成绩基本情况
	 * 
	 * @param courseName
	 * @param year
	 * @param term
	 * @return
	 */
	public BasicCourseOverallDistribution getBasicCourseOverallDistributionByCourseName(String courseName, String year,
			Integer term) {
		String grade = getGradeByCourseName(courseName, year, term);
		BasicCourseOverallDistribution basicCourseOverallDistribution = new BasicCourseOverallDistribution();
		basicCourseOverallDistribution.setGrade(grade);
		basicCourseOverallDistribution.setCourseName(courseName);
		Integer totalNumber = studentCourseMapper.getCourseTotalStudentNumberByCourseName(courseName, year, term);
		if (totalNumber != 0) {
			Integer excellentNumber = studentCourseMapper.getCourseExcellentNumberByCourseName(courseName, year, term);
			Integer failNumber = studentCourseMapper.getCourseFailNumberByCourseName(courseName, year, term);
			double excellentRate = (double) excellentNumber / totalNumber;
			double failRate = (double) failNumber / totalNumber;
			double averageScore = getCourseAverageScoreByCourseName(courseName, year, term);

			DecimalFormat rateDF = new DecimalFormat("0.00%");
			DecimalFormat scoreDF = new DecimalFormat("0.00");

			String strExcellentRate = rateDF.format(excellentRate);
			String strFailRate = rateDF.format(failRate);
			String strAverageScore = scoreDF.format(averageScore);

			basicCourseOverallDistribution.setExcellentNumber(excellentNumber);
			basicCourseOverallDistribution.setFailNumber(failNumber);
			basicCourseOverallDistribution.setTotalNumber(totalNumber);
			basicCourseOverallDistribution.setExcellentRate(strExcellentRate);
			basicCourseOverallDistribution.setFailRate(strFailRate);
			basicCourseOverallDistribution.setAverageScore(strAverageScore);
		}
		return basicCourseOverallDistribution;
	}

	/**
	 * 获得要分析总体情况的主要课程
	 * 
	 * @return
	 */
	public List<String> getBasicCourseOverallListByTerm(Integer term) {
		List<String> bcoList = new ArrayList<>();
		if (term == 1) {
			bcoList.add("自动控制原理");
			bcoList.add("电磁场");
			bcoList.add("机械设计");
			bcoList.add("数字电子技术");
			bcoList.add("大学英语（三）");
			bcoList.add("大学物理下A");
			bcoList.add("大学物理下B");
			bcoList.add("大学物理实验（二）");
			bcoList.add("复变函数与积分变换");
			bcoList.add("大学英语（一）");
			bcoList.add("大学计算机基础A");
			bcoList.add("普通化学");
			bcoList.add("线性代数与解析几何A");
			bcoList.add("微积分A（一）");
		} else if (term == 2) {
			// bcoList中加入下学期的课程
			bcoList.add("大学英语（六）");
			bcoList.add("电子电路综合实验");
			bcoList.add("管理学B");
			bcoList.add("工程实践A");
			bcoList.add("机械设计基础B");
			bcoList.add("大学英语（四）");
			bcoList.add("模拟电子技术");
			bcoList.add("材料力学B");
			bcoList.add("大学英语（二）");
			bcoList.add("程序设计基础");
			bcoList.add("大学物理上");
			bcoList.add("概率论与数理统计");
			bcoList.add("微积分A（二）");
			bcoList.add("工程图学基础");
		}
		return bcoList;
	}

	/**
	 * 获得主要基础课程成绩基本情况
	 * 
	 * @param year
	 * @param term
	 * @return
	 */
	public List<BasicCourseOverallDistribution> getBasicCourseOverallDistributionList(String year, Integer term) {
		// 所有优秀率
		ArrayList<Double> allExcellentRates = new ArrayList<>();
		// 所有课程
		ArrayList<String> courseNames = new ArrayList<>();
		// 所有不及格率
		ArrayList<Double> allFailRates = new ArrayList<>();
		// 最高优秀率
		double excellentRateHighest = 0.00;
		// 最高优秀率的课程
		String excellentRateHighestCourse = "";
		// 最低优秀率
		double excellentRateLowest = 100.00;
		// 最低优秀率的课程
		String excellentRateLowestCourse = "";
		// 较低优秀率
		double excellentRateLower = 100.00;
		// 最低优秀率的课程
		String excellentRateLowerCourse = "";
		// 最高不及格率
		double failRateHighest = 0.00;
		// 最高不及格率的课程
		String failRateHighestCourse = "";
		// 不及格率在5%以下的课程
		ArrayList<String> downFiveCourses = new ArrayList<>();
		// 不及格率高于优秀率的课程
		ArrayList<String> failOverExcellentCourses = new ArrayList<>();
		// 所有年级
		ArrayList<String> grades = new ArrayList<>();
		ArrayList<BasicCourseOverallDistribution> basicCourseOverallDistributions = new ArrayList<>();
		List<BasicCourseOverallDistribution> bcodList = new ArrayList<>();
		List<String> bcoList = getBasicCourseOverallListByTerm(term);
		String strGradeOneTemp = year.substring(0, 4);
		Integer gradeOneTemp = Integer.parseInt(strGradeOneTemp);
		Integer gradeOn = gradeOneTemp - 3;
		Integer gradeTh = gradeOn + 2;// 大二
		Integer gradeFo = gradeOn + 3;// 大一
		grades.add(gradeTh.toString());
		grades.add(gradeFo.toString());
		Integer id = 1;
		for (String courseName : bcoList) {
			BasicCourseOverallDistribution basicCourseOverallDistribution = new BasicCourseOverallDistribution();
			basicCourseOverallDistribution = getBasicCourseOverallDistributionByCourseName(courseName, year, term);
			if (basicCourseOverallDistribution.getTotalNumber() >= 10) {
				basicCourseOverallDistributions.add(basicCourseOverallDistribution);
				allExcellentRates.add(Double.parseDouble(basicCourseOverallDistribution.getExcellentRate().substring(0,
						basicCourseOverallDistribution.getExcellentRate().length() - 1)));
				courseNames.add(basicCourseOverallDistribution.getCourseName());
				allFailRates.add(Double.parseDouble(basicCourseOverallDistribution.getFailRate().substring(0,
						basicCourseOverallDistribution.getFailRate().length() - 1)));
			}
		}
		for (int i = 0; i < allExcellentRates.size(); i++) {
			if (excellentRateHighest < allExcellentRates.get(i)) {
				excellentRateHighest = allExcellentRates.get(i);
				excellentRateHighestCourse = courseNames.get(i);
			}

			if (excellentRateLowest > allExcellentRates.get(i)) {
				if (excellentRateLowest != 100.00) {
					excellentRateLower = excellentRateLowest;
					excellentRateLowerCourse = excellentRateHighestCourse;
				}
				excellentRateLowest = allExcellentRates.get(i);
				excellentRateHighestCourse = courseNames.get(i);
			}
			if (excellentRateLowest < allExcellentRates.get(i) && excellentRateLower > allExcellentRates.get(i)) {
				excellentRateLower = allExcellentRates.get(i);
				excellentRateLowerCourse = courseNames.get(i);
			}
		}
		for (int i = 0; i < allFailRates.size(); i++) {
			if (failRateHighest < allFailRates.get(i)) {
				failRateHighest = allFailRates.get(i);
				failRateHighestCourse = courseNames.get(i);
			}
			if (allFailRates.get(i) <= 5) {
				downFiveCourses.add(courseNames.get(i));
			}
			if (allFailRates.get(i) > allExcellentRates.get(i)) {
				failOverExcellentCourses.add(courseNames.get(i));
			}
		}
		String downFiveCoursesStr = "无课程 ";
		if (downFiveCourses.size() != 0) {
			downFiveCoursesStr = "";
			for (int i = 0; i < downFiveCourses.size(); i++) {
				downFiveCoursesStr += downFiveCourses.get(i) + "、";
			}
		}

		String failOverExcellentCoursesStr = "无课程 ";
		if (failOverExcellentCourses.size() != 0) {
			failOverExcellentCoursesStr = "";
			for (int i = 0; i < failOverExcellentCourses.size(); i++) {
				failOverExcellentCoursesStr += failOverExcellentCourses.get(i) + "、";
			}
		}

		String gradesStr = "";
		if (grades.size() != 0) {
			for (int i = 0; i < grades.size(); i++) {
				gradesStr += grades.get(i) + "级、";
			}
		}

		String courseTerm = "";
		if (term == 1) {
			courseTerm = "#&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;主要统计" + grades.get(0)
					+ "大学英语（三）、大学物理下A、大学物理下B、大学物理实验（二）、复变函数与积分变换5门基本课程的优秀率、不及格率。#&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;主要统计"
					+ grades.get(1) + "大学英语（一）、大学计算机基础A、普通化学、线性代数与解析几何A、微积分A（一）5门基本课程的优秀率、不及格率。";
		} else if (term == 2) {
			courseTerm = "#&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;主要统计" + grades.get(0)
					+ "工程实践A、机械设计基础B、大学英语（四）3门基本课程的优秀率、不及格率。#&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;主要统计"
					+ grades.get(1) + "大学英语（二）、程序设计基础、大学物理上、概率论与数理统计、微积分A（二）、工程图学基础6门基本课程的优秀率、不及格率。";
		}
		for (BasicCourseOverallDistribution basicCourseOverallDistribution : basicCourseOverallDistributions) {
			basicCourseOverallDistribution.setId(id++);
			basicCourseOverallDistribution.setAnalysis(
					"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;表4.1、图4.1统计数据显示：\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（1）在优秀率方面，最高的课程为"
							+ excellentRateHighestCourse + "（" + excellentRateHighest + "%），最低的课程为"
							+ excellentRateLowestCourse + "（" + excellentRateLowest + "%），其次为"
							+ excellentRateLowerCourse + "（" + excellentRateLower + "%）；\n"
							+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（2）在不及格率方面，最高的课程为"
							+ failRateHighestCourse + "（" + failRateHighest + "%），不及格率在5%以下的课程有："
							+ downFiveCoursesStr.substring(0, downFiveCoursesStr.length() - 1) + "；\n"
							+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（3）不及格率高于优秀率的课程有："
							+ failOverExcellentCoursesStr.substring(0, failOverExcellentCoursesStr.length() - 1) + "。"
							+ "#&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;基础课程面向全校开课，学生成绩的对比分析有助于进一步分析各院系间学生学习情况，为课程建设、教学改革、教师授课、学生管理提供一定的指导建议。#&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本章统计了"
							+ gradesStr.substring(0, gradesStr.length() - 1) + "较有代表性的" + courseNames.size()
							+ "门基础课程成绩情况，对其优秀率、不及格率、平均分情况进行统计分析，详见表4.1。#对表4.1中" + grades.get(0) + "级、" + grades.get(1)
							+ "级主要基础课程的优秀率、不及格率进行学院间的对比分析。" + courseTerm);
			bcodList.add(basicCourseOverallDistribution);
		}
		return bcodList;
	}
	// public List<BasicCourseOverallDistribution>
	// getBasicCourseOverallDistributionList(String year, Integer term) {
	// List<BasicCourseOverallDistribution> bcodList = new ArrayList<>();
	// List<String> bcoList = getBasicCourseOverallListByTerm(term);
	// Integer id = 1;
	// for (String courseName : bcoList) {
	// BasicCourseOverallDistribution basicCourseOverallDistribution = new
	// BasicCourseOverallDistribution();
	// basicCourseOverallDistribution =
	// getBasicCourseOverallDistributionByCourseName(courseName, year, term);
	// basicCourseOverallDistribution.setId(id++);
	// bcodList.add(basicCourseOverallDistribution);
	// }
	// return bcodList;
	// }

	/*
	 * ======大一、大二具有代表性的10门主要基础课程成绩具体分析======
	 */

	/**
	 * 通过 courseName和departmentId 获得该课程该学院的成绩具体分析
	 * 
	 * @param courseName
	 * @param departmentId
	 * @param year
	 * @param term
	 * @return
	 */
	public BasicCourseDetailDistribution getBasicCourseDetailDistributionByCourseNameAndDepartmentId(String courseName,
			Integer departmentId, String year, Integer term) {
		String departmentName = getDepartmentNameByDepartmentId(departmentId);
		BasicCourseDetailDistribution basicCourseDetailDistribution = new BasicCourseDetailDistribution();
		basicCourseDetailDistribution.setCourseName(courseName);
		basicCourseDetailDistribution.setDepartmentName(departmentName);
		Integer totalStudentNumber = 0;
		Integer excellentNumber = 0;
		Integer failNumber = 0;
		if (departmentId != 6) {
			totalStudentNumber = studentCourseMapper.getCourseTotalStudentNumberByCourseNameAndDepartmentId(courseName,
					departmentId, year, term);
			excellentNumber = studentCourseMapper.getCourseExcellentNumberByCourseNameAndDepartmentId(courseName,
					departmentId, year, term);
			failNumber = studentCourseMapper.getCourseFailNumberByCourseNameAndDepartmentId(courseName, departmentId,
					year, term);
		} else {
			List<Integer> departmentIdList = Arrays.asList(6, 20, 21);
			for (Integer id : departmentIdList) {
				totalStudentNumber += studentCourseMapper
						.getCourseTotalStudentNumberByCourseNameAndDepartmentId(courseName, id, year, term);
				excellentNumber += studentCourseMapper.getCourseExcellentNumberByCourseNameAndDepartmentId(courseName,
						id, year, term);
				failNumber += studentCourseMapper.getCourseFailNumberByCourseNameAndDepartmentId(courseName, id, year,
						term);
			}
		} // if
		if (totalStudentNumber != 0) {
			double excellentRate = (double) excellentNumber / totalStudentNumber;
			double failRate = (double) failNumber / totalStudentNumber;

			DecimalFormat rateDF = new DecimalFormat("0.00%");

			String strExcellentRate = rateDF.format(excellentRate);
			String strFailRate = rateDF.format(failRate);

			basicCourseDetailDistribution.setExcellentNumber(excellentNumber);
			basicCourseDetailDistribution.setFailNumber(failNumber);
			basicCourseDetailDistribution.setTotalStudentNumber(totalStudentNumber);
			basicCourseDetailDistribution.setExcellentRate(strExcellentRate);
			basicCourseDetailDistribution.setFailRate(strFailRate);
		}
		return basicCourseDetailDistribution;
	}

	/**
	 * 通过 courseName 获得该课程的成绩具体分析
	 * 
	 * @param courseName
	 * @param year
	 * @param term
	 * @return
	 */
	public List<BasicCourseDetailDistribution> getBasicCourseDetailDistributionListByCourseName(String courseName,
			String year, Integer term) {
		// 优秀率高于年级平均水平的院系
		ArrayList<String> overAllExcellentDepartments = new ArrayList<>();
		// 优秀率最高的院系
		String excellentRateHighestDepartment = "";
		// 最高的优秀率
		double excellentRateHighest = 0.00;
		// 不及格率高于年级平均水平的院系
		ArrayList<String> overAllFailDepartments = new ArrayList<>();
		// 不及格率最高的院系
		String failRateHighestDepartment = "";
		// 最高的不及格率
		double failRateHighest = 0.00;
		// 去掉不上该课的院系集合
		ArrayList<BasicCourseDetailDistribution> basicCourseDetailDistributions = new ArrayList<>();
		// 去掉不上该课院系的所有优秀率
		ArrayList<Double> basicCourseDetailDistributionExcellentRates = new ArrayList<>();
		// 去掉不上该课院系的所有不及格率
		ArrayList<Double> basicCourseDetailDistributionFailRates = new ArrayList<>();
		for (Integer departmentId = 1; departmentId <= 19; departmentId++) {
			BasicCourseDetailDistribution basicCourseDetailDistribution = new BasicCourseDetailDistribution();
			basicCourseDetailDistribution = getBasicCourseDetailDistributionByCourseNameAndDepartmentId(courseName,
					departmentId, year, term);
			// if(basicCourseDetailDistribution.getTotalStudentNumber() != 0) { //
			// 成绩记录数小于等于10的院系过滤
			if (basicCourseDetailDistribution.getTotalStudentNumber() >= 10) {
				basicCourseDetailDistributionFailRates.add(Double.parseDouble(basicCourseDetailDistribution
						.getFailRate().substring(0, basicCourseDetailDistribution.getFailRate().length() - 1)));
				basicCourseDetailDistributionExcellentRates
						.add(Double.parseDouble(basicCourseDetailDistribution.getExcellentRate().substring(0,
								basicCourseDetailDistribution.getExcellentRate().length() - 1)));
				basicCourseDetailDistributions.add(basicCourseDetailDistribution);
				// System.out.println("44444"+basicCourseDetailDistribution);
			}
		}
		// System.out.println("8888"+basicCourseDetailDistributionExcellentRates.toString());
		List<BasicCourseDetailDistribution> bcddList = new ArrayList<>();
		Integer id = 1;
		Integer totalStudentNumber = 0;
		Integer excellentNumber = 0;
		Integer failNumber = 0;
		for (int i = 0; i < basicCourseDetailDistributions.size(); i++) {
			totalStudentNumber += basicCourseDetailDistributions.get(i).getTotalStudentNumber();
			excellentNumber += basicCourseDetailDistributions.get(i).getExcellentNumber();
			failNumber += basicCourseDetailDistributions.get(i).getFailNumber();
			basicCourseDetailDistributions.get(i).setId(id++);
			bcddList.add(basicCourseDetailDistributions.get(i));
		}
		// 没有考虑自动生成分析报告的情况下的处理方法
		// for (Integer departmentId = 1; departmentId <= 19; departmentId++) {
		// BasicCourseDetailDistribution basicCourseDetailDistribution = new
		// BasicCourseDetailDistribution();
		// basicCourseDetailDistribution =
		// getBasicCourseDetailDistributionByCourseNameAndDepartmentId(courseName,
		// departmentId, year, term);
		// //System.out.println("5555"+basicCourseDetailDistribution);
		// totalStudentNumber += basicCourseDetailDistribution.getTotalStudentNumber();
		// excellentNumber += basicCourseDetailDistribution.getExcellentNumber();
		// failNumber += basicCourseDetailDistribution.getFailNumber();
		// basicCourseDetailDistribution.setId(id++);
		// bcddList.add(basicCourseDetailDistribution);
		// }
		String grade = getGradeByCourseName(courseName, year, term);
		String departmentName = grade + "级";
		BasicCourseDetailDistribution basicCourseDetailDistribution = new BasicCourseDetailDistribution();
		basicCourseDetailDistribution.setCourseName(courseName);
		basicCourseDetailDistribution.setDepartmentName(departmentName);
		basicCourseDetailDistribution.setId(id++);
		if (totalStudentNumber != 0) {
			double excellentRate = (double) excellentNumber / totalStudentNumber;
			double failRate = (double) failNumber / totalStudentNumber;
			DecimalFormat rateDF = new DecimalFormat("0.00%");
			String strExcellentRate = rateDF.format(excellentRate);
			String strFailRate = rateDF.format(failRate);
			basicCourseDetailDistribution.setExcellentNumber(excellentNumber);
			basicCourseDetailDistribution.setFailNumber(failNumber);
			basicCourseDetailDistribution.setTotalStudentNumber(totalStudentNumber);
			basicCourseDetailDistribution.setExcellentRate(strExcellentRate);
			basicCourseDetailDistribution.setFailRate(strFailRate);
			for (int i = 0; i < basicCourseDetailDistributionExcellentRates.size(); i++) {
				if (excellentRateHighest < basicCourseDetailDistributionExcellentRates.get(i)) {
					excellentRateHighest = basicCourseDetailDistributionExcellentRates.get(i);
					excellentRateHighestDepartment = basicCourseDetailDistributions.get(i).getDepartmentName();
				}
				if (excellentRate * 100 < basicCourseDetailDistributionExcellentRates.get(i)) {
					overAllExcellentDepartments.add(basicCourseDetailDistributions.get(i).getDepartmentName());
					// System.out.println("99999999"+excellentRate);
				}
			}
			for (int i = 0; i < basicCourseDetailDistributionFailRates.size(); i++) {
				if (failRateHighest < basicCourseDetailDistributionFailRates.get(i)) {
					failRateHighest = basicCourseDetailDistributionFailRates.get(i);
					failRateHighestDepartment = basicCourseDetailDistributions.get(i).getDepartmentName();
				}
				if (failRate * 100 < basicCourseDetailDistributionFailRates.get(i)) {
					overAllFailDepartments.add(basicCourseDetailDistributions.get(i).getDepartmentName());
				}
			}
			String overAllExcellentDepartmentsStr = "无院系 ";
			String overAllFailDepartmentsStr = "无院系 ";
			if (overAllExcellentDepartments.size() > 0) {
				overAllExcellentDepartmentsStr = "";
				for (int i = 0; i < overAllExcellentDepartments.size(); i++) {
					overAllExcellentDepartmentsStr += overAllExcellentDepartments.get(i) + "、";
				}
			}
			if (overAllFailDepartments.size() > 0) {
				overAllFailDepartmentsStr = "";
				for (int i = 0; i < overAllFailDepartments.size(); i++) {
					overAllFailDepartmentsStr += overAllFailDepartments.get(i) + "、";
				}
			}
			String highestExcellentDepartmentAndRate = "";
			if (!overAllExcellentDepartmentsStr.substring(0, overAllExcellentDepartmentsStr.length() - 1)
					.equals("无院系")) {
				highestExcellentDepartmentAndRate = "，其中" + excellentRateHighestDepartment + "最高，为"
						+ excellentRateHighest + "%";
			}
			String highestFailDepartmentAndRate = "";
			if (!overAllFailDepartmentsStr.substring(0, overAllFailDepartmentsStr.length() - 1).equals("无院系")) {
				highestFailDepartmentAndRate = "。其中" + failRateHighestDepartment + "最高，为" + failRateHighest + "%";
			}
			// System.out.println("全年级："+basicCourseDetailDistribution);
			basicCourseDetailDistribution.setAnalysis(
					"统计结果显示：\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（1）" + departmentName + courseName
							+ "课程成绩优秀率为" + basicCourseDetailDistribution.getExcellentRate() + "，优秀率高于年级平均水平的院系有："
							+ overAllExcellentDepartmentsStr.substring(0, overAllExcellentDepartmentsStr.length() - 1)
							+ highestExcellentDepartmentAndRate + "；\n"
							+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（2）" + departmentName + courseName
							+ "课程成绩不及格率为" + basicCourseDetailDistribution.getFailRate() + "，不及格率高于年级平均水平的院系有："
							+ overAllFailDepartmentsStr.substring(0, overAllFailDepartmentsStr.length() - 1)
							+ highestFailDepartmentAndRate + "。" + "##&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;对"
							+ departmentName + "各院系" + courseName + "成绩情况统计如下表：");
		}
		bcddList.add(basicCourseDetailDistribution);
		return bcddList;
	}

	/**
	 * 获得要分析具体情况的主要课程
	 * 
	 * @return
	 */
	public List<String> getBasicCourseDetailListByTerm(Integer term) {
		List<String> bcdList = new ArrayList<>();
		if (term == 1) {
			bcdList.add("大学英语（三）");
			bcdList.add("大学物理下A");
			bcdList.add("大学物理下B");
			bcdList.add("大学物理实验（二）");
			bcdList.add("复变函数与积分变换");
			bcdList.add("大学英语（一）");
			bcdList.add("大学计算机基础A");
			bcdList.add("普通化学");
			bcdList.add("线性代数与解析几何A");
			bcdList.add("微积分A（一）");
		} else if (term == 2) {
			// bcdList中加入下学期的课程
			bcdList.add("工程实践A");
			bcdList.add("机械设计基础B");
			bcdList.add("大学英语（四）");
			bcdList.add("大学英语（二）");
			bcdList.add("程序设计基础");
			bcdList.add("大学物理上");
			bcdList.add("概率论与数理统计");
			bcdList.add("微积分A（二）");
			bcdList.add("工程图学基础");
		}
		return bcdList;
	}

	/**
	 * 获得主要基础课程成绩具体情况
	 * 
	 * @param year
	 * @param term
	 * @return
	 */
	public List<List<BasicCourseDetailDistribution>> getBasicCourseDetailDistributionListList(String year,
			Integer term) {
		List<String> bcdList = getBasicCourseDetailListByTerm(term);
		List<List<BasicCourseDetailDistribution>> bcddListList = new ArrayList<>();
		for (String courseName : bcdList) {
			List<BasicCourseDetailDistribution> bcddList = new ArrayList<>();
			bcddList = getBasicCourseDetailDistributionListByCourseName(courseName, year, term);
			bcddListList.add(bcddList);
		}

		return bcddListList;
	}

	/*
	 * ==========正态分布统计分析==========
	 */

	/**
	 * 通过 courseName 计算该课程的标准差
	 * 
	 * @param courseName
	 * @param year
	 * @param term
	 * @return
	 */
	public double getCourseStandardDeviationByCourseName(String courseName, String year, Integer term) {
		List<Double> scoreList = studentCourseMapper.getCourseScoreListByCourseName(courseName, year, term);
		double averageScore = getCourseAverageScoreByCourseName(courseName, year, term);
		double standartDeviation = 0;
		if (scoreList.size() > 0) {
			double sum = 0;
			for (double score : scoreList) {
				sum += Math.pow(score - averageScore, 2);
			}
			standartDeviation = Math.sqrt(sum / scoreList.size());
		}
		return standartDeviation;
	}

	/**
	 * 通过 averageScore 和 standardDeviation 获得该课程成绩正态分布的纵坐标
	 * 
	 * @param averageScore
	 * @param standardDeviation
	 * @return
	 */
	public List<Double> getNormalDistributionOrdinateByCourseAverageScoreAndStandardDeviation(double averageScore,
			double standardDeviation) {
		List<Double> OrdinateList = new ArrayList<>();
		double left = 1 / (Math.pow(2 * Math.PI, 0.5) * standardDeviation);
		// DecimalFormat ordinateDF = new DecimalFormat("0.00000000");
		for (double x = 0; x <= 100; x++) {
			double expPart = -(Math.pow(x - averageScore, 2) / (2 * Math.pow(standardDeviation, 2)));
			double right = Math.pow(Math.E, expPart);
			double ordinate = left * right;
			// String strOrdinate = ordinateDF.format(ordinate);
			OrdinateList.add(ordinate);
		}
		return OrdinateList;
	}

	/**
	 * 通过 courseName 获得该课程的正态分布分析
	 * 
	 * @param courseName
	 * @param year
	 * @param term
	 * @return
	 */
	public BasicCourseNormalDistribution getBasicCourseNormalDistributionByCourseName(String courseName, String year,
			Integer term) {
		Integer courseNumber = studentCourseMapper.getCourseNumberByCourseName(courseName);
		String courseDepartment = studentCourseMapper.getCourseDepartmentByCourseName(courseName);
		BasicCourseNormalDistribution basicCourseNormalDistribution = new BasicCourseNormalDistribution();
		basicCourseNormalDistribution.setCourseName(courseName);
		basicCourseNormalDistribution.setCourseNumber(courseNumber);
		basicCourseNormalDistribution.setCourseDepartment(courseDepartment);
		Integer totalStudentNumber = studentCourseMapper.getCourseTotalStudentNumberByCourseName(courseName, year,
				term);
		if (totalStudentNumber != 0) {
			Integer excAndMedNumber = studentCourseMapper.getCourseExcellentAndMediumNumberByCourseName(courseName,
					year, term);
			Integer failNumber = studentCourseMapper.getCourseFailNumberByCourseName(courseName, year, term);
			double excAndMedRate = (double) excAndMedNumber / totalStudentNumber;
			double failRate = (double) failNumber / totalStudentNumber;
			double averageScore = getCourseAverageScoreByCourseName(courseName, year, term);
			double standardDeviation = getCourseStandardDeviationByCourseName(courseName, year, term);
			List<Double> ordinateList = getNormalDistributionOrdinateByCourseAverageScoreAndStandardDeviation(
					averageScore, standardDeviation);
			Map<String, Integer> studentNumberMap = studentCourseMapper
					.getCourseStudentNumberMapByCourseName(courseName, year, term);
			System.out.println(studentNumberMap);
			// 将10个分数段的Map转换为List
			List<Integer> studentNumberList = new ArrayList<>();
			studentNumberList.add(((Number) studentNumberMap.get("segmentZero")).intValue());
			studentNumberList.add(((Number) studentNumberMap.get("segmentOne")).intValue());
			studentNumberList.add(((Number) studentNumberMap.get("segmentTwo")).intValue());
			studentNumberList.add(((Number) studentNumberMap.get("segmentThree")).intValue());
			studentNumberList.add(((Number) studentNumberMap.get("segmentFour")).intValue());
			studentNumberList.add(((Number) studentNumberMap.get("segmentFive")).intValue());
			studentNumberList.add(((Number) studentNumberMap.get("segmentSix")).intValue());
			studentNumberList.add(((Number) studentNumberMap.get("segmentSeven")).intValue());
			studentNumberList.add(((Number) studentNumberMap.get("segmentEight")).intValue());
			studentNumberList.add(((Number) studentNumberMap.get("segmentNine")).intValue());

			DecimalFormat scoreDF = new DecimalFormat("0.00");
			DecimalFormat rateDF = new DecimalFormat("0.00%");

			String strExcAndMedRate = rateDF.format(excAndMedRate);
			String strFailRate = rateDF.format(failRate);
			String strAverageScore = scoreDF.format(averageScore);
			String strStandardDeviation = scoreDF.format(standardDeviation);
			basicCourseNormalDistribution.setTotalStudentNumber(totalStudentNumber);
			basicCourseNormalDistribution.setExcAndMedNumber(excAndMedNumber);
			basicCourseNormalDistribution.setFailNumber(failNumber);
			basicCourseNormalDistribution.setExcAndMedRate(strExcAndMedRate);
			basicCourseNormalDistribution.setFailRate(strFailRate);
			basicCourseNormalDistribution.setAverageScore(strAverageScore);
			basicCourseNormalDistribution.setStandardDeviation(strStandardDeviation);
			basicCourseNormalDistribution.setOrdinateList(ordinateList);
			basicCourseNormalDistribution.setStudentNumberList(studentNumberList);
		}
		return basicCourseNormalDistribution;
	}

	/*
	 * =======大一，大二具有代表性的10门基础课程各班成绩情况========
	 */

	/**
	 * 通过 courseName和classNumber 获得该课程该班级的成绩情况
	 * 
	 * @param courseName
	 * @param classNumber
	 * @param year
	 * @param term
	 * @return
	 */
	public BasicCourseClassDistribution getBasicCourseClassDistributionByCourseNameAndClassNumber(String courseName,
			String classNumber, String year, Integer term) {
		BasicCourseClassDistribution basicCourseClassDistribution = new BasicCourseClassDistribution();
		basicCourseClassDistribution.setCourseName(courseName);
		basicCourseClassDistribution.setClassNumber(classNumber);
		Integer totalNumber = studentCourseMapper.getCourseTotalStudentNumberByCourseNameAndClassNumber(courseName,
				classNumber, year, term);
		if (totalNumber != 0) {
			Integer excellentNumber = studentCourseMapper.getCourseExcellentNumberByCourseNameAndClassNumber(courseName,
					classNumber, year, term);
			Integer failNumber = studentCourseMapper.getCourseFailNumberByCourseNameAndClassNumber(courseName,
					classNumber, year, term);
			double excellentRate = (double) excellentNumber / totalNumber;
			double failRate = (double) failNumber / totalNumber;

			DecimalFormat rateDF = new DecimalFormat("0.00%");

			String strExcellentRate = rateDF.format(excellentRate);
			String strFailRate = rateDF.format(failRate);
			basicCourseClassDistribution.setTotalNumber(totalNumber);
			basicCourseClassDistribution.setExcellentRate(strExcellentRate);
			basicCourseClassDistribution.setFailRate(strFailRate);
		}
		return basicCourseClassDistribution;
	}

	/**
	 * 通过 courseName 获得该课程所有班级的成绩情况列表
	 * 
	 * @param courseName
	 * @param year
	 * @param term
	 * @return
	 */
	public List<BasicCourseClassDistribution> getBasicCourseClassDistributionListByCourseName(String courseName,
			String year, Integer term) {
		List<BasicCourseClassDistribution> bccdList = new ArrayList<>();
		String strGrade = getGradeByCourseName(courseName, year, term);
		if (!strGrade.equals("--")) {
			Integer grade = Integer.parseInt(strGrade);
			List<String> classNumberList = getClassNumberListByGrade(grade);
			Integer id = 1;
			for (String classNumber : classNumberList) {
				BasicCourseClassDistribution basicCourseClassDistribution = new BasicCourseClassDistribution();
				basicCourseClassDistribution = getBasicCourseClassDistributionByCourseNameAndClassNumber(courseName,
						classNumber, year, term);
				if (basicCourseClassDistribution.getTotalNumber() >= 10) {
					basicCourseClassDistribution.setId(id++);
					bccdList.add(basicCourseClassDistribution);
				}
			}
		}
		return bccdList;
	}

	/**
	 * 获得大一，大二10门基础课程所有班级的成绩情况列表
	 * 
	 * @param year
	 * @param term
	 * @return
	 */
	public List<List<BasicCourseClassDistribution>> getBasicCourseClassDistributionListList(String year, Integer term) {
		List<List<BasicCourseClassDistribution>> bccdListList = new ArrayList<>();
		List<String> bcdList = getBasicCourseDetailListByTerm(term);
		for (String courseName : bcdList) {
			List<BasicCourseClassDistribution> bccdList = new ArrayList<>();
			bccdList = getBasicCourseClassDistributionListByCourseName(courseName, year, term);
			bccdListList.add(bccdList);
		}
		return bccdListList;
	}

	/*
	 * ========文件上传需要的方法==========
	 */

	/**
	 * 通过 filePath 判断该文件是否被导入数据库
	 * 
	 * @param filePath
	 * @return
	 */
	public boolean isFileInsertedByFilePath(String filePath) {
		String examTerm = new InsertIntoDB().getExamTermByFilePath(filePath);
		if (examTerm.equals("")) {
			System.out.println("文件" + filePath + "未找到！");
			return true;
		}

		Integer insertFlag = studentCourseMapper.isFileInsertedByExamTerm(examTerm);
		if (insertFlag == 1) {
			return true;
		} else {
			return false;
		}
	}

}
