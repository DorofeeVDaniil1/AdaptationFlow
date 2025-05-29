package com.project.adaptationflow.controller.crud;
import com.fasterxml.jackson.databind.JsonNode;
import com.project.adaptationflow.service.RoleService;
import com.project.adaptationflow.dto.RoleDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rest/roles")
public class RoleResource {

    private final RoleService roleService;

    public RoleResource(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<RoleDto> getAll() {
        return roleService.getAll();
    }

    @GetMapping("/{id}")
    public RoleDto getOne(@PathVariable UUID id) {
        return roleService.getOne(id);
    }

    @GetMapping("/by-ids")
    public List<RoleDto> getMany(@RequestParam List<UUID> ids) {
        return roleService.getMany(ids);
    }

    @PostMapping
    public RoleDto create(@RequestBody @Valid RoleDto dto) {
        return roleService.create(dto);
    }

    @PatchMapping("/{id}")
    public RoleDto patch(@PathVariable UUID id, @RequestBody JsonNode patchNode) throws IOException {
        return roleService.patch(id, patchNode);
    }

    @PatchMapping
    public List<UUID> patchMany(@RequestParam List<UUID> ids, @RequestBody JsonNode patchNode) throws IOException {
        return roleService.patchMany(ids, patchNode);
    }

    @DeleteMapping("/{id}")
    public RoleDto delete(@PathVariable UUID id) {
        return roleService.delete(id);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<UUID> ids) {
        roleService.deleteMany(ids);
    }
}
