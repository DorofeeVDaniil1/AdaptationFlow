package com.project.adaptationflow.entity.gamefication;

import com.project.adaptationflow.entity.StandardEntityUUID;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
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
@Table(name = "points_rule", schema = "public", indexes = {
        @Index(name = "uq_points_rule", columnList = "entity_type, entity_id", unique = true)
})
public class PointsRule extends StandardEntityUUID {
    @Serial
    private static final long serialVersionUID = -6378408929916837274L;
    private UUID id;

    private String entityType;

    private UUID entityId;

    private Integer points;

    private String description;

    private Boolean active = false;

    private Set<PointsTransaction> pointsTransactions = new LinkedHashSet<>();

    @Column(name = "entity_type", nullable = false, length = 20)
    protected String getEntityType() {
        return entityType;
    }

    @Column(name = "entity_id", nullable = false)
    protected UUID getEntityId() {
        return entityId;
    }

    @Column(name = "points", nullable = false)
    protected Integer getPoints() {
        return points;
    }

    @Column(name = "description", length = Integer.MAX_VALUE)
    protected String getDescription() {
        return description;
    }

    @Column(name = "active", nullable = false)
    protected Boolean getActive() {
        return active;
    }

    @OneToMany(mappedBy = "pointsRule")
    protected Set<PointsTransaction> getPointsTransactions() {
        return pointsTransactions;
    }

}