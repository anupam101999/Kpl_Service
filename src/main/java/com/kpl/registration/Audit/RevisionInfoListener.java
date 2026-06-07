package com.kpl.registration.Audit;

import org.hibernate.envers.RevisionListener;

/**
 * Populates user metadata for Envers revision rows.
 */
public class RevisionInfoListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {
        RevisionInfo revisionInfo = (RevisionInfo) revisionEntity;
        revisionInfo.setModifiedBy(AuditUserProvider.getCurrentAuditor());
    }
}
