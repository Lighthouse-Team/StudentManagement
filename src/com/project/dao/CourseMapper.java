package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import com.project.beans.Course;

@MapperScan
@Repository
public interface CourseMapper {

	/**
	 * 通过 courseId 获取指定课程信息
	 * 
	 * @param courseId
	 * @return
	 */
	public Course getCourseByCourseId(Integer courseId);

	/**
	 * 通过 courseNumber 获取指定课程信息
	 * 
	 * @param courseNumber
	 * @return
	 */
	public Course getCourseByCourseNumber(String courseNumber);

	/**
	 * 通过 studengId 获取该学生的选课列表
	 * 
	 * @param studentId
	 * @return
	 */
	public List<Course> getCourseListByStudentId(@Param(value = "studentId") Integer studentId,
			@Param(value = "year") String year, @Param(value = "term") Integer term);

	/**
	 * 通过 course 属性查询课程信息
	 * 
	 * @param course
	 * @return
	 */
	public Course getCourseByEntity(Course course);

	/**
	 * 获取所有课程信息
	 * 
	 * @return
	 */
	public List<Course> getAllCourse();

	/**
	 * 通过 course 属性模糊查询课程信息
	 * 
	 * @param course
	 * @return
	 */
	public List<Course> getCourseListByEntityForLike(Course course);

	/**
	 * 更新课程信息
	 * 
	 * @param course
	 */
	public void updateCourseByEntity(Course course);

	/**
	 * 添加课程信息, 并返回添加后的主键值
	 * 
	 * @param course
	 * @return
	 */
	public void addCourse(Course course);

	/**
	 * 通过 courseId 删除指定课程信息
	 * 
	 * @param courseId
	 */
	public void deleteCourseByCourseId(Integer courseId);

	/**
	 * 根据课程属性查询学生选课情况
	 * 
	 * @param course
	 * @param year
	 * @param term
	 * @return
	 */
	public List<Course> getCourseDetailsByEntityForLike(@Param(value = "course") Course course,
			@Param(value = "year") String year, @Param(value = "term") Integer term);

	/**
	 * 判断数据库中是否有该课程
	 * 
	 * @param courseNumber
	 * @return
	 */
	public Integer hasCourse(String courseNumber);

	/*
	 * 这里设置 courseType
	 */

	/**
	 * 设置必修课的 courseType = 1
	 */
	public void setCourseTypeOfRequierdCourse();

	/**
	 * 设置专业选修课的 courseType = 2
	 */
	public void setCourseTypeOfProfessionalElectiveCourse();

	/**
	 * 设置通识选修课的 courseType = 3
	 */
	public void setCourseTypeOfGeneralElectiveCourse();
}