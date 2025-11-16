package com.kpl.registration.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "owner_information")
public class OwnerInfo implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = 3980366880241829960L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "owner_id")
	@Column(name = "owner_id")
	private Long imageId;

	@Column(name = "owner_name")
	private String ownerName;

	@Column(name = "owner_phNo")
	private Long ownerPhNo;
	
	@Column(name = "team_name")
	private String teamName;
}