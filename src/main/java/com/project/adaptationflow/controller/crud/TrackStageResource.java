package com.project.adaptationflow.controller.crud;

import com.fasterxml.jackson.databind.JsonNode;
import com.project.adaptationflow.dto.task.TrackStageDto;
import com.project.adaptationflow.service.TrackStageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rest/trackStages")
@RequiredArgsConstructor
public class TrackStageResource {

    private final TrackStageService trackStageService;

    @GetMapping
    public List<TrackStageDto> getAll() {
        return trackStageService.getAll();
    }

    @GetMapping("/{id}")
    public TrackStageDto getOne(@PathVariable UUID id) {
        return trackStageService.getOne(id);
    }

    @GetMapping("/by-ids")
    public List<TrackStageDto> getMany(@RequestParam List<UUID> ids) {
        return trackStageService.getMany(ids);
    }

    @PostMapping
    public TrackStageDto create(@RequestBody @Valid TrackStageDto dto) {
        return trackStageService.create(dto);
    }

    @PatchMapping("/{id}")
    public TrackStageDto patch(@PathVariable UUID id, @RequestBody JsonNode patchNode) throws IOException {
        return trackStageService.patch(id, patchNode);
    }

    @PatchMapping
    public List<UUID> patchMany(@RequestParam List<UUID> ids, @RequestBody JsonNode patchNode) throws IOException {
        return trackStageService.patchMany(ids, patchNode);
    }

    @DeleteMapping("/{id}")
    public TrackStageDto delete(@PathVariable UUID id) {
        return trackStageService.delete(id);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<UUID> ids) {
        trackStageService.deleteMany(ids);
    }
}
