package com.kpl.registration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LiveSearchVO {
	private Long registrationId;
	private String playerFirstName;
	private String playerLastName;
	private String playerAddress;
	private String category;
	private LocalDate dob;
	private String location;
	private byte[] image;
}