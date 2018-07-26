package com.project.dao;

import java.util.List;

import com.project.beans.Teacher;

public interface TeacherMapper {

	/**
	 * 通过 teacherId 获取指定教师信息
	 * 
	 * @param teacherId
	 * @return
	 */
	public Teacher getTeacherByTeacherId(Integer teacherId);

	/**
	 * 通过 teacher 对象属性查询教师信息
	 * 
	 * @param teacher
	 * @return
	 */
	public Teacher getTeacherByEntity(Teacher teacher);

	/**
	 * 获取所有教师信息
	 * 
	 * @return
	 */
	public List<Teacher> getAllTeacher();

	/**
	 * 通过 teacher 属性模糊查询教师信息
	 * 
	 * @param teacher
	 * @return
	 */
	public List<Teacher> getTeacherListByEntityForLike(Teacher teacher);

	/**
	 * 更新教师信息
	 * 
	 * @param teacher
	 */
	public void updateTeacherByEntity(Teacher teacher);

	/**
	 * 添加教师信息, 并返回添加后的主键值
	 * 
	 * @param teacher
	 * @return
	 */
	public void addTeacher(Teacher teacher);

	/**
	 * 通过 teacherId 删除指定教师信息
	 * 
	 * @param teacherId
	 */
	public void deleteTeacherByTeacherId(Integer teacherId);

}
