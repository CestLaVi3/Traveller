package com.chinasoft.controller;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import com.chinasoft.ConstantDataManager;
import com.chinasoft.common.utils.JsonDateFormate;
import com.chinasoft.common.utils.Exception.BusinessException;
import com.chinasoft.common.utils.log.HollyinfoLog;
import com.chinasoft.common.utils.log.HollyinfoLogger;

/**
 * 校验json格式文件的超类
 * @date 2014-8-17 14:33:04
 * @author 李伟
 */
public  class BaseJsonController extends MultiActionController {
    private static final HollyinfoLog LOG = HollyinfoLogger.getLog(BaseJsonController.class);

    /**
     * 返回系统时间的毫秒值
     * @return
     */
    protected Long getTime() {
        return new Long(ConstantDataManager.getDataBaseServerTimeMIllis());
    }

    /**
     * 获取当天的0点0分0秒对应的时间对应的毫秒数
     * @return
     */
    protected Long getDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(ConstantDataManager.getDataBaseServerTimeMIllis());
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Long(calendar.getTimeInMillis());
    }
    public JsonConfig getDefaultJsonConfig() {
    	 JsonConfig jsonConfig = new JsonConfig();
         jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateFormate());
         return jsonConfig;
    }
    /**
     * 传递对象信息格式
     * {state:1,value:[{"":"","":""}]}
     * @return
     */
    protected void sendForList(List obs,HttpServletResponse response,  String[] excludes) {
        JSONObject ret = new JSONObject();
        ret.put("state", "1");
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateFormate());
        if (excludes != null) {
        	jsonConfig.setExcludes(excludes);
        }
        JSONArray jo = JSONArray.fromObject(obs,jsonConfig);
        ret.put("value", jo);
        response.setCharacterEncoding("utf-8");
        try {
			response.getWriter().write(ret.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    /**
     * 传递对象信息格式
     * {state:1,value:{"":"","":""}}
     * @return
     */
    protected void sendForObject(Object ob,HttpServletResponse response , String[] excludes) {
    	JSONObject ret = new JSONObject();
        ret.put("state", "1");
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateFormate());
        if (excludes != null) {
        	jsonConfig.setExcludes(excludes);
        }
        JSONObject jo = JSONObject.fromObject(ob,jsonConfig);
        ret.put("value", jo);
        response.setCharacterEncoding("utf-8");
        try {
			response.getWriter().write(ret.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    protected void sendString(String ob,HttpServletResponse response) {
    	JSONObject ret = new JSONObject();
        ret.put("state", "1");       
        ret.put("value", ob);
        response.setCharacterEncoding("utf-8");
        try {
			response.getWriter().write(ret.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    protected void sendErrorString(String ob,HttpServletResponse response) {
    	JSONObject ret = new JSONObject();
        ret.put("state", "0");       
        ret.put("value", ob);
        response.setCharacterEncoding("utf-8");
        try {
			response.getWriter().write(ret.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    /**
     * 发送错误信息
     * {state:1,value:{"":"","":""}}
     * @return
     */
    protected void sendForError(BusinessException businessException,HttpServletResponse response) {
    	JSONObject ret = new JSONObject();
        ret.put("state", "0");
        ret.put("value", businessException.getMessage());
        response.setCharacterEncoding("utf-8");
        try {
			response.getWriter().write(ret.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    /**
     * 发送错误信息
     * {state:1,value:{"":"","":""}}
     * @return
     */
    protected void sendForStr(HttpServletResponse response,JSONObject jsonObject) {
    	JSONObject ret = new JSONObject();
        response.setCharacterEncoding("utf-8");
        try {
			response.getWriter().write(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    /**
     * 传递对象信息格式
     * {state:1,value:{"":"","":""}}
     * @return
     */
    protected JSONObject getJsonObjectReq(HttpServletRequest request) {
        String reqStr = request.getParameter("paramter");
        JsonConfig jsonConfig = new JsonConfig();
    	return JSONObject.fromObject(reqStr);
    }
    
    protected File getFile(MultipartFile imgFile,String typeName,String brandName,List fileTypes,String path) {  
        String fileName = imgFile.getOriginalFilename();  
        //获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名  
         String ext = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());  
         //对扩展名进行小写转换  
         ext = ext.toLowerCase();  
           
         File file = null; 
         Date date=new Date();
         long t=date.getTime();
         fileName=t+".jpg";
         if(fileTypes.contains(ext)) {                      //如果扩展名属于允许上传的类型，则创建文件  
             file = this.creatFolder(typeName, brandName, fileName,path);  
             try {  
                imgFile.transferTo(file);                   //保存上传的文件  
            } catch (IllegalStateException e) {  
                e.printStackTrace();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
         }  
         return file;  
    }  
    protected File creatFolder(String typeName,String brandName,String fileName,String path){  
         File file = null;  
         typeName = typeName.replaceAll("/", "");               //去掉"/"  
         typeName = typeName.replaceAll(" ", "");               //替换半角空格  
         typeName = typeName.replaceAll(" ", "");               //替换全角空格            
         brandName = brandName.replaceAll("/", "");             //去掉"/"  
         brandName = brandName.replaceAll(" ", "");             //替换半角空格  
         brandName = brandName.replaceAll(" ", "");  //替换全角空格           
         File firstFolder = new File(path+typeName);         //一级文件夹  
         if(firstFolder.exists()) {           	 //如果一级文件夹存在，则检测二级文件夹  
             File secondFolder = new File(firstFolder,brandName);  
             if(secondFolder.exists()) {                        //如果二级文件夹也存在，则创建文件  
                 file = new File(secondFolder,fileName);  
             }else {                                            //如果二级文件夹不存在，则创建二级文件夹  
                 secondFolder.mkdir();  
                 file = new File(secondFolder,fileName);        //创建完二级文件夹后，再合建文件  
             }  
         }else {                                                //如果一级不存在，则创建一级文件夹  
             firstFolder.mkdir();  
             File secondFolder = new File(firstFolder,brandName);  
             if(secondFolder.exists()) {                        //如果二级文件夹也存在，则创建文件  
                 file = new File(secondFolder,fileName);  
             }else {                                            //如果二级文件夹不存在，则创建二级文件夹  
                 secondFolder.mkdir();  
                 file = new File(secondFolder,fileName);  
             }  
         }  
         return file;  
    }

}