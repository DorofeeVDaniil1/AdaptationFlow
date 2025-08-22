package com.project.adaptationflow.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "track_stage", indexes = {
        @Index(name = "track_stage_uq_ts_order", columnList = "onboarding_track_id, order_index", unique = true)
})
public class TrackStage extends StandardEntityUUID {
    private static final long serialVersionUID = -7297852939056407293L;
    private OnboardingTrack onboardingTrack;

    private String title;

    private String description;

    private Integer orderIndex;

    private Set<Task> tasks = new LinkedHashSet<>();

    private Set<UserTrackStageLink> userTrackStageLinks = new LinkedHashSet<>();

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "onboarding_track_id", nullable = false)
    public OnboardingTrack getOnboardingTrack() {
        return onboardingTrack;
    }

    @Size(max = 255)
    @NotNull
    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    @Column(name = "description", length = Integer.MAX_VALUE)
    public String getDescription() {
        return description;
    }

    @NotNull
    @Column(name = "order_index", nullable = false)
    public Integer getOrderIndex() {
        return orderIndex;
    }

    @OneToMany(mappedBy = "trackStage")
    public Set<Task> getTasks() {
        return tasks;
    }

    @OneToMany(mappedBy = "stage")
    public Set<UserTrackStageLink> getUserTrackStageLinks() {
        return userTrackStageLinks;
    }

}