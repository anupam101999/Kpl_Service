package com.kpl.registration.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kpl.registration.dto.AdminReqVO;
import com.kpl.registration.dto.GenericVO;
import com.kpl.registration.dto.PlayerRequetVO;
import com.kpl.registration.dto.RegistrationResponse;
import com.kpl.registration.entity.AdminInfo;
import com.kpl.registration.entity.ImageInfo;
import com.kpl.registration.repository.ImageRepo;
import com.kpl.registration.repository.PlayerRepository;
import com.kpl.registration.service.PlayerService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/kpl/registration/api")
@CrossOrigin(origins = "*")
public class RegistrationController {
	@Autowired
	PlayerService playerService;
	@Autowired
	ImageRepo imageRepo;
	@Autowired
	PlayerRepository playerRepository;
	

	public static final String CONTENT_DISPOSITION = "Content-Disposition";
	public static final String PDF_MIME_TYPE = "application/pdf";
	public static final String ATTACHMENT_FILENAME = "attachment; filename=";
	public static final String GENERATION_DATE = "yyMMdd";
	public static final String DATE_FORMAT = "ddMMyyyy";

	@GetMapping("/status")
	public String ApplicationStatus() {
		return "Application is Running";
	}

	@PostMapping("/completeRegistration")
	public GenericVO saveRegistration(@RequestBody PlayerRequetVO playerRequetVO,
			@RequestParam("file") MultipartFile file, @RequestParam("file") MultipartFile docFile) throws IOException {
		byte[] imageData = file.getBytes();
		byte[] docData = docFile.getBytes();
		return playerService.savePlayerInfo(playerRequetVO, imageData, docData);
	}

	@GetMapping("/getYourRegistrationStatus")
	public RegistrationResponse registrationStatus(@RequestParam String id, @RequestParam String password)
			throws IOException {
		return playerService.getRegistrationStatus(id, password);
	}

//	category specific player PDF
	
	@GetMapping("generate/playerPdf")
	public void generueSpecificPlayerPdf(HttpServletResponse response, @RequestParam("generue") String generue)
			throws Exception {

		response.setContentType(PDF_MIME_TYPE);
		String headerKey = CONTENT_DISPOSITION;
		String headerValue ="owner"+ generue + ".pdf";
		response.setHeader(headerKey, headerValue);
		playerService.generatePdfByClassification(response, generue);

	}

	@PostMapping("/masterImage")
	public String uploadImage(@RequestParam("file") MultipartFile file, @RequestParam String name) throws IOException {
		ImageInfo imageInfo = new ImageInfo();
		imageInfo.setImageName(name);
		imageInfo.setImage(file.getBytes());
		imageRepo.save(imageInfo);
		return "Image Master data has been uploaded successfully";
	}

//	Download category specific image
	
	@GetMapping("/downloadGenerueSpImage")
	public String downloadAllPlayerImage(@RequestParam String generue) throws IOException {
		List<byte[]> images = playerRepository.findAllImageByGenerue(generue);
		final String FOLDER_PATH = "/" + generue + "_images";
		String homeDirPath = FileUtils.getUserDirectoryPath();
		File directory = new File(homeDirPath + FOLDER_PATH);
		if (!directory.exists()) {
			directory.mkdirs();
		}

		for (int i = 0; i < images.size(); i++) {
			byte[] imageData = images.get(i);
			FileOutputStream fileOutputStream = new FileOutputStream(directory + "/" + (i + 1) + ".png");
			fileOutputStream.write(imageData);
			fileOutputStream.close();
		}
		return "All images realted to " + generue + " section has been downloaded!, please check your C/Users/yourName/"
				+ generue + "_images folder";

	}

//	update player category to List A
	
	@PutMapping("/specialPlayer")
	public String updateSpecialPlayerCategory(@RequestParam List<Long> registartionIDS) throws IOException {
		playerRepository.updatePlayerCategory(registartionIDS);
		return "players category has been change to List A";
	}

//	update payment validation
	
	@PutMapping("/paymentUpdate")
	public String paymentUpdate(@RequestParam List<Long> registartionIDS) throws IOException {
		playerRepository.paymentUpdate(registartionIDS);
		return "payment details updated";
	}
	
	@GetMapping("generate/AllplayerPdf")
	public void generueSpecificPlayerPdfForCommitte(HttpServletResponse response) throws Exception {

		response.setContentType(PDF_MIME_TYPE);
		String headerKey = CONTENT_DISPOSITION;
		String headerValue = "AllPlayer" + ".pdf";
		response.setHeader(headerKey, headerValue);
		playerService.generueSpecificPlayerPdfForCommitte(response);

	}

	@GetMapping("generate/finalPlayerListPdf")
	public void generueFinalPlayerPdf(HttpServletResponse response, @RequestParam("generue") String generue)
			throws Exception {

		response.setContentType(PDF_MIME_TYPE);
		String headerKey = CONTENT_DISPOSITION;
		String headerValue = "committe" + generue + ".pdf";
		response.setHeader(headerKey, headerValue);
		playerService.generateFinalPlayerPdf(response, generue);

	}

	@GetMapping("/downloadAllDocImage")
	public String downloadAllDocImage() throws IOException {
		List<byte[]> images = playerRepository.findAllDoc();
		final String FOLDER_PATH = "/" + "kpl_doc" + "_images";
		String homeDirPath = FileUtils.getUserDirectoryPath();
		File directory = new File(homeDirPath + FOLDER_PATH);
		if (!directory.exists()) {
			directory.mkdirs();
		}

		for (int i = 0; i < images.size(); i++) {
			byte[] imageData = images.get(i);
			if (imageData != null) {
				FileOutputStream fileOutputStream = new FileOutputStream(directory + "/" + (i + 1) + ".png");
				fileOutputStream.write(imageData);
				fileOutputStream.close();
			}
		}
		return "All images realted to doc section has been downloaded!, please check your C/Users/yourName/kpl_doc_images folder";

	}

	@GetMapping("/passwordReset")
	public String resetPassword(@RequestParam Long phNumber, @RequestParam Long pinCode, @RequestParam Long aadharNo,
			@RequestParam String password) throws IOException {
		if (phNumber.toString().length() != 10) {
			return "Phone Number Must be 10 digit";
		}
		if (pinCode.toString().length() != 6) {
			return "Pin Code Must be 6 digit";
		}
		if (aadharNo.toString().length() != 12) {
			return "Aadhaar Number Must be 12 digit";
		}
		var length = password.toString().length();
		if (!(length > 3 && length < 9)) {
			return "Password Must be between 4 to 8 character";
		}
		var phNo = playerRepository.findByPhNumber(phNumber);
		var phNobyPIN = playerRepository.findByPinCode(pinCode);
		var phNobyaadhar = playerRepository.findByAaddharNo(aadharNo);
		if (phNo != null) {
			if (phNo.equals(phNobyPIN)) {
				if (phNo.equals(phNobyaadhar)) {
					playerRepository.updatePassword(password, phNumber);
					return "Success";
				} else {
					return "Incorrect Aadhaar Number";
				}
			} else {
				return "Incorrect Pin Code";
			}
		} else {
			return "Incorrect Phone Number";
		}
	}

	@PostMapping("/saveAdmin")
	public AdminInfo saveAdmin(@RequestBody AdminReqVO adminReqVO) throws IOException {
		return playerService.saveAdminDetails(adminReqVO);		 
	}

}