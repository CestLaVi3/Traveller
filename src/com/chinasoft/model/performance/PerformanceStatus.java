package com.chinasoft.model.performance;

import java.util.Date;

public class PerformanceStatus {
	private int id;
	private int opener;
	private String status;
	private int year;
	private String month;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOpener() {
		return opener;
	}
	public void setOpener(int opener) {
		this.opener = opener;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}

}
