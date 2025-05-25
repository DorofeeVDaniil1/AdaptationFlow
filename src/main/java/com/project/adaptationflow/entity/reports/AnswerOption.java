package com.project.adaptationflow.entity.reports;

import com.project.adaptationflow.entity.StandardEntityUUID;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serial;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "answer_option", schema = "public")
public class AnswerOption extends StandardEntityUUID {
    @Serial
    private static final long serialVersionUID = 2216012029228070177L;
    private UUID id;

    private QuestionOption question;

    private String answerText;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "question_id", nullable = false)
    protected QuestionOption getQuestion() {
        return question;
    }

    @Column(name = "answer_text", nullable = false, length = Integer.MAX_VALUE)
    protected String getAnswerText() {
        return answerText;
    }

}