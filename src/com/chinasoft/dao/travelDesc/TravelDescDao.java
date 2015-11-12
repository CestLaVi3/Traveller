package com.chinasoft.dao.travelDesc;

import java.util.List;


import com.chinasoft.model.general.BusCollect;
import com.chinasoft.model.general.BusPhase;
import com.chinasoft.model.strategy.BusTravelDesc;
import com.chinasoft.model.user.SysUser;

public interface TravelDescDao {
	List findDescByCondition(String s);
	
	List findDescByPage(int t1,int t2,int t3,String descTitle);
	
	BusTravelDesc findDescById(int d);
	
	List findRelationSpotById(int id,int count);
	
	void praiseTravelDesc(int tid,int userid,int num,String nt);
	
	void collectTravelDesc(int tid,int userid,int num,String nt);
	
	List findDescByUser(int userid);
	
	void saveTravelDesc(BusTravelDesc travelDesc);

	BusPhase findBusPhaseByUserId(int uid,int did,String type);
	
	BusCollect findBusCollectByUserId(int uid,int did,String type);
	
	void deleteTravelDesc(int travelDescId,int userId);
	
	void updateTravelDesc(BusTravelDesc travelDesc);
	
	List findDescByUserCollect(int userId);
	
	void insertBrowse(int userId,int relId,String type,String table,String t);

	List findDescByCollect(int userId);
	
	List findDescBySpotName(int pageNum,int pageCount,String spotName);

	void deleteDescByIds(String travelDescId);
	
}
