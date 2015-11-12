package com.chinasoft.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;

import com.chinasoft.common.utils.Exception.BusinessException;
import com.chinasoft.common.utils.log.HollyinfoLog;
import com.chinasoft.common.utils.log.HollyinfoLogger;


//import com.hollyinfo.hiiap.HiiapMaxValueIncrementer;

public abstract class BaseDao extends JdbcDaoSupport {
    private static final HollyinfoLog LOG = HollyinfoLogger.getLog(BaseDao.class);

    private DataFieldMaxValueIncrementer idIncrementer;

    public DataFieldMaxValueIncrementer getIdIncrementer() {
        return idIncrementer;
    }

    public void setIdIncrementer(DataFieldMaxValueIncrementer pincrementer) {
        idIncrementer = pincrementer;
    }

    public static Clob stringToClob(String str) {
		java.sql.Clob c = null;
		try {
			c = new javax.sql.rowset.serial.SerialClob(str.toCharArray());
		} catch (SerialException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	public static String clobToString(Clob clob) throws SQLException {
		if (clob == null) {
			return "";
		}
		StringBuffer ret = new StringBuffer("");
		Reader reader = clob.getCharacterStream();
		BufferedReader br = new BufferedReader(reader);
		String str = null;
		try {
			str = br.readLine();
			while (str != null) {
				ret.append(str);
				str = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret.toString();
	}
    /**
     * @param tableName
     * @return
     */
    public boolean isExistedTableName(final String tableName) {
        String sql = "SELECT COUNT(name)  FROM sysobjects WHERE (name = ?)";;
        int judge = this.getJdbcTemplate().queryForInt(sql, new Object[] {tableName.toUpperCase()});
        return judge == 0 ? false : true;
    }

    /**
     */
    public void createTable(final String pTableName, final String sql[]) {
        if (!isExistedTableName(pTableName)) {
            this.getJdbcTemplate().batchUpdate(sql);
        }
    }

    /**
     * @param pSql
     */
    public void executeSqlNoThrowException(String pSql) {
        try {
            getJdbcTemplate().execute(pSql);
        } catch (DataAccessException e) {
            LOG.debug("数据库错误[" + pSql + "]");
        }
    }
    /**
     * 鍒犻櫎琛紝娆℃柟娉曚笉鎶涘嚭浠讳綍寮傚父
     */
    public void dropTableNoThrowException(String tableName) {
    	if (isExistedTableName(tableName)) {
    		executeSqlNoThrowException("DROP TABLE " + tableName);
    	}
    }

    /**
     * @param pSql
     */
    public void executeSqlThrowException(String pSql) {
        getJdbcTemplate().execute(pSql);
    }
    public void createTempTable(String[] pDdlSql) {
        int size = pDdlSql.length;
        String tStr = "";
        for (int i = 0; i < size; i++) {
            tStr = pDdlSql[i];
            if (tStr != null && tStr.length() > 0) {
              this.executeSqlNoThrowException(tStr.indexOf(";") != -1 ? tStr.substring(0, tStr.indexOf(";")) : tStr);
            }
        }
        try {
            getConnection().commit();
        } catch (CannotGetJdbcConnectionException e) {
            LOG.error("创建临时表异常", e);
            throw new BusinessException("创建临时表异常", e);
        } catch (SQLException e) {
            LOG.error("创建临时表异常", e);
            throw new BusinessException("创建临时表异常", e);
        }
    }
  
}