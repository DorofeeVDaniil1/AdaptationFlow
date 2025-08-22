package com.project.adaptationflow.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "points_transaction", indexes = {
        @Index(name = "points_transaction_idx_points_transaction_user_id", columnList = "user_id")
})
public class PointsTransaction extends StandardEntityUUID {
    private static final long serialVersionUID = -8403244020063981302L;
    private SysUser user;

    private PointsRule pointsRule;

    private Anketa anketa;

    private Integer points;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    public SysUser getUser() {
        return user;
    }

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "points_rule_id", nullable = false)
    public PointsRule getPointsRule() {
        return pointsRule;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anketa_id")
    public Anketa getAnketa() {
        return anketa;
    }

    @NotNull
    @Column(name = "points", nullable = false)
    public Integer getPoints() {
        return points;
    }

}