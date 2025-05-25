package com.project.adaptationflow.entity.person;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "sys_user_role", schema = "public")
public class SysUserRole implements Serializable {
    @Serial
    private static final long serialVersionUID = -311464475003468949L;
    private SysUserRoleId id;

    private SysUser user;

    private Role role;

    private OffsetDateTime assignedAt;

    @EmbeddedId
    protected SysUserRoleId getId() {
        return id;
    }

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    protected SysUser getUser() {
        return user;
    }

    @MapsId("roleId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "role_id", nullable = false)
    protected Role getRole() {
        return role;
    }

    @Column(name = "assigned_at", nullable = false)
    protected OffsetDateTime getAssignedAt() {
        return assignedAt;
    }

}