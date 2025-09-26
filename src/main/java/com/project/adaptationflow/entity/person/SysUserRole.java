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
@ToString
@Entity
@Setter
@Getter
@Table(name = "sys_user_role", schema = "public")
public class SysUserRole implements Serializable {
    @Serial
    private static final long serialVersionUID = -311464475003468949L;

    @EmbeddedId
    private SysUserRoleId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private SysUser user;

    @MapsId("roleId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(name = "assigned_at", nullable = false)
    private OffsetDateTime assignedAt;

    public Role getRole() {
        return role;
    }

    public void setId(SysUserRoleId id) {
        this.id = id;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setAssignedAt(OffsetDateTime assignedAt) {
        this.assignedAt = assignedAt;
    }
}