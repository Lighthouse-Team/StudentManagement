package com.project.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel {

	public static void main(String[] args) {
		File file = new File("D:\\hello.xls");

		Workbook readxls;
		try {
			InputStream input = new FileInputStream(file);
			readxls = Workbook.getWorkbook(input);
			Sheet readsheet = readxls.getSheet(0); // 表索引从0开始

			int rsRows = readsheet.getRows();
			int rsColumns = readsheet.getColumns();

			for (int i = 0; i < rsRows; i++) {
				for (int j = 0; j < rsColumns; j++) {
					Cell cell = readsheet.getCell(j, i);
					String str = cell.getContents();
					System.out.println(str);
				}
			}
		} catch (BiffException | IOException e) {
			e.printStackTrace();
		}

	}

}
