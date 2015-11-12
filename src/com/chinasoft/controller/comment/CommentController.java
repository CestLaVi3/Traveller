package com.chinasoft.controller.comment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chinasoft.common.utils.Exception.BusinessException;
import com.chinasoft.controller.BaseJsonController;
import com.chinasoft.model.general.BusComment;
import com.chinasoft.model.strategy.BusTravelDesc;
import com.chinasoft.model.user.SysUser;
import com.chinasoft.service.comments.CommentsServive;

@Controller
public class CommentController extends BaseJsonController {
	private CommentsServive commentServive;

	public CommentsServive getCommentServive() {
		return commentServive;
	}

	public void setCommentServive(CommentsServive commentServive) {
		this.commentServive = commentServive;
	}

	// 删除评论
	@RequestMapping("/commentController/deleteCommentById.do")
	public String deleteCommentById(HttpServletRequest request,
			HttpServletResponse response) {
		SysUser u = (SysUser) request.getSession().getAttribute("user");
		JSONObject req = getJsonObjectReq(request);
		
			try {
				commentServive.deleteCommentById(req.getInt("commentId"),
						u.getUserId());
				String com = "删除成功";
				sendString(com, response);
			} catch (BusinessException e) {
				sendForError(e, response);
				return null;
			}
		
		return null;
	}

	// 删除回复
	@RequestMapping("/commentController/deleteReply.do")
	public String deleteReply(HttpServletRequest request,
			HttpServletResponse response) {
		SysUser u = (SysUser) request.getSession().getAttribute("user");
		JSONObject req = getJsonObjectReq(request);
	
			try {
				commentServive
						.deleteReply(req.getInt("replyId"), u.getUserId());
				String com = "删除成功";
				sendString(com, response);
			} catch (BusinessException e) {
				sendForError(e, response);
				return null;
			}
		
		return null;
	}

	// 保存回复
	@RequestMapping("/commentController/saveReply.do")
	public String saveReply(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
		SysUser u = (SysUser) request.getSession().getAttribute("user");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
			try {
				commentServive.saveReply(req.getInt("commentId"),
						u.getUserId(), req.getString("replyContent"),
						df.format(new Date()));
				String com = "回复成功";
				sendString(com, response);
			} catch (BusinessException e) {
				sendForError(e, response);
				return null;
			}
		
		return null;
	}

	// 保存评论
	@RequestMapping("/commentController/saveComment.do")
	public String saveComment(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
		SysUser u = (SysUser) request.getSession().getAttribute("user");
		
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			int commentLevel = 3;
			if (req.has("commentLevel")) {
				commentLevel = req.getInt("commentLevel");
			}
			;
			try {
				commentServive.saveComment(req.getInt("relId"), u.getUserId(),
						req.getString("commentContent"),
						req.getString("commentType"), df.format(new Date()),
						commentLevel);
				String com = "保存成功";
				sendString(com, response);
			} catch (BusinessException e) {
				sendForError(e, response);
				return null;
			}
		
		return null;
	}

	// 根据相关id查询评论
	@RequestMapping("/commentController/findCommentById.do")
	public String findCommentById(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
		JSONObject jo = new JSONObject();
		int commentLevel = 99;
		if (req.has("commentLevel")) {
			commentLevel = req.getInt("commentLevel");
		}
		;
		try {
			List comList = commentServive.findCommentById(req.getInt("relId"),
					req.getString("commentType"), req.getInt("pageNum"),
					req.getInt("pageCount"), commentLevel);
			List replyList = null;
			BusComment busComment = new BusComment();
			if (comList.size() > 0) {
				replyList = commentServive.findReplyByCommentId(comList);
				busComment = (BusComment) comList.get(0);
			}

			jo.put("state", "1");
			jo.put("count", busComment.getCount());
			jo.put("comment",
					JSONArray.fromObject(comList, getDefaultJsonConfig()));
			jo.put("reply",
					JSONArray.fromObject(replyList, getDefaultJsonConfig()));
			sendForStr(response, jo);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
		return null;

	}
}
