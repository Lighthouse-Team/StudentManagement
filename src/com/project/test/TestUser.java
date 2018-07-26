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
	
	@Test
	public void test() {
	    double percent = (double)5/15;
	    double percent3 = (double)0/1;
	    NumberFormat nt = NumberFormat.getPercentInstance();//获取格式化对象
	    nt.setMinimumFractionDigits(2);//设置百分数精确度2即保留两位小数
	    System.out.println("百分数1：" + nt.format(percent));//最后格式化并输出
	    System.out.println("百分数3：" + nt.format(percent3));
	}
	
	@Test
	public void test1() {
		userService.getSCDistribution("2015", 1);
	}
	
	
	@Test
	public void test2() {
		String a = "2.5";
		double b = Double.parseDouble(a);
		System.out.println(b);
	}
	
	@Test
	public void test3() {
		System.out.println(userService.getTotalNumberByGrade("2015"));
	}
	
}
