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
	 * 
	 * @author xiapeng 2018年7月25日17:00:33
	 */

	/**
	 * 通过 grade 获得该年级该学期所有课程的加权总分
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public double getACTotalScoreByGrade(@Param(value = "grade") Integer grade, @Param(value = "year") String year,
			@Param(value = "term") Integer term);

	/**
	 * 通过 grade 获得该年级该学期所有课程的总学分
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public double getACTotalCreditsByGrade(@Param(value = "grade") Integer grade, @Param(value = "year") String year,
			@Param(value = "term") Integer term);

	/**
	 * 通过 grade 获得该年级该学期的所有成绩记录数
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public Integer getACTotalSocreRecordNumberByGrade(@Param(value = "grade") Integer grade,
			@Param(value = "year") String year, @Param(value = "term") Integer term);

	/**
	 * 通过 grade 获得该年级该学期的优秀成绩记录数
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public Integer getACExcellentScoreRecordNumberByGrade(@Param(value = "grade") Integer grade,
			@Param(value = "year") String year, @Param(value = "term") Integer term);

	/**
	 * 通过 grade 获得该年级该学期的良好成绩记录数
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public Integer getACGoodScoreRecordNumberByGrade(@Param(value = "grade") Integer grade,
			@Param(value = "year") String year, @Param(value = "term") Integer term);

	/**
	 * 通过 grade 获得该年级该学期的中等成绩记录数
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public Integer getACMediumScoreRecordNumberByGrade(@Param(value = "grade") Integer grade,
			@Param(value = "year") String year, @Param(value = "term") Integer term);

	/**
	 * 通过 grade 获得该年级该学期的及格成绩记录数
	 * 
	 * @param grade
	 * @param year
	 * @param term
	 * @return
	 */
	public Integer getACPassScoreRecordNumberByGrade(@Param(value = "grade") Integer grade,
			@Param(value = "year") String year, @Param(value = "term") Integer term);

}
