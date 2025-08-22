package com.project.adaptationflow.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "sys_users")
public class SysUser extends StandardEntityUUID {
    private static final long serialVersionUID = -7133756337806369941L;
    private String login;

    private String passwordHash;

    private String firstName;

    private String lastName;

    private String patronymic;

    private String email;

    private Boolean isActive = false;

    private LocalDateTime lastLoginDate;

    private UserLevel userLevel;

    private Set<Anketa> anketas = new LinkedHashSet<>();

    private Set<Notification> notifications = new LinkedHashSet<>();

    private Set<PointsTransaction> pointsTransactions = new LinkedHashSet<>();

    private Set<SupportRequest> supportRequests = new LinkedHashSet<>();

    private Set<Role> roles = new LinkedHashSet<>();

    private Set<UserAchievementLink> userAchievementLinks = new LinkedHashSet<>();

    private Set<UserOnboardingTrackLink> userOnboardingTrackLinks = new LinkedHashSet<>();

    private Set<UsersTasksLink> usersTasksLinks = new LinkedHashSet<>();

    @Size(max = 100)
    @NotNull
    @Column(name = "login", nullable = false, length = 100)
    public String getLogin() {
        return login;
    }

    @Size(max = 255)
    @NotNull
    @Column(name = "password_hash", nullable = false)
    public String getPasswordHash() {
        return passwordHash;
    }

    @Size(max = 50)
    @Column(name = "first_name", length = 50)
    public String getFirstName() {
        return firstName;
    }

    @Size(max = 50)
    @Column(name = "last_name", length = 50)
    public String getLastName() {
        return lastName;
    }

    @Size(max = 100)
    @Column(name = "patronymic", length = 100)
    public String getPatronymic() {
        return patronymic;
    }

    @Size(max = 254)
    @Column(name = "email", length = 254)
    public String getEmail() {
        return email;
    }

    @NotNull
    @Column(name = "is_active", nullable = false)
    public Boolean getIsActive() {
        return isActive;
    }

    @Column(name = "last_login_date")
    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_level_id")
    public UserLevel getUserLevel() {
        return userLevel;
    }

    @OneToMany(mappedBy = "user")
    public Set<Anketa> getAnketas() {
        return anketas;
    }

    @ManyToMany(mappedBy = "user")
    public Set<Notification> getNotifications() {
        return notifications;
    }

    @OneToMany(mappedBy = "user")
    public Set<PointsTransaction> getPointsTransactions() {
        return pointsTransactions;
    }

    @OneToMany(mappedBy = "user")
    public Set<SupportRequest> getSupportRequests() {
        return supportRequests;
    }

    @ManyToMany(mappedBy = "user")
    public Set<Role> getRoles() {
        return roles;
    }

    @OneToMany(mappedBy = "sysUser")
    public Set<UserAchievementLink> getUserAchievementLinks() {
        return userAchievementLinks;
    }

    @OneToMany(mappedBy = "user")
    public Set<UserOnboardingTrackLink> getUserOnboardingTrackLinks() {
        return userOnboardingTrackLinks;
    }

    @OneToMany(mappedBy = "sysUser")
    public Set<UsersTasksLink> getUsersTasksLinks() {
        return usersTasksLinks;
    }

}