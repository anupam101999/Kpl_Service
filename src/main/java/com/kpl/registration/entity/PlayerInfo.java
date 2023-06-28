 package com.kpl.registration.entity;
 import java.io.Serializable;
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

 @Column(name = "player_name")
 private String playerName;

 @Column(name = "player_address",length = 10000)
 private String playerAddress;

 @Column(name = "pin_code")
 private Long pinCode;

 @Column(name = "aadhar_no")
 private Long aadharNo;

 @Column(name = "ph_no")
 private Long phNo;

 @Column(name = "email_id")
 private String emailId;

 @Column(name = "generue")
 private String generue;

 @Lob
 @Type(type = "org.hibernate.type.BinaryType")
 private byte[] image;

 @Column(name = "registration_time")
 private LocalDateTime registrationTime;

 @Column(name = "player_location_category")
 private String playerLocation;

 @Lob
 @Type(type = "org.hibernate.type.BinaryType")
 private byte[] docImage;

 }
