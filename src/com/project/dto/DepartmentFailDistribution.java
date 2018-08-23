package com.project.dto;

import java.io.Serializable;

public class DepartmentFailDistribution implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6064818749348718975L;

	private Integer id; 				// 序号
	private String departmentName; 		// 院系名称
	private Integer oneFailNumber; 		// 不及格门数为1的学生人数
	private Integer twoFailNumber; 		// 不及格门数为2的学生人数
	private Integer threeFailNumber; 	// 不及格门数为3的学生人数
	private Integer geFourFailNumber; 	// 不及格门数>=4的学生人数，ge代表（great than or equal）
	private String oneFailRate;			// 不及格门数为1的学生比例
	private String twoFailRate;			// 不及格门数为2的学生比例
	private String threeFailRate;		// 不及格门数为3的学生比例
	private String geFourFailRate;		// 不及格门数>=4的学生比例
	private Integer totalFailNumber; 	// 不及格人数合计
	private Integer totalStudentNumber; // 院系总人数
	private String totalFailRate;		// 学生不及格率

	public DepartmentFailDistribution() {
		super();
		this.oneFailNumber = 0;
		this.twoFailNumber = 0;
		this.threeFailNumber = 0;
		this.geFourFailNumber = 0;
		this.oneFailRate = "0.00%";
		this.twoFailRate = "0.00%";
		this.threeFailRate = "0.00%";
		this.geFourFailRate = "0.00%";
		this.totalFailNumber = 0;
		this.totalStudentNumber = 0;
		this.totalFailRate = "0.00%";
	}

	public DepartmentFailDistribution(Integer id, String departmentName, Integer oneFailNumber, Integer twoFailNumber,
			Integer threeFailNumber, Integer geFourFailNumber, String oneFailRate, String twoFailRate,
			String threeFailRate, String geFourFailRate, Integer totalFailNumber, Integer totalStudentNumber,
			String totalFailRate) {
		super();
		this.id = id;
		this.departmentName = departmentName;
		this.oneFailNumber = oneFailNumber;
		this.twoFailNumber = twoFailNumber;
		this.threeFailNumber = threeFailNumber;
		this.geFourFailNumber = geFourFailNumber;
		this.oneFailRate = oneFailRate;
		this.twoFailRate = twoFailRate;
		this.threeFailRate = threeFailRate;
		this.geFourFailRate = geFourFailRate;
		this.totalFailNumber = totalFailNumber;
		this.totalStudentNumber = totalStudentNumber;
		this.totalFailRate = totalFailRate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getOneFailRate() {
		return oneFailRate;
	}

	public void setOneFailRate(String oneFailRate) {
		this.oneFailRate = oneFailRate;
	}

	public String getTwoFailRate() {
		return twoFailRate;
	}

	public void setTwoFailRate(String twoFailRate) {
		this.twoFailRate = twoFailRate;
	}

	public String getThreeFailRate() {
		return threeFailRate;
	}

	public void setThreeFailRate(String threeFailRate) {
		this.threeFailRate = threeFailRate;
	}

	public String getGeFourFailRate() {
		return geFourFailRate;
	}

	public void setGeFourFailRate(String geFourFailRate) {
		this.geFourFailRate = geFourFailRate;
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

	public String getTotalFailRate() {
		return totalFailRate;
	}

	public void setTotalFailRate(String totalFailRate) {
		this.totalFailRate = totalFailRate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "DepartmentFailDistribution [id=" + id + ", departmentName=" + departmentName + ", oneFailNumber="
				+ oneFailNumber + ", twoFailNumber=" + twoFailNumber + ", threeFailNumber=" + threeFailNumber
				+ ", geFourFailNumber=" + geFourFailNumber + ", oneFailRate=" + oneFailRate + ", twoFailRate="
				+ twoFailRate + ", threeFailRate=" + threeFailRate + ", geFourFailRate=" + geFourFailRate
				+ ", totalFailNumber=" + totalFailNumber + ", totalStudentNumber=" + totalStudentNumber
				+ ", totalFailRate=" + totalFailRate + "]";
	}

}
