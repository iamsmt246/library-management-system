package com.libraryManagement.application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true, nullable = false)
    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    // Enum for Role
    enum Role {
        ADMIN, MEMBER
    }

    // Abstract method for role-based actions
    public abstract Role getRole();
}


