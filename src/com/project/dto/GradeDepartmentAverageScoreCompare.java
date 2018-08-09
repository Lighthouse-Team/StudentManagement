package com.project.dto;

import java.io.Serializable;

public class GradeDepartmentAverageScoreCompare implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3381304321018919091L;

	String grade;			// 年级
	String departmentName;	// 专业名称
	String averageScore;	// 年级平均分
	String difference;		// 平均分差值（院系平均分-年级平均分）
	
	public GradeDepartmentAverageScoreCompare() {
		super();
	}
	
	public void initValue() {
		this.averageScore = "0";
		this.difference = "0";
	}

	public GradeDepartmentAverageScoreCompare(String grade, String departmentName, String averageScore, String difference) {
		super();
		this.grade = grade;
		this.departmentName = departmentName;
		this.averageScore = averageScore;
		this.difference = difference;
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

	public String getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(String averageScore) {
		this.averageScore = averageScore;
	}

	public String getDifference() {
		return difference;
	}

	public void setDifference(String difference) {
		this.difference = difference;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "DepartmentAverageScoreCompare [grade=" + grade + ", departmentName=" + departmentName
				+ ", averageScore=" + averageScore + ", difference=" + difference + "]";
	}
	
	
	
}
