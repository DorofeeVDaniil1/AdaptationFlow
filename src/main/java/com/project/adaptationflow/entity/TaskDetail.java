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
@Table(name = "task_detail")
public class TaskDetail extends StandardEntityUUID {
    private static final long serialVersionUID = 8724717886515785202L;
    private Task task;

    private String name;

    private String description;

    private String status;

    private Set<Progress> progresses = new LinkedHashSet<>();

    private Set<TaskDetailAdditionalInfo> taskDetailAdditionalInfos = new LinkedHashSet<>();

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "task_id", nullable = false)
    public Task getTask() {
        return task;
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

    @Size(max = 50)
    @Column(name = "deleted_by", length = 50)
    public String getDeletedBy() {
        return deletedBy;
    }

    @OneToMany(mappedBy = "detail")
    public Set<Progress> getProgresses() {
        return progresses;
    }

    @OneToMany(mappedBy = "taskDetail")
    public Set<TaskDetailAdditionalInfo> getTaskDetailAdditionalInfos() {
        return taskDetailAdditionalInfos;
    }

}