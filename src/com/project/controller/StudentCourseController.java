package com.project.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.poi.util.SystemOutLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.beans.User;
import com.project.dto.ClassExcellentFailDistribution;
import com.project.dto.DepartmentDistribution;
import com.project.dto.OverallDistribution;
import com.project.dto.SCDistribution;
import com.project.service.StudentCourseService;

@Controller
public class StudentCourseController {
	
	@Autowired
	private StudentCourseService studentCourseService;

	//获取年份列表
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
	


	/**
	 * 返回所有课程成绩分布情况
	 * @param session
	 * @param map
	 * @param year
	 * @param term
	 * @return
	 */
	@RequestMapping("/getAllCourseDistributed")
	public String getAllCourseDistributed(HttpSession session, Map<String, Object> map, @RequestParam(value="year", required=false) String year, 
			@RequestParam(value="term", required=false) Integer term) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return "redirect:/login.jsp";
		} 
		
		List<String> yearList = getYearList();
		
		map.put("yearList", yearList);
		
		if(year != null) {
			List<OverallDistribution> odList = studentCourseService.getACOverallDistributionList(year, term);
			map.put("odList", odList);
		}
		
		return "getAllCourseDistributed";
	}
	
	/**
	 * 返回所有课程成绩分布情况(画图)
	 * @param year
	 * @param term
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAllCourseDistributedData")
	public List<OverallDistribution> sendAllCourseDistributedData(String year, Integer term){
		List<OverallDistribution> odList = studentCourseService.getACOverallDistributionList(year, term);
		return odList;
	}
	
	/**
	 * 跳转页面至各类课程成绩分布情况页面
	 * @param session
	 * @param map
	 * @return
	 */
	@RequestMapping("/getVariousCourseDistributedPage")
	public String getVariousCourseDistributedPage(HttpSession session, Map<String, Object> map) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return "redirect:/login.jsp";
		} 
		List<String> yearList = getYearList();
		map.put("yearList", yearList);
		
		return "getVariousCourseDistributed";
	}
	
	/**
	 * 返回分类课程成绩分布情况
	 * @param session
	 * @param map
	 * @param year
	 * @param term
	 * @return
	 */
	@RequestMapping("/getVariousCourseDistributed")
	public String getVariousCourseDistributed(HttpSession session, Map<String, Object> map,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "term", required = false) Integer term) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return "redirect:/login.jsp";
		} 
		
		List<String> yearList = getYearList();
		map.put("yearList", yearList);
		if(year != null) {
			List<OverallDistribution> odList = studentCourseService.getAGOverallDistributionList(year, term);
			map.put("odList", odList);
		}
		return "getVariousCourseDistributed";
	}
	
	/**
	 * 返回分类课程成绩分布情况(画图)
	 * @param year
	 * @param term
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getVariousCourseDistributedData")
	public List<OverallDistribution> sendVariousCourseDistributedData(String year, Integer term){
		List<OverallDistribution> odList = studentCourseService.getAGOverallDistributionList(year, term);
		return odList;
	}
	
	
	/**
	 * 跳转页面至全校必修专业选修课程成绩总体分布情况页面
	 * @param session
	 * @param map
	 * @return
	 */
	@RequestMapping("/getUniversityRPECScoreDistributedPage")
	public String getUniversityRPECScoreDistributedPage(HttpSession session, Map<String, Object> map) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return "redirect:/login.jsp";
		} 
		List<String> yearList = getYearList();
		map.put("yearList", yearList);
		
		return "getUniversityRPECScoreDistributed";
	}
	
	
	/**
	 * 返回全校必修专业选修课程成绩总体分布情况
	 * @param session
	 * @param map
	 * @param year
	 * @param term
	 * @return
	 */
	@RequestMapping("/getUniversityRPECScoreDistributed")
	public String getUniversityRPECScoreDistributed(HttpSession session, Map<String, Object> map,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "term", required = false) Integer term) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return "redirect:/login.jsp";
		} 
		
		List<String> yearList = getYearList();
		map.put("yearList", yearList);
		if(year != null) {
			List<OverallDistribution> odList = studentCourseService.getRPECOverallDistributionList(year, term);
			map.put("odList", odList);
		}
		return "getUniversityRPECScoreDistributed";
	}
	
	
	/**
	 * 返回全校必修专业选修课程成绩总体分布情况(画图)
	 * @param year
	 * @param term
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUniversityRPECScoreDistributedData")
	public List<OverallDistribution> sendUniversityRPECScoreDistributedData(String year, Integer term){
		List<OverallDistribution> odList = studentCourseService.getRPECOverallDistributionList(year, term);
		return odList;
	}
	
	/**
	 * 跳转页面至各年级必修专业选修课程成绩总体分布情况页面
	 * @param session
	 * @param map
	 * @return
	 */
	@RequestMapping("/getDepartmentRPECScoreDistributionListByGradePage")
	public String getDepartmentRPECScoreDistributionListByGradePage(HttpSession session, Map<String, Object> map) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return "redirect:/login.jsp";
		} 
		List<String> yearList = getYearList();
		map.put("yearList", yearList);
		
		return "getDepartmentRPECScoreDistributionListByGrade";
	}
	
	/**
	 * 返回各年级必修专业选修课程成绩总体分布情况
	 * @param session
	 * @param map
	 * @param year
	 * @param term
	 * @return
	 */
	@RequestMapping("/getDepartmentRPECScoreDistributionListByGrade")
	public String getDepartmentRPECScoreDistributionListByGrade(HttpSession session, Map<String, Object> map,
			@RequestParam(value = "grade", required = false) Integer grade,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "term", required = false) Integer term,
			@RequestParam(value = "submitMethod", required = false) String submitMethod) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return "redirect:/login.jsp";
		} 
		
		List<String> yearList = getYearList();
		map.put("yearList", yearList);
	/*	System.out.println(year);
		System.out.println(term);
		System.out.println(submitMethod);*/
		if(year != null) {
			if (submitMethod.equals("按院系查询")) {
				List<DepartmentDistribution> ddList = studentCourseService.getRPECDepartmentDistributionListByGrade(grade, year, term);
				map.put("ddList", ddList);
			}
			else {
				List<ClassExcellentFailDistribution> cefdList = studentCourseService.getRPECClassExcellentFailDistributionListByGrade(grade, year, term);
				map.put("cefdList", cefdList);
			}
		}
		return "getDepartmentRPECScoreDistributionListByGrade";
	}
}
