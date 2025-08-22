package com.project.adaptationflow.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "onboarding_track")
public class OnboardingTrack extends StandardEntityUUID {
    private static final long serialVersionUID = -3646721736408139325L;
    private String title;

    private String description;

    private Set<TrackStage> trackStages = new LinkedHashSet<>();

    private Set<UserOnboardingTrackLink> userOnboardingTrackLinks = new LinkedHashSet<>();

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

    @OneToMany(mappedBy = "onboardingTrack")
    public Set<TrackStage> getTrackStages() {
        return trackStages;
    }

    @OneToMany(mappedBy = "track")
    public Set<UserOnboardingTrackLink> getUserOnboardingTrackLinks() {
        return userOnboardingTrackLinks;
    }

}