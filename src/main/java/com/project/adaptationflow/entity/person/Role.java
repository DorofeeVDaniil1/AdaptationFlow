package com.project.adaptationflow.entity.person;

import com.project.adaptationflow.entity.StandardEntityUUID;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "role", schema = "public", indexes = {
        @Index(name = "role_name_key", columnList = "name", unique = true)
})
public class Role extends StandardEntityUUID {
    @Serial
    private static final long serialVersionUID = 6635794781039728975L;
    private UUID id;

    private String name;

    private String description;

    private Set<SysUserRole> sysUserRoles = new LinkedHashSet<>();


    @Column(name = "name", nullable = false)
    protected String getName() {
        return name;
    }

    @Column(name = "description", length = Integer.MAX_VALUE)
    protected String getDescription() {
        return description;
    }

    @OneToMany(mappedBy = "role")
    protected Set<SysUserRole> getSysUserRoles() {
        return sysUserRoles;
    }

}