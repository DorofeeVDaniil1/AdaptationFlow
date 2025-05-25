package com.project.adaptationflow.entity.reports;

import com.project.adaptationflow.entity.StandardEntityUUID;
import com.project.adaptationflow.entity.person.SysUser;
import com.project.adaptationflow.entity.gamefication.Practice;
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
@Table(name = "practice_report", schema = "public")
public class PracticeReport extends StandardEntityUUID {
    @Serial
    private static final long serialVersionUID = 455336129426869701L;
    private UUID id;

    private Practice practice;

    private SysUser user;

    private String report;

    private OffsetDateTime submittedAt;

    private UUID approvedBy;

    private OffsetDateTime approvedAt;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "practice_id", nullable = false)
    protected Practice getPractice() {
        return practice;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    protected SysUser getUser() {
        return user;
    }

    @Column(name = "report", length = Integer.MAX_VALUE)
    protected String getReport() {
        return report;
    }

    @Column(name = "submitted_at", nullable = false)
    protected OffsetDateTime getSubmittedAt() {
        return submittedAt;
    }

    @Column(name = "approved_by")
    protected UUID getApprovedBy() {
        return approvedBy;
    }

    @Column(name = "approved_at")
    protected OffsetDateTime getApprovedAt() {
        return approvedAt;
    }

}