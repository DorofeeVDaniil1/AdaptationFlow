package com.project.adaptationflow.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.adaptationflow.dto.task.TaskDetailDto;
import com.project.adaptationflow.entity.tasks.TaskDetail;
import com.project.adaptationflow.mapper.TaskDetailMapper;
import com.project.adaptationflow.repo.TaskDetailRepository;
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
public class TaskDetailService {

    private final TaskDetailMapper taskDetailMapper;

    private final TaskDetailRepository taskDetailRepository;

    private final ObjectMapper objectMapper;

    public List<TaskDetailDto> getAll() {
        List<TaskDetail> taskDetails = taskDetailRepository.findAll();
        return taskDetails.stream()
                .map(taskDetailMapper::toTaskDetailDto)
                .toList();
    }

    public TaskDetailDto getOne(UUID id) {
        Optional<TaskDetail> taskDetailOptional = taskDetailRepository.findById(id);
        return taskDetailMapper.toTaskDetailDto(taskDetailOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }

    public List<TaskDetailDto> getMany(List<UUID> ids) {
        List<TaskDetail> taskDetails = taskDetailRepository.findAllById(ids);
        return taskDetails.stream()
                .map(taskDetailMapper::toTaskDetailDto)
                .toList();
    }

    public TaskDetailDto create(TaskDetailDto dto) {
        TaskDetail taskDetail = taskDetailMapper.toEntity(dto);
        TaskDetail resultTaskDetail = taskDetailRepository.save(taskDetail);
        return taskDetailMapper.toTaskDetailDto(resultTaskDetail);
    }

    public TaskDetailDto patch(UUID id, JsonNode patchNode) throws IOException {
        TaskDetail taskDetail = taskDetailRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        TaskDetailDto taskDetailDto = taskDetailMapper.toTaskDetailDto(taskDetail);
        objectMapper.readerForUpdating(taskDetailDto).readValue(patchNode);
        taskDetailMapper.updateWithNull(taskDetailDto, taskDetail);

        TaskDetail resultTaskDetail = taskDetailRepository.save(taskDetail);
        return taskDetailMapper.toTaskDetailDto(resultTaskDetail);
    }

    public List<UUID> patchMany(List<UUID> ids, JsonNode patchNode) throws IOException {
        Collection<TaskDetail> taskDetails = taskDetailRepository.findAllById(ids);

        for (TaskDetail taskDetail : taskDetails) {
            TaskDetailDto taskDetailDto = taskDetailMapper.toTaskDetailDto(taskDetail);
            objectMapper.readerForUpdating(taskDetailDto).readValue(patchNode);
            taskDetailMapper.updateWithNull(taskDetailDto, taskDetail);
        }

        List<TaskDetail> resultTaskDetails = taskDetailRepository.saveAll(taskDetails);
        return resultTaskDetails.stream()
                .map(TaskDetail::getId)
                .toList();
    }

    public TaskDetailDto delete(UUID id) {
        TaskDetail taskDetail = taskDetailRepository.findById(id).orElse(null);
        if (taskDetail != null) {
            taskDetailRepository.delete(taskDetail);
        }
        return taskDetailMapper.toTaskDetailDto(taskDetail);
    }

    public void deleteMany(List<UUID> ids) {
        taskDetailRepository.deleteAllById(ids);
    }
}
