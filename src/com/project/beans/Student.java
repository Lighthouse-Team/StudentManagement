package com.project.beans;

import java.util.Date;
import java.util.List;

/**
 * 学生信息表
 * @author yangyang
 *
 */
public class Student {

	private Integer studentId;		// 学生 id 号
	private String studentNumber;	// 学生学号
	private String studentName;		// 学生姓名
	private String studentSex;		// 学生性别
	private String studentIDCard;	// 学生身份证号
	private Date studentBirth;		// 学生出生日期
	private Date enrollment;		// 入学时间
	private String studentClass;	// 所属班级
	
	// 级联属性
	private Department sDepartment;	// 所属院系
	private List<Course> courseList;// 选修课程
	
	// 查询属性, 供日期转换用
	private String strStudentBirth;
	private String strEnrollment;
	
	public Student() {
		super();
	}

	public Student(Integer studentId, String studentNumber, String studentName, String studentSex, String studentIDCard,
			Date studentBirth, Date enrollment, String studentClass, Department sDepartment, List<Course> courseList,
			String strStudentBirth, String strEnrollment) {
		super();
		this.studentId = studentId;
		this.studentNumber = studentNumber;
		this.studentName = studentName;
		this.studentSex = studentSex;
		this.studentIDCard = studentIDCard;
		this.studentBirth = studentBirth;
		this.enrollment = enrollment;
		this.studentClass = studentClass;
		this.sDepartment = sDepartment;
		this.courseList = courseList;
		this.strStudentBirth = strStudentBirth;
		this.strEnrollment = strEnrollment;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentSex() {
		return studentSex;
	}

	public void setStudentSex(String studentSex) {
		this.studentSex = studentSex;
	}

	public String getStudentIDCard() {
		return studentIDCard;
	}

	public void setStudentIDCard(String studentIDCard) {
		this.studentIDCard = studentIDCard;
	}

	public Date getStudentBirth() {
		return studentBirth;
	}

	public void setStudentBirth(Date studentBirth) {
		this.studentBirth = studentBirth;
	}

	public Date getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(Date enrollment) {
		this.enrollment = enrollment;
	}

	public String getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}

	public Department getsDepartment() {
		return sDepartment;
	}

	public void setsDepartment(Department sDepartment) {
		this.sDepartment = sDepartment;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	public String getStrStudentBirth() {
		return strStudentBirth;
	}

	public void setStrStudentBirth(String strStudentBirth) {
		this.strStudentBirth = strStudentBirth;
	}

	public String getStrEnrollment() {
		return strEnrollment;
	}

	public void setStrEnrollment(String strEnrollment) {
		this.strEnrollment = strEnrollment;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentNumber=" + studentNumber + ", studentName=" + studentName
				+ ", studentSex=" + studentSex + ", studentIDCard=" + studentIDCard + ", studentBirth=" + studentBirth
				+ ", enrollment=" + enrollment + ", studentClass=" + studentClass + ", sDepartment=" + sDepartment
				+ ", courseList=" + courseList + ", strStudentBirth=" + strStudentBirth + ", strEnrollment="
				+ strEnrollment + "]";
	}

}
