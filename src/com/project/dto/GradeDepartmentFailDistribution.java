package com.project.dto;

import java.io.Serializable;

public class GradeDepartmentFailDistribution implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6737905433215947383L;

	private String grade;				// 年级
	private String departmentName;		// 院系名称
	private Integer totalFailNumber;	// 不及格学生数
	private Integer totalStudentNumber;	// 学生总数
	private String failRate;			// 不及格率
	
	public GradeDepartmentFailDistribution() {
		super();
		this.totalFailNumber = 0;
		this.totalStudentNumber = 0;
		this.failRate = "0.00%";
	}

	public GradeDepartmentFailDistribution(String grade, String departmentName, Integer totalFailNumber,
			Integer totalStudentNumber, String failRate) {
		super();
		this.grade = grade;
		this.departmentName = departmentName;
		this.totalFailNumber = totalFailNumber;
		this.totalStudentNumber = totalStudentNumber;
		this.failRate = failRate;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
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
		return "GradeDepartmentFailDistribution [grade=" + grade + ", departmentName=" + departmentName
				+ ", totalFailNumber=" + totalFailNumber + ", totalStudentNumber=" + totalStudentNumber + ", failRate="
				+ failRate + "]";
	}

}
