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
public class UserTrackStageId implements Serializable {
    private static final long serialVersionUID = 7712854767431028656L;
    @NotNull
    @Column(name = "stage_id", nullable = false)
    private UUID stageId;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserTrackStageId entity = (UserTrackStageId) o;
        return Objects.equals(this.userId, entity.userId) &&
                Objects.equals(this.stageId, entity.stageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, stageId);
    }

}