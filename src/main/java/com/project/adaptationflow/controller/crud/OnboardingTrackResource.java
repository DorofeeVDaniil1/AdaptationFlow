package com.project.adaptationflow.controller.crud;

import com.fasterxml.jackson.databind.JsonNode;
import com.project.adaptationflow.dto.task.OnboardingTrackDto;
import com.project.adaptationflow.service.OnboardingTrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rest/onboardingTracks")
@RequiredArgsConstructor
public class OnboardingTrackResource {

    private final OnboardingTrackService onboardingTrackService;

    @GetMapping
    public List<OnboardingTrackDto> getAll() {
        return onboardingTrackService.getAll();
    }

    @GetMapping("/{id}")
    public OnboardingTrackDto getOne(@PathVariable UUID id) {
        return onboardingTrackService.getOne(id);
    }

    @GetMapping("/by-ids")
    public List<OnboardingTrackDto> getMany(@RequestParam List<UUID> ids) {
        return onboardingTrackService.getMany(ids);
    }

    @PostMapping
    public OnboardingTrackDto create(@RequestBody OnboardingTrackDto dto) {
        return onboardingTrackService.create(dto);
    }

    @PatchMapping("/{id}")
    public OnboardingTrackDto patch(@PathVariable UUID id, @RequestBody JsonNode patchNode) throws IOException {
        return onboardingTrackService.patch(id, patchNode);
    }

    @PatchMapping
    public List<UUID> patchMany(@RequestParam List<UUID> ids, @RequestBody JsonNode patchNode) throws IOException {
        return onboardingTrackService.patchMany(ids, patchNode);
    }

    @DeleteMapping("/{id}")
    public OnboardingTrackDto delete(@PathVariable UUID id) {
        return onboardingTrackService.delete(id);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<UUID> ids) {
        onboardingTrackService.deleteMany(ids);
    }
}
