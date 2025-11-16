package com.kpl.registration.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

 @Data
 @AllArgsConstructor
 @NoArgsConstructor
 @Entity
 @Table(name = "player_registration")
 public class PlayerInfo implements Serializable {

 /**
 *
 */
 private static final long serialVersionUID = 3980366880241829960L;

 @Id
 @GeneratedValue(strategy = GenerationType.AUTO, generator = "registration_id")
 @Column(name = "registration_id")
 private Long registrationId;

 @NonNull
 @Column(name = "player_first_name")
 private String playerFirstName;

 @NonNull
 @Column(name = "player_last_name")
 private String playerLastName;
 
 @NonNull
 @Column(name = "player_address",length = 10000)
 private String playerAddress;

 @NonNull
 @Column(name = "pin_code")
 private Long pinCode;

 @NonNull
 @Column(name = "aadhar_no")
 private Long aadharNo;

 @NonNull
 @Column(name = "ph_no")
 private Long phNo;

 @NonNull
 @Column(name = "email_id")
 private String emailId;

 @NonNull
 @Column(name = "generue")
 private String generue;

 @Column(name = "registration_time")
 private LocalDateTime registrationTime;

 @NonNull
 @Column(name = "player_location_category")
 private String location;
 
 @NonNull
 @Column(name = "date_of_birth")
 private LocalDate dateOfBirth;
 
 @Column(name = "payment_validation")
 private String paymentValidation;

 @NonNull
 @Column(name = "password")
 private String password;

 @NonNull
 @Column(name = "sold_amount")
 private Long soldAmount;
 
 @NonNull
 @Column(name = "sold_team")
 private String soldTeam;
 
 @NonNull
 @Column(name = "sold_time")
 private LocalDateTime soldTime;
 
 @NonNull
 @Column(name = "base_price")
 private Long basePrice;
 }
