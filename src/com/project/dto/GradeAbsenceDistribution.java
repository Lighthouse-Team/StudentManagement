package com.project.dto;

import java.io.Serializable;

public class GradeAbsenceDistribution implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5924185519894548252L;

	private Integer id; 				// 序号
	private String grade; 				// 年级
	private Integer rcAbsenceNumber; 	// 必修课缺考人次
	private Integer pecAbsenceNumber; 	// 专业选修课缺考人次
	private Integer gecAbsenceNumber; 	// 通识选修课缺考人次
	private Integer totalAbsenceNumber; // 缺考总人次
	private String analysis;            //分析

	public GradeAbsenceDistribution() {
		super();
		this.rcAbsenceNumber = 0;
		this.pecAbsenceNumber = 0;
		this.gecAbsenceNumber = 0;
		this.totalAbsenceNumber = 0;
		this.analysis = "";
	}

	public GradeAbsenceDistribution(Integer id, String grade, Integer rcAbsenceNumber, Integer pecAbsenceNumber,
			Integer gecAbsenceNumber, Integer totalAbsenceNumber, String analysis) {
		super();
		this.id = id;
		this.grade = grade;
		this.rcAbsenceNumber = rcAbsenceNumber;
		this.pecAbsenceNumber = pecAbsenceNumber;
		this.gecAbsenceNumber = gecAbsenceNumber;
		this.totalAbsenceNumber = totalAbsenceNumber;
		this.analysis = analysis;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Integer getRcAbsenceNumber() {
		return rcAbsenceNumber;
	}

	public void setRcAbsenceNumber(Integer rcAbsenceNumber) {
		this.rcAbsenceNumber = rcAbsenceNumber;
	}

	public Integer getPecAbsenceNumber() {
		return pecAbsenceNumber;
	}

	public void setPecAbsenceNumber(Integer pecAbsenceNumber) {
		this.pecAbsenceNumber = pecAbsenceNumber;
	}

	public Integer getGecAbsenceNumber() {
		return gecAbsenceNumber;
	}

	public void setGecAbsenceNumber(Integer gecAbsenceNumber) {
		this.gecAbsenceNumber = gecAbsenceNumber;
	}

	public Integer getTotalAbsenceNumber() {
		return totalAbsenceNumber;
	}

	public void setTotalAbsenceNumber(Integer totalAbsenceNumber) {
		this.totalAbsenceNumber = totalAbsenceNumber;
	}

	public String getAnalysis() {
		return analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	@Override
	public String toString() {
		return "GradeAbsenceDistribution [id=" + id + ", grade=" + grade + ", rcAbsenceNumber=" + rcAbsenceNumber
				+ ", pecAbsenceNumber=" + pecAbsenceNumber + ", gecAbsenceNumber=" + gecAbsenceNumber
				+ ", totalAbsenceNumber=" + totalAbsenceNumber + ", analysis=" + analysis + "]";
	}

	
}
