package com.kpl.registration.entity;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

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

 @NonNull
 @Lob
 @Type(type = "org.hibernate.type.BinaryType")
 private byte[] image;

 @Column(name = "registration_time")
 private LocalDateTime registrationTime;

 @NonNull
 @Column(name = "player_location_category")
 private String location;

 @NonNull
 @Lob
 @Type(type = "org.hibernate.type.BinaryType")
 private byte[] docImageFront;
 
 @NonNull
 @Lob
 @Type(type = "org.hibernate.type.BinaryType")
 private byte[] docImageBack;
 
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
 }
