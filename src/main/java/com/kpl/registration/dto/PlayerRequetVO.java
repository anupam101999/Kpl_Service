 package com.kpl.registration.dto;

 import lombok.AllArgsConstructor;
 import lombok.Data;
 import lombok.NoArgsConstructor;

 import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
 public class PlayerRequetVO {
	 private String playerFirstName;
	 private String playerLastName;
	 private String playerAddress;
	 private Long pinCode;
	 private Long aadharNo;
	 private Long phNo;
	 private String emailId;
	 private String generue;
	 private String password;
	 private LocalDate dob;
	 private String location;
 }