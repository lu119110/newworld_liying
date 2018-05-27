package com.newWorldly.applicationDS.business;

import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
public class ReportsBu {
	private Logger logger = Logger.getLogger(MemberSalesContr.class);;
	 SqlMapClient sqlMap=SqlMapConfig.getSqlMapDsSource();

	public  List getReportCommon(Map queryParamMap) throws SQLException{
		List retList=null;
		String sqlMapKey=(String)queryParamMap.get("sqlMapKey");
		retList=sqlMap.queryForList(sqlMapKey, queryParamMap);
		return retList;
	}
	public XSSFWorkbook getReportXlsx(List<Map> reportList) throws SQLException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		//HSSFWorkbook workbook = new HSSFWorkbook();
	    XSSFSheet sheet = workbook.createSheet("Sheet1");
	    XSSFCellStyle cellStyle = workbook.createCellStyle();    
	    cellStyle.setAlignment(HorizontalAlignment.CENTER);  
	    cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
	    cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
	    cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
	    cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
	    cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
	    Font font = workbook.createFont();  

	    //获取表头字段
	   Map theaderMap=(Map)reportList.get(0);
	 
	    String[] tableHeaders=(String[]) theaderMap.keySet().toArray(new String[theaderMap.size()]);
	    
	 // 创建表头
	    XSSFRow row = sheet.createRow(0);
	    // 创建表头
	    for (int i = 0; i < tableHeaders.length; i++) {
	    	sheet.setColumnWidth(i, 20*256);
	        XSSFCell cell = row.createCell(i);
	        cell.setCellValue(tableHeaders[i]);
	        cell.setCellStyle(cellStyle);    
	    }

	    Iterator it=reportList.iterator();
	    int CellNum=0;
	    Map tMap=null;
	    int iRow=1;
	    while(it.hasNext()){
	    	row = sheet.createRow(iRow);
	    	iRow++;
	    	tMap=(Map)it.next();
	    	for(int i=0;i<tableHeaders.length;i++) {
	    	 row.createCell(CellNum).setCellValue(tMap.get(tableHeaders[CellNum])!=null?tMap.get(tableHeaders[CellNum]).toString():"");
	    	 CellNum++;
	    	}
	    	CellNum=0;
	    	
	    }
		return workbook;
	}
}
