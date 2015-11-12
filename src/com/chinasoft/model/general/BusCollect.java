package com.chinasoft.model.general;

import java.util.Date;
//收藏
public class BusCollect {
	private int collectId;
	private int userId;
	private Date createTime;
	private String collectType;
	private int relId;
	
	
	public int getCollectId() {
		return collectId;
	}
	public void setCollectId(int collectId) {
		this.collectId = collectId;
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
	public String getCollectType() {
		return collectType;
	}
	public void setCollectType(String collectType) {
		this.collectType = collectType;
	}
	public int getRelId() {
		return relId;
	}
	public void setRelId(int relId) {
		this.relId = relId;
	}
	
	
}
