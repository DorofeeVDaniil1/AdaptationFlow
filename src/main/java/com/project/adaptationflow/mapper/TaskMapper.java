package com.project.adaptationflow.mapper;

import com.project.adaptationflow.dto.task.TaskDto;
import com.project.adaptationflow.entity.tasks.Task;
import com.project.adaptationflow.entity.tasks.TrackStage;
import com.project.adaptationflow.repo.TrackStageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class TaskMapper {

    @Autowired
    protected TrackStageRepository trackStageRepository;

    @Mapping(target = "trackStage.", source = "trackStage.id")
    public abstract Task toEntity(TaskDto taskDto);

    @AfterMapping
    protected void linkTaskDetails(@MappingTarget Task task) {
        if (task.getTaskDetails() != null) {
            task.getTaskDetails().forEach(taskDetail -> taskDetail.setTask(task));
        }
    }

    @Mapping(target = "trackStage.id", source = "trackStage.id")
    public abstract TaskDto toTaskDto(Task task);

    @Mapping(target = "trackStage.id", source = "trackStage.id")
    public abstract Task updateWithNull(TaskDto taskDto, @MappingTarget Task task);

    protected TrackStage map(UUID id) {
        if (id == null) return null;
        return trackStageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TrackStage not found"));
    }
}