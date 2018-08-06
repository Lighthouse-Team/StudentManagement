package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.beans.StudentCourse;

public interface StudentCourseMapper {

	// public StudentCourse getStudentCourseBySCId(Integer studentcourseId);

	/**
	 * 通过 studentCourse 对象属性查询学生课程信息
	 * 
	 * @param studentcourse
	 * @return
	 */
	public StudentCourse getStudentCourseByEntity(StudentCourse studentCourse);

	/**
	 * 获取所有学生课程信息
	 * 
	 * @return
	 */
	public List<StudentCourse> getAllStudentCourse();

	/**
	 * 通过 studentCourse 属性模糊查询学生课程信息
	 * 
	 * @param studentCourse
	 * @return
	 */
	public List<StudentCourse> getStudentCourseListByEntityForLike(StudentCourse studentCourse);

	/**
	 * 更新学生课程信息
	 * 
	 * @param studentCourse
	 */
	public void updateStudentCourseByEntity(StudentCourse studentCourse);

	/**
	 * 添加学生信息, 并返回添加后的主键值
	 * 
	 * @param student
	 * @return
	 */
	public void addStudentCourse(StudentCourse studentCourse);

	/**
	 * 通过 studentCourse 删除指定学生课程信息
	 * 
	 * @param studentCourse
	 */
	public void deleteStudentCourseByEntity(StudentCourse studentCourse);

	/**
	 * 下面为前端展示数据需要的接口
	 */

	/*
	 * =======通过 grade 属性获得AC的考试成绩的分布，AC指所有课程，包括：必修课、专业选修课、通识选修课=======
	 */

	/**
	 * 通过 grade 获得该年级该学期AC的加权总分
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public double getACTotalScoreByGrade(@Param(value = "grade") Integer grade, @Param(value = "year") String year,
			@Param(value = "term") Integer term);

	/**
	 * 通过 grade 获得该年级该学期AC的总学分
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public double getACTotalCreditsByGrade(@Param(value = "grade") Integer grade, @Param(value = "year") String year,
			@Param(value = "term") Integer term);

	/**
	 * 通过 grade 获得该年级该学期AC的所有成绩记录数
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public Integer getACTotalSocreRecordNumberByGrade(@Param(value = "grade") Integer grade,
			@Param(value = "year") String year, @Param(value = "term") Integer term);

	/**
	 * 通过 grade 获得该年级该学期AC的优秀成绩记录数
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public Integer getACExcellentScoreRecordNumberByGrade(@Param(value = "grade") Integer grade,
			@Param(value = "year") String year, @Param(value = "term") Integer term);

	/**
	 * 通过 grade 获得该年级该学期AC的良好成绩记录数
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public Integer getACGoodScoreRecordNumberByGrade(@Param(value = "grade") Integer grade,
			@Param(value = "year") String year, @Param(value = "term") Integer term);

	/**
	 * 通过 grade 获得该年级该学期AC的中等成绩记录数
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public Integer getACMediumScoreRecordNumberByGrade(@Param(value = "grade") Integer grade,
			@Param(value = "year") String year, @Param(value = "term") Integer term);

	/**
	 * 通过 grade 获得该年级该学期AC的及格成绩记录数
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public Integer getACPassScoreRecordNumberByGrade(@Param(value = "grade") Integer grade,
			@Param(value = "year") String year, @Param(value = "term") Integer term);

	/*
	 * =======通过 courseType 属性获得AG该课程的考试成绩分布，AG指所有年级========
	 */

