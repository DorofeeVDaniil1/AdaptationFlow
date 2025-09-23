package com.project.adaptationflow.entity.user;

import com.project.adaptationflow.entity.*;
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

    @ManyToMany(mappedBy = "sysUsers")
    private Set<Notification> notifications = new LinkedHashSet<>();

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
    private Set<SysUserRoleLink> roles = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "sysUsers")
    private Set<Achievement> achievements = new LinkedHashSet<>();

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<UserAnswer> userAnswers = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "sysUsers")
    private Set<OnboardingTrack> onboardingTracks = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "sysUsers")
    private Set<TrackStage> trackStages = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "sysUsers")
    private Set<Task> tasks = new LinkedHashSet<>();

}