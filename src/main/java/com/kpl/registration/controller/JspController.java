package com.kpl.registration.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.models.Model;

@Controller
public class JspController {
	@RequestMapping("/home")
	public String home(Model model) {
		return "home";
	}
}