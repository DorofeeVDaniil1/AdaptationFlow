package com.project.adaptationflow.entity.gamification;

import com.project.adaptationflow.entity.StandardEntityUUID;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "points_rule", indexes = {
        @Index(name = "idx_points_rule_entity_id", columnList = "entity_id")
}, uniqueConstraints = {
        @UniqueConstraint(name = "uq_points_rule", columnNames = {"entity_type", "entity_id"})
})
public class PointsRule extends StandardEntityUUID {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Size(max = 20)
    @NotNull
    @Column(name = "entity_type", nullable = false, length = 20)
    private String entityType;

    @NotNull
    @Column(name = "entity_id", nullable = false)
    private UUID entityId;

    @NotNull
    @Column(name = "points", nullable = false)
    private Integer points;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @NotNull
    @Column(name = "active", nullable = false)
    private Boolean active = false;

    @OneToMany
    @JoinColumn(name = "points_rule_id")
    private Set<PointsTransaction> pointsTransactions = new LinkedHashSet<>();

}