package com.project.test;

import org.junit.Test;

import com.project.tools.InsertIntoDB;

public class TestInsertIntoDB {

	@Test
	public void insertIntoDBTest() {
		// 导入数据库的方法,64秒
		String filePath = "E:/实验室/学生管理系统/删除重复项的成绩列表.xls";
		InsertIntoDB insertIntoDB = new InsertIntoDB();
		insertIntoDB.Insert(filePath);
	}
}
