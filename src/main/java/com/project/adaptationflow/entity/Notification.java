package com.project.adaptationflow.entity;

import com.project.adaptationflow.entity.user.SysUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "notification", indexes = {
        @Index(name = "idx_notification_type", columnList = "type")
})
public class Notification extends StandardEntityUUID {
    @Size(max = 50)
    @NotNull
    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @Column(name = "sent_at")
    private Instant sentAt;

    @NotNull
    @Column(name = "read", nullable = false)
    private Boolean read = false;

    @ManyToMany
    @JoinTable(name = "notification_user_link",
            joinColumns = @JoinColumn(name = "notification_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<SysUser> sysUsers = new LinkedHashSet<>();

}