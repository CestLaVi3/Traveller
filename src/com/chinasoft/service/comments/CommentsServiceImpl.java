package com.chinasoft.service.comments;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.chinasoft.common.utils.Exception.BusinessException;
import com.chinasoft.common.utils.log.HollyinfoLog;
import com.chinasoft.common.utils.log.HollyinfoLogger;
import com.chinasoft.dao.comments.CommentsDao;
import com.chinasoft.service.scienceSpot.ScienceSpotServiceImpl;

public class CommentsServiceImpl implements CommentsServive {
	private static final HollyinfoLog LOG = HollyinfoLogger.getLog(CommentsServiceImpl.class);
	private CommentsDao commentDao;
	public CommentsDao getCommentDao() {
		return commentDao;
	}
	public void setCommentDao(CommentsDao commentDao) {
		this.commentDao = commentDao;
	}
	public void deleteCommentById(int id,int uid) {
		try {
			commentDao.deleteCommentById(id,uid);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
		
	}
	
	public void deleteReply(int id,int uid) {
		try {
			commentDao.deleteReply(id,uid);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
		
	}
	
	public void saveReply(int cid,int uid,String rc,String rt) {
		try {
			commentDao.saveReply(cid,uid,rc,rt);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
		
	}
	
	public void saveComment(int rid, int uid, String cc, String ct, String st,int commentLevel) {
		try {
			commentDao.saveComment(rid, uid, cc, ct, st,commentLevel);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
		
	}
	
	public List findCommentById(int relId, String commentType, int pageNum,
			int pageCount,int commentLevel) {
		try {
		return commentDao.findCommentById(relId, commentType, pageNum, pageCount,commentLevel);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
	
	public List findReplyByCommentId(List c) {
		try {
		return commentDao.findReplyByCommentId(c);
		} catch (DataAccessException e) {
			LOG.debug("错误信息", e);
			throw new BusinessException("出现数据库连接错误，请与系统管理员联系");
		}
	}
}
