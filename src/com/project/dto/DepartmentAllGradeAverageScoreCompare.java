package com.project.dto;

import java.io.Serializable;

public class DepartmentAllGradeAverageScoreCompare implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3381304321018919091L;

	private Integer id;						// 序号
	private String departmentName;			// 院系名称
	private String gradeFourAverageScore;	// 该院系大四平均分
	private String gradeFourDifference;		// 该院系大四平均分和年级平均分差值
	private String gradeThreeAverageScore;	// 该院系大三平均分
	private String gradeThreeDifference;	// 该院系大三平均分和年级平均分的差值
	private String gradeTwoAverageScore;	// 该院系大二平均分
	private String gradeTwoDifference;		// 该院系大二平均分和年级平均分的差值
	private String gradeOneAverageScore;	// 该院系大一平均分
	private String gradeOneDifference;		// 该院系大一平均分和年级平均分的差值
	
	public DepartmentAllGradeAverageScoreCompare() {
		super();
		this.gradeFourAverageScore = "0.00";
		this.gradeFourDifference = "0.00";
		this.gradeThreeAverageScore = "0.00";
		this.gradeThreeDifference = "0.00";
		this.gradeTwoAverageScore = "0.00";
		this.gradeTwoDifference = "0.00";
		this.gradeOneAverageScore = "0.00";
		this.gradeOneDifference = "0.00";
	}
	public DepartmentAllGradeAverageScoreCompare(Integer id, String departmentName, String gradeFourAverageScore,
			String gradeFourDifference, String gradeThreeAverageScore, String gradeThreeDifference,
			String gradeTwoAverageScore, String gradeTwoDifference, String gradeOneAverageScore,
			String gradeOneDifference) {
		super();
		this.id = id;
		this.departmentName = departmentName;
		this.gradeFourAverageScore = gradeFourAverageScore;
		this.gradeFourDifference = gradeFourDifference;
		this.gradeThreeAverageScore = gradeThreeAverageScore;
		this.gradeThreeDifference = gradeThreeDifference;
		this.gradeTwoAverageScore = gradeTwoAverageScore;
		this.gradeTwoDifference = gradeTwoDifference;
		this.gradeOneAverageScore = gradeOneAverageScore;
		this.gradeOneDifference = gradeOneDifference;
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
	public String getGradeFourAverageScore() {
		return gradeFourAverageScore;
	}
	public void setGradeFourAverageScore(String gradeFourAverageScore) {
		this.gradeFourAverageScore = gradeFourAverageScore;
	}
	public String getGradeFourDifference() {
		return gradeFourDifference;
	}
	public void setGradeFourDifference(String gradeFourDifference) {
		this.gradeFourDifference = gradeFourDifference;
	}
	public String getGradeThreeAverageScore() {
		return gradeThreeAverageScore;
	}
	public void setGradeThreeAverageScore(String gradeThreeAverageScore) {
		this.gradeThreeAverageScore = gradeThreeAverageScore;
	}
	public String getGradeThreeDifference() {
		return gradeThreeDifference;
	}
	public void setGradeThreeDifference(String gradeThreeDifference) {
		this.gradeThreeDifference = gradeThreeDifference;
	}
	public String getGradeTwoAverageScore() {
		return gradeTwoAverageScore;
	}
	public void setGradeTwoAverageScore(String gradeTwoAverageScore) {
		this.gradeTwoAverageScore = gradeTwoAverageScore;
	}
	public String getGradeTwoDifference() {
		return gradeTwoDifference;
	}
	public void setGradeTwoDifference(String gradeTwoDifference) {
		this.gradeTwoDifference = gradeTwoDifference;
	}
	public String getGradeOneAverageScore() {
		return gradeOneAverageScore;
	}
	public void setGradeOneAverageScore(String gradeOneAverageScore) {
		this.gradeOneAverageScore = gradeOneAverageScore;
	}
	public String getGradeOneDifference() {
		return gradeOneDifference;
	}
	public void setGradeOneDifference(String gradeOneDifference) {
		this.gradeOneDifference = gradeOneDifference;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "DepartmentAverageScoreCompare [id=" + id + ", departmentName=" + departmentName
				+ ", gradeFourAverageScore=" + gradeFourAverageScore + ", gradeFourDifference=" + gradeFourDifference
				+ ", gradeThreeAverageScore=" + gradeThreeAverageScore + ", gradeThreeDifference="
				+ gradeThreeDifference + ", gradeTwoAverageScore=" + gradeTwoAverageScore + ", gradeTwoDifference="
				+ gradeTwoDifference + ", gradeOneAverageScore=" + gradeOneAverageScore + ", gradeOneDifference="
				+ gradeOneDifference + "]";
	}
	
}
