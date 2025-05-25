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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private SysUser user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "points_rule_id", nullable = false)
    private PointsRule pointsRule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anketa_id")
    private Anketa anketa;

    @Column(name = "points", nullable = false)
    private Integer points;

}