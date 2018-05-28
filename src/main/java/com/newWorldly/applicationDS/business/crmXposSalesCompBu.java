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
public class crmXposSalesCompBu {
	private Logger logger = Logger.getLogger(MemberSalesContr.class);;
	 SqlMapClient sqlMap=SqlMapConfig.getSqlMapDsSource();
	 SqlMapClient sqlMapperSm=SqlMapConfig.getSqlmappersmsource();


	public  List<Map> getXposDaySold(String txDate,String store) throws SQLException{
		List retList=null;
		Map paramMap=new HashMap();
		paramMap.put("txdate", txDate);
		paramMap.put("store", store);
		retList=sqlMap.queryForList("CrmXposSalesCompareDS.getDaySalse", paramMap);
		logger.info(retList.size());
		return retList;
	}
	public  List<Map> getXposSmDaySold(String txDate,String store) throws SQLException{
		List retList=null;
		Map paramMap=new HashMap();
		paramMap.put("txdate", txDate);
		paramMap.put("store", store);
		retList=sqlMapperSm.queryForList("CrmXposSalesCompareSM.getDaySalse", paramMap);
		logger.info(retList.size());
		return retList;
	}
	public XSSFWorkbook getMembersalesXls(List<Map> memberRetList) throws SQLException {
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
	    //font.setColor(HSSFColor.RED.index);  
	    //font.setBold(true);
	    cellStyle.setFont(font);
	    // 将第一行的7个单元格给合并
	    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
	    XSSFRow row = sheet.createRow(0);
	    XSSFCell beginCell = row.createCell(0);
	    beginCell.setCellStyle(cellStyle);
	    beginCell.setCellValue("CRM系统报表"); 
	    
	    beginCell = row.createCell(8);
	    beginCell.setCellStyle(cellStyle);
	    beginCell.setCellValue("XPOS系统");
	    
	    beginCell = row.createCell(9);
	    beginCell.setCellStyle(cellStyle);
	    beginCell.setCellValue("差异"); 
	    
	 // 创建表头
	    row = sheet.createRow(1);
	    String[] tableHeaders= {"RMS店铺代码","销售日期","会员销售金额","会员销售数量","会员交易笔数","全店销售金额","全店销售数量","全店销售笔数","全店销售金额","CRM差异"};
	    // 创建表头
	    for (int i = 0; i < tableHeaders.length; i++) {
	    	sheet.setColumnWidth(i, 20*256);
	        XSSFCell cell = row.createCell(i);
	        cell.setCellValue(tableHeaders[i]);
	        cell.setCellStyle(cellStyle);    
	    }
	    //千分位
	    XSSFCellStyle cellStyle4 = workbook.createCellStyle();
	    XSSFDataFormat format = workbook.createDataFormat();
	    cellStyle4.setDataFormat(format.getFormat("#,##0")); 
	    cellStyle4.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
	    cellStyle4.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
	    cellStyle4.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
	    cellStyle4.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
	    cellStyle4.setAlignment(HorizontalAlignment.CENTER);  
	    cellStyle4.setVerticalAlignment(VerticalAlignment.CENTER);
	    //HSSFFont Moneyfont = workbook.createFont();
	    //Moneyfont.setFontName("华文行楷");
	    //Moneyfont.setFontHeightInPoints((short)15);
	    //cellStyle4.setFont(Moneyfont);
	    XSSFCell tmpCell;
	    //
	    for (int i = 0; i < memberRetList.size(); i++) {
	        row = sheet.createRow(i + 2);
	        tmpCell=row.createCell(0);
	        tmpCell.setCellStyle(cellStyle);
	        tmpCell.setCellValue((String)memberRetList.get(i).get("storeCode"));    
	        tmpCell=row.createCell(1);
	        tmpCell.setCellStyle(cellStyle);
	        tmpCell.setCellValue((String)memberRetList.get(i).get("soldDate"));     
	        tmpCell=row.createCell(2);
	        tmpCell.setCellStyle(cellStyle);
	        tmpCell.setCellValue((String)memberRetList.get(i).get("vipSoldAmt"));  
	        tmpCell=row.createCell(3);
	        tmpCell.setCellStyle(cellStyle);
	        tmpCell.setCellValue((String)memberRetList.get(i).get("vipSoldGoodsCount"));  
	        
	        tmpCell=row.createCell(4);
	        tmpCell.setCellStyle(cellStyle);
	        tmpCell.setCellValue((String)memberRetList.get(i).get("vipSoldTransCount"));
	        
	        tmpCell=row.createCell(5);
	        tmpCell.setCellStyle(cellStyle);
	        tmpCell.setCellValue((String)memberRetList.get(i).get("storeSoldAmt"));
	        
	        tmpCell=row.createCell(6);
	        tmpCell.setCellStyle(cellStyle);
	        tmpCell.setCellValue((String)memberRetList.get(i).get("storeSoldGoodsCount"));
	        
	        tmpCell=row.createCell(7);
	        tmpCell.setCellStyle(cellStyle);
	        tmpCell.setCellValue((String)memberRetList.get(i).get("storeSoldTransCount"));

	        
	        tmpCell=row.createCell(8);
	        tmpCell.setCellStyle(cellStyle);
	        tmpCell.setCellValue(memberRetList.get(i).get("xposStoreSoldAmt").toString());
	        
	        tmpCell=row.createCell(9);
	        tmpCell.setCellStyle(cellStyle);
	        tmpCell.setCellValue(memberRetList.get(i).get("check").toString());
	    }

		return workbook;
	}
}
