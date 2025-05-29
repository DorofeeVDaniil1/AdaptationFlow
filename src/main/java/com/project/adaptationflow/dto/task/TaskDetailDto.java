package com.project.adaptationflow.dto.task;

import com.project.adaptationflow.entity.tasks.Task;
import com.project.adaptationflow.entity.tasks.TaskDetailAdditionalInfo;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.util.Set;

/**
 * DTO for {@link com.project.adaptationflow.entity.tasks.TaskDetail}
 */
@Value
public class TaskDetailDto {
    Task task;
    @NotNull
    String name;
    @NotNull
    String description;
    String status;
}