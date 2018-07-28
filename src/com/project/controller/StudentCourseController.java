package com.project.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.beans.User;
import com.project.dto.OverallDistribution;
import com.project.dto.SCDistribution;
import com.project.service.StudentCourseService;

@Controller
public class StudentCourseController {
	
	@Autowired
	private StudentCourseService studentCourseService;

	@RequestMapping("/getAllCourseDistributed")
	public String getAllCourseDistributed(HttpSession session, Map<String, Object> map, @RequestParam(value="year", required=false) String year, 
			@RequestParam(value="term", required=false) Integer term) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return "redirect:/login.jsp";
		} 
		
		List<String> yearList = getYearList();
		
//		System.out.println("year: " + year);
//		System.out.println("term: " + term);
		map.put("yearList", yearList);
		
		if(year != null) {
			List<OverallDistribution> odList = studentCourseService.getUniversityACScoreDistributionList(year, term);
			map.put("odList", odList);
		}
		
		return "getAllCourseDistributed";
	}
	
	public List<String> getYearList() {
		DateFormat df = new SimpleDateFormat("yyyy");
		String yearNow = df.format(new Date());
		
		List<String> years = new ArrayList<>();
		
		int year = Integer.parseInt(yearNow) + 1;
		int range = year - 1999;
		for(int i=0; i<range; i++) {
			years.add(String.valueOf(year - i - 1) + "-" + String.valueOf(year - i));
		}
		
		return years;
	}
	
	@ResponseBody
	@RequestMapping("/getAllCourseDistributedData")
	public List<OverallDistribution> sendAllCourseDistributedData(String year, Integer term){
		List<OverallDistribution> odList = studentCourseService.getUniversityACScoreDistributionList(year, term);
		return odList;
	}
}
