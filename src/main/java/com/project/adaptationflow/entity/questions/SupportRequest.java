package com.project.adaptationflow.entity.questions;

import com.project.adaptationflow.entity.StandardEntityUUID;
import com.project.adaptationflow.entity.person.SysUser;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serial;
import java.time.OffsetDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "support_request", schema = "public")
public class SupportRequest extends StandardEntityUUID {
    @Serial
    private static final long serialVersionUID = -3469565809643504352L;
    private UUID id;

    private SysUser user;

    private String question;

    private String answer;

    private String status;

    private OffsetDateTime answeredAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    protected SysUser getUser() {
        return user;
    }

    @Column(name = "question", nullable = false, length = Integer.MAX_VALUE)
    protected String getQuestion() {
        return question;
    }

    @Column(name = "answer", length = Integer.MAX_VALUE)
    protected String getAnswer() {
        return answer;
    }

    @Column(name = "status", nullable = false, length = 20)
    protected String getStatus() {
        return status;
    }

    @Column(name = "answered_at")
    protected OffsetDateTime getAnsweredAt() {
        return answeredAt;
    }

}