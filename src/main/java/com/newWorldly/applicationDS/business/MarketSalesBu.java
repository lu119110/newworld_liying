package com.newWorldly.applicationDS.business;

import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.newWorldly.applicationDS.controller.MemberSalesContr;
import com.newWorldly.utils.SqlMapConfig;
@Component
@Scope("prototype")
public class MarketSalesBu {
	private Logger logger = Logger.getLogger(MemberSalesContr.class);;
	 SqlMapClient sqlMap=SqlMapConfig.getSqlMapperLySource();

	 @Autowired
	 
	public  List getAllDept() throws SQLException{
		List retList=null;
		Map paramMap=new HashMap();
		retList=sqlMap.queryForList("MarketSales.getDept", paramMap);
		return retList;
	}
	 public  List getAllStyle() throws SQLException{
			List retList=null;
			Map paramMap=new HashMap();
			retList=sqlMap.queryForList("MarketSales.getStyle", paramMap);
			return retList;
		}
	 public  List getseason() throws SQLException{
			List retList=null;
			Map paramMap=new HashMap();
			retList=sqlMap.queryForList("MarketSales.getseason", paramMap);
			return retList;
		}
	 public List getBrand(String dept) throws SQLException{
		 List retList=null;
			Map paramMap=new HashMap();
			paramMap.put("dept",dept);
			
			retList=sqlMap.queryForList("MarketSales.getBrand", paramMap);
			return retList;
	 }
}