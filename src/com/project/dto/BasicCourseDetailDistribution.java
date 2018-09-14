package com.project.dto;

import java.io.Serializable;

public class BasicCourseDetailDistribution implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9136010051949633226L;

	private Integer id; 				// 序号
	private String courseName;			// 课程名称
	private String departmentName;		// 院系名称
	private Integer excellentNumber;	// 优秀学生数量
	private Integer failNumber;			// 不及格学生数量
	private Integer totalStudentNumber;	// 学生总数
	private String excellentRate;		// 优秀率
	private String failRate;			// 不及格率
	private String analysis;            //分析
	
	public BasicCourseDetailDistribution() {
		super();
		this.excellentNumber = 0;
		this.failNumber = 0;
		this.totalStudentNumber = 0;
		this.excellentRate = "0.00%";
		this.failRate = "0.00%";
		this.analysis = "";
	}

	public BasicCourseDetailDistribution(Integer id, String courseName, String departmentName, Integer excellentNumber,
			Integer failNumber, Integer totalStudentNumber, String excellentRate, String failRate, String analysis) {
		super();
		this.id = id;
		this.courseName = courseName;
		this.departmentName = departmentName;
		this.excellentNumber = excellentNumber;
		this.failNumber = failNumber;
		this.totalStudentNumber = totalStudentNumber;
		this.excellentRate = excellentRate;
		this.failRate = failRate;
		this.analysis = analysis;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getExcellentNumber() {
		return excellentNumber;
	}

	public void setExcellentNumber(Integer excellentNumber) {
		this.excellentNumber = excellentNumber;
	}

	public Integer getFailNumber() {
		return failNumber;
	}

	public void setFailNumber(Integer failNumber) {
		this.failNumber = failNumber;
	}

	public Integer getTotalStudentNumber() {
		return totalStudentNumber;
	}

	public void setTotalStudentNumber(Integer totalStudentNumber) {
		this.totalStudentNumber = totalStudentNumber;
	}

	public String getExcellentRate() {
		return excellentRate;
	}

	public void setExcellentRate(String excellentRate) {
		this.excellentRate = excellentRate;
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

	@Override
	public String toString() {
		return "BasicCourseDetailDistribution [id=" + id + ", courseName=" + courseName + ", departmentName="
				+ departmentName + ", excellentNumber=" + excellentNumber + ", failNumber=" + failNumber
				+ ", totalStudentNumber=" + totalStudentNumber + ", excellentRate=" + excellentRate + ", failRate="
				+ failRate + ", analysis=" + analysis + "]";
	}

	
}
