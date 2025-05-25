package com.project.adaptationflow.entity.gamefication;

import com.project.adaptationflow.entity.StandardEntityUUID;
import com.project.adaptationflow.entity.person.UserAchievement;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "achievement", schema = "public", indexes = {
        @Index(name = "achievement_code_key", columnList = "code", unique = true)
})
public class Achievement extends StandardEntityUUID {
    @Serial
    private static final long serialVersionUID = 7496383558005564595L;

    @Column(name = "code", nullable = false, length = 50)
    private String code;

    @Column(name = "title", nullable = false, length = 150)
    private String title;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "points", nullable = false)
    private Integer points;

    @OneToMany(mappedBy = "achievement")
    private Set<UserAchievement> userAchievements = new LinkedHashSet<>();

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ")";
    }
}