package com.project.adaptationflow.mapper;

import com.project.adaptationflow.dto.task.OnboardingTrackDto;
import com.project.adaptationflow.entity.gamefication.OnboardingTrack;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OnboardingTrackMapper {
    OnboardingTrack toEntity(OnboardingTrackDto onboardingTrackDto);

    OnboardingTrackDto toOnboardingTrackDto(OnboardingTrack onboardingTrack);

    OnboardingTrack updateWithNull(OnboardingTrackDto onboardingTrackDto, @MappingTarget OnboardingTrack onboardingTrack);
}