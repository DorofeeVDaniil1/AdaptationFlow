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
    private UUID id;

    private String login;

    private String passwordHash;

    private String firstName;

    private String lastName;

    private String patronymic;

    private String email;

    private Boolean isActive = false;

    private OffsetDateTime lastLoginDate;

    private UserLevel userLevel;

    private Set<Anketa> anketas = new LinkedHashSet<>();

    private Set<Notification> notifications = new LinkedHashSet<>();

    private Set<PointsTransaction> pointsTransactions = new LinkedHashSet<>();

    private Set<Practice> practices = new LinkedHashSet<>();

    private Set<PracticeReport> practiceReports = new LinkedHashSet<>();

    private Set<SupportRequest> supportRequests = new LinkedHashSet<>();

    private Set<SysUserRole> sysUserRoles = new LinkedHashSet<>();

    private Set<UserAchievement> userAchievements = new LinkedHashSet<>();

    private Set<UserOnboardingTrack> userOnboardingTracks = new LinkedHashSet<>();

    private Set<UsersTasksLink> usersTasksLinks = new LinkedHashSet<>();

    @Column(name = "login", nullable = false, length = 100)
    protected String getLogin() {
        return login;
    }

    @Column(name = "password_hash", nullable = false)
    protected String getPasswordHash() {
        return passwordHash;
    }

    @Column(name = "first_name", length = 50)
    protected String getFirstName() {
        return firstName;
    }

    @Column(name = "last_name", length = 50)
    protected String getLastName() {
        return lastName;
    }

    @Column(name = "patronymic", length = 100)
    protected String getPatronymic() {
        return patronymic;
    }

    @Column(name = "email", length = 254)
    protected String getEmail() {
        return email;
    }

    @Column(name = "is_active", nullable = false)
    protected Boolean getIsActive() {
        return isActive;
    }

    @Column(name = "last_login_date")
    protected OffsetDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_level_id")
    protected UserLevel getUserLevel() {
        return userLevel;
    }

    @OneToMany(mappedBy = "user")
    protected Set<Anketa> getAnketas() {
        return anketas;
    }

    @OneToMany(mappedBy = "user")
    protected Set<Notification> getNotifications() {
        return notifications;
    }

    @OneToMany(mappedBy = "user")
    protected Set<PointsTransaction> getPointsTransactions() {
        return pointsTransactions;
    }

    @OneToMany(mappedBy = "user")
    protected Set<PracticeReport> getPracticeReports() {
        return practiceReports;
    }

    @OneToMany(mappedBy = "user")
    protected Set<SupportRequest> getSupportRequests() {
        return supportRequests;
    }

    @OneToMany(mappedBy = "user")
    protected Set<SysUserRole> getSysUserRoles() {
        return sysUserRoles;
    }

    @OneToMany(mappedBy = "user")
    protected Set<UserAchievement> getUserAchievements() {
        return userAchievements;
    }

    @OneToMany(mappedBy = "user")
    protected Set<UserOnboardingTrack> getUserOnboardingTracks() {
        return userOnboardingTracks;
    }

    @OneToMany(mappedBy = "user")
    protected Set<UsersTasksLink> getUsersTasksLinks() {
        return usersTasksLinks;
    }

}