package com.chinasoft.model.strategy;

import java.util.Date;

public class BusStagtegy {
	private int stagtegyId;	
	private int stagtegyType;
	private int spotId;
	private int userId;
	private int travelDays;
	private Date createTime;
	private String stagtegyTitle;
	private String stagtegyContent;
	private String images;
	private int praiseNum;
	private String spotName;
	private int count;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getPetName() {
		return petName;
	}
	public void setPetName(String petName) {
		this.petName = petName;
	}
	private int fitMonth;
	private String status;
	private String imageUrl;
	private String petName;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getFitMonth() {
		return fitMonth;
	}
	public void setFitMonth(int fitMonth) {
		this.fitMonth = fitMonth;
	}
	public String getSpotName() {
		return spotName;
	}
	public void setSpotName(String spotName) {
		this.spotName = spotName;
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
	public String getSubComment() {
		return subComment;
	}
	public void setSubComment(String subComment) {
		this.subComment = subComment;
	}
	public String getKeys() {
		return keys;
	}
	public void setKeys(String keys) {
		this.keys = keys;
	}
	private int investNum;
	private int commentNum;
	private int collectNum;
	public int getCollectNum() {
		return collectNum;
	}
	public void setCollectNum(int collectNum) {
		this.collectNum = collectNum;
	}
	private String subComment;
	private String keys;
	
	public int getStagtegyId() {
		return stagtegyId;
	}
	public void setStagtegyId(int stagtegyId) {
		this.stagtegyId = stagtegyId;
	}
	public int getStagtegyType() {
		return stagtegyType;
	}
	public void setStagtegyType(int stagtegyType) {
		this.stagtegyType = stagtegyType;
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
	public int getTravelDays() {
		return travelDays;
	}
	public void setTravelDays(int travelDays) {
		this.travelDays = travelDays;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getStagtegyTitle() {
		return stagtegyTitle;
	}
	public void setStagtegyTitle(String stagtegyTitle) {
		this.stagtegyTitle = stagtegyTitle;
	}
	public String getStagtegyContent() {
		return stagtegyContent;
	}
	public void setStagtegyContent(String stagtegyContent) {
		this.stagtegyContent = stagtegyContent;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}

}
