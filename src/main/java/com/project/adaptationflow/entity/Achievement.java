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
@Table(name = "achievement", indexes = {
        @Index(name = "achievement_achievement_code_key", columnList = "code", unique = true),
        @Index(name = "achievement_code_key", columnList = "code", unique = true)
})
public class Achievement extends StandardEntityUUID {
    private static final long serialVersionUID = -8627604966631849824L;

    private String code;

    private String title;

    private String description;

    private Integer points;

    private Set<UserAchievementLink> userAchievementLinks = new LinkedHashSet<>();

    @Size(max = 50)
    @Column(name = "deleted_by", length = 50)
    public String getDeletedBy() {
        return deletedBy;
    }

    @Size(max = 50)
    @NotNull
    @Column(name = "code", nullable = false, length = 50)
    public String getCode() {
        return code;
    }

    @Size(max = 150)
    @NotNull
    @Column(name = "title", nullable = false, length = 150)
    public String getTitle() {
        return title;
    }

    @Column(name = "description", length = Integer.MAX_VALUE)
    public String getDescription() {
        return description;
    }

    @NotNull
    @Column(name = "points", nullable = false)
    public Integer getPoints() {
        return points;
    }

    @OneToMany(mappedBy = "achievement")
    public Set<UserAchievementLink> getUserAchievementLinks() {
        return userAchievementLinks;
    }

}