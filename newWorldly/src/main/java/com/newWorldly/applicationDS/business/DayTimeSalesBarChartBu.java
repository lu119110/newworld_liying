package com.newWorldly.applicationDS.business;

import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.newWorldly.utils.SqlMapConfig;
@Component
@Scope("prototype")
public class DayTimeSalesBarChartBu {
	 SqlMapClient sqlMap=SqlMapConfig.getSqlMapDsSource();
	 SqlMapClient sqlMapOldDs=SqlMapConfig.getSqlMapperOldDsSource();
	public  List getDayTimeSales(String strday) throws SQLException{
		List retList=null;
		String[] strDateArr=strday.split("-");
		GregorianCalendar ca=new GregorianCalendar(2017,2,1);//2017-3-1
		GregorianCalendar caDate=new GregorianCalendar(Integer.valueOf(strDateArr[0]),Integer.valueOf(strDateArr[1])-1,Integer.valueOf(strDateArr[2]));
		Map paramMap=new HashMap();
		paramMap.put("txdate", strday);
		if(caDate.before(ca)){
			retList=sqlMapOldDs.queryForList("DayTimeSalesDS.getDayTimeSalse", paramMap);
		}else{
			retList=sqlMap.queryForList("DayTimeSalesDS.getDayTimeSalse", paramMap);
		
		}
		return retList;
	}
}
