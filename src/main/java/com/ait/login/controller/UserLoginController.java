package com.ait.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ait.login.service.UserLoginService;

@Controller
public class UserLoginController {
	
	@Autowired
	UserLoginService service;
	
	@GetMapping(value = "/login")
	public String getLoginPage() {
		return "Login";
	}
	
	@PostMapping(value = "/checkLogin")
	public String verifyUserLogin(@RequestParam String username, @RequestParam String password, Model model) {
		
		String msg = service.checkUserCredentials(username, password);
		if(msg.equals("valid") ) {
			model.addAttribute("username", username);
			return "Success";
		}else {
			model.addAttribute("message", msg);
			return "Login";
		}
		
			
	}

}
