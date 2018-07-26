package com.project.beans;

/**
 * 用户表
 * @author yang
 *
 */
public class User {

	private Integer userId;			// 用户Id
	private String nickName;		// 昵称
	private String userName;		// 用户名
	private String passWord;		// 密码
	private Integer permission;		// 用户权限	：（5：管理员。3：普通用户。）
	private Department department;	// 用户隶属单位(部门)
	
	public User() {
		super();
	}

	public User(Integer userId, String nickName, String userName, String passWord, Integer permission,
			Department department) {
		super();
		this.userId = userId;
		this.nickName = nickName;
		this.userName = userName;
		this.passWord = passWord;
		this.permission = permission;
		this.department = department;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Integer getPermission() {
		return permission;
	}

	public void setPermission(Integer permission) {
		this.permission = permission;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", nickName=" + nickName + ", userName=" + userName + ", passWord=" + passWord
				+ ", permission=" + permission + ", department=" + department + "]";
	}
	
}
