package com.newWorldly.applicationDS.business;

import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
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
	 public List getBrand(String dept,String week,String seasoncode) throws SQLException{
		 List retList=null;
			Map paramMap=new HashMap();
			paramMap.put("dept",dept);
			paramMap.put("week",week);
			paramMap.put("seasoncode",seasoncode);
			
			retList=sqlMap.queryForList("MarketSales.getBrand", paramMap);
			return retList;
	 }
	 public void saveSales(List<Map> dataLst) throws SQLException{
		try {
		 sqlMap.startTransaction();
		 Iterator it=dataLst.iterator();
		 Map dataMap;
		 while(it.hasNext()) {
			 dataMap=(Map) it.next();
			 if(dataMap.get("id")!=null) {
				 sqlMap.delete("MarketSales.delDataImport", dataMap);
			 }
			 sqlMap.insert("MarketSales.insertDataImport", dataMap);
		 }
		 sqlMap.commitTransaction();
		}catch(Exception ex) {
			throw ex;
		}finally {
			sqlMap.endTransaction();
		}
	 
	 }
}