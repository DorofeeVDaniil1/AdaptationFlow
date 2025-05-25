package com.project.adaptationflow.entity.reports;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Embeddable
public class AnketaQuestionLinkId implements Serializable {
    private static final long serialVersionUID = 5703479251568249139L;
    private UUID anketaId;

    private UUID questionId;

    @Column(name = "anketa_id", nullable = false)
    protected UUID getAnketaId() {
        return anketaId;
    }

    @Column(name = "question_id", nullable = false)
    protected UUID getQuestionId() {
        return questionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AnketaQuestionLinkId entity = (AnketaQuestionLinkId) o;
        return Objects.equals(this.questionId, entity.questionId) &&
                Objects.equals(this.anketaId, entity.anketaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, anketaId);
    }

}