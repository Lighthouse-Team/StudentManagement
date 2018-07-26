package com.project.test;

import org.junit.Test;

import com.project.tools.InsertIntoDB;

public class TestInsertIntoDB {

	@Test
	public void insertIntoDBTest() {
		InsertIntoDB insertIntoDB = new InsertIntoDB();
		insertIntoDB.Insert();
	}
}
