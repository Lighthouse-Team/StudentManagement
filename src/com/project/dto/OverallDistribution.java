package com.project.dto;

import java.io.Serializable;


public class OverallDistribution implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6531312224628652521L;
	
//	private Integer overallDistributionId;	// 总体分布Id
	private String grade;					// 年级
	private String courseType;				// 课程种类:必修课、专业选修课、通识选修课
	private Integer totalNumber;			// 成绩记录总数
	private Integer excellentNumber;		// 优秀成绩数
	private Integer goodNumber;				// 良好成绩数
	private Integer mediumNumber;			// 中等成绩数
	private Integer passNumber;				// 及格成绩数
	private Integer failNumber;				// 不及格成绩数
	private String averageScore;			// 平均分
	private String excellentRate;			// 优秀率
	private String goodRate;				// 良好率
	private String mediumRate;				// 中等率
	private String passRate;				// 及格率
	private String failRate;				// 不及格率
	
	public OverallDistribution() {
		super();
	}
	
	public void initValue() {
		this.totalNumber = 0;
		this.excellentNumber = 0;
		this.goodNumber = 0;
		this.mediumNumber = 0;
		this.passNumber = 0;
		this.failNumber = 0;
		this.averageScore = "0";
		this.excellentRate = "0%";
		this.goodRate = "0%";
		this.mediumRate = "0%";
		this.passRate = "0%";
		this.failRate = "0%";
	}

	public OverallDistribution(String grade, String courseType, Integer totalNumber, Integer excellentNumber,
			Integer goodNumber, Integer mediumNumber, Integer passNumber, Integer failNumber, String averageScore,
			String excellentRate, String goodRate, String mediumRate, String passRate, String failRate) {
		super();
		this.grade = grade;
		this.courseType = courseType;
		this.totalNumber = totalNumber;
		this.excellentNumber = excellentNumber;
		this.goodNumber = goodNumber;
		this.mediumNumber = mediumNumber;
		this.passNumber = passNumber;
		this.failNumber = failNumber;
		this.averageScore = averageScore;
		this.excellentRate = excellentRate;
		this.goodRate = goodRate;
		this.mediumRate = mediumRate;
		this.passRate = passRate;
		this.failRate = failRate;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public Integer getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}

	public Integer getExcellentNumber() {
		return excellentNumber;
	}

	public void setExcellentNumber(Integer excellentNumber) {
		this.excellentNumber = excellentNumber;
	}

	public Integer getGoodNumber() {
		return goodNumber;
	}

	public void setGoodNumber(Integer goodNumber) {
		this.goodNumber = goodNumber;
	}

	public Integer getMediumNumber() {
		return mediumNumber;
	}

	public void setMediumNumber(Integer mediumNumber) {
		this.mediumNumber = mediumNumber;
	}

	public Integer getPassNumber() {
		return passNumber;
	}

	public void setPassNumber(Integer passNumber) {
		this.passNumber = passNumber;
	}

	public Integer getFailNumber() {
		return failNumber;
	}

	public void setFailNumber(Integer failNumber) {
		this.failNumber = failNumber;
	}

	public String getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(String averageScore) {
		this.averageScore = averageScore;
	}

	public String getExcellentRate() {
		return excellentRate;
	}

	public void setExcellentRate(String excellentRate) {
		this.excellentRate = excellentRate;
	}

	public String getGoodRate() {
		return goodRate;
	}

	public void setGoodRate(String goodRate) {
		this.goodRate = goodRate;
	}

	public String getMediumRate() {
		return mediumRate;
	}

	public void setMediumRate(String mediumRate) {
		this.mediumRate = mediumRate;
	}

	public String getPassRate() {
		return passRate;
	}

	public void setPassRate(String passRate) {
		this.passRate = passRate;
	}

	public String getFailRate() {
		return failRate;
	}

	public void setFailRate(String failRate) {
		this.failRate = failRate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "OverallDistribution [grade=" + grade + ", courseType=" + courseType + ", totalNumber=" + totalNumber
				+ ", excellentNumber=" + excellentNumber + ", goodNumber=" + goodNumber + ", mediumNumber="
				+ mediumNumber + ", passNumber=" + passNumber + ", failNumber=" + failNumber + ", averageScore="
				+ averageScore + ", excellentRate=" + excellentRate + ", goodRate=" + goodRate + ", mediumRate="
				+ mediumRate + ", passRate=" + passRate + ", failRate=" + failRate + "]";
	}
}
