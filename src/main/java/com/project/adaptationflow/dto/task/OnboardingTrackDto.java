package com.project.adaptationflow.dto.task;

import lombok.Value;

import java.util.UUID;

/**
 * DTO for {@link com.project.adaptationflow.entity.gamefication.OnboardingTrack}
 */
@Value
public class OnboardingTrackDto {
    UUID id;
    String title;
    String description;
}