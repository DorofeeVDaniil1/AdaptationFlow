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

    @Column(name = "entity_type", nullable = false, length = 20)
    private String entityType;

    @Column(name = "entity_id", nullable = false)
    private UUID entityId;

    @Column(name = "points", nullable = false)
    private Integer points;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "active", nullable = false)
    private Boolean active = false;

    @OneToMany(mappedBy = "pointsRule")
    private Set<PointsTransaction> pointsTransactions = new LinkedHashSet<>();

}