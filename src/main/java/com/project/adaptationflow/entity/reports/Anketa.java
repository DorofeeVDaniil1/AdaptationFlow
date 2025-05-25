package com.project.adaptationflow.entity.reports;

import com.project.adaptationflow.entity.*;
import com.project.adaptationflow.entity.gamefication.PointsTransaction;
import com.project.adaptationflow.entity.tasks.Task;
import com.project.adaptationflow.entity.tasks.TrackStage;
import com.project.adaptationflow.entity.person.SysUser;
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
@Table(name = "anketa", schema = "public")
public class Anketa extends StandardEntityUUID {
    @Serial
    private static final long serialVersionUID = 2048737643300368616L;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private SysUser user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "track_stage_id", nullable = false)
    private TrackStage trackStage;

    @Column(name = "difficulty_rating", nullable = false)
    private Short difficultyRating;

    @Column(name = "satisfaction_rating", nullable = false)
    private Short satisfactionRating;


    @ManyToMany
    @JoinTable(name = "anketa_question_link",
            joinColumns = @JoinColumn(name = "anketa_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    private Set<QuestionOption> questionOptions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "anketa")
    private Set<PointsTransaction> pointsTransactions = new LinkedHashSet<>();

}