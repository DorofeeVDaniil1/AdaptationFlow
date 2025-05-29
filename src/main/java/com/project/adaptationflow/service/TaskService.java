package com.project.adaptationflow.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.adaptationflow.dto.task.TaskDto;
import com.project.adaptationflow.entity.tasks.Task;
import com.project.adaptationflow.mapper.TaskMapper;
import com.project.adaptationflow.repo.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskMapper taskMapper;

    private final TaskRepository taskRepository;

    private final ObjectMapper objectMapper;

    public List<TaskDto> getAll() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(taskMapper::toTaskDto)
                .toList();
    }

    public TaskDto getOne(UUID id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        return taskMapper.toTaskDto(taskOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }

    public List<TaskDto> getMany(List<UUID> ids) {
        List<Task> tasks = taskRepository.findAllById(ids);
        return tasks.stream()
                .map(taskMapper::toTaskDto)
                .toList();
    }

    public TaskDto create(TaskDto dto) {
        Task task = taskMapper.toEntity(dto);
        Task resultTask = taskRepository.save(task);
        return taskMapper.toTaskDto(resultTask);
    }

    public TaskDto patch(UUID id, JsonNode patchNode) throws IOException {
        Task task = taskRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        TaskDto taskDto = taskMapper.toTaskDto(task);
        objectMapper.readerForUpdating(taskDto).readValue(patchNode);
        taskMapper.updateWithNull(taskDto, task);

        Task resultTask = taskRepository.save(task);
        return taskMapper.toTaskDto(resultTask);
    }

    public List<UUID> patchMany(List<UUID> ids, JsonNode patchNode) throws IOException {
        Collection<Task> tasks = taskRepository.findAllById(ids);

        for (Task task : tasks) {
            TaskDto taskDto = taskMapper.toTaskDto(task);
            objectMapper.readerForUpdating(taskDto).readValue(patchNode);
            taskMapper.updateWithNull(taskDto, task);
        }

        List<Task> resultTasks = taskRepository.saveAll(tasks);
        return resultTasks.stream()
                .map(Task::getId)
                .toList();
    }

    public TaskDto delete(UUID id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            taskRepository.delete(task);
        }
        return taskMapper.toTaskDto(task);
    }

    public void deleteMany(List<UUID> ids) {
        taskRepository.deleteAllById(ids);
    }
}
