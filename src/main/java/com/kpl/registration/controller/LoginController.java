package com.kpl.registration.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	@Autowired
	RegistrationController registrationController;

	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("username") String id, @RequestParam("password") String password, Model model)
			throws IOException, ParseException {

		var response = registrationController.registrationStatus(id, password);
		if (response.getRegistrationId() != null) {
			
			String dateString = response.getRegistrationTime().toString();
			SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
			Date date = inputFormat.parse(dateString);
			SimpleDateFormat outputFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
			String formattedDateTime = outputFormat.format(date);

			
			
			model.addAttribute("id", response.getRegistrationId());
			model.addAttribute("name", response.getPlayerName());
			model.addAttribute("registerDate", formattedDateTime);
			model.addAttribute("status", response.getPaymentValidation());
			return "playerView";
		} else {
			model.addAttribute("errorMessage", "Invalid Email/Ph No or password");
			return "login";
		}
	}

	@GetMapping("/welcome")
	public String showWelcomePage() {
		return "welcome";
	}

	@GetMapping("/playerView")
	public String playerView() {
		return "playerView";
	}
}
