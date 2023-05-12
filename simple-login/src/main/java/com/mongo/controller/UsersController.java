package com.mongo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mongo.model.UserModel;
import com.mongo.service.UserService;


@Controller
public class UsersController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/register")
	public String getRegisterPage(Model model) {
		model.addAttribute("registerRequest", new UserModel());
		return "register_page";
	}
	
	@RequestMapping("/login")
	public String getLoginPage(Model model) {
		model.addAttribute("loginRequest", new UserModel());
		return "login_page";
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute UserModel userModel) {
		System.out.println("Register Request: "+userModel);
		UserModel registeredUser = userService.registerUser(userModel.getLogin(), userModel.getPassword(),userModel.getEmail());
		return registeredUser == null?"error_page":"redirect:/login";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute UserModel userModel, Model model) {
		System.out.println("login Request: "+userModel);
		UserModel authenticated = userService.authenticate(userModel.getLogin(), userModel.getPassword());
		if(authenticated != null) {
			model.addAttribute("userLogin", authenticated.getLogin());
			return "personal_page";
		}else {
			return "error_page";
		}
	}
}
