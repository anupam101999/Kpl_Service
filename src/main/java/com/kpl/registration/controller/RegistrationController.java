package com.kpl.registration.controller;


import java.io.IOException;

//import com.kpl.registration.configJWT.JwtService;
import com.kpl.registration.dto.*;
import com.kpl.registration.entity.*;
import com.kpl.registration.service.GenericService.MailSendService;
import com.kpl.registration.service.Pdf.PlayerServicePdf;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.kpl.registration.repository.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.mail.MessagingException;

import org.modelmapper.ModelMapper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import com.kpl.registration.service.GenericService.PlayerService;
import com.kpl.registration.service.GenericService.PlayerServiceImpl;

import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RestController
@RequestMapping("/kpl/registration/api")
@CrossOrigin(origins = "*")
public class RegistrationController {
    @Autowired
    PlayerService playerService;
    @Autowired
    MailSendService mailSendService;
    @Autowired
    PlayerServicePdf playerServicePdf;
    @Autowired
    PlayerServiceImpl playerServiceImpl;
    @Autowired
    ImageRepo imageRepo;
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    OwnerRepo ownerRepo;
    @Autowired
    DocRepo docRepo;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    EventRepo eventRepo;
    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    PlayerRepo2024 playerRepo2024;
    public static final String CONTENT_DISPOSITION = "Content-Disposition";
    public static final String PDF_MIME_TYPE = "application/pdf";
    public static final String ATTACHMENT_FILENAME = "attachment; filename=";
    public static final String GENERATION_DATE = "yyMMdd";
    public static final String DATE_FORMAT = "ddMMyyyy";
    String telegramBotUrl = "https://api.telegram.org/bot6637753416:AAHb7DHnfrvEl6Aje0RfyrumAkZjZglxXHU/sendmessage?chat_id=@kpl2023updates&text=";
    String telegramTestBotUrl = "https://api.telegram.org/bot6637753416:AAHb7DHnfrvEl6Aje0RfyrumAkZjZglxXHU/sendmessage?chat_id=@test2017Grp&text=";

    @GetMapping("/status")
    public String ApplicationStatus() {
        return "Application is Running";
    }

//	@PostMapping("/completeRegistration")
//	public GenericVO saveRegistration(@RequestBody PlayerRequetVO playerRequetVO,
//			@RequestParam("file") MultipartFile file, @RequestParam("file") MultipartFile docFileFront,
//			@RequestParam("file") MultipartFile docFileBack) throws IOException, MessagingException, TemplateException {
//		byte[] imageData = file.getBytes();
//		byte[] docDataFront = docFileFront.getBytes();
//		byte[] docDataBack = docFileBack.getBytes();
//		return playerService.savePlayerInfo(playerRequetVO, imageData, docDataFront, docDataBack);
//	}

    @GetMapping("/getYourRegistrationStatus")
    public RegistrationResponse registrationStatus(@RequestParam String id, @RequestParam String password)
            throws IOException {
        return playerService.getRegistrationStatus(id, password);
    }

    // category specific player PDF for owners

    @GetMapping("generate/playerPdf")
    public void generueSpecificPlayerPdf(HttpServletResponse response, @RequestParam("generue") String generue,
                                         Model model) throws Exception {

        response.setContentType(PDF_MIME_TYPE);
        String headerKey = CONTENT_DISPOSITION;
        String headerValue = "owner" + generue + ".pdf";
        response.setHeader(headerKey, headerValue);
        model.addAttribute("errorMessage", "PDF download is processing");
        playerServicePdf.generatePdfByClassification(response, generue);

    }

//	for committee

    @GetMapping("generate/finalPlayerListPdf")
    public void generueFinalPlayerPdf(HttpServletResponse response, @RequestParam("generue") String generue,
                                      Model model) throws Exception {

        response.setContentType(PDF_MIME_TYPE);
        String headerKey = CONTENT_DISPOSITION;
        String headerValue = "committe" + generue + ".pdf";
        response.setHeader(headerKey, headerValue);
        model.addAttribute("errorMessage", "PDF download is processing");
        playerServicePdf.generateFinalPlayerPdf(response, generue);

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
        return "players category has been changed to List A";
    }

    // update player category to emerging Player

    @PutMapping("/emergingPlayer")
    public String emergingPlayer() throws IOException {
        var playerId = playerRepository.emergingPlayerList();
        playerRepository.updateEmergingPlayer(playerId);
        return "players category has been changed to emerging Player";
    }

    // update payment validation

