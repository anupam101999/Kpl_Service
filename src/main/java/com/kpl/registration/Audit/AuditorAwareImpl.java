package com.kpl.registration.Audit;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * Supplies the current user to Spring Data JPA auditing annotations.
 */
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(AuditUserProvider.getCurrentAuditor());
    }
}
