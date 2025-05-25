package com.project.adaptationflow.entity.gamefication;

import com.project.adaptationflow.entity.StandardEntityUUID;
import com.project.adaptationflow.entity.person.SysUser;
import com.project.adaptationflow.entity.reports.Anketa;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "points_transaction", schema = "public", indexes = {
        @Index(name = "idx_points_transaction_user_id", columnList = "user_id")
})
public class PointsTransaction extends StandardEntityUUID {
    @Serial
    private static final long serialVersionUID = -5017331812431124172L;
    private UUID id;

    private SysUser user;

    private PointsRule pointsRule;

    private Anketa anketa;

    private Integer points;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    protected SysUser getUser() {
        return user;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "points_rule_id", nullable = false)
    protected PointsRule getPointsRule() {
        return pointsRule;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anketa_id")
    protected Anketa getAnketa() {
        return anketa;
    }

    @Column(name = "points", nullable = false)
    protected Integer getPoints() {
        return points;
    }

}