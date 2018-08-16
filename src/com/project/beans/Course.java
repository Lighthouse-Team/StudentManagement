package com.project.beans;

import java.util.List;

/**
 * 课程信息表
 * @author yangyang
 *
 */
public class Course {

	private Integer courseId;			// 课程 id 号
	private String courseName;			// 课程名称
	private String courseNumber;		// 课程编号
	private String coursePeriod;		// 课程学时：多个学时或多个周
	private double courseCredits;		// 课程学分
	private String courseDepartment;	// 开课单位
	private String courseAttribute;		// 课程属性：专业核心课程、专业选修课程等等
	private String courseProperty;		// 课程性质：必修、公选、任选、限选
	
	private List<Student> studentList;	// 选课学生
	
	public Course() {
		super();
	}

	public Course(Integer courseId, String courseName, String courseNumber, String coursePeriod, double courseCredits,
			String courseDepartment, String courseAttribute, String courseProperty, List<Student> studentList) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseNumber = courseNumber;
		this.coursePeriod = coursePeriod;
		this.courseCredits = courseCredits;
		this.courseDepartment = courseDepartment;
		this.courseAttribute = courseAttribute;
		this.courseProperty = courseProperty;
		this.studentList = studentList;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public String getCoursePeriod() {
		return coursePeriod;
	}

	public void setCoursePeriod(String coursePeriod) {
		this.coursePeriod = coursePeriod;
	}

	public double getCourseCredits() {
		return courseCredits;
	}

	public void setCourseCredits(double courseCredits) {
		this.courseCredits = courseCredits;
	}

	public String getCourseDepartment() {
		return courseDepartment;
	}

	public void setCourseDepartment(String courseDepartment) {
		this.courseDepartment = courseDepartment;
	}

	public String getCourseAttribute() {
		return courseAttribute;
	}

	public void setCourseAttribute(String courseAttribute) {
		this.courseAttribute = courseAttribute;
	}

	public String getCourseProperty() {
		return courseProperty;
	}

	public void setCourseProperty(String courseProperty) {
		this.courseProperty = courseProperty;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", courseNumber=" + courseNumber
				+ ", coursePeriod=" + coursePeriod + ", courseCredits=" + courseCredits + ", courseDepartment="
				+ courseDepartment + ", courseAttribute=" + courseAttribute + ", courseProperty=" + courseProperty
				+ ", studentList=" + studentList + "]";
	}
}
