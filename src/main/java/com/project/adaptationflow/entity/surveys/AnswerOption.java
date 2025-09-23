package com.project.adaptationflow.entity.surveys;

import com.project.adaptationflow.entity.StandardEntityUUID;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "answer_option", indexes = {
        @Index(name = "idx_answer_option_question_id", columnList = "question_id")
})
public class AnswerOption extends StandardEntityUUID {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @NotNull
    @Column(name = "answer_text", nullable = false, length = Integer.MAX_VALUE)
    private String answerText;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "is_correct")
    private Boolean isCorrect;

    @OneToMany
    @JoinColumn(name = "answer_option_id")
    private Set<UserAnswer> userAnswers = new LinkedHashSet<>();

}