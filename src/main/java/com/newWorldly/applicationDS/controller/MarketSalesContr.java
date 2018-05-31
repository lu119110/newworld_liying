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
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.newWorldly.applicationDS.business.DayTimeSalesBarChartBu;
import com.newWorldly.applicationDS.business.MarketSalesBu;
import com.newWorldly.applicationDS.business.MemberSalesBu;

//import com.newWorldly.applicationDS.business.DayTimeSalesBarChartBu;



/**
 * @author Administrator
 * 
 */
@RestController
public class MarketSalesContr {
	private Logger logger;
	@Autowired
	private MarketSalesBu mbu;



	public MarketSalesContr() {
		logger = Logger.getLogger(MarketSalesContr.class);

	}
	
	@RequestMapping("/DS/MarketDepReport/getAllDepts.do")
	public List<Map> getAllDepts(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		List reList=mbu.getAllDept();
		return reList;
	}
	@RequestMapping("/DS/MarketDepReport/getAllStyles.do")
	public List<Map> getAllStyles(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		List reList=mbu.getAllStyle();
		return reList;
	}
	@RequestMapping("/DS/MarketDepReport/getAllSesson.do")
	public List<Map> getAllSesson(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		List reList=mbu.getseason();
		return reList;
	}
	@RequestMapping("/DS/MarketDepReport/getBrand.do")
	public List<Map> getBrand(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String dept=request.getParameter("dept");
		String week=request.getParameter("week");
		String seasoncode=request.getParameter("season");
		
		List reList=mbu.getBrand(dept,week,seasoncode);
		return reList;
	}
	@RequestMapping("/DS/MarketDepReport/saveSales.do")
	public Map saveSales(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String reqStr=request.getParameter("reqStr");
		ObjectMapper mapper = new ObjectMapper();
		JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, Map.class);  
		List<Map> lst =  (List<Map>)mapper.readValue(reqStr, javaType);  
		mbu.saveSales(lst);
		Map retMap=new HashMap();
		retMap.put("error", "0");
		
	
		return retMap;
	}
	
	
}
