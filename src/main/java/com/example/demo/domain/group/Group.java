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
    private String group_motto;

    @Column(name = "group_name")
    @NotNull
    @Size(min = 3, max = 20)
    private String group_name;

    @Column(name = "group_logo_url")
    @NotNull
    private String group_logo_url;

    public Group(UUID id, List<User> members, String group_motto, String group_name, String group_logo_url) {
        super(id);
        this.members = members;
        this.group_motto = group_motto;
        this.group_name = group_name;
        this.group_logo_url = group_logo_url;
    }

    public Group() {

    }
}
