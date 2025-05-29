package com.project.adaptationflow.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.adaptationflow.dto.task.TrackStageDto;
import com.project.adaptationflow.entity.tasks.TrackStage;
import com.project.adaptationflow.mapper.TrackStageMapper;
import com.project.adaptationflow.repo.TrackStageRepository;
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
public class TrackStageService {

    private final TrackStageMapper trackStageMapper;

    private final TrackStageRepository trackStageRepository;

    private final ObjectMapper objectMapper;

    public List<TrackStageDto> getAll() {
        List<TrackStage> trackStages = trackStageRepository.findAll();
        return trackStages.stream()
                .map(trackStageMapper::toTrackStageDto)
                .toList();
    }

    public TrackStageDto getOne(UUID id) {
        Optional<TrackStage> trackStageOptional = trackStageRepository.findById(id);
        return trackStageMapper.toTrackStageDto(trackStageOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }

    public List<TrackStageDto> getMany(List<UUID> ids) {
        List<TrackStage> trackStages = trackStageRepository.findAllById(ids);
        return trackStages.stream()
                .map(trackStageMapper::toTrackStageDto)
                .toList();
    }

    public TrackStageDto create(TrackStageDto dto) {
        TrackStage trackStage = trackStageMapper.toEntity(dto);
        TrackStage resultTrackStage = trackStageRepository.save(trackStage);
        return trackStageMapper.toTrackStageDto(resultTrackStage);
    }

    public TrackStageDto patch(UUID id, JsonNode patchNode) throws IOException {
        TrackStage trackStage = trackStageRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        TrackStageDto trackStageDto = trackStageMapper.toTrackStageDto(trackStage);
        objectMapper.readerForUpdating(trackStageDto).readValue(patchNode);
        trackStageMapper.updateWithNull(trackStageDto, trackStage);

        TrackStage resultTrackStage = trackStageRepository.save(trackStage);
        return trackStageMapper.toTrackStageDto(resultTrackStage);
    }

    public List<UUID> patchMany(List<UUID> ids, JsonNode patchNode) throws IOException {
        Collection<TrackStage> trackStages = trackStageRepository.findAllById(ids);

        for (TrackStage trackStage : trackStages) {
            TrackStageDto trackStageDto = trackStageMapper.toTrackStageDto(trackStage);
            objectMapper.readerForUpdating(trackStageDto).readValue(patchNode);
            trackStageMapper.updateWithNull(trackStageDto, trackStage);
        }

        List<TrackStage> resultTrackStages = trackStageRepository.saveAll(trackStages);
        return resultTrackStages.stream()
                .map(TrackStage::getId)
                .toList();
    }

    public TrackStageDto delete(UUID id) {
        TrackStage trackStage = trackStageRepository.findById(id).orElse(null);
        if (trackStage != null) {
            trackStageRepository.delete(trackStage);
        }
        return trackStageMapper.toTrackStageDto(trackStage);
    }

    public void deleteMany(List<UUID> ids) {
        trackStageRepository.deleteAllById(ids);
    }
}
