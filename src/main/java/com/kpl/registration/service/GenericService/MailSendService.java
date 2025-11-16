package com.kpl.registration.service.GenericService;

import com.kpl.registration.entity.PlayerInfo;
import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface MailSendService {
    void sendMailOnPaymentValidation(List<Long> registartionIDS)
            throws MessagingException,
            IOException,
            TemplateException;

    void resetPasswordMail(Long phNumber)
            throws MessagingException,
            TemplateNotFoundException,
            MalformedTemplateNameException,
            ParseException,
            IOException,
            TemplateException;

    void sendMailOnSold(PlayerInfo playerInfo)
            throws MessagingException,
            TemplateNotFoundException,
            MalformedTemplateNameException,
            ParseException,
            IOException,
            TemplateException;
}
