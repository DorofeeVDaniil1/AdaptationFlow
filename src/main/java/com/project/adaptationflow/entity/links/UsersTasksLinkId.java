<<<<<<<< HEAD:src/main/java/com/project/adaptationflow/entity/tasks/UsersTasksLinkId.java
package com.project.adaptationflow.entity.tasks;
========
package com.project.adaptationflow.entity.links;
>>>>>>>> RefractorEntity:src/main/java/com/project/adaptationflow/entity/links/UsersTasksLinkId.java

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
public class UsersTasksLinkId implements Serializable {
    private static final long serialVersionUID = -8733541137490251069L;
    @NotNull
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @NotNull
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