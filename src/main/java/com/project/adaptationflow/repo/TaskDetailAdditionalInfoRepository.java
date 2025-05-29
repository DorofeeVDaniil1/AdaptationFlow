package com.project.adaptationflow.repo;

import com.project.adaptationflow.entity.tasks.TaskDetailAdditionalInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskDetailAdditionalInfoRepository extends JpaRepository<TaskDetailAdditionalInfo, UUID> {
}