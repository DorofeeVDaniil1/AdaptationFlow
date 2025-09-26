package com.project.adaptationflow.entity.tasks;

import com.project.adaptationflow.entity.StandardEntityUUID;
import com.project.adaptationflow.entity.links.UsersTasksLink;
import com.project.adaptationflow.entity.surveys.Anketa;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tasks", indexes = {
        @Index(name = "idx_tasks_track_stage_id", columnList = "track_stage_id")
})
public class Task extends StandardEntityUUID {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "track_stage_id", nullable = false)
    private TrackStage trackStage;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Size(max = 50)
    @NotNull
    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @Column(name = "expired_time_task")
    private Instant expiredTimeTask;

    @OneToMany
    @JoinColumn(name = "task_id")
    private Set<Anketa> anketas = new LinkedHashSet<>();

    @OneToMany
    @JoinColumn(name = "assignment_task")
    private Set<Progress> progresses = new LinkedHashSet<>();

    @OneToMany
    @JoinColumn(name = "task_id")
    private Set<TaskDetail> taskDetails = new LinkedHashSet<>();

    @OneToMany(mappedBy = "task")
    private Set<UsersTasksLink> userLinks = new LinkedHashSet<>();

}