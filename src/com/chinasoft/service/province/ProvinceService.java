package com.chinasoft.service.province;

import java.util.List;

import com.chinasoft.model.spot.SysProvince;

public interface ProvinceService {
	SysProvince findbyId(int provinceId);
	
	void updateBasic(SysProvince province);
	
	void updatetravelDesc(int provinceId,String travelDesc);
	
	void updateprovinceDesc(int provinceId,String provinceDesc);
	
	void updatehistoryDesc(int provinceId,String historyDesc);
	
	void updatecultureDesc(int provinceId,String cultureDesc);
	
	void updateimage(int provinceId,String images);
	
	List findAllProvince();
}
