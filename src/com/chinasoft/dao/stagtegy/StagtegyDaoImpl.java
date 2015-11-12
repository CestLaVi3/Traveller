package com.chinasoft.dao.stagtegy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.chinasoft.dao.BaseDao;
import com.chinasoft.model.spot.SysScenicSpot;
import com.chinasoft.model.strategy.BusStagtegy;
import com.chinasoft.model.strategy.BusTravelDesc;
import com.chinasoft.model.strategy.SysStagtegyType;
import com.chinasoft.model.user.SysUser;

public class StagtegyDaoImpl extends BaseDao implements StagtegyDao {

	public List findStagtrgy(int pageNum,int pageCount) {
		int i1=pageNum*pageCount;
		int i2=(pageNum-1)*pageCount;
		String sql = "select aa.*,bb.coun from (select * from (select q.*,rownum t from sys_stagtegy_type q order by type_id asc) where t<=? and t>?) aa inner join (select count(*) as coun from sys_stagtegy_type) bb on 1=1";
		return getJdbcTemplate().query(sql, new Object[] {i1,i2}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				SysStagtegyType stagtegyType = new SysStagtegyType();
				stagtegyType.setCount(rs.getInt("coun"));
				stagtegyType.setTypeId(rs.getInt("Type_Id"));
				stagtegyType.setTypeName(rs.getString("Type_Name"));
				return stagtegyType;
			}
		});
	}

	public List findDescByCondition(int typeId) {

		String sql = "select * from (select a.*,b.spot_name from (select * from bus_stagtegy where Stagtegy_Type=? and status='2') a inner join sys_scenic_spot b on b.spot_id=a.spot_id order by praise_num desc) where rownum<11";
		return getJdbcTemplate().query(sql, new Object[] { typeId },
				new RowMapper() {
					public Object mapRow(ResultSet rs, int arg1)
							throws SQLException {
						BusStagtegy busStagtegy = new BusStagtegy();
						busStagtegy.setCreateTime(rs.getDate("Create_Time"));
						busStagtegy.setImages(rs.getString("Images"));
						busStagtegy.setSpotId(rs.getInt("Spot_Id"));
						busStagtegy.setStagtegyContent(clobToString(rs.getClob("Stagtegy_Content")));
						busStagtegy.setStagtegyId(rs.getInt("Stagtegy_Id"));
						busStagtegy.setStagtegyTitle(rs
								.getString("Stagtegy_Title"));
						busStagtegy.setStagtegyType(rs.getInt("Stagtegy_Type"));
						busStagtegy.setTravelDays(rs.getInt("Travel_Days"));
						busStagtegy.setUserId(rs.getInt("User_Id"));
						busStagtegy.setCommentNum(rs.getInt("Comment_Num"));
						busStagtegy.setInvestNum(rs.getInt("Invest_Num"));
						busStagtegy.setKeys(rs.getString("Keys"));
						busStagtegy.setSubComment(rs.getString("Sub_Comment"));
						busStagtegy.setPraiseNum(rs.getInt("Praise_Num"));
						busStagtegy.setSpotName(rs.getString("Spot_Name"));
						busStagtegy.setImages(rs.getString("images"));
						busStagtegy.setFitMonth(rs.getInt("Fit_Month"));
						busStagtegy.setStatus(rs.getString("Status"));
						return busStagtegy;
					}
				});
	}

	public List findByMonth(int year, int month) {
		String sql = "select * from (select rownum t,a.*,b.spot_name,c.image_url,c.petname from (select * from bus_stagtegy where to_char(create_time,'yyyy')=? and to_char(create_time,'mm')=? and status='2') a inner join sys_scenic_spot b on b.spot_id=a.spot_id inner join sys_user c on c.user_id=a.user_id order by hot_level desc) where t<5";
		return getJdbcTemplate().query(sql, new Object[] {year,month},
				new RowMapper() {
					public Object mapRow(ResultSet rs, int arg1)
							throws SQLException {
						BusStagtegy busStagtegy = new BusStagtegy();
						busStagtegy.setCreateTime(rs.getDate("Create_Time"));
						busStagtegy.setImages(rs.getString("Images"));
						busStagtegy.setSpotId(rs.getInt("Spot_Id"));
						busStagtegy.setStagtegyContent(clobToString(rs.getClob("Stagtegy_Content")));
						busStagtegy.setStagtegyId(rs.getInt("Stagtegy_Id"));
						busStagtegy.setStagtegyTitle(rs
								.getString("Stagtegy_Title"));
						busStagtegy.setStagtegyType(rs.getInt("Stagtegy_Type"));
						busStagtegy.setTravelDays(rs.getInt("Travel_Days"));
						busStagtegy.setUserId(rs.getInt("User_Id"));
						busStagtegy.setCommentNum(rs.getInt("Comment_Num"));
						busStagtegy.setInvestNum(rs.getInt("Invest_Num"));
						busStagtegy.setKeys(rs.getString("Keys"));
						busStagtegy.setSubComment(rs.getString("Sub_Comment"));
						busStagtegy.setPraiseNum(rs.getInt("Praise_Num"));
						busStagtegy.setSpotName(rs.getString("Spot_Name"));
						busStagtegy.setImages(rs.getString("images"));
						busStagtegy.setFitMonth(rs.getInt("Fit_Month"));
						busStagtegy.setStatus(rs.getString("Status"));
						busStagtegy.setImageUrl(rs.getString("Image_Url"));
						busStagtegy.setPetName(rs.getString("PetName"));
						return busStagtegy;
					}
				});
	}

	public List findByConditionAndSot(int year, int pageNum, int pageCount,
			int sort, int spotId, String fitMonth, String travelDays,
			int stagtegyType,String stagtegyName,String status) {
		Integer pInteger1 = pageNum * pageCount;
		Integer pInteger2 = (pageNum - 1) * pageCount;
		String sql = "";
		sql = "select aa.*,bb.coun from (select a.*,b.spot_name,c.image_url,c.petname,rownum t from (select * from bus_stagtegy where 1=1";
		if(stagtegyName==null){
		sql+=" and to_char(create_time,'yyyy')='"+year+"'";
		}
		else{
			sql+=" and STAGTEGY_TITLE like '%"+stagtegyName+"%'";
		}
		if(!(fitMonth==null)){
		sql+=" and fit_month in ("
				+ fitMonth
				+ ")";
		}
		if(!(travelDays==null)){			
				sql+=" and travel_days in("
						+ travelDays
						+ ")";
		}
		if(status==null){
			sql+=" and status='2'";
		}
		else{
			sql+=" and status='"+status+"'";
		}
		if(!(spotId==0)){
		sql+=" and spot_id="
				+ spotId
				+ "";
		}
		if(!(stagtegyType==0)){
		sql+=" and stagtegy_type="
				+ stagtegyType
				+ "";
		}
		sql+=") a inner join sys_scenic_spot b on b.spot_id=a.spot_id inner join sys_user c on c.user_id=a.user_id order by ";
		if(sort==1){
			sql+="stagtegy_id desc";
			}
		else{
			sql+="hot_level desc";
		}
		sql+=") aa inner join (select count(*) as coun from (select * from bus_stagtegy where 1=1";
		
		if(stagtegyName==null){
			sql+=" and to_char(create_time,'yyyy')='"+year+"'";
			}
		else{
			sql+=" and STAGTEGY_TITLE like '%"+stagtegyName+"%'";
		}
		if(!(fitMonth==null)){
			sql+=" and fit_month in ("
					+ fitMonth
					+ ")";
			}
			if(!(travelDays==null)){
			sql+=" and travel_days in("
					+ travelDays
					+ ")";
			}	
			if(status==null){
				sql+=" and status='2'";
			}
			else{
				sql+=" and status='"+status+"'";
			}
			if(!(spotId==0)){
			sql+=" and spot_id="
					+ spotId
					+ "";
			}
			if(!(stagtegyType==0)){
			sql+=" and stagtegy_type="
					+ stagtegyType
					+ "";
			}
			sql+=")";
		
		sql+=")bb on t<=? and t>?";
		return getJdbcTemplate().query(sql,
				new Object[] {pInteger1, pInteger2 }, new RowMapper() {
					public Object mapRow(ResultSet rs, int arg1)
							throws SQLException {
						BusStagtegy busStagtegy = new BusStagtegy();
						busStagtegy.setCreateTime(rs.getDate("Create_Time"));
						busStagtegy.setImages(rs.getString("Images"));
						busStagtegy.setSpotId(rs.getInt("Spot_Id"));
						busStagtegy.setStagtegyContent(clobToString(rs.getClob("Stagtegy_Content")));
						busStagtegy.setStagtegyId(rs.getInt("Stagtegy_Id"));
						busStagtegy.setStagtegyTitle(rs
								.getString("Stagtegy_Title"));
						busStagtegy.setStagtegyType(rs.getInt("Stagtegy_Type"));
						busStagtegy.setTravelDays(rs.getInt("Travel_Days"));
						busStagtegy.setUserId(rs.getInt("User_Id"));
						busStagtegy.setCommentNum(rs.getInt("Comment_Num"));
						busStagtegy.setInvestNum(rs.getInt("Invest_Num"));
						busStagtegy.setKeys(rs.getString("Keys"));
						busStagtegy.setSubComment(rs.getString("Sub_Comment"));
						busStagtegy.setPraiseNum(rs.getInt("Praise_Num"));
						busStagtegy.setSpotName(rs.getString("Spot_Name"));
						busStagtegy.setImages(rs.getString("images"));
						busStagtegy.setFitMonth(rs.getInt("Fit_Month"));
						busStagtegy.setStatus(rs.getString("Status"));
						busStagtegy.setImageUrl(rs.getString("Image_Url"));
						busStagtegy.setPetName(rs.getString("PetName"));
						busStagtegy.setCount(rs.getInt("coun"));
						return busStagtegy;
					}
				});
	}

	
	public List findHotStagtegy(int pageNUm) {
		String sql = "select * from (select a.*,b.spot_name,c.image_url,c.petname from (select * from bus_stagtegy where status='2') a inner join sys_scenic_spot b on b.spot_id=a.spot_id inner join sys_user c on c.user_id=a.user_id order by invest_num desc) where rownum<=?";
		return getJdbcTemplate().query(sql, new Object[] {pageNUm},
				new RowMapper() {
					public Object mapRow(ResultSet rs, int arg1)
							throws SQLException {
						BusStagtegy busStagtegy = new BusStagtegy();
						busStagtegy.setCreateTime(rs.getDate("Create_Time"));
						busStagtegy.setImages(rs.getString("Images"));
						busStagtegy.setSpotId(rs.getInt("Spot_Id"));
						busStagtegy.setStagtegyContent(clobToString(rs.getClob("Stagtegy_Content")));
						busStagtegy.setStagtegyId(rs.getInt("Stagtegy_Id"));
						busStagtegy.setStagtegyTitle(rs
								.getString("Stagtegy_Title"));
						busStagtegy.setStagtegyType(rs.getInt("Stagtegy_Type"));
						busStagtegy.setTravelDays(rs.getInt("Travel_Days"));
						busStagtegy.setUserId(rs.getInt("User_Id"));
						busStagtegy.setCommentNum(rs.getInt("Comment_Num"));
						busStagtegy.setInvestNum(rs.getInt("Invest_Num"));
						busStagtegy.setKeys(rs.getString("Keys"));
						busStagtegy.setSubComment(rs.getString("Sub_Comment"));
						busStagtegy.setPraiseNum(rs.getInt("Praise_Num"));
						busStagtegy.setSpotName(rs.getString("Spot_Name"));
						busStagtegy.setImages(rs.getString("images"));
						busStagtegy.setFitMonth(rs.getInt("Fit_Month"));
						busStagtegy.setStatus(rs.getString("Status"));
						busStagtegy.setImageUrl(rs.getString("Image_Url"));
						busStagtegy.setPetName(rs.getString("PetName"));
						return busStagtegy;
					}
				});
	}

	
	public BusStagtegy findById(int stagtegyId) {
		String sql="select a.*,b.petname,b.image_url,c.spot_name from bus_stagtegy a inner join sys_user b on b.user_id=a.user_id inner join sys_scenic_spot c on c.spot_id=a.spot_id where a.stagtegy_id=?";
		return (BusStagtegy)getJdbcTemplate().query(sql, new Object[] {stagtegyId}, new ResultSetExtractor() {			
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if(rs.next()){
					BusStagtegy busStagtegy = new BusStagtegy();
					busStagtegy.setCreateTime(rs.getDate("Create_Time"));
					busStagtegy.setImages(rs.getString("Images"));
					busStagtegy.setSpotId(rs.getInt("Spot_Id"));
					busStagtegy.setStagtegyContent(clobToString(rs.getClob("Stagtegy_Content")));
					busStagtegy.setStagtegyId(rs.getInt("Stagtegy_Id"));
					busStagtegy.setStagtegyTitle(rs
							.getString("Stagtegy_Title"));
					busStagtegy.setStagtegyType(rs.getInt("Stagtegy_Type"));
					busStagtegy.setTravelDays(rs.getInt("Travel_Days"));
					busStagtegy.setUserId(rs.getInt("User_Id"));
					busStagtegy.setCommentNum(rs.getInt("Comment_Num"));
					busStagtegy.setInvestNum(rs.getInt("Invest_Num"));
					busStagtegy.setKeys(rs.getString("Keys"));
					busStagtegy.setSubComment(rs.getString("Sub_Comment"));
					busStagtegy.setPraiseNum(rs.getInt("Praise_Num"));
					busStagtegy.setSpotName(rs.getString("Spot_Name"));
					busStagtegy.setImages(rs.getString("images"));
					busStagtegy.setFitMonth(rs.getInt("Fit_Month"));
					busStagtegy.setStatus(rs.getString("Status"));
					busStagtegy.setImageUrl(rs.getString("Image_Url"));
					busStagtegy.setPetName(rs.getString("PetName"));
					return busStagtegy;
				}
				return null;
			}
		});
	}

	
	public void praiseStagtegy(int stagtegyId,int userid,int num,String nt) {
		String sql = "update bus_Stagtegy set praise_num=(select praise_num+1 from bus_stagtegy where stagtegy_Id=?) where Stagtegy_Id=?";
		this.getJdbcTemplate().update(sql, new Object[] {stagtegyId,stagtegyId});
		
		String sql2 = "insert into bus_phase(phase_id,user_id,phase_type,rel_id,create_time) values(seq_user.nextval,?,'1',?,to_date(?,'yyyy-mm-dd'))";
		this.getJdbcTemplate().update(sql2, new Object[] {userid,stagtegyId,nt});
		
	}

	
	public void collectStagtegy(int stagtegyId,int userid,int num,String nt) {
		String sql = "update bus_stagtegy set collect_num=(select collect_num+1 from bus_stagtegy where stagtegy_Id=?) where stagtegy_Id=?";
		this.getJdbcTemplate().update(sql, new Object[] {stagtegyId,stagtegyId});
		
		String sql2 = "insert into bus_collect(collect_id,user_id,collect_type,rel_id,create_time) values(seq_user.nextval,?,'1',?,to_date(?,'yyyy-mm-dd'))";
		this.getJdbcTemplate().update(sql2, new Object[] {userid,stagtegyId,nt});
		
	}

	
	public void deleteStagtegy(int stagtegyId,int userId) {
				
		String sql2 = "delete bus_phase where rel_Id=? and phase_type='1'";
		this.getJdbcTemplate().update(sql2, new Object[] {stagtegyId});
		String sql3 = "delete bus_collect where rel_Id=? and collect_type='1'";
		this.getJdbcTemplate().update(sql3, new Object[] {stagtegyId});
		String sql4 = "delete bus_reply where comment_id in (select comment_id from bus_comment where rel_id=? and comment_type='1')";
		this.getJdbcTemplate().update(sql4, new Object[] {stagtegyId});
		String sql5 = "delete bus_comment where rel_Id=? and comment_type='1'";
		this.getJdbcTemplate().update(sql5, new Object[] {stagtegyId});	
		String sql = "delete bus_stagtegy where stagtegy_Id=? and user_id=?";
		this.getJdbcTemplate().update(sql, new Object[] {stagtegyId,userId});
	}

	
	public List findRelationStagtegy(int spotId, int fitMonth,
			int travelDays,int travelId,int pageNum) {
		String sql = "select * from (select a.*,b.spot_name,c.image_url,c.petname from (select * from bus_stagtegy where spot_id=? and status='2' and fit_month=? and travel_days=? and stagtegy_id!=?) a inner join sys_scenic_spot b on b.spot_id=a.spot_id inner join sys_user c on c.user_id=a.user_id order by stagtegy_id asc) where rownum<=?";
		return getJdbcTemplate().query(sql, new Object[] {spotId,fitMonth,travelDays,travelId,pageNum},
				new RowMapper() {
					public Object mapRow(ResultSet rs, int arg1)
							throws SQLException {
						BusStagtegy busStagtegy = new BusStagtegy();
						busStagtegy.setCreateTime(rs.getDate("Create_Time"));
						busStagtegy.setImages(rs.getString("Images"));
						busStagtegy.setSpotId(rs.getInt("Spot_Id"));
						busStagtegy.setStagtegyContent(clobToString(rs.getClob("Stagtegy_Content")));
						busStagtegy.setStagtegyId(rs.getInt("Stagtegy_Id"));
						busStagtegy.setStagtegyTitle(rs
								.getString("Stagtegy_Title"));
						busStagtegy.setStagtegyType(rs.getInt("Stagtegy_Type"));
						busStagtegy.setTravelDays(rs.getInt("Travel_Days"));
						busStagtegy.setUserId(rs.getInt("User_Id"));
						busStagtegy.setCommentNum(rs.getInt("Comment_Num"));
						busStagtegy.setInvestNum(rs.getInt("Invest_Num"));
						busStagtegy.setKeys(rs.getString("Keys"));
						busStagtegy.setSubComment(rs.getString("Sub_Comment"));
						busStagtegy.setPraiseNum(rs.getInt("Praise_Num"));
						busStagtegy.setSpotName(rs.getString("Spot_Name"));
						busStagtegy.setImages(rs.getString("images"));
						busStagtegy.setFitMonth(rs.getInt("Fit_Month"));
						busStagtegy.setStatus(rs.getString("Status"));
						busStagtegy.setImageUrl(rs.getString("Image_Url"));
						busStagtegy.setPetName(rs.getString("PetName"));
						return busStagtegy;
					}
				});
	}

	
	public void saveStagtegy(BusStagtegy stagtegy) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String sql = "insert into bus_stagtegy(stagtegy_Id,spot_Id,user_Id,create_Time,stagtegy_Title,stagtegy_Content,images,status,keys,fit_month,travel_days,sub_comment,stagtegy_type,invest_num,comment_num,collect_num,praise_num) values(seq_user.nextval,?,?,to_date(?,'yyyy-mm-dd'),?,?,?,?,?,?,?,?,?,0,0,0,0)";
		this.getJdbcTemplate().update(sql, new Object[] {stagtegy.getSpotId(),stagtegy.getUserId(),df.format(stagtegy.getCreateTime()),stagtegy.getStagtegyTitle(),stagtegy.getStagtegyContent(),stagtegy.getImages(),stagtegy.getStatus(),stagtegy.getKeys(),stagtegy.getFitMonth(),stagtegy.getTravelDays(),stagtegy.getSubComment(),stagtegy.getStagtegyType()});
		
	}


	public void updateStagtegy(BusStagtegy stagtegy) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String sql = "update bus_stagtegy set spot_Id=?,user_Id=?,create_Time=to_date(?,'yyyy-mm-dd'),stagtegy_Title=?,stagtegy_Content=?,images=?,status=?,keys=?,fit_month=?,travel_days=?,sub_comment=?,stagtegy_type=? where stagtegy_id=?";
		this.getJdbcTemplate().update(sql, new Object[] {stagtegy.getSpotId(),stagtegy.getUserId(),df.format(stagtegy.getCreateTime()),stagtegy.getStagtegyTitle(),stagtegy.getStagtegyContent(),stagtegy.getImages(),stagtegy.getStatus(),stagtegy.getKeys(),stagtegy.getFitMonth(),stagtegy.getTravelDays(),stagtegy.getSubComment(),stagtegy.getStagtegyType(),stagtegy.getStagtegyId()});
	}

	
	public List findStagtegyByUser(int userid) {
		String sql="select * from (select a.*,b.petname,b.image_url,c.spot_name from bus_stagtegy a inner join sys_user b on b.user_id=a.user_id inner join sys_scenic_spot c on c.spot_id=a.spot_id order by create_time desc) where user_id=?";
		
		return getJdbcTemplate().query(sql, new Object[]{userid}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {			
				BusStagtegy busStagtegy = new BusStagtegy();
				busStagtegy.setCreateTime(rs.getDate("Create_Time"));
				busStagtegy.setImages(rs.getString("Images"));
				busStagtegy.setSpotId(rs.getInt("Spot_Id"));
				busStagtegy.setStagtegyContent(clobToString(rs.getClob("Stagtegy_Content")));
				busStagtegy.setStagtegyId(rs.getInt("Stagtegy_Id"));
				busStagtegy.setStagtegyTitle(rs.getString("Stagtegy_Title"));
				busStagtegy.setStagtegyType(rs.getInt("Stagtegy_Type"));
				busStagtegy.setTravelDays(rs.getInt("Travel_Days"));
				busStagtegy.setUserId(rs.getInt("User_Id"));
				busStagtegy.setCommentNum(rs.getInt("Comment_Num"));
				busStagtegy.setInvestNum(rs.getInt("Invest_Num"));
				busStagtegy.setKeys(rs.getString("Keys"));
				busStagtegy.setSubComment(rs.getString("Sub_Comment"));
				busStagtegy.setPraiseNum(rs.getInt("Praise_Num"));
				busStagtegy.setSpotName(rs.getString("Spot_Name"));
				busStagtegy.setImages(rs.getString("images"));
				busStagtegy.setFitMonth(rs.getInt("Fit_Month"));
				busStagtegy.setStatus(rs.getString("Status"));
				busStagtegy.setImageUrl(rs.getString("Image_Url"));
				busStagtegy.setPetName(rs.getString("PetName"));
				return busStagtegy;
			}
		});
	}

	
	public List findByUserCollect(int userId) {
		String sql="select a.*,d.petname,d.image_url,c.spot_name from bus_stagtegy a inner join (select rel_id from bus_collect where collect_type='5' and user_id=?) b on a.user_id=b.rel_id inner join sys_user d on d.user_id=a.user_id inner join sys_scenic_spot c on c.spot_id=a.spot_id order by create_time desc";		
		return getJdbcTemplate().query(sql, new Object[]{userId}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {			
				BusStagtegy busStagtegy = new BusStagtegy();
				busStagtegy.setCreateTime(rs.getDate("Create_Time"));
				busStagtegy.setImages(rs.getString("Images"));
				busStagtegy.setSpotId(rs.getInt("Spot_Id"));
				busStagtegy.setStagtegyContent(clobToString(rs.getClob("Stagtegy_Content")));
				busStagtegy.setStagtegyId(rs.getInt("Stagtegy_Id"));
				busStagtegy.setStagtegyTitle(rs.getString("Stagtegy_Title"));
				busStagtegy.setStagtegyType(rs.getInt("Stagtegy_Type"));
				busStagtegy.setTravelDays(rs.getInt("Travel_Days"));
				busStagtegy.setUserId(rs.getInt("User_Id"));
				busStagtegy.setCommentNum(rs.getInt("Comment_Num"));
				busStagtegy.setInvestNum(rs.getInt("Invest_Num"));
				busStagtegy.setKeys(rs.getString("Keys"));
				busStagtegy.setSubComment(rs.getString("Sub_Comment"));
				busStagtegy.setPraiseNum(rs.getInt("Praise_Num"));
				busStagtegy.setSpotName(rs.getString("Spot_Name"));
				busStagtegy.setImages(rs.getString("images"));
				busStagtegy.setFitMonth(rs.getInt("Fit_Month"));
				busStagtegy.setStatus(rs.getString("Status"));
				busStagtegy.setImageUrl(rs.getString("Image_Url"));
				busStagtegy.setPetName(rs.getString("PetName"));
				return busStagtegy;
			}
		});
	}

	
	public List findstagtegyByCollect(int userId) {
		String sql="select a.*,d.petname,d.image_url,c.spot_name from bus_stagtegy a inner join (select rel_id from bus_collect where collect_type='1' and user_id=?) b on a.stagtegy_id=b.rel_id inner join sys_user d on d.user_id=a.user_id inner join sys_scenic_spot c on c.spot_id=a.spot_id order by create_time desc";		
		return getJdbcTemplate().query(sql, new Object[]{userId}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {			
				BusStagtegy busStagtegy = new BusStagtegy();
				busStagtegy.setCreateTime(rs.getDate("Create_Time"));
				busStagtegy.setImages(rs.getString("Images"));
				busStagtegy.setSpotId(rs.getInt("Spot_Id"));
				busStagtegy.setStagtegyContent(clobToString(rs.getClob("Stagtegy_Content")));
				busStagtegy.setStagtegyId(rs.getInt("Stagtegy_Id"));
				busStagtegy.setStagtegyTitle(rs.getString("Stagtegy_Title"));
				busStagtegy.setStagtegyType(rs.getInt("Stagtegy_Type"));
				busStagtegy.setTravelDays(rs.getInt("Travel_Days"));
				busStagtegy.setUserId(rs.getInt("User_Id"));
				busStagtegy.setCommentNum(rs.getInt("Comment_Num"));
				busStagtegy.setInvestNum(rs.getInt("Invest_Num"));
				busStagtegy.setKeys(rs.getString("Keys"));
				busStagtegy.setSubComment(rs.getString("Sub_Comment"));
				busStagtegy.setPraiseNum(rs.getInt("Praise_Num"));
				busStagtegy.setSpotName(rs.getString("Spot_Name"));
				busStagtegy.setImages(rs.getString("images"));
				busStagtegy.setFitMonth(rs.getInt("Fit_Month"));
				busStagtegy.setStatus(rs.getString("Status"));
				busStagtegy.setImageUrl(rs.getString("Image_Url"));
				busStagtegy.setPetName(rs.getString("PetName"));
				return busStagtegy;
			}
		});
	}

	
	public void addStagtegyType(SysStagtegyType stagtegyType) {
		String sql = "insert into sys_stagtegy_type(type_id,type_name) values(seq_user.nextval,?)";
		this.getJdbcTemplate().update(sql, new Object[] {stagtegyType.getTypeName()});
	}

	
	public void updateStagtegyType(SysStagtegyType stagtegyType) {
		String sql = "update sys_stagtegy_type set type_name=? where type_id=?";
		this.getJdbcTemplate().update(sql, new Object[] {stagtegyType.getTypeName(),stagtegyType.getTypeId()});
	}

	
	public void deleteStagtegyType(int typeId) {
		String sql = "delete from bus_stagtegy where stagtegy_type=?";
		this.getJdbcTemplate().update(sql, new Object[] {typeId});
		String sql2 = "delete from sys_stagtegy_type where type_id=?";
		this.getJdbcTemplate().update(sql2, new Object[] {typeId});
	}

	
	public void deleteStagtegyType(String stagtegyId) {
		String sql2 = "delete bus_phase where rel_Id in("+stagtegyId+") and phase_type='1'";
		this.getJdbcTemplate().update(sql2, new Object[] {});
		String sql3 = "delete bus_collect where rel_Id in("+stagtegyId+") and collect_type='1'";
		this.getJdbcTemplate().update(sql3, new Object[] {});
		String sql4 = "delete bus_reply where comment_id in (select comment_id from bus_comment where rel_id in("+stagtegyId+") and comment_type='1')";
		this.getJdbcTemplate().update(sql4, new Object[] {});
		String sql5 = "delete bus_comment where rel_Id in("+stagtegyId+") and comment_type='1'";
		this.getJdbcTemplate().update(sql5, new Object[] {});	
		String sql = "delete bus_stagtegy where stagtegy_Id in("+stagtegyId+")";
		this.getJdbcTemplate().update(sql, new Object[] {});
	}

	
	public void checkStagtegy(int stagtegyId, String status) {
		String sql = "update bus_stagtegy set status=? where stagtegy_id=?";
		this.getJdbcTemplate().update(sql, new Object[] {status,stagtegyId});
	}

	
	
	

}
