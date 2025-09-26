package com.project.adaptationflow.entity.tasks;

import com.project.adaptationflow.entity.StandardEntityUUID;
import com.project.adaptationflow.entity.links.UserTrackStage;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "track_stage", indexes = {
        @Index(name = "idx_track_stage_onboarding_track_id", columnList = "onboarding_track_id")
})
public class TrackStage extends StandardEntityUUID {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "onboarding_track_id", nullable = false)
    private OnboardingTrack onboardingTrack;

    @Size(max = 255)
    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @NotNull
    @Column(name = "order_index", nullable = false)
    private Integer orderIndex;

    @OneToMany
    @JoinColumn(name = "track_stage_id")
    private Set<Task> tasks = new LinkedHashSet<>();

    @OneToMany(mappedBy = "stage")
    private Set<UserTrackStage> userLinks = new LinkedHashSet<>();

}