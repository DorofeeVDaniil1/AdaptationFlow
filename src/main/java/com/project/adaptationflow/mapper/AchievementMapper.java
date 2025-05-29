package com.project.adaptationflow.mapper;

import com.project.adaptationflow.dto.AchievementDto;
import com.project.adaptationflow.entity.gamefication.Achievement;
import com.project.adaptationflow.entity.person.SysUser;
import com.project.adaptationflow.entity.person.UserAchievement;
import com.project.adaptationflow.entity.person.UserAchievementId;
import com.project.adaptationflow.repo.SysUserRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.OffsetDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AchievementMapper {

    @Autowired
    protected SysUserRepository sysUserRepository;

    @Mapping(target = "userAchievements", ignore = true)
    public abstract Achievement toEntity(AchievementDto dto);

    @AfterMapping
    void linkUserAchievements(@MappingTarget Achievement achievement, AchievementDto dto) {
        if (dto.getUserAchievements() == null || dto.getUserAchievements().isEmpty()) {
            return;
        }

        Set<UserAchievement> userAchievements = new LinkedHashSet<>();
        for (AchievementDto.UserAchievementDto uaDto : dto.getUserAchievements()) {
            String login = uaDto.getUser().getLogin();
            SysUser user = sysUserRepository.findByLogin(login)
                    .orElseThrow(() -> new IllegalArgumentException("Пользователь с логином " + login + " не найден"));

            // Формируем составной ключ
            UserAchievementId uaId = new UserAchievementId(user.getId(), achievement.getId());

            UserAchievement userAchievement = new UserAchievement();
            userAchievement.setId(uaId);
            userAchievement.setUser(user);
            userAchievement.setAchievement(achievement);
            userAchievement.setAwardedAt(OffsetDateTime.now());

            userAchievements.add(userAchievement);
        }

        achievement.setUserAchievements(userAchievements);
    }

    public abstract AchievementDto toAchievementDto(Achievement achievement);

    public abstract Achievement updateWithNull(AchievementDto dto, @MappingTarget Achievement achievement);
}
