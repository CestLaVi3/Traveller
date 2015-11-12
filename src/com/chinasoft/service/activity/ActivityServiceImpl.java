package com.chinasoft.service.activity;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.chinasoft.common.utils.Exception.BusinessException;
import com.chinasoft.common.utils.log.HollyinfoLog;
import com.chinasoft.common.utils.log.HollyinfoLogger;
import com.chinasoft.dao.activity.ActivityDao;
import com.chinasoft.model.activity.BusActive;
import com.chinasoft.model.activity.BusActiveJoin;
public class ActivityServiceImpl implements ActivityService {
	private static final HollyinfoLog LOG = HollyinfoLogger.getLog(ActivityServiceImpl.class);
	private ActivityDao activityDao;
	public ActivityDao getActivityDao() {
		return activityDao;
	}
	public void setActivityDao(ActivityDao activityDao) {
		this.activityDao = activityDao;
	}
	public List findActiveType() {
		try {
			return activityDao.findActiveType();
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	public List findActiveByStatus(String status) {
		try {
			return activityDao.findActiveByStatus(status);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public List findActiveByType(int typeId) {
		try {
			return activityDao.findActiveByType(typeId);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public List findPhotoActive(String count) {
		try {
			return activityDao.findPhotoActive(count);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public List findActiveJoinByCount(int count, int activeId) {
		try {
			return activityDao.findActiveJoinByCount(count, activeId);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	public BusActive findActiveById(int activeId) {
		try {
			return activityDao.findActiveById(activeId);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}

	public void collectActive(int activeId,int userid,int num,String nt) {
		try {
			activityDao.collectActive(activeId,userid,num,nt);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
		
	}
	
	public void praiseActiveJoin(int activeJionId,int userid,int num,String nt) {
		try {
			activityDao.praiseActiveJoin(activeJionId,userid,num,nt);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
		
	}
	
	public BusActiveJoin findActiveJoinById(int activeJoinId) {
		try {
			return activityDao.findActiveJoinById(activeJoinId);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}

	public List findActiveJoinByPage(int pageNum, int pageCount, int activeId) {
		try {
			return activityDao.findActiveJoinByPage(pageNum, pageCount, activeId);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public void saveActiveJoin(BusActiveJoin busActiveJoin) {	
		try {
			activityDao.saveActiveJoin(busActiveJoin);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public List collect() {
		try {
		   return activityDao.collect();
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public List findActiveByPageAndType(int pageNum, int pageCount,
			int activeType) {
		try {
			   return activityDao.findActiveByPageAndType(pageNum, pageCount, activeType);
			} catch (DataAccessException e) {
				LOG.debug("错误信息", e);
				throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
			}
	}
	
	public List findTemByCollect(int count, int activeType) {
		try {
			   return activityDao.findTemByCollect(count, activeType);
			} catch (DataAccessException e) {
				LOG.debug("错误信息", e);
				throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
			}
	}
	
	public List findTeamByCollect(int activeType, int pageNum, int pageCount,
			int spotId, String planDate) {
		try {
			   return activityDao.findTeamByCollect(activeType, pageNum, pageCount, spotId, planDate);
			} catch (DataAccessException e) {
				LOG.debug("错误信息", e);
				throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
			}
	}
	
	public BusActive findTeamActiveById(int activeId,int userId) {
		try {
			   return activityDao.findTeamActiveById(activeId,userId);
			} catch (DataAccessException e) {
				LOG.debug("错误信息", e);
				throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
			}
	}
	
	public List findTeamActiveBySpot(int spotId, int count, int activeType) {
		try {
			   return activityDao.findTeamActiveBySpot(spotId, count, activeType);
			} catch (DataAccessException e) {
				LOG.debug("错误信息", e);
				throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
			}
	}
	
	public void saveTeamActiveJoin(BusActiveJoin busActiveJoin) {
		try {
			  activityDao.saveTeamActiveJoin(busActiveJoin);
			} catch (DataAccessException e) {
				LOG.debug("错误信息", e);
				throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
			}
	}
	
	public void saveTeamActive(BusActive busActive) {
		try {
			  activityDao.saveTeamActive(busActive);
			} catch (DataAccessException e) {
				LOG.debug("错误信息", e);
				throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
			}
		
	}
	
	public List findTeamByUserJoin(int userId, int pageNum, int pageCount,
			String status) {
		try {
			return activityDao.findTeamByUserJoin(userId, pageNum, pageCount, status);
			} catch (DataAccessException e) {
				LOG.debug("错误信息", e);
				throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
			}
	}
	
	public void unJoinActive(int activeId, int userId) {
		try {
			activityDao.unJoinActive(activeId, userId);
			} catch (DataAccessException e) {
				LOG.debug("错误信息", e);
				throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
			}
	}
	
	public List findTeamByUserCreate(int userId, int pageNum, int pageCount,
			String status) {
		try {
			return activityDao.findTeamByUserCreate(userId, pageNum, pageCount, status);
			} catch (DataAccessException e) {
				LOG.debug("错误信息", e);
				throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
			}
	}
	
	public void closeActive(int activeId) {
		try {
			activityDao.closeActive(activeId);
			} catch (DataAccessException e) {
				LOG.debug("错误信息", e);
				throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
			}
		
	}
	
	public List findTeamByUserCollect(int userId, int pageNum, int pageCount,
			String status) {
		try {
			return activityDao.findTeamByUserCollect(userId, pageNum, pageCount, status);
			} catch (DataAccessException e) {
				LOG.debug("错误信息", e);
				throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
			}
	}
	
	public void unCollectActive(int activeId, int userId) {
		try {
			activityDao.unCollectActive(activeId, userId);
			} catch (DataAccessException e) {
				LOG.debug("错误信息", e);
				throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
			}
	}
	
	public List findInformByUser(int userId) {
		try {
			return activityDao.findInformByUser(userId);
			} catch (DataAccessException e) {
				LOG.debug("错误信息", e);
				throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
			}
	}
	
	public List findAllActiveByType(int activeType, int pageNum, int pageCount) {
		try {
			return activityDao.findAllActiveByType(activeType, pageNum, pageCount);
			} catch (DataAccessException e) {
				LOG.debug("错误信息", e);
				throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
			}
	}
	public void openActive(int activeId) {
		try {
			activityDao.openActive(activeId);
			} catch (DataAccessException e) {
				LOG.debug("错误信息", e);
				throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
			}
	}
	
	public List findActiveByStatusAndType(String status, int activeType) {
		try {
			return activityDao.findActiveByStatusAndType(status, activeType);
			} catch (DataAccessException e) {
				LOG.debug("错误信息", e);
				throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
			}
	}
	
	public void saveWebActive(BusActive busActive) {
		try {
			activityDao.saveWebActive(busActive);
			} catch (DataAccessException e) {
				LOG.debug("错误信息", e);
				throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
			}
	}

	public void saveWebTeamActive(BusActive busActive) {
		try {
			activityDao.saveWebTeamActive(busActive);
			} catch (DataAccessException e) {
				LOG.debug("错误信息", e);
				throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
			}
	}

}
