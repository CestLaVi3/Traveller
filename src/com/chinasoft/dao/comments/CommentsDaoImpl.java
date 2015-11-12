package com.chinasoft.dao.comments;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.chinasoft.dao.BaseDao;
import com.chinasoft.model.general.BusComment;
import com.chinasoft.model.general.BusReply;
import com.chinasoft.model.spot.SysScenicSpot;
import com.chinasoft.model.spot.SysSpot;
import com.chinasoft.model.strategy.BusStagtegy;

public class CommentsDaoImpl extends BaseDao implements CommentsDao{

	
	public void deleteCommentById(int cid,int uid) {
		
		String sql2 = "update bus_travel_desc set comment_num=(select comment_num-1 from bus_travel_desc where travel_desc_id=(select rel_id from bus_comment where comment_id=?)) where travel_desc_id=(select rel_id from bus_comment where comment_id=? and comment_type='0')";
		this.getJdbcTemplate().update(sql2, new Object[] {cid,cid});	
		String sql4 = "update bus_stagtegy set comment_num=(select comment_num-1 from bus_stagtegy where stagtegy_id=(select rel_id from bus_comment where comment_id=?)) where stagtegy_id=(select rel_id from bus_comment where comment_id=? and comment_type='1')";
		this.getJdbcTemplate().update(sql4, new Object[] {cid,cid});
		String sql5 = "update bus_active set comment_num=(select comment_num-1 from bus_active where active_id=(select rel_id from bus_comment where comment_id=?)) where active_id=(select rel_id from bus_comment where comment_id=? and comment_type='4')";
		this.getJdbcTemplate().update(sql5, new Object[] {cid,cid});
		String sql3 = "delete bus_reply where comment_id=?";
		this.getJdbcTemplate().update(sql3, new Object[] {cid});
		String sql = "delete bus_comment where comment_id=? and user_id=?";
		this.getJdbcTemplate().update(sql, new Object[] {cid,uid});	
	}

	
	public void deleteReply(int id,int uid) {
		String sql = "delete bus_reply where reply_id=? and user_id=?";
		this.getJdbcTemplate().update(sql, new Object[] {id,uid});
	}


	
	public void saveReply(int cid,int uid,String rc,String rt) {
		String sql = "insert into bus_reply(reply_id,comment_id,user_id,reply_time,reply_content) values(seq_user.nextval,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?)";
		this.getJdbcTemplate().update(sql, new Object[] {cid,uid,rt,rc});		
	}


	
	public void saveComment(int rid, int uid, String cc, String ct, String st,int commentLevel) {
		String sql2=null;
		if(ct.equals("0")){
			sql2 = "update bus_travel_desc set comment_num=(select comment_num+1 from bus_travel_desc where travel_desc_id=?) where travel_desc_id=?";
		}
		if(ct.equals("1")){
		sql2 = "update bus_stagtegy set comment_num=(select comment_num+1 from bus_stagtegy where stagtegy_id=?) where stagtegy_id=?";
		}
		if(ct.equals("4")){
			sql2 = "update bus_active set comment_num=(select comment_num+1 from bus_active where active_id=?) where active_id=?";
		}
		this.getJdbcTemplate().update(sql2, new Object[] {rid,rid});
		
		String sql = "insert into bus_comment(comment_id,rel_id,comment_type,user_id,create_time,comment_content) values(seq_user.nextval,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?)";
		if(ct.equals("4")){
			sql = "insert into bus_comment(comment_id,rel_id,comment_type,user_id,create_time,comment_content,comment_Level) values(seq_user.nextval,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,"+commentLevel+")";
		}
		this.getJdbcTemplate().update(sql, new Object[] {rid,ct,uid,st,cc});
		
	}


	public List findCommentById(int relId, String commentType, int pageNum,
			int pageCount,int commentLevel) {
		Integer rInteger1=pageNum*pageCount; 
		Integer rInteger2=(pageNum-1)*pageCount;		
		String sql="select aa.*,bb.coun from (select a.*,to_char(a.create_time,'yyyy-mm-dd') as create_date,b.image_url,b.petname from (select q.*,rownum t from bus_comment q where comment_type=? and rel_id=? order by comment_id desc) a inner join sys_user b on b.user_id=a.user_id and t<=? and t>?) aa inner join (select count(*) as coun from bus_comment where comment_type=? and rel_id=?) bb on 1=1";
		if(commentType.equals("4")){
			if(commentLevel!=3){
				sql="select aa.*,bb.coun from (select a.*,to_char(a.create_time,'yyyy-mm-dd') as create_date,b.image_url,b.petname from (select q.*,rownum t from bus_comment q where comment_type=? and rel_id=? and comment_level="+commentLevel+" order by comment_id desc) a inner join sys_user b on b.user_id=a.user_id and t<=? and t>?) aa inner join (select count(*) as coun from bus_comment where comment_type=? and rel_id=? and comment_level="+commentLevel+") bb on 1=1";	
			}			
		}
		return getJdbcTemplate().query(sql, new Object[]{commentType,relId,rInteger1,rInteger2,commentType,relId}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				BusComment busComment=new BusComment();
				busComment.setCommentId(rs.getInt("Comment_Id"));
				busComment.setCommentType(rs.getString("Comment_Type"));
				busComment.setUserId(rs.getInt("User_Id"));
				busComment.setCommentContent(rs.getString("Comment_Content"));
				busComment.setCommentLevel(rs.getString("Comment_Level"));
				busComment.setRelId(rs.getInt("Rel_Id"));
				try {
					busComment.setCreateTime(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("create_date")));
				} catch (ParseException e) {
					
					e.printStackTrace();
				}
				busComment.setImageUrl(rs.getString("Image_Url"));
				busComment.setCount(rs.getInt("coun"));
				busComment.setPetName(rs.getString("PetName"));
				return busComment;
			}
		});
	}


	
	public List findReplyByCommentId(List c) {
			String sql="select * from (select a.*,to_char(a.reply_time,'yyyy-mm-dd') as reply_date,b.image_Url,b.petName from bus_reply a inner join sys_user b on b.user_id=a.user_id order by a.reply_id asc)where comment_id in(";
			for(int i=0;i<c.size();i++){
				BusComment busComment=(BusComment)c.get(i);				
				sql+=busComment.getCommentId()+",";
			}				
			sql=sql.substring(0, sql.length()-1);
			sql+=")";
		return getJdbcTemplate().query(sql, new Object[]{}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			BusReply busReply=new BusReply();
			busReply.setReplyId(rs.getInt("Reply_Id"));
			busReply.setReplyContent(rs.getString("Reply_Content"));
			busReply.setUserId(rs.getInt("User_Id"));
			try {
				busReply.setReplyTime(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("reply_date")));
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
			busReply.setImageUrl(rs.getString("Image_Url"));
			busReply.setPetName(rs.getString("PetName"));
			busReply.setCommentId(rs.getInt("Comment_Id"));
			return busReply;
			}
		});
	}
	
}
