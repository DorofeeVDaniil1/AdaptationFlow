package com.project.adaptationflow.entity.tasks;

import com.project.adaptationflow.entity.StandardEntityUUID;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serial;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "task_detail_additional_info", schema = "public")
public class TaskDetailAdditionalInfo extends StandardEntityUUID {
    @Serial
    private static final long serialVersionUID = 2010954072490194152L;
    private UUID id;

    private TaskDetail taskDetail;

    private String documentUrl;

    private Object category;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "task_detail_id", nullable = false)
    protected TaskDetail getTaskDetail() {
        return taskDetail;
    }

    @Column(name = "document_url", nullable = false, length = Integer.MAX_VALUE)
    protected String getDocumentUrl() {
        return documentUrl;
    }

    @Column(name = "category", columnDefinition = "task_info_category(0, 0) not null")
    protected Object getCategory() {
        return category;
    }

}