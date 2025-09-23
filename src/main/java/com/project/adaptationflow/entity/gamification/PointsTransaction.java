package com.project.adaptationflow.entity;

import com.project.adaptationflow.entity.user.SysUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "points_transaction", indexes = {
        @Index(name = "idx_points_transaction_user", columnList = "user_id"),
        @Index(name = "idx_points_transaction_points_rule_id", columnList = "points_rule_id"),
        @Index(name = "idx_points_transaction_anketa_id", columnList = "anketa_id")
})
public class PointsTransaction extends StandardEntityUUID {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private SysUser user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "points_rule_id", nullable = false)
    private PointsRule pointsRule;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "anketa_id")
    private Anketa anketa;

    @NotNull
    @Column(name = "points", nullable = false)
    private Integer points;

}