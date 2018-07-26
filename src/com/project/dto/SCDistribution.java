package com.project.dto;

public class SCDistribution {
	
	private Integer scdId;
	private String grade; 
	private Integer recordNumber;
	private Integer aNumber;
	private Integer bNumber;
	private Integer cNumber;
	private Integer dNumber;
	private Integer eNumber;
	private double averageScore;
	private String aRatePercent;
	private String bRatePercent;
	private String cRatePercent;
	private String dRatePercent;
	private String eRatePercent;
	
	
	public SCDistribution() {
		super();
	}


	public SCDistribution(Integer scdId, String grade, Integer recordNumber, Integer aNumber, Integer bNumber,
			Integer cNumber, Integer dNumber, Integer eNumber, double averageScore, String aRatePercent,
			String bRatePercent, String cRatePercent, String dRatePercent, String eRatePercent) {
		super();
		this.scdId = scdId;
		this.grade = grade;
		this.recordNumber = recordNumber;
		this.aNumber = aNumber;
		this.bNumber = bNumber;
		this.cNumber = cNumber;
		this.dNumber = dNumber;
		this.eNumber = eNumber;
		this.averageScore = averageScore;
		this.aRatePercent = aRatePercent;
		this.bRatePercent = bRatePercent;
		this.cRatePercent = cRatePercent;
		this.dRatePercent = dRatePercent;
		this.eRatePercent = eRatePercent;
	}


	public Integer getScdId() {
		return scdId;
	}


	public void setScdId(Integer scdId) {
		this.scdId = scdId;
	}


	public String getGrade() {
		return grade;
	}


	public void setGrade(String grade) {
		this.grade = grade;
	}


	public Integer getRecordNumber() {
		return recordNumber;
	}


	public void setRecordNumber(Integer recordNumber) {
		this.recordNumber = recordNumber;
	}


	public Integer getaNumber() {
		return aNumber;
	}


	public void setaNumber(Integer aNumber) {
		this.aNumber = aNumber;
	}


	public Integer getbNumber() {
		return bNumber;
	}


	public void setbNumber(Integer bNumber) {
		this.bNumber = bNumber;
	}


	public Integer getcNumber() {
		return cNumber;
	}


	public void setcNumber(Integer cNumber) {
		this.cNumber = cNumber;
	}


	public Integer getdNumber() {
		return dNumber;
	}


	public void setdNumber(Integer dNumber) {
		this.dNumber = dNumber;
	}


	public Integer geteNumber() {
		return eNumber;
	}


	public void seteNumber(Integer eNumber) {
		this.eNumber = eNumber;
	}


	public double getAverageScore() {
		return averageScore;
	}


	public void setAverageScore(double averageScore) {
		this.averageScore = averageScore;
	}


	public String getaRatePercent() {
		return aRatePercent;
	}


	public void setaRatePercent(String aRatePercent) {
		this.aRatePercent = aRatePercent;
	}


	public String getbRatePercent() {
		return bRatePercent;
	}


	public void setbRatePercent(String bRatePercent) {
		this.bRatePercent = bRatePercent;
	}


	public String getcRatePercent() {
		return cRatePercent;
	}


	public void setcRatePercent(String cRatePercent) {
		this.cRatePercent = cRatePercent;
	}


	public String getdRatePercent() {
		return dRatePercent;
	}


	public void setdRatePercent(String dRatePercent) {
		this.dRatePercent = dRatePercent;
	}


	public String geteRatePercent() {
		return eRatePercent;
	}


	public void seteRatePercent(String eRatePercent) {
		this.eRatePercent = eRatePercent;
	}


	@Override
	public String toString() {
		return "SCDistribution [scdId=" + scdId + ", grade=" + grade + ", recordNumber=" + recordNumber + ", aNumber="
				+ aNumber + ", bNumber=" + bNumber + ", cNumber=" + cNumber + ", dNumber=" + dNumber + ", eNumber="
				+ eNumber + ", averageScore=" + averageScore + ", aRatePercent=" + aRatePercent + ", bRatePercent="
				+ bRatePercent + ", cRatePercent=" + cRatePercent + ", dRatePercent=" + dRatePercent + ", eRatePercent="
				+ eRatePercent + "]";
	}
	
	
	
	
}
