package com.project.dto;

import java.io.Serializable;

public class ClassExcellentFailDistribution implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1983569788932187142L;
	
	private String classNumber;				// 班级编号
	private Integer totalNumber;			// 成绩记录总数
	private Integer excellentNumber;		// 优秀成绩数
	private Integer failNumber;				// 不及格成绩数
	private String excellentRate;			// 优秀率
	private String failRate;				// 不及格率
	
	public ClassExcellentFailDistribution() {
		super();
	}
	
	public void InitValue() {
		totalNumber = 0;
		excellentNumber = 0;
		failNumber = 0;
		excellentRate = "0";
		failRate = "0";
	}

	public String getClassNumber() {
		return classNumber;
	}

	public void setClassNumber(String classNumber) {
		this.classNumber = classNumber;
	}

	public Integer getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}

	public Integer getExcellentNumber() {
		return excellentNumber;
	}

	public void setExcellentNumber(Integer excellentNumber) {
		this.excellentNumber = excellentNumber;
	}

	public Integer getFailNumber() {
		return failNumber;
	}

	public void setFailNumber(Integer failNumber) {
		this.failNumber = failNumber;
	}

	public String getExcellentRate() {
		return excellentRate;
	}

	public void setExcellentRate(String excellentRate) {
		this.excellentRate = excellentRate;
	}

	public String getFailRate() {
		return failRate;
	}

	public void setFailRate(String failRate) {
		this.failRate = failRate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ClassExcellentFailDistribution [classNumber=" + classNumber + ", totalNumber=" + totalNumber
				+ ", excellentNumber=" + excellentNumber + ", failNumber=" + failNumber + ", excellentRate="
				+ excellentRate + ", failRate=" + failRate + "]";
	}

	
}
