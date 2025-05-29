package com.project.adaptationflow.mapper;

import com.project.adaptationflow.dto.task.TaskDetailAdditionalInfoDto;
import com.project.adaptationflow.entity.tasks.TaskDetailAdditionalInfo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskDetailAdditionalInfoMapper {
    TaskDetailAdditionalInfo toEntity(TaskDetailAdditionalInfoDto taskDetailAdditionalInfoDto);

    TaskDetailAdditionalInfoDto toTaskDetailAdditionalInfoDto(TaskDetailAdditionalInfo taskDetailAdditionalInfo);

    TaskDetailAdditionalInfo updateWithNull(TaskDetailAdditionalInfoDto taskDetailAdditionalInfoDto, @MappingTarget TaskDetailAdditionalInfo taskDetailAdditionalInfo);
}