    @PutMapping("/paymentUpdate")
    public String paymentUpdate(@RequestParam List<Long> registartionIDS)
            throws IOException, MessagingException, TemplateException {
        playerRepository.paymentUpdate(registartionIDS);
        mailSendService.sendMailOnPaymentValidation(registartionIDS);
        return "payment details updated";
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
                    mailSendService.resetPasswordMail(phNumber);
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


    @GetMapping("/downloadGenerueSpImage")
    public ResponseEntity<Resource> downloadImages(Model model) {
        // Retrieve the list of images (assuming you have it available)
        List<DocInfo> docInfo = docRepo.findAllImageByGenerue();

        try {
            // Create a temporary file for the ZIP
            File tempFile = File.createTempFile("images", ".zip");
            FileOutputStream fos = new FileOutputStream(tempFile);
            ZipOutputStream zipOut = new ZipOutputStream(fos);

            // Add each image to the ZIP file
            for (int i = 0; i < docInfo.size(); i++) {
                byte[] imageData = docInfo.get(i).getImage();
                String fileName = docInfo.get(i).getRegistrationId() + ".jpg";

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

            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + "playerPhoto" + ".zip");
            if (docInfo.size() > 0) {
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

    @GetMapping("/downloadAllDocFrontImage")
    public ResponseEntity<Resource> downloadAllDocImageFront(Model model) throws IOException {
        List<byte[]> images = docRepo.findAllDocFront();
        List<Long> regID = playerRepository.findAllDocImageFrontRegID();
        try {
            // Create a temporary file for the ZIP
            File tempFile = File.createTempFile("images", ".zip");
            FileOutputStream fos = new FileOutputStream(tempFile);
            ZipOutputStream zipOut = new ZipOutputStream(fos);

            // Add each image to the ZIP file
            for (int i = 0; i < images.size(); i++) {
                byte[] imageData = images.get(i);
                String fileName = (regID.get(i)) + ".jpg";

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
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=documentsFront.zip");

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

    @GetMapping("/downloadAllDocBackImage")
    public ResponseEntity<Resource> downloadAllDocImageBack(Model model) throws IOException {
        List<byte[]> images = docRepo.findAllDocBack();
        List<Long> regID = playerRepository.findAllDocImageFrontRegID();
        try {
            // Create a temporary file for the ZIP
            File tempFile = File.createTempFile("images", ".zip");
            FileOutputStream fos = new FileOutputStream(tempFile);
            ZipOutputStream zipOut = new ZipOutputStream(fos);

            // Add each image to the ZIP file
            for (int i = 0; i < images.size(); i++) {
                byte[] imageData = images.get(i);
                String fileName = (regID.get(i)) + ".jpg";

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
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=documentsBack.zip");

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


    @GetMapping("/player")
    private List<PlayerVO> getSamplePlayer(String soldTeam) {
        // Replace this with your own data source or logic for fetching employees
        var allplayerInfo = playerRepository.findbyTeam(soldTeam);
        List<PlayerVO> playerVOList = new ArrayList<>();
        for (PlayerInfo playerInfo : allplayerInfo) {
            var playerVO = new PlayerVO();
            playerVO.setName(playerInfo.getPlayerFirstName() + " " + playerInfo.getPlayerLastName());
            playerVO.setUID(playerInfo.getPhNo().toString());
            playerVOList.add(playerVO);
        }
        return playerVOList;
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
        log.info(" /kpl/registration/api/findAll");
//		String messageString="Test Message";
//		  String response = restTemplate.getForObject(apiUrl+messageString, String.class);
//		  System.out.println("Response from the API: " + response);
        return playerInfoVOList;
    }

    //	API to search player based on Registration ID
    @GetMapping("/search")
    public Object searchDataById(@RequestParam("id") Long id, Model model) {
        var searchData = playerRepository.findDataByregistrationId(Long.valueOf(id));
        var searchImage = docRepo.findByregistrationId(Long.valueOf(id));
        var livesearch = new LiveSearchVO();

        if (searchData != null) {
            livesearch.setRegistrationId(searchData.getRegistrationId());
            livesearch.setDob(searchData.getDateOfBirth());
            livesearch.setCategory(searchData.getGenerue());
            livesearch.setImage(searchImage);
            livesearch.setLocation(searchData.getLocation());
            livesearch.setPlayerAddress(searchData.getPlayerAddress());
            livesearch.setPlayerFirstName(searchData.getPlayerFirstName());
            livesearch.setPlayerLastName(searchData.getPlayerLastName());
            log.info("Player name is " + searchData.getPlayerFirstName() + " and search ID is " + id);
            model.addAttribute("data", livesearch);
        }
        return livesearch;
    }


    //	@Scheduled(cron = "0 30 18 * * *", zone = "UTC")
    @GetMapping("/apiTrigger")
    public void todayRegList() {
        log.info("Daily API trigger");
        var todayTime = LocalDateTime.now();
        var yesterdayTime = LocalDateTime.now().minusDays(1);
        List<String> list = new ArrayList<>();
        var message = "List of player who have registered Today : ";
        List<PlayerInfo> playerInfo = playerRepository.todaySignedUpPlayerList(todayTime, yesterdayTime);
        for (int i = 0; i < playerInfo.size(); i++) {
            var info = (i + 1) + ". " + playerInfo.get(i).getPlayerFirstName() + " "
                    + playerInfo.get(i).getPlayerLastName() + " ,Reg ID : " + playerInfo.get(i).getRegistrationId();
            list.add(info);
        }

        restTemplate.getForObject(telegramBotUrl + message + " " + list, String.class);

        List<PlayerInfo> payment = playerRepository.paymentRem();
        List<String> paymentlist = new ArrayList<>();
        var paymentmessage = "Players who have not paid the registrartion fees : ";
        for (int i = 0; i < payment.size(); i++) {
            var info = (i + 1) + ". " + payment.get(i).getPlayerFirstName() + " " + payment.get(i).getPlayerLastName()
                    + " ,Reg ID : " + payment.get(i).getRegistrationId() + " ,Phone Num : " + payment.get(i).getPhNo();
            paymentlist.add(info);
        }

        restTemplate.getForObject(telegramBotUrl + paymentmessage + " " + paymentlist, String.class);
    }

    @DeleteMapping("/deleteRegistration")
    public String saveSoldTeamAndAmount(@RequestParam("id") Long id) throws Exception {
        var player = playerRepository.findById(id);
        String messageString = "Hey Support team @RAVVAN23 @Kalajaduu13 @emotionalclown Registration deleted for User Name : "
                + player.get().getPlayerFirstName() + " " + player.get().getPlayerLastName();
        playerRepository.deleteById(id);
        docRepo.deleteById(id);
        restTemplate.getForObject(telegramBotUrl + messageString, String.class);
        return "Player Wiped from Database";
    }


    @GetMapping("/aadharCheck")
    public String aadharCheck(@RequestParam Long aadharNo) {
        String aadhaarCheck = playerRepository.findByAadhaarID(aadharNo);
        if (aadhaarCheck != null) {
            return "ok";
        }
        return "";
    }

    @GetMapping("/phNoCheck")
    public String phNoCheck(@RequestParam Long phNo) {
        Long phNumberUniqueCheck = playerRepository.findByPhNumber(phNo);
        if (phNumberUniqueCheck != null) {
            return "ok";
        }
        return "";
    }

    @GetMapping("/emailCheck")
    public String emailCheck(@RequestParam String mail) {
        String emailUniqueCheck = playerRepository.findByEmailID(mail);
        if (emailUniqueCheck != null) {
            return "ok";
        }
        return "";
    }

    @PutMapping("/updateOwnImage")
    public String updateOwnImage(@RequestParam("id") Long id, @RequestParam("file") MultipartFile file)
            throws IOException, MessagingException, TemplateException {
        byte[] imageData = file.getBytes();
        docRepo.updateOwnImage(id, imageData);
        return "Image updated";
    }

    @GetMapping("/eventCount")
    public String eventCount(@RequestParam("id") Integer id) throws IOException, MessagingException, TemplateException {
        switch (id) {
            case 1:
                eventRepo.updateRulesPdfCount();
                break;
            case 2:
                eventRepo.updateTeamPdfCount();
                break;
            default:
                eventRepo.updateOwnerPdfCount();
                break;
        }
        return "count updated";
    }

    @GetMapping("/eventCountJson")
    public List<EventCount> eventCountJson() {
        return eventRepo.findAll();
    }

    @GetMapping("/phNumCount")
    public String phNumCount(@RequestParam Long phNo) {
        var count = playerRepository.findCount(phNo);
        if (count > 1) {
            return "fail";
        } else {
            return "ok";
        }
    }

//	@Scheduled(fixedRate = 900000)
    // @GetMapping("/mailTrigger")
    // public void mailTriggerOnRegistration() throws Exception {
    // 	log.info("Mail trigger in each 15 min API trigger");
    // 	var timeNow = LocalDateTime.now();
    // 	var time15minBack = LocalDateTime.now().minusMinutes(15);
    // 	List<PlayerInfo> playerInfo = playerRepository.todaySignedUp15minPlayerList(timeNow, time15minBack);

    // 	for (int i = 0; i < playerInfo.size(); i++) {
    // 		playerServiceImpl.sendMail(playerInfo.get(i));
    // 		String messageString = "Registration mail shooted for user name : " + playerInfo.get(i).getPlayerFirstName()
    // 				+ " " + playerInfo.get(i).getPlayerLastName();
    // 		restTemplate.getForObject(telegramBotUrl + messageString, String.class);
    // 	}
    // }

    @PutMapping("/paymentUpdateRevoke")
    public String paymentUpdateRevoke(@RequestParam Long registartionIDS)
            throws IOException, MessagingException, TemplateException {
        var player = playerRepository.findDataByregistrationId(registartionIDS);
        String messageString = "Registration revoked for user name : " + player.getPlayerFirstName()
                + " " + player.getPlayerLastName();
        restTemplate.getForObject(telegramBotUrl + messageString, String.class);
        playerRepository.paymentUpdateRevoke(registartionIDS);
        return "payment details revoked";
    }

    @GetMapping("/apiTriggerAll")
    public void apiTriggerAll(@RequestParam Long lb, @RequestParam Long ub) {
        List<PlayerInfo> paymentDone = playerRepository.paymentDone(lb, ub);
        List<String> paymentDonelist = new ArrayList<>();
        var paymentDonemessage = "Players who have paid the registrartion fees : ";
        for (int i = 0; i < paymentDone.size(); i++) {
            var info = (i + 1) + ". " + paymentDone.get(i).getPlayerFirstName() + " "
                    + paymentDone.get(i).getPlayerLastName();
            paymentDonelist.add(info);
        }

        restTemplate.getForObject(telegramBotUrl + paymentDonemessage + " " + paymentDonelist, String.class);
    }

    // @Scheduled(fixedRate = 300000)
    // @GetMapping("/mailTriggerOnSell")
    // public void mailTriggerOnSell() throws Exception {
    // 	log.info("Mail trigger in each 5 min API trigger");
    // 	var timeNow = LocalDateTime.now(Clock.systemUTC());
    // 	var time5minBack = LocalDateTime.now(Clock.systemUTC()).minusMinutes(5);
    // 	log.info(timeNow.toString());
    // 	List<PlayerInfo> playerInfo = playerRepository.sellOnLast5min(timeNow, time5minBack);
    // 	for (int i = 0; i < playerInfo.size(); i++) {
    // 		playerService.sendMailOnSold(playerInfo.get(i));
    // 	}
    // }


    @PostMapping("/upload")
    public String updateOwnImage(@RequestParam("file") List<MultipartFile> file)
            throws IOException {
        for (int i = 1; i <= file.size(); i++) {
            byte[] imageData = file.get(i-1).getBytes();
            playerRepo2024.updateImage(i, imageData);
            log.info("image update : "+(i));
        }

        return "Image updated";
    }

    @GetMapping("/search2024")
    public Object search2024(@RequestParam("id") Long id, Model model) {
        var searchData = playerRepo2024.findById(Long.valueOf(id));
        var livesearch = new LiveSearchVO();

        if (searchData.isPresent()) {
            livesearch.setRegistrationId(searchData.get().getRegId());
            livesearch.setPlayerFirstName(searchData.get().getPlayerFirstName());
            livesearch.setPlayerLastName(searchData.get().getPlayerLastName());
            livesearch.setPlayerAddress(searchData.get().getPlayerAddress());
            livesearch.setCategory(searchData.get().getCategory());
            livesearch.setLocation(searchData.get().getPlayerLocationCategory());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);

            LocalDateTime localDateTime = LocalDateTime.parse(searchData.get().getDateOfBirth(), formatter);
            livesearch.setDob(LocalDate.from(localDateTime));
            livesearch.setImage(searchData.get().getImage());
            model.addAttribute("data", livesearch);
        }
        return livesearch;

    }

    @PostMapping("/uploadSheet")
    public void uploadSheet(@RequestParam("file") MultipartFile file) throws IOException {
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // Skip header row
            var timeStamp = row.getCell(0).getDateCellValue();
            var email = row.getCell(1).getStringCellValue();
            var firstName = row.getCell(2).getStringCellValue();
            var lastName = row.getCell(3).getStringCellValue();
            var dateOfBirth = row.getCell(4).getDateCellValue();
            var location = row.getCell(5).getStringCellValue();
            var category = row.getCell(6).getStringCellValue();
            var address = row.getCell(7).getStringCellValue();
            var aadhaar = row.getCell(8).getNumericCellValue();
            var pin = row.getCell(9).getNumericCellValue();
            var phNum = row.getCell(10).getNumericCellValue();
            var paid = row.getCell(13).getStringCellValue();

                var p = new PlayerRegistration();
                p.setRegistrationTime(String.valueOf(timeStamp));
                p.setEmailId(email);
                p.setPlayerFirstName(firstName);
                p.setPlayerLastName(lastName);
                p.setDateOfBirth(String.valueOf(dateOfBirth));
                p.setPlayerLocationCategory(location);
                p.setCategory(category);
                p.setPlayerAddress(address);
                p.setAadhaar((long) aadhaar);
                p.setPinCode(Long.valueOf((long) pin));
                p.setPhNo(Long.valueOf((long) phNum));
                p.setPaid(paid);
                playerRepo2024.save(p);

        }
        workbook.close();

    }

    @PostMapping("/soldAmountandTeam")
    public String saveSoldTeamAndAmount(@RequestParam("id") Long regID, @RequestParam("soldAmount") Long soldAmount,
                                        @RequestParam("team") String team) throws Exception {
        var updationTime = LocalDateTime.now(Clock.systemUTC());
//        var info = playerRepo2024.findInfo(regID);
//        String text = info.getPlayerFirstName() + " " + info.getPlayerLastName() + " have been Sold to Team " + team + " for Rs. " + soldAmount;
        // restTemplate.getForObject(telegramBotUrl + text, String.class);
        playerRepo2024.updateSoldamountAndTeam(regID, soldAmount, team, updationTime);
        return "success";
    }

    @GetMapping("/liveTeamData")
    public LinkedList<LiveDataVO> liveTeamData() {
        LinkedList<LiveDataVO> liveDataVOList = new LinkedList<>();
//		LinkedList<String> teamList = playerRepository.getDistinctTeam();
        LinkedList<String> teamList = ownerRepo.getDistinctTeam();
        for (String s : teamList) {
            LiveDataVO liveDataVO = new LiveDataVO();
            Long overSeasPlayerCount = playerRepo2024.countOfOverSeasPlayer(s);
            Long localPlayerCount = playerRepo2024.countOfLocalPlayer(s);
            Long totalSpendMoney = playerRepo2024.totalMoneySpend(s);
            totalSpendMoney = totalSpendMoney != null ? totalSpendMoney : 0;
            Long remBalance = 10000 - totalSpendMoney;

            liveDataVO.setTeamName(s);
            liveDataVO.setOverSeasplayer(overSeasPlayerCount);
            liveDataVO.setLocalplayer(localPlayerCount);
            liveDataVO.setMoneyspend(totalSpendMoney);
            liveDataVO.setMoneyRem(remBalance);
            long maxBetOnSinglePlayer = 0;
            long playerCountRem = 18 - (overSeasPlayerCount + localPlayerCount);
            if (playerCountRem <= 0) {
                maxBetOnSinglePlayer = remBalance;
            } else {
                maxBetOnSinglePlayer = remBalance - ((playerCountRem - 1) * 50);
            }

            liveDataVO.setMaxiumBetAmountOnSinglePlayer(maxBetOnSinglePlayer);
            liveDataVOList.add(liveDataVO);
        }
        log.info("/kpl/registration/api/liveTeamData");
        return liveDataVOList;
    }

    @GetMapping("/soldPlayerList")
    public List<String> soldPlayerList() {
        List<String> list = new ArrayList<>();
        var playerInfo = playerRepo2024.findBySoldUpdateTime();
        for (int i = 0; i < playerInfo.size(); i++) {
            var name = playerInfo.get(i).getPlayerFirstName() + " " + playerInfo.get(i).getPlayerLastName()
                    + " sold to team " + playerInfo.get(i).getSoldTeam() + " for Rs."
                    + playerInfo.get(i).getSoldAmount();
            list.add(name);
        }
        return list;
    }

    @GetMapping("/teamList")
    public void teamListPdf(@RequestParam("soldTeam") String soldTeam, HttpServletResponse response) throws Exception {
        response.setContentType(PDF_MIME_TYPE);
        String headerKey = CONTENT_DISPOSITION;
        String headerValue = soldTeam + ".pdf";
        response.setHeader(headerKey, headerValue);
        log.info(" /kpl/registration/api/teamList");
        playerServicePdf.generateTeamListPdf(response, soldTeam);

    }

    @GetMapping("generate/AllplayerPdf")
    public void generueSpecificPlayerPdfForCommitte(HttpServletResponse response, Model model) throws Exception {

        response.setContentType(PDF_MIME_TYPE);
        String headerKey = CONTENT_DISPOSITION;
        String headerValue = "AllPlayer" + ".pdf";
        response.setHeader(headerKey, headerValue);
        model.addAttribute("errorMessage", "PDF download is processing");
        playerServicePdf.generueSpecificPlayerPdfForCommitte(response);

    }
}