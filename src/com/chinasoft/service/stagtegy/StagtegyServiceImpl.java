package com.chinasoft.service.stagtegy;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.chinasoft.common.utils.Exception.BusinessException;
import com.chinasoft.common.utils.log.HollyinfoLog;
import com.chinasoft.common.utils.log.HollyinfoLogger;
import com.chinasoft.dao.stagtegy.StagtegyDao;
import com.chinasoft.dao.stagtegy.StagtegyDaoImpl;
import com.chinasoft.model.strategy.BusStagtegy;
import com.chinasoft.model.strategy.SysStagtegyType;
import com.chinasoft.model.user.SysUser;

public class StagtegyServiceImpl implements StagtegyService{
	private static final HollyinfoLog LOG = HollyinfoLogger.getLog(StagtegyDaoImpl.class);
	private StagtegyDao stagtegyDao;
	
	public StagtegyDao getStagtegyDao() {
		return stagtegyDao;
	}
	public void setStagtegyDao(StagtegyDao stagtegyDao) {
		this.stagtegyDao = stagtegyDao;
	}
	public List findStagtrgy(int pageNum,int pageCount) {
		try {
			return stagtegyDao.findStagtrgy(pageNum,pageCount);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	public List findDescByCondition(int typeId) {
		try {
			return stagtegyDao.findDescByCondition(typeId);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}

	public List findByMonth(int year,int month) {
		try {
			return stagtegyDao.findByMonth(year,month);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	public List findByConditionAndSot(int year,int pageNum, int pageCount, int sort,
			int spotId, String fitMonth, String travelDays,int stagtegyType,String stagtegyName,String status) {
		
		try {
			return stagtegyDao.findByConditionAndSot(year, pageNum, pageCount, sort, spotId, fitMonth, travelDays, stagtegyType,stagtegyName,status);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public List findHotStagtegy(int pageNUm) {
		try {
			return stagtegyDao.findHotStagtegy(pageNUm);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public BusStagtegy findById(int stagtegyId) {
		try {
			return stagtegyDao.findById(stagtegyId);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}

	public void praiseStagtegy(int stagtegyId,int userid,int num,String nt) {
		try {
			stagtegyDao.praiseStagtegy(stagtegyId, userid, num, nt);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
		
	}

	public void collectStagtegy(int stagtegyId,int userid,int num,String nt) {
		try {
			stagtegyDao.collectStagtegy(stagtegyId, userid, num, nt);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
		
	}

	public void deleteStagtegy(int stagtegyId,int userId) {
		try {
			stagtegyDao.deleteStagtegy(stagtegyId,userId);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
		
	}
	
	public List findRelationStagtegy(int spotId, int fitMonth,
			int travelDays,int travelId,int pageNum) {
		try {
			return stagtegyDao.findRelationStagtegy(spotId, fitMonth, travelDays,travelId,pageNum);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public void saveStagtegy(BusStagtegy stagtegy) {
		try {
			stagtegyDao.saveStagtegy(stagtegy);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
		
	}
	
	public void updateStagtegy(BusStagtegy stagtegy) {
		try {
			stagtegyDao.updateStagtegy(stagtegy);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
		
	}
	
	public List findStagtegyByUser(int userid) {
		try {
			return stagtegyDao.findStagtegyByUser(userid);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public List findByUserCollect(int userId) {
		try {
			return stagtegyDao.findByUserCollect(userId);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public List findstagtegyByCollect(int userId) {
		try {
			return stagtegyDao.findstagtegyByCollect(userId);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public void addStagtegyType(SysStagtegyType stagtegyType) {
		try {
			stagtegyDao.addStagtegyType(stagtegyType);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}

	public void updateStagtegyType(SysStagtegyType stagtegyType) {
		try {
			stagtegyDao.updateStagtegyType(stagtegyType);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public void deleteStagtegyType(int typeId) {
		try {
			stagtegyDao.deleteStagtegyType(typeId);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public void deleteStagtegyType(String stagtegyId) {
		try {
			stagtegyDao.deleteStagtegyType(stagtegyId);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public void checkStagtegy(int stagtegyId, String status) {
		try {
			stagtegyDao.checkStagtegy(stagtegyId, status);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	


}
