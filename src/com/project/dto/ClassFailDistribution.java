package com.project.dto;

import java.io.Serializable;

public class ClassFailDistribution implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3193638017424670917L;

	private Integer id; 				// 序号
	private String classNumber; 		// 班号
	private Integer totalFailNumber; 	// 不及格学生人数
	private Integer totalStudentNumber;	// 学生总数
	private String failRate; 			// 不及格率

	public ClassFailDistribution() {
		super();
		// TODO Auto-generated constructor stub
		this.totalFailNumber = 0;
		this.totalStudentNumber = 0;
		this.failRate = "0.00%";
	}

	public ClassFailDistribution(Integer id, String classNumber, Integer totalFailNumber, Integer totalStudentNumber,
			String failRate) {
		super();
		this.id = id;
		this.classNumber = classNumber;
		this.totalFailNumber = totalFailNumber;
		this.totalStudentNumber = totalStudentNumber;
		this.failRate = failRate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClassNumber() {
		return classNumber;
	}

	public void setClassNumber(String classNumber) {
		this.classNumber = classNumber;
	}

	public Integer getTotalFailNumber() {
		return totalFailNumber;
	}

	public void setTotalFailNumber(Integer totalFailNumber) {
		this.totalFailNumber = totalFailNumber;
	}

	public Integer getTotalStudentNumber() {
		return totalStudentNumber;
	}

	public void setTotalStudentNumber(Integer totalStudentNumber) {
		this.totalStudentNumber = totalStudentNumber;
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
		return "ClassFailDistribution [id=" + id + ", classNumber=" + classNumber + ", totalFailNumber="
				+ totalFailNumber + ", totalStudentNumber=" + totalStudentNumber + ", failRate=" + failRate + "]";
	}

}
