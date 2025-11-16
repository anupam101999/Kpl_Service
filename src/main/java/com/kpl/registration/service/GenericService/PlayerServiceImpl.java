package com.kpl.registration.service.GenericService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import com.kpl.registration.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.client.RestTemplate;

import com.kpl.registration.dto.AdminReqVO;
import com.kpl.registration.dto.GenericVO;
import com.kpl.registration.dto.PlayerRequetVO;
import com.kpl.registration.dto.RegistrationResponse;
import com.kpl.registration.entity.AdminInfo;
import com.kpl.registration.entity.DocInfo;
import com.kpl.registration.entity.PlayerInfo;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    PlayerRepo2024 playerRepo2024;

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    DocRepo docRepo;


//	String telegramBotUrl = "https://api.telegram.org/bot6637753416:AAHb7DHnfrvEl6Aje0RfyrumAkZjZglxXHU/sendmessage?chat_id=@kpl2023updates&text=";
//	String telegramTestBotUrl = "https://api.telegram.org/bot6637753416:AAHb7DHnfrvEl6Aje0RfyrumAkZjZglxXHU/sendmessage?chat_id=@test2017Grp&text=";

    // @Override
    public GenericVO savePlayerInfo(PlayerRequetVO playerRequetVO, byte[] imageData, byte[] docDataFront,
                                    byte[] docDataBack) throws IOException, MessagingException, TemplateException {
        GenericVO genericVO = new GenericVO();
        PlayerInfo playerInfo = new PlayerInfo();
        DocInfo docInfo = new DocInfo();
        playerInfo.setAadharNo(playerRequetVO.getAadharNo());

        playerInfo.setEmailId(playerRequetVO.getEmailId());
        playerInfo.setGenerue(playerRequetVO.getGenerue());
        playerInfo.setPhNo(playerRequetVO.getPhNo());
        playerInfo.setPinCode(playerRequetVO.getPinCode());
        playerInfo.setPlayerAddress(playerRequetVO.getPlayerAddress());
        playerInfo.setPlayerFirstName(playerRequetVO.getPlayerFirstName());
        playerInfo.setPlayerLastName(playerRequetVO.getPlayerLastName());

        playerInfo.setRegistrationTime(LocalDateTime.now(Clock.systemUTC()));
        playerInfo.setGenerue(playerRequetVO.getGenerue());
        playerInfo.setDateOfBirth(playerRequetVO.getDob());
        playerInfo.setPassword(playerRequetVO.getPassword());
        playerInfo.setLocation(playerRequetVO.getLocation());

        docInfo.setImage(imageData);
        docInfo.setDocImageFront(docDataFront);
        docInfo.setDocImageBack(docDataBack);

        var res = playerRepository.save(playerInfo);


        log.info("Registration ID for " + playerRequetVO.getPlayerFirstName() + " is " + res.getRegistrationId());
        docInfo.setRegistrationId(res.getRegistrationId());
        docRepo.save(docInfo);


        var count = playerRepository.findCount(playerRequetVO.getPhNo());
        if (count > 1) {
            String message = "Hey, @Insanebaby2017 there is a major issue :" + playerInfo.getPlayerFirstName() + " "
                    + playerInfo.getPlayerLastName() + " and his registration id and phone number are :"
                    + res.getRegistrationId() + " ," + playerInfo.getPhNo();
//			restTemplate.getForObject(telegramBotUrl + message, String.class);
        }


        String firstname = playerInfo.getPlayerFirstName();
        String message = "Hey, @RAVVAN23 we have a new Registration!,His name is :" + firstname + " "
                + playerInfo.getPlayerLastName() + " and his registration id and phone number are :"
                + res.getRegistrationId() + " ," + playerInfo.getPhNo();
//		restTemplate.getForObject(telegramBotUrl + message, String.class);

        log.info("User has been Registered successfully" + ",name : " + firstname);

        genericVO.setResponse("You have been successfully Registered");
        genericVO.setRegistrationID(res.getRegistrationId().toString());
        return genericVO;

    }

    @Override
    public RegistrationResponse getRegistrationStatus(String id, String password) {
        var playerExistence = playerRepository.findByMailOrPhNumberandpassword(id, password);
        if (playerExistence != null) {
            if (playerExistence.getPaymentValidation() != null) {
                playerExistence.setPaymentValidation("Completed");
            } else {
                playerExistence.setPaymentValidation("Pending Stage");
            }
            return modelMapper.map(playerExistence, RegistrationResponse.class);

        }
        RegistrationResponse registrationResponse = new RegistrationResponse();
        registrationResponse.setPlayerFirstName("No Record Found");
        registrationResponse.setPlayerLastName("No Record Found");
        return registrationResponse;
    }
}