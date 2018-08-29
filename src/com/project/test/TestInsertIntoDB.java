package com.project.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import com.project.tools.InsertIntoDB;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class TestInsertIntoDB {

	@Test
	public void insertIntoDBTest() {
		// 2015级-2017-2018-1.xls,52450条记录,633秒
		// E:/实验室/学生管理系统/2017-2018-2成绩/2017级-2017-2018-2成绩.xls,55186条记录,936秒
		// E:/实验室/学生管理系统/2017-2018-2成绩/2016级-2017-2018-2成绩.xls,51194条记录,827秒
		// E:/实验室/学生管理系统/2017-2018-2成绩/2015级-2017-2018-2.xls,42952条记录,2689
		// E:/实验室/学生管理系统/2017-2018-2成绩/2014级-2017-2018-2.xls
		String filePath = "E:/实验室/学生管理系统/2017-2018-2成绩/2014级-2017-2018-2.xls";
		InsertIntoDB insertIntoDB = new InsertIntoDB();
		insertIntoDB.Insert(filePath);
	}

	@Test
	public void rowSelectTest() {
		String filePath = "E:/实验室/学生管理系统/2017-2018-2成绩/2017级-2017-2018-2成绩.xls";
		File file = new File(filePath);
		try {
			InputStream input = new FileInputStream(file);
			Workbook readxls = Workbook.getWorkbook(input);
			Sheet readsheet = readxls.getSheet(0); // 表索引从0开始,取xls的第一张表
			int rsRows = readsheet.getRows(); // 获得表格的行数
			int startRow = 0;
			System.out.println(readsheet.getCell(0, startRow).getContents());
			while (!readsheet.getCell(0, startRow++).getContents().equals("学号")) {
			}
			System.out.println("startRow:" + startRow);
			System.out.println("本次导入记录总数：" + (rsRows - startRow));
		} catch (BiffException | IOException e) {
			e.printStackTrace();
		}
	}
}
