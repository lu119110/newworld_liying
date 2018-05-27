package com.newWorldly.applicationDS.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jcabi.log.Logger;
import com.newWorldly.applicationDS.business.ReportsBu;

import net.sf.json.JSON;

@RestController
public class ReportCommonContr {
	@Autowired
	private ReportsBu bu;
	
	@RequestMapping("/DS/Reports/getReport.do")
	public List<Map> getReport(@RequestBody HashMap<String,String> reqMap) {
		
		try {
			return bu.getReportCommon(reqMap);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	@RequestMapping("/DS/Reports/exportReportXlsx.do")
	public void exportReportXlsx(@RequestParam(value="reqStr",required=true) String strReq, HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException {
		Logger.info("11111111111111111111111111111111111SS", strReq);
		ObjectMapper mapper = new ObjectMapper();
        Map reqmap = mapper.readValue(strReq, Map.class); 
		try {
			List reList=bu.getReportCommon(reqmap);
			
			XSSFWorkbook wb=bu.getReportXlsx(reList);
			OutputStream outputStream = response.getOutputStream(); 
		    response.reset(); 
		    response.setContentType("application/vnd.ms-excel");    
		    response.setHeader("Content-disposition", "attachment;filename=export.xlsx");    

		    wb.write(outputStream);
		    outputStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
}
