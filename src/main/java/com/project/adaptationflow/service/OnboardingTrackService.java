package com.project.adaptationflow.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.adaptationflow.dto.task.OnboardingTrackDto;
import com.project.adaptationflow.entity.tasks.OnboardingTrack;
import com.project.adaptationflow.mapper.OnboardingTrackMapper;
import com.project.adaptationflow.repo.OnboardingTrackRepository;
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
public class OnboardingTrackService {

    private final OnboardingTrackMapper onboardingTrackMapper;

    private final OnboardingTrackRepository onboardingTrackRepository;

    private final ObjectMapper objectMapper;

    public List<OnboardingTrackDto> getAll() {
        List<OnboardingTrack> onboardingTracks = onboardingTrackRepository.findAll();
        return onboardingTracks.stream()
                .map(onboardingTrackMapper::toOnboardingTrackDto)
                .toList();
    }

    public OnboardingTrackDto getOne(UUID id) {
        Optional<OnboardingTrack> onboardingTrackOptional = onboardingTrackRepository.findById(id);
        return onboardingTrackMapper.toOnboardingTrackDto(onboardingTrackOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }

    public List<OnboardingTrackDto> getMany(List<UUID> ids) {
        List<OnboardingTrack> onboardingTracks = onboardingTrackRepository.findAllById(ids);
        return onboardingTracks.stream()
                .map(onboardingTrackMapper::toOnboardingTrackDto)
                .toList();
    }

    public OnboardingTrackDto create(OnboardingTrackDto dto) {
        OnboardingTrack onboardingTrack = onboardingTrackMapper.toEntity(dto);
        OnboardingTrack resultOnboardingTrack = onboardingTrackRepository.save(onboardingTrack);
        return onboardingTrackMapper.toOnboardingTrackDto(resultOnboardingTrack);
    }

    public OnboardingTrackDto patch(UUID id, JsonNode patchNode) throws IOException {
        OnboardingTrack onboardingTrack = onboardingTrackRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        OnboardingTrackDto onboardingTrackDto = onboardingTrackMapper.toOnboardingTrackDto(onboardingTrack);
        objectMapper.readerForUpdating(onboardingTrackDto).readValue(patchNode);
        onboardingTrackMapper.updateWithNull(onboardingTrackDto, onboardingTrack);

        OnboardingTrack resultOnboardingTrack = onboardingTrackRepository.save(onboardingTrack);
        return onboardingTrackMapper.toOnboardingTrackDto(resultOnboardingTrack);
    }

    public List<UUID> patchMany(List<UUID> ids, JsonNode patchNode) throws IOException {
        Collection<OnboardingTrack> onboardingTracks = onboardingTrackRepository.findAllById(ids);

        for (OnboardingTrack onboardingTrack : onboardingTracks) {
            OnboardingTrackDto onboardingTrackDto = onboardingTrackMapper.toOnboardingTrackDto(onboardingTrack);
            objectMapper.readerForUpdating(onboardingTrackDto).readValue(patchNode);
            onboardingTrackMapper.updateWithNull(onboardingTrackDto, onboardingTrack);
        }

        List<OnboardingTrack> resultOnboardingTracks = onboardingTrackRepository.saveAll(onboardingTracks);
        return resultOnboardingTracks.stream()
                .map(OnboardingTrack::getId)
                .toList();
    }

    public OnboardingTrackDto delete(UUID id) {
        OnboardingTrack onboardingTrack = onboardingTrackRepository.findById(id).orElse(null);
        if (onboardingTrack != null) {
            onboardingTrackRepository.delete(onboardingTrack);
        }
        return onboardingTrackMapper.toOnboardingTrackDto(onboardingTrack);
    }

    public void deleteMany(List<UUID> ids) {
        onboardingTrackRepository.deleteAllById(ids);
    }
}
