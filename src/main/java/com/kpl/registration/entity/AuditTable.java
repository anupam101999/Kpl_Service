package com.kpl.registration.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditTable {

    @Column(nullable = false, updatable = false)
    @CreatedBy
    protected String createdBy;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    protected LocalDateTime createdDate;

    @Column(insertable = false)
    @LastModifiedBy
    protected String lastModifiedBy;

    @Column(insertable = false)
    @LastModifiedDate
    protected LocalDateTime lastModifiedDate;

}
