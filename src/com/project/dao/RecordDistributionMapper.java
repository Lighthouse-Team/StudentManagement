package com.project.dao;

import org.apache.ibatis.annotations.Param;

public interface RecordDistributionMapper {

	/**
	 * 通过年级查询成绩记录总数
	 * @param grade
	 * @return
	 */
	public Integer getTotalNumberByGrade(@Param("grade") String grade);
	
}
