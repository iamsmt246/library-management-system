package com.libraryManagement.application.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Getter
@Setter
public class Library {
    private List<Book> books = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> findAvailableBooks() {
        return books.stream()
                .filter(book -> book.getAvailableQuantity() > 0)
                .collect(Collectors.toList());
    }

    public void lendBook(Book book, Member member) {
        book.lendBook();
    }
}
