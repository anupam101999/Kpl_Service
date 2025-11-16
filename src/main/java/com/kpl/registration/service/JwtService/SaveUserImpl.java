package com.kpl.registration.service.JwtService;

import com.kpl.registration.dto.AdminReqVO;
import com.kpl.registration.entity.AdminInfo;
import com.kpl.registration.repository.AdminRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class SaveUserImpl implements SaveUser {
    @Autowired
    AdminRepo adminRepo;

    //Save Admin Data
    @Override
    public AdminInfo saveAdminDetails(AdminReqVO adminReqVO) {
        AdminInfo adminInfo = new AdminInfo();
        adminInfo.setId(adminReqVO.getId());
        adminInfo.setPassword(adminReqVO.getPassword());
        adminInfo.setRoleCode("R"+adminReqVO.getId());
        return adminRepo.save(adminInfo);
    }
}
