package com.project.adaptationflow.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Embeddable
public class UserAchievementLinkId implements Serializable {
    private static final long serialVersionUID = 9027461330665601241L;
    private UUID userId;

    private UUID achievementId;

    @NotNull
    @Column(name = "user_id", nullable = false)
    public UUID getUserId() {
        return userId;
    }

    @NotNull
    @Column(name = "achievement_id", nullable = false)
    public UUID getAchievementId() {
        return achievementId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserAchievementLinkId entity = (UserAchievementLinkId) o;
        return Objects.equals(this.achievementId, entity.achievementId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(achievementId, userId);
    }

}