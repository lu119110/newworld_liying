package com.newWorldly.applicationDS.aspect;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jcabi.log.Logger;
import com.newWorldly.applicationDS.bo.UserBo;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import net.sf.json.JSON;
@Component  
@Aspect
@Configuration
public class ContrllorInterceptor {
	private static final String execution_str_01 = "execution(* com.newWorldly.applicationDS.controller.*.*(..))";//controller包下任意方法  
    private static final String execution_str_02 = "execution(* com.newWorldly.applicationDS.controller..*.*(..))";//controller包或子包下任意方法  
    private static final String execution_str_03 = "@annotation(org.springframework.web.bind.annotation.RequestMapping)";//带RequestMapping注解的方法  
    private static final String execution_str_04 = "execution(* com.newWorldly.applicationDS.controller..*(..))";//controller包或子包下任意方法  


    //@Pointcut(execution_str_01)  
    private void controllerCut() {
    	
    	
    }  
  
    //@Before(value = "controllerCut()")  
   // @Around(value = "controllerCut()") 
    public Object cutBefore(ProceedingJoinPoint pjp) throws Throwable {  
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();  
        HttpServletRequest httpServletRequest = requestAttributes.getRequest();  
        HttpServletResponse resonse=requestAttributes.getResponse();
        Logger.info(httpServletRequest, "AOP==>" + httpServletRequest.getRequestURL());
        Logger.info(httpServletRequest,"AOP==>" + pjp.getSignature());  
        //如果是login和getmenu
         String reqUri=httpServletRequest.getRequestURI();
         String[] uri=reqUri.split("/");
         if(uri[uri.length-1]==null || "".equals(uri[uri.length-1]) || "NewWorld_LIYING".equals(uri[uri.length-1]) || uri[uri.length-1].equals("Login.do")) {
        	 return  pjp.proceed();//返回目标方法的返回
         }
        //"执行目标方法之后，模拟结束事务..."
        UserBo user=(UserBo)httpServletRequest.getSession().getAttribute("user");
        resonse.setCharacterEncoding("UTF-8");  
        resonse.setContentType("application/json; charset=utf-8");  
        OutputStream out=resonse.getOutputStream();
        PrintWriter pw=new PrintWriter(out);
        if(user==null ) {
        	//Map map=new HashMap();
        	//map.put("error", "-1");
        	//map.put("msg", "登录超时请重新登录");
        	String retStr="{\"error\":\"-1\",\"msg\":\"登录超时请重新登录\"}";
        	pw.write(retStr);
        	pw.flush();
        	pw.close();
        	return null;
        	
        }
        if(uri[uri.length-1].equals("getUserMenus.do")) {
        	return  pjp.proceed();
        }
        if(!isLogin(user.getUrlList(),reqUri)) {
        	String retStr="{\"error\":\"-1\",\"msg\":\"没有权限\"}";
        	pw.write(retStr);
        	pw.flush();
        	pw.close();
        	return null;
        }
        //java.util.List urls=user.getUrlList();
        
      
        return  pjp.proceed();
}
    public boolean isLogin(java.util.List<String> urls,String reqUri) {
    	Iterator it =urls.iterator();
    	String urlstr;
    	String[] uris=reqUri.split("/");
    	String reqUrlSpace=uris[uris.length-2];
    	String[] urlarray;
    	while(it.hasNext()) {
    		urlstr=(String) it.next();
    		urlarray=urlstr.split("/");
    		if(reqUrlSpace.equals(urlarray[urlarray.length-2])) {
    			return true;
    		}
    	}
    	return false;
    }
}
