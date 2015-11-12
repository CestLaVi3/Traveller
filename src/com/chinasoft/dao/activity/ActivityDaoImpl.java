package com.chinasoft.dao.activity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.chinasoft.dao.BaseDao;
import com.chinasoft.model.activity.BusActive;
import com.chinasoft.model.activity.BusActiveInform;
import com.chinasoft.model.activity.BusActiveJoin;
import com.chinasoft.model.activity.SysActiveType;

public class ActivityDaoImpl extends BaseDao implements ActivityDao {
	public List findActiveType() {
		String sql="select * from sys_active_type";
		return getJdbcTemplate().query(sql, new Object[]{}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				SysActiveType activeType=new SysActiveType();
				activeType.setTypeId(rs.getInt("Type_Id"));
				activeType.setStatus(rs.getString("status"));
				activeType.setImageUrl(rs.getString("ImageUrl"));
				activeType.setTypeName(rs.getString("Type_Name"));
				activeType.setTypeDesc(rs.getString("Type_Desc"));
				return activeType;
			}
		});
	}
	public List findActiveByStatus(String status) {
		String sql="select a.*,b.type_name from (select * from (select * from bus_active order by Start_Date desc) where rownum<4 and status=?) a inner join sys_active_type b on b.type_id=a.active_type";
		return getJdbcTemplate().query(sql, new Object[]{status}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				BusActive busActive=new BusActive();
				busActive.setActiveId(rs.getInt("Active_Id"));
				busActive.setActiveName(rs.getString("Active_Name"));
				busActive.setActiveType(rs.getInt("Active_Type"));
				busActive.setCollectNum(rs.getInt("Collect_Num"));
				busActive.setCreateTime(rs.getDate("Create_Time"));
				busActive.setEndDate(rs.getDate("End_Date"));
				busActive.setJoinNum(rs.getInt("Join_Num"));
				busActive.setPhaseNum(rs.getInt("Phase_Num"));
				busActive.setPrizeImage(rs.getString("Prize_Image"));
				busActive.setPrizeTile(rs.getString("Prize_Tile"));
				busActive.setStartDate(rs.getDate("Start_Date"));
				busActive.setStatus(rs.getString("status"));
				busActive.setUserId(rs.getInt("User_Id"));
				busActive.setImages(rs.getString("Images"));
				busActive.setActiveTypeName(rs.getString("Type_Name"));
				return busActive;
			}
		});
	}
	
	
	
	public List findActiveByType(int typeId) {
		String sql="select a.*,b.type_name from (select * from (select * from bus_active order by Phase_Num desc) where Active_Type=? and rownum<4 and status!='0') a inner join sys_active_type b on b.type_id=a.active_type";
		return getJdbcTemplate().query(sql, new Object[]{typeId}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				BusActive busActive1=new BusActive();
				busActive1.setActiveId(rs.getInt("Active_Id"));
				busActive1.setActiveName(rs.getString("Active_Name"));
				busActive1.setActiveType(rs.getInt("Active_Type"));
				busActive1.setCollectNum(rs.getInt("Collect_Num"));
				busActive1.setCreateTime(rs.getDate("Create_Time"));
				busActive1.setEndDate(rs.getDate("End_Date"));
				busActive1.setJoinNum(rs.getInt("Join_Num"));
				busActive1.setPhaseNum(rs.getInt("Phase_Num"));
				busActive1.setPrizeImage(rs.getString("Prize_Image"));
				busActive1.setPrizeTile(rs.getString("Prize_Tile"));
				busActive1.setStartDate(rs.getDate("Start_Date"));
				busActive1.setStatus(rs.getString("status"));
				busActive1.setUserId(rs.getInt("User_Id"));
				busActive1.setImages(rs.getString("Images"));
				busActive1.setActiveTypeName(rs.getString("Type_Name"));
				return busActive1;
			}
		});
	}
	
	public List findPhotoActive(String count) {		
		String sql="select a.*,b.type_name,c.image_url,c.petname from (select m.*,rownum t from (select * from bus_active where active_type='1' order by active_id desc) m where status!='0'";
				if(count.equals("ALL")){
					
				}
				else{
					sql+=" and t<="+Integer.parseInt(count);
				}
				sql+=") a inner join sys_active_type b on b.type_id=a.active_type left join sys_user c on c.user_id=a.user_id";		
		return getJdbcTemplate().query(sql, new Object[]{}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				BusActive busActive1=new BusActive();
				busActive1.setActiveId(rs.getInt("Active_Id"));
				busActive1.setActiveName(rs.getString("Active_Name"));
				busActive1.setActiveType(rs.getInt("Active_Type"));
				busActive1.setCollectNum(rs.getInt("Collect_Num"));
				busActive1.setCreateTime(rs.getDate("Create_Time"));
				busActive1.setEndDate(rs.getDate("End_Date"));
				busActive1.setJoinNum(rs.getInt("Join_Num"));
				busActive1.setPhaseNum(rs.getInt("Phase_Num"));
				busActive1.setPrizeImage(rs.getString("Prize_Image"));
				busActive1.setPrizeTile(rs.getString("Prize_Tile"));
				busActive1.setStartDate(rs.getDate("Start_Date"));
				busActive1.setStatus(rs.getString("status"));
				busActive1.setUserId(rs.getInt("User_Id"));
				busActive1.setImages(rs.getString("Images"));
				busActive1.setActiveTypeName(rs.getString("Type_Name"));
				busActive1.setImageUrl(rs.getString("Image_Url"));
				busActive1.setPetName(rs.getString("PetName"));
				busActive1.setVoteDesc(rs.getString("Vote_Desc"));
				busActive1.setProductDesc(rs.getString("Product_Desc"));
				return busActive1;
			}
		});
	}
	
	public List findActiveJoinByCount(int count, int activeId) {
		String sql="select aa.*,bb.type_name from (select a.*,b.image_url,b.petname,c.active_Type from (select * from (select q.*,rownum t from bus_active_join q where active_id=? order by q.praise_num desc) where t<=?) a left join sys_user b on b.user_id=a.user_id inner join bus_active c on c.active_id=a.active_id) aa inner join sys_active_type bb on bb.type_id=aa.active_type";
		return getJdbcTemplate().query(sql, new Object[]{activeId,count}, new RowMapper() {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			BusActiveJoin busActiveJoin=new BusActiveJoin();
			busActiveJoin.setActiveId(rs.getInt("Active_Id"));
			busActiveJoin.setActiveJoinId(rs.getInt("Active_Join_Id"));
			busActiveJoin.setActiveType(rs.getInt("Active_Type"));
			busActiveJoin.setActiveTypeName(rs.getString("Type_Name"));
			busActiveJoin.setCreateDate(rs.getDate("Create_Date"));
			busActiveJoin.setImages(rs.getString("Images"));
			busActiveJoin.setImageUrl(rs.getString("Image_Url"));
			busActiveJoin.setInvestNum(rs.getInt("Invest_Num"));
			busActiveJoin.setJoinDesc(clobToString(rs.getClob("Join_Desc")));
			busActiveJoin.setJoinName(rs.getString("Join_Name"));
			busActiveJoin.setPetName(rs.getString("PetName"));
			busActiveJoin.setPhoneNum(rs.getString("Phone_Num"));
			busActiveJoin.setPraiseNum(rs.getInt("Praise_Num"));
			busActiveJoin.setUserId(rs.getInt("User_Id"));
			return busActiveJoin;
		}
		});
	}
	
	public BusActive findActiveById(int activeId) {	
		String sql="select a.*,b.type_name,c.image_url,c.petname from (select * from bus_active where Active_id=?) a inner join sys_active_type b on b.type_id=a.active_type left join sys_user c on c.user_id=a.user_id";
		return (BusActive)getJdbcTemplate().query(sql, new Object[] {activeId}, new ResultSetExtractor() {			
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if(rs.next()){
					BusActive busActive1=new BusActive();
					busActive1.setActiveId(rs.getInt("Active_Id"));
					busActive1.setActiveName(rs.getString("Active_Name"));
					busActive1.setActiveType(rs.getInt("Active_Type"));
					busActive1.setCollectNum(rs.getInt("Collect_Num"));
					busActive1.setCreateTime(rs.getDate("Create_Time"));
					busActive1.setEndDate(rs.getDate("End_Date"));
					busActive1.setJoinNum(rs.getInt("Join_Num"));
					busActive1.setPhaseNum(rs.getInt("Phase_Num"));
					busActive1.setPrizeImage(rs.getString("Prize_Image"));
					busActive1.setPrizeTile(rs.getString("Prize_Tile"));
					busActive1.setStartDate(rs.getDate("Start_Date"));
					busActive1.setStatus(rs.getString("status"));
					busActive1.setUserId(rs.getInt("User_Id"));
					busActive1.setImages(rs.getString("Images"));
					busActive1.setActiveTypeName(rs.getString("Type_Name"));
					busActive1.setImageUrl(rs.getString("Image_Url"));
					busActive1.setPetName(rs.getString("PetName"));
					busActive1.setVoteDesc(rs.getString("Vote_Desc"));
					busActive1.setProductDesc(rs.getString("Product_Desc"));
					return busActive1;
			}
			return null;
			}
		});
	}
	
	
	public BusActiveJoin findActiveJoinById(int activeJoinId) {
		String sql="select a.*,b.active_type,c.image_url,c.petname,d.type_name from (select * from bus_active_join where Active_join_id=?) a inner join bus_active b on b.active_id=a.active_id inner join sys_active_type d on d.type_id=active_type left join sys_user c on c.user_id=a.user_id";
		return (BusActiveJoin)getJdbcTemplate().query(sql, new Object[] {activeJoinId}, new ResultSetExtractor() {			
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if(rs.next()){
					BusActiveJoin busActiveJoin=new BusActiveJoin();
					busActiveJoin.setActiveId(rs.getInt("Active_Id"));
					busActiveJoin.setActiveJoinId(rs.getInt("Active_Join_Id"));
					busActiveJoin.setActiveType(rs.getInt("Active_Type"));
					busActiveJoin.setActiveTypeName(rs.getString("Type_Name"));
					busActiveJoin.setCreateDate(rs.getDate("Create_Date"));
					busActiveJoin.setImages(rs.getString("Images"));
					busActiveJoin.setImageUrl(rs.getString("Image_Url"));
					busActiveJoin.setInvestNum(rs.getInt("Invest_Num"));
					busActiveJoin.setJoinDesc(clobToString(rs.getClob("Join_Desc")));
					busActiveJoin.setJoinName(rs.getString("Join_Name"));
					busActiveJoin.setPetName(rs.getString("PetName"));
					busActiveJoin.setPhoneNum(rs.getString("Phone_Num"));
					busActiveJoin.setPraiseNum(rs.getInt("Praise_Num"));
					busActiveJoin.setUserId(rs.getInt("User_Id"));
					return busActiveJoin;
			}
			return null;
			}
		});
	}

	public void praiseActiveJoin(int activeJionId,int userid,int num,String nt) {
		String sql = "update bus_active_join set praise_num=(select praise_num+1 from bus_active_join where active_join_Id=?) where active_join_Id=?";
		this.getJdbcTemplate().update(sql, new Object[] {activeJionId,activeJionId});
		
		String sql2 = "insert into bus_phase(phase_id,user_id,phase_type,rel_id,create_time) values(seq_user.nextval,?,'4',?,to_date(?,'yyyy-mm-dd'))";
		this.getJdbcTemplate().update(sql2, new Object[] {userid,activeJionId,nt});
		
	}
	
	public void collectActive(int activeId,int userid,int num,String nt) {
		String sql = "update bus_active set collect_num=(select collect_num+1 from bus_active where active_id=?) where active_Id=?";
		this.getJdbcTemplate().update(sql, new Object[] {activeId,activeId});
		
		String sql2 = "insert into bus_collect(collect_id,user_id,collect_type,rel_id,create_time) values(seq_user.nextval,?,'4',?,to_date(?,'yyyy-mm-dd'))";
		this.getJdbcTemplate().update(sql2, new Object[] {userid,activeId,nt});
		
	}
	
	public List findActiveJoinByPage(int pageNum, int pageCount, int activeId) {
		Integer tInteger1=pageNum*pageCount;
		Integer tInteger2=(pageNum-1)*pageCount;
		String sql="select aa.*,bb.coun from (select m.*,n.type_name from(select * from (select rownum t,a.*,b.petname,b.image_url,c.active_type from (select * from bus_active_join where active_id=?) a left join sys_user b on b.user_id=a.user_id inner join bus_active c on c.active_id=a.active_id order by active_join_id desc) where t<=? and t>?) m inner join sys_active_type n on n.type_id=active_type) aa,(select count(*) as coun from bus_active_join where active_id=?) bb";
		return getJdbcTemplate().query(sql, new Object[]{activeId,tInteger1,tInteger2,activeId}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				BusActiveJoin busActiveJoin=new BusActiveJoin();
				busActiveJoin.setCount(rs.getInt("coun"));
				busActiveJoin.setActiveId(rs.getInt("Active_Id"));
				busActiveJoin.setActiveJoinId(rs.getInt("Active_Join_Id"));
				busActiveJoin.setActiveType(rs.getInt("Active_Type"));
				busActiveJoin.setActiveTypeName(rs.getString("Type_Name"));
				busActiveJoin.setCreateDate(rs.getDate("Create_Date"));
				busActiveJoin.setImages(rs.getString("Images"));
				busActiveJoin.setImageUrl(rs.getString("Image_Url"));
				busActiveJoin.setInvestNum(rs.getInt("Invest_Num"));
				busActiveJoin.setJoinDesc(clobToString(rs.getClob("Join_Desc")));
				busActiveJoin.setJoinName(rs.getString("Join_Name"));
				busActiveJoin.setPetName(rs.getString("PetName"));
				busActiveJoin.setPhoneNum(rs.getString("Phone_Num"));
				busActiveJoin.setPraiseNum(rs.getInt("Praise_Num"));
				busActiveJoin.setUserId(rs.getInt("User_Id"));
				return busActiveJoin;
			}
		});
	}
	
	public void saveActiveJoin(BusActiveJoin busActiveJoin) {
		String sql2 = "update bus_active set join_num=(select join_num+1 from bus_active where active_id=?) where active_id=?";
		this.getJdbcTemplate().update(sql2, new Object[] {busActiveJoin.getActiveId(),busActiveJoin.getActiveId()});
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String sql = "insert into bus_active_join(active_join_id,create_date,active_id,user_id,join_name,join_desc,images,praise_num,phone_num) values(seq_user.nextval,to_date(?,'yyyy-mm-dd'),?,?,?,?,?,0,?)";
		this.getJdbcTemplate().update(sql, new Object[] {df.format(busActiveJoin.getCreateDate()),busActiveJoin.getActiveId(),busActiveJoin.getUserId(),busActiveJoin.getJoinName(),busActiveJoin.getJoinDesc(),busActiveJoin.getImages(),busActiveJoin.getPhoneNum()});
	}
	
	public List collect() {
		String sql="select a.*,b.type_name,c.image_url,c.petname from (select * from bus_active  where active_Type='2' order by active_id desc) a inner join sys_active_type b on b.type_id=a.active_type left join sys_user c on c.user_id=a.user_id";
		return getJdbcTemplate().query(sql, new Object[]{}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				BusActive busActive1=new BusActive();
				busActive1.setActiveId(rs.getInt("Active_Id"));
				busActive1.setActiveName(rs.getString("Active_Name"));
				busActive1.setActiveType(rs.getInt("Active_Type"));
				busActive1.setCollectNum(rs.getInt("Collect_Num"));
				busActive1.setCreateTime(rs.getDate("Create_Time"));
				busActive1.setEndDate(rs.getDate("End_Date"));
				busActive1.setJoinNum(rs.getInt("Join_Num"));
				busActive1.setPhaseNum(rs.getInt("Phase_Num"));
				busActive1.setPrizeImage(rs.getString("Prize_Image"));
				busActive1.setPrizeTile(rs.getString("Prize_Tile"));
				busActive1.setStartDate(rs.getDate("Start_Date"));
				busActive1.setStatus(rs.getString("status"));
				busActive1.setUserId(rs.getInt("User_Id"));
				busActive1.setImages(rs.getString("Images"));
				busActive1.setActiveTypeName(rs.getString("Type_Name"));
				busActive1.setImageUrl(rs.getString("Image_Url"));
				busActive1.setPetName(rs.getString("PetName"));
				busActive1.setVoteDesc(rs.getString("Vote_Desc"));
				busActive1.setProductDesc(rs.getString("Product_Desc"));
				return busActive1;
			}
		});
	}
	
	public List findActiveByPageAndType(int pageNum, int pageCount,
			int activeType) {
		int i1=pageNum*pageCount;
		int i2=(pageNum-1)*pageCount;
		String sql="select aa.*,bb.coun from (select a.*,b.type_name,c.image_url,c.petname from (select * from(select q.*,rownum t from bus_active q where active_type=? and status='2' order by phase_num,active_id desc) where t<=? and t>?)a inner join sys_active_type b on b.type_id=a.active_type left join sys_user c on c.user_id=a.user_id) aa inner join (select count(*) as coun from bus_active q where active_type=? and status='2')bb on 1=1";
		return getJdbcTemplate().query(sql, new Object[]{activeType,i1,i2,activeType}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				BusActive busActive1=new BusActive();
				busActive1.setActiveId(rs.getInt("Active_Id"));
				busActive1.setActiveName(rs.getString("Active_Name"));
				busActive1.setActiveType(rs.getInt("Active_Type"));
				busActive1.setCollectNum(rs.getInt("Collect_Num"));
				busActive1.setCreateTime(rs.getDate("Create_Time"));
				busActive1.setEndDate(rs.getDate("End_Date"));
				busActive1.setJoinNum(rs.getInt("Join_Num"));
				busActive1.setPhaseNum(rs.getInt("Phase_Num"));
				busActive1.setPrizeImage(rs.getString("Prize_Image"));
				busActive1.setPrizeTile(rs.getString("Prize_Tile"));
				busActive1.setStartDate(rs.getDate("Start_Date"));
				busActive1.setStatus(rs.getString("status"));
				busActive1.setUserId(rs.getInt("User_Id"));
				busActive1.setImages(rs.getString("Images"));
				busActive1.setActiveTypeName(rs.getString("Type_Name"));
				busActive1.setImageUrl(rs.getString("Image_Url"));
				busActive1.setPetName(rs.getString("PetName"));
				busActive1.setVoteDesc(rs.getString("Vote_Desc"));
				busActive1.setProductDesc(rs.getString("Product_Desc"));
				busActive1.setCount(rs.getInt("coun"));
				return busActive1;
			}
		});
	}
	
	public List findTemByCollect(int count, int activeType) {
		String sql="select aa.*,bb.spot_name from (select a.*,b.petname,b.image_url,c.Actual_Name,c.qq_num,c.phone_num,c.weixin_num,c.spot_id,c.person_num,c.plan_date,c.travel_days,d.type_name from (select * from (select q.*,rownum t from bus_active q where active_type=? and status='1' order by collect_num desc) where t<=?) a left join sys_user b on b.user_id=a.user_id inner join bus_active_ex c on c.active_id=a.active_id inner join sys_active_type d on d.type_id=a.active_type) aa inner join sys_scenic_spot bb on bb.spot_id=aa.spot_id";
		return getJdbcTemplate().query(sql, new Object[]{activeType,count}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				BusActive busActive1=new BusActive();
				busActive1.setActiveId(rs.getInt("Active_Id"));
				busActive1.setActiveName(rs.getString("Active_Name"));
				busActive1.setActiveType(rs.getInt("Active_Type"));
				busActive1.setCollectNum(rs.getInt("Collect_Num"));
				busActive1.setCreateTime(rs.getDate("Create_Time"));
				busActive1.setEndDate(rs.getDate("End_Date"));
				busActive1.setJoinNum(rs.getInt("Join_Num"));
				busActive1.setPhaseNum(rs.getInt("Phase_Num"));
				busActive1.setPrizeImage(rs.getString("Prize_Image"));
				busActive1.setPrizeTile(rs.getString("Prize_Tile"));
				busActive1.setStartDate(rs.getDate("Start_Date"));
				busActive1.setStatus(rs.getString("status"));
				busActive1.setUserId(rs.getInt("User_Id"));
				busActive1.setImages(rs.getString("Images"));
				busActive1.setActiveTypeName(rs.getString("Type_Name"));
				busActive1.setImageUrl(rs.getString("Image_Url"));
				busActive1.setPetName(rs.getString("PetName"));
				busActive1.setVoteDesc(rs.getString("Vote_Desc"));
				busActive1.setProductDesc(rs.getString("Product_Desc"));
				busActive1.setActualName(rs.getString("Actual_Name"));
				busActive1.setQqNum(rs.getString("Qq_Num"));
				busActive1.setWeixinNum(rs.getString("Weixin_Num"));
				busActive1.setPhoneNum(rs.getString("Phone_Num"));
				busActive1.setSpotId(rs.getInt("Spot_Id"));
				busActive1.setSpotName(rs.getString("Spot_Name"));
				busActive1.setTravelDays(rs.getInt("Travel_Days"));
				busActive1.setPersonNum(rs.getString("Person_Num"));
				busActive1.setPlanDate(rs.getDate("Plan_Date"));
				return busActive1;
			}
		});
	}
	
	public List findTeamByCollect(int activeType, int pageNum, int pageCount,
			int spotId, String planDate) {
		int i1=pageNum*pageCount;
		int i2=(pageNum-1)*pageCount;
		String sql="select aa.*,bb.coun,cc.spot_name from (select * from (select p.*,rownum t from (select a.*,b.petname,b.image_url,c.Actual_Name,c.qq_num,c.phone_num,c.weixin_num,c.spot_id,c.person_num,c.plan_date,c.travel_days,d.type_name from (select * from bus_active where active_type=? and status='1') a left join sys_user b on b.user_id=a.user_id inner join bus_active_ex c on c.active_id=a.active_id inner join sys_active_type d on d.type_id=a.active_type) p where 1=1";
		if(spotId!=0){
			sql+=" and p.spot_id="+spotId;
		}
		if(!(planDate.equals(""))){
			sql+=" and p.start_date>=to_date('"+planDate+"','yyyy-mm-dd')";
		}
		sql+="order by p.active_id desc) where t<=? and t>?) aa inner join (select count(*) as coun from (select a.*,b.petname,b.image_url,c.Actual_Name,c.qq_num,c.phone_num,c.weixin_num,c.spot_id,c.person_num,c.plan_date,c.travel_days,d.type_name from (select * from bus_active where active_type=? and status='1') a left join sys_user b on b.user_id=a.user_id inner join bus_active_ex c on c.active_id=a.active_id inner join sys_active_type d on d.type_id=a.active_type) p where 1=1";
		if(spotId!=0){
			sql+=" and p.spot_id="+spotId;
		}
		if(!(planDate.equals(""))){
			sql+=" and p.start_date>=to_date('"+planDate+"','yyyy-mm-dd')";
		}
		sql+=" order by p.active_id desc) bb on 1=1 inner join sys_scenic_spot cc on cc.spot_id=aa.spot_id";
		return getJdbcTemplate().query(sql, new Object[]{activeType,i1,i2,activeType}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				BusActive busActive1=new BusActive();
				busActive1.setCount(rs.getInt("coun"));
				busActive1.setActiveId(rs.getInt("Active_Id"));
				busActive1.setActiveName(rs.getString("Active_Name"));
				busActive1.setActiveType(rs.getInt("Active_Type"));
				busActive1.setCollectNum(rs.getInt("Collect_Num"));
				busActive1.setCreateTime(rs.getDate("Create_Time"));
				busActive1.setEndDate(rs.getDate("End_Date"));
				busActive1.setJoinNum(rs.getInt("Join_Num"));
				busActive1.setPhaseNum(rs.getInt("Phase_Num"));
				busActive1.setPrizeImage(rs.getString("Prize_Image"));
				busActive1.setPrizeTile(rs.getString("Prize_Tile"));
				busActive1.setStartDate(rs.getDate("Start_Date"));
				busActive1.setStatus(rs.getString("status"));
				busActive1.setUserId(rs.getInt("User_Id"));
				busActive1.setImages(rs.getString("Images"));
				busActive1.setActiveTypeName(rs.getString("Type_Name"));
				busActive1.setImageUrl(rs.getString("Image_Url"));
				busActive1.setPetName(rs.getString("PetName"));
				busActive1.setVoteDesc(rs.getString("Vote_Desc"));
				busActive1.setProductDesc(rs.getString("Product_Desc"));
				busActive1.setActualName(rs.getString("Actual_Name"));
				busActive1.setQqNum(rs.getString("Qq_Num"));
				busActive1.setWeixinNum(rs.getString("Weixin_Num"));
				busActive1.setPhoneNum(rs.getString("Phone_Num"));
				busActive1.setSpotId(rs.getInt("Spot_Id"));
				busActive1.setSpotName(rs.getString("Spot_Name"));
				busActive1.setTravelDays(rs.getInt("Travel_Days"));
				busActive1.setPersonNum(rs.getString("Person_Num"));
				busActive1.setPlanDate(rs.getDate("Plan_Date"));
				return busActive1;
			}
		});
	}
	
	public BusActive findTeamActiveById(int activeId,int userId) {
		String sql="select aa.*,bb.spot_name,cc.isjoin from (select a.*,b.petname,b.image_url,c.Actual_Name,c.qq_num,c.phone_num,c.weixin_num,c.spot_id,c.person_num,c.plan_date,c.travel_days,c.active_desc,d.type_name from (select * from bus_active where active_id=?) a left join sys_user b on b.user_id=a.user_id inner join bus_active_ex c on c.active_id=a.active_id inner join sys_active_type d on d.type_id=a.active_type) aa inner join sys_scenic_spot bb on bb.spot_id=aa.spot_id inner join (select case when count(*)>0 then 0 when count(*)=0 then 1 end as isjoin from bus_active_join where user_id=? and active_id=?) cc on 1=1";
		return (BusActive)getJdbcTemplate().query(sql, new Object[] {activeId,userId,activeId}, new ResultSetExtractor() {			
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if(rs.next()){
					BusActive busActive1=new BusActive();
					busActive1.setActiveDesc(clobToString(rs.getClob("Active_Desc")));
					busActive1.setActiveId(rs.getInt("Active_Id"));
					busActive1.setActiveName(rs.getString("Active_Name"));
					busActive1.setActiveType(rs.getInt("Active_Type"));
					busActive1.setCollectNum(rs.getInt("Collect_Num"));
					busActive1.setCreateTime(rs.getDate("Create_Time"));
					busActive1.setEndDate(rs.getDate("End_Date"));
					busActive1.setJoinNum(rs.getInt("Join_Num"));
					busActive1.setPhaseNum(rs.getInt("Phase_Num"));
					busActive1.setPrizeImage(rs.getString("Prize_Image"));
					busActive1.setPrizeTile(rs.getString("Prize_Tile"));
					busActive1.setStartDate(rs.getDate("Start_Date"));
					busActive1.setStatus(rs.getString("status"));
					busActive1.setUserId(rs.getInt("User_Id"));
					busActive1.setImages(rs.getString("Images"));
					busActive1.setActiveTypeName(rs.getString("Type_Name"));
					busActive1.setImageUrl(rs.getString("Image_Url"));
					busActive1.setPetName(rs.getString("PetName"));
					busActive1.setVoteDesc(rs.getString("Vote_Desc"));
					busActive1.setProductDesc(rs.getString("Product_Desc"));
					busActive1.setActualName(rs.getString("Actual_Name"));
					busActive1.setQqNum(rs.getString("Qq_Num"));
					busActive1.setWeixinNum(rs.getString("Weixin_Num"));
					busActive1.setPhoneNum(rs.getString("Phone_Num"));
					busActive1.setSpotId(rs.getInt("Spot_Id"));
					busActive1.setSpotName(rs.getString("Spot_Name"));
					busActive1.setTravelDays(rs.getInt("Travel_Days"));
					busActive1.setPersonNum(rs.getString("Person_Num"));
					busActive1.setPlanDate(rs.getDate("Plan_Date"));
					busActive1.setIsJoin(rs.getString("IsJoin"));
					return busActive1;
			}
			return null;
			}
		});
	}
	
	public List findTeamActiveBySpot(int spotId, int count, int activeType) {
		String sql="select aa.*,bb.spot_name from (select * from (select m.*,rownum t from (select a.*,b.petname,b.image_url,c.Actual_Name,c.qq_num,c.phone_num,c.weixin_num,c.spot_id,c.person_num,c.plan_date,c.travel_days,d.type_name from (select * from bus_active where active_type=? and status='1') a left join sys_user b on b.user_id=a.user_id inner join bus_active_ex c on c.active_id=a.active_id inner join sys_active_type d on d.type_id=a.active_type) m where spot_id=? order by collect_num desc) where t<?) aa inner join sys_scenic_spot bb on bb.spot_id=aa.spot_id";
		return getJdbcTemplate().query(sql, new Object[]{activeType,spotId,count}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				BusActive busActive1=new BusActive();
				busActive1.setActiveId(rs.getInt("Active_Id"));
				busActive1.setActiveName(rs.getString("Active_Name"));
				busActive1.setActiveType(rs.getInt("Active_Type"));
				busActive1.setCollectNum(rs.getInt("Collect_Num"));
				busActive1.setCreateTime(rs.getDate("Create_Time"));
				busActive1.setEndDate(rs.getDate("End_Date"));
				busActive1.setJoinNum(rs.getInt("Join_Num"));
				busActive1.setPhaseNum(rs.getInt("Phase_Num"));
				busActive1.setPrizeImage(rs.getString("Prize_Image"));
				busActive1.setPrizeTile(rs.getString("Prize_Tile"));
				busActive1.setStartDate(rs.getDate("Start_Date"));
				busActive1.setStatus(rs.getString("status"));
				busActive1.setUserId(rs.getInt("User_Id"));
				busActive1.setImages(rs.getString("Images"));
				busActive1.setActiveTypeName(rs.getString("Type_Name"));
				busActive1.setImageUrl(rs.getString("Image_Url"));
				busActive1.setPetName(rs.getString("PetName"));
				busActive1.setVoteDesc(rs.getString("Vote_Desc"));
				busActive1.setProductDesc(rs.getString("Product_Desc"));
				busActive1.setActualName(rs.getString("Actual_Name"));
				busActive1.setQqNum(rs.getString("Qq_Num"));
				busActive1.setWeixinNum(rs.getString("Weixin_Num"));
				busActive1.setPhoneNum(rs.getString("Phone_Num"));
				busActive1.setSpotId(rs.getInt("Spot_Id"));
				busActive1.setSpotName(rs.getString("Spot_Name"));
				busActive1.setTravelDays(rs.getInt("Travel_Days"));
				busActive1.setPersonNum(rs.getString("Person_Num"));
				busActive1.setPlanDate(rs.getDate("Plan_Date"));
				return busActive1;
			}
		});
	}
	
	public void saveTeamActiveJoin(BusActiveJoin busActiveJoin) {	
			String sql2 = "update bus_active set join_num=(select join_num+1 from bus_active where active_id=?) where active_id=?";
			this.getJdbcTemplate().update(sql2, new Object[] {busActiveJoin.getActiveId(),busActiveJoin.getActiveId()});
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String sql = "insert into bus_active_join(active_join_id,create_date,active_id,user_id,join_desc,praise_num,phone_num) values(seq_user.nextval,to_date(?,'yyyy-mm-dd'),?,?,?,0,?)";
			this.getJdbcTemplate().update(sql, new Object[] {df.format(busActiveJoin.getCreateDate()),busActiveJoin.getActiveId(),busActiveJoin.getUserId(),busActiveJoin.getJoinDesc(),busActiveJoin.getPhoneNum()});
	}

	public void saveTeamActive(BusActive busActive) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String sql2 = "insert into bus_active(active_id,user_id,active_type,create_time,comment_num,collect_num,phase_num,start_date,end_date,status,active_name,product_desc,images) values(seq_user.nextval,?,?,to_date(?,'yyyy-mm-dd'),0,0,0,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),'1',?,?,?)";
		this.getJdbcTemplate().update(sql2, new Object[] {busActive.getUserId(),busActive.getActiveType(),df.format(busActive.getCreateTime()),df.format(busActive.getStartDate()),df.format(busActive.getEndDate()),busActive.getActiveName(),busActive.getProductDesc(),busActive.getImages()});		
		String sql = "insert into bus_active_ex(active_ex_id,active_id,qq_num,phone_num,weixin_num,spot_id,person_num,plan_date,active_desc,travel_days) values(seq_user.nextval,(select active_id from(select q.*,rownum t from bus_active q order by active_id desc) where t=1),?,?,?,?,?,to_date(?,'yyyy-mm-dd'),?,?)";
		this.getJdbcTemplate().update(sql, new Object[] {busActive.getQqNum(),busActive.getPhoneNum(),busActive.getWeixinNum(),busActive.getSpotId(),busActive.getPersonNum(),df.format(busActive.getPlanDate()),busActive.getActiveDesc(),busActive.getTravelDays()});
		
	}
	
	public List findTeamByUserJoin(int userId, int pageNum, int pageCount,
			String status) {
		int i1=pageNum*pageCount;
		int i2=(pageNum-1)*pageCount;
		String sql="select aa.*,bb.spot_name,cc.coun from (select a.*,b.petname,b.image_url,c.Actual_Name,c.qq_num,c.phone_num,c.weixin_num,c.spot_id,c.person_num,c.plan_date,c.travel_days,d.type_name from (select * from (select aq.*,rownum t from bus_active aq inner join (select active_id from bus_active_join where user_id=?) aw on aq.active_id=aw.active_id and status=? order by aq.active_id desc) where t<=? and t>?) a left join sys_user b on b.user_id=a.user_id inner join bus_active_ex c on c.active_id=a.active_id inner join sys_active_type d on d.type_id=a.active_type) aa inner join sys_scenic_spot bb on bb.spot_id=aa.spot_id inner join (select count(*) as coun from bus_active aq inner join (select q.active_id,rownum t from bus_active_join q where user_id=?) aw on aq.active_id=aw.active_id and status=?) cc on 1=1";
		return getJdbcTemplate().query(sql, new Object[]{userId,status,i1,i2,userId,status}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				BusActive busActive1=new BusActive();
				busActive1.setCount(rs.getInt("coun"));
				busActive1.setActiveId(rs.getInt("Active_Id"));
				busActive1.setActiveName(rs.getString("Active_Name"));
				busActive1.setActiveType(rs.getInt("Active_Type"));
				busActive1.setCollectNum(rs.getInt("Collect_Num"));
				busActive1.setCreateTime(rs.getDate("Create_Time"));
				busActive1.setEndDate(rs.getDate("End_Date"));
				busActive1.setJoinNum(rs.getInt("Join_Num"));
				busActive1.setPhaseNum(rs.getInt("Phase_Num"));
				busActive1.setPrizeImage(rs.getString("Prize_Image"));
				busActive1.setPrizeTile(rs.getString("Prize_Tile"));
				busActive1.setStartDate(rs.getDate("Start_Date"));
				busActive1.setStatus(rs.getString("status"));
				busActive1.setUserId(rs.getInt("User_Id"));
				busActive1.setImages(rs.getString("Images"));
				busActive1.setActiveTypeName(rs.getString("Type_Name"));
				busActive1.setImageUrl(rs.getString("Image_Url"));
				busActive1.setPetName(rs.getString("PetName"));
				busActive1.setVoteDesc(rs.getString("Vote_Desc"));
				busActive1.setProductDesc(rs.getString("Product_Desc"));
				busActive1.setActualName(rs.getString("Actual_Name"));
				busActive1.setQqNum(rs.getString("Qq_Num"));
				busActive1.setWeixinNum(rs.getString("Weixin_Num"));
				busActive1.setPhoneNum(rs.getString("Phone_Num"));
				busActive1.setSpotId(rs.getInt("Spot_Id"));
				busActive1.setSpotName(rs.getString("Spot_Name"));
				busActive1.setTravelDays(rs.getInt("Travel_Days"));
				busActive1.setPersonNum(rs.getString("Person_Num"));
				busActive1.setPlanDate(rs.getDate("Plan_Date"));
				return busActive1;
			}
		});
	}
	
	public void unJoinActive(int activeId, int userId) {
		String sql = "update bus_active set join_num=(select join_num-1 from bus_active where active_Id=?) where active_Id=?";
		this.getJdbcTemplate().update(sql, new Object[] {activeId,activeId});
		
		String sql2 = "delete bus_active_join where active_id=? and user_id=?";
		this.getJdbcTemplate().update(sql2, new Object[] {activeId,userId});
		
	}
		
	public List findTeamByUserCreate(int userId, int pageNum, int pageCount,
			String status) {
		int i1=pageNum*pageCount;
		int i2=(pageNum-1)*pageCount;
		String sql="select aa.*,bb.spot_name,cc.coun from (select a.*,b.petname,b.image_url,c.Actual_Name,c.qq_num,c.phone_num,c.weixin_num,c.spot_id,c.person_num,c.plan_date,c.travel_days,d.type_name from (select * from (select p.*,rownum t from bus_active p where user_id=? and status=? order by active_id desc) where t<=? and t>?) a inner join sys_user b on b.user_id=a.user_id inner join bus_active_ex c on c.active_id=a.active_id inner join sys_active_type d on d.type_id=a.active_type) aa inner join sys_scenic_spot bb on bb.spot_id=aa.spot_id inner join (select count(*) as coun from bus_active where user_id=? and status=?) cc on 1=1";
		return getJdbcTemplate().query(sql, new Object[]{userId,status,i1,i2,userId,status}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				BusActive busActive1=new BusActive();
				busActive1.setCount(rs.getInt("coun"));
				busActive1.setActiveId(rs.getInt("Active_Id"));
				busActive1.setActiveName(rs.getString("Active_Name"));
				busActive1.setActiveType(rs.getInt("Active_Type"));
				busActive1.setCollectNum(rs.getInt("Collect_Num"));
				busActive1.setCreateTime(rs.getDate("Create_Time"));
				busActive1.setEndDate(rs.getDate("End_Date"));
				busActive1.setJoinNum(rs.getInt("Join_Num"));
				busActive1.setPhaseNum(rs.getInt("Phase_Num"));
				busActive1.setPrizeImage(rs.getString("Prize_Image"));
				busActive1.setPrizeTile(rs.getString("Prize_Tile"));
				busActive1.setStartDate(rs.getDate("Start_Date"));
				busActive1.setStatus(rs.getString("status"));
				busActive1.setUserId(rs.getInt("User_Id"));
				busActive1.setImages(rs.getString("Images"));
				busActive1.setActiveTypeName(rs.getString("Type_Name"));
				busActive1.setImageUrl(rs.getString("Image_Url"));
				busActive1.setPetName(rs.getString("PetName"));
				busActive1.setVoteDesc(rs.getString("Vote_Desc"));
				busActive1.setProductDesc(rs.getString("Product_Desc"));
				busActive1.setActualName(rs.getString("Actual_Name"));
				busActive1.setQqNum(rs.getString("Qq_Num"));
				busActive1.setWeixinNum(rs.getString("Weixin_Num"));
				busActive1.setPhoneNum(rs.getString("Phone_Num"));
				busActive1.setSpotId(rs.getInt("Spot_Id"));
				busActive1.setSpotName(rs.getString("Spot_Name"));
				busActive1.setTravelDays(rs.getInt("Travel_Days"));
				busActive1.setPersonNum(rs.getString("Person_Num"));
				busActive1.setPlanDate(rs.getDate("Plan_Date"));
				return busActive1;
			}
		});
	}
	
	public void closeActive(int activeId) {
		String sql2 = "update bus_active set status='2' where active_id=?";
		this.getJdbcTemplate().update(sql2, new Object[] {activeId});
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String sql = "insert into bus_active_inform(inform_id,active_id,createdate,inform_content) values(seq_user.nextval,?,to_date(?,'yyyy-mm-dd'),'活动已关闭')";
		this.getJdbcTemplate().update(sql, new Object[] {activeId,df.format(new Date())});
	
	}
	
	public List findTeamByUserCollect(int userId, int pageNum, int pageCount,
			String status) {
		int i1=pageNum*pageCount;
		int i2=(pageNum-1)*pageCount;
		String sql="select aa.*,bb.spot_name,cc.coun from (select a.*,b.petname,b.image_url,c.Actual_Name,c.qq_num,c.phone_num,c.weixin_num,c.spot_id,c.person_num,c.plan_date,c.travel_days,d.type_name from (select * from (select p.*,rownum t from bus_active p inner join (select * from bus_collect where user_id=? and collect_type='4') h on p.active_id=h.rel_id  where status=? order by active_id desc) where t<=? and t>?) a inner join sys_user b on b.user_id=a.user_id inner join bus_active_ex c on c.active_id=a.active_id inner join sys_active_type d on d.type_id=a.active_type) aa inner join sys_scenic_spot bb on bb.spot_id=aa.spot_id inner join (select count(*) as coun from bus_active p inner join (select * from bus_collect where user_id=? and collect_type='4') h on p.active_id=h.rel_id  where status=?) cc on 1=1";
		return getJdbcTemplate().query(sql, new Object[]{userId,status,i1,i2,userId,status}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				BusActive busActive1=new BusActive();
				busActive1.setCount(rs.getInt("coun"));
				busActive1.setActiveId(rs.getInt("Active_Id"));
				busActive1.setActiveName(rs.getString("Active_Name"));
				busActive1.setActiveType(rs.getInt("Active_Type"));
				busActive1.setCollectNum(rs.getInt("Collect_Num"));
				busActive1.setCreateTime(rs.getDate("Create_Time"));
				busActive1.setEndDate(rs.getDate("End_Date"));
				busActive1.setJoinNum(rs.getInt("Join_Num"));
				busActive1.setPhaseNum(rs.getInt("Phase_Num"));
				busActive1.setPrizeImage(rs.getString("Prize_Image"));
				busActive1.setPrizeTile(rs.getString("Prize_Tile"));
				busActive1.setStartDate(rs.getDate("Start_Date"));
				busActive1.setStatus(rs.getString("status"));
				busActive1.setUserId(rs.getInt("User_Id"));
				busActive1.setImages(rs.getString("Images"));
				busActive1.setActiveTypeName(rs.getString("Type_Name"));
				busActive1.setImageUrl(rs.getString("Image_Url"));
				busActive1.setPetName(rs.getString("PetName"));
				busActive1.setVoteDesc(rs.getString("Vote_Desc"));
				busActive1.setProductDesc(rs.getString("Product_Desc"));
				busActive1.setActualName(rs.getString("Actual_Name"));
				busActive1.setQqNum(rs.getString("Qq_Num"));
				busActive1.setWeixinNum(rs.getString("Weixin_Num"));
				busActive1.setPhoneNum(rs.getString("Phone_Num"));
				busActive1.setSpotId(rs.getInt("Spot_Id"));
				busActive1.setSpotName(rs.getString("Spot_Name"));
				busActive1.setTravelDays(rs.getInt("Travel_Days"));
				busActive1.setPersonNum(rs.getString("Person_Num"));
				busActive1.setPlanDate(rs.getDate("Plan_Date"));
				return busActive1;
			}
		});
	}
	
	public void unCollectActive(int activeId, int userId) {
		String sql = "update bus_active set collect_num=(select collect_num-1 from bus_active where active_Id=?) where active_Id=?";
		this.getJdbcTemplate().update(sql, new Object[] {activeId,activeId});
		
		String sql2 = "delete bus_collect where rel_id=? and user_id=? and collect_type='4'";
		this.getJdbcTemplate().update(sql2, new Object[] {activeId,userId});
	}
	
	public List findInformByUser(int userId) {
		String sql="select aa.user_id,aa.active_name,bb.* from (select b.user_id,b.active_name,b.active_id from (select * from bus_active_join where user_id=?) a inner join bus_active b on b.active_id=a.active_id order by b.active_id desc) aa inner join bus_active_inform bb on bb.active_id=aa.active_id and bb.createdate>=trunc(sysdate - 3)";
		return getJdbcTemplate().query(sql, new Object[]{userId}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				BusActiveInform busActiveInform=new BusActiveInform();
				busActiveInform.setInformId(rs.getInt("Inform_Id"));
				busActiveInform.setActiveId(rs.getInt("Active_Id"));
				busActiveInform.setCreateDate(rs.getDate("CreateDate"));
				busActiveInform.setInformContent(rs.getString("Inform_Content"));
				busActiveInform.setUserId(rs.getInt("User_Id"));
				busActiveInform.setActiveName(rs.getString("Active_Name"));
				return busActiveInform;
			}
		});
	}
	
	public List findAllActiveByType(int activeType, int pageNum, int pageCount) {
		int i1=pageNum*pageCount;
		int i2=(pageNum-1)*pageCount;
		String sql="select aa.*,bb.coun from (select a.*,b.type_name,c.image_url,c.petname from (select * from(select q.*,rownum t from bus_active q where active_type=? order by active_id desc) where t<=? and t>?) a inner join sys_active_type b on b.type_id=a.active_type left join sys_user c on c.user_id=a.user_id) aa inner join (select count(*) as coun from bus_active q where active_type=?) bb on 1=1";
		return getJdbcTemplate().query(sql, new Object[]{activeType,i1,i2,activeType}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				BusActive busActive1=new BusActive();
				busActive1.setActiveId(rs.getInt("Active_Id"));
				busActive1.setActiveName(rs.getString("Active_Name"));
				busActive1.setActiveType(rs.getInt("Active_Type"));
				busActive1.setCollectNum(rs.getInt("Collect_Num"));
				busActive1.setCreateTime(rs.getDate("Create_Time"));
				busActive1.setEndDate(rs.getDate("End_Date"));
				busActive1.setJoinNum(rs.getInt("Join_Num"));
				busActive1.setPhaseNum(rs.getInt("Phase_Num"));
				busActive1.setPrizeImage(rs.getString("Prize_Image"));
				busActive1.setPrizeTile(rs.getString("Prize_Tile"));
				busActive1.setStartDate(rs.getDate("Start_Date"));
				busActive1.setStatus(rs.getString("status"));
				busActive1.setUserId(rs.getInt("User_Id"));
				busActive1.setImages(rs.getString("Images"));
				busActive1.setActiveTypeName(rs.getString("Type_Name"));
				busActive1.setImageUrl(rs.getString("Image_Url"));
				busActive1.setPetName(rs.getString("PetName"));
				busActive1.setVoteDesc(rs.getString("Vote_Desc"));
				busActive1.setProductDesc(rs.getString("Product_Desc"));
				busActive1.setCount(rs.getInt("coun"));
				return busActive1;
			}
		});
	}
	
	public void openActive(int activeId) {
		String sql2 = "update bus_active set status='1' where active_id=?";
		this.getJdbcTemplate().update(sql2, new Object[] {activeId});
	}
	
	public List findActiveByStatusAndType(String status, int activeType) {
		String sql="select * from bus_active where status=? and active_Type=?";
		return getJdbcTemplate().query(sql, new Object[]{status,activeType}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				BusActive busActive1=new BusActive();
				busActive1.setActiveId(rs.getInt("active_id"));
				return busActive1;
			}
		});
	}
	
	public void saveWebActive(BusActive busActive) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String sql2 = "insert into bus_active(active_id,active_type,create_time,comment_num,collect_num,phase_num,start_date,end_date,status,active_name,product_desc,images,vote_Desc,PRIZE_TILE,PRIZE_IMAGE) values(seq_user.nextval,?,to_date(?,'yyyy-mm-dd'),0,0,0,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),'0',?,?,?,?,?,?)";
		this.getJdbcTemplate().update(sql2, new Object[] {busActive.getActiveType(),df.format(busActive.getCreateTime()),df.format(busActive.getStartDate()),df.format(busActive.getEndDate()),busActive.getActiveName(),busActive.getProductDesc(),busActive.getImages(),busActive.getVoteDesc(),busActive.getPrizeTile(),busActive.getPrizeImage()});		
		String sql = "insert into bus_active_ex(active_ex_id,active_id,qq_num,phone_num,weixin_num,spot_id,person_num,plan_date,active_desc,travel_days) values(seq_user.nextval,(select active_id from(select * from bus_active order by active_id desc) where rownum=1),?,?,?,?,?,to_date(?,'yyyy-mm-dd'),?,?)";
		this.getJdbcTemplate().update(sql, new Object[] {busActive.getQqNum(),busActive.getPhoneNum(),busActive.getWeixinNum(),busActive.getSpotId(),busActive.getPersonNum(),df.format(busActive.getPlanDate()),busActive.getActiveDesc(),busActive.getTravelDays()});
	}
	
	public void saveWebTeamActive(BusActive busActive) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String sql2 = "insert into bus_active(active_id,active_type,create_time,comment_num,collect_num,phase_num,start_date,end_date,status,active_name,product_desc,images) values(seq_user.nextval,?,to_date(?,'yyyy-mm-dd'),0,0,0,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),'1',?,?,?)";
		this.getJdbcTemplate().update(sql2, new Object[] {busActive.getActiveType(),df.format(busActive.getCreateTime()),df.format(busActive.getStartDate()),df.format(busActive.getEndDate()),busActive.getActiveName(),busActive.getProductDesc(),busActive.getImages()});		
		String sql = "insert into bus_active_ex(active_ex_id,active_id,qq_num,phone_num,weixin_num,spot_id,person_num,plan_date,active_desc,travel_days) values(seq_user.nextval,(select actice_id from (select q.* from bus_active q order by q.active_id desc)m where rownum=1),?,?,?,?,?,to_date(?,'yyyy-mm-dd'),?,?)";
		this.getJdbcTemplate().update(sql, new Object[] {busActive.getQqNum(),busActive.getPhoneNum(),busActive.getWeixinNum(),busActive.getSpotId(),busActive.getPersonNum(),df.format(busActive.getPlanDate()),busActive.getActiveDesc(),busActive.getTravelDays()});
		
	}

}
