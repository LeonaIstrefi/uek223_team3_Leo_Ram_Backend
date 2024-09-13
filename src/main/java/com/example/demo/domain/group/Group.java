package com.example.demo.domain.group;


import com.example.demo.core.generic.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "group")
public class Group extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private int group_id;

    @Column(name = "group_motto")
    @NotNull
    @Max(20)
    private String group_motto;

    @Column(name = "group_name")
    @NotNull
    @Size(min = 3, max = 20)
    private String group_name;

    @Column(name = "group_logo_url")
    @NotNull
    private String group_logo_url;

    @Column(name = "group_member")
    @Max(20)
    private User member;




}

