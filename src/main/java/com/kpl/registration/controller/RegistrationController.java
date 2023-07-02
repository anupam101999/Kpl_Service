package com.kpl.registration.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
import com.kpl.registration.dto.LiveDataVO;
import com.kpl.registration.dto.PlayerInfoVO;
import com.kpl.registration.dto.PlayerRequetVO;
import com.kpl.registration.dto.PlayerResponseVO;
import com.kpl.registration.dto.RegistrationResponse;
import com.kpl.registration.entity.AdminInfo;
import com.kpl.registration.entity.ImageInfo;
import com.kpl.registration.entity.PlayerInfo;
import com.kpl.registration.repository.ImageRepo;
import com.kpl.registration.repository.PlayerRepository;
import com.kpl.registration.service.PlayerService;

import freemarker.template.TemplateException;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	private ResourceLoader resourceLoader;

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
			@RequestParam("file") MultipartFile file, @RequestParam("file") MultipartFile docFile)
			throws IOException, MessagingException, TemplateException {
		byte[] imageData = file.getBytes();
		byte[] docData = docFile.getBytes();
		return playerService.savePlayerInfo(playerRequetVO, imageData, docData);
	}

	@GetMapping("/getYourRegistrationStatus")
	public RegistrationResponse registrationStatus(@RequestParam String id, @RequestParam String password)
			throws IOException {
		return playerService.getRegistrationStatus(id, password);
	}

	// category specific player PDF

	@GetMapping("generate/playerPdf")
	public void generueSpecificPlayerPdf(HttpServletResponse response, @RequestParam("generue") String generue,
			Model model) throws Exception {

		response.setContentType(PDF_MIME_TYPE);
		String headerKey = CONTENT_DISPOSITION;
		String headerValue = "owner" + generue + ".pdf";
		response.setHeader(headerKey, headerValue);
		model.addAttribute("errorMessage", "PDF download is processing");
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

	// update player category to List A

	@PutMapping("/specialPlayer")
	public String updateSpecialPlayerCategory(@RequestParam List<Long> registartionIDS) throws IOException {
		playerRepository.updatePlayerCategory(registartionIDS);
		return "players category has been change to List A";
	}

	// update payment validation

	@PutMapping("/paymentUpdate")
	public String paymentUpdate(@RequestParam List<Long> registartionIDS)
			throws IOException, MessagingException, TemplateException {
		playerRepository.paymentUpdate(registartionIDS);
		playerService.sendMailOnPaymentValidation(registartionIDS);
		return "payment details updated";
	}

	@GetMapping("generate/AllplayerPdf")
	public void generueSpecificPlayerPdfForCommitte(HttpServletResponse response, Model model) throws Exception {

		response.setContentType(PDF_MIME_TYPE);
		String headerKey = CONTENT_DISPOSITION;
		String headerValue = "AllPlayer" + ".pdf";
		response.setHeader(headerKey, headerValue);
		model.addAttribute("errorMessage", "PDF download is processing");
		playerService.generueSpecificPlayerPdfForCommitte(response);

	}

	@GetMapping("generate/finalPlayerListPdf")
	public void generueFinalPlayerPdf(HttpServletResponse response, @RequestParam("generue") String generue,
			Model model) throws Exception {

		response.setContentType(PDF_MIME_TYPE);
		String headerKey = CONTENT_DISPOSITION;
		String headerValue = "committe" + generue + ".pdf";
		response.setHeader(headerKey, headerValue);
		model.addAttribute("errorMessage", "PDF download is processing");
		playerService.generateFinalPlayerPdf(response, generue);

	}

	@GetMapping("/passwordReset")
	public String resetPassword(@RequestParam Long phNumber, @RequestParam Long pinCode, @RequestParam Long aadharNo,
			@RequestParam String password) throws IOException, MessagingException, TemplateException {
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
		var phNobyPIN = playerRepository.findByPinCode(phNumber);
		var phNobyaadhar = playerRepository.findByAaddharNo(aadharNo);
		if (phNo != null) {
			if (pinCode.equals(phNobyPIN)) {
				if (phNo.equals(phNobyaadhar)) {
					playerRepository.updatePassword(password, phNumber);
					playerService.resetPasswordMail(phNumber);
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

	@GetMapping("/downloadGenerueSpImage")
	public ResponseEntity<Resource> downloadImages(@RequestParam String generue, Model model) {
		// Retrieve the list of images (assuming you have it available)
		List<byte[]> images = playerRepository.findAllImageByGenerue(generue);

		try {
			// Create a temporary file for the ZIP
			File tempFile = File.createTempFile("images", ".zip");
			FileOutputStream fos = new FileOutputStream(tempFile);
			ZipOutputStream zipOut = new ZipOutputStream(fos);

			// Add each image to the ZIP file
			for (int i = 0; i < images.size(); i++) {
				byte[] imageData = images.get(i);
				String fileName = (i + 1) + ".jpg";

				// Create a new entry in the ZIP file
				zipOut.putNextEntry(new ZipEntry(fileName));

				// Write the image data to the ZIP file
				zipOut.write(imageData);

				// Close the current entry
				zipOut.closeEntry();
			}

			// Close the ZIP output stream
			zipOut.close();

			// Load the temporary ZIP file as a resource
			Resource zipFileResource = resourceLoader.getResource("file:" + tempFile.getAbsolutePath());

			// Set the appropriate response headers for file download
			HttpHeaders headers = new HttpHeaders();

			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + generue + ".zip");
			if (images.size() > 0) {
				model.addAttribute("errorMessage", "Zip will be downloaded shortly");
			} else {
				model.addAttribute("errorMessage", "Please check your input correctly");
			}
			// Return the ZIP file as a response entity
			return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_OCTET_STREAM)
					.body(zipFileResource);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "Issue with the file please connect with dev team");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/downloadAllDocImage")
	public ResponseEntity<Resource> downloadAllDocImage(Model model) throws IOException {
		List<byte[]> images = playerRepository.findAllDoc();

		try {
			// Create a temporary file for the ZIP
			File tempFile = File.createTempFile("images", ".zip");
			FileOutputStream fos = new FileOutputStream(tempFile);
			ZipOutputStream zipOut = new ZipOutputStream(fos);

			// Add each image to the ZIP file
			for (int i = 0; i < images.size(); i++) {
				byte[] imageData = images.get(i);
				String fileName = (i + 1) + ".jpg";

				// Create a new entry in the ZIP file
				zipOut.putNextEntry(new ZipEntry(fileName));

				// Write the image data to the ZIP file
				zipOut.write(imageData);

				// Close the current entry
				zipOut.closeEntry();
			}

			// Close the ZIP output stream
			zipOut.close();

			// Load the temporary ZIP file as a resource
			Resource zipFileResource = resourceLoader.getResource("file:" + tempFile.getAbsolutePath());

			// Set the appropriate response headers for file download
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=documents.zip");

			if (images.size() > 0) {
				model.addAttribute("errorMessage", "Zip will be downloaded shortly");
			} else {
				model.addAttribute("errorMessage", "Please check your input correctly");
			}

			// Return the ZIP file as a response entity
			return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_OCTET_STREAM)
					.body(zipFileResource);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "Issue with the file please connect with dev team");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// live data feed

	@GetMapping("/findPlayerInfo")
	public PlayerResponseVO findByRegistratonID(@RequestParam("id") Long regID) throws Exception {
		var response = playerRepository.findDataByregistrationId(regID);
		return modelMapper.map(response, PlayerResponseVO.class);
	}

	@PostMapping("/soldAmountandTeam")
	public void saveSoldTeamAndAmount(@RequestParam("id") Long regID, @RequestParam("soldAmount") Long soldAmount,
			@RequestParam("soldTeam") String soldTeam) throws Exception {
		playerRepository.updateSoldamountAndTeam(regID, soldAmount, soldTeam);
	}

	@GetMapping("/teamList")
	public void teamListPdf(@RequestParam("soldTeam") String soldTeam, HttpServletResponse response) throws Exception {
		response.setContentType(PDF_MIME_TYPE);
		String headerKey = CONTENT_DISPOSITION;
		String headerValue = "committe" + soldTeam + ".pdf";
		response.setHeader(headerKey, headerValue);
		log.info("           ::::::::::::::::             /kpl/registration/api/teamList");
		playerService.generateTeamListPdf(response, soldTeam);

	}

	@GetMapping("/liveTeamData")
	public List<LiveDataVO> liveTeamData() {
		List<LiveDataVO> liveDataVOList = new ArrayList<>();
		List<String> teamList = playerRepository.getDistinctTeam();
		for (int i = 0; i < teamList.size(); i++) {
			LiveDataVO liveDataVO = new LiveDataVO();
			Long overSeasPlayerCount = playerRepository.countOfOverSeasPlayer(teamList.get(i));
			Long localPlayerCount = playerRepository.countOfLocalPlayer(teamList.get(i));
			Long totalSpendMoney = playerRepository.totalMoneySpend(teamList.get(i));
			Long remBalance = 5000 - totalSpendMoney;

			liveDataVO.setTeamName(teamList.get(i));
			liveDataVO.setOverSeasplayer(overSeasPlayerCount);
			liveDataVO.setLocalplayer(localPlayerCount);
			liveDataVO.setMoneyspend(totalSpendMoney);
			liveDataVO.setMoneyRem(remBalance);
			liveDataVOList.add(liveDataVO);
		}
		log.info("           ::::::::::::::::             /kpl/registration/api/liveTeamData");
		return liveDataVOList;
	}

	@GetMapping("/findAll")
	public List<PlayerInfoVO> findAll() {
		List<PlayerInfoVO> playerInfoVOList = new ArrayList<>();
		List<PlayerInfo> player = playerRepository.findAll(Sort.by(Sort.Direction.ASC, "registrationId"));
		for (int i = 0; i < player.size(); i++) {
			PlayerInfoVO playerInfoVO = new PlayerInfoVO();
			modelMapper.map(player.get(i), playerInfoVO);
			playerInfoVOList.add(playerInfoVO);
		}
		log.info("           ::::::::::::::::             /kpl/registration/api/findAll");
		return playerInfoVOList;
	}
}