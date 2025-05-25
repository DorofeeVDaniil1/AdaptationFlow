package com.project.adaptationflow.entity.integration;

import com.project.adaptationflow.entity.StandardEntityUUID;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serial;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "external_system", schema = "public")
public class ExternalSystem extends StandardEntityUUID {
    @Serial
    private static final long serialVersionUID = -5511870607339895199L;
    private UUID id;

    private String name;

    private Map<String, Object> config;

    private Set<IntegrationMapping> integrationMappings = new LinkedHashSet<>();

    @Column(name = "name", nullable = false, length = 100)
    protected String getName() {
        return name;
    }

    @Column(name = "config", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    protected Map<String, Object> getConfig() {
        return config;
    }

    @OneToMany(mappedBy = "system")
    protected Set<IntegrationMapping> getIntegrationMappings() {
        return integrationMappings;
    }

}