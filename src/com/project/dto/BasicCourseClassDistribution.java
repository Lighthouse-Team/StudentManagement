package com.project.dto;

import java.io.Serializable;

public class BasicCourseClassDistribution implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -887900102106066590L;

	private String courseName;		// 课程名称
	private String classNumber;		// 班号
	private Integer totalNumber;	// 总人数
	private String excellentRate;	// 优秀率
	private String failRate;		// 不及格率
	
	public BasicCourseClassDistribution() {
		super();
		// TODO Auto-generated constructor stub
		this.totalNumber = 0;
		this.excellentRate = "0.00%";
		this.failRate = "0.00%";
	}

	public BasicCourseClassDistribution(String courseName, String classNumber, Integer totalNumber,
			String excellentRate, String failRate) {
		super();
		this.courseName = courseName;
		this.classNumber = classNumber;
		this.totalNumber = totalNumber;
		this.excellentRate = excellentRate;
		this.failRate = failRate;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getClassNumber() {
		return classNumber;
	}

	public void setClassNumber(String classNumber) {
		this.classNumber = classNumber;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "BasicCourseClassDistribution [courseName=" + courseName + ", classNumber=" + classNumber
				+ ", totalNumber=" + totalNumber + ", excellentRate=" + excellentRate + ", failRate=" + failRate + "]";
	}
}
