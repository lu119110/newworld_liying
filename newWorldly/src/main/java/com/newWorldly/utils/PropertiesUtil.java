/**
 * 
 */
package com.newWorldly.utils;




import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;


public class PropertiesUtil {
	

	private HashMap hs;
	private static PropertiesUtil prpUtil=new PropertiesUtil();
	private PropertiesUtil(){
		hs=new HashMap();

		hs.put("users", "users.properties");
		
	}
	private Properties getProperties(String fileAlias)
	{
		Properties prp = new Properties();
		try{
			prp.load(getClass().getClassLoader().getResourceAsStream((String)hs.get(fileAlias)));
			
		}catch(IOException e)
		{
			
		}
		return prp;
	}

	public String getValue(String fileAlias,String name)
	{
		String str="";
		Properties prp = getProperties(fileAlias);
		str = prp.getProperty(name);
		return str;
	}
	public static PropertiesUtil getInstance(){
		return prpUtil;
	}
}
