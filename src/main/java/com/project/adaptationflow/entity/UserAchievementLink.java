package com.project.adaptationflow.entity;

import com.project.adaptationflow.entity.user.SysUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "user_achievement_link", indexes = {
        @Index(name = "idx_user_achievement_link_user_id", columnList = "user_id"),
        @Index(name = "idx_user_achievement_link_achievement_id", columnList = "achievement_id")
})
public class UserAchievementLink {
    @EmbeddedId
    private UserAchievementLinkId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private SysUser user;

    @MapsId("achievementId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "achievement_id", nullable = false)
    private Achievement achievement;

}