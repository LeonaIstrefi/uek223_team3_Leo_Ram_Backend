package com.example.demo.domain.group;

import com.example.demo.core.generic.AbstractEntity;
import com.example.demo.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Log4j2
@Table(name = "groups")
public class Group extends AbstractEntity {

    private static final Logger logger = LoggerFactory.getLogger(Group.class);

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "group_users",
            joinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private List<User> members = new ArrayList<>();

    @Column(name = "group_motto")
    @NotNull
    @Size(max = 100)
    private String groupMotto;

    @Column(name = "group_name")
    @NotNull
    @Size(min = 3, max = 20)
    private String groupName;

    @Column(name = "group_logo_url")
    @NotNull
    private String groupLogoUrl;

    public Group(UUID id, List<User> members, String groupMotto, String groupName, String groupLogoUrl) {
        super(id);
        this.members = (members != null) ? members : new ArrayList<>();
        this.groupMotto = groupMotto;
        this.groupName = groupName;
        this.groupLogoUrl = groupLogoUrl;
    }

    public Group() {
        this.members = new ArrayList<>();
    }

    @PrePersist
    public void prePersist() {
        log.info("Preparing to persist Group: {}", this);
    }

    @PostPersist
    public void postPersist() {
        log.info("Group persisted: {}", this);
    }

    @PreRemove
    public void preRemove() {
        log.info("Preparing to remove Group: {}", this);
    }

    @PostRemove
    public void postRemove() {
        log.info("Group removed: {}", this);
    }

    @PreUpdate
    public void preUpdate() {
        log.info("Preparing to update Group: {}", this);
    }

    @PostUpdate
    public void postUpdate() {
        log.info("Group updated: {}", this);
    }

    @PostLoad
    public void postLoad() {
        log.info("Group loaded: {}", this);
    }
}
