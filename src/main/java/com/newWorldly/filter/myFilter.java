/**
 * 
 */
package com.newWorldly.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 *
 */
public class myFilter implements Filter {

	/**
	 * 
	 */
	public myFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest req, ServletResponse rsp,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest)req ;
		HttpServletResponse httpResponse = (HttpServletResponse)rsp ;
		String actionDoStr = httpRequest.getRequestURI() ;
	
		System.out.println("=========================="+actionDoStr);
		//to Logon action and LogOut action , continue linking
		if(actionDoStr.equals("/NewWorld_LIYING/Login.do")|| actionDoStr.equals("/NewWorld_LIYING/login.jsp")
				|| actionDoStr.equals("/NewWorld_LIYING/DS/DayClearPos/clearPosList.jsp")
				|| actionDoStr.equals("/NewWorld_LIYING/DS/DayClearPos/clearPosListRet.jsp")
				|| actionDoStr.equals("/NewWorld_LIYING/SM/DayClearPos/clearPosListRet.jsp")
				|| actionDoStr.equals("/NewWorld_LIYING/SM/DayClearPos/clearPosList.jsp")
				|| actionDoStr.equals("/NewWorld_LIYING/clearPosAll_sm.do")
				|| actionDoStr.equals("/NewWorld_LIYING/clearPOSByTillid.do")
				|| actionDoStr.equals("/NewWorld_LIYING/clearPosAll.do")
				|| actionDoStr.equals("/NewWorld_LIYING/clearPOSByTillid_sm.do")){			
			chain.doFilter(req, rsp) ;
			return ;
		}
		if(httpRequest.getSession().getAttribute("user")==null){
			Cookie[] cks= httpRequest.getCookies();
			boolean bun=false,bpd=false;
			if(cks!=null){
				for(int i=0 ; i< cks.length;i++){
					Cookie ck=cks[i];
					if("username".equals(ck.getName()) && "tulip".equals(ck.getValue())){
						bun=true;
					}
					if("pwd".equals(ck.getName()) && "8888".equals(ck.getValue())){
						bpd=true;
					}
					if(bun && bpd){
						httpRequest.getSession().setAttribute("user","tulip");
						chain.doFilter(req, rsp) ;
					}
				}
			}
			//httpResponse.sendRedirect("/NewWorld_LIYING/login.jsp") ;
			String pathStr="/NewWorld_LIYING";
			//String str="<script language=\"javascript\" src=\"/CRS_Web/js/common_util.js\"></script>";
			String str = "<script language='javascript' src='"
				+ pathStr	+ "/js/common_util.js'>"
				+"</script>";
			str+="<script language=\"javascript\">"+"goUrl('"+pathStr+"/login.jsp')";
			str+="</script>";
			rsp.setContentType("text/html; charset=utf-8");
			rsp.getWriter().print(str);	
			rsp.getWriter().close();
			return ;
		}
		chain.doFilter(req, rsp) ;
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
