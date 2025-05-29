package com.project.adaptationflow.controller.crud;

import com.fasterxml.jackson.databind.JsonNode;
import com.project.adaptationflow.dto.task.TaskDto;
import com.project.adaptationflow.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rest/tasks")
@RequiredArgsConstructor
public class TaskResource {

    private final TaskService taskService;

    @GetMapping
    public List<TaskDto> getAll() {
        return taskService.getAll();
    }

    @GetMapping("/{id}")
    public TaskDto getOne(@PathVariable UUID id) {
        return taskService.getOne(id);
    }

    @GetMapping("/by-ids")
    public List<TaskDto> getMany(@RequestParam List<UUID> ids) {
        return taskService.getMany(ids);
    }

    @PostMapping
    public TaskDto create(@RequestBody TaskDto dto) {
        return taskService.create(dto);
    }

    @PatchMapping("/{id}")
    public TaskDto patch(@PathVariable UUID id, @RequestBody JsonNode patchNode) throws IOException {
        return taskService.patch(id, patchNode);
    }

    @PatchMapping
    public List<UUID> patchMany(@RequestParam List<UUID> ids, @RequestBody JsonNode patchNode) throws IOException {
        return taskService.patchMany(ids, patchNode);
    }

    @DeleteMapping("/{id}")
    public TaskDto delete(@PathVariable UUID id) {
        return taskService.delete(id);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<UUID> ids) {
        taskService.deleteMany(ids);
    }
}
