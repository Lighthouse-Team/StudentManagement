package com.project.beans;

/**
 * 学生选课信息表
 * @author yangyang
 *
 */
public class StudentCourse {

	private Integer scId;			// 选课Id，主键
	private double score;			// 课程分数
	private String scLevel;			// 课程等级	（优秀：95	 良好85	中等：75	及格：65	不及格：35）
	private String examTerm;  		// 选课年份
	private Integer examProperty; 	// 考试性质（1：正常考试	2：补考	3：补考二	4：自主考试）
	private Integer scoreMark; 		// 成绩标志（1：空（参加考试）	2：缺考	3：缓考	4：违纪	5：作弊）
	
	
	// 级联属性
	private Student scStudent;
	private Course scCourse;
	
	public StudentCourse() {
		super();
	}

	public StudentCourse(Integer scId, double score, String scLevel, String examTerm, Integer examProperty,
			Integer scoreMark, Student scStudent, Course scCourse) {
		super();
		this.scId = scId;
		this.score = score;
		this.scLevel = scLevel;
		this.examTerm = examTerm;
		this.examProperty = examProperty;
		this.scoreMark = scoreMark;
		this.scStudent = scStudent;
		this.scCourse = scCourse;
	}

	public Integer getScId() {
		return scId;
	}

	public void setScId(Integer scId) {
		this.scId = scId;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getScLevel() {
		return scLevel;
	}

	public void setScLevel(String scLevel) {
		this.scLevel = scLevel;
	}

	public String getExamTerm() {
		return examTerm;
	}

	public void setExamTerm(String examTerm) {
		this.examTerm = examTerm;
	}

	public Integer getExamProperty() {
		return examProperty;
	}

	public void setExamProperty(Integer examProperty) {
		this.examProperty = examProperty;
	}

	public Integer getScoreMark() {
		return scoreMark;
	}

	public void setScoreMark(Integer scoreMark) {
		this.scoreMark = scoreMark;
	}

	public Student getScStudent() {
		return scStudent;
	}

	public void setScStudent(Student scStudent) {
		this.scStudent = scStudent;
	}

	public Course getScCourse() {
		return scCourse;
	}

	public void setScCourse(Course scCourse) {
		this.scCourse = scCourse;
	}

	@Override
	public String toString() {
		return "StudentCourse [scId=" + scId + ", score=" + score + ", scLevel=" + scLevel + ", examTerm=" + examTerm
				+ ", examProperty=" + examProperty + ", scoreMark=" + scoreMark + ", scStudent=" + scStudent
				+ ", scCourse=" + scCourse + "]";
	}


}
