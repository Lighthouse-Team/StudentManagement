package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.beans.Course;
import com.project.dao.CourseMapper;

@Service
public class CourseService {
	@Autowired
	private CourseMapper courseMapper;

	/**
	 * 通过 courseId 获取指定课程
	 * 
	 * @param courseId
	 * @return
	 */
	public Course getCourseByCourseId(Integer courseId) {
		return courseMapper.getCourseByCourseId(courseId);
	}

	/**
	 * 通过 courseNumber 获取指定课程信息
	 * 
	 * @param courseNumber
	 * @return
	 */
	public Course getCourseByCourseNumber(String courseNumber) {
		return courseMapper.getCourseByCourseNumber(courseNumber);
	}

	/**
	 * 添加学生信息并返回主键值
	 * 
	 * @param course
	 * @return
	 */
	public void insertCourse(Course course) {
		courseMapper.addCourse(course);
		// course = getcourseByEntity(course); // 注释了节省导入数据库的时间
		// if (course != null) {
		// return course.getCourseId();
		// } else {
		// return -1; // 插入异常
		// }
	}

	/**
	 * 通过 course 属性查询课程信息
	 * 
	 * @param course
	 * @return
	 */
	public Course getcourseByEntity(Course course) {
		return courseMapper.getCourseByEntity(course);
	}

	/**
	 * 通过 course 属性模糊查询课程信息
	 * 
	 * @param course
	 * @return
	 */
	public List<Course> getCourseListByEntityForLike(Course course) {
		return courseMapper.getCourseListByEntityForLike(course);
	}

	/**
	 * 通过 course 属性查询学生选课信息
	 * 
	 * @param course
	 * @return
	 */
	public List<Course> getCourseDetailsByEntityForLike(Course course) {
		return courseMapper.getCourseDetailsByEntityForLike(course);
	}

	/**
	 * 设置 courseType
	 */
	public void setCourseType() {
		courseMapper.setCourseTypeOfRequierdCourse();
		courseMapper.setCourseTypeOfProfessionalElectiveCourse();
		courseMapper.setCourseTypeOfGeneralElectiveCourse();
	}

}
