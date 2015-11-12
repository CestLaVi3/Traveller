package com.chinasoft.dao.travelDesc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.crypto.Data;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.chinasoft.dao.BaseDao;
import com.chinasoft.model.general.BusCollect;
import com.chinasoft.model.general.BusPhase;
import com.chinasoft.model.main.SysHref;
import com.chinasoft.model.strategy.BusTravelDesc;
import com.chinasoft.model.user.SysUser;

public class TravelDescDaoImpl extends BaseDao implements TravelDescDao {
	public List findDescByCondition(String s) {
		String sql="select * from (select a.*,b.petname,b.image_url,c.spot_name from bus_travel_desc a inner join sys_user b on b.user_id=a.userid inner join sys_scenic_spot c on c.spot_id=a.spot_id order by "+s+" desc) where rownum<5";
		return getJdbcTemplate().query(sql, new Object[]{}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				BusTravelDesc travelDesc=new BusTravelDesc();
				travelDesc.setTravelDescId(rs.getInt("Travel_Desc_Id"));
				travelDesc.setCollectNum(rs.getInt("Collect_Num"));
				travelDesc.setCommentNum(rs.getInt("Comment_Num"));
				travelDesc.setCreateTime(rs.getDate("Create_Time"));
				travelDesc.setDescContent(clobToString(rs.getClob("Desc_Content")));
				travelDesc.setDescTitle(rs.getString("Desc_Title"));
				travelDesc.setImages(rs.getString("Images"));
				
				travelDesc.setInvestNum(rs.getInt("Invest_Num"));
				travelDesc.setPraiseNum(rs.getInt("Praise_Num"));
				travelDesc.setSpotId(rs.getInt("Spot_Id"));
				
				travelDesc.setUserId(rs.getInt("UserId"));
				travelDesc.setImageUrl(rs.getString("Image_Url"));
				travelDesc.setSpotName(rs.getString("Spot_Name"));
				travelDesc.setPetName(rs.getString("PetName"));
				return travelDesc;
			}
		});
	}

	public List findDescByPage(int t1,int t2,int t3,String descTitle) {
		Integer tInteger1=t1*t2;
		Integer tInteger2=(t1-1)*t2;
		String sql="";
		sql+="select aa.*,bb.coun from (select * from (select rownum t,a.*,b.petname,b.image_url,c.spot_name from (select * from bus_travel_desc where 1=1";
		if(t3==0){
		
		}
		else{
			sql+=" and spot_id="+t3+"";
		}
		if(descTitle==null){}
		else{
			sql+=" and desc_Title like '%"+descTitle+"%'";
		}
		sql+=") a inner join sys_user b on b.user_id=a.userid inner join sys_scenic_spot c on c.spot_id=a.spot_id order by travel_desc_id desc) where t<=? and t>?) aa,(select count(*) as coun from bus_travel_desc where 1=1";
		if(t3==0){
			
		}
		else{
			sql+=" and spot_id="+t3+"";
		}
		if(descTitle==null){}
		else{
			sql+=" and desc_Title like '%"+descTitle+"%'";
		}
		sql+=") bb";
		return getJdbcTemplate().query(sql, new Object[]{tInteger1,tInteger2}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				BusTravelDesc travelDesc=new BusTravelDesc();
				travelDesc.setTravelDescId(rs.getInt("Travel_Desc_Id"));
				travelDesc.setCollectNum(rs.getInt("Collect_Num"));
				travelDesc.setCommentNum(rs.getInt("Comment_Num"));
				travelDesc.setCreateTime(rs.getDate("Create_Time"));
				travelDesc.setDescContent(clobToString(rs.getClob("Desc_Content")));
				travelDesc.setDescTitle(rs.getString("Desc_Title"));
				travelDesc.setImages(rs.getString("Images"));
				
				travelDesc.setInvestNum(rs.getInt("Invest_Num"));
				travelDesc.setPraiseNum(rs.getInt("Praise_Num"));
				travelDesc.setSpotId(rs.getInt("Spot_Id"));
				
				travelDesc.setUserId(rs.getInt("UserId"));
				travelDesc.setImageUrl(rs.getString("Image_Url"));
				travelDesc.setSpotName(rs.getString("Spot_Name"));
				travelDesc.setPetName(rs.getString("PetName"));
				travelDesc.setCount(rs.getInt("Coun"));
				return travelDesc;
			}
		});
	}

	
	public BusTravelDesc findDescById(int d) {		
		String sql="select a.*,b.petname,b.image_url,c.spot_name from bus_travel_desc a inner join sys_user b on b.user_id=a.userid inner join sys_scenic_spot c on c.spot_id=a.spot_id where a.travel_desc_id=?";
		return (BusTravelDesc)getJdbcTemplate().query(sql, new Object[] {d}, new ResultSetExtractor() {			
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if(rs.next()){
					BusTravelDesc travelDesc=new BusTravelDesc();
					travelDesc.setTravelDescId(rs.getInt("Travel_Desc_Id"));
					travelDesc.setCollectNum(rs.getInt("Collect_Num"));
					travelDesc.setCommentNum(rs.getInt("Comment_Num"));
					travelDesc.setCreateTime(rs.getDate("Create_Time"));
					travelDesc.setDescContent(clobToString(rs.getClob("Desc_Content")));
					travelDesc.setDescTitle(rs.getString("Desc_Title"));
					travelDesc.setImages(rs.getString("Images"));
					
					travelDesc.setInvestNum(rs.getInt("Invest_Num"));
					travelDesc.setPraiseNum(rs.getInt("Praise_Num"));
					travelDesc.setSpotId(rs.getInt("Spot_Id"));
					
					travelDesc.setUserId(rs.getInt("UserId"));
					travelDesc.setImageUrl(rs.getString("Image_Url"));
					travelDesc.setSpotName(rs.getString("Spot_Name"));
					travelDesc.setPetName(rs.getString("PetName"));
					return travelDesc;
				}
				return null;
			}
		});
	}

	
	public List findRelationSpotById(int id,int count) {
		String sql="select * from (select a.*,b.petname,b.image_url,c.spot_name from bus_travel_desc a inner join sys_user b on b.user_id=a.userid inner join sys_scenic_spot c on c.spot_id=a.spot_id order by a.travel_desc_id asc) where spot_id=? and rownum<=?";
		
		return getJdbcTemplate().query(sql, new Object[]{id,count}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					BusTravelDesc travelDesc=new BusTravelDesc();
					travelDesc.setTravelDescId(rs.getInt("Travel_Desc_Id"));
					travelDesc.setCollectNum(rs.getInt("Collect_Num"));
					travelDesc.setCommentNum(rs.getInt("Comment_Num"));
					travelDesc.setCreateTime(rs.getDate("Create_Time"));
					travelDesc.setDescContent(clobToString(rs.getClob("Desc_Content")));
					travelDesc.setDescTitle(rs.getString("Desc_Title"));
					travelDesc.setImages(rs.getString("Images"));
					
					travelDesc.setInvestNum(rs.getInt("Invest_Num"));
					travelDesc.setPraiseNum(rs.getInt("Praise_Num"));
					travelDesc.setSpotId(rs.getInt("Spot_Id"));
					
					travelDesc.setUserId(rs.getInt("UserId"));
					travelDesc.setImageUrl(rs.getString("Image_Url"));
					travelDesc.setSpotName(rs.getString("Spot_Name"));
					travelDesc.setPetName(rs.getString("PetName"));
					return travelDesc;
				
			}
		});
	}

	
	public void praiseTravelDesc(int tid,int userid,int num,String nt) {
		String sql = "update bus_travel_desc set praise_num=(select praise_num+1 from bus_travel_desc where Travel_Desc_Id=?) where Travel_Desc_Id=?";
		this.getJdbcTemplate().update(sql, new Object[] {tid,tid});
		
		String sql2 = "insert into bus_phase(phase_id,user_id,phase_type,rel_id,create_time) values(seq_user.nextval,?,'0',?,to_date(?,'yyyy-mm-dd'))";
		this.getJdbcTemplate().update(sql2, new Object[] {userid,tid,nt});
	}
	
	public void collectTravelDesc(int tid,int userid,int num,String nt) {
		String sql = "update bus_travel_desc set collect_num=(select collect_num+1 from bus_travel_desc where Travel_Desc_Id=?) where Travel_Desc_Id=?";
		this.getJdbcTemplate().update(sql, new Object[] {tid,tid});
		
		String sql2 = "insert into bus_collect(collect_id,user_id,collect_type,rel_id,create_time) values(seq_user.nextval,?,'0',?,to_date(?,'yyyy-mm-dd'))";
		this.getJdbcTemplate().update(sql2, new Object[] {userid,tid,nt});
	}
	
	public List findDescByUser(int userid) {
		String sql="select * from (select a.*,b.petname,b.image_url,c.spot_name from bus_travel_desc a inner join sys_user b on b.user_id=a.userid inner join sys_scenic_spot c on c.spot_id=a.spot_id order by create_time desc) where userid=?";
		
		return getJdbcTemplate().query(sql, new Object[]{userid}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					BusTravelDesc travelDesc=new BusTravelDesc();
					travelDesc.setTravelDescId(rs.getInt("Travel_Desc_Id"));
					travelDesc.setCollectNum(rs.getInt("Collect_Num"));
					travelDesc.setCommentNum(rs.getInt("Comment_Num"));
					travelDesc.setCreateTime(rs.getDate("Create_Time"));
					travelDesc.setDescContent(clobToString(rs.getClob("Desc_Content")));
					travelDesc.setDescTitle(rs.getString("Desc_Title"));
					travelDesc.setImages(rs.getString("Images"));
					
					travelDesc.setInvestNum(rs.getInt("Invest_Num"));
					travelDesc.setPraiseNum(rs.getInt("Praise_Num"));
					travelDesc.setSpotId(rs.getInt("Spot_Id"));
					
					travelDesc.setUserId(rs.getInt("UserId"));
					travelDesc.setImageUrl(rs.getString("Image_Url"));
					travelDesc.setSpotName(rs.getString("Spot_Name"));
					travelDesc.setPetName(rs.getString("PetName"));
					return travelDesc;
				
			}
		});
	}



	public void saveTravelDesc(BusTravelDesc travelDesc) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String sql = "insert into bus_travel_desc(travel_Desc_Id,spot_Id,userId,create_Time,desc_Title,desc_Content,images,invest_num,comment_num,collect_num,praise_num) values(seq_user.nextval,?,?,to_date(?,'yyyy-mm-dd'),?,?,?,0,0,0,0)";
		this.getJdbcTemplate().update(sql, new Object[] {travelDesc.getSpotId(),travelDesc.getUserId(),df.format(travelDesc.getCreateTime()),travelDesc.getDescTitle(),travelDesc.getDescContent(),travelDesc.getImages()});
		
	}

	
	public BusPhase findBusPhaseByUserId(int uid,int did,String type) {
		String sql="select * from bus_phase where user_id=? and rel_id=? and phase_type=?";
		return (BusPhase)getJdbcTemplate().query(sql, new Object[] {uid,did,type}, new ResultSetExtractor() {			
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if(rs.next()){
					BusPhase busPhase=new BusPhase();
					busPhase.setPhaseId(rs.getInt("Phase_Id"));
					busPhase.setRelId(rs.getInt("Rel_Id"));
					busPhase.setUserId(rs.getInt("User_Id"));
					busPhase.setPhaseType(rs.getString("Phase_Type"));
					busPhase.setCreateTime(rs.getDate("Create_Time"));
					return busPhase;
				}
				return null;
			}
		});
	}

	
	public BusCollect findBusCollectByUserId(int uid,int did,String type) {
		String sql="select * from bus_collect where user_id=? and rel_id=? and collect_type=?";
		return (BusCollect)getJdbcTemplate().query(sql, new Object[] {uid,did,type}, new ResultSetExtractor() {			
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if(rs.next()){
					BusCollect busCollect=new BusCollect();
					busCollect.setCollectId(rs.getInt("Collect_Id"));
					busCollect.setCollectType(rs.getString("Collect_Type"));
					busCollect.setRelId(rs.getInt("Rel_Id"));
					busCollect.setUserId(rs.getInt("User_Id"));
					busCollect.setCreateTime(rs.getDate("Create_Time"));
					return busCollect;
				}
				return null;
			}
		});
	}

	
	public void deleteTravelDesc(int travelDescId,int userId) {				
		String sql2 = "delete from bus_phase where rel_Id=? and phase_type='0'";
		this.getJdbcTemplate().update(sql2, new Object[] {travelDescId});
		String sql3 = "delete from bus_collect where rel_Id=? and collect_type='0'";
		this.getJdbcTemplate().update(sql3, new Object[] {travelDescId});
		String sql4 = "delete from bus_reply where comment_Id=(select comment_id from bus_comment where rel_id=? and comment_type='0')";
		this.getJdbcTemplate().update(sql4, new Object[] {travelDescId});
		String sql5 = "delete from bus_comment where rel_Id=? and comment_type='0'";
		this.getJdbcTemplate().update(sql5, new Object[] {travelDescId});
		String sql = "delete from bus_travel_desc where travel_Desc_Id=? and userid=?";
		this.getJdbcTemplate().update(sql, new Object[] {travelDescId,userId});
	
	}

	
	public void updateTravelDesc(BusTravelDesc travelDesc) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String sql = "update bus_travel_desc set spot_Id=?,userId=?,create_Time=to_date(?,'yyyy-mm-dd'),desc_Title=?,desc_Content=?,images=? where travel_desc_id=?";
		this.getJdbcTemplate().update(sql, new Object[] {travelDesc.getSpotId(),travelDesc.getUserId(),df.format(travelDesc.getCreateTime()),travelDesc.getDescTitle(),travelDesc.getDescContent(),travelDesc.getImages(),travelDesc.getTravelDescId()});
		
	}

	
	public List findDescByUserCollect(int userId) {
		String sql="select a.*,d.petname,d.image_url,c.spot_name from bus_travel_desc a inner join (select rel_id from bus_collect where collect_type='5' and user_id=?) b on a.userid=b.rel_id inner join sys_user d on d.user_id=a.userid inner join sys_scenic_spot c on c.spot_id=a.spot_id order by create_time desc";		
		return getJdbcTemplate().query(sql, new Object[]{userId}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					BusTravelDesc travelDesc=new BusTravelDesc();
					travelDesc.setTravelDescId(rs.getInt("Travel_Desc_Id"));
					travelDesc.setCollectNum(rs.getInt("Collect_Num"));
					travelDesc.setCommentNum(rs.getInt("Comment_Num"));
					travelDesc.setCreateTime(rs.getDate("Create_Time"));
					travelDesc.setDescContent(clobToString(rs.getClob("Desc_Content")));
					travelDesc.setDescTitle(rs.getString("Desc_Title"));
					travelDesc.setImages(rs.getString("Images"));
					
					travelDesc.setInvestNum(rs.getInt("Invest_Num"));
					travelDesc.setPraiseNum(rs.getInt("Praise_Num"));
					travelDesc.setSpotId(rs.getInt("Spot_Id"));
					
					travelDesc.setUserId(rs.getInt("UserId"));
					travelDesc.setImageUrl(rs.getString("Image_Url"));
					travelDesc.setSpotName(rs.getString("Spot_Name"));
					travelDesc.setPetName(rs.getString("PetName"));
					return travelDesc;
				
			}
		});
	}

	
	public void insertBrowse(int userId, int relId, String type, String table,String t) {
		String sql2 = "update "+table+" set invest_num=(select invest_num+1 from "+table+" where "+t+"_id=?) where "+t+"_id=?";
		this.getJdbcTemplate().update(sql2, new Object[] {relId,relId});
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String sql = "insert into bus_browse(browse_id,user_id,create_time,browse_type,rel_id) values(seq_user.nextval,?,to_date(?,'yyyy-mm-dd'),?,?)";
		this.getJdbcTemplate().update(sql, new Object[] {userId,df.format(new Date()),type,relId});
	}

	
	public List findDescByCollect(int userId) {
		String sql="select a.*,d.petname,d.image_url,c.spot_name from bus_travel_desc a inner join (select rel_id from bus_collect where collect_type='0' and user_id=?) b on a.travel_desc_id=b.rel_id inner join sys_user d on d.user_id=a.userid inner join sys_scenic_spot c on c.spot_id=a.spot_id order by create_time desc";		
		return getJdbcTemplate().query(sql, new Object[]{userId}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					BusTravelDesc travelDesc=new BusTravelDesc();
					travelDesc.setTravelDescId(rs.getInt("Travel_Desc_Id"));
					travelDesc.setCollectNum(rs.getInt("Collect_Num"));
					travelDesc.setCommentNum(rs.getInt("Comment_Num"));
					travelDesc.setCreateTime(rs.getDate("Create_Time"));
					travelDesc.setDescContent(clobToString(rs.getClob("Desc_Content")));
					travelDesc.setDescTitle(rs.getString("Desc_Title"));
					travelDesc.setImages(rs.getString("Images"));
					
					travelDesc.setInvestNum(rs.getInt("Invest_Num"));
					travelDesc.setPraiseNum(rs.getInt("Praise_Num"));
					travelDesc.setSpotId(rs.getInt("Spot_Id"));
					
					travelDesc.setUserId(rs.getInt("UserId"));
					travelDesc.setImageUrl(rs.getString("Image_Url"));
					travelDesc.setSpotName(rs.getString("Spot_Name"));
					travelDesc.setPetName(rs.getString("PetName"));
					return travelDesc;
				
			}
		});
	}

	
	public List findDescBySpotName(int pageNum, int pageCount, String spotName) {
		Integer tInteger1=pageCount*pageNum;
		Integer tInteger2=(pageNum-1)*pageCount;
		String sql="select aa.*,bb.coun from (select * from (select rownum t,a.*,b.petname,b.image_url,c.spot_name from (select * from bus_travel_desc where spot_id=(select spot_id from sys_scenic_spot where spot_Name like '%"+spotName+"%')) a inner join sys_user b on b.user_id=a.userid inner join sys_scenic_spot c on c.spot_id=a.spot_id order by travel_desc_id desc) where t<=? and t>?) aa,(select count(*) as coun from bus_travel_desc where spot_id=(select spot_id from sys_scenic_spot where spot_Name like '%"+spotName+"%')) bb";
		return getJdbcTemplate().query(sql, new Object[]{tInteger1,tInteger2}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				BusTravelDesc travelDesc=new BusTravelDesc();
				travelDesc.setTravelDescId(rs.getInt("Travel_Desc_Id"));
				travelDesc.setCollectNum(rs.getInt("Collect_Num"));
				travelDesc.setCommentNum(rs.getInt("Comment_Num"));
				travelDesc.setCreateTime(rs.getDate("Create_Time"));
				travelDesc.setDescContent(clobToString(rs.getClob("Desc_Content")));
				travelDesc.setDescTitle(rs.getString("Desc_Title"));
				travelDesc.setImages(rs.getString("Images"));
				
				travelDesc.setInvestNum(rs.getInt("Invest_Num"));
				travelDesc.setPraiseNum(rs.getInt("Praise_Num"));
				travelDesc.setSpotId(rs.getInt("Spot_Id"));
				
				travelDesc.setUserId(rs.getInt("UserId"));
				travelDesc.setImageUrl(rs.getString("Image_Url"));
				travelDesc.setSpotName(rs.getString("Spot_Name"));
				travelDesc.setPetName(rs.getString("PetName"));
				travelDesc.setCount(rs.getInt("Coun"));
				return travelDesc;
			}
		});
	}

	
	public void deleteDescByIds(String travelDescId) {
		String sql2 = "delete from bus_phase where rel_Id in("+travelDescId+") and phase_type='0'";
		this.getJdbcTemplate().update(sql2, new Object[] {});
		String sql3 = "delete from bus_collect where rel_Id in("+travelDescId+") and collect_type='0'";
		this.getJdbcTemplate().update(sql3, new Object[] {});
		String sql4 = "delete from bus_reply where comment_Id=(select comment_id from bus_comment where rel_id in("+travelDescId+") and comment_type='0')";
		this.getJdbcTemplate().update(sql4, new Object[] {});
		String sql5 = "delete from bus_comment where rel_Id in("+travelDescId+") and comment_type='0'";
		this.getJdbcTemplate().update(sql5, new Object[] {});
		String sql = "delete from bus_travel_desc where travel_Desc_Id in("+travelDescId+")";
		this.getJdbcTemplate().update(sql, new Object[] {});
	
	}
}
