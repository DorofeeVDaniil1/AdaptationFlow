package com.project.adaptationflow.dto.task;

import com.project.adaptationflow.entity.tasks.Task;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link com.project.adaptationflow.entity.tasks.TrackStage}
 */
@Value
public class TrackStageDto {
    UUID id;
    OnboardingTrackDto onboardingTrack;
    @NotNull
    String title;
    @NotNull
    String description;
    @NotNull
    Integer orderIndex;
}