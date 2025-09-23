package com.project.adaptationflow.entity.links;

import com.project.adaptationflow.entity.notifications.Notification;
import com.project.adaptationflow.entity.user.SysUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "notification_user_link", indexes = {
        @Index(name = "idx_notification_user_link_notification_id", columnList = "notification_id"),
        @Index(name = "idx_notification_user_link_user_id", columnList = "user_id")
})
public class NotificationUserLink {
    @EmbeddedId
    private NotificationUserLinkId id;

    @MapsId("notificationId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "notification_id", nullable = false)
    private Notification notification;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private SysUser user;

}