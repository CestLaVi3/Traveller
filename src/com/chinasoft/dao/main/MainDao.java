package com.chinasoft.dao.main;

import java.util.List;

import com.chinasoft.model.main.SysHref;
import com.chinasoft.model.main.SysMainImage;


public interface MainDao {
	List findMainHref();
	
	List findMainImage();
		
	void addImage(SysMainImage mainImage);
	
	void updateImage(SysMainImage mainImage);
	
	SysMainImage findMainImageById(int imageId);
	
	void deleteImage(String imageId);
	 
	List findMainHrefByName(String hrefName,int pageNum,int pageCount);
	
	void addMainHref(SysHref href);
	
	void updateMainHref(SysHref href);
	
	void deleteMainHref(String hrefId);
	
	
	
}
