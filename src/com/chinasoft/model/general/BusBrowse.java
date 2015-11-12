package com.chinasoft.model.general;

import java.util.Date;
//浏览
public class BusBrowse {
	private int browseId;
	private int userId;
	private Date createTime;
	private String browseType;
	private int relId;
	
	public int getBrowseId() {
		return browseId;
	}
	public void setBrowseId(int browseId) {
		this.browseId = browseId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getBrowseType() {
		return browseType;
	}
	public void setBrowseType(String browseType) {
		this.browseType = browseType;
	}

	public int getRelId() {
		return relId;
	}
	public void setRelId(int relId) {
		this.relId = relId;
	}
	
}
