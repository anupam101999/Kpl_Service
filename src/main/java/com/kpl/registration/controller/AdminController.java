package com.kpl.registration.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kpl.registration.repository.AdminRepo;
import com.kpl.registration.repository.PlayerRepository;

import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class AdminController {

	@Autowired
	AdminRepo adminRepo;
	@Autowired
	RegistrationController registrationController;
	@Autowired
	PlayerRepository playerRepository;


	@GetMapping(value = "/adminDashboardLogin")
	public String adminDashboardLogin() {
		return "adminLogin";
	}

	@RequestMapping(value = "/adminDashboardView", method = RequestMethod.POST)
	public String adminLogin(@RequestParam String id, @RequestParam String password, Model model,
			@RequestParam(value = "dashboard", required = false) String dashboard,
			@RequestParam(value = "liveData", required = false) String liveData) {
		String res = adminRepo.adminLoginValidation(id, password);
        log.info("Admin Log in done using ID {} and password {}", id, password);
		if (res != null) {
			if (dashboard!=null) {
				return "adminView";
			} else {
				return "dataFeed";
			}
		} else {
			model.addAttribute("errorMessage", "Please input valid id and pasword");
			return "adminLogin";
		}
	}

	@RequestMapping(value = "/adminDashboardView", method = RequestMethod.GET)
	public String adminLoginValidationDisableForward(Model model) {
		return "adminLogin";
	}

	@RequestMapping(value = "/updateCategory", method = RequestMethod.POST)
	public String updateCategory(@RequestParam String regid, Model model) throws IOException {
		String[] listOfId = regid.split(",");

		// Convert the array to a list
		List<Long> idList = new ArrayList<>();
		for (String value : listOfId) {
			try {
                log.info("Player category updated to List A for reg ID {}", value);
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
	public String paymentUpdate(@RequestParam String regid, Model model)
			throws IOException, MessagingException, TemplateException {
		String[] listOfId = regid.split(",");

		// Convert the array to a list
		List<Long> idList = new ArrayList<>();
		for (String value : listOfId) {
			try {
                log.info("Player Payment updated for reg ID {}", value);
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

	@GetMapping("/liveDataFeeding")
	public String test() {
		return "adminLogin";
	}

	@PostMapping("/soldAmountAndTeam")
	public String saveSoldTeamAndAmount(@RequestParam("id") Long id, @RequestParam("soldAmount") Long soldAmount,
			@RequestParam("team") String team, Model model) throws Exception {
		if (team.equals("Team List")) {
			model.addAttribute("errorMessage", "Please Select Sold Team");
			return "dataFeed";
		}
		registrationController.saveSoldTeamAndAmount(id, soldAmount, team);
		model.addAttribute("errorMessage", "Data Updated For Reg ID : " + id);
		return "dataFeed";
	}
	
}
