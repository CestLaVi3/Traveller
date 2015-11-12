package com.chinasoft.controller.scienceSpot;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.chinasoft.dao.spot.SpotDao;
import com.chinasoft.model.spot.RlSpotFamousHuman;
import com.chinasoft.model.spot.RlSpotFamousMenu;
import com.chinasoft.model.spot.RlSpotSpecialty;
import com.chinasoft.model.spot.SysProvince;
import com.chinasoft.model.spot.SysScenicSpot;
import com.chinasoft.model.strategy.BusTravelDesc;
import com.chinasoft.model.user.SysUser;
import com.chinasoft.service.province.ProvinceService;
import com.chinasoft.service.scienceSpot.ScienceSpotService;
import com.chinasoft.service.spot.SpotService;
import com.chinasoft.service.travelDesc.TravelDescService;

@Controller
public class ScienceSpotController extends BaseJsonController {
private ScienceSpotService scienceSpotService;
private SpotService spotService;
private ProvinceService provinceService;


public SpotService getSpotService() {
	return spotService;
}

public void setSpotService(SpotService spotService) {
	this.spotService = spotService;
}

public ProvinceService getProvinceService() {
	return provinceService;
}

public void setProvinceService(ProvinceService provinceService) {
	this.provinceService = provinceService;
}

public ScienceSpotService getScienceSpotService() {
	return scienceSpotService;
}

public void setScienceSpotService(ScienceSpotService scienceSpotService) {
	this.scienceSpotService = scienceSpotService;
}
//查询主页景区以及相关景点11111
@RequestMapping("/scienceSpotController/findMainSpot.do")
public String findMainSpot(HttpServletRequest request,
		HttpServletResponse response) {		
	JSONObject jo=new JSONObject();
	try {
		List scienceSpot=scienceSpotService.findScienceSpotByHotLevel();
		List spot=scienceSpotService.findSpotByScienceSpotId(scienceSpot);
		jo.put("state", "1");
		jo.put("scienceSpot", JSONArray.fromObject(scienceSpot,getDefaultJsonConfig()));
		jo.put("spot", JSONArray.fromObject(spot,getDefaultJsonConfig()));
		sendForStr(response, jo);
	} catch (BusinessException e) {
		sendForError(e, response);
		return null;
	}		
	return null;
	
}

//查询景区
@RequestMapping("/scienceSpotController/findSpotByCount.do")
public String findSpotByCount(HttpServletRequest request,
		HttpServletResponse response) {	
	JSONObject req = getJsonObjectReq(request);
	try {
		List hotSpot=scienceSpotService.findSpotByCount(req.getString("count"));
		sendForList(hotSpot, response, null);
	} catch (BusinessException e) {
		sendForError(e, response);
		return null;
	}		
	return null;
	
}

@RequestMapping("/scienceSpotController/findbyProvinceId.do")
public String findbyProvinceId(HttpServletRequest request,
		HttpServletResponse response) {	
	JSONObject req = getJsonObjectReq(request);
	try {
		List hotSpot=scienceSpotService.findbyProvinceId(req.getInt("provinceId"));
		sendForList(hotSpot, response, null);
	} catch (BusinessException e) {
		sendForError(e, response);
		return null;
	}		
	return null;
	
}

@RequestMapping("/scienceSpotController/findById.do")
public String findById(HttpServletRequest request,
		HttpServletResponse response) {	
	JSONObject req = getJsonObjectReq(request);
	try {
		SysScenicSpot scenicSpot=scienceSpotService.findById(req.getInt("spotId"));
		sendForObject(scenicSpot, response, null);
	} catch (BusinessException e) {
		sendForError(e, response);
		return null;
	}	
	return null;
}
	



@RequestMapping("/scienceSpotController/updateBasic.do")
public String updateBasic(HttpServletRequest request,
		HttpServletResponse response) {	
	JSONObject req = getJsonObjectReq(request);
	
		
		SysScenicSpot scenicSpot=new SysScenicSpot();
		scenicSpot.setSpotId(req.getInt("spotId"));
		scenicSpot.setProvinceId(req.getInt("provinceId"));
		scenicSpot.setSpotName(req.getString("spotName"));
		scenicSpot.setLocationX(req.getString("locationX"));
		scenicSpot.setLocationY(req.getString("locationY"));
		scenicSpot.setHotLevel(req.getInt("hotLevel"));
		scenicSpot.setLocationDesc(req.getString("locationDesc"));
	try {
		scienceSpotService.updateBasic(scenicSpot);
		sendString("修改成功", response);
		
	} catch (BusinessException e) {
		sendForError(e, response);
		return null;
	}
	return null;
}


@RequestMapping("/scienceSpotController/findhumanbyspotId.do")
public String findhumanbyspotId(HttpServletRequest request,
		HttpServletResponse response) {	
	JSONObject req = getJsonObjectReq(request);			
	try {
		List sList=scienceSpotService.findhumanbyspotId(req.getInt("spotId"));
		sendForList(sList, response, null);
	} catch (BusinessException e) {
		sendForError(e, response);
		return null;
	}	
	return null;
}


@RequestMapping("/scienceSpotController/findmenubyspotId.do")
public String findmenubyspotId(HttpServletRequest request,
		HttpServletResponse response) {	
	JSONObject req = getJsonObjectReq(request);			
	try {
		List sList=scienceSpotService.findmenubyspotId(req.getInt("spotId"));
		sendForList(sList, response, null);
	} catch (BusinessException e) {
		sendForError(e, response);
		return null;
	}	
	return null;
}

@RequestMapping("/scienceSpotController/findSpecialtybyspotId.do")
public String findSpecialtybyspotId(HttpServletRequest request,
		HttpServletResponse response) {	
	JSONObject req = getJsonObjectReq(request);			
	try {
		List sList=scienceSpotService.findSpecialtybyspotId(req.getInt("spotId"));
		sendForList(sList, response, null);
	} catch (BusinessException e) {
		sendForError(e, response);
		return null;
	}	
	return null;
}

@RequestMapping("/scienceSpotController/updateMenu.do")
public String updateMenu(HttpServletRequest request,
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
    String path1="scienceSpot";
    String path2="images";
    String imgurl1 = null;
	String imgurl2 = null;
	String imgurl3 = null;
	RlSpotFamousMenu spotFamousMenu2=new RlSpotFamousMenu();
	try {
		spotFamousMenu2=scienceSpotService.findMenubyHumanId(Integer.parseInt(multipartRequest.getParameter("menuId")));
		
	} catch (BusinessException e) {
		sendForError(e, response);
		return null;
	}
	List imgList=new ArrayList();
	if(!(spotFamousMenu2.getMenuImages()==null))
	{
		JSONObject j=JSONObject.fromObject(spotFamousMenu2.getMenuImages());
		imgList=JSONArray.fromObject(j.get("menuImages"));
	}
	List urlList = new ArrayList();
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
	
	
	
	
	JSONObject imagesUrl = new JSONObject();
	imagesUrl.put("menuImages", JSONArray.fromObject(urlList));
	RlSpotFamousMenu spotFamousMenu=new RlSpotFamousMenu();
	spotFamousMenu.setMenuDesc(multipartRequest.getParameter("menuDesc"));
	spotFamousMenu.setMenuId(Integer.parseInt(multipartRequest.getParameter("menuId")));
	spotFamousMenu.setMenuImages(imagesUrl.toString());
	spotFamousMenu.setMenuName(multipartRequest.getParameter("menuName"));
	spotFamousMenu.setSpotId(Integer.parseInt(multipartRequest.getParameter("spotId")));
	try {
		scienceSpotService.updateMenu(spotFamousMenu);
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

@RequestMapping("/scienceSpotController/updateSpecialty.do")
public String updateSpecialty(HttpServletRequest request,
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
    String path1="scienceSpot";
    String path2="images";
    String imgurl1 = null;
	String imgurl2 = null;
	String imgurl3 = null;
	RlSpotSpecialty specialty2=new RlSpotSpecialty();
	try {
		specialty2=scienceSpotService.findSpecialtybyId(Integer.parseInt(multipartRequest.getParameter("specialtyId")));
		
	} catch (BusinessException e) {
		sendForError(e, response);
		return null;
	}
	List imgList=new ArrayList();
	if(!(specialty2.getImages()==null))
	{
		JSONObject j=JSONObject.fromObject(specialty2.getImages());
		imgList=JSONArray.fromObject(j.get("images"));
	}
	List urlList = new ArrayList();
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
	
	JSONObject imagesUrl = new JSONObject();
	imagesUrl.put("images", JSONArray.fromObject(urlList));
	RlSpotSpecialty specialty=new RlSpotSpecialty();
	specialty.setImages(imagesUrl.toString());
	specialty.setSpecialtyDesc(multipartRequest.getParameter("specialtyDesc"));
	specialty.setSpecialtyId(Integer.parseInt(multipartRequest.getParameter("specialtyId")));
	specialty.setSpecialtyName(multipartRequest.getParameter("specialtyName"));
	specialty.setSpotId(Integer.parseInt(multipartRequest.getParameter("spotId")));
	try {
		scienceSpotService.updateSpecialty(specialty);
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


@RequestMapping("/scienceSpotController/updateHuman.do")
public String updateHuman(HttpServletRequest request,
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
    String path1="scienceSpot";
    String path2="images";
    String imgurl1 = null;
	String imgurl2 = null;
	String imgurl3 = null;
	RlSpotFamousHuman spotFamousHuman2=new RlSpotFamousHuman();
	try {
		spotFamousHuman2=scienceSpotService.findhumanbyHumanId(Integer.parseInt(multipartRequest.getParameter("humanId")));
		
	} catch (BusinessException e) {
		sendForError(e, response);
		return null;
	}
	List imgList=new ArrayList();
	if(!(spotFamousHuman2.getImages()==null))
	{
		JSONObject j=JSONObject.fromObject(spotFamousHuman2.getImages());
		imgList=JSONArray.fromObject(j.get("images"));
	}
	List urlList = new ArrayList();
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
	JSONObject imagesUrl = new JSONObject();
	imagesUrl.put("images", JSONArray.fromObject(urlList));
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	RlSpotFamousHuman spotFamousHuman=new RlSpotFamousHuman();
	try {
		spotFamousHuman.setBirthday(df.parse(multipartRequest.getParameter("birthday")));
	} catch (ParseException e1) {
		
		e1.printStackTrace();
	}
	try {
		spotFamousHuman.setDieDay(df.parse(multipartRequest.getParameter("dieDay")));
	} catch (ParseException e1) {
		
		e1.printStackTrace();
	}
	spotFamousHuman.setHumanDesc(multipartRequest.getParameter("humanDesc"));
	spotFamousHuman.setHumanId(Integer.parseInt(multipartRequest.getParameter("humanId")));
	spotFamousHuman.setHumanName(multipartRequest.getParameter("humanName"));
	spotFamousHuman.setImages(imagesUrl.toString());
	spotFamousHuman.setSpotId(Integer.parseInt(multipartRequest.getParameter("spotId")));
	try {
		scienceSpotService.updateHuman(spotFamousHuman);
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



@RequestMapping("/scienceSpotController/drophumanbyhumanId.do")
public String drophumanbyhumanId(HttpServletRequest request,
		HttpServletResponse response) {	
	JSONObject req = getJsonObjectReq(request);
				
	try {
		scienceSpotService.drophumanbyhumanId(req.getInt("humanId"));
		sendString("删除成功", response);
		
	} catch (BusinessException e) {
		sendForError(e, response);
		return null;
	}
	return null;
}


@RequestMapping("/scienceSpotController/deleteSpecialtybyId.do")
public String deleteSpecialtybyId(HttpServletRequest request,
		HttpServletResponse response) {	
	JSONObject req = getJsonObjectReq(request);
				
	try {
		scienceSpotService.deleteSpecialtybyId(req.getInt("specialtyId"));
		sendString("删除成功", response);
		
	} catch (BusinessException e) {
		sendForError(e, response);
		return null;
	}

	return null;
}


@RequestMapping("/scienceSpotController/deleteById.do")
public String deleteById(HttpServletRequest request,
		HttpServletResponse response) {	
	JSONObject req = getJsonObjectReq(request);
				
	try {
		scienceSpotService.deleteById(req.getInt("menuId"));
		sendString("删除成功", response);
		
	} catch (BusinessException e) {
		sendForError(e, response);
		return null;
	}
	
	return null;
}

@RequestMapping("/scienceSpotController/saveHuman.do")
public String saveHuman(HttpServletRequest request,
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
    String path1="scienceSpot";
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
	
	JSONObject imagesUrl = new JSONObject();
	imagesUrl.put("images", JSONArray.fromObject(urlList));
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	RlSpotFamousHuman spotFamousHuman=new RlSpotFamousHuman();
	try {
		spotFamousHuman.setBirthday(df.parse(multipartRequest.getParameter("birthday").replace("/", "-")));
	} catch (ParseException e1) {
		
		e1.printStackTrace();
	}
	try {
		spotFamousHuman.setDieDay(df.parse(multipartRequest.getParameter("dieDay").replace("/", "-")));
	} catch (ParseException e1) {
		
		e1.printStackTrace();
	}
	spotFamousHuman.setHumanDesc(multipartRequest.getParameter("humanDesc"));
	spotFamousHuman.setHumanName(multipartRequest.getParameter("humanName"));
	spotFamousHuman.setImages(imagesUrl.toString());
	spotFamousHuman.setSpotId(Integer.parseInt(multipartRequest.getParameter("spotId")));
	try {
		scienceSpotService.saveHuman(spotFamousHuman);
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

@RequestMapping("/scienceSpotController/saveMenu.do")
public String saveMenu(HttpServletRequest request,
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
    String path1="scienceSpot";
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
	JSONObject imagesUrl = new JSONObject();
	imagesUrl.put("menuImages", JSONArray.fromObject(urlList));
	RlSpotFamousMenu spotFamousMenu=new RlSpotFamousMenu();
	spotFamousMenu.setMenuDesc(multipartRequest.getParameter("menuDesc"));
	spotFamousMenu.setMenuImages(imagesUrl.toString());
	spotFamousMenu.setMenuName(multipartRequest.getParameter("menuName"));
	spotFamousMenu.setSpotId(Integer.parseInt(multipartRequest.getParameter("spotId")));
	try {
		scienceSpotService.saveMenu(spotFamousMenu);
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

@RequestMapping("/scienceSpotController/saveSpecialty.do")
public String saveSpecialty(HttpServletRequest request,
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
    String path1="scienceSpot";
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
	JSONObject imagesUrl = new JSONObject();
	imagesUrl.put("images", JSONArray.fromObject(urlList));
	RlSpotSpecialty specialty=new RlSpotSpecialty();
	specialty.setImages(imagesUrl.toString());
	specialty.setSpecialtyDesc(multipartRequest.getParameter("specialtyDesc"));
	specialty.setSpecialtyName(multipartRequest.getParameter("specialtyName"));
	specialty.setSpotId(Integer.parseInt(multipartRequest.getParameter("spotId")));
	try {
		scienceSpotService.saveSpecialty(specialty);
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

@RequestMapping("/scienceSpotController/updateCustombyspotId.do")
public String updateCustombyspotId(HttpServletRequest request,
		HttpServletResponse response) {	
	JSONObject req = getJsonObjectReq(request);
	SysUser u = (SysUser) request.getSession().getAttribute("user");
					
	try {
		scienceSpotService.updateCustombyspotId(req.getInt("spotId"), req.getString("custom"));
		sendString("修改成功", response);
		
	} catch (BusinessException e) {
		sendForError(e, response);
		return null;
	}
	
	return null;
}


@RequestMapping("/scienceSpotController/updateSpotDescbyId.do")
public String updateSpotDescbyId(HttpServletRequest request,
		HttpServletResponse response) {	
	JSONObject req = getJsonObjectReq(request);
					
	try {
		scienceSpotService.updateSpotDescbyId(req.getInt("spotId"), req.getString("spotDesc"));
		sendString("修改成功", response);
		
	} catch (BusinessException e) {
		sendForError(e, response);
		return null;
	}
	
	return null;
}

@RequestMapping("/scienceSpotController/updateimageBySpotId.do")
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
	SysScenicSpot scenicSpot=new SysScenicSpot();
	try {
		scenicSpot=scienceSpotService.findById(Integer.parseInt(multipartRequest.getParameter("spotId")));
		
	} catch (BusinessException e) {
		sendForError(e, response);
		return null;
	}
	List imgList=new ArrayList();
	if(!(scenicSpot.getImageUrl()==null))
	{
		JSONObject j=JSONObject.fromObject(scenicSpot.getImageUrl());
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
    }
    else{
    	if(imgList.size()>2){
    	urlList.add(imgList.get(2).toString());
    	}
    }
    if(!(imgFile4.getOriginalFilename() ==null || "".equals(imgFile4.getOriginalFilename()))) {  
    	File file4 = this.getFile(imgFile4,path1,path2,fileTypes,path);          	              
    	imgurl4=file4.getAbsolutePath().replace(request.getSession().getServletContext().getRealPath("/"), "");        	
    	imgurl4=imgurl4.replace("\\", "/");
    	urlList.add(imgurl4);
    }
    else{
    	if(imgList.size()>3){
    	urlList.add(imgList.get(3).toString());
    	}
    }
    if(!(imgFile5.getOriginalFilename() ==null || "".equals(imgFile5.getOriginalFilename()))) {  
    	File file5 = this.getFile(imgFile5,path1,path2,fileTypes,path);          	              
    	imgurl5=file5.getAbsolutePath().replace(request.getSession().getServletContext().getRealPath("/"), "");        	
    	imgurl5=imgurl5.replace("\\", "/");
    	urlList.add(imgurl5);
    }
    else{
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
		scienceSpotService.updateimageBySpotId(Integer.parseInt(multipartRequest.getParameter("spotId")), imagesUrl.toString());
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

@RequestMapping("/scienceSpotController/updateTravleRoutebyspotId.do")
public String updateTravleRoutebyspotId(HttpServletRequest request,
		HttpServletResponse response) {	
	JSONObject req = getJsonObjectReq(request);
				
	try {
		scienceSpotService.updateTravleRoutebyspotId(req.getInt("spotId"), req.getString("travleRoute"));
		sendString("修改成功", response);
		
	} catch (BusinessException e) {
		sendForError(e, response);
		return null;
	}
	
	return null;
}

@RequestMapping("/scienceSpotController/saveSpotBasic.do")
public String saveSpotBasic(HttpServletRequest request,
		HttpServletResponse response) {	
	JSONObject req = getJsonObjectReq(request);
	
		SysScenicSpot scenicSpot=new SysScenicSpot();
		scenicSpot.setProvinceId(req.getInt("provinceId"));
		scenicSpot.setHotLevel(req.getInt("HotLevel"));
		scenicSpot.setLocationDesc(req.getString("LocationDesc"));
		scenicSpot.setLocationX(req.getString("LocationX"));
		scenicSpot.setLocationY(req.getString("LocationY"));
		scenicSpot.setSpotName(req.getString("SpotName"));
	try {
		
		scienceSpotService.saveSpotBasic(scenicSpot);
		sendString("修改成功", response);
		
	} catch (BusinessException e) {
		sendForError(e, response);
		return null;
	}
	
	return null;
}


@RequestMapping("/scienceSpotController/forbitScenicSpot.do")
public String forbitScenicSpot(HttpServletRequest request,
		HttpServletResponse response) {	
	JSONObject req = getJsonObjectReq(request);
					
	try {
		scienceSpotService.forbitScenicSpot(req.getInt("spotId"));
		sendString("修改成功", response);		
	} catch (BusinessException e) {
		sendForError(e, response);
		return null;
	}
	
	return null;
}


@RequestMapping("/scienceSpotController/unForbitScenicSpot.do")
public String unForbitScenicSpot(HttpServletRequest request,
		HttpServletResponse response) {	
	JSONObject req = getJsonObjectReq(request);
					
	try {
		scienceSpotService.unForbitScenicSpot(req.getInt("spotId"));
		sendString("修改成功", response);		
	} catch (BusinessException e) {
		sendForError(e, response);
		return null;
	}
	
	return null;
}

@RequestMapping("/scienceSpotController/findall.do")
public String findall(HttpServletRequest request,
		HttpServletResponse response) {	
					
	try {
		List province=provinceService.findAllProvince();
		List scienceSpot=scienceSpotService.findAllScenicSpot();
		List spot=spotService.findAllSpots();
		JSONObject jo=new JSONObject();
		jo.put("state", "1");
		jo.put("province", JSONArray.fromObject(province, getDefaultJsonConfig()));
		jo.put("scienceSpot", JSONArray.fromObject(scienceSpot, getDefaultJsonConfig()));
		jo.put("spot", JSONArray.fromObject(spot, getDefaultJsonConfig()));
		sendForStr(response, jo);
	} catch (BusinessException e) {
		sendForError(e, response);
		return null;
	}
	
	return null;
}

@RequestMapping("/scienceSpotController/findallProvinces.do")
public String findallProvinces(HttpServletRequest request,
		HttpServletResponse response) {	
					
	try {
		List province=provinceService.findAllProvince();
		sendForList(province, response, null);
	} catch (BusinessException e) {
		sendForError(e, response);
		return null;
	}
	
	return null;
}
}
