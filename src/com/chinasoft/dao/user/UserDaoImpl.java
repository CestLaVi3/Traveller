package com.chinasoft.dao.user;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.chinasoft.dao.BaseDao;
import com.chinasoft.model.manager.SysManager;
import com.chinasoft.model.strategy.BusTravelDesc;
import com.chinasoft.model.user.SysUser;

public class UserDaoImpl extends BaseDao implements UserDao {

	public SysUser login(String loginName) {
		
		String sql="SELECT * FROM SYS_USER WHERE LOGIN_NAME=?";
		
		return (SysUser)getJdbcTemplate().query(sql, new Object[] {loginName},new ResultSetExtractor() {					
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					SysUser user=new SysUser();
					user.setAddress(rs.getString("Address"));
					user.setAttentionNum(rs.getInt("Attention_Num"));
					user.setAuthenticated(rs.getString("AuthenTicated"));
					user.setBirthday(rs.getDate("Birthday"));
					user.setEmail(rs.getString("Email"));
					user.setFreezeStatus(rs.getString("Freeze_Status"));
					user.setFunsNum(rs.getInt("Funs_Num"));	
					user.setIdentitycard(rs.getString("IdentityCard"));					
					user.setIdentityCardImage(rs.getString("IdentityCard_Image"));
					user.setImageUrl(rs.getString("Image_Url"));
					user.setLocation(rs.getString("Location"));
					user.setLoginName(rs.getString("Login_Name"));
					user.setPassword(rs.getString("Password"));
					user.setPetName(rs.getString("PetName"));
					user.setPhoneNum(rs.getString("Phone_Num"));
					user.setQqNum(rs.getString("Qq_Num"));
					user.setSex(rs.getString("sex"));
					user.setSignature(rs.getString("SigNature"));
					user.setUserId(rs.getInt("user_id"));
					user.setUserName(rs.getString("user_name"));
					user.setVistNum(rs.getInt("Vist_Num"));
					return user;
				}
				return null;
			}
		});
	}

	public void insertUser(SysUser user) {
		String sql = "insert into sys_user(user_id,login_name,email,password,image_url,petname,AUTHENTICATED,FREEZE_STATUS) values (seq_user.nextval,?,?,?,'upload/index/images/22.jpg','小不点','0','1')";
		this.getJdbcTemplate().update(sql, new Object[] {user.getEmail(),user.getEmail(),user.getPassword()});
	}

	
	public List findCurUserCollect(int userId,int pageNum,int pageCount) {
		Integer i1=pageNum*pageCount;
		Integer i2=(pageNum-1)*pageCount;
		String sql="select aa.*,bb.coun from (select * from (select a.*,rownum t from sys_user a inner join (select * from bus_collect where collect_type='5' and user_id=?) b on a.user_id=b.rel_id order by b.create_time desc) where t<=? and t>?) aa inner join (select count(*) as coun from sys_user a inner join (select * from bus_collect where collect_type='5' and user_id=?) b on a.user_id=b.rel_id) bb on 1=1";
		return getJdbcTemplate().query(sql, new Object[]{userId,i1,i2,userId}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				SysUser user=new SysUser();
				user.setCount(rs.getInt("coun"));
				user.setAddress(rs.getString("Address"));
				user.setAttentionNum(rs.getInt("Attention_Num"));
				user.setAuthenticated(rs.getString("AuthenTicated"));
				user.setBirthday(rs.getDate("Birthday"));
				user.setEmail(rs.getString("Email"));
				user.setFreezeStatus(rs.getString("Freeze_Status"));
				user.setFunsNum(rs.getInt("Funs_Num"));
				user.setIdentitycard(rs.getString("IdentityCard"));
				user.setIdentityCardImage(rs.getString("IdentityCard_Image"));
				user.setImageUrl(rs.getString("Image_Url"));
				user.setLocation(rs.getString("Location"));
				user.setLoginName(rs.getString("Login_Name"));
				user.setPassword(rs.getString("Password"));
				user.setPetName(rs.getString("PetName"));
				user.setPhoneNum(rs.getString("Phone_Num"));
				user.setQqNum(rs.getString("Qq_Num"));
				user.setSex(rs.getString("sex"));
				user.setSignature(rs.getString("SigNature"));
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setVistNum(rs.getInt("Vist_Num"));
				return user;
			}
		});
	}

	
	public void unCollectUser(int userId,int userId2) {
		String sql = "update sys_user set funs_num=(select funs_num-1 from sys_user where user_id=?) where user_id=?";
		this.getJdbcTemplate().update(sql, new Object[] {userId,userId});
		String sql3 = "update sys_user set attention_num=(select attention_num-1 from sys_user where user_id=?) where user_id=?";
		this.getJdbcTemplate().update(sql3, new Object[] {userId2,userId2});
		String sql2 = "delete from bus_collect where collect_type='5' and user_id=? and rel_id=?";
		this.getJdbcTemplate().update(sql2, new Object[] {userId2,userId});
	}

	
	public List findByCondition(String sex, String petName,
			String freezeStatus, int pageNum, int pageCount) {
		Integer i1=pageNum*pageCount;
		Integer i2=(pageNum-1)*pageCount;
		String sql="select aa.*,bb.coun from (select * from (select a.*,rownum t from sys_user a where";
		if(!sex.equals("3")){
			sql+=" sex="+sex+" and";
		}
		sql+=" petName like '%"+petName+"%' and freeze_Status=? order by user_id desc) where t<=? and t>?) aa inner join (select count(*) as coun from sys_user a where";
		if(!sex.equals("3")){
			sql+=" sex="+sex+" and";
		}
		sql+=" petName like '%"+petName+"%' and freeze_Status=?) bb on 1=1";
		return getJdbcTemplate().query(sql, new Object[]{freezeStatus,i1,i2,freezeStatus}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				SysUser user=new SysUser();
				user.setCount(rs.getInt("coun"));
				user.setAddress(rs.getString("Address"));
				user.setAttentionNum(rs.getInt("Attention_Num"));
				user.setAuthenticated(rs.getString("AuthenTicated"));
				user.setBirthday(rs.getDate("Birthday"));
				user.setEmail(rs.getString("Email"));
				user.setFreezeStatus(rs.getString("Freeze_Status"));
				user.setFunsNum(rs.getInt("Funs_Num"));
				user.setIdentitycard(rs.getString("IdentityCard"));
				user.setIdentityCardImage(rs.getString("IdentityCard_Image"));
				user.setImageUrl(rs.getString("Image_Url"));
				user.setLocation(rs.getString("Location"));
				user.setLoginName(rs.getString("Login_Name"));
				user.setPassword(rs.getString("Password"));
				user.setPetName(rs.getString("PetName"));
				user.setPhoneNum(rs.getString("Phone_Num"));
				user.setQqNum(rs.getString("Qq_Num"));
				user.setSex(rs.getString("sex"));
				user.setSignature(rs.getString("SigNature"));
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setVistNum(rs.getInt("Vist_Num"));
				return user;
			}
		});
	}

	
	public void collectUser(int userId, int userId2) {
		String sql = "update sys_user set funs_num=(select funs_num+1 from sys_user where user_id=?) where user_id=?";
		this.getJdbcTemplate().update(sql, new Object[] {userId,userId});
		String sql3 = "update sys_user set attention_num=(select attention_num+1 from sys_user where user_id=?) where user_id=?";
		this.getJdbcTemplate().update(sql3, new Object[] {userId2,userId2});
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String sql2 = "insert into bus_collect(collect_id,user_id,rel_id,create_time,collect_type) values(seq_user.nextval,?,?,to_date(?,'yyyy-mm-dd'),'5')";
		this.getJdbcTemplate().update(sql2, new Object[] {userId2,userId,df.format(new Date())});
	}

	
	public List findCurUserFuns(int userId, int pageNum, int pageCount) {
		Integer i1=pageNum*pageCount;
		Integer i2=(pageNum-1)*pageCount;
		String sql="select aa.*,bb.coun from (select * from (select a.*,rownum t from sys_user a inner join (select * from bus_collect where collect_type='5' and rel_id=?) b on a.user_id=b.user_id order by b.create_time desc) where t<=? and t>?) aa inner join (select count(*) as coun from sys_user a inner join (select * from bus_collect where collect_type='5' and rel_id=?) b on a.user_id=b.user_id) bb on 1=1";
		return getJdbcTemplate().query(sql, new Object[]{userId,i1,i2,userId}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				SysUser user=new SysUser();
				user.setCount(rs.getInt("coun"));
				user.setAddress(rs.getString("Address"));
				user.setAttentionNum(rs.getInt("Attention_Num"));
				user.setAuthenticated(rs.getString("AuthenTicated"));
				user.setBirthday(rs.getDate("Birthday"));
				user.setEmail(rs.getString("Email"));
				user.setFreezeStatus(rs.getString("Freeze_Status"));
				user.setFunsNum(rs.getInt("Funs_Num"));

				user.setIdentitycard(rs.getString("IdentityCard"));

				user.setIdentityCardImage(rs.getString("IdentityCard_Image"));
				user.setImageUrl(rs.getString("Image_Url"));
				user.setLocation(rs.getString("Location"));
				user.setLoginName(rs.getString("Login_Name"));
				user.setPassword(rs.getString("Password"));
				user.setPetName(rs.getString("PetName"));
				user.setPhoneNum(rs.getString("Phone_Num"));
				user.setQqNum(rs.getString("Qq_Num"));
				user.setSex(rs.getString("sex"));
				user.setSignature(rs.getString("SigNature"));
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setVistNum(rs.getInt("Vist_Num"));
				return user;
			}
		});
	}

	
	public List findCurUserBrowse(int userId, int pageNum, int pageCount) {
		Integer i1=pageNum*pageCount;
		Integer i2=(pageNum-1)*pageCount;
		String sql="select aa.*,bb.coun from (select * from (select a.*,rownum t from sys_user a inner join (select distinct user_id from bus_browse where browse_type='5' and rel_id=?) b on a.user_id=b.user_id order by b.user_id desc) where t<=? and t>?) aa inner join (select count(distinct user_id) as coun from bus_browse where browse_type='5' and rel_id=?) bb on 1=1";
		return getJdbcTemplate().query(sql, new Object[]{userId,i1,i2,userId}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				SysUser user=new SysUser();
				user.setCount(rs.getInt("coun"));
				user.setAddress(rs.getString("Address"));
				user.setAttentionNum(rs.getInt("Attention_Num"));
				user.setAuthenticated(rs.getString("AuthenTicated"));
				user.setBirthday(rs.getDate("Birthday"));
				user.setEmail(rs.getString("Email"));
				user.setFreezeStatus(rs.getString("Freeze_Status"));
				user.setFunsNum(rs.getInt("Funs_Num"));

				user.setIdentitycard(rs.getString("IdentityCard"));

				user.setIdentityCardImage(rs.getString("IdentityCard_Image"));
				user.setImageUrl(rs.getString("Image_Url"));
				user.setLocation(rs.getString("Location"));
				user.setLoginName(rs.getString("Login_Name"));
				user.setPassword(rs.getString("Password"));
				user.setPetName(rs.getString("PetName"));
				user.setPhoneNum(rs.getString("Phone_Num"));
				user.setQqNum(rs.getString("Qq_Num"));
				user.setSex(rs.getString("sex"));
				user.setSignature(rs.getString("SigNature"));
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setVistNum(rs.getInt("Vist_Num"));
				return user;
			}
		});
	}

	
	public SysUser findUserByUserId(int userId) {		
		String sql="SELECT * FROM SYS_USER WHERE user_id=?";		
		return (SysUser)getJdbcTemplate().query(sql, new Object[] {userId},new ResultSetExtractor() {		
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					SysUser user=new SysUser();
					user.setAddress(rs.getString("Address"));
					user.setAttentionNum(rs.getInt("Attention_Num"));
					user.setAuthenticated(rs.getString("AuthenTicated"));
					user.setBirthday(rs.getDate("Birthday"));
					user.setEmail(rs.getString("Email"));
					user.setFreezeStatus(rs.getString("Freeze_Status"));
					user.setFunsNum(rs.getInt("Funs_Num"));
					user.setIdentitycard(rs.getString("IdentityCard"));
					user.setIdentityCardImage(rs.getString("IdentityCard_Image"));
					user.setImageUrl(rs.getString("Image_Url"));
					user.setLocation(rs.getString("Location"));
					user.setLoginName(rs.getString("Login_Name"));
					user.setPassword(rs.getString("Password"));
					user.setPetName(rs.getString("PetName"));
					user.setPhoneNum(rs.getString("Phone_Num"));
					user.setQqNum(rs.getString("Qq_Num"));
					user.setSex(rs.getString("sex"));
					user.setSignature(rs.getString("SigNature"));
					user.setUserId(rs.getInt("user_id"));
					user.setUserName(rs.getString("user_name"));
					user.setVistNum(rs.getInt("Vist_Num"));
					return user;
				}
				return null;
			}
		});
	}

	
	public void updateBaseInfor(SysUser user) {
		String sql2 = "update sys_user set image_url=?,petname=?,sex=?,location=?,qq_num=?,email=?,signature=? where user_id=?";
		this.getJdbcTemplate().update(sql2, new Object[] {user.getImageUrl(),user.getPetName(),user.getSex(),user.getLocation(),user.getQqNum(),user.getEmail(),user.getSignature(),user.getUserId()});
	}

	
	public void updateAuthInfor(SysUser user) {
		String sql2 = "update sys_user set user_name=?,address=?,phone_num=?,identitycard=?,identitycard_image=?,Authenticated=? where user_id=?";
		this.getJdbcTemplate().update(sql2, new Object[] {user.getUserName(),user.getAddress(),user.getPhoneNum(),Integer.parseInt(user.getIdentitycard()),user.getIdentityCardImage(),user.getAuthenticated(),user.getUserId()});
	}

	
	public void updatePassword(int userId, String password) {
		String sql2 = "update sys_user set password=? where user_id=?";
		this.getJdbcTemplate().update(sql2, new Object[] {password,userId});
	}

	
	public SysManager findManagerByLoginName(String loginName) {
		String sql="SELECT * FROM SYS_manager WHERE login_name=?";		
		return (SysManager)getJdbcTemplate().query(sql, new Object[] {loginName},new ResultSetExtractor() {		
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					SysManager manager=new SysManager();
					manager.setLoginName(rs.getString("login_name"));
					manager.setManagerId(rs.getInt("manager_id"));
					manager.setPassword(rs.getString("password"));
					manager.setStatus(rs.getString("status"));
					return manager;
				}
				return null;
			}
		});
	}

	
	public void updateVistNum(int userId,int userId2) {
		String sql2 = "update sys_user set vist_num=(select vist_num+1 from sys_user where user_ID=?) where user_id=?";
		this.getJdbcTemplate().update(sql2, new Object[] {userId2,userId2});SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String sql = "insert into bus_browse(browse_id,user_id,create_time,browse_type,rel_id) values(seq_user.nextval,?,to_date(?,'yyyy-mm-dd'),'5',?)";
		this.getJdbcTemplate().update(sql, new Object[] {userId,df.format(new Date()),userId2});
	
	}

	
	public void checkUser(int userId,String authenticated) {
		String sql = "update sys_user set AUTHENTICATED=? where user_id=?";
		this.getJdbcTemplate().update(sql, new Object[] {authenticated,userId});	
	}

	
	

	
	public void freezeUser(int userId,String freezeStatus) {
		String sql = "update sys_user set FREEZE_STATUS=? where user_id=?";
		this.getJdbcTemplate().update(sql, new Object[] {freezeStatus,userId});	
	}


	
	public List findAllUser(int pageNum,int pageCount) {
		Integer i1=pageNum*pageCount;
		Integer i2=(pageNum-1)*pageCount;
		String sql="select aa.*,bb.coun from (select * from (select q.*,rownum t from sys_user order q by user_id desc) where t<=? and t>?) aa inner join (select count(*) as coun from sys_user) bb on 1=1";
		return getJdbcTemplate().query(sql, new Object[]{i1,i2}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				SysUser user=new SysUser();
				user.setCount(rs.getInt("coun"));
				user.setAddress(rs.getString("Address"));
				user.setAttentionNum(rs.getInt("Attention_Num"));
				user.setAuthenticated(rs.getString("AuthenTicated"));
				user.setBirthday(rs.getDate("Birthday"));
				user.setEmail(rs.getString("Email"));
				user.setFreezeStatus(rs.getString("Freeze_Status"));
				user.setFunsNum(rs.getInt("Funs_Num"));
				user.setIdentitycard(rs.getString("IdentityCard"));
				user.setIdentityCardImage(rs.getString("IdentityCard_Image"));
				user.setImageUrl(rs.getString("Image_Url"));
				user.setLocation(rs.getString("Location"));
				user.setLoginName(rs.getString("Login_Name"));
				user.setPassword(rs.getString("Password"));
				user.setPetName(rs.getString("PetName"));
				user.setPhoneNum(rs.getString("Phone_Num"));
				user.setQqNum(rs.getString("Qq_Num"));
				user.setSex(rs.getString("sex"));
				user.setSignature(rs.getString("SigNature"));
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setVistNum(rs.getInt("Vist_Num"));
				return user;
			}
		});
	}


	public List findUserByName(String name,int pageNum,int pageCount) {
		Integer i1=pageNum*pageCount;
		Integer i2=(pageNum-1)*pageCount;
		String sql="select aa.*,bb.coun from (select * from (select q.*,rownum t from sys_user q where q.petName like '%"+name+"%' order by q.user_id desc) where t<=? and t>?) aa inner join (select count(*) as coun from sys_user where petName like '%"+name+"%') bb on 1=1";
		return getJdbcTemplate().query(sql, new Object[]{i1,i2}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				SysUser user=new SysUser();
				user.setCount(rs.getInt("coun"));
				user.setAddress(rs.getString("Address"));
				user.setAttentionNum(rs.getInt("Attention_Num"));
				user.setAuthenticated(rs.getString("AuthenTicated"));
				user.setBirthday(rs.getDate("Birthday"));
				user.setEmail(rs.getString("Email"));
				user.setFreezeStatus(rs.getString("Freeze_Status"));
				user.setFunsNum(rs.getInt("Funs_Num"));
				user.setIdentitycard(rs.getString("IdentityCard"));
				user.setIdentityCardImage(rs.getString("IdentityCard_Image"));
				user.setImageUrl(rs.getString("Image_Url"));
				user.setLocation(rs.getString("Location"));
				user.setLoginName(rs.getString("Login_Name"));
				user.setPassword(rs.getString("Password"));
				user.setPetName(rs.getString("PetName"));
				user.setPhoneNum(rs.getString("Phone_Num"));
				user.setQqNum(rs.getString("Qq_Num"));
				user.setSex(rs.getString("sex"));
				user.setSignature(rs.getString("SigNature"));
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setVistNum(rs.getInt("Vist_Num"));
				return user;
			}
		});
	}

	

}
