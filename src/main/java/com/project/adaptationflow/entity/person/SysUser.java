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
import lombok.Builder;



@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Getter
@Setter
@Builder
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

    @Setter
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public OffsetDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(OffsetDateTime lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public UserLevel getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(UserLevel userLevel) {
        this.userLevel = userLevel;
    }

    public Set<Anketa> getAnketas() {
        return anketas;
    }

    public void setAnketas(Set<Anketa> anketas) {
        this.anketas = anketas;
    }

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }

    public Set<PointsTransaction> getPointsTransactions() {
        return pointsTransactions;
    }

    public void setPointsTransactions(Set<PointsTransaction> pointsTransactions) {
        this.pointsTransactions = pointsTransactions;
    }

    public Set<PracticeReport> getPracticeReports() {
        return practiceReports;
    }

    public void setPracticeReports(Set<PracticeReport> practiceReports) {
        this.practiceReports = practiceReports;
    }

    public Set<SupportRequest> getSupportRequests() {
        return supportRequests;
    }

    public void setSupportRequests(Set<SupportRequest> supportRequests) {
        this.supportRequests = supportRequests;
    }

    public Set<SysUserRole> getSysUserRoles() {
        return sysUserRoles;
    }

    public void setSysUserRoles(Set<SysUserRole> sysUserRoles) {
        this.sysUserRoles = sysUserRoles;
    }

    public Set<UserAchievement> getUserAchievements() {
        return userAchievements;
    }

    public void setUserAchievements(Set<UserAchievement> userAchievements) {
        this.userAchievements = userAchievements;
    }

    public Set<UserOnboardingTrack> getUserOnboardingTracks() {
        return userOnboardingTracks;
    }

    public void setUserOnboardingTracks(Set<UserOnboardingTrack> userOnboardingTracks) {
        this.userOnboardingTracks = userOnboardingTracks;
    }

    public Set<UsersTasksLink> getUsersTasksLinks() {
        return usersTasksLinks;
    }

    public void setUsersTasksLinks(Set<UsersTasksLink> usersTasksLinks) {
        this.usersTasksLinks = usersTasksLinks;
    }

    public static class Builder {
        private final SysUser instance = new SysUser();

        public Builder login(String login) {
            instance.login = login;
            return this;
        }

        public Builder passwordHash(String passwordHash) {
            instance.passwordHash = passwordHash;
            return this;
        }

        public Builder firstName(String firstName) {
            instance.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            instance.lastName = lastName;
            return this;
        }

        public Builder patronymic(String patronymic) {
            instance.patronymic = patronymic;
            return this;
        }

        public Builder email(String email) {
            instance.email = email;
            return this;
        }

        public Builder isActive(Boolean isActive) {
            instance.isActive = isActive;
            return this;
        }

        public Builder lastLoginDate(OffsetDateTime lastLoginDate) {
            instance.lastLoginDate = lastLoginDate;
            return this;
        }

        public SysUser build() {
            // Тут можно добавить проверки обязательных полей
            if (instance.login == null || instance.passwordHash == null) {
                throw new IllegalStateException("login and passwordHash must be set");
            }
            return instance;
        }
    }

    // Удобный статический метод для начала построения
    public static Builder builder() {
        return new Builder();
    }


}