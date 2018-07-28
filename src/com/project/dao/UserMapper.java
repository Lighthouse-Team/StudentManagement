package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import com.project.beans.User;

@MapperScan
@Repository
public interface UserMapper {

	/**
	 * 根据指定用户 id 查询用户
	 * 
	 * @param userId:
	 * @return
	 */
	public User getUserById(Integer userId);

	/**
	 * 查看指定用户名的用户是否存在(用户名验证)
	 * 
	 * @param userName
	 * @return
	 */
	public User getUserByUserName(String userName);

	/**
	 * 查看指定昵称的用户是否存在
	 * 
	 * @param nickName
	 * @return
	 */
	public User getUserByNickName(String nickName);

	/**
	 * 通过用户名及密码查看用户是否存在(登陆使用)
	 * 
	 * @param userName
	 * @param passWord
	 * @return
	 */
	public User getUserByUserNameAndPassWord(@Param("userName") String userName, @Param("passWord") String passWord);

	/**
	 * 删除指定 id 号的用户
	 * 
	 * @param userId
	 */
	public void deleteUserById(Integer userId);

	/**
	 * 添加新用户
	 * 
	 * @param user
	 */
	public void addUser(User user);

	/**
	 * 通过指定 User 对象的属性更新用户
	 * 
	 * @param user
	 */
	public void updateUser(User user);

	/**
	 * 查询用户表中用户的个数
	 * 
	 * @return
	 */
	public int getUserNumber();

	/**
	 * 查询所有用户
	 * 
	 * @return
	 */
	public List<User> getUserAllUsers();

	/**
	 * 通过 User 对象中的条件进行模糊查询
	 * 
	 * @param user
	 * @return
	 */
	public List<User> getUserForList(String userName);

}
