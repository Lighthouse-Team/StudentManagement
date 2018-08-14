package com.project.dto;

import java.io.Serializable;

public class BasicCourseOverallDistribution implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -843603307733414933L;

	private String grade;				// 年级
	private String courseName;			// 课程名称
	private Integer excellentNumber;	// 优秀学生数量
	private Integer failNumber;			// 不及格学生数量
	private Integer totalNumber;		// 学生总数
	private String excellentRate;		// 优秀率
	private String failRate;			// 不及格率
	private String averageScore;		// 平均分
	
	public BasicCourseOverallDistribution() {
		super();
		// TODO Auto-generated constructor stub
		this.excellentNumber = 0;
		this.failNumber = 0;
		this.totalNumber = 0;
		this.excellentRate = "0.00%";
		this.failRate = "0.00%";
		this.averageScore = "0.00";
	}

	public BasicCourseOverallDistribution(String grade, String courseName, Integer excellentNumber, Integer failNumber,
			Integer totalNumber, String excellentRate, String failRate, String averageScore) {
		super();
		this.grade = grade;
		this.courseName = courseName;
		this.excellentNumber = excellentNumber;
		this.failNumber = failNumber;
		this.totalNumber = totalNumber;
		this.excellentRate = excellentRate;
		this.failRate = failRate;
		this.averageScore = averageScore;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Integer getExcellentNumber() {
		return excellentNumber;
	}

	public void setExcellentNumber(Integer excellentNumber) {
		this.excellentNumber = excellentNumber;
	}

	public Integer getFailNumber() {
		return failNumber;
	}

	public void setFailNumber(Integer failNumber) {
		this.failNumber = failNumber;
	}

	public Integer getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}

	public String getExcellentRate() {
		return excellentRate;
	}

	public void setExcellentRate(String excellentRate) {
		this.excellentRate = excellentRate;
	}

	public String getFailRate() {
		return failRate;
	}

	public void setFailRate(String failRate) {
		this.failRate = failRate;
	}

	public String getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(String averageScore) {
		this.averageScore = averageScore;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "BasicCourseOverallDistribution [grade=" + grade + ", courseName=" + courseName + ", excellentNumber="
				+ excellentNumber + ", failNumber=" + failNumber + ", totalNumber=" + totalNumber + ", excellentRate="
				+ excellentRate + ", failRate=" + failRate + ", averageScore=" + averageScore + "]";
	}
}
