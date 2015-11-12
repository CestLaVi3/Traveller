package com.chinasoft.dao.province;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.chinasoft.dao.BaseDao;
import com.chinasoft.model.activity.BusActive;
import com.chinasoft.model.activity.SysActiveType;
import com.chinasoft.model.spot.SysProvince;
import com.chinasoft.model.spot.SysSpot;

public class ProvinceDaoImpl extends BaseDao implements ProvinceDao {

	
	public SysProvince findbyId(int provinceId) {
		String sql="select * from sys_province where province_id=?";
		return (SysProvince)getJdbcTemplate().query(sql, new Object[] {provinceId}, new ResultSetExtractor() {			
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if(rs.next()){
				SysProvince province=new SysProvince();
				province.setCultureDesc(clobToString(rs.getClob("Culture_Desc")));
				province.setHistoryDesc(clobToString(rs.getClob("History_Desc")));
				province.setHotLevel(rs.getInt("Hot_Level"));
				province.setImageUrl(rs.getString("Image_Url"));
				province.setLocationDesc(rs.getString("Location_Desc"));
				province.setLocationX(rs.getString("Location_X"));
				province.setLocationY(rs.getString("Location_Y"));
				province.setPath(clobToString(rs.getClob("Path")));
				province.setProvinceDesc(clobToString(rs.getClob("Province_Desc")));
				province.setProvinceId(rs.getInt("Province_Id"));
				province.setProvinceName(rs.getString("Province_Name"));
				province.setSrc(rs.getString("Src"));
				province.setTravelDesc(clobToString(rs.getClob("Travel_Desc")));
				return province;
				}
				return null;
				}
			});
	}

	
	public void updateBasic(SysProvince province) {
		String sql = "update sys_province set province_Name=?,location_X=?,location_Y=?,hot_Level=?,location_Desc=? where province_id=?";
		this.getJdbcTemplate().update(sql, new Object[] {province.getProvinceName(),province.getLocationX(),province.getLocationY(),province.getHotLevel(),province.getLocationDesc(),province.getProvinceId()});
	}


	
	public void updatetravelDesc(int provinceId, String travelDesc) {
		String sql = "update sys_province set travel_Desc=? where province_id=?";
		this.getJdbcTemplate().update(sql, new Object[] {travelDesc,provinceId});
	}


	
	public void updateprovinceDesc(int provinceId, String provinceDesc) {
		String sql = "update sys_province set province_Desc=? where province_id=?";
		this.getJdbcTemplate().update(sql, new Object[] {provinceDesc,provinceId});
	}


	
	public void updatehistoryDesc(int provinceId, String historyDesc) {
		String sql = "update sys_province set history_Desc=? where province_id=?";
		this.getJdbcTemplate().update(sql, new Object[] {historyDesc,provinceId});
	}


	
	public void updatecultureDesc(int provinceId, String cultureDesc) {
		String sql = "update sys_province set culture_Desc=? where province_id=?";
		this.getJdbcTemplate().update(sql, new Object[] {cultureDesc,provinceId});
	}


	
	public void updateimage(int provinceId, String images) {
		String sql = "update sys_province set image_url=? where province_id=?";
		this.getJdbcTemplate().update(sql, new Object[] {images,provinceId});
	}


	
	public List findAllProvince() {
		String sql="select * from sys_province" ;

		return getJdbcTemplate().query(sql, new Object[]{}, new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			SysProvince province=new SysProvince();
			province.setCultureDesc(clobToString(rs.getClob("Culture_Desc")));
			province.setHistoryDesc(clobToString(rs.getClob("History_Desc")));
			province.setHotLevel(rs.getInt("Hot_Level"));
			province.setImageUrl(rs.getString("Image_Url"));
			province.setLocationDesc(rs.getString("Location_Desc"));
			province.setLocationX(rs.getString("Location_X"));
			province.setLocationY(rs.getString("Location_Y"));
			province.setPath(clobToString(rs.getClob("Path")));
			province.setProvinceDesc(clobToString(rs.getClob("Province_Desc")));
			province.setProvinceId(rs.getInt("Province_Id"));
			province.setProvinceName(rs.getString("Province_Name"));
			province.setSrc(rs.getString("Src"));
			province.setTravelDesc(clobToString(rs.getClob("Travel_Desc")));
			return province;
		}
	});
	}

}
