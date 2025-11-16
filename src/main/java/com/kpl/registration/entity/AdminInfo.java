package com.kpl.registration.entity;

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
@Table(name = "admin_mst_table")
public class AdminInfo extends AuditTable implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = 3980366880241829960L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "admin_id")
	@Column(name = "admin_id")
	private Long adminId;

	@Column(name = "id")
	private String id;

	@Column(name = "password")
	private String password;
	private String roleCode;
}