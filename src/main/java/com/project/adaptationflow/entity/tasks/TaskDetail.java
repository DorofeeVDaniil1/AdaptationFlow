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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;


    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;


    @Column(name = "status", nullable = false, length = 50)
    private String status;


    @OneToMany(mappedBy = "detail")
    private Set<Progress> progresses = new LinkedHashSet<>();


    @OneToMany(mappedBy = "taskDetail")
    private Set<TaskDetailAdditionalInfo> taskDetailAdditionalInfos = new LinkedHashSet<>();


}