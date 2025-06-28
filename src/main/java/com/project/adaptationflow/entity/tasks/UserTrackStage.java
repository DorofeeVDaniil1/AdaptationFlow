package com.project.adaptationflow.entity.tasks;

import com.project.adaptationflow.entity.StandardEntityUUID;
import jakarta.persistence.*;
import liquibase.integration.ant.type.DatabaseType;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serial;
import java.time.OffsetDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user_track_stage", schema = "public", indexes = {
        @Index(name = "uq_uts", columnList = "uot_id, stage_id", unique = true),
        @Index(name = "idx_user_track_stage_uot_id", columnList = "uot_id")
})
public class UserTrackStage extends StandardEntityUUID {
    @Serial
    private static final long serialVersionUID = 5396129083534315085L;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "uot_id", nullable = false)
    private UserOnboardingTrack uot;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "stage_id", nullable = false)
    private TrackStage stage;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Column(name = "unlocked_at")
    private OffsetDateTime unlockedAt;

    @Column(name = "completed_at")
    private OffsetDateTime completedAt;

}