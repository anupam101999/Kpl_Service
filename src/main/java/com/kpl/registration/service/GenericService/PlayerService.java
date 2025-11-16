package com.kpl.registration.service.GenericService;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import com.kpl.registration.dto.AdminReqVO;
import com.kpl.registration.dto.RegistrationResponse;
import com.kpl.registration.entity.AdminInfo;
import com.kpl.registration.entity.PlayerInfo;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public interface PlayerService {
  //	GenericVO savePlayerInfo(PlayerRequetVO playerRequetVO, byte[] imageData, byte[]
  // docDataFront,byte[] docDataBack)
  //			throws IOException, MessagingException, TemplateException ;

  RegistrationResponse getRegistrationStatus(String searchParam, String password);

}
