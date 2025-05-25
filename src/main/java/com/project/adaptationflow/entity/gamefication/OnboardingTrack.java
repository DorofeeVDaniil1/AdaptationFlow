package com.project.adaptationflow.entity.gamefication;

import com.project.adaptationflow.entity.StandardEntityUUID;
import com.project.adaptationflow.entity.person.UserOnboardingTrack;
import com.project.adaptationflow.entity.tasks.TrackStage;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
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
    @Serial
    private static final long serialVersionUID = 2438073981188357860L;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @OneToMany(mappedBy = "onboardingTrack")
    private Set<TrackStage> trackStages = new LinkedHashSet<>();

    @OneToMany(mappedBy = "track")
    private Set<UserOnboardingTrack> userOnboardingTracks = new LinkedHashSet<>();

}