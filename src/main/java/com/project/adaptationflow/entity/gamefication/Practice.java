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

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "location", length = Integer.MAX_VALUE)
    private String location;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @OneToMany(mappedBy = "practice")
    private Set<PracticeReport> practiceReports = new LinkedHashSet<>();

}