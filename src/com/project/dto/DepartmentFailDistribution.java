package com.project.dto;

import java.io.Serializable;

public class DepartmentFailDistribution implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6064818749348718975L;
	
	private String departmentName;		// 院系名称
	private Integer oneFailNumber;		// 不及格门数为1的学生人数
	private Integer twoFailNumber;		// 不及格门数为2的学生人数
	private Integer threeFailNumber;	// 不及格门数为3的学生人数
	private Integer geFourFailNumber;	// 不及格门数>=4的学生人数，ge代表（great than or equal）
	private Integer totalFailNumber;	// 不及格人数合计
	private Integer totalStudentNumber;	// 院系总人数
	private String failRate;			// 学生不及格率
	
	public DepartmentFailDistribution() {
		super();
		this.oneFailNumber = 0;
		this.twoFailNumber = 0;
		this.threeFailNumber = 0;
		this.geFourFailNumber = 0;
		this.totalFailNumber = 0;
		this.totalStudentNumber = 0;
		this.failRate = "0%";
	}

	public DepartmentFailDistribution(String departmentName, Integer oneFailNumber, Integer twoFailNumber,
			Integer threeFailNumber, Integer geFourFailNumber, Integer totalFailNumber, Integer totalStudentNumber,
			String failRate) {
		super();
		this.departmentName = departmentName;
		this.oneFailNumber = oneFailNumber;
		this.twoFailNumber = twoFailNumber;
		this.threeFailNumber = threeFailNumber;
		this.geFourFailNumber = geFourFailNumber;
		this.totalFailNumber = totalFailNumber;
		this.totalStudentNumber = totalStudentNumber;
		this.failRate = failRate;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getOneFailNumber() {
		return oneFailNumber;
	}

	public void setOneFailNumber(Integer oneFailNumber) {
		this.oneFailNumber = oneFailNumber;
	}

	public Integer getTwoFailNumber() {
		return twoFailNumber;
	}

	public void setTwoFailNumber(Integer twoFailNumber) {
		this.twoFailNumber = twoFailNumber;
	}

	public Integer getThreeFailNumber() {
		return threeFailNumber;
	}

	public void setThreeFailNumber(Integer threeFailNumber) {
		this.threeFailNumber = threeFailNumber;
	}

	public Integer getGeFourFailNumber() {
		return geFourFailNumber;
	}

	public void setGeFourFailNumber(Integer geFourFailNumber) {
		this.geFourFailNumber = geFourFailNumber;
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
		return "DepartmentFailDistribution [departmentName=" + departmentName + ", oneFailNumber=" + oneFailNumber
				+ ", twoFailNumber=" + twoFailNumber + ", threeFailNumber=" + threeFailNumber + ", geFourFailNumber="
				+ geFourFailNumber + ", totalFailNumber=" + totalFailNumber + ", totalStudentNumber="
				+ totalStudentNumber + ", failRate=" + failRate + "]";
	}
}