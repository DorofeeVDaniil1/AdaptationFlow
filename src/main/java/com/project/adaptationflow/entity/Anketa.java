package com.project.adaptationflow.entity;

import com.project.adaptationflow.entity.user.SysUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "anketa", indexes = {
        @Index(name = "idx_anketa_user_id", columnList = "user_id"),
        @Index(name = "idx_anketa_task_id", columnList = "task_id")
})
public class Anketa extends StandardEntityUUID {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private SysUser user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @NotNull
    @Column(name = "difficulty_rating", nullable = false)
    private Short difficultyRating;

    @NotNull
    @Column(name = "satisfaction_rating", nullable = false)
    private Short satisfactionRating;

    @ManyToMany
    @JoinTable(name = "anketa_question_link",
            joinColumns = @JoinColumn(name = "anketa_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    private Set<Question> questions = new LinkedHashSet<>();

    @OneToMany
    @JoinColumn(name = "anketa_id")
    private Set<PointsTransaction> pointsTransactions = new LinkedHashSet<>();

}