package com.project.adaptationflow.controller.crud;
import com.fasterxml.jackson.databind.JsonNode;
import com.project.adaptationflow.dto.task.TaskDetailAdditionalInfoDto;
import com.project.adaptationflow.service.TaskDetailAdditionalInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rest/taskDetailAdditionalInfos")
@RequiredArgsConstructor
public class TaskDetailAdditionalInfoResource {

    private final TaskDetailAdditionalInfoService taskDetailAdditionalInfoService;

    @GetMapping
    public List<TaskDetailAdditionalInfoDto> getAll() {
        return taskDetailAdditionalInfoService.getAll();
    }

    @GetMapping("/{id}")
    public TaskDetailAdditionalInfoDto getOne(@PathVariable UUID id) {
        return taskDetailAdditionalInfoService.getOne(id);
    }

    @GetMapping("/by-ids")
    public List<TaskDetailAdditionalInfoDto> getMany(@RequestParam List<UUID> ids) {
        return taskDetailAdditionalInfoService.getMany(ids);
    }

    @PostMapping
    public TaskDetailAdditionalInfoDto create(@RequestBody TaskDetailAdditionalInfoDto dto) {
        return taskDetailAdditionalInfoService.create(dto);
    }

    @PatchMapping("/{id}")
    public TaskDetailAdditionalInfoDto patch(@PathVariable UUID id, @RequestBody JsonNode patchNode) throws IOException {
        return taskDetailAdditionalInfoService.patch(id, patchNode);
    }

    @PatchMapping
    public List<UUID> patchMany(@RequestParam List<UUID> ids, @RequestBody JsonNode patchNode) throws IOException {
        return taskDetailAdditionalInfoService.patchMany(ids, patchNode);
    }

    @DeleteMapping("/{id}")
    public TaskDetailAdditionalInfoDto delete(@PathVariable UUID id) {
        return taskDetailAdditionalInfoService.delete(id);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<UUID> ids) {
        taskDetailAdditionalInfoService.deleteMany(ids);
    }
}
