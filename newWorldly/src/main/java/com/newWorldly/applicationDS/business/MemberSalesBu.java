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
public class MemberSalesBu {
	private Logger logger = Logger.getLogger(MemberSalesContr.class);;
	 SqlMapClient sqlMap=SqlMapConfig.getSqlMapperIQVipSource();

	 @Autowired
	 private ExcelTempletConfig excelTempletConfig;
	public  List getMemberSales(String startDate,String endDate,String store,String category,String bonus ) throws SQLException{
		List retList=null;
		String filePaht=excelTempletConfig.getMemberSoldTmpFile();
		Map paramMap=new HashMap();
		paramMap.put("startdate", startDate);
		paramMap.put("enddate", endDate);
		paramMap.put("category",category);
		paramMap.put("bonus",bonus);
		if(store!=null && !"".equals(store)) {
			paramMap.put("store", store);
		}
		logger.info("queryForList(\"MemberSalesDS.getMemSales");
		retList=sqlMap.queryForList("MemberSalesDS.getMemSales", paramMap);
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
	    // 将第一行的三个单元格给合并
	    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
	    XSSFRow row = sheet.createRow(0);
	    XSSFCell beginCell = row.createCell(0);
	    beginCell.setCellStyle(cellStyle);
	    beginCell.setCellValue("有消费会员消费信息"); 
	    
	 // 创建表头
	    row = sheet.createRow(1);
	    String[] tableHeaders= {"会员卡号","消费金额","会员姓名","会员电话"};
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
	        tmpCell.setCellValue((String)memberRetList.get(i).get("vipcode"));    
	        tmpCell=row.createCell(1);
	        tmpCell.setCellStyle(cellStyle);
	        tmpCell.setCellValue(memberRetList.get(i).get("amtsold").toString());     
	        tmpCell=row.createCell(2);
	        tmpCell.setCellStyle(cellStyle);
	        tmpCell.setCellValue((String)memberRetList.get(i).get("surname"));  
	        tmpCell=row.createCell(3);
	        tmpCell.setCellStyle(cellStyle);
	        tmpCell.setCellValue((String)memberRetList.get(i).get("telephone"));  
	    }

		return workbook;
	}
}
