package com.project.adaptationflow.entity.tasks;

import com.project.adaptationflow.entity.gamefication.Progress;
import com.project.adaptationflow.entity.person.SysUser;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serial;
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
    @Serial
    private static final long serialVersionUID = -2668222496441016601L;

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

    @Column(name = "assigned_at", nullable = false)
    private OffsetDateTime assignedAt;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Column(name = "due_date")
    private OffsetDateTime dueDate;


    @OneToMany(mappedBy = "usersTasksLink")
    private Set<Progress> progresses = new LinkedHashSet<>();

}