package com.chinasoft.service.spot;

import java.util.List;

import com.chinasoft.model.spot.SysScenicSpot;
import com.chinasoft.model.spot.SysSpot;

public interface SpotService {
	SysScenicSpot findSpotById(int t);
	
	List findSpotByScienceSpotId(int t);
	
	List findAllSpot();
	
	List findAllSpots();
	
	void forbitSpot(int sysSpotId);
	
	void unForbitSpot(int sysSpotId);
	
	SysSpot findbyId(int sysSpotId);
	
	List findAllspotType();
	
	void updateBasic(SysSpot spot);
	
	void updateSpotDesc(int sysSpotId,String spotDesc);
	
	void updateImages(int sysSpotId,String images);
	
	void saveBasic(SysSpot spot);
}
