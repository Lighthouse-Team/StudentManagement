package com.project.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.project.tools.InsertIntoDB;

public class TestInsertIntoDB {

	@Test
	public void insertIntoDBTest() { // 导入数据库的方法
		String filePath = "E:/实验室/学生管理系统/删除重复项的成绩列表.xls";
		InsertIntoDB insertIntoDB = new InsertIntoDB();
		insertIntoDB.Insert(filePath);
	}
	
	@Test
	public void mapTest() {
		Map<String, Integer> map = new HashMap<>();
		map.put("name", 1);
		System.out.println(map.get("name")); // 1
		System.out.println(map.get("hello")); // null
	}
}
