package com.project.adaptationflow.entity.tasks;

import com.project.adaptationflow.entity.StandardEntityUUID;
import com.project.adaptationflow.entity.links.UserOnboardingTrackLink;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "onboarding_track")
public class OnboardingTrack extends StandardEntityUUID {
    @Size(max = 255)
    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @OneToMany
    @JoinColumn(name = "onboarding_track_id")
    private Set<TrackStage> trackStages = new LinkedHashSet<>();

    @OneToMany(mappedBy = "track")
    private Set<UserOnboardingTrackLink> userLinks = new LinkedHashSet<>();

}