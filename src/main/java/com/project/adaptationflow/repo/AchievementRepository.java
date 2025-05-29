package com.project.adaptationflow.repo;

import com.project.adaptationflow.entity.gamefication.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AchievementRepository extends JpaRepository<Achievement, UUID> {
}