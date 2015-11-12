package com.chinasoft.service.scienceSpot;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.chinasoft.common.utils.Exception.BusinessException;
import com.chinasoft.common.utils.log.HollyinfoLog;
import com.chinasoft.common.utils.log.HollyinfoLogger;
import com.chinasoft.dao.scienceSpot.ScienceSpotDao;
import com.chinasoft.model.spot.RlSpotFamousHuman;
import com.chinasoft.model.spot.RlSpotFamousMenu;
import com.chinasoft.model.spot.RlSpotSpecialty;
import com.chinasoft.model.spot.SysProvince;
import com.chinasoft.model.spot.SysScenicSpot;

public class ScienceSpotServiceImpl implements ScienceSpotService {

	private static final HollyinfoLog LOG = HollyinfoLogger.getLog(ScienceSpotServiceImpl.class);
	private ScienceSpotDao scienceSpotDao;
	public ScienceSpotDao getScienceSpotDao() {
		return scienceSpotDao;
	}
	public void setScienceSpotDao(ScienceSpotDao scienceSpotDao) {
		this.scienceSpotDao = scienceSpotDao;
	}
	public List findScienceSpotByHotLevel() {
		try {
			return scienceSpotDao.findScienceSpotByHotLevel();
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public List findSpotByScienceSpotId(List list) {
		try {
			return scienceSpotDao.findSpotByScienceSpotId(list);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public List findSpotByCount(String c) {
		try {
			return scienceSpotDao.findSpotByCount(c);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public SysScenicSpot findById(int spotId) {
		try {
			return scienceSpotDao.findById(spotId);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public void updateBasic(SysScenicSpot scenicSpot) {
		try {
			scienceSpotDao.updateBasic(scenicSpot);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public List findhumanbyspotId(int spotId) {
		try {
			return scienceSpotDao.findhumanbyspotId(spotId);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public void updateHuman(RlSpotFamousHuman spotFamousHuman) {
		try {
			scienceSpotDao.updateHuman(spotFamousHuman);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public void drophumanbyhumanId(int humanId) {
		try {
			scienceSpotDao.drophumanbyhumanId(humanId);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public RlSpotFamousHuman findhumanbyHumanId(int humanId) {
		try {
			return scienceSpotDao.findhumanbyHumanId(humanId);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public void saveHuman(RlSpotFamousHuman spotFamousHuman) {
		try {
			scienceSpotDao.saveHuman(spotFamousHuman);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
		
	}
	
	public List findmenubyspotId(int spotId) {
		try {
			return scienceSpotDao.findmenubyspotId(spotId);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public void updateMenu(RlSpotFamousMenu spotFamousMenu) {
		try {
			scienceSpotDao.updateMenu(spotFamousMenu);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public RlSpotFamousMenu findMenubyHumanId(int menuId) {
		try {
			return scienceSpotDao.findMenubyHumanId(menuId);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public void deleteById(int menuId) {
		try {
			scienceSpotDao.deleteById(menuId);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public void saveMenu(RlSpotFamousMenu spotFamousMenu) {
		try {
			scienceSpotDao.saveMenu(spotFamousMenu);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public List findSpecialtybyspotId(int spotId) {
		try {
			return scienceSpotDao.findSpecialtybyspotId(spotId);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public void updateSpecialty(RlSpotSpecialty specialty) {
		try {
			scienceSpotDao.updateSpecialty(specialty);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public RlSpotSpecialty findSpecialtybyId(int specialId) {
		try {
			return scienceSpotDao.findSpecialtybyId(specialId);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public void deleteSpecialtybyId(int specialtyId) {
		try {
			scienceSpotDao.deleteSpecialtybyId(specialtyId);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public void saveSpecialty(RlSpotSpecialty specialty) {
		try {
			scienceSpotDao.saveSpecialty(specialty);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public void updateCustombyspotId(int spotId,String custom) {
		try {
			scienceSpotDao.updateCustombyspotId(spotId,custom);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public void updateSpotDescbyId(int spotId, String spotDesc) {
		try {
			scienceSpotDao.updateSpotDescbyId(spotId, spotDesc);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public void updateimageBySpotId(int spotId, String images) {
		try {
			scienceSpotDao.updateimageBySpotId(spotId, images);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public void updateTravleRoutebyspotId(int spotId, String travleRoute) {
		try {
			scienceSpotDao.updateTravleRoutebyspotId(spotId, travleRoute);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public void saveSpotBasic(SysScenicSpot scenicSpot) {
		try {
			scienceSpotDao.saveSpotBasic(scenicSpot);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public void forbitScenicSpot(int spotId) {
		try {
			scienceSpotDao.forbitScenicSpot(spotId);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public void unForbitScenicSpot(int spotId) {
		try {
			scienceSpotDao.unForbitScenicSpot(spotId);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public List findAllScenicSpot() {
		try {
			return scienceSpotDao.findAllScenicSpot();
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public List findbyProvinceId(int provinceId) {
		try {
			return scienceSpotDao.findbyProvinceId(provinceId);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	


}
