package com.project.adaptationflow.dto.task;

import lombok.Value;

/**
 * DTO for {@link com.project.adaptationflow.entity.tasks.TaskDetailAdditionalInfo}
 */
@Value
public class TaskDetailAdditionalInfoDto {
    TaskDetailDto taskDetail;
    String documentUrl;
    String category;
}