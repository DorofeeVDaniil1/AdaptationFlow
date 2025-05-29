package com.project.adaptationflow.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.adaptationflow.dto.task.TaskDetailAdditionalInfoDto;
import com.project.adaptationflow.entity.tasks.TaskDetailAdditionalInfo;
import com.project.adaptationflow.mapper.TaskDetailAdditionalInfoMapper;
import com.project.adaptationflow.repo.TaskDetailAdditionalInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.Instant;
import java.util.*;

@RequiredArgsConstructor
@Service
public class TaskDetailAdditionalInfoService {

    private final TaskDetailAdditionalInfoMapper taskDetailAdditionalInfoMapper;

    private final TaskDetailAdditionalInfoRepository taskDetailAdditionalInfoRepository;

    private final ObjectMapper objectMapper;

    public List<TaskDetailAdditionalInfoDto> getAll() {
        List<TaskDetailAdditionalInfo> taskDetailAdditionalInfos = taskDetailAdditionalInfoRepository.findAll();
        return taskDetailAdditionalInfos.stream()
                .map(taskDetailAdditionalInfoMapper::toTaskDetailAdditionalInfoDto)
                .toList();
    }

    public TaskDetailAdditionalInfoDto getOne(UUID id) {
        Optional<TaskDetailAdditionalInfo> taskDetailAdditionalInfoOptional = taskDetailAdditionalInfoRepository.findById(id);
        return taskDetailAdditionalInfoMapper.toTaskDetailAdditionalInfoDto(taskDetailAdditionalInfoOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }

    public List<TaskDetailAdditionalInfoDto> getMany(List<UUID> ids) {
        List<TaskDetailAdditionalInfo> taskDetailAdditionalInfos = taskDetailAdditionalInfoRepository.findAllById(ids);
        return taskDetailAdditionalInfos.stream()
                .map(taskDetailAdditionalInfoMapper::toTaskDetailAdditionalInfoDto)
                .toList();
    }

    public TaskDetailAdditionalInfoDto create(TaskDetailAdditionalInfoDto dto) {
        TaskDetailAdditionalInfo taskDetailAdditionalInfo = taskDetailAdditionalInfoMapper.toEntity(dto);
        taskDetailAdditionalInfo.setCreatedBy("system");
        taskDetailAdditionalInfo.setCreateTs(Date.from(Instant.now()));
        TaskDetailAdditionalInfo resultTaskDetailAdditionalInfo = taskDetailAdditionalInfoRepository.save(taskDetailAdditionalInfo);
        return taskDetailAdditionalInfoMapper.toTaskDetailAdditionalInfoDto(resultTaskDetailAdditionalInfo);
    }

    public TaskDetailAdditionalInfoDto patch(UUID id, JsonNode patchNode) throws IOException {
        TaskDetailAdditionalInfo taskDetailAdditionalInfo = taskDetailAdditionalInfoRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        TaskDetailAdditionalInfoDto taskDetailAdditionalInfoDto = taskDetailAdditionalInfoMapper.toTaskDetailAdditionalInfoDto(taskDetailAdditionalInfo);
        objectMapper.readerForUpdating(taskDetailAdditionalInfoDto).readValue(patchNode);
        taskDetailAdditionalInfoMapper.updateWithNull(taskDetailAdditionalInfoDto, taskDetailAdditionalInfo);

        TaskDetailAdditionalInfo resultTaskDetailAdditionalInfo = taskDetailAdditionalInfoRepository.save(taskDetailAdditionalInfo);
        return taskDetailAdditionalInfoMapper.toTaskDetailAdditionalInfoDto(resultTaskDetailAdditionalInfo);
    }

    public List<UUID> patchMany(List<UUID> ids, JsonNode patchNode) throws IOException {
        Collection<TaskDetailAdditionalInfo> taskDetailAdditionalInfos = taskDetailAdditionalInfoRepository.findAllById(ids);

        for (TaskDetailAdditionalInfo taskDetailAdditionalInfo : taskDetailAdditionalInfos) {
            TaskDetailAdditionalInfoDto taskDetailAdditionalInfoDto = taskDetailAdditionalInfoMapper.toTaskDetailAdditionalInfoDto(taskDetailAdditionalInfo);
            objectMapper.readerForUpdating(taskDetailAdditionalInfoDto).readValue(patchNode);
            taskDetailAdditionalInfoMapper.updateWithNull(taskDetailAdditionalInfoDto, taskDetailAdditionalInfo);
        }

        List<TaskDetailAdditionalInfo> resultTaskDetailAdditionalInfos = taskDetailAdditionalInfoRepository.saveAll(taskDetailAdditionalInfos);
        return resultTaskDetailAdditionalInfos.stream()
                .map(TaskDetailAdditionalInfo::getId)
                .toList();
    }

    public TaskDetailAdditionalInfoDto delete(UUID id) {
        TaskDetailAdditionalInfo taskDetailAdditionalInfo = taskDetailAdditionalInfoRepository.findById(id).orElse(null);
        if (taskDetailAdditionalInfo != null) {
            taskDetailAdditionalInfoRepository.delete(taskDetailAdditionalInfo);
        }
        return taskDetailAdditionalInfoMapper.toTaskDetailAdditionalInfoDto(taskDetailAdditionalInfo);
    }

    public void deleteMany(List<UUID> ids) {
        taskDetailAdditionalInfoRepository.deleteAllById(ids);
    }
}
