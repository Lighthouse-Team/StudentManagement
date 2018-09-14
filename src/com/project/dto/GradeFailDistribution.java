package com.project.dto;

import java.io.Serializable;

public class GradeFailDistribution implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4781441940900414013L;

	private Integer id; 				// 序号
	private String grade;				// 年级
	private Integer oneFailNumber;		// 不及格门数为1的学生人数
	private Integer twoFailNumber;		// 不及格门数为2的学生人数
	private Integer threeFailNumber;	// 不及格门数为3的学生人数
	private Integer fourFailNumber;		// 不及格门数为4的学生人数
	private Integer fiveFailNumber;		// 不及格门数为5的学生人数
	private Integer sixFailNumber;		// 不及格门数为6的学生人数
	private Integer sevenFailNumber;	// 不及格门数为7的学生人数
	private Integer eightFailNumber;	// 不及格门数为8的学生人数
	private Integer totalFailNumber;	// 不及格人数合计
	private Integer totalStudentNumber;	// 年级总人数
	private String failRate;			// 学生不及格率
	private String analysis;            //分析
	
	public GradeFailDistribution() {
		super();
		this.oneFailNumber = 0;
		this.twoFailNumber = 0;
		this.threeFailNumber = 0;
		this.fourFailNumber = 0;
		this.fiveFailNumber = 0;
		this.sixFailNumber = 0;
		this.sevenFailNumber = 0;
		this.eightFailNumber = 0;
		this.totalFailNumber = 0;
		this.totalStudentNumber = 0;
		this.failRate = "0.00%";
		this.analysis = "";
	}

	public GradeFailDistribution(Integer id, String grade, Integer oneFailNumber, Integer twoFailNumber,
			Integer threeFailNumber, Integer fourFailNumber, Integer fiveFailNumber, Integer sixFailNumber,
			Integer sevenFailNumber, Integer eightFailNumber, Integer totalFailNumber, Integer totalStudentNumber,
			String failRate, String analysis) {
		super();
		this.id = id;
		this.grade = grade;
		this.oneFailNumber = oneFailNumber;
		this.twoFailNumber = twoFailNumber;
		this.threeFailNumber = threeFailNumber;
		this.fourFailNumber = fourFailNumber;
		this.fiveFailNumber = fiveFailNumber;
		this.sixFailNumber = sixFailNumber;
		this.sevenFailNumber = sevenFailNumber;
		this.eightFailNumber = eightFailNumber;
		this.totalFailNumber = totalFailNumber;
		this.totalStudentNumber = totalStudentNumber;
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

	public Integer getFourFailNumber() {
		return fourFailNumber;
	}

	public void setFourFailNumber(Integer fourFailNumber) {
		this.fourFailNumber = fourFailNumber;
	}

	public Integer getFiveFailNumber() {
		return fiveFailNumber;
	}

	public void setFiveFailNumber(Integer fiveFailNumber) {
		this.fiveFailNumber = fiveFailNumber;
	}

	public Integer getSixFailNumber() {
		return sixFailNumber;
	}

	public void setSixFailNumber(Integer sixFailNumber) {
		this.sixFailNumber = sixFailNumber;
	}

	public Integer getSevenFailNumber() {
		return sevenFailNumber;
	}

	public void setSevenFailNumber(Integer sevenFailNumber) {
		this.sevenFailNumber = sevenFailNumber;
	}

	public Integer getEightFailNumber() {
		return eightFailNumber;
	}

	public void setEightFailNumber(Integer eightFailNumber) {
		this.eightFailNumber = eightFailNumber;
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
		return "GradeFailDistribution [id=" + id + ", grade=" + grade + ", oneFailNumber=" + oneFailNumber
				+ ", twoFailNumber=" + twoFailNumber + ", threeFailNumber=" + threeFailNumber + ", fourFailNumber="
				+ fourFailNumber + ", fiveFailNumber=" + fiveFailNumber + ", sixFailNumber=" + sixFailNumber
				+ ", sevenFailNumber=" + sevenFailNumber + ", eightFailNumber=" + eightFailNumber + ", totalFailNumber="
				+ totalFailNumber + ", totalStudentNumber=" + totalStudentNumber + ", failRate=" + failRate
				+ ", analysis=" + analysis + "]";
	}

	
}
