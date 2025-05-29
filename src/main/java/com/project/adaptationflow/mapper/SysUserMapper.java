package com.project.adaptationflow.mapper;

import com.project.adaptationflow.dto.AchievementDto;
import com.project.adaptationflow.entity.person.SysUser;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SysUserMapper {
    SysUser toEntity(AchievementDto.UserAchievementDto.SysUserDto sysUserDto);

    AchievementDto.UserAchievementDto.SysUserDto toSysUserDto(SysUser sysUser);
}