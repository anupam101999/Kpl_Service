package com.kpl.registration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerInfoVO {

	private Long registrationId;
	private String playerFirstName;
	private String playerLastName;
	private String playerAddress;
	private Long pinCode;
	private Long aadharNo;
	private Long phNo;
	private String emailId;
	private String generue;
	private LocalDateTime registrationTime;
	private String location;
	private LocalDate dateOfBirth;
	private String paymentValidation;
	private String password;
	private Long soldAmount;
	private String soldTeam;
}
