 package com.kpl.registration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
 public class PlayerRequetVO {
	 private String playerName;
	 private String playerAddress;
	 private Long pinCode;
	 private Long aadharNo;
	 private Long phNo;
	 private String emailId;
	 private String generue;
 }