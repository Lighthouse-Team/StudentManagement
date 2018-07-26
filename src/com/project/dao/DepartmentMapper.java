package com.project.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import com.project.beans.Department;

@MapperScan
@Repository
public interface DepartmentMapper {

	/**
	 * 通过 departmentId 号获取指定部门信息
	 * @param departmentId
	 * @return
	 */
	public Department getDepartmentByDepartmentId(Integer departmentId);
	
	/**
	 * 通过 department 对象属性查询学生信息
	 * @param department
	 * @return
	 */
	public Department getDepartmentByEntity(Department department);
	
	/**
	 * 获取所有部门信息
	 * @return
	 */
	public List<Department> getAllDepartment();
	
	/**
	 * 通过 department 属性模糊查询学生信息
	 * @param department
	 * @return
	 */
	public List<Department> getDepartmentListByEntityForLike(Department department);
	
	/**
	 * 更新部门信息
	 * @param department
	 */
	public void updateDepartmentByEntity(Department department);
	
	/**
	 * 添加部门信息, 并返回添加后的主键值
	 * @param department
	 * @return
	 */
	public void addDepartment(Department department);
	
	/**
	 * 通过 departmentId 删除指定部门信息
	 * @param departmentId
	 */
	public void deleteDepartmentByDepartmentId(Integer departmentId);
	
}
