package com.chinasoft;

import java.io.IOException;
import java.util.Properties;

import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;

import com.chinasoft.common.utils.Exception.BusinessException;
import com.chinasoft.common.utils.file.FileUtil;


public final class ConstantDataManager {
	private ConstantDataManager() {
	}
	  public static long getDataBaseServerTimeMIllis() {
		  //TODO请同学实现数据库查询
	        long curTime = System.currentTimeMillis();
	    	return System.currentTimeMillis();
	    }
	
		/* ================================== */
		public static final String FILE_SEPARATOR = "/";
		
	
}
