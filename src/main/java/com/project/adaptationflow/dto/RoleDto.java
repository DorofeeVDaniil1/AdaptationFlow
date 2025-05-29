package com.project.adaptationflow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Value;

/**
 * DTO for {@link com.project.adaptationflow.entity.person.Role}
 */
@Data
public class RoleDto {
    @NotNull
    @NotEmpty
    @NotBlank
    String name;
    @NotNull
    @NotEmpty
    @NotBlank
    String description;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}