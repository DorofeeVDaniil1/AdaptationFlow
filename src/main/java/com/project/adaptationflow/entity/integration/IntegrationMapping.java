package com.project.adaptationflow.entity.integration;

import com.project.adaptationflow.entity.StandardEntityUUID;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.type.SqlTypes;

import java.io.Serial;
import java.util.Map;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "integration_mapping", schema = "public")
public class IntegrationMapping extends StandardEntityUUID {
    @Serial
    private static final long serialVersionUID = 5340600778658817333L;
    private UUID id;

    private ExternalSystem system;

    private String localEntity;

    private String remoteEntity;

    private Map<String, Object> mapping;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "system_id", nullable = false)
    protected ExternalSystem getSystem() {
        return system;
    }

    @Column(name = "local_entity", nullable = false, length = 100)
    protected String getLocalEntity() {
        return localEntity;
    }

    @Column(name = "remote_entity", nullable = false, length = 100)
    protected String getRemoteEntity() {
        return remoteEntity;
    }

    @Column(name = "mapping")
    @JdbcTypeCode(SqlTypes.JSON)
    protected Map<String, Object> getMapping() {
        return mapping;
    }

}