package com.chinasoft.model.activity;

import java.util.Date;

public class BusActiveJoin {
    private int activeJoinId;
    private Date createDate;
    private int userId;
    private int activeId;
    private String joinName;
    private String joinDesc;
    private String phoneNum;
    private String images;
    private int activeType;
    private String petName;
    private String imageUrl;
    private int praiseNum;
    private int investNum;
    private String activeTypeName;
    private int count;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getActiveTypeName() {
		return activeTypeName;
	}
	public void setActiveTypeName(String activeTypeName) {
		this.activeTypeName = activeTypeName;
	}
	public int getActiveType() {
		return activeType;
	}
	public void setActiveType(int activeType) {
		this.activeType = activeType;
	}
	public String getPetName() {
		return petName;
	}
	public void setPetName(String petName) {
		this.petName = petName;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public int getPraiseNum() {
		return praiseNum;
	}
	public void setPraiseNum(int praiseNum) {
		this.praiseNum = praiseNum;
	}
	public int getInvestNum() {
		return investNum;
	}
	public void setInvestNum(int investNum) {
		this.investNum = investNum;
	}
	public int getActiveJoinId() {
		return activeJoinId;
	}
	public void setActiveJoinId(int activeJoinId) {
		this.activeJoinId = activeJoinId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getActiveId() {
		return activeId;
	}
	public void setActiveId(int activeId) {
		this.activeId = activeId;
	}
	public String getJoinName() {
		return joinName;
	}
	public void setJoinName(String joinName) {
		this.joinName = joinName;
	}
	public String getJoinDesc() {
		return joinDesc;
	}
	public void setJoinDesc(String joinDesc) {
		this.joinDesc = joinDesc;
	}
	
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
    
}
