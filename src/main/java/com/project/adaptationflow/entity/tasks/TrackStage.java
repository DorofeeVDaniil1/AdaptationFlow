package com.project.adaptationflow.entity.tasks;

import com.project.adaptationflow.entity.StandardEntityUUID;
import com.project.adaptationflow.entity.person.UserTrackStage;
import com.project.adaptationflow.entity.gamefication.OnboardingTrack;
import com.project.adaptationflow.entity.reports.Anketa;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
@Table(name = "track_stage", schema = "public", indexes = {
        @Index(name = "uq_ts_order", columnList = "onboarding_track_id, order_index", unique = true)
})
public class TrackStage extends StandardEntityUUID {
    @Serial
    private static final long serialVersionUID = -7630006727126164650L;
    private UUID id;

    private OnboardingTrack onboardingTrack;

    private String title;

    private String description;

    private Integer orderIndex;

    private Set<Anketa> anketas = new LinkedHashSet<>();

    private Set<Task> tasks = new LinkedHashSet<>();

    private Set<UserTrackStage> userTrackStages = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "onboarding_track_id", nullable = false)
    protected OnboardingTrack getOnboardingTrack() {
        return onboardingTrack;
    }

    @Column(name = "title", nullable = false)
    protected String getTitle() {
        return title;
    }

    @Column(name = "description", length = Integer.MAX_VALUE)
    protected String getDescription() {
        return description;
    }

    @Column(name = "order_index", nullable = false)
    protected Integer getOrderIndex() {
        return orderIndex;
    }

    @OneToMany(mappedBy = "trackStage")
    protected Set<Anketa> getAnketas() {
        return anketas;
    }

    @OneToMany(mappedBy = "trackStage")
    protected Set<Task> getTasks() {
        return tasks;
    }

    @OneToMany(mappedBy = "stage")
    protected Set<UserTrackStage> getUserTrackStages() {
        return userTrackStages;
    }

}