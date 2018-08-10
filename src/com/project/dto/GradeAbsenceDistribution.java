package com.project.dto;

import java.io.Serializable;

public class GradeAbsenceDistribution implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5924185519894548252L;

	private String grade;				// 年级
	private Integer rcAbsenceNumber;	// 必修课缺考人次
	private Integer pecAbsenceNumber;	// 专业选修课缺考人次
	private Integer gecAbsenceNumber;	// 通识选修课缺考人次
	private Integer totalAbsenceNumber;	// 缺考总人次
	
	public GradeAbsenceDistribution() {
		super();
		// TODO Auto-generated constructor stub
		this.rcAbsenceNumber = 0;
		this.pecAbsenceNumber = 0;
		this.gecAbsenceNumber = 0;
		this.totalAbsenceNumber = 0;
	}

	public GradeAbsenceDistribution(String grade, Integer rcAbsenceNumber, Integer pecAbsenceNumber,
			Integer gecAbsenceNumber, Integer totalAbsenceNumber) {
		super();
		this.grade = grade;
		this.rcAbsenceNumber = rcAbsenceNumber;
		this.pecAbsenceNumber = pecAbsenceNumber;
		this.gecAbsenceNumber = gecAbsenceNumber;
		this.totalAbsenceNumber = totalAbsenceNumber;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "GradeAbsenceDistribution [grade=" + grade + ", rcAbsenceNumber=" + rcAbsenceNumber
				+ ", pecAbsenceNumber=" + pecAbsenceNumber + ", gecAbsenceNumber=" + gecAbsenceNumber
				+ ", totalAbsenceNumber=" + totalAbsenceNumber + "]";
	}
}
