package com.project.adaptationflow.entity.user;

import com.project.adaptationflow.entity.StandardEntityUUID;
import com.project.adaptationflow.entity.gamification.UserLevel;
import com.project.adaptationflow.entity.gamification.PointsTransaction;
import com.project.adaptationflow.entity.links.NotificationUserLink;
import com.project.adaptationflow.entity.links.UserAchievementLink;
import com.project.adaptationflow.entity.links.UserOnboardingTrackLink;
import com.project.adaptationflow.entity.links.UserTrackStage;
import com.project.adaptationflow.entity.links.UsersTasksLink;
import com.project.adaptationflow.entity.notifications.SupportRequest;
import com.project.adaptationflow.entity.surveys.Anketa;
import com.project.adaptationflow.entity.surveys.UserAnswer;
import com.project.adaptationflow.entity.tasks.Progress;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "sys_users", indexes = {
        @Index(name = "idx_sys_users_user_level_id", columnList = "user_level_id")
})
public class SysUser extends StandardEntityUUID {
    @Size(max = 100)
    @NotNull
    @Column(name = "login", nullable = false, length = 100)
    private String login;

    @Size(max = 255)
    @NotNull
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Size(max = 50)
    @Column(name = "first_name", length = 50)
    private String firstName;

    @Size(max = 50)
    @Column(name = "last_name", length = 50)
    private String lastName;

    @Size(max = 100)
    @Column(name = "patronymic", length = 100)
    private String patronymic;

    @Size(max = 254)
    @Column(name = "email", length = 254)
    private String email;

    @NotNull
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = false;

    @Column(name = "last_login_date")
    private Instant lastLoginDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "user_level_id")
    private UserLevel userLevel;

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<Anketa> anketas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<NotificationUserLink> notificationLinks = new LinkedHashSet<>();

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<PointsTransaction> pointsTransactions = new LinkedHashSet<>();

    @OneToMany
    @JoinColumn(name = "assignment_user")
    private Set<Progress> progresses = new LinkedHashSet<>();

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<SupportRequest> supportRequests = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<SysUserRoleLink> roleLinks = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<UserAchievementLink> achievementLinks = new LinkedHashSet<>();

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<UserAnswer> userAnswers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<UserOnboardingTrackLink> onboardingTrackLinks = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<UserTrackStage> trackStageLinks = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<UsersTasksLink> taskLinks = new LinkedHashSet<>();

}