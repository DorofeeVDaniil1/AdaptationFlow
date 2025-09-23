package com.project.adaptationflow.entity;

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
public class NotificationUserLinkId implements Serializable {
    private static final long serialVersionUID = 6895816219259468112L;
    @NotNull
    @Column(name = "notification_id", nullable = false)
    private UUID notificationId;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        NotificationUserLinkId entity = (NotificationUserLinkId) o;
        return Objects.equals(this.notificationId, entity.notificationId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notificationId, userId);
    }

}