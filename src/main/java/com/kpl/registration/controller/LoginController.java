package com.kpl.registration.controller;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.kpl.registration.repository.PlayerRepository;
import com.kpl.registration.service.GenericService.PlayerService;
import com.kpl.registration.service.GenericService.PlayerServiceImpl;

import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {
	@Autowired
	RegistrationController registrationController;
	@Autowired
	PlayerRepository playerRepository;
	@Autowired
	PlayerService playerService;
	@Autowired
	PlayerServiceImpl playerServiceImpl;

	@Autowired
	RestTemplate restTemplate;

	String telegramBotUrl = "https://api.telegram.org/bot6637753416:AAHb7DHnfrvEl6Aje0RfyrumAkZjZglxXHU/sendmessage?chat_id=@kpl2023updates&text=";

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

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginValidationOnForwardAgain(Model model) throws IOException, ParseException {
		return "login";
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
			@RequestParam String password, Model model)
			throws IOException, ParseException, MessagingException, TemplateException {

		var response = registrationController.resetPassword(phNumber, pinCode, aadhaarNo, password);
		if (response == "Success") {

			model.addAttribute("errorMessage", "Password has been updated successfully");
			return "login";
		} else {
			model.addAttribute("errorMessage", response);
			return "forgetPassword";
		}
	}

//	@GetMapping("/signUpHomePage")
//	public String signUp() {
//		return "signUp";
//	}

//	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
//	public String signUp(@RequestParam String playerFirstName, @RequestParam String playerLastName,
//			@RequestParam Long aadharNo, @RequestParam MultipartFile docImageBack,
//			@RequestParam MultipartFile docImageFront, @RequestParam Long phNo, @RequestParam String dob,
//			@RequestParam Long pinCode, @RequestParam MultipartFile playerPhoto, @RequestParam String address,
//			@RequestParam String password, @RequestParam String location, @RequestParam String playerCategory,
//			@RequestParam String mail, Model model)
//			throws IOException, ParseException, MessagingException, TemplateException {
//		String message = "";
//
//		log.info("Player who tried to sign up : " + playerFirstName + " " + playerLastName);
//		log.info(playerFirstName + " phone number  : " + phNo);
//		Long phNumberUniqueCheck = playerRepository.findByPhNumber(phNo);
//		if (phNumberUniqueCheck != null) {
//			var regId = playerRepository.findByPhNu(phNo.toString());
//			message = "Hey Support team @RAVVAN23 @Kalajaduu13 @emotionalclown   " + playerFirstName + " "
//					+ playerLastName
//					+ " is refreshing page unnecessarily please ask him to avoid so and his Phone number is : " + phNo;
//			restTemplate.getForObject(telegramBotUrl + message, String.class);
//			model.addAttribute("id", regId);
//			log.info(playerLastName
//					+ " is refreshing page unnecessarily please ask him to avoid so and his Phone number is : " + phNo);
//			return directPayment(model);
//		}
//
//		if (!(docImageFront.getOriginalFilename().toString().toLowerCase().endsWith(".png")
//				|| docImageFront.getOriginalFilename().toString().toLowerCase().endsWith(".jpg")
//				|| docImageFront.getOriginalFilename().toString().toLowerCase().endsWith(".jpeg"))) {
//			model.addAttribute("errorMessage",
//					"Aadhaar Card Front Image must be an Image and it should be in jpg or png or jpeg format");
//			message = "Hey Support team @RAVVAN23 @Kalajaduu13 @emotionalclown   " + playerFirstName + " "
//					+ playerLastName
//					+ " is trying to Register but he has not uploaded Aadhar card front image in correct format, please help him and his Phone number is : "
//					+ phNo;
//			restTemplate.getForObject(telegramBotUrl + message, String.class);
//			log.info("Aadhaar Card Front Image must be an Image and it should be in jpg or png or jpeg format");
//			return "signUp";
//		}
//
//		if (!(docImageBack.getOriginalFilename().toString().toLowerCase().endsWith(".png")
//				|| docImageBack.getOriginalFilename().toString().toLowerCase().endsWith(".jpg")
//				|| docImageFront.getOriginalFilename().toString().toLowerCase().endsWith(".jpeg"))) {
//			model.addAttribute("errorMessage",
//					"Aadhaar Card Back Image must be an Image and it should be in jpg or png or jpeg format");
//			message = "Hey Support team @RAVVAN23 @Kalajaduu13 @emotionalclown   " + playerFirstName + " "
//					+ playerLastName
//					+ " is trying to Register but he has not uploaded Aadhar card Back image in correct format, please help him and his Phone number is : "
//					+ phNo;
//			restTemplate.getForObject(telegramBotUrl + message, String.class);
//			log.info("Aadhaar Card Back Image must be an Image and it should be in jpg or png or jpeg format");
//			return "signUp";
//		}
//		if (!(playerPhoto.getOriginalFilename().toString().toLowerCase().endsWith(".png")
//				|| playerPhoto.getOriginalFilename().toString().toLowerCase().endsWith(".jpg")
//				|| docImageFront.getOriginalFilename().toString().toLowerCase().endsWith(".jpeg"))) {
//			model.addAttribute("errorMessage",
//					"Your photo must be an Image and it should be in jpg or png or jpeg format");
//			message = "Hey Support team @RAVVAN23 @Kalajaduu13 @emotionalclown   " + playerFirstName + " "
//					+ playerLastName
//					+ " is trying to Register but he has not uploaded his own image in correct format, please help him and his Phone number is : "
//					+ phNo;
//			restTemplate.getForObject(telegramBotUrl + message, String.class);
//			log.info("Your photo must be an Image and it should be in jpg or png or jpeg format");
//			return "signUp";
//		}
//
//		if (Period.between(LocalDate.parse(dob).plusYears(14), LocalDate.now()).getYears() < 0) {
//			model.addAttribute("errorMessage", "Please Enter Correct Date of Birth");
//			log.info("Please Enter Correct Date of Birth");
//			message = "Hey Support team @RAVVAN23 @Kalajaduu13 @emotionalclown   " + playerFirstName + " "
//					+ playerLastName
//					+ " is trying to Register but he has selected invalid Date of Birth, please help him and his Phone number is : "
//					+ phNo;
//			restTemplate.getForObject(telegramBotUrl + message, String.class);
//			return "signUp";
//		}
////
//		var playerRequetVO = new PlayerRequetVO();
//		playerRequetVO.setAadharNo(aadharNo);
//		playerRequetVO.setEmailId(mail);
//		playerRequetVO.setPlayerAddress(address);
//		playerRequetVO.setGenerue(playerCategory);
//		playerRequetVO.setPhNo(phNo);
//		playerRequetVO.setPinCode(pinCode);
//		playerRequetVO.setPlayerFirstName(playerFirstName);
//		playerRequetVO.setPlayerLastName(playerLastName);
//		playerRequetVO.setDob(LocalDate.parse(dob));
//		playerRequetVO.setPassword(password);
//		playerRequetVO.setLocation(location);
//		byte[] imageData = playerPhoto.getBytes();
//		byte[] docDataFront = docImageFront.getBytes();
//		byte[] docDataBack = docImageBack.getBytes();
//
//		var res = playerService.savePlayerInfo(playerRequetVO, imageData, docDataFront, docDataBack);
//		model.addAttribute("errorMessage", res.getResponse());
//		model.addAttribute("id", res.getRegistrationID());
//		return directPayment(model);
//		return directPayment(model);
//		if (docImageFront.getSize() > 1 * 512 * 1024) {
//		message = "Hey Support team @RAVVAN23 @Kalajaduu13 @emotionalclown   " + playerFirstName + " " + playerLastName
//				+ " is trying to Register but he has selected Aadhar Front image more than 300 KB, please help him and his Phone number is : "
//				+ phNo;
//		restTemplate.getForObject(telegramBotUrl + message, String.class);
//		model.addAttribute("errorMessage", "Please Compress your Aadhar Front Image less than 512 KB");
//		log.info("Please Compress your Aadhar Front Image less than 512 KB");
//		return "signUp";
//	}
//
//	if (docImageBack.getSize() > 1 * 512 * 1024) {
//		message = "Hey Support team @RAVVAN23 @Kalajaduu13 @emotionalclown   " + playerFirstName + " " + playerLastName
//				+ " is trying to Register but he has selected Aadhar Back image more than 300 KB, please help him and his Phone number is : "
//				+ phNo;
//		restTemplate.getForObject(telegramBotUrl + message, String.class);
//		model.addAttribute("errorMessage", "Please Compress your Aadhar Back Image less than 512 KB");
//		log.info("Please Compress your Back Aadhar Image less than 512 KB");
//		return "signUp";
//	}
//
//	if (playerPhoto.getSize() > 1 * 512 * 1024) {
//		message = "Hey Support team @RAVVAN23 @Kalajaduu13 @emotionalclown   " + playerFirstName + " " + playerLastName
//				+ " is trying to Register but he has selected His Own image more than 300 KB, please help him and his Phone number is : "
//				+ phNo;
//		restTemplate.getForObject(telegramBotUrl + message, String.class);
//		model.addAttribute("errorMessage", "Please Compress your photo less than 512 KB");
//		log.info("Please Compress your photo less than 512 KB");
//		return "signUp";
//	}

//	if (playerFirstName.length() > 20) {
//		model.addAttribute("errorMessage", "Please use your first name in short format");
//		log.info("Please use your first name less than 20 word");
//		message = "Hey Support team @RAVVAN23 @Kalajaduu13 @emotionalclown   " + playerFirstName + " " + playerLastName
//				+ " is trying to Register but he is using First name more than 20 words, please help him and his Phone number is : "
//				+ phNo;
//		restTemplate.getForObject(telegramBotUrl + message, String.class);
//		return "signUp";
//	}

//	message = "Hey Support team @RAVVAN23 @Kalajaduu13 @emotionalclown   " + playerFirstName + " " + playerLastName
//			+ " is trying to Register help him if he requires any help his phone number is : " + phNo;
//	restTemplate.getForObject(telegramBotUrl + message, String.class);

//	if (playerLastName.length() > 20) {
//		model.addAttribute("errorMessage", "Please use your last name in short format");
//		log.info("Please use your last name in short format");
//		message = "Hey Support team @RAVVAN23 @Kalajaduu13 @emotionalclown   " + playerFirstName + " " + playerLastName
//				+ " is trying to Register but he is using Last name more than 20 words, please help him and his Phone number is : "
//				+ phNo;
//		restTemplate.getForObject(telegramBotUrl + message, String.class);
//		return "signUp";
//	}
//	if (aadharNo.toString().length() != 12) {
//		model.addAttribute("errorMessage", "Please use Correct aadhaar No");
//		log.info("Please use Correct aadhaar No");
//		message = "Hey Support team @RAVVAN23 @Kalajaduu13 @emotionalclown   " + playerFirstName + " " + playerLastName
//				+ " is trying to Register but he is not using 12 digits Aadhar no, please help him and his Phone number is : "
//				+ phNo;
//		restTemplate.getForObject(telegramBotUrl + message, String.class);
//		return "signUp";
//	}
//	if (pinCode.toString().length() != 6) {
//		model.addAttribute("errorMessage", "Please use Correct Postal Code");
//		message = "Hey Support team @RAVVAN23 @Kalajaduu13 @emotionalclown   " + playerFirstName + " "
//				+ playerLastName
//				+ " is trying to Register but he is using Postal code more than 6 digits, please help him and his Phone number is : "
//				+ phNo;
//		restTemplate.getForObject(telegramBotUrl + message, String.class);
//		log.info("Please use Correct Postal Code");
//		return "signUp";
//	}
//
//	if (playerCategory.toString().equals("Player Category")) {
//		model.addAttribute("errorMessage", "Please Select Your Category");
//		message = "Hey Support team @RAVVAN23 @Kalajaduu13 @emotionalclown   " + playerFirstName + " " + playerLastName
//				+ " is trying to Register but he has not selected player category, please help him and his Phone number is : "
//				+ phNo;
//		restTemplate.getForObject(telegramBotUrl + message, String.class);
//		log.info("Please Select Your Category");
//		return "signUp";
//	}
//
//	if (location.toString().equals("Your Home location")) {
//		model.addAttribute("errorMessage", "Please Select Your Home Location");
//		message = "Hey Support team @RAVVAN23 @Kalajaduu13 @emotionalclown   " + playerFirstName + " " + playerLastName
//				+ " is trying to Register but he has not selected Home Location, please help him and his Phone number is : "
//				+ phNo;
//		restTemplate.getForObject(telegramBotUrl + message, String.class);
//		log.info("Please Select Your Home Location");
//		return "signUp";
//	}

//		if (!(password.length() > 3 && password.length() < 9)) {
//		model.addAttribute("errorMessage", "Password Must be between 4 to 8 character");
//		log.info("Password Must be between 4 to 8 character");
//		message = "Hey Support team @RAVVAN23 @Kalajaduu13 @emotionalclown   " + playerFirstName + " "
//				+ playerLastName
//				+ " is trying to Register but he has not selected password between 4 to 8 character, please help him and his Phone number is : "
//				+ phNo;
//		restTemplate.getForObject(telegramBotUrl + message, String.class);
//		return "signUp";
//	}
//		Long phNumberUniqueCheck = playerRepository.findByPhNumber(phNo);
//
//		if (phNumberUniqueCheck != null) {
//			message = "Hey Support team @RAVVAN23 @Kalajaduu13 @emotionalclown   " + playerFirstName + " "
//					+ playerLastName
//					+ " is trying to Register using already registered Phone Number, please help him and his Phone number is : "
//					+ phNo;
//			restTemplate.getForObject(telegramBotUrl + message, String.class);
//			model.addAttribute("errorMessage", "Please use unique phone number!");
//			log.info("Please use unique phone number!");
//			return "signUp";
//		}
//		String emailUniqueCheck = playerRepository.findByEmailID(mail);
//		if (emailUniqueCheck != null) {
//			message = "Hey Support team @RAVVAN23 @Kalajaduu13 @emotionalclown   " + playerFirstName + " "
//					+ playerLastName
//					+ " is trying to Register using already registered Email ID, please help him and his Phone number is : "
//					+ phNo;
//			restTemplate.getForObject(telegramBotUrl + message, String.class);
//			model.addAttribute("errorMessage", "Please use unique email ID!");
//			log.info("Please use unique email ID!");
//			return "signUp";
//		}
//		String aadhaarCheck = playerRepository.findByAadhaarID(aadharNo);
//		if (aadhaarCheck != null) {
//			message = "Hey Support team @RAVVAN23 @Kalajaduu13 @emotionalclown   " + playerFirstName + " "
//					+ playerLastName
//					+ " is trying to Register but he is using already registered Aadhar no, please help him and his Phone number is : "
//					+ phNo;
//			restTemplate.getForObject(telegramBotUrl + message, String.class);
//			model.addAttribute("errorMessage", "Please use unique aadhaar ID!");
//			log.info("Please use unique aadhaar ID!");
//			return "signUp";
//		}
//
//	}

	@GetMapping("/payment")
	public String payment() {
		return "payment";
	}

	@GetMapping("/liveAution")
	public String liveAution() {
		return "liveauction";
	}

	public String directPayment(Model model) {
		return "directPayment";

	}

}
