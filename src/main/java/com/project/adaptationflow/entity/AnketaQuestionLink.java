package com.project.adaptationflow.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "anketa_question_link", indexes = {
        @Index(name = "idx_anketa_question_link_anketa_id", columnList = "anketa_id"),
        @Index(name = "idx_anketa_question_link_question_id", columnList = "question_id")
})
public class AnketaQuestionLink {
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
    private Question question;

}