package com.project.adaptationflow.controller.crud;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.adaptationflow.dto.AchievementDto;
import com.project.adaptationflow.entity.gamefication.Achievement;
import com.project.adaptationflow.mapper.AchievementMapper;
import com.project.adaptationflow.repo.AchievementRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/rest/achievements")
@RequiredArgsConstructor
public class AchievementResource {

    private final AchievementRepository achievementRepository;

    private final AchievementMapper achievementMapper;

    private final ObjectMapper objectMapper;

    @GetMapping
    public List<AchievementDto> getAll() {
        List<Achievement> achievements = achievementRepository.findAll();
        return achievements.stream()
                .map(achievementMapper::toAchievementDto)
                .toList();
    }

    @GetMapping("/{id}")
    public AchievementDto getOne(@PathVariable UUID id) {
        Optional<Achievement> achievementOptional = achievementRepository.findById(id);
        return achievementMapper.toAchievementDto(achievementOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }

    @GetMapping("/by-ids")
    public List<AchievementDto> getMany(@RequestParam List<UUID> ids) {
        List<Achievement> achievements = achievementRepository.findAllById(ids);
        return achievements.stream()
                .map(achievementMapper::toAchievementDto)
                .toList();
    }

    @PostMapping
    @Transactional
    public AchievementDto create(@RequestBody @Valid AchievementDto dto) {
        Achievement achievement = achievementMapper.toEntity(dto);
        Achievement resultAchievement = achievementRepository.save(achievement);
        return achievementMapper.toAchievementDto(resultAchievement);
    }

    @PatchMapping("/{id}")
    public AchievementDto patch(@PathVariable UUID id, @RequestBody JsonNode patchNode) throws IOException {
        Achievement achievement = achievementRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        AchievementDto achievementDto = achievementMapper.toAchievementDto(achievement);
        objectMapper.readerForUpdating(achievementDto).readValue(patchNode);
        achievementMapper.updateWithNull(achievementDto, achievement);

        Achievement resultAchievement = achievementRepository.save(achievement);
        return achievementMapper.toAchievementDto(resultAchievement);
    }

    @PatchMapping
    public List<UUID> patchMany(@RequestParam List<UUID> ids, @RequestBody JsonNode patchNode) throws IOException {
        Collection<Achievement> achievements = achievementRepository.findAllById(ids);

        for (Achievement achievement : achievements) {
            AchievementDto achievementDto = achievementMapper.toAchievementDto(achievement);
            objectMapper.readerForUpdating(achievementDto).readValue(patchNode);
            achievementMapper.updateWithNull(achievementDto, achievement);
        }

        List<Achievement> resultAchievements = achievementRepository.saveAll(achievements);
        return resultAchievements.stream()
                .map(Achievement::getId)
                .toList();
    }

    @DeleteMapping("/{id}")
    public AchievementDto delete(@PathVariable UUID id) {
        Achievement achievement = achievementRepository.findById(id).orElse(null);
        if (achievement != null) {
            achievementRepository.delete(achievement);
        }
        return achievementMapper.toAchievementDto(achievement);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<UUID> ids) {
        achievementRepository.deleteAllById(ids);
    }
}