	/**
	 * 通过 courseType 获得AG该学期该课程的加权总分
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public double getAGTotalScoreByCourseType(@Param(value = "courseType") Integer courseType,
			@Param(value = "year") String year, @Param(value = "term") Integer term);

	/**
	 * 通过 CourseType 获得AG该学期该课程的总学分
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public double getAGTotalCreditsByCourseType(@Param(value = "courseType") Integer courseType,
			@Param(value = "year") String year, @Param(value = "term") Integer term);

	/**
	 * 通过 CourseType 获得AG该学期该课程的所有成绩记录数
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public Integer getAGTotalSocreRecordNumberByCourseType(@Param(value = "courseType") Integer courseType,
			@Param(value = "year") String year, @Param(value = "term") Integer term);

	/**
	 * 通过 CourseType 获得AG该学期该课程的优秀成绩记录数
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public Integer getAGExcellentScoreRecordNumberByCourseType(@Param(value = "courseType") Integer courseType,
			@Param(value = "year") String year, @Param(value = "term") Integer term);

	/**
	 * 通过 CourseType 获得AG该学期该课程的良好成绩记录数
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public Integer getAGGoodScoreRecordNumberByCourseType(@Param(value = "courseType") Integer courseType,
			@Param(value = "year") String year, @Param(value = "term") Integer term);

	/**
	 * 通过 CourseType 获得AG该学期该课程的中等成绩记录数
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public Integer getAGMediumScoreRecordNumberByCourseType(@Param(value = "courseType") Integer courseType,
			@Param(value = "year") String year, @Param(value = "term") Integer term);

	/**
	 * 通过 CourseType 获得AG该学期该课程的及格成绩记录数
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public Integer getAGPassScoreRecordNumberByCourseType(@Param(value = "courseType") Integer courseType,
			@Param(value = "year") String year, @Param(value = "term") Integer term);

	/*
	 * ======通过 grade 获得RPEC的考试成绩的分布，RPEC指必修课和专业选修课=======
	 */

	/**
	 * 通过 grade 获得该年级该学期RPEC的加权总分
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public double getRPECTotalScoreByGrade(@Param(value = "grade") Integer grade, @Param(value = "year") String year,
			@Param(value = "term") Integer term);

	/**
	 * 通过 grade 获得该年级该学期RPEC的总学分
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public double getRPECTotalCreditsByGrade(@Param(value = "grade") Integer grade, @Param(value = "year") String year,
			@Param(value = "term") Integer term);

	/**
	 * 通过 grade 获得该年级该学期RPEC的所有成绩记录数
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public Integer getRPECTotalSocreRecordNumberByGrade(@Param(value = "grade") Integer grade,
			@Param(value = "year") String year, @Param(value = "term") Integer term);

	/**
	 * 通过 grade 获得该年级该学期RPEC的优秀成绩记录数
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public Integer getRPECExcellentScoreRecordNumberByGrade(@Param(value = "grade") Integer grade,
			@Param(value = "year") String year, @Param(value = "term") Integer term);

	/**
	 * 通过 grade 获得该年级该学期RPEC的良好成绩记录数
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public Integer getRPECGoodScoreRecordNumberByGrade(@Param(value = "grade") Integer grade,
			@Param(value = "year") String year, @Param(value = "term") Integer term);

	/**
	 * 通过 grade 获得该年级该学期RPEC的中等成绩记录数
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public Integer getRPECMediumScoreRecordNumberByGrade(@Param(value = "grade") Integer grade,
			@Param(value = "year") String year, @Param(value = "term") Integer term);

	/**
	 * 通过 grade 获得该年级该学期RPEC的及格成绩记录数
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public Integer getRPECPassScoreRecordNumberByGrade(@Param(value = "grade") Integer grade,
			@Param(value = "year") String year, @Param(value = "term") Integer term);

	/*
	 * =======通过 grade和departmentName 获得RPEC的考试成绩的分布，RPEC指必修课和专业选修课=========
	 */

	/**
	 * 通过 grade和departmentId 获得该学院该年级该学期RPEC的加权总分
	 * 
	 * @param grade
	 * @param departmentId
	 * @param year
	 * @param term
	 * @return
	 */
	public double getRPECTotalScoreByGradeAndDepartmentId(@Param(value = "departmentId") Integer departmentId,
			@Param(value = "grade") Integer grade, @Param(value = "year") String year,
			@Param(value = "term") Integer term);

	/**
	 * 通过 grade和departmentId 获得该学院该年级该学期RPEC的总学分
	 * 
	 * @param grade
	 * @param departmentId
	 * @param year
	 * @param term
	 * @return
	 */
	public double getRPECTotalCreditsByGradeAndDepartmentId(@Param(value = "departmentId") Integer departmentId,
			@Param(value = "grade") Integer grade, @Param(value = "year") String year,
			@Param(value = "term") Integer term);

