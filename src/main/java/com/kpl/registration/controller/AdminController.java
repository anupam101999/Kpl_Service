package com.kpl.registration.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kpl.registration.repository.AdminRepo;
import com.kpl.registration.repository.PlayerRepository;

@Controller
public class AdminController {

	@Autowired
	AdminRepo adminRepo;
	@Autowired
	RegistrationController registrationController;
	@Autowired
	PlayerRepository playerRepository;
//	@Autowired
//	AdminService adminService;
//	@Autowired
//	ModelMapper map;

	@GetMapping(value = "/adminDashboardLogin")
	public String adminDashboardLogin() {
		return "adminLogin";
	}

	@RequestMapping(value = "/adminDashboardView", method = RequestMethod.POST)
	public String adminLogin(@RequestParam String id, @RequestParam String password, Model model) {
		String res = adminRepo.adminLoginValidation(id, password);
		if (res != null) {
			return "adminView";
		} else {
			model.addAttribute("errorMessage", "Please input valid id and pasword");
			return "adminLogin";
		}
	}

	@RequestMapping(value = "/updateCategory", method = RequestMethod.POST)
	public String updateCategory(@RequestParam String regid, Model model) throws IOException {
		String[] listOfId = regid.split(",");

		// Convert the array to a list
		List<Long> idList = new ArrayList<>();
		for (String value : listOfId) {
			try {
				idList.add(Long.parseLong(value));
			} catch (Exception e) {
				model.addAttribute("errorMessage", "Please re verify registration ids");
				return "adminView";
			}

		}
		registrationController.updateSpecialPlayerCategory(idList);
		model.addAttribute("errorMessage", "Players has been transferred to List A");
		return "adminView";
	}
	
	
	@RequestMapping(value = "/paymentUpdate", method = RequestMethod.POST)
	public String paymentUpdate(@RequestParam String regid, Model model) throws IOException {
		String[] listOfId = regid.split(",");

		// Convert the array to a list
		List<Long> idList = new ArrayList<>();
		for (String value : listOfId) {
			try {
				idList.add(Long.parseLong(value));
			} catch (Exception e) {
				model.addAttribute("errorMessage", "Please re verify registration ids");
				return "adminView";
			}

		}
		registrationController.paymentUpdate(idList);
		model.addAttribute("errorMessage", "Payment details has been updated");
		return "adminView";
	}
	
	
	@RequestMapping(value = "/downloadDocImg", method = RequestMethod.GET)
	public String downloadDocImg() throws IOException {
		registrationController.downloadAllDocImage();
		return "adminView";
	}
	
	@RequestMapping(value = "/downloadCategorySpecificImage", method = RequestMethod.POST)
	public String downloadCategorySpecificImage(@RequestParam String playerCategory) throws IOException {
		registrationController.downloadAllPlayerImage(playerCategory);
		return "adminView";
	}

}
