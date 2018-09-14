package com.project.dto;

import java.io.Serializable;

public class DepartmentDistribution implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2867490890837158905L;

	private Integer id; 				// 序号
	private String grade; 				// 年级
	private String departmentName; 		// 院系名称
	private Integer totalNumber; 		// 成绩记录总数
	private Integer excellentNumber; 	// 优秀记录数
	private Integer goodNumber; 		// 良好记录数
	private Integer mediumNumber; 		// 中等记录数
	private Integer passNumber; 		// 及格记录数
	private Integer failNumber; 		// 不及格记录数
	private String averageScore; 		// 平均分
	private String excellentRate; 		// 优秀率
	private String goodRate; 			// 良好率
	private String mediumRate; 			// 中等率
	private String passRate; 			// 及格率
	private String failRate; 			// 不及格率
	private String analysis;                // 分析

	public DepartmentDistribution() {
		super();
		this.totalNumber = 0;
		this.excellentNumber = 0;
		this.goodNumber = 0;
		this.mediumNumber = 0;
		this.passNumber = 0;
		this.failNumber = 0;
		this.averageScore = "0.00";
		this.excellentRate = "0.00%";
		this.goodRate = "0.00%";
		this.mediumRate = "0.00%";
		this.passRate = "0.00%";
		this.failRate = "0.00%";
		this.analysis = "";
	}

	public DepartmentDistribution(Integer id, String grade, String departmentName, Integer totalNumber,
			Integer excellentNumber, Integer goodNumber, Integer mediumNumber, Integer passNumber, Integer failNumber,
			String averageScore, String excellentRate, String goodRate, String mediumRate, String passRate,
			String failRate, String analysis) {
		super();
		this.id = id;
		this.grade = grade;
		this.departmentName = departmentName;
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

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
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

	public String getAnalysis() {
		return analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "DepartmentDistribution [id=" + id + ", grade=" + grade + ", departmentName=" + departmentName
				+ ", totalNumber=" + totalNumber + ", excellentNumber=" + excellentNumber + ", goodNumber=" + goodNumber
				+ ", mediumNumber=" + mediumNumber + ", passNumber=" + passNumber + ", failNumber=" + failNumber
				+ ", averageScore=" + averageScore + ", excellentRate=" + excellentRate + ", goodRate=" + goodRate
				+ ", mediumRate=" + mediumRate + ", passRate=" + passRate + ", failRate=" + failRate + ", analysis="
				+ analysis + "]";
	}
	
	

	

}
