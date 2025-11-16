package com.kpl.registration.service.GenericService;

import com.kpl.registration.entity.PlayerInfo;
import com.kpl.registration.repository.OwnerRepo;
import com.kpl.registration.repository.PlayerRepository;
import freemarker.core.ParseException;
import freemarker.template.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class MailSendServiceImpl implements MailSendService{
    @Autowired
    Configuration config;
    @Value("${spring.mail.username}")
    private String emailUsername;
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    OwnerRepo ownerRepo;
    @Autowired
    PlayerRepository playerRepository;

    public void sendMail(PlayerInfo playerInfo) throws MessagingException, TemplateNotFoundException,
            MalformedTemplateNameException, ParseException, IOException, TemplateException {
        Map<String, Object> model = new HashMap<>();
        String phNu = playerInfo.getPhNo().toString();
        String password = playerInfo.getPassword();
        var regID = playerRepository.findByPhNu(phNu);

        model.put("regid", regID);
        model.put("firstname", playerInfo.getPlayerFirstName());
        model.put("name", playerInfo.getPlayerFirstName() + " " + playerInfo.getPlayerLastName());
        model.put("location", playerInfo.getLocation());
        model.put("mail", playerInfo.getEmailId());
        model.put("phNo", phNu);
        model.put("category", playerInfo.getGenerue());
        model.put("address", playerInfo.getPlayerAddress());
        model.put("password", password);

        var message = javaMailSender.createMimeMessage();
        var mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        Template mailTemplate = config.getTemplate("registration.ftl");
        var htmlTemp = FreeMarkerTemplateUtils.processTemplateIntoString(mailTemplate, model);

        mimeMessageHelper.setFrom(emailUsername);
        mimeMessageHelper.setTo(playerInfo.getEmailId());
        mimeMessageHelper.setText(htmlTemp, true);
        mimeMessageHelper.setSubject(playerInfo.getPlayerFirstName()
                + ",You have been registered successfully for KPL season 5 grand Auction");
        javaMailSender.send(message);

    }

    @Override
    public void sendMailOnSold(PlayerInfo playerInfo) throws MessagingException, TemplateNotFoundException,
            MalformedTemplateNameException, ParseException, IOException, TemplateException {
        Map<String, Object> model = new HashMap<>();
        var message = javaMailSender.createMimeMessage();
        var mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        mimeMessageHelper.setFrom(emailUsername);
        Template mailTemplate = config.getTemplate("soldTeam.ftl");

        model.put("firstname", playerInfo.getPlayerFirstName());
        model.put("soldteam", playerInfo.getSoldTeam());
        model.put("soldamount", playerInfo.getSoldAmount());

        var ownerInfo = ownerRepo.ownerInformation(playerInfo.getSoldTeam());
        if (ownerInfo.isPresent()) {
            model.put("ownername", ownerInfo.get().getOwnerName());
            model.put("phnum", ownerInfo.get().getOwnerPhNo().toString());
        }

        var htmlTemp = FreeMarkerTemplateUtils.processTemplateIntoString(mailTemplate, model);
        mimeMessageHelper.setTo(playerInfo.getEmailId());
        mimeMessageHelper.setText(htmlTemp, true);
        mimeMessageHelper.setSubject(playerInfo.getPlayerFirstName() + ",Hurray! you have been sold");
        log.info("name : {} , Mail ID : {}", playerInfo.getPlayerFirstName(), playerInfo.getEmailId());
        javaMailSender.send(message);

    }

    @Override
    public void sendMailOnPaymentValidation(List<Long> registartionIDS) throws MessagingException,
            TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
        Map<String, Object> model = new HashMap<>();
        var message = javaMailSender.createMimeMessage();
        var mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        mimeMessageHelper.setFrom(emailUsername);
        Template mailTemplate = config.getTemplate("paymentValidation.ftl");

        List<PlayerInfo> playerInfo = playerRepository.findByRegistriondList(registartionIDS);
        for (PlayerInfo info : playerInfo) {
            model.put("firstname", info.getPlayerFirstName());
            var htmlTemp = FreeMarkerTemplateUtils.processTemplateIntoString(mailTemplate, model);
            mimeMessageHelper.setTo(info.getEmailId());
            mimeMessageHelper.setText(htmlTemp, true);
            mimeMessageHelper
                    .setSubject(info.getPlayerFirstName() + ",Your payment status has been Updated");
            String text = "@RAVVAN23 @Kalajaduu13 @emotionalclown  Payment status updated for : "
                    + info.getPlayerFirstName() + " " + info.getPlayerLastName()
                    + " ,and his Mail ID,Reg ID are : " + info.getEmailId() + ","
                    + info.getRegistrationId();
            log.info(text);

//			restTemplate.getForObject(telegramBotUrl + text, String.class);
            javaMailSender.send(message);
        }

    }

    @Override
    public void resetPasswordMail(Long phNumber) throws MessagingException, TemplateNotFoundException,
            MalformedTemplateNameException, ParseException, IOException, TemplateException {
        Map<String, Object> model = new HashMap<>();
        var message = javaMailSender.createMimeMessage();
        var mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        mimeMessageHelper.setFrom(emailUsername);
        Template mailTemplate = config.getTemplate("passwordReset.ftl");

        var playerInfo = playerRepository.findByMailOrPhNumber(String.valueOf(phNumber));

        model.put("firstname", playerInfo.getPlayerFirstName());
        var htmlTemp = FreeMarkerTemplateUtils.processTemplateIntoString(mailTemplate, model);
        mimeMessageHelper.setTo(playerInfo.getEmailId());
        mimeMessageHelper.setText(htmlTemp, true);
        mimeMessageHelper.setSubject(playerInfo.getPlayerFirstName() + ",password changed");
        javaMailSender.send(message);

    }
}
