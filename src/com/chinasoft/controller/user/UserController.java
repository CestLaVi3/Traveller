package com.chinasoft.controller.user;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.chinasoft.common.utils.Exception.BusinessException;
import com.chinasoft.controller.BaseJsonController;
import com.chinasoft.model.general.BusCollect;
import com.chinasoft.model.user.SysUser;
import com.chinasoft.service.travelDesc.TravelDescService;
import com.chinasoft.service.user.UserService;

@Controller
public class UserController extends BaseJsonController {
	private UserService userService;
	private TravelDescService travelDescService;

	public TravelDescService getTravelDescService() {
		return travelDescService;
	}

	public void setTravelDescService(TravelDescService travelDescService) {
		this.travelDescService = travelDescService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/userController/findCurUserCollect.do")
	public String findCurUserCollect(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
		JSONObject jo = new JSONObject();
		SysUser u = (SysUser) request.getSession().getAttribute("user");
		
			try {
				List uList = userService.findCurUserCollect(
						req.getInt("userId"), req.getInt("pageNum"),
						req.getInt("pageCount"));
				SysUser user = new SysUser();
				if (uList.size() > 0) {
					user = (SysUser) uList.get(0);
				}
				jo.put("state", "1");
				jo.put("count", user.getCount());
				jo.put("value",
						JSONArray.fromObject(uList, getDefaultJsonConfig()));
				sendForStr(response, jo);
			} catch (BusinessException e) {
				sendForError(e, response);
				return null;
			}
		
		return null;
	}

	@RequestMapping("/userController/unCollectUser.do")
	public String unCollectUser(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
		SysUser u = (SysUser) request.getSession().getAttribute("user");
		if (u == null) {
			String ob = "没有登录";
			sendErrorString(ob, response);
		} else {
			try {
				userService.unCollectUser(req.getInt("userId"), u.getUserId());
				sendString("取消成功", response);
			} catch (BusinessException e) {
				sendForError(e, response);
				return null;
			}
		}
		return null;
	}

	@RequestMapping("/userController/findByCondition.do")
	public String findByCondition(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
		JSONObject jo = new JSONObject();
		SysUser u = (SysUser) request.getSession().getAttribute("user");
		
			try {
				List uList = userService.findByCondition(req.getString("sex"),
						req.getString("petName"),
						req.getString("freezeStatus"), req.getInt("pageNum"),
						req.getInt("pageCount"));
				SysUser user = new SysUser();
				if (uList.size() > 0) {
					user = (SysUser) uList.get(0);
				}
				jo.put("state", "1");
				jo.put("count", user.getCount());
				jo.put("value",
						JSONArray.fromObject(uList, getDefaultJsonConfig()));
				sendForStr(response, jo);

			} catch (BusinessException e) {
				sendForError(e, response);
				return null;
			}
		
		return null;
	}

	@RequestMapping("/userController/collectUser.do")
	public String collectUser(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
		SysUser u = (SysUser) request.getSession().getAttribute("user");
			BusCollect busCollect = new BusCollect();
			try {
				busCollect = travelDescService.findBusCollectByUserId(
						u.getUserId(), req.getInt("userId"), "5");
			} catch (BusinessException e) {
				sendForError(e, response);
				return null;
			}
			if (busCollect == null) {
				try {
					userService
							.collectUser(req.getInt("userId"), u.getUserId());
					sendString("关注成功", response);
				} catch (BusinessException e) {
					sendForError(e, response);
					return null;
				}
			} else {
				sendErrorString("已经关注", response);
			}
		
		return null;
	}

	@RequestMapping("/userController/findCurUserFuns.do")
	public String findCurUserFuns(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
		JSONObject jo = new JSONObject();
		SysUser u = (SysUser) request.getSession().getAttribute("user");
		
			try {
				List uList = userService.findCurUserFuns(req.getInt("userId"),
						req.getInt("pageNum"), req.getInt("pageCount"));
				SysUser user = new SysUser();
				if (uList.size() > 0) {
					user = (SysUser) uList.get(0);
				}
				jo.put("state", "1");
				jo.put("count", user.getCount());
				jo.put("value",
						JSONArray.fromObject(uList, getDefaultJsonConfig()));
				sendForStr(response, jo);

			} catch (BusinessException e) {
				sendForError(e, response);
				return null;
			}
		
		return null;
	}

	@RequestMapping("/userController/findCurUserBrowse.do")
	public String findCurUserBrowse(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
		JSONObject jo = new JSONObject();
		SysUser u = (SysUser) request.getSession().getAttribute("user");
		
			try {
				List uList = userService.findCurUserBrowse(
						req.getInt("userId"), req.getInt("pageNum"),
						req.getInt("pageCount"));
				SysUser user = new SysUser();
				if (uList.size() > 0) {
					user = (SysUser) uList.get(0);
				}
				jo.put("state", "1");
				jo.put("count", user.getCount());
				jo.put("value",
						JSONArray.fromObject(uList, getDefaultJsonConfig()));
				sendForStr(response, jo);
			} catch (BusinessException e) {
				sendForError(e, response);
				return null;
			}
		
		return null;
	}

	@RequestMapping("/userController/findUserByUserId.do")
	public String findUserByUserId(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
		SysUser u = (SysUser) request.getSession().getAttribute("user");
		
			try {
				SysUser user = userService.findUserByUserId(req
						.getInt("userId"));
				userService.updateVistNum(u.getUserId(), req.getInt("userId"));	
				sendForObject(user, response, null);
			} catch (BusinessException e) {
				sendForError(e, response);
				return null;
			}
		
		return null;
	}

	@RequestMapping("/userController/updatePassword.do")
	public String updatePassword(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
		SysUser u = (SysUser) request.getSession().getAttribute("user");
		
			try {
				userService.updatePassword(req.getInt("userId"),
						req.getString("password"));
				sendString("修改成功", response);
			} catch (BusinessException e) {
				sendForError(e, response);
				return null;
			}
		
		return null;
	}

	@RequestMapping("/userController/updateBaseInfor.do")
	public String updateBaseInfor(HttpServletRequest request,
			HttpServletResponse response) {
		String path = request.getSession().getServletContext().getRealPath("/")
				+ "/upload/";
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile imgFile1 = multipartRequest.getFile("imageUrl");
		List fileTypes = new ArrayList();
		fileTypes.add("jpg");
		fileTypes.add("jpeg");
		fileTypes.add("bmp");
		fileTypes.add("gif");
		String path1 = "user";
		String path2 = "images";
		String imgurl1 = null;
		SysUser user = new SysUser();
		try {
			user = userService.findUserByUserId(Integer
					.parseInt(multipartRequest.getParameter("userId")));
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
		if (!(imgFile1.getOriginalFilename() == null || "".equals(imgFile1
				.getOriginalFilename()))) {
			File file1 = this.getFile(imgFile1, path1, path2, fileTypes, path);
			imgurl1 = file1.getAbsolutePath().replace(
					request.getSession().getServletContext().getRealPath("/"),
					"");
			imgurl1 = imgurl1.replace("\\", "/");
		} else {
			imgurl1 = user.getImageUrl();
		}
		int userId = Integer.parseInt(multipartRequest.getParameter("userId"));
		String signAture = multipartRequest.getParameter("signature");
		String petName = multipartRequest.getParameter("petName");
		String sex = multipartRequest.getParameter("sex");
		String location = multipartRequest.getParameter("location");
		String qqNum = multipartRequest.getParameter("qqNum");
		String email = multipartRequest.getParameter("email");
		SysUser u = (SysUser) request.getSession().getAttribute("user");
		SysUser user2 = new SysUser();
		user2.setUserId(userId);
		user2.setImageUrl(imgurl1);
		user2.setSignature(signAture);
		user2.setPetName(petName);
		user2.setSex(sex);
		user2.setLocation(location);
		user2.setQqNum(qqNum);
		user.setEmail(email);
		
			try {
				userService.updateBaseInfor(user2);
				String scriptString = "<script> window.location.href= '../"+multipartRequest.getParameter("successUrl")+"'</script>";
				response.getWriter().write(scriptString);
			} catch (BusinessException e) {
				sendForError(e, response);
				return null;
			}
			catch (Exception e1) {
				
			}
		
		return null;

	}

	@RequestMapping("/userController/updateAuthInfor.do")
	public String updateAuthInfor(HttpServletRequest request,
			HttpServletResponse response) {
		String path = request.getSession().getServletContext().getRealPath("/")
				+ "/upload/";
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile imgFile1 = multipartRequest.getFile("positive");
		MultipartFile imgFile2 = multipartRequest.getFile("opposite");
		List fileTypes = new ArrayList();
		fileTypes.add("jpg");
		fileTypes.add("jpeg");
		fileTypes.add("bmp");
		fileTypes.add("gif");
		String path1 = "user";
		String path2 = "images";
		String imgurl1 = null;
		String imgurl2 = null;
		SysUser user = new SysUser();
		try {
			user = userService.findUserByUserId(Integer
					.parseInt(multipartRequest.getParameter("userId")));
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
		JSONObject jo=new JSONObject();
		if(user.getIdentityCardImage()!=null){
			jo = JSONObject.fromObject(user.getIdentityCardImage());
		}
		else{
			jo.put("positive", "");
			jo.put("opposite", "");
		}
		if (!(imgFile1.getOriginalFilename() == null || "".equals(imgFile1
				.getOriginalFilename()))) {
			File file1 = this.getFile(imgFile1, path1, path2, fileTypes, path);
			imgurl1 = file1.getAbsolutePath().replace(
					request.getSession().getServletContext().getRealPath("/"),
					"");
			imgurl1 = imgurl1.replace("\\", "/");
		} else {
			imgurl1 = jo.getString("positive");
		}
		if (!(imgFile2.getOriginalFilename() == null || "".equals(imgFile2
				.getOriginalFilename()))) {
			File file2 = this.getFile(imgFile2, path1, path2, fileTypes, path);
			imgurl2 = file2.getAbsolutePath().replace(
					request.getSession().getServletContext().getRealPath("/"),
					"");
			imgurl2 = imgurl2.replace("\\", "/");
		} else {
			imgurl2 = jo.getString("opposite");
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("positive", imgurl1);
		jsonObject.put("opposite", imgurl2);
		int userId = Integer.parseInt(multipartRequest.getParameter("userId"));
		String userName = multipartRequest.getParameter("userName");
		String address = multipartRequest.getParameter("address");
		String phoneNum = multipartRequest.getParameter("phoneNum");
		String identitycard = multipartRequest.getParameter("identitycard");
		String authenticated = "1";
		SysUser u = (SysUser) request.getSession().getAttribute("user");
		SysUser user2 = new SysUser();
		user2.setUserId(userId);
		user2.setUserName(userName);
		user2.setAddress(address);
		user2.setPhoneNum(phoneNum);
		user2.setIdentitycard(identitycard);
		user2.setAuthenticated(authenticated);
		user2.setIdentityCardImage(jsonObject.toString());
		
			try {
				userService.updateAuthInfor(user2);
				String scriptString = "<script> window.location.href= '../"+multipartRequest.getParameter("successUrl")+"'</script>";
				response.getWriter().write(scriptString);
			} catch (BusinessException e) {
				sendForError(e, response);
				return null;
			} catch (Exception e1) {
				
			}
		
		return null;

	}
	
	
	@RequestMapping("/userController/checkUser.do")
	public String checkUser(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
			try {
				userService.checkUser(req.getInt("userId"),req.getString("authenticated"));
				sendString("审核成功", response);
			} catch (BusinessException e) {
				sendForError(e, response);
				return null;
			}
		
		return null;
	}
	
	
	
	@RequestMapping("/userController/freezeUser.do")
	public String freezeUser(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
			try {
				userService.freezeUser(req.getInt("userId"),req.getString("freezeStatus"));
				sendString("冻结成功", response);
			} catch (BusinessException e) {
				sendForError(e, response);
				return null;
			}
		
		return null;
	
	}
	
	@RequestMapping("/userController/findAllUser.do")
	public String findAllUser(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);		
			try {
				List uList=userService.findAllUser(req.getInt("pageNum"), req.getInt("pageCount"));
				sendForList(uList, response, null);
			} catch (BusinessException e) {
				sendForError(e, response);
				return null;
			}
		
		return null;
	}
	
	@RequestMapping("/userController/findUserByName.do")
	public String findUserByName(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
		JSONObject jo=new JSONObject();
			try {
				List uList=userService.findUserByName(req.getString("petName"),req.getInt("pageNum"), req.getInt("pageCount"));
				SysUser user=new SysUser();
				if(uList.size()>0){
					user=(SysUser)uList.get(0);
				}
				jo.put("state", "1");
				jo.put("count", user.getCount());
				jo.put("value", JSONArray.fromObject(uList, getDefaultJsonConfig()));
				sendForStr(response, jo);
			} catch (BusinessException e) {
				sendForError(e, response);
				return null;
			}
		return null;
	}
}
