package com.project.adaptationflow.repo;

import com.project.adaptationflow.entity.tasks.TaskDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskDetailRepository extends JpaRepository<TaskDetail, UUID> {
}