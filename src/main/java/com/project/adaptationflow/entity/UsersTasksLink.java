package com.project.adaptationflow.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "users_tasks_link", indexes = {
        @Index(name = "users_tasks_link_idx_users_tasks_link_user_id", columnList = "user_id"),
        @Index(name = "users_tasks_link_idx_users_tasks_link_task_id", columnList = "task_id")
})
public class UsersTasksLink implements Serializable {
    private static final long serialVersionUID = 836099087705124829L;
    private UsersTasksLinkId id;

    private SysUser user;

    private Task task;

    private LocalDateTime assignedAt;

    private String status;

    private LocalDateTime dueDate;

    @EmbeddedId
    public UsersTasksLinkId getId() {
        return id;
    }

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    public SysUser getUser() {
        return user;
    }

    @MapsId("taskId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "task_id", nullable = false)
    public Task getTask() {
        return task;
    }

    @NotNull
    @Column(name = "assigned_at", nullable = false)
    public LocalDateTime getAssignedAt() {
        return assignedAt;
    }

    @Size(max = 20)
    @NotNull
    @Column(name = "status", nullable = false, length = 20)
    public String getStatus() {
        return status;
    }

    @Column(name = "due_date")
    public LocalDateTime getDueDate() {
        return dueDate;
    }

}