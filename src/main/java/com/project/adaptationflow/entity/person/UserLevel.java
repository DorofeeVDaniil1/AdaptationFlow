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
@Table(name = "user_level", schema = "public", indexes = {
        @Index(name = "user_level_title_key", columnList = "title", unique = true)
})
public class UserLevel extends StandardEntityUUID {
    @Serial
    private static final long serialVersionUID = -7629891876657492716L;
    private UUID id;

    private String title;

    private Integer minPoints;

    private Integer maxPoints;

    private String description;

    private Set<SysUser> sysUsers = new LinkedHashSet<>();


    @Column(name = "title", nullable = false, length = 100)
    protected String getTitle() {
        return title;
    }

    @Column(name = "min_points", nullable = false)
    protected Integer getMinPoints() {
        return minPoints;
    }

    @Column(name = "max_points")
    protected Integer getMaxPoints() {
        return maxPoints;
    }

    @Column(name = "description", length = Integer.MAX_VALUE)
    protected String getDescription() {
        return description;
    }

    @OneToMany(mappedBy = "userLevel")
    protected Set<SysUser> getSysUsers() {
        return sysUsers;
    }

}