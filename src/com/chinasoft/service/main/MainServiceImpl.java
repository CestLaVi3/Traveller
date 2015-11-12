package com.chinasoft.service.main;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.chinasoft.common.utils.Exception.BusinessException;
import com.chinasoft.common.utils.log.HollyinfoLog;
import com.chinasoft.common.utils.log.HollyinfoLogger;
import com.chinasoft.dao.main.MainDao;
import com.chinasoft.model.main.SysHref;
import com.chinasoft.model.main.SysMainImage;

public class MainServiceImpl implements MainService {
	private static final HollyinfoLog LOG = HollyinfoLogger.getLog(MainServiceImpl.class);
	private MainDao mainDao;
	
	public MainDao getMainDao() {
		return mainDao;
	}

	public void setMainDao(MainDao mainDao) {
		this.mainDao = mainDao;
	}

	public List findMainHref() {
		try {
			return mainDao.findMainHref();
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	public List findMainImage() {
		try {
			return mainDao.findMainImage();
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}

	
	public void addImage(SysMainImage mainImage) {
		try {
			mainDao.addImage(mainImage);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}

	
	public void updateImage(SysMainImage mainImage) {
		try {
			mainDao.updateImage(mainImage);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}

	
	public SysMainImage findMainImageById(int imageId) {
		try {
			return mainDao.findMainImageById(imageId);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}

	
	public void deleteImage(String imageId) {
		try {
			mainDao.deleteImage(imageId);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}

	
	public List findMainHrefByName(String hrefName,int pageNum,int pageCount) {
		try {
			return mainDao.findMainHrefByName(hrefName,pageNum,pageCount);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}

	
	public void addMainHref(SysHref href) {
		try {
			mainDao.addMainHref(href);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}

	
	public void updateMainHref(SysHref href) {
		try {
			mainDao.updateMainHref(href);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}

	
	public void deleteMainHref(String hrefId) {
		try {
			mainDao.deleteMainHref(hrefId);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}

	

}
