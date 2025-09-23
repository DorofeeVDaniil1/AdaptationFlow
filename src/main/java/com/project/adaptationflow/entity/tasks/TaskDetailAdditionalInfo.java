package com.project.adaptationflow.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "task_detail_additional_info", indexes = {
        @Index(name = "idx_task_detail_additional_info_task_detail_id", columnList = "task_detail_id")
})
public class TaskDetailAdditionalInfo extends StandardEntityUUID {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "task_detail_id", nullable = false)
    private TaskDetail taskDetail;

    @NotNull
    @Column(name = "document_url", nullable = false, length = Integer.MAX_VALUE)
    private String documentUrl;

    @NotNull
    @Column(name = "category", nullable = false, length = Integer.MAX_VALUE)
    private String category;

}