package com.chinasoft.common.utils.aop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;

import com.chinasoft.controller.BaseJsonController;
import com.chinasoft.model.manager.SysManager;
import com.chinasoft.model.user.SysUser;

public class TestAround extends BaseJsonController{		
	public void doAround(ProceedingJoinPoint pj){
			HttpServletResponse response=(HttpServletResponse) pj.getArgs()[1];
			HttpServletRequest request=(HttpServletRequest) pj.getArgs()[0];
			AopJson aopJson=new AopJson();
			
			List aList=aopJson.getJson();	
			int k=0;
			for(int i=0;i<aList.size();i++){				
				if(aList.get(i).equals(pj.getSignature().getName())){	
					k=1;
				}
				
			}
			if(k==1){				
				SysUser user=findUser(request, response);
				SysManager manager=findManager(request, response);
				if(user!=null||manager!=null){
				try {
					Object ob=pj.proceed();
				} catch (Throwable e) {
					sendErrorString("执行异常程序", response);
				}
				}
				else{
					sendErrorString("没有登录", response);
				}
			}else{
				try {
					Object ob=pj.proceed();
				} catch (Throwable e) {
					sendErrorString("执行异常程序", response);
				}
			}
			
		
	}
	
	
	public SysUser findUser(HttpServletRequest request,
			HttpServletResponse response) {
				SysUser user=(SysUser)request.getSession().getAttribute("user");				
				return user;
	}
	public SysManager findManager(HttpServletRequest request,
			HttpServletResponse response) {
				SysManager manager=(SysManager)request.getSession().getAttribute("manager");
				return manager;
	}
}

