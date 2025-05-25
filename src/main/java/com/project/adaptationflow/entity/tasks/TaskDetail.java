package com.project.adaptationflow.entity.tasks;

import com.project.adaptationflow.entity.StandardEntityUUID;
import com.project.adaptationflow.entity.gamefication.Progress;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serial;
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
@Table(name = "task_detail", schema = "public")
public class TaskDetail extends StandardEntityUUID {
    @Serial
    private static final long serialVersionUID = -6126321772207161422L;
    private UUID id;

    private Task task;

    private String name;

    private String description;

    private String status;

    private Set<Progress> progresses = new LinkedHashSet<>();

    private Set<TaskDetailAdditionalInfo> taskDetailAdditionalInfos = new LinkedHashSet<>();


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "task_id", nullable = false)
    protected Task getTask() {
        return task;
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

    @OneToMany(mappedBy = "detail")
    protected Set<Progress> getProgresses() {
        return progresses;
    }

    @OneToMany(mappedBy = "taskDetail")
    protected Set<TaskDetailAdditionalInfo> getTaskDetailAdditionalInfos() {
        return taskDetailAdditionalInfos;
    }

}