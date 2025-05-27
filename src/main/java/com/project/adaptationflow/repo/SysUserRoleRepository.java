package com.project.adaptationflow.repo;

import com.project.adaptationflow.entity.person.SysUserRole;
import com.project.adaptationflow.entity.person.SysUserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserRoleRepository extends JpaRepository<SysUserRole, SysUserRoleId> {
}