package com.newWorldly.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class ContextListener implements ServletContextListener{

	public ContextListener() {
		// TODO Auto-generated constructor stub
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		/*ScanDsTask sct=new ScanDsTask();
		Thread th=new Thread(sct);
		th.start();
		*/
	}

}