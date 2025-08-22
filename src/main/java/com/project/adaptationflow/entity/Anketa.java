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
@Table(name = "anketa")
public class Anketa extends StandardEntityUUID {
    private static final long serialVersionUID = -2584156072765997349L;

    private SysUser user;

    private Task task;

    private Short difficultyRating;

    private Short satisfactionRating;

    private Set<QuestionOption> questionOptions = new LinkedHashSet<>();

    private Set<PointsTransaction> pointsTransactions = new LinkedHashSet<>();

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    public SysUser getUser() {
        return user;
    }

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "task_id", nullable = false)
    public Task getTask() {
        return task;
    }

    @NotNull
    @Column(name = "difficulty_rating", nullable = false)
    public Short getDifficultyRating() {
        return difficultyRating;
    }

    @NotNull
    @Column(name = "satisfaction_rating", nullable = false)
    public Short getSatisfactionRating() {
        return satisfactionRating;
    }

    @ManyToMany
    @JoinTable(name = "anketa_question_link",
            joinColumns = @JoinColumn(name = "anketa_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    public Set<QuestionOption> getQuestionOptions() {
        return questionOptions;
    }

    @OneToMany(mappedBy = "anketa")
    public Set<PointsTransaction> getPointsTransactions() {
        return pointsTransactions;
    }

}