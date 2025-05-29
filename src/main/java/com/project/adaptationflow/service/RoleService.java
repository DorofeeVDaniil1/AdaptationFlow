package com.project.adaptationflow.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.adaptationflow.dto.RoleDto;
import com.project.adaptationflow.entity.person.Role;
import com.project.adaptationflow.mapper.RoleMapper;
import com.project.adaptationflow.repo.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoleService {

    private final RoleMapper roleMapper;

    private final RoleRepository roleRepository;

    private final ObjectMapper objectMapper;

    public RoleService(RoleMapper roleMapper, RoleRepository roleRepository, ObjectMapper objectMapper) {
        this.roleMapper = roleMapper;
        this.roleRepository = roleRepository;
        this.objectMapper = objectMapper;
    }

    public List<RoleDto> getAll() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(roleMapper::toRoleDto)
                .toList();
    }

    public RoleDto getOne(UUID id) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        return roleMapper.toRoleDto(roleOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }

    public List<RoleDto> getMany(List<UUID> ids) {
        List<Role> roles = roleRepository.findAllById(ids);
        return roles.stream()
                .map(roleMapper::toRoleDto)
                .toList();
    }

    public RoleDto create(RoleDto dto) {
        Role role = roleMapper.toEntity(dto);
        Role resultRole = roleRepository.save(role);
        return roleMapper.toRoleDto(resultRole);
    }

    public RoleDto patch(UUID id, JsonNode patchNode) throws IOException {
        Role role = roleRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        RoleDto roleDto = roleMapper.toRoleDto(role);
        objectMapper.readerForUpdating(roleDto).readValue(patchNode);
        roleMapper.updateWithNull(roleDto, role);

        Role resultRole = roleRepository.save(role);
        return roleMapper.toRoleDto(resultRole);
    }

    public List<UUID> patchMany(List<UUID> ids, JsonNode patchNode) throws IOException {
        Collection<Role> roles = roleRepository.findAllById(ids);

        for (Role role : roles) {
            RoleDto roleDto = roleMapper.toRoleDto(role);
            objectMapper.readerForUpdating(roleDto).readValue(patchNode);
            roleMapper.updateWithNull(roleDto, role);
        }

        List<Role> resultRoles = roleRepository.saveAll(roles);
        return resultRoles.stream()
                .map(Role::getId)
                .toList();
    }

    public RoleDto delete(UUID id) {
        Role role = roleRepository.findById(id).orElse(null);
        if (role != null) {
            roleRepository.delete(role);
        }
        return roleMapper.toRoleDto(role);
    }

    public void deleteMany(List<UUID> ids) {
        roleRepository.deleteAllById(ids);
    }
}
