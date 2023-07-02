package com.kpl.registration.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationResponse {
	private Long registrationId;
	private String playerFirstName;
	private String playerLastName;
	@JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss", timezone = "Asia/Kolkata")
	private LocalDateTime registrationTime;
	private String paymentValidation;
}