package com.chinasoft.controller.activity;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import sun.misc.Perf.GetPerfAction;

import com.chinasoft.common.utils.Exception.BusinessException;
import com.chinasoft.controller.BaseJsonController;
import com.chinasoft.model.activity.BusActive;
import com.chinasoft.model.activity.BusActiveJoin;
import com.chinasoft.model.general.BusCollect;
import com.chinasoft.model.general.BusPhase;
import com.chinasoft.model.strategy.BusTravelDesc;
import com.chinasoft.model.user.SysUser;
import com.chinasoft.service.activity.ActivityService;
import com.chinasoft.service.travelDesc.TravelDescService;
import com.chinasoft.service.user.UserService;

@Controller
public class ActiveController extends BaseJsonController{
	private ActivityService activityService;
	private TravelDescService travelDescService;
	public TravelDescService getTravelDescService() {
		return travelDescService;
	}
	public void setTravelDescService(TravelDescService travelDescService) {
		this.travelDescService = travelDescService;
	}
	public ActivityService getActivityService() {
		return activityService;
	}
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}
	
	
	
	//查询活动类型
	@RequestMapping("/activeController/findActiveType.do")
	public String findActiveType(HttpServletRequest request,
			HttpServletResponse response) {		
		try {
			List activityType=activityService.findActiveType();			
			sendForList(activityType, response, null);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}		
		return null;
		
	}
	
	
	//根据状态查询活动
	@RequestMapping("/activeController/findActiveByStatus.do")
	public String findActiveByStatus(HttpServletRequest request,
			HttpServletResponse response) {		
		JSONObject req = getJsonObjectReq(request);
		try {
			List activity=activityService.findActiveByStatus(req.getString("status"));
			sendForList(activity, response, null);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}		
		return null;
		
	}
	//根据活动id查询活动
	@RequestMapping("/activeController/findActiveByType.do")
	public String findActiveByType(HttpServletRequest request,
			HttpServletResponse response) {		
		JSONObject req = getJsonObjectReq(request);
		try {
			List activity1=activityService.findActiveByType(req.getInt("typeId"));
			sendForList(activity1, response, null);			
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}		
		return null;
		
	}
	
	
	@RequestMapping("/activeController/findPhotoActive.do")
	public String findPhotoActive(HttpServletRequest request,
			HttpServletResponse response) {		
		JSONObject req = getJsonObjectReq(request);
		try {
			List active=activityService.findPhotoActive(req.getString("count"));
			sendForList(active, response, null);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}		
		return null;
		
	}
	
	@RequestMapping("/activeController/findActiveById.do")
	public String findActiveById(HttpServletRequest request,
			HttpServletResponse response) {		
		JSONObject req = getJsonObjectReq(request);		
		try {
			BusActive active=activityService.findActiveById(req.getInt("activeId"));
			sendForObject(active, response, null);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}		
		return null;
		
	}	
	
	@RequestMapping("/activeController/praiseActiveJoin.do")
	public String praiseActiveJoin(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
		SysUser u = (SysUser) request.getSession().getAttribute("user");
		BusPhase busPhase = new BusPhase();
		try {
			busPhase = travelDescService.findBusPhaseByUserId(u.getUserId(),
					req.getInt("activeJoinId"),"4");
		} catch (Exception e) {
			return null;
		}
		if (busPhase == null) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				
				activityService.praiseActiveJoin(req.getInt("activeJoinId"),
						u.getUserId(), 0,
						df.format(new Date()));
				String r = "点赞成功";
				sendString(r, response);
			} catch (BusinessException e) {
				sendForError(e, response);
				return null;
			}
		}
		else{
			String r1 = "已经点赞";
			sendErrorString(r1, response);
		}
		return null;

	}

	@RequestMapping("/activeController/collectActive.do")
	public String collectActive(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
		SysUser u = (SysUser) request.getSession().getAttribute("user");
		
		BusCollect busCollect = new BusCollect();
		try {
			busCollect = travelDescService.findBusCollectByUserId(
					u.getUserId(), req.getInt("activeId"),"4");
		} catch (Exception e) {
			return null;
		}
		if (busCollect == null) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				activityService.collectActive(req.getInt("activeId"),
						u.getUserId(), 0,
						df.format(new Date()));
				String r = "关注成功";
				sendString(r, response);
			} catch (BusinessException e) {
				sendForError(e, response);
				return null;
			}
		}
		else{
			String r1 = "已经关注";
			sendErrorString(r1, response);
		}
		
		return null;

	}
	
	
	
	@RequestMapping("/activeController/saveActiveJoin.do")
	public String saveActiveJoin(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
		String path = request.getSession().getServletContext().getRealPath("/")+"/upload/";		
		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  						
		List fileTypes = new ArrayList();  
        fileTypes.add("jpg");  
        fileTypes.add("jpeg");  
        fileTypes.add("bmp");  
        fileTypes.add("gif"); 
        String path1="activity";
        String path2="images";
        
        SysUser u = (SysUser) request.getSession().getAttribute("user");
      
        	int k=0;
        	try {
        		BusActive busActive=activityService.findActiveById(Integer.parseInt(multipartRequest.getParameter("activeId")));
        		if(busActive.getStatus().equals("1")){
    			List aList=activityService.findActiveJoinByCount(1000,Integer.parseInt(multipartRequest.getParameter("activeId")));
    			for(int i=0;i<aList.size();i++){
    				BusActiveJoin busActiveJoin=(BusActiveJoin)aList.get(i);
    				if(busActiveJoin.getUserId()==u.getUserId()){
    					k=1;
    					break;
    				}
    			}
        		}
        		else{
        			k=1;
        		}
        	
        	} catch (BusinessException e) {
    			sendForError(e, response);
    			return null;
    		}
        	
        	
        	
        if(k==0){	
        List urlList = new ArrayList();		
        if(multipartRequest.getParameterMap().size()<5){
        	MultipartFile imgFile1  =  multipartRequest.getFile("url");
        	String imgurl1 = null;
        	if(!(imgFile1.getOriginalFilename() ==null || "".equals(imgFile1.getOriginalFilename()))) {  
	        	File file1 = this.getFile(imgFile1,path1,path2,fileTypes,path);          	              
	        	imgurl1=file1.getAbsolutePath().replace(request.getSession().getServletContext().getRealPath("/"), "");        	
	        	imgurl1=imgurl1.replace("\\", "/");
	        	urlList.add(imgurl1);
	        }        	
		}
		else{
		MultipartFile imgFile1  =  multipartRequest.getFile("url1");  
		MultipartFile imgFile2  =  multipartRequest.getFile("url2");  
		MultipartFile imgFile3  =  multipartRequest.getFile("url3");  
		 String imgurl1 = null;
	        String imgurl2 = null;
	        String imgurl3 = null;
	        if(!(imgFile1.getOriginalFilename() ==null || "".equals(imgFile1.getOriginalFilename()))) {  
	        	File file1 = this.getFile(imgFile1,path1,path2,fileTypes,path);          	              
	        	imgurl1=file1.getAbsolutePath().replace(request.getSession().getServletContext().getRealPath("/"), "");        	
	        	imgurl1=imgurl1.replace("\\", "/");
	        	 urlList.add(imgurl1);
	        }
	        if(!(imgFile2.getOriginalFilename() ==null || "".equals(imgFile1.getOriginalFilename()))) {  
	        	File file2 = this.getFile(imgFile2,path1,path2,fileTypes,path);          	              
	        	imgurl2=file2.getAbsolutePath().replace(request.getSession().getServletContext().getRealPath("/"), "");        	
	        	imgurl2=imgurl2.replace("\\", "/");
	        	 urlList.add(imgurl2);
	        }
	        if(!(imgFile3.getOriginalFilename() ==null || "".equals(imgFile1.getOriginalFilename()))) {  
	        	File file3 = this.getFile(imgFile3,path1,path2,fileTypes,path);          	              
	        	imgurl3=file3.getAbsolutePath().replace(request.getSession().getServletContext().getRealPath("/"), "");        	
	        	imgurl3=imgurl3.replace("\\", "/");
	        	 urlList.add(imgurl3);
	        }      
		}
       
		Integer activeId = Integer.parseInt(multipartRequest.getParameter("activeId"));
		String joinName = multipartRequest.getParameter("joinName");
		String joinDesc = multipartRequest.getParameter("joinDesc");
		String phoneNum=multipartRequest.getParameter("phoneNum");		
		JSONObject imagesUrl = new JSONObject();
		imagesUrl.put("images", JSONArray.fromObject(urlList));		
		BusActiveJoin busActiveJoin=new BusActiveJoin();
		busActiveJoin.setActiveId(activeId);
		busActiveJoin.setCreateDate(new Date());
		busActiveJoin.setJoinDesc(joinDesc);
		busActiveJoin.setJoinName(joinName);
		busActiveJoin.setUserId(u.getUserId());
		busActiveJoin.setPhoneNum(phoneNum);	
		busActiveJoin.setImages(imagesUrl.toString());
		try {
			activityService.saveActiveJoin(busActiveJoin);
			String scriptString = "<script> window.location.href= '../"+multipartRequest.getParameter("successUrl")+"'</script>";
			response.getWriter().write(scriptString);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
		catch (Exception e1) {
			
		}
		
        }
        else {
        	String ob="已经参加或活动关闭";
			sendErrorString(ob, response);
		}
		
        return null;

	}
	
	@RequestMapping("/activeController/collect.do")
	public String collect(HttpServletRequest request,
			HttpServletResponse response) {				
			try {
				List activeList=activityService.collect();
				sendForList(activeList, response, null);
			} catch (BusinessException e) {
				sendForError(e, response);
				return null;
			}		
		return null;
	}
	@RequestMapping("/activeController/findActiveByPageAndType.do")
	public String findActiveByPageAndType(HttpServletRequest request,
			HttpServletResponse response) {		
			JSONObject req = getJsonObjectReq(request);
			JSONObject jo=new JSONObject();
			try {
				List activeList=activityService.findActiveByPageAndType(req.getInt("pageNum"), req.getInt("pageCount"), req.getInt("activeType"));
				BusActive busActive=new BusActive();
				if(activeList.size()>0){
					busActive=(BusActive)activeList.get(0);
				}
				jo.put("state", "1");
				jo.put("count", busActive.getCount());
				jo.put("value", JSONArray.fromObject(activeList,getDefaultJsonConfig()));
				sendForStr(response, jo);
			} catch (BusinessException e) {
				sendForError(e, response);
				return null;
			}		
		return null;
	}
	
	@RequestMapping("/activeController/findTemByCollect.do")
	public String findTemByCollect(HttpServletRequest request,
			HttpServletResponse response) {		
			JSONObject req = getJsonObjectReq(request);
			try {
				List aList=activityService.findTemByCollect(req.getInt("count"), req.getInt("activeType"));
				sendForList(aList, response, null);
			} catch (BusinessException e) {
				sendForError(e, response);
				return null;
			}		
		return null;
	}
	
	@RequestMapping("/activeController/findTeamByCollect.do")
	public String findTeamByCollect(HttpServletRequest request,
			HttpServletResponse response) {		
			JSONObject req = getJsonObjectReq(request);
			JSONObject jo=new JSONObject();
			try {
				List activeList=activityService.findTeamByCollect(req.getInt("activeType"),req.getInt("pageNum"), req.getInt("pageCount"), req.getInt("spotId"), req.getString("planDate"));
				BusActive busActive=new BusActive();
				if(activeList.size()>0){
					busActive=(BusActive)activeList.get(0);
				}
				jo.put("state", "1");
				jo.put("count", busActive.getCount());
				jo.put("value", JSONArray.fromObject(activeList,getDefaultJsonConfig()));
				sendForStr(response, jo);
			} catch (BusinessException e) {
				sendForError(e, response);
				return null;
			}		
		return null;
	}
	
	@RequestMapping("/activeController/findTeamActiveById.do")
	public String findTeamActiveById(HttpServletRequest request,
			HttpServletResponse response) {		
			JSONObject req = getJsonObjectReq(request);
			SysUser u = (SysUser) request.getSession().getAttribute("user");
			
			try {
				BusActive busActive=activityService.findTeamActiveById(req.getInt("activeId"),u.getUserId());
				sendForObject(busActive, response, null);			
			} catch (BusinessException e) {
				sendForError(e, response);
				return null;
			}		
			
		return null;
	}
	@RequestMapping("/activeController/findActiveJoinById.do")
	public String findActiveJoinById(HttpServletRequest request,
			HttpServletResponse response) {		
			JSONObject req = getJsonObjectReq(request);
			SysUser u = (SysUser) request.getSession().getAttribute("user");
			
			try {
				BusActiveJoin busActiveJoin=activityService.findActiveJoinById(req.getInt("activeJoinId"));
				travelDescService.insertBrowse(u.getUserId(),req.getInt("activeJoinId"), "4", "bus_active_join", "active_join");
				sendForObject(busActiveJoin, response, null);			
			} catch (BusinessException e) {
				sendForError(e, response);
				return null;
			}		
			
		return null;
	}
	
	@RequestMapping("/activeController/findTeamActiveBySpot.do")
	public String findTeamActiveBySpot(HttpServletRequest request,
			HttpServletResponse response) {		
			JSONObject req = getJsonObjectReq(request);
			try {
				List aList=activityService.findTeamActiveBySpot(req.getInt("spotId"), req.getInt("count"), req.getInt("activeType"));		
				sendForList(aList, response, null);
			} catch (BusinessException e) {
				sendForError(e, response);
				return null;
			}		
		return null;
	}
	
	
	@RequestMapping("/activeController/saveTeamActiveJoin.do")
	public String saveTeamActiveJoin(HttpServletRequest request,
			HttpServletResponse response) {	
		JSONObject req = getJsonObjectReq(request);
		SysUser u = (SysUser) request.getSession().getAttribute("user");
		BusActiveJoin busActiveJoin=new BusActiveJoin();
		busActiveJoin.setActiveId(req.getInt("activeId"));
		busActiveJoin.setCreateDate(new Date());
		busActiveJoin.setJoinDesc(req.getString("joinDesc"));
		busActiveJoin.setUserId(u.getUserId());
		busActiveJoin.setPhoneNum(req.getString("phoneNum"));
		
		try {
			int k=0;
			List aList=activityService.findTeamByUserJoin(u.getUserId(), 1, 100, "1");
			if(aList.size()>0){
				for(int i=0;i<aList.size();i++){
					BusActive busActive=(BusActive)aList.get(i);
					if(!(busActive.getStatus().equals("1"))){
						k=1;
					}
					else
					if(busActive.getActiveId()==req.getInt("activeId")){
						k=1;
						break;
					}
				}	
			}else{
				k=0;
			}
			if(k==0){
			activityService.saveTeamActiveJoin(busActiveJoin);
			String r = "参加成功";
			sendString(r, response);
			
			}
			else {
				String ob="已经参加或活动关闭";
				sendErrorString(ob, response);
			}
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
		
		return null;

	}
	
	
	
	@RequestMapping("/activeController/saveTeamActive.do")
	public String saveTeamActive(HttpServletRequest request,
			HttpServletResponse response) {
		SysUser u = (SysUser) request.getSession().getAttribute("user");
		String path = request.getSession().getServletContext().getRealPath("/")+"/upload/";		
		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  				
		List fileTypes = new ArrayList();  
        fileTypes.add("jpg");  
        fileTypes.add("jpeg");  
        fileTypes.add("bmp");  
        fileTypes.add("gif"); 
        String path1="active";
        String path2="images";
        List urlList = new ArrayList();		     
        	MultipartFile imgFile  =  multipartRequest.getFile("imageUrl");
        	String imgurl = null;
        	if(!(imgFile.getOriginalFilename() ==null || "".equals(imgFile.getOriginalFilename()))) {  
	        	File file = this.getFile(imgFile,path1,path2,fileTypes,path);          	              
	        	imgurl=file.getAbsolutePath().replace(request.getSession().getServletContext().getRealPath("/"), "");        	
	        	imgurl=imgurl.replace("\\", "/");
	        	urlList.add(imgurl);
        	}
        					
		JSONObject imagesUrl = new JSONObject();
		imagesUrl.put("images", JSONArray.fromObject(urlList));
		BusActive busActive=new BusActive();
		busActive.setActiveName(multipartRequest.getParameter("activeName"));
		busActive.setProductDesc(multipartRequest.getParameter("productDesc"));
		busActive.setActiveType(Integer.parseInt(multipartRequest.getParameter("activeType")));
		busActive.setQqNum(multipartRequest.getParameter("qqNum"));
		busActive.setActualName(multipartRequest.getParameter("actualName"));
		busActive.setPhoneNum(multipartRequest.getParameter("phoneNum"));
		busActive.setWeixinNum(multipartRequest.getParameter("weixinNum"));
		busActive.setPersonNum(multipartRequest.getParameter("personNum"));
		busActive.setCreateTime(new Date());
		try {
			busActive.setPlanDate(new SimpleDateFormat("yyyy-MM-dd").parse(multipartRequest.getParameter("planDate")));
		} catch (ParseException e1) {
			
			e1.printStackTrace();
		}
		try {
			busActive.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(multipartRequest.getParameter("planDate")));
		} catch (ParseException e1) {
		
			e1.printStackTrace();
		}
		try {
			Date d1=new SimpleDateFormat("yyyy-MM-dd").parse(multipartRequest.getParameter("planDate"));
			Date date = new Date(d1.getTime() + Integer.parseInt(multipartRequest.getParameter("travelDays")) * 24 * 60 * 60 * 1000);	
			busActive.setEndDate(date);
		} catch (ParseException e1) {
	
			e1.printStackTrace();
		}
		busActive.setTravelDays(Integer.parseInt(multipartRequest.getParameter("travelDays")));
		busActive.setImages(imagesUrl.toString());
		busActive.setSpotId(Integer.parseInt(multipartRequest.getParameter("spotId")));
		List descList=new ArrayList();
		for(int i=1;i<=Integer.parseInt(multipartRequest.getParameter("travelDays"));i++){
			JSONObject jo=new JSONObject();
			MultipartFile imgFile1 =  multipartRequest.getFile("url"+i);
        	String imgurl1 = null;
        	if(!(imgFile1.getOriginalFilename() ==null || "".equals(imgFile1.getOriginalFilename()))) {  
	        	File file1 = this.getFile(imgFile1,path1,path2,fileTypes,path);          	              
	        	imgurl1=file1.getAbsolutePath().replace(request.getSession().getServletContext().getRealPath("/"), "");        	
	        	imgurl1=imgurl1.replace("\\", "/");
        	}
        	
        	jo.put("imageUrl", imgurl1);
        	jo.put("desc", multipartRequest.getParameter("desc"+i));
        	jo.put("liveDesc",multipartRequest.getParameter("liveDesc"+i));
        	jo.put("footDesc",multipartRequest.getParameter("footDesc"+i));
        	descList.add(jo);
		}
		busActive.setActiveDesc(descList.toString());
		try {
			if(u!=null){
			busActive.setUserId(u.getUserId());	
			activityService.saveTeamActive(busActive);
			}else {
			activityService.saveWebTeamActive(busActive);	
			}
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
	
	
	@RequestMapping("/activeController/findTeamByUserJoin.do")
	public String findTeamByUserJoin(HttpServletRequest request,
			HttpServletResponse response) {	
		JSONObject req = getJsonObjectReq(request);
		SysUser u = (SysUser) request.getSession().getAttribute("user");		
		JSONObject jo=new JSONObject();
		
		try {
			List aList=activityService.findTeamByUserJoin(req.getInt("userId"), req.getInt("pageNum"), req.getInt("pageCount"), req.getString("status"));
			BusActive busActive=new BusActive();
			if(aList.size()>0){
				busActive=(BusActive)aList.get(0);
			}
			jo.put("state", "1");
			jo.put("count", busActive.getCount());
			jo.put("value", JSONArray.fromObject(aList,getDefaultJsonConfig()));
			sendForStr(response, jo);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
		
		return null;

	}
	
	
	@RequestMapping("/activeController/unJoinActive.do")
	public String unJoinActive(HttpServletRequest request,
			HttpServletResponse response) {	
		JSONObject req = getJsonObjectReq(request);
		SysUser u = (SysUser) request.getSession().getAttribute("user");		
		
		try {
			activityService.unJoinActive(req.getInt("activeId"), u.getUserId());
			String ob="取消成功";
			sendString(ob, response);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
		
		return null;

	}
	
	@RequestMapping("/activeController/unCollectActive.do")
	public String unCollectActive(HttpServletRequest request,
			HttpServletResponse response) {	
		JSONObject req = getJsonObjectReq(request);
		SysUser u = (SysUser) request.getSession().getAttribute("user");		
		
		try {
			activityService.unCollectActive(req.getInt("activeId"), u.getUserId());
			String ob="取消成功";
			sendString(ob, response);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
		
		return null;

	}
	
	@RequestMapping("/activeController/findTeamByUserCreate.do")
	public String findTeamByUserCreate(HttpServletRequest request,
			HttpServletResponse response) {	
		JSONObject req = getJsonObjectReq(request);
		SysUser u = (SysUser) request.getSession().getAttribute("user");		
		JSONObject jo=new JSONObject();
		
		try {			
			List aList=activityService.findTeamByUserCreate(req.getInt("userId"), req.getInt("pageNum"), req.getInt("pageCount"), req.getString("status"));
			BusActive busActive=new BusActive();
			if(aList.size()>0){
				busActive=(BusActive)aList.get(0);
			}
			jo.put("state", "1");
			jo.put("count", busActive.getCount());
			jo.put("value", JSONArray.fromObject(aList,getDefaultJsonConfig()));
			sendForStr(response, jo);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
		
		return null;

	}
	
	@RequestMapping("/activeController/findTeamByUserCollect.do")
	public String findTeamByUserCollect(HttpServletRequest request,
			HttpServletResponse response) {	
		JSONObject req = getJsonObjectReq(request);
		SysUser u = (SysUser) request.getSession().getAttribute("user");		
		JSONObject jo=new JSONObject();
		
		try {			
			List aList=activityService.findTeamByUserCollect(req.getInt("userId"), req.getInt("pageNum"), req.getInt("pageCount"), req.getString("status"));
			BusActive busActive=new BusActive();
			if(aList.size()>0){
				busActive=(BusActive)aList.get(0);
			}
			jo.put("state", "1");
			jo.put("count", busActive.getCount());
			jo.put("value", JSONArray.fromObject(aList,getDefaultJsonConfig()));
			sendForStr(response, jo);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
		
		return null;

	}
	
	
	@RequestMapping("/activeController/closeActive.do")
	public String closeActive(HttpServletRequest request,
			HttpServletResponse response) {	
		JSONObject req = getJsonObjectReq(request);
		try {
			activityService.closeActive(req.getInt("activeId"));
			String ob="关闭成功";
			sendString(ob, response);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}	
		
		return null;

	}
	
	
	@RequestMapping("/activeController/findInformByUser.do")
	public String findInformByUser(HttpServletRequest request,
			HttpServletResponse response) {	
		JSONObject req = getJsonObjectReq(request);
		SysUser u = (SysUser) request.getSession().getAttribute("user");		
		
		try {
			List aList=activityService.findInformByUser(u.getUserId());
			sendForList(aList, response, null);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
		
		return null;

	}
	
	
	@RequestMapping("/activeController/findActiveJoinByPage.do")
	public String findActiveJoinByPage(HttpServletRequest request,
			HttpServletResponse response) {	
		JSONObject req = getJsonObjectReq(request);		
		JSONObject jo=new JSONObject();
		try {
			List aList=activityService.findActiveJoinByPage(req.getInt("pageNum"), req.getInt("pageCount"), req.getInt("activeId"));
			BusActiveJoin busActiveJoin=new BusActiveJoin();
			if(aList.size()>0){
				busActiveJoin=(BusActiveJoin)aList.get(0);
			}
			jo.put("state", "1");
			jo.put("count", busActiveJoin.getCount());
			jo.put("value", JSONArray.fromObject(aList,getDefaultJsonConfig()));
			sendForStr(response, jo);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}		
		return null;

	}
	
	@RequestMapping("/activeController/findAllActiveByType.do")
	public String findAllActiveByType(HttpServletRequest request,
			HttpServletResponse response) {	
		JSONObject req = getJsonObjectReq(request);	
		JSONObject jo=new JSONObject();
		try {
			List aList=activityService.findAllActiveByType(req.getInt("activeType"),req.getInt("pageNum"),req.getInt("pageCount"));
			BusActive busActiveJoin=new BusActive();
			if(aList.size()>0){
				busActiveJoin=(BusActive)aList.get(0);
			}
			jo.put("state", "1");
			jo.put("count", busActiveJoin.getCount());
			jo.put("value", JSONArray.fromObject(aList,getDefaultJsonConfig()));
			sendForStr(response, jo);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}		
		return null;

	}
	
	
	@RequestMapping("/activeController/findActiveJoinByCount.do")
	public String findActiveJoinByCount(HttpServletRequest request,
			HttpServletResponse response) {	
		JSONObject req = getJsonObjectReq(request);		
		try {
			List aList=activityService.findActiveJoinByCount(req.getInt("count"), req.getInt("activeId"));
			sendForList(aList, response, null);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}		
		return null;

	}
	
	
	@RequestMapping("/activeController/openActive.do")
	public String openActive(HttpServletRequest request,
			HttpServletResponse response) {	
		JSONObject req = getJsonObjectReq(request);		
		BusActive busActive=new BusActive();
		List aList=new ArrayList();
		try {
			busActive=activityService.findActiveById(req.getInt("activeId"));
			aList=activityService.findActiveByStatusAndType("1", busActive.getActiveType());
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}	
		if(aList.size()==0){
		if(busActive.getStatus().equals("0")){		
		try {
			activityService.openActive(req.getInt("activeId"));
			sendString("开启成功", response);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}	
		}
		else{
			sendErrorString("该活动不可开启", response);
		}
		}
		else {
			sendErrorString("该类型还有活动正在进行", response);
		}
		return null;

	}
	
	
	
	
	@RequestMapping("/activeController/saveActive.do")
	public String saveActive(HttpServletRequest request,
			HttpServletResponse response) {
		String path = request.getSession().getServletContext().getRealPath("/")+"/upload/";
		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  				
		List fileTypes = new ArrayList();  
        fileTypes.add("jpg");  
        fileTypes.add("jpeg");  
        fileTypes.add("bmp");  
        fileTypes.add("gif"); 
        String path1="active";
        String path2="images";
        List urlList = new ArrayList();	
        List urlList1 = new ArrayList();
        MultipartFile imgFile  =  multipartRequest.getFile("imageUrl");
        MultipartFile imgFile1  =  multipartRequest.getFile("prizeImage1");
        MultipartFile imgFile2  =  multipartRequest.getFile("prizeImage2");
        MultipartFile imgFile3  =  multipartRequest.getFile("prizeImage3");
        String imgurl = null;
        String imgurl1 = null;
        String imgurl2 = null;
        String imgurl3 = null;
        if(!(imgFile.getOriginalFilename() ==null || "".equals(imgFile.getOriginalFilename()))) {  
	        File file = this.getFile(imgFile,path1,path2,fileTypes,path);          	              
	        imgurl=file.getAbsolutePath().replace(request.getSession().getServletContext().getRealPath("/"), "");        	
	        imgurl=imgurl.replace("\\", "/");
	        urlList.add(imgurl);
        } 
        if(!(imgFile1.getOriginalFilename() ==null || "".equals(imgFile1.getOriginalFilename()))) {  
	        File file1 = this.getFile(imgFile1,path1,path2,fileTypes,path);          	              
	        imgurl1=file1.getAbsolutePath().replace(request.getSession().getServletContext().getRealPath("/"), "");        	
	        imgurl1=imgurl1.replace("\\", "/");
	        urlList1.add(imgurl1);
        }
        if(!(imgFile2.getOriginalFilename() ==null || "".equals(imgFile2.getOriginalFilename()))) {  
	        File file2 = this.getFile(imgFile2,path1,path2,fileTypes,path);          	              
	        imgurl2=file2.getAbsolutePath().replace(request.getSession().getServletContext().getRealPath("/"), "");        	
	        imgurl2=imgurl2.replace("\\", "/");
	        urlList1.add(imgurl2);
        }
        if(!(imgFile3.getOriginalFilename() ==null || "".equals(imgFile3.getOriginalFilename()))) {  
	        File file3 = this.getFile(imgFile3,path1,path2,fileTypes,path);          	              
	        imgurl3=file3.getAbsolutePath().replace(request.getSession().getServletContext().getRealPath("/"), "");        	
	        imgurl3=imgurl3.replace("\\", "/");
	        urlList1.add(imgurl3);
        }
		JSONObject imagesUrl = new JSONObject();
		imagesUrl.put("images", JSONArray.fromObject(urlList));
		JSONObject imagesUrl1 = new JSONObject();
		imagesUrl.put("images", JSONArray.fromObject(urlList1));
		BusActive busActive=new BusActive();
		busActive.setActiveName(multipartRequest.getParameter("activeName"));
		busActive.setProductDesc(multipartRequest.getParameter("productDesc"));
		busActive.setActiveType(Integer.parseInt(multipartRequest.getParameter("activeType")));
		busActive.setCreateTime(new Date());
		busActive.setPrizeImage(imagesUrl1.toString());
		busActive.setVoteDesc(multipartRequest.getParameter("voteDesc"));
		String prizeTile1=multipartRequest.getParameter("prizeTile1");
		String prizeTile2=multipartRequest.getParameter("prizeTile2");
		String prizeTile3=multipartRequest.getParameter("prizeTile3");
		List pList=new ArrayList();
		pList.add(prizeTile1);
		pList.add(prizeTile2);
		pList.add(prizeTile3);
		JSONObject jo=new JSONObject();
		jo.put("title", pList);
		busActive.setPrizeTile(jo.toString());
		try {
			busActive.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(multipartRequest.getParameter("startDate")));
		} catch (ParseException e1) {
		
			e1.printStackTrace();
		}
		try {
			busActive.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(multipartRequest.getParameter("endDate")));
		} catch (ParseException e1) {
	
			e1.printStackTrace();
		}		
		try {
			activityService.saveWebActive(busActive);
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
}
