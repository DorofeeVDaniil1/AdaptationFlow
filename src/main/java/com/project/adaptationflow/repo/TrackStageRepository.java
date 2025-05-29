package com.project.adaptationflow.repo;

import com.project.adaptationflow.entity.tasks.TrackStage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TrackStageRepository extends JpaRepository<TrackStage, UUID> {
}