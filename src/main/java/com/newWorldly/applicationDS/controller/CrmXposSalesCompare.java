package com.newWorldly.applicationDS.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
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
import org.springframework.web.multipart.MultipartFile;

import org.apache.log4j.Logger;
import com.newWorldly.applicationDS.business.ReportsBu;
import com.newWorldly.applicationDS.business.crmXposSalesCompBu;

import net.sf.json.JSON;

@RestController
public class CrmXposSalesCompare {
	private static Logger logger= Logger.getLogger(CrmXposSalesCompare.class);
	@Autowired
	private crmXposSalesCompBu bu;
	
	@RequestMapping("/DS/CrmXposComparison/CrmXposSalesComp.do")
	public List<Map> crmXposSalesComp(@RequestParam("crmFile") MultipartFile file) throws IOException, SQLException {
		logger.info("3333333333333333333333333333333333333333333");
			InputStream in=file.getInputStream();
			InputStreamReader inr=new InputStreamReader(in); 
			BufferedReader binf=new BufferedReader(inr);
			List crmList=new ArrayList(128);	
			Map itMap=null;
			Map xposMap=null;
			String strLine=null;
			String[] datas=null;
			BigDecimal crmSold,xposSold;
			while ((strLine=binf.readLine())!=null) {
				datas=strLine.split("	");
				itMap=new HashMap();
				itMap.put("storeCode", datas[0]);
				itMap.put("soldDate", datas[1]);
				itMap.put("vipSoldAmt", datas[2]);
				itMap.put("vipSoldGoodsCount", datas[3]);
				itMap.put("vipSoldTransCount", datas[4]);
				itMap.put("storeSoldAmt", datas[5]);
				itMap.put("storeSoldGoodsCount", datas[6]);
				itMap.put("storeSoldTransCount", datas[7]);
				if("11014".equals(itMap.get("storeCode"))) {
					xposMap=bu.getXposDaySold((String)itMap.get("soldDate"),(String)itMap.get("storeCode")).get(0);
				}else {
					xposMap=bu.getXposSmDaySold((String)itMap.get("soldDate"),(String)itMap.get("storeCode")).get(0);
				}
					itMap.put("xposStoreSoldAmt", xposMap.get("sold"));
				//差异
				crmSold=new BigDecimal(datas[5]);
				xposSold=new BigDecimal(xposMap.get("sold").toString());
				itMap.put("check", crmSold.subtract(xposSold));
				crmList.add(itMap);
			}
			return crmList;
		
	}
	@RequestMapping("/DS/CrmXposComparison/exportCrmXposSalesCompXlsx.do")
	public void exportReportXlsx(@RequestParam("crmFile") MultipartFile file,HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException, SQLException {
		logger.info("11111111111111111111111111111111111SS");
		InputStream in=file.getInputStream();
		InputStreamReader inr=new InputStreamReader(in); 
		BufferedReader binf=new BufferedReader(inr);
		List crmList=new ArrayList(128);	
		Map itMap=null;
		Map xposMap=null;
		String strLine=null;
		String[] datas=null;
		BigDecimal crmSold,xposSold;
		while ((strLine=binf.readLine())!=null) {
			datas=strLine.split("	");
			itMap=new HashMap();
			itMap.put("storeCode", datas[0]);
			itMap.put("soldDate", datas[1]);
			itMap.put("vipSoldAmt", datas[2]);
			itMap.put("vipSoldGoodsCount", datas[3]);
			itMap.put("vipSoldTransCount", datas[4]);
			itMap.put("storeSoldAmt", datas[5]);
			itMap.put("storeSoldGoodsCount", datas[6]);
			itMap.put("storeSoldTransCount", datas[7]);
			if("11014".equals(itMap.get("storeCode"))) {
				xposMap=bu.getXposDaySold((String)itMap.get("soldDate"),(String)itMap.get("storeCode")).get(0);
			}else {
				xposMap=bu.getXposSmDaySold((String)itMap.get("soldDate"),(String)itMap.get("storeCode")).get(0);
			}
			itMap.put("xposStoreSoldAmt", xposMap.get("sold"));
			//差异
			crmSold=new BigDecimal(datas[5]);
			xposSold=new BigDecimal(xposMap.get("sold").toString());
			itMap.put("check", crmSold.subtract(xposSold));
			crmList.add(itMap);
		}
		XSSFWorkbook wb=bu.getMembersalesXls(crmList);
		OutputStream outputStream = response.getOutputStream(); 
	    response.reset(); 
	    response.setContentType("application/vnd.ms-excel");    
	    response.setHeader("Content-disposition", "attachment;filename='"+java.net.URLEncoder.encode(file.getName(), "UTF-8")+".xlsx'");    

	    wb.write(outputStream);
	    outputStream.close();


	}
	
}
