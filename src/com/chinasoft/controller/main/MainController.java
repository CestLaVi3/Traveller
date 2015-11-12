package com.chinasoft.controller.main;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import com.chinasoft.model.main.SysHref;
import com.chinasoft.model.main.SysMainImage;
import com.chinasoft.model.spot.RlSpotSpecialty;
import com.chinasoft.model.user.SysUser;
import com.chinasoft.service.main.MainService;
import com.sun.crypto.provider.RSACipher;
@Controller
public class MainController extends BaseJsonController {
	private MainService mainService;

	
	public MainService getMainService() {
		return mainService;
	}


	public void setMainService(MainService mainService) {
		this.mainService = mainService;
	}

	//查询主页链接
	@RequestMapping("/mainController/findMainHref.do")
	public String findMainHref(HttpServletRequest request,
			HttpServletResponse response) {	
		
		try {
			List list=mainService.findMainHref();
			this.sendForList(list, response, null);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}		
		return null;
		
	}
	//查询主页轮播图片
	@RequestMapping("/mainController/findMainImage.do")
	public String findMainImage(HttpServletRequest request,
			HttpServletResponse response) {	
		try {
			List list=mainService.findMainImage();
			this.sendForList(list, response, null);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}		
		return null;
		
	}
	
	//查询主页轮播图片
		@RequestMapping("/mainController/findSession.do")
		public String findSession(HttpServletRequest request,
				HttpServletResponse response) {	
			SysUser u = (SysUser) request.getSession().getAttribute("user");	
			if(u==null){
				sendErrorString("没有登录", response);
			}
			else{
				sendForObject(u, response, null);
			}
			return null;
			
		}
	@RequestMapping("/mainController/addImage.do")
	public String addImage(HttpServletRequest request,
			HttpServletResponse response) {		
		String path = request.getSession().getServletContext().getRealPath("/")+"/upload/";
		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  		
		MultipartFile imgFile1  =  multipartRequest.getFile("url");	
		List fileTypes = new ArrayList();  
	    fileTypes.add("jpg");  
	    fileTypes.add("jpeg");  
	    fileTypes.add("bmp");  
	    fileTypes.add("gif"); 
	    String path1="mainImage";
	    String path2="images";
	    String imgurl1 = null;
	    if(!(imgFile1.getOriginalFilename() ==null || "".equals(imgFile1.getOriginalFilename()))) {  
	    	File file1 = this.getFile(imgFile1,path1,path2,fileTypes,path);          	              
	    	imgurl1=file1.getAbsolutePath().replace(request.getSession().getServletContext().getRealPath("/"), "");        	
	    	imgurl1=imgurl1.replace("\\", "/");	    	
	    }
	    SysMainImage mainImage=new SysMainImage();
	    mainImage.setImageUrl(imgurl1);
	    mainImage.setImageDesc(multipartRequest.getParameter("imageDesc"));
		try {
			mainService.addImage(mainImage);
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
	
	@RequestMapping("/mainController/updateImage.do")
	public String updateImage(HttpServletRequest request,
			HttpServletResponse response) {		
		String path = request.getSession().getServletContext().getRealPath("/")+"/upload/";
		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  		
		MultipartFile imgFile1  =  multipartRequest.getFile("url");	
		List fileTypes = new ArrayList();  
	    fileTypes.add("jpg");  
	    fileTypes.add("jpeg");  
	    fileTypes.add("bmp");  
	    fileTypes.add("gif"); 
	    String path1="mainImage";
	    String path2="images";
	    String imgurl1 = null;
	    SysMainImage mainImage2=new SysMainImage();
	    try {
			mainImage2=mainService.findMainImageById(Integer.parseInt(multipartRequest.getParameter("imageId")));
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
	    if(!(imgFile1.getOriginalFilename() ==null || "".equals(imgFile1.getOriginalFilename()))) {  
	    	File file1 = this.getFile(imgFile1,path1,path2,fileTypes,path);          	              
	    	imgurl1=file1.getAbsolutePath().replace(request.getSession().getServletContext().getRealPath("/"), "");        	
	    	imgurl1=imgurl1.replace("\\", "/");	    	
	    }else{
	    	imgurl1=mainImage2.getImageUrl();	    	
	    }
	    SysMainImage mainImage=new SysMainImage();
	    mainImage.setImageId(Integer.parseInt(multipartRequest.getParameter("imageId")));
	    mainImage.setImageUrl(imgurl1);
	    mainImage.setImageDesc(multipartRequest.getParameter("imageDesc"));
		try {
			mainService.updateImage(mainImage);
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
	
	@RequestMapping("/mainController/deleteImage.do")
	public String deleteImage(HttpServletRequest request,
			HttpServletResponse response) {	
		JSONObject req = getJsonObjectReq(request);
		List jList=JSONArray.fromObject(req.getString("imageId"));
		String image="";
		for(int i=0;i<jList.size();i++){
			image+=jList.get(i)+",";
		}
		image=image.substring(0,image.length()-1);
		try {
			mainService.deleteImage(image);	
			sendString("删除成功", response);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}		
		return null;		
	}
	
	@RequestMapping("/mainController/findMainHrefByName.do")
	public String findMainHrefByName(HttpServletRequest request,
			HttpServletResponse response) {	
		JSONObject req = getJsonObjectReq(request);
		JSONObject jo=new JSONObject();
		try {
			List hList=mainService.findMainHrefByName(req.getString("hrefName"),req.getInt("pageNum"),req.getInt("pageCount"));		
			SysHref href=new SysHref();
			if(hList.size()>0){
				href=(SysHref)hList.get(0);
			}
			jo.put("state", "1");
			jo.put("count", href.getCount());
			jo.put("value", JSONArray.fromObject(hList, getDefaultJsonConfig()));
			sendForStr(response, jo);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}		
		return null;		
	}
	
	@RequestMapping("/mainController/addMainHref.do")
	public String addMainHref(HttpServletRequest request,
			HttpServletResponse response) {	
		JSONObject req = getJsonObjectReq(request);
		SysHref href=new SysHref();
		href.setHrefName(req.getString("hrefName"));
		href.setHrefUrl(req.getString("hrefUrl"));
		try {
			mainService.addMainHref(href);		
			sendString("增加成功", response);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}		
		return null;		
	}
	
	@RequestMapping("/mainController/updateMainHref.do")
	public String updateMainHref(HttpServletRequest request,
			HttpServletResponse response) {	
		JSONObject req = getJsonObjectReq(request);
		SysHref href=new SysHref();
		href.setHrefId(req.getInt("hrefId"));
		href.setHrefName(req.getString("hrefName"));
		href.setHrefUrl(req.getString("hrefUrl"));
		try {
			mainService.updateMainHref(href);		
			sendString("修改成功", response);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}		
		return null;		
	}
	
	
	@RequestMapping("/mainController/deleteMainHref.do")
	public String deleteMainHref(HttpServletRequest request,
			HttpServletResponse response) {	
		JSONObject req = getJsonObjectReq(request);		
		List jList=JSONArray.fromObject(req.getString("hrefId"));
		String image="";
		for(int i=0;i<jList.size();i++){
			image+=jList.get(i)+",";
		}
		image=image.substring(0,image.length()-1);
		try {
			mainService.deleteMainHref(image);		
			sendString("删除成功", response);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}		
		return null;		
	}
}
