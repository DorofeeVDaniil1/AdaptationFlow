package com.project.adaptationflow.entity.gamification;

import com.project.adaptationflow.entity.StandardEntityUUID;
import com.project.adaptationflow.entity.user.SysUser;
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
@Table(name = "user_level", uniqueConstraints = {
        @UniqueConstraint(name = "uq_user_level_title", columnNames = {"title"})
})
public class UserLevel extends StandardEntityUUID {
    @Size(max = 100)
    @NotNull
    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @NotNull
    @Column(name = "min_points", nullable = false)
    private Integer minPoints;

    @Column(name = "max_points")
    private Integer maxPoints;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @OneToMany
    @JoinColumn(name = "user_level_id")
    private Set<SysUser> sysUsers = new LinkedHashSet<>();

}