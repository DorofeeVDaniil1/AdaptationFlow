package com.project.adaptationflow.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user_level", indexes = {
        @Index(name = "user_level_title_key", columnList = "title", unique = true),
        @Index(name = "user_level_user_level_title_key", columnList = "title", unique = true)
})
public class UserLevel extends StandardEntityUUID {
    private static final long serialVersionUID = 3755788269480838544L;
    private String title;

    private Integer minPoints;

    private Integer maxPoints;

    private String description;

    private Set<SysUser> sysUsers = new LinkedHashSet<>();

    @Size(max = 100)
    @NotNull
    @Column(name = "title", nullable = false, length = 100)
    public String getTitle() {
        return title;
    }

    @NotNull
    @Column(name = "min_points", nullable = false)
    public Integer getMinPoints() {
        return minPoints;
    }

    @Column(name = "max_points")
    public Integer getMaxPoints() {
        return maxPoints;
    }

    @Column(name = "description", length = Integer.MAX_VALUE)
    public String getDescription() {
        return description;
    }

    @Size(max = 50)
    @Column(name = "deleted_by", length = 50)
    public String getDeletedBy() {
        return deletedBy;
    }

    @OneToMany(mappedBy = "userLevel")
    public Set<SysUser> getSysUsers() {
        return sysUsers;
    }

}