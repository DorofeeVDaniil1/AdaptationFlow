package com.project.adaptationflow.entity.notification;

import com.project.adaptationflow.entity.StandardEntityUUID;
import com.project.adaptationflow.entity.person.SysUser;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serial;
import java.time.OffsetDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "notification", schema = "public")
public class Notification extends StandardEntityUUID {
    @Serial
    private static final long serialVersionUID = 4999855229722736308L;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private SysUser user;

    @Column(name = "type", nullable = false, length = 50)
    private String type;


    @Column(name = "sent_at")
    private OffsetDateTime sentAt;

    @Column(name = "read", nullable = false)
    private Boolean read = false;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ")";
    }
}