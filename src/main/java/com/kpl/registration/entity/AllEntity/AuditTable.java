package com.kpl.registration.entity.AllEntity;

import com.kpl.registration.Audit.AuditUserProvider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

/**
 * Shared audit columns for entities persisted through Spring Data JPA.
 *
 * <p>Native and JPQL update queries bypass JPA entity listeners. Those updates
 * are handled centrally by {@code AuditSqlStatementInspector}, so repository
 * methods do not need to add audit columns by hand.</p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Audited
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditTable {

    @Column(name = "created_by", updatable = false)
    @CreatedBy
    protected String createdBy;

    @Column(name = "created_date", updatable = false)
    @CreatedDate
    protected LocalDateTime createdDate;

    @Column(name = "last_modified_by")
    @LastModifiedBy
    protected String lastModifiedBy;

    @Column(name = "last_modified_date")
    @LastModifiedDate
    protected LocalDateTime lastModifiedDate;

    @PrePersist
    public void auditCreate() {
        LocalDateTime now = LocalDateTime.now();
        String auditor = AuditUserProvider.getCurrentAuditor();

        if (createdDate == null) {
            createdDate = now;
        }
        if (createdBy == null || createdBy.trim().isEmpty()) {
            createdBy = auditor;
        }
    }

    @PreUpdate
    public void auditUpdate() {
        LocalDateTime now = LocalDateTime.now();
        String auditor = AuditUserProvider.getCurrentAuditor();

        if (createdDate == null) {
            createdDate = now;
        }
        if (createdBy == null || createdBy.trim().isEmpty()) {
            createdBy = auditor;
        }
        lastModifiedDate = now;
        lastModifiedBy = auditor;
    }
}
