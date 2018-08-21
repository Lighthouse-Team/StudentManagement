package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import com.project.beans.Student;

@MapperScan
@Repository
public interface StudentMapper {

	/**
	 * 通过 studentId 获取指定学生信息
	 * 
	 * @param studentId
	 * @return
	 */
	public Student getStudentByStudentId(Integer studentId);

	/**
	 * 通过 studentNumber 获取指定学生信息
	 * 
	 * @param studentNumber
	 * @return
	 */
	public Student getStudentByStudentNumber(String studentNumber);

	/**
	 * 通过 courseId 获得选该课程的学生列表
	 * 
	 * @param courseId
	 * @return
	 */
	public List<Student> getStudentListByCourseId(Integer courseId);

	/**
	 * 通过 student 对象属性查询学生信息
	 * 
	 * @param student
	 * @return
	 */
	public Student getStudentByEntity(Student student);

	/**
	 * 获取所有学生信息
	 * 
	 * @return
	 */
	public List<Student> getAllStudent();

	/**
	 * 通过 student 属性模糊查询学生信息
	 * 
	 * @param student
	 * @return
	 */
	public List<Student> getStudentListByEntityForLike(Student student);

	/**
	 * 更新学生信息
	 * 
	 * @param student
	 */
	public void updateStudentByEntity(Student student);

	/**
	 * 添加学生信息, 并返回添加后的主键值
	 * 
	 * @param student
	 * @return
	 */
	public void addStudent(Student student);

	/**
	 * 通过 studentId 删除指定学生信息
	 * 
	 * @param studentId
	 */
	public void deleteStudentByStudentId(Integer studentId);

	/**
	 * 通过班级号查询固定年级学生信息
	 * 
	 * @param studentClass
	 * @return
	 */
	public List<Student> getStudentByStudentClass(String studentClass);

	/**
	 * 查询学生选课情况
	 * 
	 * @param student
	 * @return
	 */
	public List<Student> getStudentDetailsByEntityForLike(Student student);
}
