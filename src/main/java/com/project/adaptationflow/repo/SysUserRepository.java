package com.project.adaptationflow.repo;

import com.project.adaptationflow.entity.person.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SysUserRepository extends JpaRepository<SysUser, UUID> {
    Optional<SysUser> findByLogin(String login);
}