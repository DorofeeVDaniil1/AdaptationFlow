package com.project.adaptationflow.entity.links;

import com.project.adaptationflow.entity.tasks.Task;
import com.project.adaptationflow.entity.user.SysUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "users_tasks_link", indexes = {
        @Index(name = "idx_users_tasks_link_user_id", columnList = "user_id"),
        @Index(name = "idx_users_tasks_link_task_id", columnList = "task_id")
})
public class UsersTasksLink {
    @EmbeddedId
    private UsersTasksLinkId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private SysUser user;

    @MapsId("taskId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

}