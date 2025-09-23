package com.project.adaptationflow.entity.links;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Embeddable
public class UserAchievementLinkId implements Serializable {
    private static final long serialVersionUID = 1092624886342258426L;
    @NotNull
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @NotNull
    @Column(name = "achievement_id", nullable = false)
    private UUID achievementId;

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