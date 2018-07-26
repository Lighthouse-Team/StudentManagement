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

import com.project.beans.User;
import com.project.dto.SCDistribution;
import com.project.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// yang: 07-04 14:39
	@RequestMapping("/userLoginPage")
	public String userLoginPage(HttpSession session) {
		session.removeAttribute("user");
		
		return "redirect:/login.jsp";
	}
	
	@RequestMapping("/userLogin")
	public String userLogin(HttpSession session, @RequestParam("userName") String userName, @RequestParam("passWord") String passWord) {
		User user = userService.getUserByUserNameAndPassWord(userName, passWord);
		if(user == null) {
			// 无当前用户, 跳转至失败页面
			return "error";
		} 
		else {
			// 有当前用户，跳转至主界面
			session.setAttribute("user", user);
			session.setAttribute("permission", user.getPermission());
			
			return "index";
		}
	}

/*	@RequestMapping("/getAllCourseDistributed")
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
			List<SCDistribution> scDistributionList = userService.getSCDistributionListByYear(year, term);
			map.put("scDistributionList", scDistributionList);
		}
		
		return "getAllCourseDistributed";
	}*/
	
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
	
	// end yang
	
}
