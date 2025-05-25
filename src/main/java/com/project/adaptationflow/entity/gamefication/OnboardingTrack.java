package com.project.adaptationflow.entity.gamefication;

import com.project.adaptationflow.entity.StandardEntityUUID;
import com.project.adaptationflow.entity.person.UserOnboardingTrack;
import com.project.adaptationflow.entity.tasks.TrackStage;
import jakarta.persistence.*;
import lombok.*;

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
@Table(name = "onboarding_track", schema = "public")
public class OnboardingTrack extends StandardEntityUUID {
    private static final long serialVersionUID = 2438073981188357860L;
    private UUID id;

    private String title;

    private String description;

    private Set<TrackStage> trackStages = new LinkedHashSet<>();

    private Set<UserOnboardingTrack> userOnboardingTracks = new LinkedHashSet<>();

    @Column(name = "title", nullable = false)
    protected String getTitle() {
        return title;
    }

    @Column(name = "description", length = Integer.MAX_VALUE)
    protected String getDescription() {
        return description;
    }

    @OneToMany(mappedBy = "onboardingTrack")
    protected Set<TrackStage> getTrackStages() {
        return trackStages;
    }

    @OneToMany(mappedBy = "track")
    protected Set<UserOnboardingTrack> getUserOnboardingTracks() {
        return userOnboardingTracks;
    }

}