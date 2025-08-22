package com.project.adaptationflow.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "answer_option")
public class AnswerOption extends StandardEntityUUID {
    private static final long serialVersionUID = 9046812293144395439L;
    private QuestionOption question;

    private String answerText;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_id", nullable = false)
    public QuestionOption getQuestion() {
        return question;
    }

    @NotNull
    @Column(name = "answer_text", nullable = false, length = Integer.MAX_VALUE)
    public String getAnswerText() {
        return answerText;
    }


}