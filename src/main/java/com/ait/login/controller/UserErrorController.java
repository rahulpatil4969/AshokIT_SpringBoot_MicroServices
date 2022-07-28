package com.ait.login.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserErrorController implements ErrorController {
	
	@GetMapping("/error")
	public String getErrorPage() {
		 return "error-404";
	}

}
