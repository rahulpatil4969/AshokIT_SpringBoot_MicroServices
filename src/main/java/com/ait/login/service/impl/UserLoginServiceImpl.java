package com.ait.login.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ait.login.entity.UserLogin;
import com.ait.login.repository.UserLoginRepository;
import com.ait.login.service.UserLoginService;

@Service
public class UserLoginServiceImpl implements UserLoginService {
	@Autowired
	UserLoginRepository repo;

	@Override
	public String checkUserCredentials(String username, String password) {
		
		String msg="";
		
		Optional<UserLogin> opt = repo.fetchUser(username);
		if(opt.isPresent()) {
			UserLogin user = opt.get();
			if(user.getActive().equalsIgnoreCase("active")) {
				if(user.getPassword().equals(password)) {
					msg = "valid";
				}
				else {
					msg = "Bad Credentials";
				}
			}
			else {
				msg = "User Disabled";
			}
		}
		return msg;
	}

}
