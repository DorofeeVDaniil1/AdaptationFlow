package com.project.adaptationflow.entity.person;

import com.project.adaptationflow.entity.gamefication.Achievement;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user_achievement", schema = "public")
public class UserAchievement implements Serializable {
    private static final long serialVersionUID = -3324939935931600699L;
    private UserAchievementId id;

    private SysUser user;

    private Achievement achievement;

    private OffsetDateTime awardedAt;

    @EmbeddedId
    protected UserAchievementId getId() {
        return id;
    }

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    protected SysUser getUser() {
        return user;
    }

    @MapsId("achievementId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "achievement_id", nullable = false)
    protected Achievement getAchievement() {
        return achievement;
    }

    @Column(name = "awarded_at", nullable = false)
    protected OffsetDateTime getAwardedAt() {
        return awardedAt;
    }

}