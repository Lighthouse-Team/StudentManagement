package com.project.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicCourseNormalDistribution implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3595737495315352005L;
	
	private Integer courseNumber; 			// 课程编号，没有这个课时，其值为0
	private String courseName;				// 课程名称
	private String courseDepartment;		// 开课单位，没有这个课时，其值为“没有这个课”
	private String courseTerm;				// 授课学期，由选择的学期获取
	private String courseObject;			// 授课对象，内容是20XX级本科生，具体年份由选择的学年和课程的开设年级（大一、大二或大三）获取
	private Integer totalStudentNumber;		// 学生总数
	private Integer excellentNumber;		// 优秀学生数
	private Integer failNumber;				// 不及格人数
	private String excellentRate;			// 优秀率
	private String failRate;				// 不及格率
	private String averageScore;			// 平均分
	private String standardDeviation;		// 标准差
	
	private Map<String, Integer> studentNumberMap;	// 各分数段学生数列表
	private List<String> ordinateList;				// 正态分布纵坐标列表
	
	public BasicCourseNormalDistribution() {
		super();
		this.courseNumber = 0;
		this.totalStudentNumber = 0;
		this.excellentNumber = 0;
		this.failNumber = 0;
		this.excellentRate = "0.00%";
		this.failRate = "0.00%";
		this.averageScore = "0.00";
		this.standardDeviation = "0.00"; // 只有一个数时的标准差为0
		this.studentNumberMap = new HashMap<>();
		this.ordinateList = new ArrayList<>();
	}

	public BasicCourseNormalDistribution(Integer courseNumber, String courseName, String courseDepartment,
			String courseTerm, String courseObject, Integer totalStudentNumber, Integer excellentNumber,
			Integer failNumber, String excellentRate, String failRate, String averageScore, String standardDeviation,
			Map<String, Integer> studentNumberMap, List<String> ordinateList) {
		super();
		this.courseNumber = courseNumber;
		this.courseName = courseName;
		this.courseDepartment = courseDepartment;
		this.courseTerm = courseTerm;
		this.courseObject = courseObject;
		this.totalStudentNumber = totalStudentNumber;
		this.excellentNumber = excellentNumber;
		this.failNumber = failNumber;
		this.excellentRate = excellentRate;
		this.failRate = failRate;
		this.averageScore = averageScore;
		this.standardDeviation = standardDeviation;
		this.studentNumberMap = studentNumberMap;
		this.ordinateList = ordinateList;
	}

	public Integer getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(Integer courseNumber) {
		this.courseNumber = courseNumber;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseDepartment() {
		return courseDepartment;
	}

	public void setCourseDepartment(String courseDepartment) {
		this.courseDepartment = courseDepartment;
	}

	public String getCourseTerm() {
		return courseTerm;
	}

	public void setCourseTerm(String courseTerm) {
		this.courseTerm = courseTerm;
	}

	public String getCourseObject() {
		return courseObject;
	}

	public void setCourseObject(String courseObject) {
		this.courseObject = courseObject;
	}

	public Integer getTotalStudentNumber() {
		return totalStudentNumber;
	}

	public void setTotalStudentNumber(Integer totalStudentNumber) {
		this.totalStudentNumber = totalStudentNumber;
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

	public String getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(String averageScore) {
		this.averageScore = averageScore;
	}

	public String getStandardDeviation() {
		return standardDeviation;
	}

	public void setStandardDeviation(String standardDeviation) {
		this.standardDeviation = standardDeviation;
	}

	public Map<String, Integer> getStudentNumberMap() {
		return studentNumberMap;
	}

	public void setStudentNumberMap(Map<String, Integer> studentNumberMap) {
		this.studentNumberMap = studentNumberMap;
	}

	public List<String> getOrdinateList() {
		return ordinateList;
	}

	public void setOrdinateList(List<String> ordinateList) {
		this.ordinateList = ordinateList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "BasicCourseNormalDistribution [courseNumber=" + courseNumber + ", courseName=" + courseName
				+ ", courseDepartment=" + courseDepartment + ", courseTerm=" + courseTerm + ", courseObject="
				+ courseObject + ", totalStudentNumber=" + totalStudentNumber + ", excellentNumber=" + excellentNumber
				+ ", failNumber=" + failNumber + ", excellentRate=" + excellentRate + ", failRate=" + failRate
				+ ", averageScore=" + averageScore + ", standardDeviation=" + standardDeviation + ", studentNumberMap="
				+ studentNumberMap + ", ordinateList=" + ordinateList + "]";
	}
	
}
