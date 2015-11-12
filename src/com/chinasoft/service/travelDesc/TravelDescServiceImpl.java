package com.chinasoft.service.travelDesc;

import java.util.Date;
import java.util.List;


import org.springframework.dao.DataAccessException;

import com.chinasoft.common.utils.Exception.BusinessException;
import com.chinasoft.common.utils.log.HollyinfoLog;
import com.chinasoft.common.utils.log.HollyinfoLogger;
import com.chinasoft.dao.travelDesc.TravelDescDao;
import com.chinasoft.model.general.BusCollect;
import com.chinasoft.model.general.BusPhase;
import com.chinasoft.model.strategy.BusTravelDesc;
import com.chinasoft.model.user.SysUser;

public class TravelDescServiceImpl implements TravelDescService {
	private static final HollyinfoLog LOG = HollyinfoLogger.getLog(TravelDescServiceImpl.class);
	private TravelDescDao travelDescDao; 

	public TravelDescDao getTravelDescDao() {
		return travelDescDao;
	}

	public void setTravelDescDao(TravelDescDao travelDescDao) {
		this.travelDescDao = travelDescDao;
	}

	public List findDescByCondition(String s) {
		try {
			return travelDescDao.findDescByCondition(s);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}

	public List findDescByPage(int t1,int t2,int t3,String descTitle) {
		try {
			return travelDescDao.findDescByPage(t1,t2,t3,descTitle);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}

	
	public BusTravelDesc findDescById(int d) {
		try {
			return travelDescDao.findDescById(d);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}

	
	public List findRelationSpotById(int id,int count) {
		try {
			return travelDescDao.findRelationSpotById(id,count);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}

	
	public void praiseTravelDesc(int tid,int userid,int num,String nt) {
		try {
			travelDescDao.praiseTravelDesc(tid,userid,num,nt);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	public void collectTravelDesc(int tid,int userid,int num,String nt) {
		try {
			travelDescDao.collectTravelDesc(tid,userid,num,nt);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public List findDescByUser(int userid) {
		try {
			return travelDescDao.findDescByUser(userid);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}

	
	public void saveTravelDesc(BusTravelDesc travelDesc) {
		try {
			travelDescDao.saveTravelDesc(travelDesc);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
		
	}

	
	public BusPhase findBusPhaseByUserId(int uid,int did,String type) {
		try {
			return travelDescDao.findBusPhaseByUserId(uid,did,type);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}

	
	public BusCollect findBusCollectByUserId(int uid,int did,String type) {
		try {
			return travelDescDao.findBusCollectByUserId(uid,did,type);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}

	public void deleteTravelDesc(int travelDescId,int userId) {
		try {
			travelDescDao.deleteTravelDesc(travelDescId,userId);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
		
	}

	
	public void updateTravelDesc(BusTravelDesc travelDesc) {
		try {
			travelDescDao.updateTravelDesc(travelDesc);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
		
	}

	
	public List findDescByUserCollect(int userId) {
		try {
			return travelDescDao.findDescByUserCollect(userId);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}


	public void insertBrowse(int userId, int relId,String type, String table,String t) {
		try {
			travelDescDao.insertBrowse(userId, relId, type, table,t);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}

	
	public List findDescByCollect(int userId) {
		try {
			return travelDescDao.findDescByCollect(userId);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}

	public List findDescBySpotName(int pageNum, int pageCount, String spotName) {
		try {
			return travelDescDao.findDescBySpotName(pageNum, pageCount, spotName);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}

	
	public void deleteDescByIds(String travelDescId) {
		try {
			travelDescDao.deleteDescByIds(travelDescId);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}

	
	
}
