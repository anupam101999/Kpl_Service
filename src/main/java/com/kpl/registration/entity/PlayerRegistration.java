package com.kpl.registration.entity;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "player_registration_six")
public class PlayerRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "reg_id")
    private Long regId;

    private String category;
    private Long aadhaar;
    private Long basePrice;
    private String dateOfBirth;
    private String emailId;

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] image;

    private String playerLocationCategory;
    private String paid;
    private Long phNo;
    private Long pinCode;
    private String playerAddress;
    private String playerFirstName;
    private String playerLastName;
    private String registrationTime;
    private Long soldAmount;
    private String soldTeam;
    private LocalDateTime soldTime;

}