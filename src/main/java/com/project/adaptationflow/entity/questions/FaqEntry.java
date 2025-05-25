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

    @Column(name = "question", nullable = false, length = Integer.MAX_VALUE)
    private String question;

    @Column(name = "answer", nullable = false, length = Integer.MAX_VALUE)
    private String answer;

}