package com.chinasoft.service.user;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.chinasoft.common.utils.Exception.BusinessException;
import com.chinasoft.common.utils.log.HollyinfoLog;
import com.chinasoft.common.utils.log.HollyinfoLogger;
import com.chinasoft.dao.user.UserDao;
import com.chinasoft.model.manager.SysManager;
import com.chinasoft.model.user.SysUser;

public class UserServiceImpl implements UserService {
	private static final HollyinfoLog LOG = HollyinfoLogger.getLog(UserServiceImpl.class);
	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public SysUser findByLognName(String loginName) {
		try {
			return userDao.login(loginName);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
		
	}
	public void insertUser(SysUser user) {
		try {
			userDao.insertUser(user);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public List findCurUserCollect(int userId,int pageNum,int pageCount) {
		try {
			return userDao.findCurUserCollect(userId,pageNum,pageCount);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public void unCollectUser(int userId,int userId2) {
		try {
			userDao.unCollectUser(userId,userId2);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public List findByCondition(String sex, String petName,
			String freezeStatus, int pageNum, int pageCount) {
		try {
			return userDao.findByCondition(sex, petName, freezeStatus, pageNum, pageCount);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public void collectUser(int userId, int userId2) {
		try {
			userDao.collectUser(userId, userId2);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public List findCurUserFuns(int userId, int pageNum, int pageCount) {
		try {
			return userDao.findCurUserFuns(userId, pageNum, pageCount);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public List findCurUserBrowse(int userId, int pageNum, int pageCount) {
		try {
			return userDao.findCurUserBrowse(userId, pageNum, pageCount);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public SysUser findUserByUserId(int userId) {
		try {
			return userDao.findUserByUserId(userId);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public void updateBaseInfor(SysUser user) {
		try {
			userDao.updateBaseInfor(user);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public void updateAuthInfor(SysUser user) {
		try {
			userDao.updateAuthInfor(user);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public void updatePassword(int userId, String password) {
		try {
			userDao.updatePassword(userId, password);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}	
	public SysManager findManagerByLoginName(String loginName) {
		try {
			return userDao.findManagerByLoginName(loginName);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}

	public void updateVistNum(int userId,int userId2) {
		try {
			userDao.updateVistNum(userId,userId2);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public void checkUser(int userId,String authenticated) {
		try {
			userDao.checkUser(userId,authenticated);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	
	public void freezeUser(int userId,String freezeStatus) {
		try {
			userDao.freezeUser(userId,freezeStatus);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	
	
	public List findAllUser(int pageNum,int pageCount) {
		try {
			return userDao.findAllUser(pageNum,pageCount);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public List findUserByName(String name,int pageNum,int pageCount) {
		try {
			return userDao.findUserByName(name,pageNum,pageCount);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	

}
