package com.chinasoft.service.province;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.chinasoft.common.utils.Exception.BusinessException;
import com.chinasoft.common.utils.log.HollyinfoLog;
import com.chinasoft.common.utils.log.HollyinfoLogger;
import com.chinasoft.dao.main.MainDao;
import com.chinasoft.dao.province.ProvinceDao;
import com.chinasoft.model.spot.SysProvince;
import com.chinasoft.service.comments.CommentsServiceImpl;


public class ProvinceServiceImpl implements ProvinceService {
	private static final HollyinfoLog LOG = HollyinfoLogger.getLog(ProvinceServiceImpl.class);

	private ProvinceDao provinceDao;

	public ProvinceDao getProvinceDao() {
		return provinceDao;
	}

	public void setProvinceDao(ProvinceDao provinceDao) {
		this.provinceDao = provinceDao;
	}

	
	public SysProvince findbyId(int provinceId) {
		try {
			return provinceDao.findbyId(provinceId);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}

	
	public void updateBasic(SysProvince province) {
		try {
			provinceDao.updateBasic(province);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}


	public void updatetravelDesc(int provinceId, String travelDesc) {
		try {
			provinceDao.updatetravelDesc(provinceId, travelDesc);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}

	
	public void updateprovinceDesc(int provinceId, String provinceDesc) {
		try {
			provinceDao.updateprovinceDesc(provinceId, provinceDesc);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}

	
	public void updatehistoryDesc(int provinceId, String historyDesc) {
		try {
			provinceDao.updatehistoryDesc(provinceId, historyDesc);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}

	
	public void updatecultureDesc(int provinceId, String cultureDesc) {
		try {
			provinceDao.updatecultureDesc(provinceId, cultureDesc);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}

	
	public void updateimage(int provinceId, String images) {
		try {
			provinceDao.updateimage(provinceId, images);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}

	
	public List findAllProvince() {
		try {
			return provinceDao.findAllProvince();
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
}
