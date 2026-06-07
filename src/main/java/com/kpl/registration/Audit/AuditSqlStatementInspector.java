package com.kpl.registration.Audit;

import org.hibernate.resource.jdbc.spi.StatementInspector;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Adds last-modified audit columns to custom SQL update statements.
 *
 * <p>Spring Data entity auditing handles normal {@code save(...)} operations,
 * but repository-level {@code @Modifying @Query} statements bypass entity
 * listeners. This inspector closes that gap in one place by enriching updates
 * for known audited tables before Hibernate prepares the SQL.</p>
 */
public class AuditSqlStatementInspector implements StatementInspector {

    private static final Pattern UPDATE_PATTERN =
            Pattern.compile("(?is)^(\\s*update\\s+)(?:([a-zA-Z0-9_]+)\\.)?([a-zA-Z0-9_]+)(\\s+set\\s+)");

    @Override
    public String inspect(String sql) {
        if (sql == null || alreadyAudited(sql)) {
            return sql;
        }

        Matcher matcher = UPDATE_PATTERN.matcher(sql);
        if (!matcher.find()) {
            return sql;
        }

        String tableName = matcher.group(3).toLowerCase(Locale.ROOT);
        if (!AuditTableRegistry.isAuditedTable(tableName)) {
            return sql;
        }

        String auditClause = "last_modified_by='" + escapeSql(AuditUserProvider.getCurrentAuditor())
                + "', last_modified_date=CURRENT_TIMESTAMP, ";
        return sql.substring(0, matcher.end()) + auditClause + sql.substring(matcher.end());
    }

    private boolean alreadyAudited(String sql) {
        String normalizedSql = sql.toLowerCase(Locale.ROOT);
        return normalizedSql.contains("last_modified_by") || normalizedSql.contains("last_modified_date");
    }

    private String escapeSql(String value) {
        return value == null ? "" : value.replace("'", "''");
    }
}
