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
@Table(name = "event_count")
public class EventCount implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = 3980366880241829960L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "event_id")
	@Column(name = "event_id")
	private Long eventId;

	@Column(name = "rules_pdf")
	private Long reulesPdf;

	@Column(name = "owner_pdf")
	private Long ownerPdf;
	
	@Column(name = "player_pdf")
	private Long playerPdf;
}
