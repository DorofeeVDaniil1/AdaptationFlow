package com.project.adaptationflow.entity.reports;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serial;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "anketa_question_link", schema = "public")
public class AnketaQuestionLink implements Serializable {
    @Serial
    private static final long serialVersionUID = 3527952192536168889L;

    @EmbeddedId
    private AnketaQuestionLinkId id;

    @MapsId("anketaId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "anketa_id", nullable = false)
    private Anketa anketa;

    @MapsId("questionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "question_id", nullable = false)
    private QuestionOption question;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "EmbeddedId = " + id + ")";
    }
}