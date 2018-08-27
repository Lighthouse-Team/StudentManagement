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
import com.project.dto.BasicCourseClassDistribution;
import com.project.dto.BasicCourseDetailDistribution;
import com.project.dto.BasicCourseOverallDistribution;
import com.project.dto.ClassExcellentFailDistribution;
import com.project.dto.ClassFailDistribution;
import com.project.dto.DepartmentAllGradeAverageScoreCompare;
import com.project.dto.DepartmentAllGradeFailDistribution;
import com.project.dto.DepartmentDistribution;
import com.project.dto.DepartmentFailDistribution;
import com.project.dto.GradeAbsenceDistribution;
import com.project.dto.GradeFailDistribution;
import com.project.dto.OverallDistribution;
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
	 * 跳转至所有课程成绩分布情况页面
	 * @param session
	 * @param map
	 * @return
	 */
	@RequestMapping("/getAllCourseDistributedPgae")
	public String getAllCourseDistributedPgae(HttpSession session, Map<String, Object> map) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return "redirect:/login.jsp";
		} 
		
		List<String> yearList = getYearList();
		
		map.put("yearList", yearList);
		
		return "getAllCourseDistributed";
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
	 * 跳转页面至各年级必修专业选修课程成绩总体分布情况(按院系查询)页面
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
	 * 返回各年级必修专业选修课程成绩总体分布情况(按院系查询)
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
			@RequestParam(value = "term", required = false) Integer term) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return "redirect:/login.jsp";
		} 
		
		List<String> yearList = getYearList();
		map.put("yearList", yearList);
		if(year != null) {
			List<DepartmentDistribution> ddList = studentCourseService.getRPECDepartmentDistributionListByGrade(grade, year, term);
			map.put("ddList", ddList);
			
		}
		return "getDepartmentRPECScoreDistributionListByGrade";
	}
	
	/**
	 * 跳转页面至各年级必修专业选修课程成绩总体分布情况(按班级查询)页面
	 * @param session
	 * @param map
	 * @return
	 */
	@RequestMapping("/getRPECClassExcellentFailDistributionListByGradePage")
	public String getRPECClassExcellentFailDistributionListByGradePage(HttpSession session, Map<String, Object> map) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return "redirect:/login.jsp";
		} 
		List<String> yearList = getYearList();
		map.put("yearList", yearList);
		
		return "getRPECClassExcellentFailDistributionListByGrade";
	}
	
	/**
	 * 返回各年级必修专业选修课程成绩总体分布情况(按班级查询)
	 * @param session
	 * @param map
	 * @param year
	 * @param term
	 * @return
	 */
	@RequestMapping("/getRPECClassExcellentFailDistributionListByGrade")
	public String getRPECClassExcellentFailDistributionListByGrade(HttpSession session, Map<String, Object> map,
			@RequestParam(value = "grade", required = false) Integer grade,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "term", required = false) Integer term) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return "redirect:/login.jsp";
		} 
		
		List<String> yearList = getYearList();
		map.put("yearList", yearList);
		if(year != null) {
			List<ClassExcellentFailDistribution> cefdList = studentCourseService.getRPECClassExcellentFailDistributionListByGrade(grade, year, term);
			map.put("cefdList", cefdList);
			
		}
		return "getRPECClassExcellentFailDistributionListByGrade";
	}
	
	/**
	 * 跳转至各院系分年级成绩平均分比较页面
	 * @param session
	 * @param map
	 * @return
	 */
	@RequestMapping("/getRPECDepartmentAllGradeAverageScoreCompareListPage")
	public String getRPECDepartmentAllGradeAverageScoreCompareListPage(HttpSession session, Map<String, Object> map) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return "redirect:/login.jsp";
		} 
		List<String> yearList = getYearList();
		map.put("yearList", yearList);
		
		return "getRPECDepartmentAllGradeAverageScoreCompareList";
	}
	
	/**
	 * 返回各院系分年级成绩平均分比较情况
	 * @param session
	 * @param map
	 * @param year
	 * @param term
	 * @return
	 */
	@RequestMapping("/getRPECDepartmentAllGradeAverageScoreCompareList")
	public String getRPECDepartmentAllGradeAverageScoreCompareList(HttpSession session, Map<String, Object> map,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "term", required = false) Integer term) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return "redirect:/login.jsp";
		} 
		
		List<String> yearList = getYearList();
		map.put("yearList", yearList);
		if(year != null) {
			int yearSelected = Integer.parseInt(year.substring(0,4));
			List<String> gradeList = new ArrayList<>();
			for(int i=0; i<4; i++) {
				gradeList.add(String.valueOf(yearSelected - 3 + i) + "级");
			}
			map.put("gradeList", gradeList);
			
			List<DepartmentAllGradeAverageScoreCompare> dagascList = studentCourseService.getRPECDepartmentAllGradeAverageScoreCompareList(year, term);
			map.put("dagascList", dagascList);
			
		}
		return "getRPECDepartmentAllGradeAverageScoreCompareList";
	}
	
	/**
	 * 返回各院系分年级成绩平均分比较情况(画图)
	 * @param year
	 * @param term
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getRPECDepartmentAllGradeAverageScoreCompareListData")
	public List<DepartmentAllGradeAverageScoreCompare> sendRPECDepartmentAllGradeAverageScoreCompareListData(String year, Integer term){
		List<DepartmentAllGradeAverageScoreCompare> dagascList = studentCourseService.getRPECDepartmentAllGradeAverageScoreCompareList(year, term);
		return dagascList;
	}
	
	/**
	 * 根据学年返回4个年级(画图)
	 * @param year
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getGradeListData")
	public List<String> sendGradeList(String year){
		int yearSelected = Integer.parseInt(year.substring(0,4));
		List<String> gradeList = new ArrayList<>();
		for(int i=0; i<4; i++) {
			gradeList.add(String.valueOf(yearSelected - 3 + i) + "级");
		}
		return gradeList;
	}
	
	/**
	 * 跳转至全校本科生不及格整体情况统计页面
	 * @param session
	 * @param map
	 * @return
	 */
	@RequestMapping("/getRCGradeFailDistributionListPage")
	public String getRCGradeFailDistributionListPage(HttpSession session, Map<String, Object> map) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return "redirect:/login.jsp";
		} 
		List<String> yearList = getYearList();
		map.put("yearList", yearList);
		
		return "getRCGradeFailDistributionList";
	}
	
	/**
	 * 返回全校本科生不及格整体情况统计情况
	 * @param session
	 * @param map
	 * @param year
	 * @param term
	 * @return
	 */
	@RequestMapping("/getRCGradeFailDistributionList")
	public String getRCGradeFailDistributionList(HttpSession session, Map<String, Object> map,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "term", required = false) Integer term) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return "redirect:/login.jsp";
		} 
		
		List<String> yearList = getYearList();
		map.put("yearList", yearList);
		if(year != null) {
			List<GradeFailDistribution> gfdList = new ArrayList<>();
			gfdList = studentCourseService.getRCGradeFailDistributionList(year, term);
			map.put("gfdList", gfdList);
		}
		return "getRCGradeFailDistributionList";
	}
	
	/**
	 * 返回全校本科生不及格整体情况统计情况(画图)
	 * @param year
	 * @param term
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getRCGradeFailDistributionListData")
	public List<GradeFailDistribution> sendRCGradeFailDistributionListData(String year, Integer term){
		List<GradeFailDistribution> gfdList = new ArrayList<>();
		gfdList = studentCourseService.getRCGradeFailDistributionList(year, term);
		return gfdList;
	}
	
	/**
	 * 跳转至各院系整体学生不及格情况统计页面
	 * @param session
	 * @param map
	 * @return
	 */
	@RequestMapping("/getRCDepartmentFailDistributionListPage")
	public String getRCDepartmentFailDistributionListPage(HttpSession session, Map<String, Object> map) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return "redirect:/login.jsp";
		} 
		List<String> yearList = getYearList();
		map.put("yearList", yearList);
		
		return "getRCDepartmentFailDistributionList";
	}
	
	/**
	 * 返回各院系整体学生不及格情况
	 * @param session
	 * @param map
	 * @param year
	 * @param term
	 * @return
	 */
	@RequestMapping("/getRCDepartmentFailDistributionList")
	public String getRCDepartmentFailDistributionList(HttpSession session, Map<String, Object> map,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "term", required = false) Integer term) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return "redirect:/login.jsp";
		} 
		
		List<String> yearList = getYearList();
		map.put("yearList", yearList);
		if(year != null) {
			List<DepartmentFailDistribution> dfdList = studentCourseService.getRCDepartmentFailDistributionList(year, term);
			map.put("dfdList", dfdList);
		}
		return "getRCDepartmentFailDistributionList";
	}
	
	/**
	 * 返回各院系整体学生不及格情况(画图)
	 * @param year
	 * @param term
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getRCDepartmentFailDistributionListData")
	public List<DepartmentFailDistribution> sendRCDepartmentFailDistributionListData(String year, Integer term){
		List<DepartmentFailDistribution> dfdList = studentCourseService.getRCDepartmentFailDistributionList(year, term);
		return dfdList;
	}
	
	/**
	 * 跳转至各院系分年级学生不及格情况统计页面
	 * @param session
	 * @param map
	 * @return
	 */
	@RequestMapping("/getRCDepartmentAllGradeFailDistributionListPage")
	public String getRCDepartmentAllGradeFailDistributionListPage(HttpSession session, Map<String, Object> map) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return "redirect:/login.jsp";
		} 
		List<String> yearList = getYearList();
		map.put("yearList", yearList);
		
		return "getRCDepartmentAllGradeFailDistributionList";
	}
	
	/**
	 * 返回各院系分年级学生不及格情况
	 * @param session
	 * @param map
	 * @param year
	 * @param term
	 * @return
	 */
	@RequestMapping("/getRCDepartmentAllGradeFailDistributionList")
	public String getRCDepartmentAllGradeFailDistributionList(HttpSession session, Map<String, Object> map,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "term", required = false) Integer term) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return "redirect:/login.jsp";
		} 
		
		List<String> yearList = getYearList();
		map.put("yearList", yearList);
		if(year != null) {
			int yearSelected = Integer.parseInt(year.substring(0,4));
			List<String> gradeList = new ArrayList<>();
			for(int i=0; i<4; i++) {
				gradeList.add(String.valueOf(yearSelected - 3 + i) + "级");
			}
			map.put("gradeList", gradeList);
			
			List<DepartmentAllGradeFailDistribution> dagfdList = studentCourseService
					.getRCDepartmentAllGradeFailDistributionList(year, term);
			map.put("dagfdList", dagfdList);
			
		}
		return "getRCDepartmentAllGradeFailDistributionList";
	}
	
	/**
	 * 返回各院系分年级学生不及格情况(画图)
	 * @param year
	 * @param term
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getRCDepartmentAllGradeFailDistributionListData")
	public List<DepartmentAllGradeFailDistribution> sendRCDepartmentAllGradeFailDistributionData(String year, Integer term){
		List<DepartmentAllGradeFailDistribution> dagfdList = studentCourseService
				.getRCDepartmentAllGradeFailDistributionList(year, term);
		return dagfdList;
	}

	/**
	 * 跳转至各班级不及格情况统计页面
	 * @param session
	 * @param map
	 * @return
	 */
	@RequestMapping("/getRCClassFailDistributionListByGradePage")
	public String getRCClassFailDistributionListByGradePage(HttpSession session, Map<String, Object> map) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return "redirect:/login.jsp";
		} 
		List<String> yearList = getYearList();
		map.put("yearList", yearList);
		
		return "getRCClassFailDistributionListByGrade";
	}
	
	/**
	 * 返回各班级学生不及格情况
	 * @param session
	 * @param map
	 * @param year
	 * @param term
	 * @return
	 */
	@RequestMapping("/getRCClassFailDistributionListByGrade")
	public String getRCClassFailDistributionListByGrade(HttpSession session, Map<String, Object> map,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "term", required = false) Integer term,
			@RequestParam(value = "grade", required = false) Integer grade) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return "redirect:/login.jsp";
		} 
		
		List<String> yearList = getYearList();
		map.put("yearList", yearList);
		if(year != null) {
			
			List<ClassFailDistribution> cfdList = new ArrayList<>();
			cfdList = studentCourseService.getRCClassFailDistributionListByGrade(grade, year, term);
			map.put("cfdList", cfdList);
			
		}
		return "getRCClassFailDistributionListByGrade";
	}
	
	/**
	 * 跳转至各年级缺考情况统计页面
	 * @param session
	 * @param map
	 * @return
	 */
	@RequestMapping("/getGradeAbsenceDistributionListPage")
	public String getGradeAbsenceDistributionListPage(HttpSession session, Map<String, Object> map) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return "redirect:/login.jsp";
		} 
		List<String> yearList = getYearList();
		map.put("yearList", yearList);
		
		return "getGradeAbsenceDistributionList";
	}
	
	/**
	 * 返回各年级缺考情况统计情况
	 * @param session
	 * @param map
	 * @param year
	 * @param term
	 * @return
	 */
	@RequestMapping("/getGradeAbsenceDistributionList")
	public String getGradeAbsenceDistributionList(HttpSession session, Map<String, Object> map,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "term", required = false) Integer term) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return "redirect:/login.jsp";
		} 
		
		List<String> yearList = getYearList();
		map.put("yearList", yearList);
		if(year != null) {
			
			List<GradeAbsenceDistribution> gadList = new ArrayList<>();
			gadList = studentCourseService.getGradeAbsenceDistributionList(year, term);
			map.put("dagfdList", gadList);
			
		}
		return "getGradeAbsenceDistributionList";
	}
	
	/**
	 * 跳转至主要基础课程成绩基本情况统计页面
	 * @param session
	 * @param map
	 * @return
	 */
	@RequestMapping("/getBasicCourseOverallDistributionListPage")
	public String getBasicCourseOverallDistributionListPage(HttpSession session, Map<String, Object> map) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return "redirect:/login.jsp";
		} 
		List<String> yearList = getYearList();
		map.put("yearList", yearList);
		
		return "getBasicCourseOverallDistributionList";
	}
	
	/**
	 * 返回主要基础课程成绩基本情况
	 * @param session
	 * @param map
	 * @param year
	 * @param term
	 * @return
	 */
	@RequestMapping("/getBasicCourseOverallDistributionList")
	public String getBasicCourseOverallDistributionList(HttpSession session, Map<String, Object> map,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "term", required = false) Integer term) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return "redirect:/login.jsp";
		} 
		
		List<String> yearList = getYearList();
		map.put("yearList", yearList);
		if(year != null) {
			
			List<BasicCourseOverallDistribution> bcodList = new ArrayList<>();
			bcodList = studentCourseService.getBasicCourseOverallDistributionList(year, term);
			map.put("bcodList", bcodList);
			
		}
		return "getBasicCourseOverallDistributionList";
	}
	
	/**
	 * 返回各院系分年级学生不及格情况(画图)
	 * @param year
	 * @param term
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getBasicCourseOverallDistributionListData")
	public List<BasicCourseOverallDistribution> sendBasicCourseOverallDistributionListData(String year, Integer term){
		List<BasicCourseOverallDistribution> bcodList = new ArrayList<>();
		bcodList = studentCourseService.getBasicCourseOverallDistributionList(year, term);
		return bcodList;
	}
	
	/**
	 * 跳转至大一大二主要基础课程成绩基本情况统计页面
	 * @param session
	 * @param map
	 * @return
	 */
	@RequestMapping("/getBasicCourseDetailDistributionListByCourseNamePage")
	public String getBasicCourseDetailDistributionListByCourseNamePage(HttpSession session, Map<String, Object> map) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return "redirect:/login.jsp";
		} 
		List<String> yearList = getYearList();
		map.put("yearList", yearList);
		
		return "getBasicCourseDetailDistributionListByCourseName";
	}
	
	/**
	 * 返回大一大二主要基础课程成绩基本情况
	 * @param session
	 * @param map
	 * @param year
	 * @param term
	 * @return
	 */
	@RequestMapping("/getBasicCourseDetailDistributionListByCourseName")
	public String getBasicCourseDetailDistributionListByCourseName(HttpSession session, Map<String, Object> map,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "term", required = false) Integer term,
			@RequestParam(value = "courseName", required = false) String courseName) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return "redirect:/login.jsp";
		} 
		
		List<String> yearList = getYearList();
		map.put("yearList", yearList);
		if(year != null) {
			
			List<BasicCourseDetailDistribution> bcddList = new ArrayList<>();
			bcddList = studentCourseService.getBasicCourseDetailDistributionListByCourseName(courseName, year, term);
			map.put("bcddList", bcddList);
			
		}
		return "getBasicCourseDetailDistributionListByCourseName";
	}
	
	/**
	 * 返回大一大二主要基础课程成绩基本情况(画图)
	 * @param year
	 * @param term
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getBasicCourseDetailDistributionListByCourseNameData")
	public List<BasicCourseDetailDistribution> sendBasicCourseDetailDistributionListByCourseNameData(String year, Integer term , String courseName){
		List<BasicCourseDetailDistribution> bcddList = new ArrayList<>();
		bcddList = studentCourseService.getBasicCourseDetailDistributionListByCourseName(courseName, year, term);
		return bcddList;
	}
	
	/**
	 * 跳转至主要基础课程各班成绩情况统计页面
	 * @param session
	 * @param map
	 * @return
	 */
	@RequestMapping("/getBasicCourseClassDistributionListByCourseNamePage")
	public String getBasicCourseClassDistributionListByCourseNamePage(HttpSession session, Map<String, Object> map) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return "redirect:/login.jsp";
		} 
		List<String> yearList = getYearList();
		map.put("yearList", yearList);
		
		return "getBasicCourseClassDistributionListByCourseName";
	}
	
	/**
	 * 返回主要基础课程各班成绩基本情况
	 * @param session
	 * @param map
	 * @param year
	 * @param term
	 * @return
	 */
	@RequestMapping("/getBasicCourseClassDistributionListByCourseName")
	public String getBasicCourseClassDistributionListByCourseName(HttpSession session, Map<String, Object> map,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "term", required = false) Integer term,
			@RequestParam(value = "courseName", required = false) String courseName) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return "redirect:/login.jsp";
		} 
		
		List<String> yearList = getYearList();
		map.put("yearList", yearList);
		if(year != null) {
			
			List<BasicCourseClassDistribution> bccdList = new ArrayList<>();
			bccdList = studentCourseService.getBasicCourseClassDistributionListByCourseName(courseName, year, term);
			map.put("bccdList", bccdList);
			
		}
		return "getBasicCourseClassDistributionListByCourseName";
	}
	
	/**
	 * 跳转至打印准备页面
	 * @param session
	 * @param map
	 * @return
	 */
	@RequestMapping("/readyPrint")
	public String readyPrint(HttpSession session, Map<String, Object> map) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return "redirect:/login.jsp";
		} 
		
		List<String> yearList = getYearList();
		map.put("yearList", yearList);
		
		return "readyPrint";
	}
	
	/**
	 * 跳转至打印页面
	 */
	@RequestMapping("/print")
	public String print(HttpSession session, Map<String, Object> map,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "term", required = false) Integer term) {
		
		List<OverallDistribution> odList = studentCourseService.getACOverallDistributionList(year, term);
		map.put("odList", odList);  //第一章第一个功能
		
		List<OverallDistribution> odList1 = studentCourseService.getAGOverallDistributionList(year, term);
		map.put("odList1", odList1); //第一章第二个功能
		
		return "print";
	}
	
}
