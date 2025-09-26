package com.project.adaptationflow.entity.user;

import com.project.adaptationflow.entity.StandardEntityUUID;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "role", uniqueConstraints = {
        @UniqueConstraint(name = "uq_role_name", columnNames = {"name"})
})
public class Role extends StandardEntityUUID {
    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @OneToMany(mappedBy = "role")
    private Set<SysUserRoleLink> userLinks = new LinkedHashSet<>();

}