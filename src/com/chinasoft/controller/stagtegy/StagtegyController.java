package com.chinasoft.controller.stagtegy;

import java.io.File;
import java.io.IOException;
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

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import sun.security.jca.GetInstance;

import com.chinasoft.common.utils.Exception.BusinessException;
import com.chinasoft.controller.BaseJsonController;
import com.chinasoft.model.activity.BusActiveJoin;
import com.chinasoft.model.general.BusCollect;
import com.chinasoft.model.general.BusPhase;
import com.chinasoft.model.spot.SysSpotType;
import com.chinasoft.model.strategy.BusStagtegy;
import com.chinasoft.model.strategy.BusTravelDesc;
import com.chinasoft.model.strategy.SysStagtegyType;
import com.chinasoft.model.user.SysUser;
import com.chinasoft.service.stagtegy.StagtegyService;
import com.chinasoft.service.travelDesc.TravelDescService;

@Controller
public class StagtegyController extends BaseJsonController {
	private TravelDescService travelDescService;

	public TravelDescService getTravelDescService() {
		return travelDescService;
	}

	public void setTravelDescService(TravelDescService travelDescService) {
		this.travelDescService = travelDescService;
	}

	private StagtegyService stagtegyService;

	public StagtegyService getStagtegyService() {
		return stagtegyService;
	}

	public void setStagtegyService(StagtegyService stagtegyService) {
		this.stagtegyService = stagtegyService;
	}

