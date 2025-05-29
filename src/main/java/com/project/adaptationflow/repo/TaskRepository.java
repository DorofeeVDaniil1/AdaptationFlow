package com.project.adaptationflow.repo;

import com.project.adaptationflow.entity.tasks.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
}