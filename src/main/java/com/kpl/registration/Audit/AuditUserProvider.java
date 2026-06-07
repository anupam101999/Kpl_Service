package com.kpl.registration.Audit;

import com.kpl.registration.dto.AllOther.UserInfo;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Resolves the username used by both JPA entity auditing and custom update
 * queries.
 */
@Component("auditUserProvider")
public class AuditUserProvider {

    private static final String SYSTEM_USER = "system";

    public String currentAuditor() {
        return getCurrentAuditor();
    }

    public static String getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null
                || !authentication.isAuthenticated()
                || authentication instanceof AnonymousAuthenticationToken) {
            return SYSTEM_USER;
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserInfo) {
            return ((UserInfo) principal).getUsername();
        }
        if (principal instanceof org.springframework.security.core.userdetails.UserDetails) {
            return ((org.springframework.security.core.userdetails.UserDetails) principal).getUsername();
        }
        if (principal instanceof String) {
            return (String) principal;
        }
        return authentication.getName();
    }
}
