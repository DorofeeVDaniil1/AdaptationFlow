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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "assignment_user", referencedColumnName = "user_id"),
            @JoinColumn(name = "assignment_task", referencedColumnName = "task_id")
    })
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UsersTasksLink usersTasksLink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "detail_id")
    private TaskDetail detail;

    @Column(name = "completed_at")
    private OffsetDateTime completedAt;

    @Column(name = "comment", length = Integer.MAX_VALUE)
    private String comment;

}