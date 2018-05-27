/**
 * 
 */
package com.newWorldly.applicationDS.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.newWorldly.applicationDS.business.DayTimeSalesBarChartBu;
import com.newWorldly.applicationDS.business.MemberSalesBu;

//import com.newWorldly.applicationDS.business.DayTimeSalesBarChartBu;



/**
 * @author Administrator
 * 
 */
@RestController
public class MemberSalesContr {
	private Logger logger;
	@Autowired
	private MemberSalesBu mbu;
	public MemberSalesBu getMbu() {
		return mbu;
	}

	public void setMbu(MemberSalesBu mbu) {
		this.mbu = mbu;
	}

	public MemberSalesContr() {
		logger = Logger.getLogger(MemberSalesContr.class);

	}
	
	@RequestMapping("/DS/MemberSales/getMemberSales.do")
	public List<Map> getDSDayTimeSalesChart(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("+++++++getMemberSales.do++++++");
		String strStartDay=request.getParameter("startdate");
		String strEndDay=request.getParameter("enddate");
		String store=request.getParameter("store");
		String category=request.getParameter("category");
		String bonus=request.getParameter("bonus");
		logger.info("param "+strStartDay +"|"+strEndDay+"|"+store);
		List reList=mbu.getMemberSales(strStartDay, strEndDay,store,category,bonus);
		/*List reList=new ArrayList();
        Random random = new Random();

		Map map=new HashMap();
		map.put("vipcode", "110111111");
		map.put("amtsold", Integer.toString(random.nextInt(200*10000)));
		map.put("surname", "张三");
		map.put("telephone", "1234567");
		reList.add(map);
		
	    map=new HashMap();
		map.put("vipcode", "110111112");
		map.put("amtsold", Integer.toString(random.nextInt(200*10000)));
		map.put("surname", "李四");
		map.put("telephone", "12444455555");
		reList.add(map);
	    map=new HashMap();
		map.put("vipcode", "110111114");
		map.put("amtsold", Integer.toString(random.nextInt(200*10000)));
		map.put("surname", "王五");
		map.put("telephone", "455555333333");
		reList.add(map);
	    map=new HashMap();
		map.put("vipcode", "110111116");
		map.put("amtsold", Integer.toString(random.nextInt(200*10000)));
		map.put("surname", "赵6");
		map.put("telephone", "1244888885");
		reList.add(map);*/
		//JSONArray jsonArray = JSONArray.fromObject( reList );
		//logger.debug(jsonArray.toString());
		//pw = response.getWriter();
		//pw.write(jsonArray.toString());
		//pw.close();
		//return null;
		return reList;
	}
	@RequestMapping("/DS/MemberSales/exportExcel.do")
	
	public void  exportExcel(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String strStartDay=request.getParameter("startdate");
		String strEndDay=request.getParameter("enddate");
		String store=request.getParameter("store");
		String category=request.getParameter("category");
		String bonus=request.getParameter("bonus");
		logger.info("param "+strStartDay +"|"+strEndDay+"|"+store);
		List reList=mbu.getMemberSales(strStartDay, strEndDay,store,category,bonus);
		/*List reList=new ArrayList();
        Random random = new Random();

		Map map=new HashMap();
		map.put("vipcode", "110111111");
		map.put("amtsold", Integer.toString(random.nextInt(200*10000)));
		map.put("surname", "张三");
		map.put("telephone", "1234567");
		reList.add(map);
		
	    map=new HashMap();
		map.put("vipcode", "110111112");
		map.put("amtsold", Integer.toString(random.nextInt(200*10000)));
		map.put("surname", "李四");
		map.put("telephone", "12444455555");
		reList.add(map);
	    map=new HashMap();
		map.put("vipcode", "110111114");
		map.put("amtsold", Integer.toString(random.nextInt(200*10000)));
		map.put("surname", "王五");
		map.put("telephone", "455555333333");
		reList.add(map);
	    map=new HashMap();
		map.put("vipcode", "110111116");
		map.put("amtsold", Integer.toString(random.nextInt(200*10000)));
		map.put("surname", "赵6");
		map.put("telephone", "1244888885");
		reList.add(map);
		*/
		XSSFWorkbook wb=mbu.getMembersalesXls(reList);
		OutputStream outputStream = response.getOutputStream(); 
	    response.reset(); 
	    response.setContentType("application/vnd.ms-excel");    
	    response.setHeader("Content-disposition", "attachment;filename='"+java.net.URLEncoder.encode("会员消费查詢", "UTF-8")+strStartDay+"T"+strEndDay+".xlsx'");    

	    wb.write(outputStream);
	    outputStream.close();
		
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
