package com.chinasoft.model.activity;

import java.util.Date;

public class BusActiveInform {
	private int informId;
	private int activeId;
	private Date createDate;
	private String informContent;
	private int userId;
	private String activeName;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getActiveName() {
		return activeName;
	}
	public void setActiveName(String activeName) {
		this.activeName = activeName;
	}
	public int getInformId() {
		return informId;
	}
	public void setInformId(int informId) {
		this.informId = informId;
	}
	public int getActiveId() {
		return activeId;
	}
	public void setActiveId(int activeId) {
		this.activeId = activeId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getInformContent() {
		return informContent;
	}
	public void setInformContent(String informContent) {
		this.informContent = informContent;
	}
	
}
