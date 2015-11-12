package com.chinasoft.service.spot;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.chinasoft.common.utils.Exception.BusinessException;
import com.chinasoft.common.utils.log.HollyinfoLog;
import com.chinasoft.common.utils.log.HollyinfoLogger;
import com.chinasoft.dao.spot.SpotDao;
import com.chinasoft.model.spot.SysScenicSpot;
import com.chinasoft.model.spot.SysSpot;

public class SpotServiceImpl implements SpotService{
	private static final HollyinfoLog LOG = HollyinfoLogger.getLog(SpotServiceImpl.class);
	private SpotDao spotDao;
	public SpotDao getSpotDao() {
		return spotDao;
	}
	public void setSpotDao(SpotDao spotDao) {
		this.spotDao = spotDao;
	}
	public SysScenicSpot findSpotById(int t) {
		try {
			return spotDao.findSpotById(t);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public List findSpotByScienceSpotId(int t) {
		try {
			return spotDao.findSpotByScienceSpotId(t);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public List findAllSpot() {
		try {
			return spotDao.findAllSpot();
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public List findAllSpots() {
		try {
			return spotDao.findAllSpots();
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public void forbitSpot(int sysSpotId) {
		try {
			spotDao.forbitSpot(sysSpotId);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public void unForbitSpot(int sysSpotId) {
		try {
			spotDao.unForbitSpot(sysSpotId);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public SysSpot findbyId(int sysSpotId) {
		try {
			return spotDao.findbyId(sysSpotId);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public List findAllspotType() {
		try {
			return spotDao.findAllspotType();
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public void updateBasic(SysSpot spot) {
		try {
			spotDao.updateBasic(spot);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public void updateSpotDesc(int sysSpotId,String spotDesc) {
		try {
			spotDao.updateSpotDesc(sysSpotId,spotDesc);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public void updateImages(int sysSpotId, String images) {
		try {
			spotDao.updateImages(sysSpotId, images);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public void saveBasic(SysSpot spot) {
		try {
			spotDao.saveBasic(spot);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
}
