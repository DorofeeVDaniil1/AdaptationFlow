package com.project.adaptationflow.entity;

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
@Table(name = "achievement", uniqueConstraints = {
        @UniqueConstraint(name = "uq_achievement_code", columnNames = {"code"})
})
public class Achievement extends StandardEntityUUID {
    @Size(max = 50)
    @NotNull
    @Column(name = "code", nullable = false, length = 50)
    private String code;

    @Size(max = 150)
    @NotNull
    @Column(name = "title", nullable = false, length = 150)
    private String title;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @NotNull
    @Column(name = "points", nullable = false)
    private Integer points;

    @ManyToMany
    @JoinTable(name = "user_achievement_link",
            joinColumns = @JoinColumn(name = "achievement_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<SysUser> sysUsers = new LinkedHashSet<>();

}