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
@Table(name = "notification")
public class Notification extends StandardEntityUUID {
    private static final long serialVersionUID = -5705774046250719310L;
    private String type;

    private LocalDateTime sentAt;

    private Boolean read = false;

    private Set<SysUser> sysUsers = new LinkedHashSet<>();

    @Size(max = 50)
    @NotNull
    @Column(name = "type", nullable = false, length = 50)
    public String getType() {
        return type;
    }

    @Column(name = "sent_at")
    public LocalDateTime getSentAt() {
        return sentAt;
    }

    @NotNull
    @Column(name = "read", nullable = false)
    public Boolean getRead() {
        return read;
    }

    @ManyToMany
    @JoinTable(name = "notification_user_link",
            joinColumns = @JoinColumn(name = "notification_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    public Set<SysUser> getSysUsers() {
        return sysUsers;
    }

}