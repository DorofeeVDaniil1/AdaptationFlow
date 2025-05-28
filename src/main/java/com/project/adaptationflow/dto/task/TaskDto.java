package com.project.adaptationflow.dto.task;

import com.project.adaptationflow.entity.tasks.TaskDetail;
import com.project.adaptationflow.entity.tasks.TrackStage;
import lombok.Value;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link com.project.adaptationflow.entity.tasks.Task}
 */
@Value
public class TaskDto {
    UUID id;
    TrackStageDto trackStage;
    String name;
    String description;
    String status;
    OffsetDateTime expiredTimeTask;
}