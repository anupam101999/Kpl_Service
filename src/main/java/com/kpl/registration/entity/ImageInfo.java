package com.kpl.registration.entity;

import java.io.Serializable;

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
@Table(name = "master_image_details")
public class ImageInfo implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = 3980366880241829960L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "image_id")
	@Column(name = "image_id")
	private Long imageId;

	@Column(name = "image_name")
	private String imageName;

	@Lob
	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] image;
}