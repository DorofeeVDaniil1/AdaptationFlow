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
    private UUID id;

    private SysUser user;

    private Task task;

    private TrackStage trackStage;

    private Short difficultyRating;

    private Short satisfactionRating;

    private Set<QuestionOption> questionOptions = new LinkedHashSet<>();

    private Set<PointsTransaction> pointsTransactions = new LinkedHashSet<>();


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    protected SysUser getUser() {
        return user;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "task_id", nullable = false)
    protected Task getTask() {
        return task;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "track_stage_id", nullable = false)
    protected TrackStage getTrackStage() {
        return trackStage;
    }

    @Column(name = "difficulty_rating", nullable = false)
    protected Short getDifficultyRating() {
        return difficultyRating;
    }

    @Column(name = "satisfaction_rating", nullable = false)
    protected Short getSatisfactionRating() {
        return satisfactionRating;
    }

    @ManyToMany
    @JoinTable(name = "anketa_question_link",
            joinColumns = @JoinColumn(name = "anketa_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    protected Set<QuestionOption> getQuestionOptions() {
        return questionOptions;
    }

    @OneToMany(mappedBy = "anketa")
    protected Set<PointsTransaction> getPointsTransactions() {
        return pointsTransactions;
    }

}