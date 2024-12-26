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
    private Long bookId;

    private String title;
    private String author;
    private String isbn;
    private String genre;
    private Integer totalQuantity;
    private Integer availableQuantity;

    public void lendBook() {
        if (availableQuantity > 0) {
            availableQuantity--;
        } else {
            throw new RuntimeException("No copies available to lend!");
        }
    }

    public void returnBook() {
        if (availableQuantity < totalQuantity) {
            availableQuantity++;
        } else {
            throw new RuntimeException("All copies are already in the library!");
        }
    }
}