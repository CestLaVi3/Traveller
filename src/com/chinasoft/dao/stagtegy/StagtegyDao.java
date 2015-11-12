package com.chinasoft.dao.stagtegy;

import java.util.List;

import javax.ejb.FinderException;

import com.chinasoft.model.strategy.BusStagtegy;
import com.chinasoft.model.strategy.SysStagtegyType;
import com.chinasoft.model.user.SysUser;

public interface StagtegyDao {
	List findStagtrgy(int pageNum,int pageCount);
	
	List findDescByCondition(int typeId);
	
	List findByMonth(int year,int month);
	
	List findByConditionAndSot(int year,int pageNum, int pageCount, int sort,int spotId, String fitMonth, String travelDays,int stagtegyType,String stagtegyName,String status);


	List findHotStagtegy(int pageNUm);
	
	BusStagtegy findById(int stagtegyId);
	
	void praiseStagtegy(int stagtegyId,int userid,int num,String nt);
	
	void collectStagtegy(int stagtegyId,int userid,int num,String nt);
	
	void deleteStagtegy(int stagtegyId,int userId);
	
	List findRelationStagtegy(int spotId,int fitMonth,int travelDays,int travelId,int pageNum);

	void saveStagtegy(BusStagtegy stagtegy);

	void updateStagtegy(BusStagtegy stagtegy);
	
	List findStagtegyByUser(int userid);
	
	List findByUserCollect(int userId);
	
	List findstagtegyByCollect(int userId);	
	
	void addStagtegyType(SysStagtegyType stagtegyType);
	
	void updateStagtegyType(SysStagtegyType stagtegyType);
	
	void deleteStagtegyType(int typeId);
	
	void deleteStagtegyType(String stagtegyId);
	
	void checkStagtegy(int stagtegyId,String status);
	
	
}

