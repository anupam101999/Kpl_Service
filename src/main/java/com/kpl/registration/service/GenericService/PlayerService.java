package com.kpl.registration.service.GenericService;

import com.kpl.registration.dto.RegistrationResponse;

public interface PlayerService {
    //	GenericVO savePlayerInfo(PlayerRequetVO playerRequetVO, byte[] imageData, byte[]
    // docDataFront,byte[] docDataBack)
    //			throws IOException, MessagingException, TemplateException ;

    RegistrationResponse getRegistrationStatus(String searchParam, String password);

}
