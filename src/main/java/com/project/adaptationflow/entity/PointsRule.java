package com.project.adaptationflow.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "points_rule", indexes = {
        @Index(name = "points_rule_uq_points_rule", columnList = "entity_type, entity_id", unique = true)
})
public class PointsRule extends StandardEntityUUID {
    private static final long serialVersionUID = 4205264148810574808L;
    private String entityType;

    private UUID entityId;

    private Integer points;

    private String description;

    private Boolean active = false;

    private Set<PointsTransaction> pointsTransactions = new LinkedHashSet<>();

    @Size(max = 20)
    @NotNull
    @Column(name = "entity_type", nullable = false, length = 20)
    public String getEntityType() {
        return entityType;
    }

    @NotNull
    @Column(name = "entity_id", nullable = false)
    public UUID getEntityId() {
        return entityId;
    }

    @NotNull
    @Column(name = "points", nullable = false)
    public Integer getPoints() {
        return points;
    }

    @Column(name = "description", length = Integer.MAX_VALUE)
    public String getDescription() {
        return description;
    }

    @NotNull
    @Column(name = "active", nullable = false)
    public Boolean getActive() {
        return active;
    }

    @Size(max = 50)
    @Column(name = "deleted_by", length = 50)
    public String getDeletedBy() {
        return deletedBy;
    }

    @OneToMany(mappedBy = "pointsRule")
    public Set<PointsTransaction> getPointsTransactions() {
        return pointsTransactions;
    }

}