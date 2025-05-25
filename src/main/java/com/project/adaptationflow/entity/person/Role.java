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

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @OneToMany(mappedBy = "role")
    private Set<SysUserRole> sysUserRoles = new LinkedHashSet<>();

}