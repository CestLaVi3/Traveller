package com.chinasoft.model.spot;

import java.util.Date;

public class RlSpotFamousHuman {
	private int humanId;
	private int spotId;
	private String humanDesc;
	private Date birthday;
	private Date dieDay;
	private String images;
	private String humanName;
	public String getHumanName() {
		return humanName;
	}
	public void setHumanName(String humanName) {
		this.humanName = humanName;
	}
	public int getHumanId() {
		return humanId;
	}
	public void setHumanId(int humanId) {
		this.humanId = humanId;
	}
	public int getSpotId() {
		return spotId;
	}
	public void setSpotId(int spotId) {
		this.spotId = spotId;
	}
	public String getHumanDesc() {
		return humanDesc;
	}
	public void setHumanDesc(String humanDesc) {
		this.humanDesc = humanDesc;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Date getDieDay() {
		return dieDay;
	}
	public void setDieDay(Date dieDay) {
		this.dieDay = dieDay;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	
}
