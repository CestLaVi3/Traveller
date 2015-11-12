package com.chinasoft.controller.province;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import oracle.net.aso.p;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.chinasoft.common.utils.Exception.BusinessException;
import com.chinasoft.controller.BaseJsonController;
import com.chinasoft.model.spot.SysProvince;
import com.chinasoft.model.user.SysUser;
import com.chinasoft.service.province.ProvinceService;
import com.sun.crypto.provider.RSACipher;
@Controller
public class ProvinceController extends BaseJsonController {
	private ProvinceService provinceService;

	public ProvinceService getProvinceService() {
		return provinceService;
	}

	public void setProvinceService(ProvinceService provinceService) {
		this.provinceService = provinceService;
	}
	
	
	@RequestMapping("/provinceController/findbyId.do")
	public String findbyId(HttpServletRequest request,
			HttpServletResponse response) {		
		JSONObject req = getJsonObjectReq(request);
		try {
			SysProvince province=provinceService.findbyId(req.getInt("provinceId"));
			sendForObject(province, response, null);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}		
		return null;
		
	}
	
	@RequestMapping("/provinceController/findallProvinces.do")
	public String findallProvinces(HttpServletRequest request,
			HttpServletResponse response) {		
		JSONObject req = getJsonObjectReq(request);
		try {
			List pList=provinceService.findAllProvince();
			sendForList(pList, response, null);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}		
		return null;
		
	}
	
	@RequestMapping("/provinceController/updateBasic.do")
	public String updateBasic(HttpServletRequest request,
			HttpServletResponse response) {		
		JSONObject req = getJsonObjectReq(request);
		SysProvince province=new SysProvince();
		province.setProvinceId(req.getInt("provinceId"));
		province.setProvinceName(req.getString("provinceName"));
		province.setLocationDesc(req.getString("locationDesc"));
		province.setLocationX(req.getString("locationX"));
		province.setLocationY(req.getString("locationY"));
		province.setHotLevel(req.getInt("hotLevel"));
		try {
			provinceService.updateBasic(province);
			sendString("修改成功", response);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}		
		return null;
		
	}
	
	
	@RequestMapping("/provinceController/updatetravelDesc.do")
	public String updatetravelDesc(HttpServletRequest request,
			HttpServletResponse response) {		
		JSONObject req = getJsonObjectReq(request);
		try {
			provinceService.updatetravelDesc(req.getInt("provinceId"),req.getString("travelDesc"));
			sendString("修改成功", response);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}		
		return null;
		
	}
	
	@RequestMapping("/provinceController/updateprovinceDesc.do")
	public String updateprovinceDesc(HttpServletRequest request,
			HttpServletResponse response) {		
		JSONObject req = getJsonObjectReq(request);
		try {
			provinceService.updateprovinceDesc(req.getInt("provinceId"),req.getString("provinceDesc"));
			sendString("修改成功", response);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}		
		return null;
		
	}
	
	@RequestMapping("/provinceController/updatehistoryDesc.do")
	public String updatehistoryDesc(HttpServletRequest request,
			HttpServletResponse response) {		
		JSONObject req = getJsonObjectReq(request);
		try {
			provinceService.updatehistoryDesc(req.getInt("provinceId"),req.getString("historyDesc"));
			sendString("修改成功", response);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}		
		return null;
		
	}
	
	@RequestMapping("/provinceController/updatecultureDesc.do")
	public String updatecultureDesc(HttpServletRequest request,
			HttpServletResponse response) {		
		JSONObject req = getJsonObjectReq(request);
		try {
			provinceService.updatecultureDesc(req.getInt("provinceId"),req.getString("cultureDesc"));
			sendString("修改成功", response);
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}		
		return null;
		
	}
	
