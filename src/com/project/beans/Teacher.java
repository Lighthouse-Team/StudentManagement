package com.project.beans;

import java.util.Date;

/**
 * 教师表
 * @author yangyang
 *
 */
public class Teacher {

	private Integer teacherId;		// 教师 id 号
	private String teacherName;		// 教师姓名
	private String teacherNumber;	// 教师工号
	private String teacherSex;		// 教师性别
	private Date hireDate;			// 入职时间
	private Date teacherBirth;		// 教师出生日期
	
	// 级联属性
	private Department teacherDepartment;
	
	// 查询属性, 供日期转换使用
	private String strHireDate;
	private String strTeacherBirth;

	public Teacher() {
		super();
	}

	public Teacher(Integer teacherId, String teacherName, String teacherNumber, String teacherSex, Date hireDate,
			Date teacherBirth, Department teacherDepartment, String strHireDate, String strTeacherBirth) {
		super();
		this.teacherId = teacherId;
		this.teacherName = teacherName;
		this.teacherNumber = teacherNumber;
		this.teacherSex = teacherSex;
		this.hireDate = hireDate;
		this.teacherBirth = teacherBirth;
		this.teacherDepartment = teacherDepartment;
		this.strHireDate = strHireDate;
		this.strTeacherBirth = strTeacherBirth;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTeacherNumber() {
		return teacherNumber;
	}

	public void setTeacherNumber(String teacherNumber) {
		this.teacherNumber = teacherNumber;
	}

	public String getTeacherSex() {
		return teacherSex;
	}

	public void setTeacherSex(String teacherSex) {
		this.teacherSex = teacherSex;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Date getTeacherBirth() {
		return teacherBirth;
	}

	public void setTeacherBirth(Date teacherBirth) {
		this.teacherBirth = teacherBirth;
	}

	public Department getTeacherDepartment() {
		return teacherDepartment;
	}

	public void setTeacherDepartment(Department teacherDepartment) {
		this.teacherDepartment = teacherDepartment;
	}

	public String getStrHireDate() {
		return strHireDate;
	}

	public void setStrHireDate(String strHireDate) {
		this.strHireDate = strHireDate;
	}

	public String getStrTeacherBirth() {
		return strTeacherBirth;
	}

	public void setStrTeacherBirth(String strTeacherBirth) {
		this.strTeacherBirth = strTeacherBirth;
	}

	@Override
	public String toString() {
		return "Teacher [teacherId=" + teacherId + ", teacherName=" + teacherName + ", teacherNumber=" + teacherNumber
				+ ", teacherSex=" + teacherSex + ", hireDate=" + hireDate + ", teacherBirth=" + teacherBirth
				+ ", teacherDepartment=" + teacherDepartment + ", strHireDate=" + strHireDate + ", strTeacherBirth="
				+ strTeacherBirth + "]";
	}

}
