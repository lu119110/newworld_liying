/**
 * 
 */
package com.newWorldly.applicationDS.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.newWorldly.applicationDS.bo.UserBo;
import com.newWorldly.applicationDS.business.DayTimeSalesBarChartBu;

import com.newWorldly.utils.PropertiesUtil;

//import com.newWorldly.applicationDS.business.DayTimeSalesBarChartBu;



/**
 * @author Administrator
 * 
 */
@RestController
public class DayTimeSalesBarChartContr {
	private Logger logger;
	@Autowired
	private DayTimeSalesBarChartBu bu;

	public DayTimeSalesBarChartBu getBu() {
		return bu;
	}
	public void setBu(DayTimeSalesBarChartBu bu) {
		this.bu = bu;
	}
	public DayTimeSalesBarChartContr() {
		logger = Logger.getLogger(DayTimeSalesBarChartContr.class);

	}
	@RequestMapping("/Login.do")
	public Map login(HttpServletRequest request)
			throws Exception {
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		Map retMap=new HashMap();
		String code;
		String msg;
		if(username==null || pwd==null || "".equals(username.trim())||"".equals(pwd.trim())) {
			code="1";
			msg="用户名密码空";
			retMap.put("code", code);
			retMap.put("msg", msg);
			return retMap;
		}
		PropertiesUtil pu=PropertiesUtil.getInstance();
		String usrInfo=pu.getValue("users", "user."+username);
		if(usrInfo==null || "".equals(usrInfo.trim())) {
			code="2";
			msg="用户名错误";
			retMap.put("code", code);
			retMap.put("msg", msg);
			return retMap;
		}
		
		String[] arryUserInfo=usrInfo.split("\\|");
		UserBo userBo=new UserBo();
		userBo.setUserName(arryUserInfo[0]);
		userBo.setPwd(arryUserInfo[1]);
		userBo.setRole(arryUserInfo[2]);
		//查找菜单
		
		String strMenus=pu.getValue("users", "role.menuids."+userBo.getRole());
		
		List listMenu=new ArrayList(Arrays.asList(strMenus.split(",")));
		userBo.setMenuList(listMenu);
		
		//
		
		if(!pwd.equals(userBo.getPwd())) {
			code="3";
			msg="密码错误";
			retMap.put("code", code);
			retMap.put("msg", msg);
			return retMap;
		}
		code="0";
		msg="";
		retMap.put("code", code);
		retMap.put("msg", msg);
		request.getSession().setAttribute("user", userBo);
		return retMap;

	}

