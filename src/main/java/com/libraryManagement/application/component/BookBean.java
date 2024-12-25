package com.libraryManagement.application.component;

import com.libraryManagement.application.entity.Book;
import com.libraryManagement.application.service.BookService;
import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@Component("bookBean")
@ManagedBean
@SessionScope
public class BookBean {

    @Autowired
    private BookService bookService;

    private List<Book> books;
    private Book newBook = new Book();
    private Book selectedBook;
    private Long searchBookId;
    private Book searchResult;

    @PostConstruct
    public void init() {
        books = bookService.getAllBooks();
        if (books == null || books.isEmpty()) {
            System.out.println("No books available in the database");
        }
    }

    public void addBook() {
        bookService.addBook(newBook);
        books = bookService.getAllBooks(); // Refresh the list
    }

    public void searchBook() {
        searchResult = bookService.getBookById(searchBookId);
    }

    public void updateBook() {
        bookService.updateBook(selectedBook, selectedBook.getBookId());
        books = bookService.getAllBooks(); // Refresh the list
    }

    // Getters and Setters
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Book getNewBook() {
        return newBook;
    }

    public void setNewBook(Book newBook) {
        this.newBook = newBook;
    }

    public Book getSelectedBook() {
        return selectedBook;
    }

    public void setSelectedBook(Book selectedBook) {
        this.selectedBook = selectedBook;
    }

    public Long getSearchBookId() {
        return searchBookId;
    }

    public void setSearchBookId(Long searchBookId) {
        this.searchBookId = searchBookId;
    }

    public Book getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(Book searchResult) {
        this.searchResult = searchResult;
    }
}

