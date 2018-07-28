package com.project.service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.beans.Course;
import com.project.beans.Student;
import com.project.beans.StudentCourse;
import com.project.beans.User;
import com.project.dao.StudentCourseMapper;
import com.project.dao.StudentMapper;
import com.project.dao.UserMapper;
import com.project.dto.SCDistribution;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private StudentMapper studentMapper;

	@Autowired
	private StudentCourseMapper studentCourseMapper;

	
	public User getUserByUserName(String userName) {
		return userMapper.getUserByUserName(userName);
	}

	public User getUserByNickName(String nickName) {
		return userMapper.getUserByNickName(nickName);
	}

	public User getUserByUserNameAndPassWord(String userName, String passWord) {
		return userMapper.getUserByUserNameAndPassWord(userName, passWord);
	}

	public List<User> getAllUsers() {
		return userMapper.getUserAllUsers();
	}

	public Integer addUser(User user) {
		userMapper.addUser(user);
		return user.getUserId();
	}

	// 编辑除密码外的所有用户信息
	public void useEdit(User user) {
		userMapper.updateUser(user);
	}

	/**
	 * shao 2018-7-13 20:09
	 */

	/**
	 * 获取四个年级以及全年的成绩信息
	 * 
	 * @param year
	 * @param term
	 * @return List<SCDistribution>
	 */
	public List<SCDistribution> getSCDistributionListByYear(String year, Integer term) {
		List<SCDistribution> resultList = new ArrayList<>();
		SCDistribution scDistribution = null;
		SCDistribution allScDistribution = null;
		String grade = null;

		// 截取 year 的前4个字符
		String strYear = year.substring(0, 4);

		// 转化为整数年
		Integer curYear = Integer.parseInt(strYear);
		Integer beginYear = curYear - 3;

		for (int i = beginYear; i <= curYear; i++) {
			grade = String.valueOf(i);
			scDistribution = getSCDistribution(grade, term);
			resultList.add(scDistribution);
		}

		allScDistribution = getAllSCDistribution(grade, term);

		resultList.add(allScDistribution);

		return resultList;
	}

	/**
	 * 获取单个年级的成绩信息
	 * 
	 * @param grade
	 * @param term
	 * @return
	 */
	public SCDistribution getSCDistribution(String grade, Integer term) {
		SCDistribution scDistribution = new SCDistribution();
		Integer recordNumber = 0;
		Integer aNumber = 0;
		Integer bNumber = 0;
		Integer cNumber = 0;
		Integer dNumber = 0;
		Integer eNumber = 0;
		double scoreSum = 0;
		double courseCredits = 0;

		Student student = new Student();
		Course course = new Course();
		StudentCourse studentCourse = new StudentCourse();
		StudentCourse studentCourseResult = new StudentCourse();
		student.setStudentClass(grade);
		List<Student> studentList = studentMapper.getStudentDetailsByEntityForLike(student); // 遍历学生列表得到学生选课信息表
		for (int i = 0; i < studentList.size(); i++) {
			student = studentList.get(i);
			List<Course> courseList = new ArrayList<Course>();
			courseList = student.getCourseList();
			for (int j = 0; j < courseList.size(); j++) {
				course = new Course();
				course = courseList.get(j);
				if (course.getCourseTerm() == term) {
					studentCourse.setScStudent(student);
					studentCourse.setScCourse(course);
					studentCourse.setExamProperty(1);
					studentCourse.setScoreMark(1);
					studentCourseResult = studentCourseMapper.getStudentCourseByEntity(studentCourse);
					if (studentCourseResult != null) {
						recordNumber = recordNumber + 1;
						System.out.println(recordNumber);
						if (studentCourseResult.getScore() >= 90 && studentCourseResult.getScore() <= 100) {
							aNumber = aNumber + 1;
						} else if (studentCourseResult.getScore() >= 80 && studentCourseResult.getScore() < 90) {
							bNumber = bNumber + 1;
						} else if (studentCourseResult.getScore() >= 70 && studentCourseResult.getScore() < 80) {
							cNumber = cNumber + 1;
						} else if (studentCourseResult.getScore() >= 60 && studentCourseResult.getScore() < 70) {
							dNumber = dNumber + 1;
						} else {
							eNumber = eNumber + 1;
						}
						courseCredits = courseCredits + course.getCourseCredits();
						scoreSum = scoreSum + course.getCourseCredits() * studentCourseResult.getScore();
					}
				}
			}

		}

		double averageScore = scoreSum / courseCredits;

		NumberFormat nt = NumberFormat.getPercentInstance();// 获取格式化对象
		nt.setMinimumFractionDigits(2);// 设置百分数精确度2即保留两位小数

		double aRate = (double) aNumber / recordNumber;
		double bRate = (double) bNumber / recordNumber;
		double cRate = (double) cNumber / recordNumber;
		double dRate = (double) dNumber / recordNumber;
		double eRate = (double) eNumber / recordNumber;

		String aRatePercent = nt.format(aRate);
		String bRatePercent = nt.format(bRate);
		String cRatePercent = nt.format(cRate);
		String dRatePercent = nt.format(dRate);
		String eRatePercent = nt.format(eRate);

		scDistribution.setGrade(grade);
		scDistribution.setRecordNumber(recordNumber);
		scDistribution.setaNumber(aNumber);
		scDistribution.setbNumber(bNumber);
		scDistribution.setcNumber(cNumber);
		scDistribution.setdNumber(dNumber);
		scDistribution.seteNumber(eNumber);
		scDistribution.setAverageScore(averageScore);
		scDistribution.setaRatePercent(aRatePercent);
		scDistribution.setbRatePercent(bRatePercent);
		scDistribution.setcRatePercent(cRatePercent);
		scDistribution.setdRatePercent(dRatePercent);
		scDistribution.seteRatePercent(eRatePercent);
		return scDistribution;
	}

	/**
	 * 获取全年的成绩信息
	 * 
	 * @param grade
	 * @param term
	 * @return
	 */
	public SCDistribution getAllSCDistribution(String grade, Integer term) {
		SCDistribution scDistribution = new SCDistribution();
		Integer allRecordNumber = 0;
		Integer allANumber = 0;
		Integer allBNumber = 0;
		Integer allCNumber = 0;
		Integer allDNumber = 0;
		Integer allENumber = 0;
		double scoreSum = 0;
		double courseCredits = 0;

		List<Student> allStudentList = new ArrayList<>();
		Student student = new Student();
		Course course = new Course();
		StudentCourse studentCourse = new StudentCourse();
		StudentCourse studentCourseResult = new StudentCourse();
		Integer curYear = Integer.parseInt(grade);
		Integer beginYear = curYear - 3;
		for (int i = beginYear; i <= curYear; i++) {
			grade = String.valueOf(i);
			student.setStudentClass(grade);
			List<Student> studentList = studentMapper.getStudentDetailsByEntityForLike(student); // 遍历学生列表得到学生选课信息表
			allStudentList.addAll(studentList);
			for (int j = 0; j < allStudentList.size(); j++) {
				student = allStudentList.get(j);
				List<Course> courseList = new ArrayList<Course>();
				courseList = student.getCourseList();
				for (int k = 0; k < courseList.size(); k++) {
					course = courseList.get(k);
					if (course.getCourseTerm() == term) {
						studentCourse.setScStudent(student);
						studentCourse.setScCourse(course);
						studentCourse.setExamProperty(1);
						studentCourseResult = studentCourseMapper.getStudentCourseByEntity(studentCourse);
						allRecordNumber = allRecordNumber + 1;
						if (studentCourseResult.getScore() >= 90 && studentCourseResult.getScore() <= 100) {
							allANumber = allANumber + 1;
						} else if (studentCourseResult.getScore() >= 80 && studentCourseResult.getScore() < 90) {
							allBNumber = allBNumber + 1;
						} else if (studentCourseResult.getScore() >= 70 && studentCourseResult.getScore() < 80) {
							allCNumber = allCNumber + 1;
						} else if (studentCourseResult.getScore() >= 60 && studentCourseResult.getScore() < 70) {
							allDNumber = allDNumber + 1;
						} else {
							allENumber = allENumber + 1;
						}
						courseCredits = courseCredits + course.getCourseCredits();
						scoreSum = scoreSum + course.getCourseCredits() * studentCourseResult.getScore();
					}
				}

				NumberFormat nt = NumberFormat.getPercentInstance();// 获取格式化对象
				nt.setMinimumFractionDigits(2);// 设置百分数精确度2即保留两位小数
				double allARate = (double) allANumber / allRecordNumber;
				double allBRate = (double) allBNumber / allRecordNumber;
				double allCRate = (double) allCNumber / allRecordNumber;
				double allDRate = (double) allDNumber / allRecordNumber;
				double allERate = (double) allENumber / allRecordNumber;
				String allARatePercent = nt.format(allARate);
				String allBRatePercent = nt.format(allBRate);
				String allCRatePercent = nt.format(allCRate);
				String allDRatePercent = nt.format(allDRate);
				String allERatePercent = nt.format(allERate);

				double allAverageScore = scoreSum / courseCredits;

				scDistribution.setGrade("全年");
				scDistribution.setRecordNumber(allRecordNumber);
				scDistribution.setAverageScore(allAverageScore);
				scDistribution.setaNumber(allANumber);
				scDistribution.setbNumber(allBNumber);
				scDistribution.setcNumber(allCNumber);
				scDistribution.setdNumber(allDNumber);
				scDistribution.seteNumber(allENumber);
				scDistribution.setaRatePercent(allARatePercent);
				scDistribution.setbRatePercent(allBRatePercent);
				scDistribution.setcRatePercent(allCRatePercent);
				scDistribution.setdRatePercent(allDRatePercent);
				scDistribution.seteRatePercent(allERatePercent);
			}
		}

		return scDistribution;
	}
	
}
