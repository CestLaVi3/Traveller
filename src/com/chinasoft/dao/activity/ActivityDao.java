package com.chinasoft.dao.activity;

import java.util.List;

import com.chinasoft.model.activity.BusActive;
import com.chinasoft.model.activity.BusActiveJoin;

public interface ActivityDao {
	List findActiveType();
	
	List findActiveByStatus(String status);
	
	List findActiveByType(int typeId);
	
	List findPhotoActive(String count);
	
	List findActiveJoinByCount(int count,int activeId);
	
	void collectActive(int activeId,int userid,int num,String nt);
	
	void praiseActiveJoin(int activeJionId,int userid,int num,String nt);
	
	BusActive findActiveById(int activeId);
	
	BusActiveJoin findActiveJoinById(int activeJoinId);
	
	List findActiveJoinByPage(int pageNum,int pageCount,int activeId);

	void saveActiveJoin(BusActiveJoin busActiveJoin);
	
	List collect();
	
	List findActiveByPageAndType(int pageNum,int pageCount,int activeType);
	
	List findTemByCollect(int count,int activeType);
	
	List findTeamByCollect(int activeType,int pageNum,int pageCount,int spotId,String planDate);

	BusActive findTeamActiveById(int activeId,int userId);
	
	List findTeamActiveBySpot(int spotId,int count,int activeType);

	void saveTeamActiveJoin(BusActiveJoin busActiveJoin);

	void saveTeamActive(BusActive busActive);
	
	void saveWebTeamActive(BusActive busActive);
	
	List findTeamByUserJoin(int userId,int pageNum,int pageCount,String status);

	void unJoinActive(int activeId,int userId);
	
	void unCollectActive(int activeId,int userId);
	
	List findTeamByUserCreate(int userId,int pageNum,int pageCount,String status);
	
	void closeActive(int activeId);
	
	List findTeamByUserCollect(int userId,int pageNum,int pageCount,String status);

	List findInformByUser(int userId);
	
	List findAllActiveByType(int activeType,int pageNum,int pageCount);

	void openActive(int activeId);
	
	List findActiveByStatusAndType(String status,int activeType);
	
	void saveWebActive(BusActive busActive);
}
