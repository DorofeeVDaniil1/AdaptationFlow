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
@Table(name = "question_option")
public class QuestionOption extends StandardEntityUUID {
    private static final long serialVersionUID = -4045599059582075145L;
    private String questionText;

    private Boolean isActive = false;

    private Set<Anketa> anketas = new LinkedHashSet<>();

    private Set<AnswerOption> answerOptions = new LinkedHashSet<>();

    @NotNull
    @Column(name = "question_text", nullable = false, length = Integer.MAX_VALUE)
    public String getQuestionText() {
        return questionText;
    }

    @NotNull
    @Column(name = "is_active", nullable = false)
    public Boolean getIsActive() {
        return isActive;
    }

    @Size(max = 50)
    @Column(name = "deleted_by", length = 50)
    public String getDeletedBy() {
        return deletedBy;
    }

    @ManyToMany(mappedBy = "questionOptions")
    public Set<Anketa> getAnketas() {
        return anketas;
    }

    @OneToMany(mappedBy = "question")
    public Set<AnswerOption> getAnswerOptions() {
        return answerOptions;
    }

}