package com.chinasoft.dao.comments;

import java.util.List;

public interface CommentsDao {
	void deleteCommentById(int id,int uid);
	
	void deleteReply(int id,int uid);
	
	void saveReply(int cid,int uid,String rc,String rt);
	
	void saveComment(int rid,int uid,String cc,String ct,String st,int commentLevel);

	List findCommentById(int relId,String  commentType,int pageNum,int pageCount,int commentLevel);

	List findReplyByCommentId(List c);
}
