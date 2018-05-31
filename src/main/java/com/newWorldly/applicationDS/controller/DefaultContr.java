package com.newWorldly.applicationDS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller
public class DefaultContr {
	//@RequestMapping("/")
	public String indexHtml() {
		return "forward:Login.html";
	}

}
