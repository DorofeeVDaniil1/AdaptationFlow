package com.project.adaptationflow.entity.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "sys_user_role_link", indexes = {
        @Index(name = "idx_sys_user_role_link_user_id", columnList = "user_id"),
        @Index(name = "idx_sys_user_role_link_role_id", columnList = "role_id")
})
public class SysUserRoleLink {
    @EmbeddedId
    private SysUserRoleLinkId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private SysUser user;

    @MapsId("roleId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

}