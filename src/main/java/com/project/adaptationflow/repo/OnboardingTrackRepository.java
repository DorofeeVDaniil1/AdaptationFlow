package com.project.adaptationflow.repo;

import com.project.adaptationflow.entity.tasks.OnboardingTrack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OnboardingTrackRepository extends JpaRepository<OnboardingTrack, UUID> {
}