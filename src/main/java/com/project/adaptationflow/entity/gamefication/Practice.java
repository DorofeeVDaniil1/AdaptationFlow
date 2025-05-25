package com.project.adaptationflow.entity.gamefication;

import com.project.adaptationflow.entity.reports.PracticeReport;
import com.project.adaptationflow.entity.StandardEntityUUID;
import com.project.adaptationflow.entity.person.SysUser;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.time.LocalDate;
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
@Table(name = "practice", schema = "public")
public class Practice extends StandardEntityUUID {
    @Serial
    private static final long serialVersionUID = 9106888302701121999L;
    private UUID id;

    private String name;

    private String location;

    private LocalDate startDate;

    private LocalDate endDate;

    private Set<PracticeReport> practiceReports = new LinkedHashSet<>();

    @Column(name = "name", nullable = false, length = 150)
    protected String getName() {
        return name;
    }

    @Column(name = "location", length = Integer.MAX_VALUE)
    protected String getLocation() {
        return location;
    }

    @Column(name = "start_date")
    protected LocalDate getStartDate() {
        return startDate;
    }

    @Column(name = "end_date")
    protected LocalDate getEndDate() {
        return endDate;
    }

    @OneToMany(mappedBy = "practice")
    protected Set<PracticeReport> getPracticeReports() {
        return practiceReports;
    }

}