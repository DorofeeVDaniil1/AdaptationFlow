package com.project.adaptationflow.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user_track_stage_link", indexes = {
        @Index(name = "user_track_stage_uq_uts", columnList = "uot_id, stage_id", unique = true),
        @Index(name = "user_track_stage_idx_user_track_stage_uot_id", columnList = "uot_id")
})
public class UserTrackStageLink implements Serializable {
    private static final long serialVersionUID = 5254012679313517012L;
    private UUID id;

    private UserOnboardingTrackLink uot;

    private TrackStage stage;

    private String status;

    private LocalDateTime unlockedAt;

    private LocalDateTime completedAt;

    @Id
    @Column(name = "id", nullable = false)
    public UUID getId() {
        return id;
    }

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "uot_id", nullable = false)
    public UserOnboardingTrackLink getUot() {
        return uot;
    }

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "stage_id", nullable = false)
    public TrackStage getStage() {
        return stage;
    }

    @Size(max = 20)
    @NotNull
    @Column(name = "status", nullable = false, length = 20)
    public String getStatus() {
        return status;
    }

    @Column(name = "unlocked_at")
    public LocalDateTime getUnlockedAt() {
        return unlockedAt;
    }

    @Column(name = "completed_at")
    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

}