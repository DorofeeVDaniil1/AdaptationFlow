package com.project.adaptationflow.entity.questions;

import com.project.adaptationflow.entity.StandardEntityUUID;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "faq_entry", schema = "public")
public class FaqEntry extends StandardEntityUUID {
    @Serial
    private static final long serialVersionUID = -8227505441964521460L;
    private UUID id;

    private String question;

    private String answer;

    @Column(name = "question", nullable = false, length = Integer.MAX_VALUE)
    protected String getQuestion() {
        return question;
    }

    @Column(name = "answer", nullable = false, length = Integer.MAX_VALUE)
    protected String getAnswer() {
        return answer;
    }

}