	@RequestMapping("/stagtegyController/findStagtegy.do")
	public String findStagtegy(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			List stagtegy = stagtegyService.findStagtrgy(1,9);
			sendForList(stagtegy, response, null);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
		return null;

	}
	@RequestMapping("/stagtegyController/findStagtegyType.do")
	public String findStagtegyType(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
		JSONObject jo=new JSONObject();
		try {
			List stagtegy = stagtegyService.findStagtrgy(req.getInt("pageNum"), req.getInt("pageCount"));
			SysStagtegyType type=new SysStagtegyType();
			if(stagtegy.size()>0){
				type=(SysStagtegyType)stagtegy.get(0);
			}
			jo.put("state", "1");
			jo.put("count", type.getCount());
			jo.put("value", JSONArray.fromObject(stagtegy,getDefaultJsonConfig()));
			sendForStr(response, jo);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
		return null;

	}
	@RequestMapping("/stagtegyController/findDescByCondition.do")
	public String findDescByCondition(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
		try {
			List stagtegy1 = stagtegyService.findDescByCondition(req
					.getInt("typeId"));
			sendForList(stagtegy1, response, null);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
		return null;

	}

	@RequestMapping("/stagtegyController/findByMonth.do")
	public String findByMonth(HttpServletRequest request,
			HttpServletResponse response) {
		Date date = new Date();
		int year = date.getYear()+1900;
		int month = date.getMonth();
		JSONObject jo = new JSONObject();
		JSONObject js = new JSONObject();
		int month1=month+1;
		int month2=month+2;
		int month3=month+3;
		int year1=year-1;
		int year2=year-1;
		int year3=year-1;
		if(month==10){
			month3=1;
			year3++;
		}
		if(month==11){
			month2=1;
			month3=2;
			year2++;
			year3++;
		}
		try {	
			List stagtegy1 = stagtegyService.findByMonth(year1, month1);
			List stagtegy2 = stagtegyService.findByMonth(year2, month2);			
			List stagtegy3 = stagtegyService.findByMonth(year3, month3);
			if (stagtegy1 == null) {
				month1=month;
				if(month==0){
					month1=12;
					year1--;
				}
				stagtegy1 = stagtegyService.findByMonth(year1,month1);
			}
			if (stagtegy2 == null) {
				month2=month-1;
				if(month==1){
					month2=12;
					year2--;
				}
				if(month==0){
					month2=11;
					year2--;
				}
				stagtegy3 = stagtegyService.findByMonth(year2, month2);
			}
			if (stagtegy3 == null) {
				month3=month-2;
				if(month==2){
					month3=12;
					year3--;
				}
				if(month==1){
					month3=11;
					year3--;
				}
				if(month==0){
					month3=10;
					year3--;
				}
				stagtegy3 = stagtegyService.findByMonth(year3, month3);
			}
			jo.put("state", "1");
			js.put(month1, JSONArray.fromObject(stagtegy1,getDefaultJsonConfig()));
			js.put(month2, JSONArray.fromObject(stagtegy2,getDefaultJsonConfig()));
			js.put(month3, JSONArray.fromObject(stagtegy3,getDefaultJsonConfig()));
			jo.put("value",js.toString());
			sendForStr(response, jo);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
		return null;

	}

	@RequestMapping("/stagtegyController/findByConditionAndSot.do")
	public String findByConditionAndSot(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
		JSONObject aj = req.getJSONObject("sort");		
		JSONObject jo = new JSONObject();
		int sort = 0;
		Date date = new Date();
		int year = date.getYear()+1900;
		int spotId = 0;
		int stagtegyType = 0;
		String fitMonth = null;
		String travelDays = null;
		String stagtegyName=null;
		String status=null;
		if (!aj.getString("travelDescId").equals("0")) {
			sort = 1;
		}		
		if(req.has("condition")==true){
			JSONObject bj = req.getJSONObject("condition");
			if (bj.has("spotId")==true) {
				spotId =Integer.parseInt(bj.getString("spotId"));
			}
			if(bj.has("stagtegyName")==true){
				stagtegyName=bj.getString("stagtegyName");
			}
			if(bj.has("status")==true){
				status=req.getString("status");
			}
			if (bj.has("fitMonth")==true){
				fitMonth = bj.getString("fitMonth");
			}
			if (bj.has("travelDays")==true) {
				travelDays = bj.getString("travelDays");
			}
			if ((bj.has("stagtegyType") == true)) {
				stagtegyType =Integer.parseInt(bj.getString("stagtegyType"));
			}
		}
		try {
			List sList = stagtegyService.findByConditionAndSot(year-1,
					req.getInt("pageNum"), req.getInt("pageCount"), sort,
					spotId, fitMonth, travelDays, stagtegyType,stagtegyName,status);			
			jo.put("state", "1");
			if(sList.size()>0){
				BusStagtegy stagtegy = (BusStagtegy) sList.get(0);
				jo.put("count", stagtegy.getCount());			
			}
			else {
				jo.put("count", "0");
			}
			jo.put("value",JSONArray.fromObject(sList,getDefaultJsonConfig()));
			sendForStr(response, jo);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
		return null;

	}
	
	
	
	@RequestMapping("/stagtegyController/findHotStagtegy.do")
	public String findHotStagtegy(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
		try {
			List sList = stagtegyService.findHotStagtegy(req.getInt("pageNUm"));
			sendForList(sList, response, null);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
		return null;

	}

	@RequestMapping("/stagtegyController/findById.do")
	public String findById(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
		SysUser u = (SysUser) request.getSession().getAttribute("user");		
		try {
			BusStagtegy stagtegy = stagtegyService.findById(req
					.getInt("stagtegyId"));
			travelDescService.insertBrowse(u.getUserId(),req.getInt("stagtegyId"), "1", "bus_stagtegy", "stagtegy");
			sendForObject(stagtegy, response, null);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
		
		return null;

	}

	@RequestMapping("/stagtegyController/praiseStagtegy.do")
	public String praiseStagtegy(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
		SysUser u = (SysUser) request.getSession().getAttribute("user");
			

		BusPhase busPhase = new BusPhase();
		try {
			busPhase = travelDescService.findBusPhaseByUserId(u.getUserId(),
					req.getInt("stagtegyId"),"1");
		} catch (Exception e) {
			return null;
		}
		if (busPhase == null) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {			
				stagtegyService.praiseStagtegy(req.getInt("stagtegyId"),
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

	@RequestMapping("/stagtegyController/collectStagtegy.do")
	public String collectStagtegy(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
		SysUser u = (SysUser) request.getSession().getAttribute("user");
		
		BusCollect busCollect = new BusCollect();
		try {
			busCollect = travelDescService.findBusCollectByUserId(
					u.getUserId(), req.getInt("stagtegyId"),"1");
		} catch (Exception e) {
			return null;
		}
		if (busCollect == null) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {			
				stagtegyService.collectStagtegy(req.getInt("stagtegyId"),
						u.getUserId(),0,
						df.format(new Date()));
				String r = "收藏成功";
				sendString(r, response);
			} catch (BusinessException e) {
				sendForError(e, response);
				return null;
			}
		}
		else{
			String r1 = "已经收藏";
			sendErrorString(r1, response);
		}
		
		return null;

	}

	@RequestMapping("/stagtegyController/deleteStagtegy.do")
	public String deleteStagtegy(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
		SysUser u = (SysUser) request.getSession().getAttribute("user");
		
		try {
			stagtegyService.deleteStagtegy(req.getInt("stagtegyId"),u.getUserId());
			String r = "删除成功";
			sendString(r, response);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}	
		
		return null;

	}
	
	@RequestMapping("/stagtegyController/checkStagtegy.do")
	public String checkStagtegy(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
		try {
			stagtegyService.checkStagtegy(req.getInt("stagtegyId"),req.getString("status"));
			String r = "成功";
			sendString(r, response);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}		
		return null;

	}
	@RequestMapping("/stagtegyController/findRelationStagtegy.do")
	public String findRelationStagtegy(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
		
		try {
			BusStagtegy stagtegy=stagtegyService.findById(req.getInt("stagtegyId"));
			List sList = stagtegyService.findRelationStagtegy(
					stagtegy.getSpotId(), stagtegy.getFitMonth(),
					stagtegy.getTravelDays(),req.getInt("stagtegyId"), req.getInt("count"));
			sendForList(sList, response, null);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
		return null;

	}

	
	@RequestMapping("/stagtegyController/saveStagtegy.do")
	public String saveStagtegy(HttpServletRequest request,
			HttpServletResponse response) {
		String path = request.getSession().getServletContext().getRealPath("/")+"/upload/";
		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  		
		MultipartFile imgFile1  =  multipartRequest.getFile("url1");  
		MultipartFile imgFile2  =  multipartRequest.getFile("url2");
		MultipartFile imgFile3  =  multipartRequest.getFile("url3");		
		List fileTypes = new ArrayList();  
        fileTypes.add("jpg");  
        fileTypes.add("jpeg");  
        fileTypes.add("bmp");  
        fileTypes.add("gif"); 
        String path1="stagtegy";
        String path2="images";
        String imgurl1 = null;
		String imgurl2 = null;
		String imgurl3 = null;
		List urlList = new ArrayList();
		if(!(imgFile1.getOriginalFilename() ==null || "".equals(imgFile1.getOriginalFilename()))) {  
        	File file1 = this.getFile(imgFile1,path1,path2,fileTypes,path);          	              
        	imgurl1=file1.getAbsolutePath().replace(request.getSession().getServletContext().getRealPath("/"), "");        	
        	imgurl1=imgurl1.replace("\\", "/");
        	urlList.add(imgurl1);
		}
        if(!(imgFile2.getOriginalFilename() ==null || "".equals(imgFile2.getOriginalFilename()))) {  
        	File file2 = this.getFile(imgFile2,path1,path2,fileTypes,path);          	              
        	imgurl2=file2.getAbsolutePath().replace(request.getSession().getServletContext().getRealPath("/"), "");        	
        	imgurl2=imgurl2.replace("\\", "/");
        	urlList.add(imgurl2);
        }
        if(!(imgFile3.getOriginalFilename() ==null || "".equals(imgFile3.getOriginalFilename()))) {  
        	File file3 = this.getFile(imgFile3,path1,path2,fileTypes,path);          	              
        	imgurl3=file3.getAbsolutePath().replace(request.getSession().getServletContext().getRealPath("/"), "");        	
        	imgurl3=imgurl3.replace("\\", "/");
        	urlList.add(imgurl3);
        }
		Integer spotId = Integer.parseInt(multipartRequest.getParameter("spotId"));
		String stagtegyTitle = multipartRequest.getParameter("stagtegyTitle");
		String stagtegyContent = multipartRequest.getParameter("stagtegyContent");
		Integer travelDays = Integer.parseInt(multipartRequest
				.getParameter("travelDays"));
		Integer fitMonth = Integer.parseInt(multipartRequest.getParameter("fitMonth"));
		Integer stagtegyType = Integer.parseInt(multipartRequest
				.getParameter("stagtegyType"));
		String status = multipartRequest.getParameter("status");
		String keys = multipartRequest.getParameter("keys");
		String subComment = multipartRequest.getParameter("subComment");
		
		JSONObject imagesUrl = new JSONObject();
		imagesUrl.put("images", JSONArray.fromObject(urlList));
		SysUser u = (SysUser) request.getSession().getAttribute("user");
		BusStagtegy stagtegy2 = new BusStagtegy();
		stagtegy2.setImages(imagesUrl.toString());
		stagtegy2.setUserId(u.getUserId());
		stagtegy2.setCreateTime(new Date());
		stagtegy2.setFitMonth(fitMonth);
		stagtegy2.setKeys(keys);
		stagtegy2.setSpotId(spotId);
		stagtegy2.setStagtegyContent(stagtegyContent);
		stagtegy2.setStagtegyTitle(stagtegyTitle);
		stagtegy2.setStagtegyType(stagtegyType);
		stagtegy2.setStatus(status);
		stagtegy2.setSubComment(subComment);
		stagtegy2.setTravelDays(travelDays);
		
		try {

			stagtegyService.saveStagtegy(stagtegy2);
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

	@RequestMapping("/stagtegyController/updateStagtegy.do")
	public String updateStagtegy(HttpServletRequest request,
			HttpServletResponse response) {
		String path = request.getSession().getServletContext().getRealPath("/")+"/upload/";
		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  		
		MultipartFile imgFile1  =  multipartRequest.getFile("url1");  
		MultipartFile imgFile2  =  multipartRequest.getFile("url2");
		MultipartFile imgFile3  =  multipartRequest.getFile("url3");		
		List fileTypes = new ArrayList();  
        fileTypes.add("jpg");  
        fileTypes.add("jpeg");  
        fileTypes.add("bmp");  
        fileTypes.add("gif"); 
        String path1="stagtegy";
        String path2="images";
        String imgurl1 = null;
		String imgurl2 = null;
		String imgurl3 = null;
		BusStagtegy busStagtegy=new BusStagtegy();
		try {
			busStagtegy=stagtegyService.findById(Integer.parseInt(multipartRequest.getParameter("stagtegyId")));					
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
		List urlList = new ArrayList();
		
		List imgList=new ArrayList();
		if(!(busStagtegy.getImages()==null))
		{
			JSONObject j=JSONObject.fromObject(busStagtegy.getImages());
			imgList=JSONArray.fromObject(j.get("images"));
		}
		if(!(imgFile1.getOriginalFilename() ==null || "".equals(imgFile1.getOriginalFilename()))) {  
        	File file1 = this.getFile(imgFile1,path1,path2,fileTypes,path);          	              
        	imgurl1=file1.getAbsolutePath().replace(request.getSession().getServletContext().getRealPath("/"), "");        	
        	imgurl1=imgurl1.replace("\\", "/");
        	urlList.add(imgurl1);
		}
		else{
			
			imgurl1=imgList.get(0).toString();
			urlList.add(imgurl1);
		}
        if(!(imgFile2.getOriginalFilename() ==null || "".equals(imgFile2.getOriginalFilename()))) {  
        	File file2 = this.getFile(imgFile2,path1,path2,fileTypes,path);          	              
        	imgurl2=file2.getAbsolutePath().replace(request.getSession().getServletContext().getRealPath("/"), "");        	
        	imgurl2=imgurl2.replace("\\", "/");
        	urlList.add(imgurl2);
        }
        else{
        	if(imgList.size()>1){
			imgurl2=imgList.get(1).toString();
			urlList.add(imgurl2);
        	}
		}
        if(!(imgFile3.getOriginalFilename() ==null || "".equals(imgFile3.getOriginalFilename()))) {  
        	File file3 = this.getFile(imgFile3,path1,path2,fileTypes,path);          	              
        	imgurl3=file3.getAbsolutePath().replace(request.getSession().getServletContext().getRealPath("/"), "");        	
        	imgurl3=imgurl3.replace("\\", "/");
        	urlList.add(imgurl3);
        }
        else{
        	if(imgList.size()>2){
			imgurl3=imgList.get(2).toString();
			urlList.add(imgurl3);
        	}
		}
		Integer spotId = Integer.parseInt(multipartRequest.getParameter("spotId"));
		String stagtegyTitle = multipartRequest.getParameter("stagtegyTitle");
		String stagtegyContent = multipartRequest.getParameter("stagtegyContent");
		Integer travelDays = Integer.parseInt(multipartRequest
				.getParameter("travelDays"));
		Integer fitMonth = Integer.parseInt(multipartRequest.getParameter("fitMonth"));
		Integer stagtegyType = Integer.parseInt(multipartRequest
				.getParameter("stagtegyType"));
		Integer stagtegyId = Integer.parseInt(multipartRequest
				.getParameter("stagtegyId"));
		String status = multipartRequest.getParameter("status");
		String keys = multipartRequest.getParameter("keys");
		String subComment = multipartRequest.getParameter("subComment");
		
		
		
		
		
		JSONObject imagesUrl = new JSONObject();
		imagesUrl.put("images", JSONArray.fromObject(urlList));
		SysUser u = (SysUser) request.getSession().getAttribute("user");
		BusStagtegy stagtegy2 = new BusStagtegy();
		stagtegy2.setImages(imagesUrl.toString());
		stagtegy2.setUserId(u.getUserId());
		stagtegy2.setCreateTime(new Date());
		stagtegy2.setFitMonth(fitMonth);
		stagtegy2.setKeys(keys);
		stagtegy2.setSpotId(spotId);
		stagtegy2.setStagtegyContent(stagtegyContent);
		stagtegy2.setStagtegyTitle(stagtegyTitle);
		stagtegy2.setStagtegyType(stagtegyType);
		stagtegy2.setStatus(status);
		stagtegy2.setSubComment(subComment);
		stagtegy2.setTravelDays(travelDays);
		stagtegy2.setStagtegyId(stagtegyId);
	
		try {

			stagtegyService.updateStagtegy(stagtegy2);
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

	
	@RequestMapping("/stagtegyController/findStagtegyByUser.do")
	public String findDescByUser(HttpServletRequest request,
			HttpServletResponse response) {
		SysUser u = (SysUser) request.getSession().getAttribute("user");
		JSONObject req = getJsonObjectReq(request);
		
		try {
			List stagtegyByUserId = stagtegyService.findStagtegyByUser(req.getInt("userId"));
			sendForList(stagtegyByUserId, response, null);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
		
		return null;
	}
	
	
	@RequestMapping("/stagtegyController/findByUserCollect.do")
	public String findByUserCollect(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
		SysUser u = (SysUser) request.getSession().getAttribute("user");
		
		try {
			List sList=stagtegyService.findByUserCollect(req.getInt("userId"));
			sendForList(sList, response, null);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
		
		return null;

	}
	
	@RequestMapping("/stagtegyController/findstagtegyByCollect.do")
	public String findstagtegyByCollect(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
		SysUser u = (SysUser) request.getSession().getAttribute("user");
		
		try {
			List sList=stagtegyService.findstagtegyByCollect(req.getInt("userId"));
			sendForList(sList, response, null);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
		
		return null;

	}
	
	
	@RequestMapping("/stagtegyController/addStagtegyType.do")
	public String addStagtegyType(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);		
		SysStagtegyType stagtegyType=new SysStagtegyType();
		stagtegyType.setTypeName(req.getString("typeName"));
		try {
			stagtegyService.addStagtegyType(stagtegyType);
			sendString("增加成功", response);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
		
		return null;

	}
	
	@RequestMapping("/stagtegyController/updateStagtegyType.do")
	public String updateStagtegyType(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);		
		SysStagtegyType stagtegyType=new SysStagtegyType();
		stagtegyType.setTypeId(req.getInt("typeId"));
		stagtegyType.setTypeName(req.getString("typeName"));
		try {
			stagtegyService.updateStagtegyType(stagtegyType);
			sendString("修改成功", response);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
		
		return null;

	}
	
	
	@RequestMapping("/stagtegyController/deleteStagtegyType.do")
	public String deleteStagtegyType(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);		
		try {
			stagtegyService.deleteStagtegyType(req.getInt("typeId"));
			sendString("删除成功", response);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
		
		return null;

	}
	
	@RequestMapping("/stagtegyController/deleteStagtegyByIds.do")
	public String deleteStagtegyByIds(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);		
		List jList=JSONArray.fromObject(req.getString("stagtegyId"));
		String image="";
		for(int i=0;i<jList.size();i++){
			image+=jList.get(i)+",";
		}
		image=image.substring(0,image.length()-1);
		try {
			stagtegyService.deleteStagtegyType(image);
			sendString("删除成功", response);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
		
		return null;

	}
	
	
	
	
}
