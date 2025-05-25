package com.project.adaptationflow.entity.reports;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "anketa_question_link", schema = "public")
public class AnketaQuestionLink implements Serializable {
    private static final long serialVersionUID = 3527952192536168889L;
    private AnketaQuestionLinkId id;

    private Anketa anketa;

    private QuestionOption question;

    @EmbeddedId
    protected AnketaQuestionLinkId getId() {
        return id;
    }

    @MapsId("anketaId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "anketa_id", nullable = false)
    protected Anketa getAnketa() {
        return anketa;
    }

    @MapsId("questionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "question_id", nullable = false)
    protected QuestionOption getQuestion() {
        return question;
    }

}