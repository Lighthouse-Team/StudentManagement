package com.project.dto;

import java.io.Serializable;

public class DepartmentAllGradeFailDistribution implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7747757817957235741L;

	private Integer id;							// 序号
	private String departmentName;				// 院系名称
	private Integer gradeFourStudentNumber;		// 该院系大四学生总数
	private Integer gradeFourFailNumber;		// 该院系大四不及格人数
	private String gradeFourFailRate;			// 该院系大四不及格率
	private Integer gradeThreeStudentNumber;	// 该院系大三学生总数
	private Integer gradeThreeFailNumber;		// 该院系大三不及格人数
	private String gradeThreeFailRate;			// 该院系大三不及格率
	private Integer gradeTwoStudentNumber;		// 该院系大二学生总数
	private Integer gradeTwoFailNumber;			// 该院系大二不及格人数
	private String gradeTwoFailRate;			// 该院系大二不及格率
	private Integer gradeOneStudentNumber;		// 该院系大一学生总数
	private Integer gradeOneFailNumber;			// 该院系大一不及格人数
	private String gradeOneFailRate;			// 该院系大一不及格率
	private String analysis;                    // 情况分析
	
	public DepartmentAllGradeFailDistribution() {
		super();
		this.gradeFourStudentNumber = 0;
		this.gradeFourFailNumber = 0;
		this.gradeFourFailRate = "0.00%";
		this.gradeThreeStudentNumber = 0;
		this.gradeThreeFailNumber = 0;
		this.gradeThreeFailRate = "0.00%";
		this.gradeTwoStudentNumber = 0;
		this.gradeTwoFailNumber = 0;
		this.gradeTwoFailRate = "0.00%";
		this.gradeOneStudentNumber = 0;
		this.gradeOneFailNumber = 0;
		this.gradeOneFailRate = "0.00%";
		this.analysis = "";
	}

	public DepartmentAllGradeFailDistribution(Integer id, String departmentName, Integer gradeFourStudentNumber,
			Integer gradeFourFailNumber, String gradeFourFailRate, Integer gradeThreeStudentNumber,
			Integer gradeThreeFailNumber, String gradeThreeFailRate, Integer gradeTwoStudentNumber,
			Integer gradeTwoFailNumber, String gradeTwoFailRate, Integer gradeOneStudentNumber,
			Integer gradeOneFailNumber, String gradeOneFailRate, String analysis) {
		super();
		this.id = id;
		this.departmentName = departmentName;
		this.gradeFourStudentNumber = gradeFourStudentNumber;
		this.gradeFourFailNumber = gradeFourFailNumber;
		this.gradeFourFailRate = gradeFourFailRate;
		this.gradeThreeStudentNumber = gradeThreeStudentNumber;
		this.gradeThreeFailNumber = gradeThreeFailNumber;
		this.gradeThreeFailRate = gradeThreeFailRate;
		this.gradeTwoStudentNumber = gradeTwoStudentNumber;
		this.gradeTwoFailNumber = gradeTwoFailNumber;
		this.gradeTwoFailRate = gradeTwoFailRate;
		this.gradeOneStudentNumber = gradeOneStudentNumber;
		this.gradeOneFailNumber = gradeOneFailNumber;
		this.gradeOneFailRate = gradeOneFailRate;
		this.analysis = analysis;
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

	public Integer getGradeFourStudentNumber() {
		return gradeFourStudentNumber;
	}

	public void setGradeFourStudentNumber(Integer gradeFourStudentNumber) {
		this.gradeFourStudentNumber = gradeFourStudentNumber;
	}

	public Integer getGradeFourFailNumber() {
		return gradeFourFailNumber;
	}

	public void setGradeFourFailNumber(Integer gradeFourFailNumber) {
		this.gradeFourFailNumber = gradeFourFailNumber;
	}

	public String getGradeFourFailRate() {
		return gradeFourFailRate;
	}

	public void setGradeFourFailRate(String gradeFourFailRate) {
		this.gradeFourFailRate = gradeFourFailRate;
	}

	public Integer getGradeThreeStudentNumber() {
		return gradeThreeStudentNumber;
	}

	public void setGradeThreeStudentNumber(Integer gradeThreeStudentNumber) {
		this.gradeThreeStudentNumber = gradeThreeStudentNumber;
	}

	public Integer getGradeThreeFailNumber() {
		return gradeThreeFailNumber;
	}

	public void setGradeThreeFailNumber(Integer gradeThreeFailNumber) {
		this.gradeThreeFailNumber = gradeThreeFailNumber;
	}

	public String getGradeThreeFailRate() {
		return gradeThreeFailRate;
	}

	public void setGradeThreeFailRate(String gradeThreeFailRate) {
		this.gradeThreeFailRate = gradeThreeFailRate;
	}

	public Integer getGradeTwoStudentNumber() {
		return gradeTwoStudentNumber;
	}

	public void setGradeTwoStudentNumber(Integer gradeTwoStudentNumber) {
		this.gradeTwoStudentNumber = gradeTwoStudentNumber;
	}

	public Integer getGradeTwoFailNumber() {
		return gradeTwoFailNumber;
	}

	public void setGradeTwoFailNumber(Integer gradeTwoFailNumber) {
		this.gradeTwoFailNumber = gradeTwoFailNumber;
	}

	public String getGradeTwoFailRate() {
		return gradeTwoFailRate;
	}

	public void setGradeTwoFailRate(String gradeTwoFailRate) {
		this.gradeTwoFailRate = gradeTwoFailRate;
	}

	public Integer getGradeOneStudentNumber() {
		return gradeOneStudentNumber;
	}

	public void setGradeOneStudentNumber(Integer gradeOneStudentNumber) {
		this.gradeOneStudentNumber = gradeOneStudentNumber;
	}

	public Integer getGradeOneFailNumber() {
		return gradeOneFailNumber;
	}

	public void setGradeOneFailNumber(Integer gradeOneFailNumber) {
		this.gradeOneFailNumber = gradeOneFailNumber;
	}

	public String getGradeOneFailRate() {
		return gradeOneFailRate;
	}

	public void setGradeOneFailRate(String gradeOneFailRate) {
		this.gradeOneFailRate = gradeOneFailRate;
	}

	public String getAnalysis() {
		return analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	@Override
	public String toString() {
		return "DepartmentAllGradeFailDistribution [id=" + id + ", departmentName=" + departmentName
				+ ", gradeFourStudentNumber=" + gradeFourStudentNumber + ", gradeFourFailNumber=" + gradeFourFailNumber
				+ ", gradeFourFailRate=" + gradeFourFailRate + ", gradeThreeStudentNumber=" + gradeThreeStudentNumber
				+ ", gradeThreeFailNumber=" + gradeThreeFailNumber + ", gradeThreeFailRate=" + gradeThreeFailRate
				+ ", gradeTwoStudentNumber=" + gradeTwoStudentNumber + ", gradeTwoFailNumber=" + gradeTwoFailNumber
				+ ", gradeTwoFailRate=" + gradeTwoFailRate + ", gradeOneStudentNumber=" + gradeOneStudentNumber
				+ ", gradeOneFailNumber=" + gradeOneFailNumber + ", gradeOneFailRate=" + gradeOneFailRate
				+ ", analysis=" + analysis + "]";
	}

	
	
}
