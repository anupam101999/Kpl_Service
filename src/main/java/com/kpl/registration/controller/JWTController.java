package com.kpl.registration.controller;

import com.kpl.registration.configJWT.JwtService;
import com.kpl.registration.dto.AdminReqVO;
import com.kpl.registration.dto.JWTResponse;
import com.kpl.registration.entity.AdminInfo;
import com.kpl.registration.exception.KPLException;
import com.kpl.registration.repository.AdminRepo;
import com.kpl.registration.service.JwtService.SaveUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Component
@RequestMapping("/kpl/jwt/api")
@RestController
@CrossOrigin(origins = "*")
public class JWTController {
    @Autowired
    SaveUser saveUser;
    @Autowired
    AdminRepo adminRepo;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtService jwtService;

    @PostMapping("/saveAdmin")
    public AdminInfo saveAdmin(@RequestBody AdminReqVO adminReqVO) {
        return saveUser.saveAdminDetails(adminReqVO);
    }

    @GetMapping("/validId/{id}/{password}")
    public JWTResponse getRoleAndUserName(@PathVariable String id, @PathVariable String password) throws Exception {
        var res = adminRepo.findByIdAndPassword(id, password);
        if (res.isPresent()) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(res.get().getId(), "abc"));
            return JWTResponse.builder().jwtCode(jwtService.generateToken(id, password, res.get().getRoleCode())).build();
        } else {
            throw new KPLException("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
    }
}
