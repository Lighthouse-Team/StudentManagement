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
		// 2017级.xls,38650条记录,2585秒
		// 2016级.xls,63761条记录,3569秒
		// 2015级.xls,52450条记录,633秒
		// 2014级.xls,18101条记录,284秒
		
		// 2017级-2017-2018-2成绩.xls,55186条记录,936秒
		// 2016级-2017-2018-2成绩.xls,51194条记录,827秒
		// 2015级-2017-2018-2.xls,42952条记录,2689秒
		// 2014级-2017-2018-2.xls,7083条记录,200秒
		String filePath = "E:/实验室/学生管理系统/2017-2018-1成绩/2017级.xls";
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
			int startRow = 0; // 识别有效数据开始的行号
			while (!readsheet.getCell(0, startRow++).getContents().equals("学号")) {
			}
			System.out.println("startRow:" + startRow);
			System.out.println("本次导入记录总数：" + (rsRows - startRow));
		} catch (BiffException | IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getExamTermByExcelTest() {
		String filePath = "E:/实验室/学生管理系统/2017-2018-1成绩/2018级.xls";
		String examTerm = new InsertIntoDB().getExamTermByFilePath(filePath);
		System.out.println("examTerm:" + examTerm);
	}
	
	@Test
	public void getProjectPathTest() {
		String projectPath = System.getProperty("user.dir");
		System.out.println("projectPath:" + projectPath);
		projectPath = projectPath.replace("\\", "/");
		System.out.println("projectPath:" + projectPath);
	}
}
