package com.project.adaptationflow.entity.person;

import com.project.adaptationflow.entity.gamefication.Progress;
import com.project.adaptationflow.entity.tasks.Task;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "users_tasks_link", schema = "public", indexes = {
        @Index(name = "idx_users_tasks_link_user_id", columnList = "user_id"),
        @Index(name = "idx_users_tasks_link_task_id", columnList = "task_id")
})
public class UsersTasksLink implements Serializable {
    private static final long serialVersionUID = -2668222496441016601L;
    private UsersTasksLinkId id;

    private SysUser user;

    private Task task;

    private OffsetDateTime assignedAt;

    private String status;

    private OffsetDateTime dueDate;

    private Set<Progress> progresses = new LinkedHashSet<>();

    @EmbeddedId
    protected UsersTasksLinkId getId() {
        return id;
    }

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    protected SysUser getUser() {
        return user;
    }

    @MapsId("taskId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "task_id", nullable = false)
    protected Task getTask() {
        return task;
    }

    @Column(name = "assigned_at", nullable = false)
    protected OffsetDateTime getAssignedAt() {
        return assignedAt;
    }

    @Column(name = "status", nullable = false, length = 20)
    protected String getStatus() {
        return status;
    }

    @Column(name = "due_date")
    protected OffsetDateTime getDueDate() {
        return dueDate;
    }

    @OneToMany(mappedBy = "usersTasksLink")
    protected Set<Progress> getProgresses() {
        return progresses;
    }

}