	@RequestMapping("/DS/DayTimeSales/getDSDayTimeSalesChart.do")
	public List<Map> getDSDayTimeSalesChart(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter pw = null;
		String strDay=request.getParameter("txdate");
		//DayTimeSalesBarChartBu bu=new DayTimeSalesBarChartBu();

		List reList=bu.getDayTimeSales(strDay);
		/*List reList=new ArrayList();
        Random random = new Random();

		Map map=new HashMap();
		map.put("txhour", "9");
		map.put("amtsold", Integer.toString(random.nextInt(200*10000)));
		reList.add(map);
		map=new HashMap();
		map.put("txhour", "10");
		map.put("amtsold", Integer.toString(random.nextInt(200*10000)));
		reList.add(map);
		map=new HashMap();
		map.put("txhour", "11");
		map.put("amtsold", Integer.toString(random.nextInt(200*10000)));
		reList.add(map);
		map=new HashMap();
		map.put("txhour", "12");
		map.put("amtsold", Integer.toString(random.nextInt(200*10000)));
		reList.add(map);
		map=new HashMap();
		map.put("txhour", "13");
		map.put("amtsold", Integer.toString(random.nextInt(200*10000)));
		reList.add(map);
		map=new HashMap();
		map.put("txhour", "14");
		map.put("amtsold", Integer.toString(random.nextInt(200*10000)));
		reList.add(map);
		map=new HashMap();
		map.put("txhour", "15");
		map.put("amtsold", Integer.toString(random.nextInt(200*10000)));
		reList.add(map);
		map=new HashMap();
		map.put("txhour", "16");
		map.put("amtsold", Integer.toString(random.nextInt(200*10000)));
		reList.add(map);
		map=new HashMap();
		map.put("txhour", "17");
		map.put("amtsold", Integer.toString(random.nextInt(200*10000)));
		reList.add(map);
		map=new HashMap();
		map.put("txhour", "18");
		map.put("amtsold", Integer.toString(random.nextInt(200*10000)));
		reList.add(map);
		map=new HashMap();
		map.put("txhour", "19");
		map.put("amtsold", Integer.toString(random.nextInt(200*10000)));
		reList.add(map);
		map=new HashMap();
		map.put("txhour", "20");
		map.put("amtsold", Integer.toString(random.nextInt(200*10000)));
		reList.add(map);*/
		//JSONArray jsonArray = JSONArray.fromObject( reList );
		//logger.debug(jsonArray.toString());
		//pw = response.getWriter();
		//pw.write(jsonArray.toString());
		//pw.close();
		//return null;
		return reList;
	}
	@RequestMapping("/getUserMenus.do")
	public List<Map> getUserMenus(
			
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PropertiesUtil pu=PropertiesUtil.getInstance();
		UserBo user=(UserBo)request.getSession().getAttribute("user");
		//String[] menuids=pu.getValue("users", "menu.ids").split(",");
		//String[] menuids=(String[])user.getMenuList().toArray();
		List menuids=user.getMenuList();
		List menuList=new ArrayList();
		List urlList=new ArrayList();
		Map mMap=null;
		String strValue=null;
		String[] strArry=null;
		Iterator it=menuids.iterator();
		while(it.hasNext()) {
			String strKey=it.next().toString();
			strValue=pu.getValue("users", "menu."+strKey);
			mMap=new HashMap();
			strArry=strValue.split("\\|");
			mMap.put("menuname",strArry[0]);
			mMap.put("url",strArry[1]);
			menuList.add(mMap);
			urlList.add(strArry[1]);
		}
		user.setUrlList(urlList);
		return menuList;
	}
/*
	public ActionForward analysisTransByDate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter pw = null;
		try {
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");

			String[] splitenddate = endDate.split("-");
			String starttxdate = startDate + " 00:00:00";
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			c.set(Integer.valueOf(splitenddate[0]),
					Integer.valueOf(splitenddate[1]) - 1,
					Integer.valueOf(splitenddate[2]));
			// c.set(Calendar.YEAR, Integer.valueOf(splitenddate[0]));
			// c.set(Calendar.MONTH, Integer.valueOf(splitenddate[1])-1);
			// c.set(Calendar.DAY_OF_MONTH, Integer.valueOf(splitenddate[2]));
			c.add(Calendar.DAY_OF_YEAR, 1);
			String endYear = Integer.toString(c.get(Calendar.YEAR));
			String endMonth = Integer.toString(c.get(Calendar.MONTH) + 1);
			String endDay = Integer.toString(c.get(Calendar.DAY_OF_MONTH));
			while (endMonth.length() < 2) {
				endMonth = "0" + endMonth;
			}
			while (endDay.length() < 2) {
				endDay = "0" + endDay;
			}
			String endtxdate = endYear + "-" + endMonth + "-" + endDay
					+ " 00:00:00";

			TransAnalysisDsBusi tBu = new TransAnalysisDsBusi(null);
			tBu.handleTransByDate(starttxdate, endtxdate);
			pw = response.getWriter();
			pw.write("<script language=\"javascript\">");
			pw.write("parent.addok();");
			pw.write("</script >");
			pw.close();
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			pw = response.getWriter();
			pw.write("<script language=\"javascript\">");
			pw.write("parent.adderror();");
			pw.write("</script >");
			pw.close();
		}
		return null;
	}
	
	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		if (!"tulip".equals(username)) {
			request.setAttribute("errormsg", "�û�������");
			return mapping.findForward("error");
		}
		if (!"8888".equals(pwd)) {
			request.setAttribute("errormsg", "�������");
			return mapping.findForward("error");
		}
		request.getSession().setAttribute("user", username);
		return mapping.findForward("next");
	}
*/
	
}
