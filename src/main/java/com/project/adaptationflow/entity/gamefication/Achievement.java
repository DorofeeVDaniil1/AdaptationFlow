package com.project.adaptationflow.entity.gamefication;

import com.project.adaptationflow.entity.StandardEntityUUID;
import com.project.adaptationflow.entity.person.UserAchievement;
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
@Table(name = "achievement", schema = "public", indexes = {
        @Index(name = "achievement_code_key", columnList = "code", unique = true)
})
public class Achievement extends StandardEntityUUID {
    @Serial
    private static final long serialVersionUID = 7496383558005564595L;
    private UUID id;

    private String code;

    private String title;

    private String description;

    private Integer points;

    private Set<UserAchievement> userAchievements = new LinkedHashSet<>();

    @Column(name = "code", nullable = false, length = 50)
    protected String getCode() {
        return code;
    }

    @Column(name = "title", nullable = false, length = 150)
    protected String getTitle() {
        return title;
    }

    @Column(name = "description", length = Integer.MAX_VALUE)
    protected String getDescription() {
        return description;
    }

    @Column(name = "points", nullable = false)
    protected Integer getPoints() {
        return points;
    }

    @OneToMany(mappedBy = "achievement")
    protected Set<UserAchievement> getUserAchievements() {
        return userAchievements;
    }

}