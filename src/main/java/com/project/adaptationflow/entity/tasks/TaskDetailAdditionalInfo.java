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

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "task_detail_id")
    private TaskDetail taskDetail;

    @Column(name = "document_url", nullable = false, length = Integer.MAX_VALUE)
    private String documentUrl;

    @Column(name = "category", nullable = false)
    private String category;

}