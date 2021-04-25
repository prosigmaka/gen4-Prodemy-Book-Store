package com.example.onlinebookstore.model.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = User.TABLE_NAME)
@Data
public class User extends CommonEntity {
    public static final String TABLE_NAME = "t_user";

    @Id
    @Column(name = "id")
    @GenericGenerator(
            name = "user-generator",
            strategy = "com.example.onlinebookstore.configuration.MyGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "prefix", value = "UID")
            }
    )

    @GeneratedValue(generator = "user-generator")
    private String id;

    @Column(name = "username", nullable = false, unique = true)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "token_expired")
    private Boolean tokenExpired;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;
}
