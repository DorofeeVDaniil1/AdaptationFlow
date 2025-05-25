package com.project.adaptationflow.entity.person;

import com.project.adaptationflow.entity.*;
import com.project.adaptationflow.entity.gamefication.PointsTransaction;
import com.project.adaptationflow.entity.gamefication.Practice;
import com.project.adaptationflow.entity.notification.Notification;
import com.project.adaptationflow.entity.questions.SupportRequest;
import com.project.adaptationflow.entity.reports.Anketa;
import com.project.adaptationflow.entity.reports.PracticeReport;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.time.OffsetDateTime;
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
@Table(name = "sys_users", schema = "public")
public class SysUser extends StandardEntityUUID {
    @Serial
    private static final long serialVersionUID = 3705836661104986912L;

    @Column(name = "login", nullable = false, length = 100)
    private String login;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "patronymic", length = 100)
    private String patronymic;

    @Column(name = "email", length = 254)
    private String email;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = false;

    @Column(name = "last_login_date")
    private OffsetDateTime lastLoginDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_level_id")
    private UserLevel userLevel;

    @OneToMany(mappedBy = "user")
    private Set<Anketa> anketas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Notification> notifications = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<PointsTransaction> pointsTransactions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<PracticeReport> practiceReports = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<SupportRequest> supportRequests = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<SysUserRole> sysUserRoles = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<UserAchievement> userAchievements = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<UserOnboardingTrack> userOnboardingTracks = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<UsersTasksLink> usersTasksLinks = new LinkedHashSet<>();

}