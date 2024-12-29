package com.demo.api.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class AppController {

	@GetMapping("")
	public String viewHomePage() {
		return "index";
		
	}
}
