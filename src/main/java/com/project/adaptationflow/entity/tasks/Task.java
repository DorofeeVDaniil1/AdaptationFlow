package com.project.adaptationflow.entity.tasks;

import com.project.adaptationflow.entity.StandardEntityUUID;
import com.project.adaptationflow.entity.reports.Anketa;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serial;
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
@Table(name = "tasks", schema = "public", indexes = {
        @Index(name = "idx_task_stage_id", columnList = "track_stage_id")
})
public class Task extends StandardEntityUUID {
    @Serial
    private static final long serialVersionUID = -7545796661791342625L;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "track_stage_id", nullable = false)
    private TrackStage trackStage;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @Column(name = "expired_time_task")
    private OffsetDateTime expiredTimeTask;

    @OneToMany(mappedBy = "task")
    private Set<Anketa> anketas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "task")
    private Set<TaskDetail> taskDetails = new LinkedHashSet<>();

    @OneToMany(mappedBy = "task")
    private Set<UsersTasksLink> usersTasksLinks = new LinkedHashSet<>();

}