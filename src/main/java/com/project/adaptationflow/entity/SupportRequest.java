package com.project.adaptationflow.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "support_request")
public class SupportRequest extends StandardEntityUUID {
    private static final long serialVersionUID = -7907197298521401027L;
    private SysUser user;

    private String question;

    private String answer;

    private String status;

    private LocalDateTime answeredAt;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    public SysUser getUser() {
        return user;
    }

    @NotNull
    @Column(name = "question", nullable = false, length = Integer.MAX_VALUE)
    public String getQuestion() {
        return question;
    }

    @Column(name = "answer", length = Integer.MAX_VALUE)
    public String getAnswer() {
        return answer;
    }

    @Size(max = 20)
    @NotNull
    @Column(name = "status", nullable = false, length = 20)
    public String getStatus() {
        return status;
    }

    @Column(name = "answered_at")
    public LocalDateTime getAnsweredAt() {
        return answeredAt;
    }

}