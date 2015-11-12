package com.chinasoft.dao.scienceSpot;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import com.chinasoft.dao.BaseDao;
import com.chinasoft.model.spot.RlSpotFamousHuman;
import com.chinasoft.model.spot.RlSpotFamousMenu;
import com.chinasoft.model.spot.RlSpotSpecialty;
import com.chinasoft.model.spot.SysProvince;
import com.chinasoft.model.spot.SysScenicSpot;
import com.chinasoft.model.spot.SysSpot;
import com.chinasoft.model.strategy.BusTravelDesc;
import com.chinasoft.model.user.SysUser;

public class ScienceSpotDaoImpl extends BaseDao implements ScienceSpotDao{

	public List findScienceSpotByHotLevel() {
		String sql="select * from (select * from sys_scenic_Spot order by hot_level desc) where rownum<8";
		return getJdbcTemplate().query(sql, new Object[]{}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				SysScenicSpot scenicSpot=new SysScenicSpot();
				scenicSpot.setCustom(clobToString(rs.getClob("Custom")));
				scenicSpot.setHotLevel(rs.getInt("Hot_Level"));
				scenicSpot.setImageUrl(rs.getString("Image_Url"));
				scenicSpot.setLocationDesc(rs.getString("Location_Desc"));
				scenicSpot.setLocationX(rs.getString("Location_X"));
				scenicSpot.setLocationY(rs.getString("Location_y"));
				scenicSpot.setProvinceId(rs.getInt("Province_Id"));
				scenicSpot.setSpotDesc(clobToString(rs.getClob("Spot_Desc")));
				scenicSpot.setSpotId(rs.getInt("Spot_Id"));
				scenicSpot.setSpotName(rs.getString("Spot_Name"));
				scenicSpot.setTravelRoute(clobToString(rs.getClob("Travle_Route")));
				scenicSpot.setStatus(rs.getString("status"));
				return scenicSpot;
			}
		});
	}
	public List findSpotByScienceSpotId(List list) {
			String sql="" ;
			for(int i=0;i<list.size();i++){
				SysScenicSpot aScenicSpot=(SysScenicSpot)list.get(i);				
				sql=sql+"select * from (select * from sys_spot order by hot_level desc) where spot_id='"+aScenicSpot.getSpotId()+"' and rownum<6 union all ";
			}	
			sql=sql.substring(0, sql.length()-11);
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
				sysSpot.setSpotDesc(clobToString(rs.getClob("Spot_Desc")));
				sysSpot.setStatus(rs.getString("status"));
				return sysSpot;
			}
		});
	}

	
	public List findSpotByCount(String c) {
		String sql=null;		
		if(c.equals("all")){
			sql="select * from sys_scenic_Spot where status='1' order by hot_level desc";
		}
		else{			
			sql="select * from (select * from sys_scenic_Spot order by hot_level desc) where rownum<"+(Integer.parseInt(c)+1);
		}
		return getJdbcTemplate().query(sql, new Object[]{}, new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			SysScenicSpot scenicSpot=new SysScenicSpot();
			scenicSpot.setCustom(clobToString(rs.getClob("Custom")));
			scenicSpot.setHotLevel(rs.getInt("Hot_Level"));
			scenicSpot.setImageUrl(rs.getString("Image_Url"));
			scenicSpot.setLocationDesc(rs.getString("Location_Desc"));
			scenicSpot.setLocationX(rs.getString("Location_X"));
			scenicSpot.setLocationY(rs.getString("Location_y"));
			scenicSpot.setProvinceId(rs.getInt("Province_Id"));
			scenicSpot.setSpotDesc(clobToString(rs.getClob("Spot_Desc")));
			scenicSpot.setSpotId(rs.getInt("Spot_Id"));
			scenicSpot.setSpotName(rs.getString("Spot_Name"));
			scenicSpot.setTravelRoute(clobToString(rs.getClob("Travle_Route")));
			return scenicSpot;
		}
	});
	}
	
	public SysScenicSpot findById(int spotId) {
		String sql="select * from sys_scenic_spot where spot_id=?";
		return (SysScenicSpot)getJdbcTemplate().query(sql, new Object[] {spotId},new ResultSetExtractor() {					
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					SysScenicSpot scenicSpot=new SysScenicSpot();
					scenicSpot.setCustom(clobToString(rs.getClob("Custom")));
					scenicSpot.setHotLevel(rs.getInt("Hot_Level"));
					scenicSpot.setImageUrl(rs.getString("Image_Url"));
					scenicSpot.setLocationDesc(rs.getString("Location_Desc"));
					scenicSpot.setLocationX(rs.getString("Location_X"));
					scenicSpot.setLocationY(rs.getString("Location_y"));
					scenicSpot.setProvinceId(rs.getInt("Province_Id"));
					scenicSpot.setSpotDesc(clobToString(rs.getClob("Spot_Desc")));
					scenicSpot.setSpotId(rs.getInt("Spot_Id"));
					scenicSpot.setSpotName(rs.getString("Spot_Name"));
					scenicSpot.setTravelRoute(clobToString(rs.getClob("Travle_Route")));
					scenicSpot.setStatus(rs.getString("status"));
					return scenicSpot;
				}
				return null;
			}
		});
	}
	
	public void updateBasic(SysScenicSpot scenicSpot) {
		String sql = "update sys_scenic_spot set spot_Name=?,province_Id=?,location_X=?,location_Y=?,hot_Level=?,location_Desc=? where spot_ID=?";
		this.getJdbcTemplate().update(sql, new Object[] {scenicSpot.getSpotName(),scenicSpot.getProvinceId(),scenicSpot.getLocationX(),scenicSpot.getLocationY(),scenicSpot.getHotLevel(),scenicSpot.getLocationDesc(),scenicSpot.getSpotId()});
	}
	
	public List findhumanbyspotId(int spotId) {
		String sql="select * from rl_spot_famous_human where spot_id=?";
		return getJdbcTemplate().query(sql, new Object[]{spotId}, new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			RlSpotFamousHuman spotFamousHuman=new RlSpotFamousHuman();
			spotFamousHuman.setBirthday(rs.getDate("Birthday"));
			spotFamousHuman.setDieDay(rs.getDate("DieDay"));
			spotFamousHuman.setHumanDesc(clobToString(rs.getClob("Human_Desc")));
			spotFamousHuman.setHumanId(rs.getInt("Human_Id"));
			spotFamousHuman.setHumanName(rs.getString("Human_Name"));
			spotFamousHuman.setImages(rs.getString("Images"));
			spotFamousHuman.setSpotId(rs.getInt("Spot_Id"));
			return spotFamousHuman;
		}
	});
	}
	
	public void updateHuman(RlSpotFamousHuman spotFamousHuman) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String sql = "update rl_spot_famous_human set birthday=to_date(?,'yyyy-mm-dd'),dieday=to_date(?,'yyyy-mm-dd'),human_desc=?,human_name=?,images=? where spot_id=? and human_id=?";
		this.getJdbcTemplate().update(sql, new Object[] {df.format(spotFamousHuman.getBirthday()),df.format(spotFamousHuman.getDieDay()),spotFamousHuman.getHumanDesc(),spotFamousHuman.getHumanName(),spotFamousHuman.getImages(),spotFamousHuman.getSpotId(),spotFamousHuman.getHumanId()});
	}
	
	public void drophumanbyhumanId(int humanId) {
		String sql = "delete from rl_spot_famous_human where human_id=?";
		this.getJdbcTemplate().update(sql, new Object[] {humanId});
	}
	
	public RlSpotFamousHuman findhumanbyHumanId(int humanId) {
		String sql="select * from rl_spot_famous_human where human_id=?";
		return (RlSpotFamousHuman)getJdbcTemplate().query(sql, new Object[] {humanId},new ResultSetExtractor() {					
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					RlSpotFamousHuman spotFamousHuman=new RlSpotFamousHuman();
					spotFamousHuman.setBirthday(rs.getDate("Birthday"));
					spotFamousHuman.setDieDay(rs.getDate("DieDay"));
					spotFamousHuman.setHumanDesc(clobToString(rs.getClob("Human_Desc")));
					spotFamousHuman.setHumanId(rs.getInt("Human_Id"));
					spotFamousHuman.setHumanName(rs.getString("Human_Name"));
					spotFamousHuman.setImages(rs.getString("Images"));
					spotFamousHuman.setSpotId(rs.getInt("Spot_Id"));
					return spotFamousHuman;
				}
				return null;
			}
		});
	}
	
	public void saveHuman(RlSpotFamousHuman spotFamousHuman) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String sql = "insert into rl_spot_famous_human(human_id,birthday,dieday,human_desc,human_name,spot_id,images) values(seq_user.nextval,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),?,?,?,?)";
		this.getJdbcTemplate().update(sql, new Object[] {df.format(spotFamousHuman.getBirthday()),df.format(spotFamousHuman.getDieDay()),spotFamousHuman.getHumanDesc(),spotFamousHuman.getHumanName(),spotFamousHuman.getSpotId(),spotFamousHuman.getImages()});
	}
	
	public List findmenubyspotId(int spotId) {
		String sql="select * from rl_spot_famous_menu where spot_id=?";
		return getJdbcTemplate().query(sql, new Object[]{spotId}, new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			RlSpotFamousMenu spotFamousMenu=new RlSpotFamousMenu();
			spotFamousMenu.setMenuDesc(clobToString(rs.getClob("Menu_Desc")));
			spotFamousMenu.setMenuId(rs.getInt("Menu_Id"));
			spotFamousMenu.setMenuImages(rs.getString("Menu_Images"));
			spotFamousMenu.setMenuName(rs.getString("Menu_Nam"));
			spotFamousMenu.setSpotId(rs.getInt("spot_id"));
			return spotFamousMenu;
		}
	});
	}
	
	public void updateMenu(RlSpotFamousMenu spotFamousMenu) {
		String sql = "update rl_spot_famous_menu set menu_nam=?,menu_desc=?,menu_images=? where menu_id=? and spot_id=?";
		this.getJdbcTemplate().update(sql, new Object[] {spotFamousMenu.getMenuName(),spotFamousMenu.getMenuDesc(),spotFamousMenu.getMenuImages(),spotFamousMenu.getMenuId(),spotFamousMenu.getSpotId()});
	}
	
	public RlSpotFamousMenu findMenubyHumanId(int menuId) {
		String sql="select * from rl_spot_famous_menu where menu_id=?";
		return (RlSpotFamousMenu)getJdbcTemplate().query(sql, new Object[] {menuId},new ResultSetExtractor() {					
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					RlSpotFamousMenu spotFamousMenu=new RlSpotFamousMenu();
					spotFamousMenu.setMenuDesc(clobToString(rs.getClob("Menu_Desc")));
					spotFamousMenu.setMenuId(rs.getInt("Menu_Id"));
					spotFamousMenu.setMenuImages(rs.getString("Menu_Images"));
					spotFamousMenu.setMenuName(rs.getString("Menu_Nam"));
					spotFamousMenu.setSpotId(rs.getInt("spot_id"));
					return spotFamousMenu;
				}
				return null;
			}
		});
	}
	
	public void deleteById(int menuId) {
		String sql = "delete from rl_spot_famous_menu where menu_id=?";
		this.getJdbcTemplate().update(sql, new Object[] {menuId});
	}
	
	public void saveMenu(RlSpotFamousMenu spotFamousMenu) {
		String sql = "insert into rl_spot_famous_menu(menu_id,menu_nam,menu_desc,menu_images,spot_id) values(seq_user.nextval,?,?,?,?)";
		this.getJdbcTemplate().update(sql, new Object[] {spotFamousMenu.getMenuName(),spotFamousMenu.getMenuDesc(),spotFamousMenu.getMenuImages(),spotFamousMenu.getSpotId()});
	}
	
	public List findSpecialtybyspotId(int spotId) {
		String sql="select * from rl_spot_SPECIALTY where spot_id=?";
		return getJdbcTemplate().query(sql, new Object[]{spotId}, new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			RlSpotSpecialty specialty=new RlSpotSpecialty();
			specialty.setImages(rs.getString("Images"));
			specialty.setSpecialtyDesc(clobToString(rs.getClob("Specialty_Desc")));
			specialty.setSpecialtyId(rs.getInt("Specialty_Id"));
			specialty.setSpecialtyName(rs.getString("Specialty_Name"));
			specialty.setSpotId(rs.getInt("Spot_Id"));
			return specialty;
		}
	});
	}
	
	public void updateSpecialty(RlSpotSpecialty specialty) {
		String sql = "update rl_spot_Specialty set specialty_name=?,specialty_desc=?,images=? where specialty_id=? and spot_id=?";
		this.getJdbcTemplate().update(sql, new Object[] {specialty.getSpecialtyName(),specialty.getSpecialtyDesc(),specialty.getImages(),specialty.getSpecialtyId(),specialty.getSpotId()});
	}
	
	public RlSpotSpecialty findSpecialtybyId(int specialId) {
		String sql="select * from rl_spot_Specialty where Specialty_id=?";
		return (RlSpotSpecialty)getJdbcTemplate().query(sql, new Object[] {specialId},new ResultSetExtractor() {					
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					RlSpotSpecialty specialty=new RlSpotSpecialty();
					specialty.setImages(rs.getString("images"));
					specialty.setSpecialtyDesc(clobToString(rs.getClob("specialty_Desc")));
					specialty.setSpecialtyId(rs.getInt("Specialty_Id"));
					specialty.setSpecialtyName(rs.getString("Specialty_Name"));
					specialty.setSpotId(rs.getInt("Spot_Id"));
					return specialty;
					
				}
				return null;
			}
		});
	}
	
	public void deleteSpecialtybyId(int specialtyId) {
		String sql = "delete from rl_spot_specialty where specialty_id=?";
		this.getJdbcTemplate().update(sql, new Object[] {specialtyId});
	}
	
	public void saveSpecialty(RlSpotSpecialty specialty) {
		String sql = "insert into rl_spot_Specialty(specialty_id,specialty_name,specialty_desc,images,spot_id) values(seq_user.nextval,?,?,?,?)";
		this.getJdbcTemplate().update(sql, new Object[] {specialty.getSpecialtyName(),specialty.getSpecialtyDesc(),specialty.getImages(),specialty.getSpotId()});
	}
	
	public void updateCustombyspotId(int spotId, String custom) {
		String sql = "update sys_scenic_spot set custom=? where spot_id=?";
		this.getJdbcTemplate().update(sql, new Object[] {custom,spotId});
	}
	
	public void updateSpotDescbyId(int spotId, String spotDesc) {
		String sql = "update sys_scenic_spot set spot_Desc=? where spot_id=?";
		this.getJdbcTemplate().update(sql, new Object[] {spotDesc,spotId});
	}
	
	public void updateimageBySpotId(int spotId, String images) {
		String sql = "update sys_scenic_spot set image_url=? where spot_id=?";
		this.getJdbcTemplate().update(sql, new Object[] {images,spotId});
	}

	public void updateTravleRoutebyspotId(int spotId, String travleRoute) {
		String sql = "update sys_scenic_spot set travle_Route=? where spot_id=?";
		this.getJdbcTemplate().update(sql, new Object[] {travleRoute,spotId});
	}
	
	public void saveSpotBasic(SysScenicSpot scenicSpot) {
		String sql = "insert into sys_scenic_spot(spot_id,province_Id,spot_Name,location_X,location_y,hot_Level,location_Desc,status) values(seq_user.nextval,?,?,?,?,?,?,'1')";
		this.getJdbcTemplate().update(sql, new Object[] {scenicSpot.getProvinceId(),scenicSpot.getSpotName(),scenicSpot.getLocationX(),scenicSpot.getLocationY(),scenicSpot.getHotLevel(),scenicSpot.getLocationDesc()});
	}
	
	public void forbitScenicSpot(int spotId) {
		String sql = "update sys_scenic_spot set status='0' where spot_id=?";
		this.getJdbcTemplate().update(sql, new Object[] {spotId});
	}
	
	public void unForbitScenicSpot(int spotId) {
		String sql = "update sys_scenic_spot set status='1' where spot_id=?";
		this.getJdbcTemplate().update(sql, new Object[] {spotId});
	}
	
	public List findAllScenicSpot() {
		String sql="select * from sys_scenic_spot";
		return getJdbcTemplate().query(sql, new Object[]{}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				SysScenicSpot scenicSpot=new SysScenicSpot();
				scenicSpot.setCustom(clobToString(rs.getClob("Custom")));
				scenicSpot.setHotLevel(rs.getInt("Hot_Level"));
				scenicSpot.setImageUrl(rs.getString("Image_Url"));
				scenicSpot.setLocationDesc(rs.getString("Location_Desc"));
				scenicSpot.setLocationX(rs.getString("Location_X"));
				scenicSpot.setLocationY(rs.getString("Location_y"));
				scenicSpot.setProvinceId(rs.getInt("Province_Id"));
				scenicSpot.setSpotDesc(clobToString(rs.getClob("Spot_Desc")));
				scenicSpot.setSpotId(rs.getInt("Spot_Id"));
				scenicSpot.setSpotName(rs.getString("Spot_Name"));
				scenicSpot.setTravelRoute(clobToString(rs.getClob("Travle_Route")));
				scenicSpot.setStatus(rs.getString("status"));
				return scenicSpot;
			}
		});
	}
	
	public List findbyProvinceId(int provinceId) {
		String sql="select * from sys_scenic_spot where province_ID=?";
		return getJdbcTemplate().query(sql, new Object[]{provinceId}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				SysScenicSpot scenicSpot=new SysScenicSpot();
				scenicSpot.setCustom(clobToString(rs.getClob("Custom")));
				scenicSpot.setHotLevel(rs.getInt("Hot_Level"));
				scenicSpot.setImageUrl(rs.getString("Image_Url"));
				scenicSpot.setLocationDesc(rs.getString("Location_Desc"));
				scenicSpot.setLocationX(rs.getString("Location_X"));
				scenicSpot.setLocationY(rs.getString("Location_y"));
				scenicSpot.setProvinceId(rs.getInt("Province_Id"));
				scenicSpot.setSpotDesc(clobToString(rs.getClob("Spot_Desc")));
				scenicSpot.setSpotId(rs.getInt("Spot_Id"));
				scenicSpot.setSpotName(rs.getString("Spot_Name"));
				scenicSpot.setTravelRoute(clobToString(rs.getClob("Travle_Route")));
				scenicSpot.setStatus(rs.getString("status"));
				return scenicSpot;
			}
		});
	}
	
	
	
	
}
