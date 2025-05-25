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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "onboarding_track_id", nullable = false)
    private OnboardingTrack onboardingTrack;


    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "order_index", nullable = false)
    private Integer orderIndex;

    @OneToMany(mappedBy = "trackStage")
    private Set<Anketa> anketas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "trackStage")
    private Set<Task> tasks = new LinkedHashSet<>();

    @OneToMany(mappedBy = "stage")
    private Set<UserTrackStage> userTrackStages = new LinkedHashSet<>();

}