package com.project.adaptationflow.dto;

import com.project.adaptationflow.entity.gamefication.UserAchievement;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link com.project.adaptationflow.entity.gamefication.Achievement}
 */
@Value
public class AchievementDto {
    UUID id;
    @NotNull
    String code;
    @NotNull
    String title;
    @NotNull
    String description;
    @NotNull
    Integer points;
    Set<UserAchievementDto> userAchievements;

    /**
     * DTO for {@link UserAchievement}
     */
    @Value
    public static class UserAchievementDto {
        SysUserDto user;

        /**
         * DTO for {@link com.project.adaptationflow.entity.person.SysUser}
         */
        @Value
        public static class SysUserDto {
            @NotBlank
            String login;
        }
    }
}