	/**
	 * 通过 grade和departmentId 获得该学院该年级该学期RPEC的所有成绩记录数
	 * 
	 * @param grade
	 * @param departmentId
	 * @param year
	 * @param term
	 * @return
	 */
	public Integer getRPECTotalScoreRecordNumberByGradeAndDepartmentId(
			@Param(value = "departmentId") Integer departmentId, @Param(value = "grade") Integer grade,
			@Param(value = "year") String year, @Param(value = "term") Integer term);

	/**
	 * 通过 grade和departmentId 获得该学院该年级该学期RPEC的优秀成绩记录数
	 * 
	 * @param grade
	 * @param departmentId
	 * @param year
	 * @param term
	 * @return
	 */
	public Integer getRPECExcellentScoreRecordNumberByGradeAndDepartmentId(
			@Param(value = "departmentId") Integer departmentId, @Param(value = "grade") Integer grade,
			@Param(value = "year") String year, @Param(value = "term") Integer term);

	/**
	 * 通过 grade和departmentId 获得该学院该年级该学期RPEC的良好成绩记录数
	 * 
	 * @param grade
	 * @param departmentId
	 * @param year
	 * @param term
	 * @return
	 */
	public Integer getRPECGoodScoreRecordNumberByGradeAndDepartmentId(
			@Param(value = "departmentId") Integer departmentId, @Param(value = "grade") Integer grade,
			@Param(value = "year") String year, @Param(value = "term") Integer term);

	/**
	 * 通过 grade和departmentId 获得该学院该年级该学期RPEC的中等成绩记录数
	 * 
	 * @param grade
	 * @param departmentId
	 * @param year
	 * @param term
	 * @return
	 */
	public Integer getRPECMediumScoreRecordNumberByGradeAndDepartmentId(
			@Param(value = "departmentId") Integer departmentId, @Param(value = "grade") Integer grade,
			@Param(value = "year") String year, @Param(value = "term") Integer term);

	/**
	 * 通过 grade和departmentId 获得该学院该年级该学期RPEC的及格成绩记录数
	 * 
	 * @param grade
	 * @param departmentId
	 * @param year
	 * @param term
	 * @return
	 */
	public Integer getRPECPassScoreRecordNumberByGradeAndDepartmentId(
			@Param(value = "departmentId") Integer departmentId, @Param(value = "grade") Integer grade,
			@Param(value = "year") String year, @Param(value = "term") Integer term);

	/*
	 * ======通过 classNumber 获得该班级RPEC的优秀率、不及格情况，RPEC指必修课和专业选修课========
	 */

	/**
	 * 通过 grade 获得该年级的所有班级
	 * 
	 * @param grade
	 * @return
	 */
	public List<String> getAllClassNumberByGrade(@Param(value = "grade") Integer grade);

	/**
	 * 通过 classNumber 获得该班级RPEC的成绩记录总数
	 * 
	 * @param classNumber
	 * @param year
	 * @param term
	 * @return
	 */
	public Integer getRPECTotalScoreRecordNumberByClassNumber(@Param(value = "classNumber") String classNumber,
			@Param(value = "year") String year, @Param(value = "term") Integer term);

	/**
	 * 通过 classNumber 获得该班级RPEC的优秀成绩记录数
	 * 
	 * @param classNumber
	 * @param year
	 * @param term
	 * @return
	 */
	public Integer getRPECExcellentScoreRecordNumberByClassNumber(@Param(value = "classNumber") String classNumber,
			@Param(value = "year") String year, @Param(value = "term") Integer term);

	/**
	 * 通过 classNumber 获得该班级RPEC的不及格成绩记录数
	 * 
	 * @param classNumber
	 * @param year
	 * @param term
	 * @return
	 */
	public Integer getRPECFailScoreRecordNumberByClassNumber(@Param(value = "classNumber") String classNumber,
			@Param(value = "year") String year, @Param(value = "term") Integer term);

	/*
	 * ======获得所有专业所有年级的平均成绩和差值====== 这里没有定义新的接口，使用之前定义的接口可以实现预期功能
	 */

}
