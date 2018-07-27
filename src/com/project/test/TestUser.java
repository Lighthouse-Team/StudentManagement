package com.project.test;

import java.text.NumberFormat;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.project.dto.SCDistribution;
import com.project.service.UserService;

public class TestUser {

	@Autowired
	private UserService userService;
	
	@Before
	public void before() {
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		userService = (UserService) applicationContext.getBean("userService");
	}
	
	public String testTest() {
		System.out.println("xiapeng");
		return "xiapeng";
	}
	
}
