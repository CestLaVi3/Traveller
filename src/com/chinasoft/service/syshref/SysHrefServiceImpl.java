package com.chinasoft.service.syshref;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.chinasoft.common.utils.Exception.BusinessException;
import com.chinasoft.common.utils.log.HollyinfoLog;
import com.chinasoft.common.utils.log.HollyinfoLogger;
import com.chinasoft.dao.syshref.SysHrefDao;
import com.chinasoft.model.link.SysHref;
import com.chinasoft.service.user.UserServiceImpl;

public class SysHrefServiceImpl implements SysHrefService {
	private static final HollyinfoLog LOG = HollyinfoLogger.getLog(SysHrefServiceImpl.class);
	private SysHrefDao sysHrefDao;
	public SysHrefDao getSysHrefDao() {
		return sysHrefDao;
	}
	public void setSysHrefDao(SysHrefDao sysHrefDao) {
		this.sysHrefDao = sysHrefDao;
	}
	public List findMainHref() {
		try {
			return sysHrefDao.findMainHref();
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}

}
