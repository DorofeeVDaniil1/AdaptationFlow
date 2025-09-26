package com.project.adaptationflow.entity.links;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Embeddable
public class AnketaQuestionLinkId implements Serializable {
    private static final long serialVersionUID = -8114457840303167723L;
    @NotNull
    @Column(name = "anketa_id", nullable = false)
    private UUID anketaId;

    @NotNull
    @Column(name = "question_id", nullable = false)
    private UUID questionId;

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