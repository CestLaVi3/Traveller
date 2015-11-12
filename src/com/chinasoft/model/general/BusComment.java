package com.chinasoft.model.general;

import java.util.Date;

//评论
public class BusComment {
	private int commentId;
	private int userId;
	private Date createTime;
	private String commentContent;
	private String commentType;
	private int relId;
	private String commentLevel;
	private String imageUrl;
	private int count;
	private String petName;
	public String getPetName() {
		return petName;
	}
	public void setPetName(String petName) {
		this.petName = petName;
	}
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
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
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
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getCommentType() {
		return commentType;
	}
	public void setCommentType(String commentType) {
		this.commentType = commentType;
	}
	public int getRelId() {
		return relId;
	}
	public void setRelId(int relId) {
		this.relId = relId;
	}
	public String getCommentLevel() {
		return commentLevel;
	}
	public void setCommentLevel(String commentLevel) {
		this.commentLevel = commentLevel;
	}
	
	
}
