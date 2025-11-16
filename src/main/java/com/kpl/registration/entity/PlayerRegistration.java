package com.kpl.registration.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

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