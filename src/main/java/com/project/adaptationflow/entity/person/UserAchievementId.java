package com.project.adaptationflow.entity.person;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
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
public class UserAchievementId implements Serializable {
    private static final long serialVersionUID = -2581733769417890041L;
    private UUID userId;

    private UUID achievementId;

    @Column(name = "user_id", nullable = false)
    protected UUID getUserId() {
        return userId;
    }

    @Column(name = "achievement_id", nullable = false)
    protected UUID getAchievementId() {
        return achievementId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserAchievementId entity = (UserAchievementId) o;
        return Objects.equals(this.achievementId, entity.achievementId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(achievementId, userId);
    }

}