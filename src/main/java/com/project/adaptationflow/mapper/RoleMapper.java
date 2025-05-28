package com.project.adaptationflow.mapper;

import com.project.adaptationflow.dto.RoleDto;
import com.project.adaptationflow.entity.person.Role;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleMapper {
    Role toEntity(RoleDto roleDto);

    RoleDto toRoleDto(Role role);

    Role updateWithNull(RoleDto roleDto, @MappingTarget Role role);
}