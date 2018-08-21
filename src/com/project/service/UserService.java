package com.project.service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.beans.Course;
import com.project.beans.Student;
import com.project.beans.StudentCourse;
import com.project.beans.User;
import com.project.dao.CourseMapper;
import com.project.dao.StudentCourseMapper;
import com.project.dao.StudentMapper;
import com.project.dao.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	/**
	 * 通过用户名获取用户
	 * 
	 * @param userName
	 * @return
	 */
	public User getUserByUserName(String userName) {
		return userMapper.getUserByUserName(userName);
	}

	/**
	 * 通过昵称获取用户
	 * 
	 * @param nickName
	 * @return
	 */
	public User getUserByNickName(String nickName) {
		return userMapper.getUserByNickName(nickName);
	}

	/**
	 * 通过用户名和密码获取用户
	 * 
	 * @param userName
	 * @param passWord
	 * @return
	 */
	public User getUserByUserNameAndPassWord(String userName, String passWord) {
		return userMapper.getUserByUserNameAndPassWord(userName, passWord);
	}

	/**
	 * 获得所有用户列表
	 * 
	 * @return
	 */
	public List<User> getAllUsers() {
		return userMapper.getUserAllUsers();
	}

	/**
	 * 增加用户
	 * 
	 * @param user
	 * @return
	 */
	public Integer addUser(User user) {
		userMapper.addUser(user);
		return user.getUserId();
	}

	/**
	 * 编辑用户信息
	 * 
	 * @param user
	 */
	public void useEdit(User user) {
		userMapper.updateUser(user);
	}

}
