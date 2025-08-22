package com.project.adaptationflow.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user_achievement_link")
public class UserAchievementLink implements Serializable {
    private static final long serialVersionUID = -7268812551987606204L;
    private UserAchievementLinkId id;

    private SysUser user;

    private Achievement achievement;

    private LocalDateTime awardedAt;

    @EmbeddedId
    public UserAchievementLinkId getId() {
        return id;
    }

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    public SysUser getUser() {
        return user;
    }

    @MapsId("achievementId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "achievement_id", nullable = false)
    public Achievement getAchievement() {
        return achievement;
    }

    @NotNull
    @Column(name = "awarded_at", nullable = false)
    public LocalDateTime getAwardedAt() {
        return awardedAt;
    }

}