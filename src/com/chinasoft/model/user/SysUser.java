package com.chinasoft.model.user;

import java.util.Date;


public class SysUser {
	private int count;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	private Integer userId;
	private String userName;
	private String imageUrl;	
	private String sex; 
	private String location;
	private String qqNum;
	private String email;
	private String address;
	private String phoneNum;
	private String petName;
	private Date birthday;
	private String signature;
	private String identitycard;
	private Integer attentionNum;
	private Integer funsNum;
	private Integer vistNum;
	private String freezeStatus;
	private String authenticated;
	private String identityCardImage;
	
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	public Integer getAttentionNum() {
		return attentionNum;
	}
	public void setAttentionNum(Integer attentionNum) {
		this.attentionNum = attentionNum;
	}
	public Integer getFunsNum() {
		return funsNum;
	}
	public void setFunsNum(Integer funsNum) {
		this.funsNum = funsNum;
	}
	public Integer getVistNum() {
		return vistNum;
	}
	public void setVistNum(Integer vistNum) {
		this.vistNum = vistNum;
	}
	public String getFreezeStatus() {
		return freezeStatus;
	}
	public void setFreezeStatus(String freezeStatus) {
		this.freezeStatus = freezeStatus;
	}
		
	
	public String getIdentitycard() {
		return identitycard;
	}
	public void setIdentitycard(String identitycard) {
		this.identitycard = identitycard;
	}
	public String getAuthenticated() {
		return authenticated;
	}
	public void setAuthenticated(String authenticated) {
		this.authenticated = authenticated;
	}
	public String getIdentityCardImage() {
		return identityCardImage;
	}
	public void setIdentityCardImage(String identityCardImage) {
		this.identityCardImage = identityCardImage;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getQqNum() {
		return qqNum;
	}
	public void setQqNum(String qqNum) {
		this.qqNum = qqNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getPetName() {
		return petName;
	}
	public void setPetName(String petName) {
		this.petName = petName;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	private String loginName;
	private String password;
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
