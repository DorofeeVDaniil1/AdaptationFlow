package com.project.adaptationflow.repo;

import com.project.adaptationflow.entity.user.SysUserRoleLink;
import com.project.adaptationflow.entity.user.SysUserRoleLinkId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SysUserRoleRepository extends JpaRepository<SysUserRoleLink, SysUserRoleLinkId> {

    List<SysUserRoleLink> findByUser_Id(UUID userId);
    List<SysUserRoleLink> findByRole_Id(UUID roleId);

    void deleteByUser_IdAndRole_Id(UUID userId, UUID roleId);
}
