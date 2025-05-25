package com.project.adaptationflow.entity.gamefication;

import com.project.adaptationflow.entity.StandardEntityUUID;
import com.project.adaptationflow.entity.person.UsersTasksLink;
import com.project.adaptationflow.entity.tasks.TaskDetail;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serial;
import java.time.OffsetDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "progress", schema = "public")
public class Progress extends StandardEntityUUID {
    @Serial
    private static final long serialVersionUID = -2685042614015425863L;
    private UUID id;

    private UsersTasksLink usersTasksLink;

    private TaskDetail detail;

    private OffsetDateTime completedAt;

    private String comment;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "assignment_user", referencedColumnName = "user_id", nullable = false),
            @JoinColumn(name = "assignment_task", referencedColumnName = "task_id", nullable = false)
    })
    @OnDelete(action = OnDeleteAction.CASCADE)
    protected UsersTasksLink getUsersTasksLink() {
        return usersTasksLink;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "detail_id")
    protected TaskDetail getDetail() {
        return detail;
    }

    @Column(name = "completed_at")
    protected OffsetDateTime getCompletedAt() {
        return completedAt;
    }

    @Column(name = "comment", length = Integer.MAX_VALUE)
    protected String getComment() {
        return comment;
    }

}