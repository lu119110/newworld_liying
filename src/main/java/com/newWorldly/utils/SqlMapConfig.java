
package com.newWorldly.utils;

import java.io.IOException;
import java.io.Reader;
import org.apache.log4j.Logger;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;



public class SqlMapConfig {
	private static Logger logger = Logger.getLogger(SqlMapConfig.class);

	//private static final SqlMapClient sqlMapper;
	private static  SqlMapClient sqlMapperDsSource;
	private static  SqlMapClient sqlMapperSmSource;
	private static  SqlMapClient sqlMapperOldDsSource;
	private static SqlMapClient sqlMapperIQVIpSource;
	private static SqlMapClient sqlMapperLySource; 

	static {
		try {
			Reader reader = Resources.getResourceAsReader("SqlMapConfig_ds_source.xml");
			//Reader reader = Resources.getResourceAsReader("SqlMapConfig_ds_source_dev.xml");
			sqlMapperDsSource= SqlMapClientBuilder.buildSqlMapClient(reader);
			reader.close();
			reader = Resources.getResourceAsReader("SqlMapConfig_oldds_source.xml");
			sqlMapperOldDsSource= SqlMapClientBuilder.buildSqlMapClient(reader);
			reader.close();
			reader = Resources.getResourceAsReader("SqlMapConfig_IQVip_source.xml");
			sqlMapperIQVIpSource= SqlMapClientBuilder.buildSqlMapClient(reader);
			reader.close();
			reader = Resources.getResourceAsReader("SqlMapConfig_sm_source.xml");
			sqlMapperSmSource= SqlMapClientBuilder.buildSqlMapClient(reader);
			reader.close();
			reader = Resources.getResourceAsReader("SqlMapConfig_ly_source.xml");
			sqlMapperLySource=SqlMapClientBuilder.buildSqlMapClient(reader);
			reader.close();
			
			/*reader = Resources.getResourceAsReader("SqlMapConfig_ds_source_conxpos.xml");
			sqlMapperDsSourceConxpos= SqlMapClientBuilder.buildSqlMapClient(reader);
			reader.close();	*/
		} catch (IOException e) {
			logger.error("static read sqlMapConfig.xml file error.");
			// Fail fast.
			throw new RuntimeException(
					"Something bad happened while building the SqlMapClient instance."
							+ e, e);
		}
	}


	public static SqlMapClient getSqlMapperLySource() {
		return sqlMapperLySource;
	}
	public static SqlMapClient getSqlMapDsSource() {
		return sqlMapperDsSource;
	}
	public static SqlMapClient getSqlMapperOldDsSource() {
		return sqlMapperOldDsSource;
	}
	public static SqlMapClient getSqlMapperIQVipSource() {
		return sqlMapperIQVIpSource;
	}
	
	public static SqlMapClient getSqlmappersmsource() {
		return sqlMapperSmSource;
	}
	/*
	public static SqlMapClient getSqlmapperdssourceconxpos() {
		return sqlMapperDsSourceConxpos;
	}*/
	// not to instance
	private SqlMapConfig() {

	}
}
