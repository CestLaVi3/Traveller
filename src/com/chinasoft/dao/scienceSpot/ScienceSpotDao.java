package com.chinasoft.dao.scienceSpot;

import java.util.List;

import com.chinasoft.model.spot.RlSpotFamousHuman;
import com.chinasoft.model.spot.RlSpotFamousMenu;
import com.chinasoft.model.spot.RlSpotSpecialty;
import com.chinasoft.model.spot.SysProvince;
import com.chinasoft.model.spot.SysScenicSpot;

public interface ScienceSpotDao {
	List findScienceSpotByHotLevel();
	
	List findSpotByScienceSpotId(List list);
	
	List findSpotByCount(String c);
	
	SysScenicSpot findById(int spotId);
	
	void updateBasic(SysScenicSpot scenicSpot);
	
	List findhumanbyspotId(int spotId);
	
	void updateHuman(RlSpotFamousHuman spotFamousHuman);
	
	void drophumanbyhumanId(int humanId);
	
	RlSpotFamousHuman findhumanbyHumanId(int humanId);
	
	void saveHuman(RlSpotFamousHuman spotFamousHuman);
	
	List findmenubyspotId(int spotId);
	
	void updateMenu(RlSpotFamousMenu spotFamousMenu);
	
	RlSpotFamousMenu findMenubyHumanId(int menuId);
	
	void deleteById(int menuId);
	
	void saveMenu(RlSpotFamousMenu spotFamousMenu);
	
	List findSpecialtybyspotId(int spotId);
	
	void updateSpecialty(RlSpotSpecialty specialty);
	
	RlSpotSpecialty findSpecialtybyId(int specialId);
	
	void deleteSpecialtybyId(int specialtyId);
	
	void saveSpecialty(RlSpotSpecialty specialty);
	
	void updateCustombyspotId(int spotId,String custom);
	
	void updateSpotDescbyId(int spotId,String spotDesc);
	
	void updateimageBySpotId(int spotId,String images);
	
	void updateTravleRoutebyspotId(int spotId,String travleRoute);
	
	void saveSpotBasic(SysScenicSpot scenicSpot);
	
	void forbitScenicSpot(int spotId);
	
	void unForbitScenicSpot(int spotId);
	
	List findAllScenicSpot();
	
	List findbyProvinceId(int provinceId);
}
