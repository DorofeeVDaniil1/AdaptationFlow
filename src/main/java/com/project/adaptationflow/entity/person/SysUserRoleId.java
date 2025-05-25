package com.project.adaptationflow.entity.person;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Embeddable
public class SysUserRoleId implements Serializable {
    private static final long serialVersionUID = -4675079510385780694L;
    private UUID userId;

    private UUID roleId;

    @Column(name = "user_id", nullable = false)
    protected UUID getUserId() {
        return userId;
    }

    @Column(name = "role_id", nullable = false)
    protected UUID getRoleId() {
        return roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SysUserRoleId entity = (SysUserRoleId) o;
        return Objects.equals(this.roleId, entity.roleId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, userId);
    }

}