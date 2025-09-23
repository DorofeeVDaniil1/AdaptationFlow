package com.project.adaptationflow.entity.surveys;

import com.project.adaptationflow.entity.StandardEntityUUID;
import com.project.adaptationflow.entity.user.SysUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "user_answer", indexes = {
        @Index(name = "idx_user_answer_user_id", columnList = "user_id"),
        @Index(name = "idx_user_answer_question_id", columnList = "question_id"),
        @Index(name = "idx_user_answer_answer_option_id", columnList = "answer_option_id")
})
public class UserAnswer {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private SysUser user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "answer_option_id")
    private AnswerOption answerOption;

    @Column(name = "answer_text", length = Integer.MAX_VALUE)
    private String answerText;

    @Column(name = "answered_at")
    private Instant answeredAt;

}