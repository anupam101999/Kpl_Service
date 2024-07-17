 package com.kpl.registration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
 public class AdminReqVO {
	 private Long adminId;
	 private String id;
	 private String password;
 }