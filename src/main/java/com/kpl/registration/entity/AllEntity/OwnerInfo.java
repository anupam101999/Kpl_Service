package com.kpl.registration.entity.AllEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Audited
@Entity
@Table(name = "owner_information")
public class OwnerInfo extends AuditTable implements Serializable {

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
