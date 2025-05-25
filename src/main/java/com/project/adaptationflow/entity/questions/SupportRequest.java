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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private SysUser user;

    @Column(name = "question", nullable = false, length = Integer.MAX_VALUE)
    private String question;

    @Column(name = "answer", length = Integer.MAX_VALUE)
    private String answer;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Column(name = "answered_at")
    private OffsetDateTime answeredAt;

}