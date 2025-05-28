package com.project.adaptationflow.mapper;

import com.project.adaptationflow.dto.task.TaskDetailDto;
import com.project.adaptationflow.entity.tasks.TaskDetail;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {TaskDetailAdditionalInfoMapper.class})
public interface TaskDetailMapper {
    TaskDetail toEntity(TaskDetailDto taskDetailDto);

    @AfterMapping
    default void linkTaskDetailAdditionalInfos(@MappingTarget TaskDetail taskDetail) {
        taskDetail.getTaskDetailAdditionalInfos().forEach(taskDetailAdditionalInfo -> taskDetailAdditionalInfo.setTaskDetail(taskDetail));
    }

    TaskDetailDto toTaskDetailDto(TaskDetail taskDetail);

    TaskDetail updateWithNull(TaskDetailDto taskDetailDto, @MappingTarget TaskDetail taskDetail);
}