package com.chinasoft.dao.syshref;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.chinasoft.dao.BaseDao;
import com.chinasoft.model.link.SysHref;


public class SysHrefDaoImpl extends BaseDao implements SysHrefDao {
	
	public List findMainHref() {
		String sql="select * from sys_href";
		return getJdbcTemplate().query(sql, new Object[]{}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				SysHref sysHref=new SysHref();
				sysHref.setHrefId(rs.getInt("Href_Id"));
				sysHref.setHrefName(rs.getString("Href_Name"));
				sysHref.setHrefUrl(rs.getString("Href_Url"));
				return sysHref;
			}
		});
	}

}