	@RequestMapping("/provinceController/updateimage.do")
	public String updateimageBySpotId(HttpServletRequest request,
			HttpServletResponse response) {	
		
		String path = request.getSession().getServletContext().getRealPath("/")+"/upload/";
		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  		
		MultipartFile imgFile1  =  multipartRequest.getFile("url1");  
		MultipartFile imgFile2  =  multipartRequest.getFile("url2");
		MultipartFile imgFile3  =  multipartRequest.getFile("url3");	
		MultipartFile imgFile4  =  multipartRequest.getFile("url4");
		MultipartFile imgFile5  =  multipartRequest.getFile("url5");
		MultipartFile imgFile6  =  multipartRequest.getFile("url6");
		List fileTypes = new ArrayList();  
	    fileTypes.add("jpg");  
	    fileTypes.add("jpeg");  
	    fileTypes.add("bmp");  
	    fileTypes.add("gif"); 
	    String path1="scienceSpot";
	    String path2="images";
	    String imgurl1 = null;
		String imgurl2 = null;
		String imgurl3 = null;
		String imgurl4 = null;
		String imgurl5 = null;
		String imgurl6 = null;
		List urlList = new ArrayList();
		SysProvince province=new SysProvince();
		try {
			province=provinceService.findbyId(Integer.parseInt(multipartRequest.getParameter("provinceId")));
		} catch (BusinessException e) {
			sendForError(e, response);
			return null;
		}
		List imgList=new ArrayList();
		if(!(province.getImageUrl()==null))
		{
			JSONObject j=JSONObject.fromObject(province.getImageUrl());
			imgList=JSONArray.fromObject(j.get("images"));
		}
	    if(!(imgFile1.getOriginalFilename() ==null || "".equals(imgFile1.getOriginalFilename()))) {  
	    	File file1 = this.getFile(imgFile1,path1,path2,fileTypes,path);          	              
	    	imgurl1=file1.getAbsolutePath().replace(request.getSession().getServletContext().getRealPath("/"), "");        	
	    	imgurl1=imgurl1.replace("\\", "/");
	    	urlList.add(imgurl1);
	    }
	    else{
	    	if(imgList.size()>0){
	    	urlList.add(imgList.get(0).toString());
	    	}
	    }
	    if(!(imgFile2.getOriginalFilename() ==null || "".equals(imgFile2.getOriginalFilename()))) {  
	    	File file2 = this.getFile(imgFile2,path1,path2,fileTypes,path);          	              
	    	imgurl2=file2.getAbsolutePath().replace(request.getSession().getServletContext().getRealPath("/"), "");        	
	    	imgurl2=imgurl2.replace("\\", "/");
	    	urlList.add(imgurl2);
	    }
	    else{
	    	if(imgList.size()>1){
	    	urlList.add(imgList.get(1).toString());
	    	}
	    }
	    if(!(imgFile3.getOriginalFilename() ==null || "".equals(imgFile3.getOriginalFilename()))) {  
	    	File file3 = this.getFile(imgFile3,path1,path2,fileTypes,path);          	              
	    	imgurl3=file3.getAbsolutePath().replace(request.getSession().getServletContext().getRealPath("/"), "");        	
	    	imgurl3=imgurl3.replace("\\", "/");
	    	urlList.add(imgurl3);
	    } else{
	    	if(imgList.size()>2){
	        	urlList.add(imgList.get(2).toString());
	        	}
	        }
	    if(!(imgFile4.getOriginalFilename() ==null || "".equals(imgFile4.getOriginalFilename()))) {  
	    	File file4 = this.getFile(imgFile4,path1,path2,fileTypes,path);          	              
	    	imgurl4=file4.getAbsolutePath().replace(request.getSession().getServletContext().getRealPath("/"), "");        	
	    	imgurl4=imgurl4.replace("\\", "/");
	    	urlList.add(imgurl4);
	    } else{
	    	if(imgList.size()>3){
	        	urlList.add(imgList.get(3).toString());
	        	}
	        }
	    if(!(imgFile5.getOriginalFilename() ==null || "".equals(imgFile5.getOriginalFilename()))) {  
	    	File file5 = this.getFile(imgFile5,path1,path2,fileTypes,path);          	              
	    	imgurl5=file5.getAbsolutePath().replace(request.getSession().getServletContext().getRealPath("/"), "");        	
	    	imgurl5=imgurl5.replace("\\", "/");
	    	urlList.add(imgurl5);
	    } else{
	    	if(imgList.size()>4){
	        	urlList.add(imgList.get(4).toString());
	        	}
	        }
	    if(!(imgFile6.getOriginalFilename() ==null || "".equals(imgFile6.getOriginalFilename()))) {  
	    	File file6 = this.getFile(imgFile6,path1,path2,fileTypes,path);          	              
	    	imgurl6=file6.getAbsolutePath().replace(request.getSession().getServletContext().getRealPath("/"), "");        	
	    	imgurl6=imgurl6.replace("\\", "/");
	    	urlList.add(imgurl6);
	    }
	    else{
	    	if(imgList.size()>5){
	    	urlList.add(imgList.get(5).toString());
	    	}
	    }
		JSONObject imagesUrl = new JSONObject();
		imagesUrl.put("images", JSONArray.fromObject(urlList));
		try {
			provinceService.updateimage(Integer.parseInt(multipartRequest.getParameter("provinceId")), imagesUrl.toString());
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
