package com.project.adaptationflow.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
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
@Table(name = "role", indexes = {
        @Index(name = "role_name_key", columnList = "name", unique = true),
        @Index(name = "role_role_name_key", columnList = "name", unique = true)
})
public class Role extends StandardEntityUUID {
    private static final long serialVersionUID = -9063213456994985619L;
    private String name;

    private String description;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    @Column(name = "description", length = Integer.MAX_VALUE)
    public String getDescription() {
        return description;
    }

    @Size(max = 50)
    @Column(name = "deleted_by", length = 50)
    public String getDeletedBy() {
        return deletedBy;
    }

}