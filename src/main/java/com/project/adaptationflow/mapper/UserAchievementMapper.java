package com.project.adaptationflow.mapper;

import com.project.adaptationflow.dto.AchievementDto;
import com.project.adaptationflow.entity.gamefication.UserAchievement;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserAchievementMapper {
    UserAchievement toEntity(AchievementDto.UserAchievementDto userAchievementDto);

    AchievementDto.UserAchievementDto toUserAchievementDto(UserAchievement userAchievement);
}