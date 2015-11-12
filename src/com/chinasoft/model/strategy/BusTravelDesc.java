package com.chinasoft.model.strategy;

import java.util.Date;

public class BusTravelDesc {
	private int travelDescId;
	private int spotId;
	private int userId;
	private Date createTime;
	private String descTitle;
	private String descContent;
	private String images;
	private int collectNum;
	private int praiseNum;
	private int investNum;
	private int commentNum;
	private String imageUrl;
	private String spotName;
	private String petName;
	private int count;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
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
	public String getSpotName() {
		return spotName;
	}
	public void setSpotName(String spotName) {
		this.spotName = spotName;
	}
	public int getTravelDescId() {
		return travelDescId;
	}
	public void setTravelDescId(int travelDescId) {
		this.travelDescId = travelDescId;
	}
	public int getSpotId() {
		return spotId;
	}
	public void setSpotId(int spotId) {
		this.spotId = spotId;
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
	public String getDescTitle() {
		return descTitle;
	}
	public void setDescTitle(String descTitle) {
		this.descTitle = descTitle;
	}
	public String getDescContent() {
		return descContent;
	}
	public void setDescContent(String descContent) {
		this.descContent = descContent;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public int getCollectNum() {
		return collectNum;
	}
	public void setCollectNum(int collectNum) {
		this.collectNum = collectNum;
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
	public int getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	
	
}
