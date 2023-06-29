package com.kpl.registration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JspController {

@Autowired
RegistrationController registrationController;


	@RequestMapping("/home")
	public String home(Model model) {
		return "home";
	}
//	@RequestMapping("/login")
//	public String login(Model model) throws IOException {
////		RegistrationResponse registrationResponse=registrationController.registrationStatus(searchParam);
//		 model.addAttribute("message","test message");
//		return "login";
//	}
}