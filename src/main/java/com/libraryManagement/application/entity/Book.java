package com.libraryManagement.application.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long bookId;

    String title;
    String author;
    String isbn;
    String genre;
    Integer totalQuantity;
    Integer availableQuantity;

}