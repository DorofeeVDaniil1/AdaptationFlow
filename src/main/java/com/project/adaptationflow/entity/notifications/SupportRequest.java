package com.project.adaptationflow.entity.notifications;

import com.project.adaptationflow.entity.StandardEntityUUID;
import com.project.adaptationflow.entity.user.SysUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "support_request", indexes = {
        @Index(name = "idx_support_request_user_id", columnList = "user_id")
})
public class SupportRequest extends StandardEntityUUID {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private SysUser user;

    @NotNull
    @Column(name = "question", nullable = false, length = Integer.MAX_VALUE)
    private String question;

    @Column(name = "answer", length = Integer.MAX_VALUE)
    private String answer;

    @Size(max = 20)
    @NotNull
    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Column(name = "answered_at")
    private Instant answeredAt;

}