package com.project.adaptationflow.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "tasks", indexes = {
        @Index(name = "tasks_idx_task_stage_id", columnList = "track_stage_id")
})
public class Task extends StandardEntityUUID {
    private static final long serialVersionUID = -6933553383652835338L;
    private TrackStage trackStage;

    private String name;

    private String description;

    private String status;

    private LocalDateTime expiredTimeTask;

    private Set<Anketa> anketas = new LinkedHashSet<>();

    private Set<TaskDetail> taskDetails = new LinkedHashSet<>();

    private Set<UsersTasksLink> usersTasksLinks = new LinkedHashSet<>();

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "track_stage_id", nullable = false)
    public TrackStage getTrackStage() {
        return trackStage;
    }

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    @Column(name = "description", length = Integer.MAX_VALUE)
    public String getDescription() {
        return description;
    }

    @Size(max = 50)
    @NotNull
    @Column(name = "status", nullable = false, length = 50)
    public String getStatus() {
        return status;
    }

    @Column(name = "expired_time_task")
    public LocalDateTime getExpiredTimeTask() {
        return expiredTimeTask;
    }

    @OneToMany(mappedBy = "task")
    public Set<Anketa> getAnketas() {
        return anketas;
    }

    @OneToMany(mappedBy = "task")
    public Set<TaskDetail> getTaskDetails() {
        return taskDetails;
    }

    @OneToMany(mappedBy = "task")
    public Set<UsersTasksLink> getUsersTasksLinks() {
        return usersTasksLinks;
    }

}