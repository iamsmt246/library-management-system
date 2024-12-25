package com.libraryManagement.application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userId;

    @Column(unique = true, nullable = false)
    String name;
    String email;
    @Enumerated(EnumType.STRING)
    Role role;

    // Enum for Role
    public enum Role {
        ADMIN, LIBRARIAN, MEMBER
    }
}