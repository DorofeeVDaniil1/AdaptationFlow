package com.project.adaptationflow.entity;

import com.project.adaptationflow.entity.user.SysUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "progress", indexes = {
        @Index(name = "idx_progress_detail_id", columnList = "detail_id"),
        @Index(name = "idx_progress_assignment_user", columnList = "assignment_user"),
        @Index(name = "idx_progress_assignment_task", columnList = "assignment_task")
})
public class Progress extends StandardEntityUUID {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "detail_id")
    private TaskDetail detail;

    @Column(name = "completed_at")
    private Instant completedAt;

    @Column(name = "comment", length = Integer.MAX_VALUE)
    private String comment;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "assignment_user", nullable = false)
    private SysUser assignmentUser;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "assignment_task", nullable = false)
    private Task assignmentTask;

}