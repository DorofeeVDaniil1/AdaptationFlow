package com.project.adaptationflow.controller.crud;

import com.fasterxml.jackson.databind.JsonNode;
import com.project.adaptationflow.service.TaskDetailService;
import com.project.adaptationflow.dto.task.TaskDetailDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rest/taskDetails")
@RequiredArgsConstructor
public class TaskDetailResource {

    private final TaskDetailService taskDetailService;

    @GetMapping
    public List<TaskDetailDto> getAll() {
        return taskDetailService.getAll();
    }

    @GetMapping("/{id}")
    public TaskDetailDto getOne(@PathVariable UUID id) {
        return taskDetailService.getOne(id);
    }

    @GetMapping("/by-ids")
    public List<TaskDetailDto> getMany(@RequestParam List<UUID> ids) {
        return taskDetailService.getMany(ids);
    }

    @PostMapping
    public TaskDetailDto create(@RequestBody @Valid TaskDetailDto dto) {
        return taskDetailService.create(dto);
    }

    @PatchMapping("/{id}")
    public TaskDetailDto patch(@PathVariable UUID id, @RequestBody JsonNode patchNode) throws IOException {
        return taskDetailService.patch(id, patchNode);
    }

    @PatchMapping
    public List<UUID> patchMany(@RequestParam List<UUID> ids, @RequestBody JsonNode patchNode) throws IOException {
        return taskDetailService.patchMany(ids, patchNode);
    }

    @DeleteMapping("/{id}")
    public TaskDetailDto delete(@PathVariable UUID id) {
        return taskDetailService.delete(id);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<UUID> ids) {
        taskDetailService.deleteMany(ids);
    }
}
