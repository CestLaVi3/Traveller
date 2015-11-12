package com.chinasoft.dao.user;

import java.util.List;

import com.chinasoft.model.manager.SysManager;
import com.chinasoft.model.user.SysUser;

public interface UserDao {
	SysUser login(String loginName);
	
	void insertUser(SysUser user);
	
	List findCurUserCollect(int userId,int pageNum,int pageCount);
	
	void unCollectUser(int userId,int userId2);
	
	List findByCondition(String sex,String petName,String freezeStatus,int pageNum,int pageCount);

	void collectUser(int userId,int userId2);
	
	List findCurUserFuns(int userId,int pageNum,int pageCount);

	List findCurUserBrowse(int userId,int pageNum,int pageCount);

	SysUser findUserByUserId(int userId);
	
	void updateBaseInfor(SysUser user);
	
	void updateAuthInfor(SysUser user);
	
	void updatePassword(int userId,String password);
	
	SysManager findManagerByLoginName(String loginName);
	
	void updateVistNum(int userId,int userId2);
		
	void checkUser(int userId,String authenticated);
	

	
	void freezeUser(int userId,String freezeStatus);
	

	
	List findAllUser(int pageNum,int pageCount);
	
	List findUserByName(String name,int pageNum,int pageCount);
}
