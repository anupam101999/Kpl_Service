package com.kpl.registration.controller;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kpl.registration.dto.PlayerRequetVO;
import com.kpl.registration.repository.PlayerRepository;
import com.kpl.registration.service.PlayerService;

@Controller
public class LoginController {
	@Autowired
	RegistrationController registrationController;
	@Autowired
	PlayerRepository playerRepository;
	@Autowired
	PlayerService playerService;

	@GetMapping("/loginHomePage")
	public String showLoginPage() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("username") String id, @RequestParam("password") String password, Model model)
			throws IOException, ParseException {
		if (playerRepository.findByMailOrPhNumber(id) == null) {			
				model.addAttribute("errorMessage", "Invalid Email ID or Phone Number");
				return "login";
		}
		var response = registrationController.registrationStatus(id, password);
		if (response.getRegistrationId() != null) {

			String dateTimeString = response.getRegistrationTime().toString();
		        
		        // Parse the input string to LocalDateTime
		        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString);
		        
		        // Define the desired output format
		        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		        
		        // Format the LocalDateTime using the output format
		        String formattedDateTime = dateTime.format(outputFormatter);
			model.addAttribute("id", response.getRegistrationId());
			model.addAttribute("firstname", response.getPlayerFirstName());
			model.addAttribute("lastname", response.getPlayerLastName());
			model.addAttribute("name", response.getPlayerFirstName() + " " + response.getPlayerLastName());
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

	@GetMapping("/signUpHomePage")
	public String signUp() {
		return "signUp";
	}

	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public String signUp(@RequestParam String playerFirstName, @RequestParam String playerLastName,
			@RequestParam Long aadharNo, @RequestParam MultipartFile docImage, @RequestParam Long phNo,
			@RequestParam String dob, @RequestParam Long pinCode, @RequestParam MultipartFile playerPhoto,
			@RequestParam String address, @RequestParam String password, @RequestParam String location,
			@RequestParam String playerCategory, @RequestParam String mail, Model model)
			throws IOException, ParseException {

		if (playerFirstName.length() > 20) {
			model.addAttribute("errorMessage", "Please use your first name in short format");
			return "signUp";
		}
		if (playerLastName.length() > 20) {
			model.addAttribute("errorMessage", "Please use your last name in short format");
			return "signUp";
		}
		if (aadharNo.toString().length() != 12) {
			model.addAttribute("errorMessage", "Please use Correct aadhaar No");
			return "signUp";
		}
		if (pinCode.toString().length() != 6) {
			model.addAttribute("errorMessage", "Please use Correct Postal Code");
			return "signUp";
		}

		if (!(docImage.getOriginalFilename().toString().toLowerCase().endsWith(".png")
				|| docImage.getOriginalFilename().toString().toLowerCase().endsWith(".jpg"))) {
			model.addAttribute("errorMessage",
					"Aadhaar Card photo must be an Image and it should be in jpg or png format");
			return "signUp";
		}
		if (!(playerPhoto.getOriginalFilename().toString().toLowerCase().endsWith(".png")
				|| playerPhoto.getOriginalFilename().toString().toLowerCase().endsWith(".jpg"))) {
			model.addAttribute("errorMessage", "Your photo must be an Image and it should be in jpg or png format");
			return "signUp";
		}
		if (!(password.length() > 3 && password.length() < 9)) {
			model.addAttribute("errorMessage", "Password Must be between 4 to 8 character");
			return "signUp";
		}

		if (Period.between(LocalDate.parse(dob).plusYears(14), LocalDate.now()).getYears() < 0) {
			model.addAttribute("errorMessage", "Please Enter Correct Date of Birth");
			return "signUp";
		}

		System.out.println(docImage.getSize());
		
		if (docImage.getSize() > 1 * 512 * 1024) {
			model.addAttribute("errorMessage", "Please Compress your Aadhar Image less than 1 MB");
			return "signUp";
		}

		if (playerPhoto.getSize() > 1 * 512 * 1024) {
			model.addAttribute("errorMessage", "Please Compress your photo less than 1 MB");
			return "signUp";
		}

		Long phNumberUniqueCheck = playerRepository.findByPhNumber(phNo);
		String emailUniqueCheck = playerRepository.findByEmailID(mail);
		if (phNumberUniqueCheck != null) {
			model.addAttribute("errorMessage", "Please use unique phone number!");
			return "signUp";
		}
		if (emailUniqueCheck != null) {
			model.addAttribute("errorMessage", "Please use unique email ID!");
			return "signUp";
		}

		var playerRequetVO = new PlayerRequetVO();
		playerRequetVO.setAadharNo(aadharNo);
		playerRequetVO.setEmailId(mail);
		playerRequetVO.setPlayerAddress(address);
		playerRequetVO.setGenerue(playerCategory);
		playerRequetVO.setPhNo(phNo);
		playerRequetVO.setPinCode(pinCode);
		playerRequetVO.setPlayerFirstName(playerFirstName);
		playerRequetVO.setPlayerLastName(playerLastName);
		playerRequetVO.setDob(LocalDate.parse(dob));
		playerRequetVO.setPassword(password);
		playerRequetVO.setLocation(location);
		byte[] imageData = playerPhoto.getBytes();
		byte[] docData = docImage.getBytes();
		playerService.savePlayerInfo(playerRequetVO, imageData, docData);
		model.addAttribute("errorMessage", "You have been Registered successfully !");
		return "login";

	}
	

}
