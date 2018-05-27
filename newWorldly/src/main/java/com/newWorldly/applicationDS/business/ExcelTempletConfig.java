package com.newWorldly.applicationDS.business;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:ExcelTempletConfig.properties")

public class ExcelTempletConfig {
	 @Value("${MemberSoldTmpFile}")
	private String MemberSoldTmpFile;

	public String getMemberSoldTmpFile() {
		return MemberSoldTmpFile;
	}

	public void setMemberSoldTmpFile(String memberSoldTmpFile) {
		MemberSoldTmpFile = memberSoldTmpFile;
	}
	 
}
