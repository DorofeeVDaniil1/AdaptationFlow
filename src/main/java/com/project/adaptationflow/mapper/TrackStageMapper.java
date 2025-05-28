package com.project.adaptationflow.mapper;

import com.project.adaptationflow.dto.task.TrackStageDto;
import com.project.adaptationflow.entity.gamefication.OnboardingTrack;
import com.project.adaptationflow.entity.tasks.TrackStage;
import com.project.adaptationflow.repo.OnboardingTrackRepository;
import jakarta.persistence.EntityNotFoundException;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;


@Mapper(componentModel = "spring", uses = {TaskMapper.class})
public abstract class TrackStageMapper {

    @Autowired
    protected OnboardingTrackRepository onboardingTrackRepository;

    @AfterMapping
    protected void linkTasks(@MappingTarget TrackStage trackStage) {
        if (trackStage.getTasks() != null) {
            trackStage.getTasks().forEach(task -> task.setTrackStage(trackStage));
        }
    }

    @Mapping(target = "onboardingTrack", source = "onboardingTrack.id")
    public abstract TrackStage toEntity(TrackStageDto dto);

    @Mapping(target = "onboardingTrack.id", source = "onboardingTrack.id")
    public abstract TrackStageDto toTrackStageDto(TrackStage entity);

    @Mapping(target = "onboardingTrack.id", source = "onboardingTrack.id")
    public abstract TrackStage updateWithNull(TrackStageDto dto, @MappingTarget TrackStage entity);


    protected OnboardingTrack map(UUID id) {
        if (id == null) return null;
        return onboardingTrackRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("OnboardingTrack not found"));
    }
}
