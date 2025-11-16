package com.kpl.registration.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationResponse {
	private Long registrationId;
	private String playerFirstName;
	private String playerLastName;
	@JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
	private LocalDateTime registrationTime;
	private String paymentValidation;
}