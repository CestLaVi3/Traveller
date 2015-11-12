package com.chinasoft.service.login;

import org.springframework.dao.DataAccessException;

import com.chinasoft.common.utils.Exception.BusinessException;
import com.chinasoft.common.utils.log.HollyinfoLog;
import com.chinasoft.common.utils.log.HollyinfoLogger;
import com.chinasoft.dao.login.LoginDao;
import com.chinasoft.model.user.User;

public class LoginServiceImpl implements LoginService {
	private static final HollyinfoLog LOG = HollyinfoLogger.getLog(LoginServiceImpl.class);
	private LoginDao loginDao;
	public LoginDao getLoginDao() {
		return loginDao;
	}
	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}
	public User findByLognName(String loginName) {
		try {
			return loginDao.login(loginName);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
		
	}

	public void insertUser(User user) {
		try {
			this.loginDao.insertUser(user);
		} catch (Exception e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
		
	}

}
