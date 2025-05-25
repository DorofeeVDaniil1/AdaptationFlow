package com.project.adaptationflow.entity.tasks;

import com.project.adaptationflow.entity.StandardEntityUUID;
import com.project.adaptationflow.entity.person.UsersTasksLink;
import com.project.adaptationflow.entity.reports.Anketa;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serial;
import java.time.OffsetDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "tasks", schema = "public", indexes = {
        @Index(name = "idx_task_stage_id", columnList = "track_stage_id")
})
public class Task extends StandardEntityUUID {
    @Serial
    private static final long serialVersionUID = -7545796661791342625L;
    private UUID id;

    private TrackStage trackStage;

    private String name;

    private String description;

    private String status;

    private OffsetDateTime expiredTimeTask;

    private Set<Anketa> anketas = new LinkedHashSet<>();

    private Set<TaskDetail> taskDetails = new LinkedHashSet<>();

    private Set<UsersTasksLink> usersTasksLinks = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "track_stage_id", nullable = false)
    protected TrackStage getTrackStage() {
        return trackStage;
    }

    @Column(name = "name", nullable = false)
    protected String getName() {
        return name;
    }

    @Column(name = "description", length = Integer.MAX_VALUE)
    protected String getDescription() {
        return description;
    }

    @Column(name = "status", nullable = false, length = 50)
    protected String getStatus() {
        return status;
    }

    @Column(name = "expired_time_task")
    protected OffsetDateTime getExpiredTimeTask() {
        return expiredTimeTask;
    }

    @OneToMany(mappedBy = "task")
    protected Set<Anketa> getAnketas() {
        return anketas;
    }

    @OneToMany(mappedBy = "task")
    protected Set<TaskDetail> getTaskDetails() {
        return taskDetails;
    }

    @OneToMany(mappedBy = "task")
    protected Set<UsersTasksLink> getUsersTasksLinks() {
        return usersTasksLinks;
    }

}