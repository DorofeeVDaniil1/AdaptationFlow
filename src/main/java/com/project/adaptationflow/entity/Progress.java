package com.project.adaptationflow.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "progress")
public class Progress extends StandardEntityUUID {
    private static final long serialVersionUID = -7230323278318860083L;
    private TaskDetail detail;

    private LocalDateTime completedAt;

    private String comment;

    private UUID assignmentUser;

    private UUID assignmentTask;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "detail_id")
    public TaskDetail getDetail() {
        return detail;
    }

    @Column(name = "completed_at")
    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    @Column(name = "comment", length = Integer.MAX_VALUE)
    public String getComment() {
        return comment;
    }

    @NotNull
    @Column(name = "assignment_user", nullable = false)
    public UUID getAssignmentUser() {
        return assignmentUser;
    }

    @NotNull
    @Column(name = "assignment_task", nullable = false)
    public UUID getAssignmentTask() {
        return assignmentTask;
    }

}