package com.libraryManagement.application.entity;

import jakarta.persistence.Entity;

@Entity
public class Admin extends User {
    @Override
    public Role getRole() {
        return Role.ADMIN;
    }

    public void addBook(Book book, Library library) {
        library.addBook(book);
    }

    public void removeBook(Book book, Library library) {
        library.getBooks().remove(book);
    }
}