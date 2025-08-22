package com.project.adaptationflow.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
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
@Table(name = "user_onboarding_track_link", indexes = {
        @Index(name = "user_onboarding_track_link_uq_uot", columnList = "user_id, track_id", unique = true)
})
public class UserOnboardingTrackLink implements Serializable {
    private static final long serialVersionUID = 2751540309865132913L;
    private UUID id;

    private SysUser user;

    private OnboardingTrack track;

    private LocalDateTime startedAt;

    private String status;

    private LocalDateTime createTs;

    private String createdBy;

    private LocalDateTime updateTs;

    private String updatedBy;

    private LocalDateTime deleteTs;

    private String deletedBy;

    private Set<UserTrackStageLink> userTrackStageLinks = new LinkedHashSet<>();

    @Id
    @Column(name = "id", nullable = false)
    public UUID getId() {
        return id;
    }

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    public SysUser getUser() {
        return user;
    }

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "track_id", nullable = false)
    public OnboardingTrack getTrack() {
        return track;
    }

    @NotNull
    @Column(name = "started_at", nullable = false)
    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    @Size(max = 20)
    @NotNull
    @Column(name = "status", nullable = false, length = 20)
    public String getStatus() {
        return status;
    }

    @Column(name = "create_ts")
    public LocalDateTime getCreateTs() {
        return createTs;
    }

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    public String getCreatedBy() {
        return createdBy;
    }

    @Column(name = "update_ts")
    public LocalDateTime getUpdateTs() {
        return updateTs;
    }

    @Size(max = 50)
    @Column(name = "updated_by", length = 50)
    public String getUpdatedBy() {
        return updatedBy;
    }

    @Column(name = "delete_ts")
    public LocalDateTime getDeleteTs() {
        return deleteTs;
    }

    @Size(max = 50)
    @Column(name = "deleted_by", length = 50)
    public String getDeletedBy() {
        return deletedBy;
    }

    @OneToMany(mappedBy = "uot")
    public Set<UserTrackStageLink> getUserTrackStageLinks() {
        return userTrackStageLinks;
    }

}