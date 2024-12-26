package com.libraryManagement.application.entity;

import jakarta.persistence.Entity;

// Member Class
@Entity
public class Member extends User {
    @Override
    public Role getRole() {
        return Role.MEMBER;
    }

    public void borrowBook(Book book) {
        book.lendBook();
    }

    public void returnBook(Book book) {
        book.returnBook();
    }
}