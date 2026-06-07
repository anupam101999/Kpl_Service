package com.kpl.registration.Audit;

import com.kpl.registration.entity.AllEntity.AuditTable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.Table;
import javax.persistence.metamodel.EntityType;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * Discovers database tables whose entities inherit audit columns.
 */
@Component
public class AuditTableRegistry {

    private static volatile Set<String> auditedTables = Collections.emptySet();

    private final EntityManager entityManager;

    public AuditTableRegistry(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @PostConstruct
    public void registerAuditedTables() {
        Set<String> tableNames = new HashSet<>();
        for (EntityType<?> entity : entityManager.getMetamodel().getEntities()) {
            Class<?> javaType = entity.getJavaType();
            if (AuditTable.class.isAssignableFrom(javaType)) {
                tableNames.add(resolveTableName(javaType, entity).toLowerCase(Locale.ROOT));
            }
        }
        auditedTables = Collections.unmodifiableSet(tableNames);
    }

    public static boolean isAuditedTable(String tableName) {
        return auditedTables.contains(tableName.toLowerCase(Locale.ROOT));
    }

    private String resolveTableName(Class<?> entityClass, EntityType<?> entityType) {
        Table table = entityClass.getAnnotation(Table.class);
        if (table != null && !table.name().trim().isEmpty()) {
            return table.name();
        }
        return entityType.getName();
    }
}
