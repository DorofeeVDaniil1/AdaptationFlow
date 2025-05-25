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

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "system")
    private Set<IntegrationMapping> integrationMappings = new LinkedHashSet<>();

}