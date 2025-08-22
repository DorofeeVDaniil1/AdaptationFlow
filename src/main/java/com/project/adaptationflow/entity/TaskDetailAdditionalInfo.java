package com.project.adaptationflow.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "task_detail_additional_info")
public class TaskDetailAdditionalInfo extends StandardEntityUUID {
    private static final long serialVersionUID = 4088438935129382007L;
    private TaskDetail taskDetail;

    private String documentUrl;

    private String category;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "task_detail_id", nullable = false)
    public TaskDetail getTaskDetail() {
        return taskDetail;
    }

    @NotNull
    @Column(name = "document_url", nullable = false, length = Integer.MAX_VALUE)
    public String getDocumentUrl() {
        return documentUrl;
    }

    @NotNull
    @Column(name = "category", nullable = false, length = Integer.MAX_VALUE)
    public String getCategory() {
        return category;
    }

}