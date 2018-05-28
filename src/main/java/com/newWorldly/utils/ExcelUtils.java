package com.newWorldly.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelUtils {
	
	private static final String INSPECTIONRECORD_SURFACE_TEMPLET_PATH = "/asserts/templete/InspectionRecordSurface.xls";  
    private static HSSFCellStyle cellstyle = null;  
    
	 private static Workbook readExcel(String filePath) {  
	        InputStream in = null;  
	        Workbook work = null;  
	        try {  
	            in = new FileInputStream(filePath);  
	            work = new HSSFWorkbook(in);  
	        } catch (FileNotFoundException e) {  
	            System.out.println("文件路径错误");  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            System.out.println("文件输入流错误");  
	            e.printStackTrace();  
	        }  
	        return work;  
	    }  
	 private static Cell setCellStyleWithStyleAndValue(CellStyle style, Cell cell, String value) {  
	        cell.setCellStyle(style);  
	        cell.setCellValue(value);  
	        return cell;  
	    }  
	  
	    private static Cell setCellStyleWithValue(Cell cell, String value) {  
	        cell.setCellStyle(cellstyle);  
	        cell.setCellValue(value);  
	        return cell;  
	    }  
	  
	  
	    private static Cell setCellStyleWithStyleAndValue(CellStyle style, Cell cell, RichTextString value) {  
	        cell.setCellStyle(style);  
	        cell.setCellValue(value);  
	        return cell;  
	    }  
	  
	    private static Cell setCellStyleWithValue(Cell cell, int value) {  
	        cell.setCellStyle(cellstyle);  
	        cell.setCellValue(value);  
	        return cell;  
	    }  
	  
	    private static Cell setCellStyleWithValue(Cell cell, double value) {  
	        cell.setCellStyle(cellstyle);  
	        cell.setCellValue(value);  
	        return cell;  
	    }  
	  
	    private static HSSFCellStyle createCellStyle(Workbook wb) {  
	        cellstyle = (HSSFCellStyle) wb.createCellStyle();  
	        cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
	        cellstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
	        cellstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
	        cellstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);  
	        cellstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);  
	        cellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
	        return cellstyle;  
	    }  
}
