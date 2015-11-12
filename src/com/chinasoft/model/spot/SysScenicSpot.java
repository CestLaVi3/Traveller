package com.chinasoft.model.spot;

import java.util.Date;

public class SysScenicSpot {
	private int spotId;
	private String spotName;
	private String locationX;
	private String locationY;
	private String locationDesc;
	private int provinceId;
	private int hotLevel;
	private String custom;
	private String spotDesc;
	private String imageUrl;
	private String travelRoute;
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getSpotId() {
		return spotId;
	}
	public void setSpotId(int spotId) {
		this.spotId = spotId;
	}
	public String getSpotName() {
		return spotName;
	}
	public void setSpotName(String spotName) {
		this.spotName = spotName;
	}
	public String getLocationX() {
		return locationX;
	}
	public void setLocationX(String locationX) {
		this.locationX = locationX;
	}
	public String getLocationY() {
		return locationY;
	}
	public void setLocationY(String locationY) {
		this.locationY = locationY;
	}
	public String getLocationDesc() {
		return locationDesc;
	}
	public void setLocationDesc(String locationDesc) {
		this.locationDesc = locationDesc;
	}
	public int getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}
	public int getHotLevel() {
		return hotLevel;
	}
	public void setHotLevel(int hotLevel) {
		this.hotLevel = hotLevel;
	}
	public String getCustom() {
		return custom;
	}
	public void setCustom(String custom) {
		this.custom = custom;
	}
	public String getSpotDesc() {
		return spotDesc;
	}
	public void setSpotDesc(String spotDesc) {
		this.spotDesc = spotDesc;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getTravelRoute() {
		return travelRoute;
	}
	public void setTravelRoute(String travelRoute) {
		this.travelRoute = travelRoute;
	}
}
