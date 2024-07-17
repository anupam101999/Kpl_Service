package com.kpl.registration.configJWT;

import com.kpl.registration.dto.UserInfo;
import com.kpl.registration.entity.AdminInfo;
import com.kpl.registration.repository.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {
  @Autowired private AdminRepo adminRepo;
  @Autowired PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<AdminInfo> userInfo = adminRepo.findByUserId(username);
    UserInfo u = new UserInfo();
    if (userInfo.isPresent()) {
      u.setUsername(username);
      u.setPassword(passwordEncoder.encode("abc"));
      u.setRoleCode(userInfo.get().getRoleCode());
      return new CustomUserDetails(u);
    } else {
      throw new UsernameNotFoundException("could not found username");
    }
  }
}
