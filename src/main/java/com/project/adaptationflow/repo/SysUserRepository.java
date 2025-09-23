package com.project.adaptationflow.repo;

import com.project.adaptationflow.entity.user.SysUser;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SysUserRepository extends JpaRepository<SysUser, UUID> {
    @EntityGraph(attributePaths = {"roleLinks.role"})
    Optional<SysUser> findByLogin(String login);
}