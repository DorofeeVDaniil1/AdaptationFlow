package com.project.adaptationflow.entity.person;

import com.project.adaptationflow.entity.StandardEntityUUID;
import com.project.adaptationflow.entity.tasks.TrackStage;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serial;
import java.time.OffsetDateTime;
import java.util.UUID;

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
    private UUID id;

    private UserOnboardingTrack uot;

    private TrackStage stage;

    private String status;

    private OffsetDateTime unlockedAt;

    private OffsetDateTime completedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "uot_id", nullable = false)
    protected UserOnboardingTrack getUot() {
        return uot;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "stage_id", nullable = false)
    protected TrackStage getStage() {
        return stage;
    }

    @Column(name = "status", nullable = false, length = 20)
    protected String getStatus() {
        return status;
    }

    @Column(name = "unlocked_at")
    protected OffsetDateTime getUnlockedAt() {
        return unlockedAt;
    }

    @Column(name = "completed_at")
    protected OffsetDateTime getCompletedAt() {
        return completedAt;
    }

}