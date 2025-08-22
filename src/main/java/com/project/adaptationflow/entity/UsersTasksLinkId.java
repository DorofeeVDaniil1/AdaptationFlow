package com.project.adaptationflow.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
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
public class UsersTasksLinkId implements Serializable {
    private static final long serialVersionUID = -4134527725970835515L;
    private UUID userId;

    private UUID taskId;

    @NotNull
    @Column(name = "user_id", nullable = false)
    public UUID getUserId() {
        return userId;
    }

    @NotNull
    @Column(name = "task_id", nullable = false)
    public UUID getTaskId() {
        return taskId;
    }

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