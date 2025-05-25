package com.project.adaptationflow.entity.reports;

import com.project.adaptationflow.entity.StandardEntityUUID;
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
@Table(name = "question_option", schema = "public")
public class QuestionOption extends StandardEntityUUID {
    @Serial
    private static final long serialVersionUID = -3224581886804009883L;
    private UUID id;

    private String questionText;

    private Boolean isActive = false;

    private Set<Anketa> anketas = new LinkedHashSet<>();

    private Set<AnswerOption> answerOptions = new LinkedHashSet<>();

    @Column(name = "question_text", nullable = false, length = Integer.MAX_VALUE)
    protected String getQuestionText() {
        return questionText;
    }

    @Column(name = "is_active", nullable = false)
    protected Boolean getIsActive() {
        return isActive;
    }

    @ManyToMany(mappedBy = "questionOptions")
    protected Set<Anketa> getAnketas() {
        return anketas;
    }

    @OneToMany(mappedBy = "question")
    protected Set<AnswerOption> getAnswerOptions() {
        return answerOptions;
    }

}