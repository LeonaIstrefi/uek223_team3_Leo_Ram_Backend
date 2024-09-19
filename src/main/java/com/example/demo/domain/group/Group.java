package com.example.demo.domain.group;

import com.example.demo.core.generic.AbstractEntity;
import com.example.demo.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "groups")
public class Group extends AbstractEntity {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "group_users",
            joinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )

    @Max(10)
    private List<User> members;

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
        this.members = members;
        this.groupMotto = groupMotto;
        this.groupName = groupName;
        this.groupLogoUrl = groupLogoUrl;
    }

    public Group() {
    }
}
