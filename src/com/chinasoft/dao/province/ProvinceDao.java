package com.chinasoft.dao.province;

import java.util.List;

import com.chinasoft.model.spot.SysProvince;

public interface ProvinceDao {
	SysProvince findbyId(int provinceId);
	
	void updateBasic(SysProvince province);
	
	void updatetravelDesc(int provinceId,String travelDesc);
	
	void updateprovinceDesc(int provinceId,String provinceDesc);
	
	void updatehistoryDesc(int provinceId,String historyDesc);
	
	void updatecultureDesc(int provinceId,String cultureDesc);
	
	void updateimage(int provinceId,String images);
	
	List findAllProvince();
	
}
