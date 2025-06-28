package com.project.adaptationflow.dto.task;

import com.project.adaptationflow.entity.tasks.OnboardingTrack;
import lombok.Value;

import java.util.UUID;

/**
 * DTO for {@link OnboardingTrack}
 */
@Value
public class OnboardingTrackDto {
    UUID id;
    String title;
    String description;
}