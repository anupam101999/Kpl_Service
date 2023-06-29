package com.kpl.registration.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kpl.registration.repository.PlayerRepository;

@Controller
public class LoginController {
	@Autowired
	RegistrationController registrationController;
	@Autowired
	PlayerRepository playerRepository;
	
	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("username") String id, @RequestParam("password") String password, Model model)
			throws IOException, ParseException {
		if (playerRepository.findByMailOrPhNumber(id)==null) {
			try {
				Integer.parseInt(id);
			} catch (Exception ep) {
				model.addAttribute("errorMessage", "Invalid Email ID");
				return "login";
			}
			model.addAttribute("errorMessage", "Invalid Phone Number");
			return "login";
		}
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
			model.addAttribute("errorMessage", "Invalid Password");
			return "login";
		}
	}

	@GetMapping("/playerView")
	public String playerView() {
		return "playerView";
	}

	@GetMapping("/forgetPassword")
	public String forgetPassword() {
		return "forgetPassword";
	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public String resetPassword(@RequestParam Long phNumber, @RequestParam Long pinCode, @RequestParam Long aadhaarNo,
			@RequestParam String password, Model model) throws IOException, ParseException {

		var response = registrationController.resetPassword(phNumber, pinCode, aadhaarNo, password);
		if (response == "Success") {

			model.addAttribute("errorMessage", "Password has been updated successfully");
			return "login";
		} else {
			model.addAttribute("errorMessage", response);
			return "forgetPassword";
		}
	}

	
	@GetMapping("/signUp")
	public String signUp() {
		return "signUp";
	}
}
