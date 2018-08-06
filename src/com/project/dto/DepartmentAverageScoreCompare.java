package com.project.dto;

import java.io.Serializable;

public class DepartmentAverageScoreCompare implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3381304321018919091L;

	String grade;
	String departmentName;
	String averageScore;
	String difference;
	
	public DepartmentAverageScoreCompare() {
		super();
	}
	
	public void initValue() {
		this.averageScore = "0";
		this.difference = "0";
	}

	public DepartmentAverageScoreCompare(String grade, String departmentName, String averageScore, String difference) {
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
