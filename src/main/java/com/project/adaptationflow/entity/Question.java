package com.project.adaptationflow.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "questions")
public class Question extends StandardEntityUUID {
    @NotNull
    @Column(name = "question_text", nullable = false, length = Integer.MAX_VALUE)
    private String questionText;

    @NotNull
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = false;

    @ManyToMany(mappedBy = "questions")
    private Set<Anketa> anketas = new LinkedHashSet<>();

    @OneToMany
    @JoinColumn(name = "question_id")
    private Set<AnswerOption> answerOptions = new LinkedHashSet<>();

    @OneToMany
    @JoinColumn(name = "question_id")
    private Set<UserAnswer> userAnswers = new LinkedHashSet<>();

}