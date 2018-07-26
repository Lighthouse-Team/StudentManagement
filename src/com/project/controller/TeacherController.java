package com.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TeacherController {
	
	@ResponseBody
	@RequestMapping("/testGetData")
	public List<String> validateUserName() {
		List<String> resultList = new ArrayList<>();
		
		resultList.add("1");
		resultList.add("2");
		resultList.add("3");
		resultList.add("4");
		resultList.add("5");
		resultList.add("6");
		resultList.add("7");
		
		//return GetJson(resultList);
		return resultList;
	}
}
	
