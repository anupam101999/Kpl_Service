package com.kpl.registration.service.JwtService;

import com.kpl.registration.dto.AdminReqVO;
import com.kpl.registration.entity.AdminInfo;

public interface SaveUser {
    AdminInfo saveAdminDetails(AdminReqVO adminReqVO);

}
