package com.chinasoft.controller.travelDesc;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.chinasoft.common.utils.Exception.BusinessException;
import com.chinasoft.controller.BaseJsonController;
import com.chinasoft.model.general.BusCollect;
import com.chinasoft.model.general.BusPhase;
import com.chinasoft.model.spot.SysSpot;
import com.chinasoft.model.strategy.BusTravelDesc;
import com.chinasoft.model.user.SysUser;
import com.chinasoft.service.travelDesc.TravelDescService;
import com.sun.script.javascript.JSAdapter;

@Controller
public class TravelDescController extends BaseJsonController {
	private TravelDescService travelDescService;

	public TravelDescService getTravelDescService() {
		return travelDescService;
	}

	public void setTravelDescService(TravelDescService travelDescService) {
		this.travelDescService = travelDescService;
	}

	@RequestMapping("/travelDescController/findDescByCondition.do")
	public String findDescByCondition(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
		String s = null;
		if (req.getString("type").equals("0")) {
			s = "create_Time";
		} else if (req.getString("type").equals("1")) {
			s = "praise_Num";
		} else if (req.getString("type").equals("2")) {
			s = "comment_Num";
		} else if (req.getString("type").equals("3")) {
			s = "invest_Num";
		}
		try {
			List travelDesc = travelDescService.findDescByCondition(s);
			sendForList(travelDesc, response, null);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
		return null;

	}

	@RequestMapping("/travelDescController/findDescByPage.do")
	public String findDescByPage(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
		JSONObject jo = new JSONObject();
		String descTitle=null;
		if(req.has("descTitle")){
			descTitle=req.getString("descTitle");
		}
		try {
			List aList = travelDescService.findDescByPage(
					req.getInt("pageNum"), req.getInt("pageCount"),
					req.getInt("spotId"),descTitle);
			BusTravelDesc busTravelDesc=new BusTravelDesc();
			if(aList.size()>0){
			busTravelDesc = (BusTravelDesc) aList.get(0);
			}
			jo.put("state", "1");
			jo.put("count", busTravelDesc.getCount());
			jo.put("value", JSONArray.fromObject(aList, getDefaultJsonConfig()));
			sendForStr(response, jo);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
		return null;

	}
	@RequestMapping("/travelDescController/findDescBySpotName.do")
	public String findDescBySpotName(HttpServletRequest request,
			HttpServletResponse response){
		JSONObject req = getJsonObjectReq(request);
		JSONObject jo = new JSONObject();
		try {
			List aList = travelDescService.findDescBySpotName(
					req.getInt("pageNum"), req.getInt("pageCount"),
					req.getString("spotName"));
			BusTravelDesc busTravelDesc=new BusTravelDesc();
			if(aList.size()>0){
			busTravelDesc = (BusTravelDesc) aList.get(0);
			}
			jo.put("state", "1");
			jo.put("count", busTravelDesc.getCount());
			jo.put("value", JSONArray.fromObject(aList, getDefaultJsonConfig()));
			sendForStr(response, jo);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
		return null;

	}
	@RequestMapping("/travelDescController/findDescById.do")
	public String findDescById(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
		SysUser u = (SysUser) request.getSession().getAttribute("user");
		
			try {
				BusTravelDesc descById = travelDescService.findDescById(req.getInt("travelId"));
				travelDescService.insertBrowse(u.getUserId(),req.getInt("travelId"), "0", "bus_travel_desc", "travel_desc");
				sendForObject(descById, response, null);
			} catch (BusinessException e) {
				sendForError(e, response);
				return null;
			}
		

		return null;
	}

	@RequestMapping("/travelDescController/findRelationSpotById.do")
	public String findRelationSpotById(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
		try {
			List descBySpotId = travelDescService.findRelationSpotById(
					req.getInt("spotId"), req.getInt("count"));
			sendForList(descBySpotId, response, null);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
		return null;

	}

	@RequestMapping("/travelDescController/findDescByUser.do")
	public String findDescByUser(HttpServletRequest request,
			HttpServletResponse response) {
		SysUser u = (SysUser) request.getSession().getAttribute("user");
		JSONObject req = getJsonObjectReq(request);
		
		try {
			List descByUserId = travelDescService.findDescByUser(req.getInt("userId"));
			sendForList(descByUserId, response, null);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
		
		return null;

	}

	@RequestMapping("/travelDescController/praiseTravelDesc.do")
	public String praiseTravelDesc(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
		SysUser u = (SysUser) request.getSession().getAttribute("user");
		
		BusPhase busPhase = new BusPhase();
		try {
			busPhase = travelDescService.findBusPhaseByUserId(u.getUserId(),
					req.getInt("travelDescId"),"0");
		} catch (Exception e) {
			return null;
		}
		if (busPhase == null) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				travelDescService.praiseTravelDesc(req.getInt("travelDescId"),
						u.getUserId(),0,
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

	@RequestMapping("/travelDescController/collectTravelDesc.do")
	public String collectTravelDesc(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
		SysUser u = (SysUser) request.getSession().getAttribute("user");
		
		BusCollect busCollect = new BusCollect();
		try {
			busCollect = travelDescService.findBusCollectByUserId(
					u.getUserId(), req.getInt("travelDescId"),"0");
		} catch (Exception e) {
			return null;
		}
		if (busCollect == null) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {				
				travelDescService.collectTravelDesc(req.getInt("travelDescId"),
						u.getUserId(), 0,
						df.format(new Date()));
				String r = "收藏成功";
				sendString(r, response);
			} catch (BusinessException e) {
				sendForError(e, response);
				return null;
			}
		}else{
			String r1 = "已经收藏";
			sendErrorString(r1, response);
		}
		
		return null;

	}

	
	
	@RequestMapping("/travelDescController/saveTravelDesc.do")
	public String saveTravelDesc(HttpServletRequest request,
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
        String path1="travelDesc";
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
		String descTitle = multipartRequest.getParameter("descTitle");
		String descContent = multipartRequest.getParameter("descContent");
		
		JSONObject imagesUrl = new JSONObject();
		imagesUrl.put("images", JSONArray.fromObject(urlList));
		SysUser u = (SysUser) request.getSession().getAttribute("user");
		BusTravelDesc travelDesc = new BusTravelDesc();
		travelDesc.setImages(imagesUrl.toString());
		travelDesc.setSpotId(spotId);		
		travelDesc.setCreateTime(new Date());	
		travelDesc.setDescTitle(descTitle);
		travelDesc.setUserId(u.getUserId());
		travelDesc.setDescContent(descContent);
		
		try {
			travelDescService.saveTravelDesc(travelDesc);
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

	@RequestMapping("/travelDescController/updateTravelDesc.do")
	public String updateTravelDesc(HttpServletRequest request,
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
        String path1="travelDesc";
        String path2="images";
        String imgurl1 = null;
		String imgurl2 = null;
		String imgurl3 = null;
		BusTravelDesc busTravelDesc=new BusTravelDesc();
		try {
			busTravelDesc=travelDescService.findDescById(Integer.parseInt(multipartRequest.getParameter("travelDescId")));					
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
		List urlList = new ArrayList();
		List imgList=new ArrayList();
		if(!(busTravelDesc.getImages()==null))
		{
			JSONObject j=JSONObject.fromObject(busTravelDesc.getImages());
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
        	if(imgList.size()>1){
			imgurl3=imgList.get(2).toString();
			urlList.add(imgurl3);
        	}
		}
		Integer spotId = Integer.parseInt(multipartRequest.getParameter("spotId"));
		String descTitle = multipartRequest.getParameter("descTitle");
		String descContent = multipartRequest.getParameter("descContent");
		Integer travelDescId = Integer.parseInt(multipartRequest
				.getParameter("travelDescId"));
		
		JSONObject imagesUrl = new JSONObject();
		imagesUrl.put("images", JSONArray.fromObject(urlList));
		SysUser u = (SysUser) request.getSession().getAttribute("user");
		BusTravelDesc travelDesc = new BusTravelDesc();
		travelDesc.setImages(imagesUrl.toString());
		travelDesc.setSpotId(spotId);
		travelDesc.setCreateTime(new Date());
		travelDesc.setDescTitle(descTitle);
		travelDesc.setUserId(u.getUserId());
		travelDesc.setDescContent(descContent);
		travelDesc.setTravelDescId(travelDescId);
		
		try {
			travelDescService.updateTravelDesc(travelDesc);
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

	@RequestMapping("/travelDescController/deleteTravelDesc.do")
	public String deleteTravelDesc(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
		SysUser u = (SysUser) request.getSession().getAttribute("user");				
		try {
			travelDescService.deleteTravelDesc(req.getInt("travelDescId"),u.getUserId());
			String r = "删除成功";
			sendString(r, response);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
		return null;

	}
	
	
	@RequestMapping("/travelDescController/deleteDescByIds.do")
	public String deleteDescByIds(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
		List jList=JSONArray.fromObject(req.getString("travelDescId"));
		String image="";
		for(int i=0;i<jList.size();i++){
			image+=jList.get(i)+",";
		}
		image=image.substring(0,image.length()-1);
		try {
			travelDescService.deleteDescByIds(image);
			String r = "删除成功";
			sendString(r, response);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
		return null;

	}
	
	@RequestMapping("/travelDescController/findDescByUserCollect.do")
	public String findDescByUserCollect(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
		SysUser u = (SysUser) request.getSession().getAttribute("user");
		
		try {
			List tList=travelDescService.findDescByUserCollect(req.getInt("userId"));
			sendForList(tList, response, null);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
		
		return null;

	}
	
	@RequestMapping("/travelDescController/findDescByCollect.do")
	public String findDescByCollect(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject req = getJsonObjectReq(request);
		SysUser u = (SysUser) request.getSession().getAttribute("user");
		
		try {
			List tList=travelDescService.findDescByCollect(req.getInt("userId"));
			sendForList(tList, response, null);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
		
		return null;

	}
}
