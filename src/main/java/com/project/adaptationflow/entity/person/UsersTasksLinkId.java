package com.project.adaptationflow.entity.person;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Embeddable
public class UsersTasksLinkId implements Serializable {
    @Serial
    private static final long serialVersionUID = 6548196332103063854L;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "task_id", nullable = false)
    private UUID taskId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UsersTasksLinkId entity = (UsersTasksLinkId) o;
        return Objects.equals(this.userId, entity.userId) &&
                Objects.equals(this.taskId, entity.taskId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, taskId);
    }

}