package com.project.adaptationflow.entity.notification;

import com.project.adaptationflow.entity.StandardEntityUUID;
import com.project.adaptationflow.entity.person.SysUser;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.type.SqlTypes;

import java.io.Serial;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "notification", schema = "public")
public class Notification extends StandardEntityUUID {
    @Serial
    private static final long serialVersionUID = 4999855229722736308L;
    private UUID id;

    private SysUser user;

    private String type;

    private Map<String, Object> payload;

    private OffsetDateTime sentAt;

    private Boolean read = false;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    protected SysUser getUser() {
        return user;
    }

    @Column(name = "type", nullable = false, length = 50)
    protected String getType() {
        return type;
    }

    @Column(name = "payload", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    protected Map<String, Object> getPayload() {
        return payload;
    }

    @Column(name = "sent_at")
    protected OffsetDateTime getSentAt() {
        return sentAt;
    }

    @Column(name = "read", nullable = false)
    protected Boolean getRead() {
        return read;
    }

}