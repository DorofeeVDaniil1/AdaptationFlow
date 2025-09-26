package com.project.adaptationflow.entity.links;

import com.project.adaptationflow.entity.tasks.TrackStage;
import com.project.adaptationflow.entity.user.SysUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "user_track_stage", indexes = {
        @Index(name = "idx_user_track_stage_stage_id", columnList = "stage_id"),
        @Index(name = "idx_user_track_stage_user_id", columnList = "user_id")
})
public class UserTrackStage {
    @EmbeddedId
    private UserTrackStageId id;

    @MapsId("stageId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "stage_id", nullable = false)
    private TrackStage stage;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private SysUser user;

}