package com.kpl.registration.entity.AllEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Audited
@Entity
@Table(name = "master_image_details")
public class ImageInfo extends AuditTable implements Serializable {

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
