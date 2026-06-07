package com.kpl.registration.Audit;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Envers revision metadata shared by every *_aud table row.
 */
@Getter
@Setter
@Entity
@RevisionEntity(RevisionInfoListener.class)
@Table(name = "revinfo")
public class RevisionInfo extends DefaultRevisionEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "modified_by")
    private String modifiedBy;
}
