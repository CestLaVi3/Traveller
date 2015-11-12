package com.chinasoft.dao.spot;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import oracle.net.aso.s;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.chinasoft.dao.BaseDao;
import com.chinasoft.model.spot.SysScenicSpot;
import com.chinasoft.model.spot.SysSpot;
import com.chinasoft.model.spot.SysSpotType;

public class SpotDaoImpl extends BaseDao implements SpotDao{
	public List findSpotByScienceSpotId(int t) {
		String sql="select * from sys_Spot where spot_id=?" ;

		return getJdbcTemplate().query(sql, new Object[]{t}, new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			SysSpot sysSpot=new SysSpot();
			sysSpot.setDiscountDesc(rs.getString("Discount_Desc"));
			sysSpot.setFitSeason(rs.getString("Fit_Season"));
			sysSpot.setHotLevel(rs.getInt("Hot_Level"));
			sysSpot.setImages(rs.getString("Images"));
			sysSpot.setLocationX(rs.getString("Location_X"));
			sysSpot.setLocationY(rs.getString("Location_Y"));
			sysSpot.setOpenTime(rs.getString("Open_Time"));
			sysSpot.setSpotId(rs.getInt("Spot_Id"));
			sysSpot.setSpotName(rs.getString("Spot_Name"));
			sysSpot.setSpotTypeId(rs.getInt("Spot_Type_Id"));
			sysSpot.setSysSpotId(rs.getInt("Sys_Spot_Id"));
			sysSpot.setTicketDesc(rs.getString("Ticket_Desc"));
			sysSpot.setLocationDesc(rs.getString("Location_Desc"));
			sysSpot.setSpotDesc(clobToString(rs.getClob("Spot_Desc")));
			sysSpot.setStatus(rs.getString("status"));
			return sysSpot;
		}
	});
	}

	
	public SysScenicSpot findSpotById(int t) {
		String sql="select * from sys_scenic_spot where spot_id=?";
		return (SysScenicSpot)getJdbcTemplate().query(sql, new Object[] {t}, new ResultSetExtractor() {			
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				
				if(rs.next()){				
					SysScenicSpot scenicSpot=new SysScenicSpot();
					scenicSpot.setSpotId(rs.getInt("spot_id"));
					scenicSpot.setSpotName(rs.getString("spot_name"));
					scenicSpot.setLocationX(rs.getString("Location_X"));
					scenicSpot.setLocationY(rs.getString("Location_Y"));
					scenicSpot.setImageUrl(rs.getString("Image_Url"));
					scenicSpot.setHotLevel(rs.getInt("Hot_Level"));
					scenicSpot.setLocationDesc(rs.getString("Location_Desc"));
					scenicSpot.setCustom(clobToString(rs.getClob("Custom")));
					scenicSpot.setProvinceId(rs.getInt("Province_Id"));
					scenicSpot.setSpotDesc(clobToString(rs.getClob("Spot_Desc")));
					scenicSpot.setTravelRoute(clobToString(rs.getClob("Travle_Route")));
					scenicSpot.setStatus(rs.getString("status"));
					return scenicSpot;
				}
				return null;
			}
		});
	}


	public List findAllSpot() {
		String sql="select * from sys_spot where status='1'";
		return getJdbcTemplate().query(sql, new Object[]{}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				SysSpot sysSpot=new SysSpot();
				sysSpot.setDiscountDesc(rs.getString("Discount_Desc"));
				sysSpot.setFitSeason(rs.getString("Fit_Season"));
				sysSpot.setHotLevel(rs.getInt("Hot_Level"));
				sysSpot.setImages(rs.getString("Images"));
				sysSpot.setLocationX(rs.getString("Location_X"));
				sysSpot.setLocationY(rs.getString("Location_Y"));
				sysSpot.setOpenTime(rs.getString("Open_Time"));
				sysSpot.setSpotId(rs.getInt("Spot_Id"));
				sysSpot.setSpotName(rs.getString("Spot_Name"));
				sysSpot.setSpotTypeId(rs.getInt("Spot_Type_Id"));
				sysSpot.setSysSpotId(rs.getInt("Sys_Spot_Id"));
				sysSpot.setTicketDesc(rs.getString("Ticket_Desc"));
				sysSpot.setLocationDesc(rs.getString("Location_Desc"));
				sysSpot.setSpotDesc(rs.getString("Spot_Desc"));
				sysSpot.setStatus(rs.getString("status"));
				return sysSpot;
			}
		});
	}

	public List findAllSpots() {
		String sql="select * from sys_spot";
		return getJdbcTemplate().query(sql, new Object[]{}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				SysSpot sysSpot=new SysSpot();
				sysSpot.setDiscountDesc(rs.getString("Discount_Desc"));
				sysSpot.setFitSeason(rs.getString("Fit_Season"));
				sysSpot.setHotLevel(rs.getInt("Hot_Level"));
				sysSpot.setImages(rs.getString("Images"));
				sysSpot.setLocationX(rs.getString("Location_X"));
				sysSpot.setLocationY(rs.getString("Location_Y"));
				sysSpot.setOpenTime(rs.getString("Open_Time"));
				sysSpot.setSpotId(rs.getInt("Spot_Id"));
				sysSpot.setSpotName(rs.getString("Spot_Name"));
				sysSpot.setSpotTypeId(rs.getInt("Spot_Type_Id"));
				sysSpot.setSysSpotId(rs.getInt("Sys_Spot_Id"));
				sysSpot.setTicketDesc(rs.getString("Ticket_Desc"));
				sysSpot.setLocationDesc(rs.getString("Location_Desc"));
				sysSpot.setSpotDesc(rs.getString("Spot_Desc"));
				sysSpot.setStatus(rs.getString("status"));
				return sysSpot;
			}
		});
	}
	
	public void forbitSpot(int sysSpotId) {
		String sql = "update sys_spot set status='0' where sys_Spot_Id=?";
		this.getJdbcTemplate().update(sql, new Object[] {sysSpotId});
	}


	
	public void unForbitSpot(int sysSpotId) {
		String sql = "update sys_spot set status='1' where sys_Spot_Id=?";
		this.getJdbcTemplate().update(sql, new Object[] {sysSpotId});
	}


	
	public SysSpot findbyId(int sysSpotId) {
		String sql="select * from sys_spot where sys_spot_id=?";
		return (SysSpot)getJdbcTemplate().query(sql, new Object[] {sysSpotId}, new ResultSetExtractor() {			
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {				
				if(rs.next()){				
					SysSpot sysSpot=new SysSpot();
					sysSpot.setDiscountDesc(rs.getString("Discount_Desc"));
					sysSpot.setFitSeason(rs.getString("Fit_Season"));
					sysSpot.setHotLevel(rs.getInt("Hot_Level"));
					sysSpot.setImages(rs.getString("Images"));
					sysSpot.setLocationX(rs.getString("Location_X"));
					sysSpot.setLocationY(rs.getString("Location_Y"));
					sysSpot.setOpenTime(rs.getString("Open_Time"));
					sysSpot.setSpotId(rs.getInt("Spot_Id"));
					sysSpot.setSpotName(rs.getString("Spot_Name"));
					sysSpot.setSpotTypeId(rs.getInt("Spot_Type_Id"));
					sysSpot.setSysSpotId(rs.getInt("Sys_Spot_Id"));
					sysSpot.setTicketDesc(rs.getString("Ticket_Desc"));
					sysSpot.setLocationDesc(rs.getString("Location_Desc"));
					sysSpot.setSpotDesc(rs.getString("Spot_Desc"));
					sysSpot.setStatus(rs.getString("status"));
					return sysSpot;
				}
				return null;
			}
		});
	}


	
	public List findAllspotType() {
		String sql="select * from sys_spot_type";
		return getJdbcTemplate().query(sql, new Object[]{}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				SysSpotType spotType=new SysSpotType();
				spotType.setSportTypeId(rs.getInt("Sport_Type_Id"));
				spotType.setSportTypeName(rs.getString("Sport_Type_Name"));
				return spotType;
			}
		});
	}


	
	public void updateBasic(SysSpot spot) {
		String sql = "update sys_spot set spot_name=?,location_X=?,location_y=?,location_Desc=?,hot_Level=?,fit_Season=?,ticket_Desc=?,discount_Desc=?,open_Time=?,spot_type_id=? where sys_Spot_Id=?";
		this.getJdbcTemplate().update(sql, new Object[] {spot.getSpotName(),spot.getLocationX(),spot.getLocationY(),spot.getLocationDesc(),spot.getHotLevel(),spot.getFitSeason(),spot.getTicketDesc(),spot.getDiscountDesc(),spot.getOpenTime(),spot.getSpotTypeId(),spot.getSysSpotId()});
	}


	
	public void updateSpotDesc(int sysSpotId,String spotDesc) {
		String sql = "update sys_spot set spot_Desc=? where sys_Spot_Id=?";
		this.getJdbcTemplate().update(sql, new Object[] {spotDesc,sysSpotId});
	}



	public void updateImages(int sysSpotId, String images) {
		String sql = "update sys_spot set images=? where sys_Spot_Id=?";
		this.getJdbcTemplate().update(sql, new Object[] {images,sysSpotId});
	}


	
	public void saveBasic(SysSpot spot) {
		String sql = "insert into sys_spot(sys_spot_id,spot_name,location_X,location_y,location_Desc,hot_Level,fit_Season,ticket_Desc,discount_Desc,open_Time,spot_id,spot_type_id,status) values(seq_user.nextval,?,?,?,?,?,?,?,?,?,?,?,'1')";
		this.getJdbcTemplate().update(sql, new Object[] {spot.getSpotName(),spot.getLocationX(),spot.getLocationY(),spot.getLocationDesc(),spot.getHotLevel(),spot.getFitSeason(),spot.getTicketDesc(),spot.getDiscountDesc(),spot.getOpenTime(),spot.getSpotId(),spot.getSpotTypeId()});
	}

}
