package com.project.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Testpoi {

	//转换数据格式
    private static String getValue(XSSFCell xssfRow) {
        if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfRow.getBooleanCellValue());
        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
            return String.valueOf(xssfRow.getNumericCellValue());
        } else {
            return String.valueOf(xssfRow.getStringCellValue());
        }
    }
	
	public static void main(String[] args) {
		try {
			InputStream is = new FileInputStream("D:/hello.xlsx");
			@SuppressWarnings("resource")
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
			// 获取每一个工作薄
			for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
			    XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
			    if (xssfSheet == null) {
			        continue;
			    }
			    // 获取当前工作薄的每一行
			    for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
			        XSSFRow xssfRow = xssfSheet.getRow(rowNum);
			        if (xssfRow != null) {
			            XSSFCell one = xssfRow.getCell(0);
			            //读取第一列数据
			            XSSFCell two = xssfRow.getCell(1);
			            //读取第二列数据
			            XSSFCell three = xssfRow.getCell(2);
			            //读取第三列数据
			            //需要转换数据的话直接调用getValue获取字符串
//			            System.out.println(one + " , " + two + " , " + three);
			            System.out.println(one.toString());
			        }
			    }
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}