package com.kpl.registration.service.GenericService;

import com.kpl.registration.entity.PlayerInfo;
import freemarker.template.TemplateException;

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
            IOException,
            TemplateException;

    void sendMailOnSold(PlayerInfo playerInfo)
            throws MessagingException,
            IOException,
            TemplateException;
}
