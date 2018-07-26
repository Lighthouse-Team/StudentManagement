package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.beans.Department;
import com.project.dao.DepartmentMapper;

@Service
public class DepartmentService {
	@Autowired
	private DepartmentMapper departmentMapper;
	
	/**
	 * 根据院系编号获得院系对象
	 * @param departmentId
	 * @return
	 */
	public Department getDepartmentByDepartmentId(Integer departmentId) {
		return departmentMapper.getDepartmentByDepartmentId(departmentId);
	}
	
	/**
	 * 通过 department 模糊查询院系
	 * @param department
	 * @return
	 */
	public Department getDepartmentByEntity(Department department) {
		return departmentMapper.getDepartmentByEntity(department);
	}
	
	/**
	 * 根据 department 属性模糊查询
	 * @param department
	 * @return
	 */
	public List<Department> getDepartmentListByEntityForLike(Department department){
		return departmentMapper.getDepartmentListByEntityForLike(department);
	}
}
