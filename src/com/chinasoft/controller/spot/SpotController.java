package com.chinasoft.controller.spot;

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
import com.chinasoft.model.spot.SysProvince;
import com.chinasoft.model.spot.SysScenicSpot;
import com.chinasoft.model.spot.SysSpot;
import com.chinasoft.model.user.SysUser;
import com.chinasoft.service.spot.SpotService;

@Controller
public class SpotController extends BaseJsonController{
private SpotService spotService;

public SpotService getSpotService() {
	return spotService;
}

public void setSpotService(SpotService spotService) {
	this.spotService = spotService;
}
//根据景区id获取景区信息以及景点
@RequestMapping("/spotController/findSpotById.do")
public String findSpotById(HttpServletRequest request,
		HttpServletResponse response) {	
	JSONObject req = getJsonObjectReq(request);
	JSONObject jo=new JSONObject();
	try {
		SysScenicSpot scenicSpot=spotService.findSpotById(req.getInt("spotId"));
		System.out.println(scenicSpot);
		List Spot=spotService.findSpotByScienceSpotId(req.getInt("spotId"));
		jo.put("state", "1");
		jo.put("scienceSpot", JSONObject.fromObject(scenicSpot,getDefaultJsonConfig()));
		jo.put("spot",JSONArray.fromObject(Spot,getDefaultJsonConfig()));
		sendForStr(response, jo);
	} catch (BusinessException e) {
		sendForError(e, response);
		return null;
	}		
	return null;
	
}



@RequestMapping("/spotController/findAllSpot.do")
public String findAllSpot(HttpServletRequest request,
		HttpServletResponse response) {	
	try {
		List Spot=spotService.findAllSpot();
		sendForList(Spot, response, null);
	} catch (BusinessException e) {
		sendForError(e, response);
		return null;
	}		
	return null;
	
}

@RequestMapping("/spotController/findBySpotId.do")
public String findBySpotId(HttpServletRequest request,
		HttpServletResponse response) {	
	JSONObject req = getJsonObjectReq(request);
	try {
		List Spot=spotService.findSpotByScienceSpotId(req.getInt("spotId"));
		sendForList(Spot, response, null);
	} catch (BusinessException e) {
		sendForError(e, response);
		return null;
	}		
	return null;
	
}

@RequestMapping("/spotController/forbitSpot.do")
public String forbitSpot(HttpServletRequest request,
		HttpServletResponse response) {	
	JSONObject req = getJsonObjectReq(request);
					
	try {
		spotService.forbitSpot(req.getInt("sysSpotId"));
		sendString("修改成功", response);		
	} catch (BusinessException e) {
		sendForError(e, response);
		return null;
	}
	
	return null;
}

@RequestMapping("/spotController/unForbitSpot.do")
public String unForbitSpot(HttpServletRequest request,
		HttpServletResponse response) {	
	JSONObject req = getJsonObjectReq(request);
				
	try {
		spotService.unForbitSpot(req.getInt("sysSpotId"));
		sendString("修改成功", response);		
	} catch (BusinessException e) {
		sendForError(e, response);
		return null;
	}
	
	return null;
}


@RequestMapping("/spotController/findbyId.do")
public String findbyId(HttpServletRequest request,
		HttpServletResponse response) {	
	JSONObject req = getJsonObjectReq(request);				
	try {
		SysSpot spot=spotService.findbyId(req.getInt("sysSpotId"));
		sendForObject(spot, response, null);
	} catch (BusinessException e) {
		sendForError(e, response);
		return null;
	}
	
	return null;
}


@RequestMapping("/spotController/findAllspotType.do")
public String findAllspotType(HttpServletRequest request,
		HttpServletResponse response) {					
	try {
		List typeList=spotService.findAllspotType();
		sendForList(typeList, response, null);
	} catch (BusinessException e) {
		sendForError(e, response);
		return null;
	}
	
	return null;
}


@RequestMapping("/spotController/updateBasic.do")
public String updateBasic(HttpServletRequest request,
		HttpServletResponse response) {	
	JSONObject req = getJsonObjectReq(request);	
	SysSpot spot=new SysSpot();
	spot.setDiscountDesc(req.getString("discountDesc"));
	spot.setFitSeason(req.getString("fitSeason"));
	spot.setHotLevel(req.getInt("hotLevel"));
	spot.setLocationDesc(req.getString("locationDesc"));
	spot.setLocationX(req.getString("locationX"));
	spot.setLocationY(req.getString("locationY"));
	spot.setOpenTime(req.getString("openTime"));
	spot.setSpotName(req.getString("spotName"));
	spot.setSpotTypeId(req.getInt("spotTypeId"));
	spot.setSysSpotId(req.getInt("sysSpotId"));
	spot.setTicketDesc(req.getString("ticketDesc"));
	try {
		spotService.updateBasic(spot);
		sendString("修改成功", response);
	} catch (BusinessException e) {
		sendForError(e, response);
		return null;
	}
	
	return null;
}


@RequestMapping("/spotController/saveBasic.do")
public String saveBasic(HttpServletRequest request,
		HttpServletResponse response) {	
	JSONObject req = getJsonObjectReq(request);	
	SysSpot spot=new SysSpot();
	spot.setDiscountDesc(req.getString("discountDesc"));
	spot.setFitSeason(req.getString("fitSeason"));
	spot.setHotLevel(req.getInt("hotLevel"));
	spot.setLocationDesc(req.getString("locationDesc"));
	spot.setLocationX(req.getString("locationX"));
	spot.setLocationY(req.getString("locationY"));
	spot.setOpenTime(req.getString("openTime"));
        spot.setSpotTypeId(req.getInt("spotTypeId"));
	spot.setSpotName(req.getString("spotName"));
	spot.setTicketDesc(req.getString("ticketDesc"));
	spot.setSpotId(req.getInt("spotId"));
	try {
		spotService.saveBasic(spot);
		sendString("保存成功", response);
	} catch (BusinessException e) {
		sendForError(e, response);
		return null;
	}
	
	return null;
}

@RequestMapping("/spotController/updateSpotDesc.do")
public String updateSpotDesc(HttpServletRequest request,
		HttpServletResponse response) {	
	JSONObject req = getJsonObjectReq(request);	
	try {
		spotService.updateSpotDesc(req.getInt("sysSpotId"),req.getString("spotDesc"));
		sendString("修改成功", response);
	} catch (BusinessException e) {
		sendForError(e, response);
		return null;
	}
	
	return null;
}



@RequestMapping("/spotController/updateimages.do")
public String updateimages(HttpServletRequest request,
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
    String path1="Spot";
    String path2="images";
    String imgurl1 = null;
	String imgurl2 = null;
	String imgurl3 = null;
	String imgurl4 = null;
	String imgurl5 = null;
	String imgurl6 = null;
	List urlList = new ArrayList();
	SysSpot spot=new SysSpot();
	try {
		spot=spotService.findbyId(Integer.parseInt(multipartRequest.getParameter("sysSpotId")));
	} catch (BusinessException e) {
		sendForError(e, response);
		return null;
	}
	List imgList=new ArrayList();
	if(!(spot.getImages()==null))
	{
		JSONObject j=JSONObject.fromObject(spot.getImages());
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
		spotService.updateImages(Integer.parseInt(multipartRequest.getParameter("sysSpotId")), imagesUrl.toString());
		
		String scriptString = "<script>history.back();</script>";
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

