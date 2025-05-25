package com.project.adaptationflow.entity.reports;

import com.project.adaptationflow.entity.StandardEntityUUID;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "question_option", schema = "public")
public class QuestionOption extends StandardEntityUUID {
    @Serial
    private static final long serialVersionUID = -3224581886804009883L;

    @Column(name = "question_text", nullable = false, length = Integer.MAX_VALUE)
    private String questionText;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = false;

    @ManyToMany(mappedBy = "questionOptions")
    private Set<Anketa> anketas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "question")
    private Set<AnswerOption> answerOptions = new LinkedHashSet<>();

}