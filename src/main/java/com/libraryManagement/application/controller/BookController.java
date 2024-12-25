package com.libraryManagement.application.controller;

import com.libraryManagement.application.entity.Book;
import com.libraryManagement.application.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/books")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/books/{bookId}")
    public Book getBookById(@PathVariable long bookId){
        return bookService.getBookById(bookId);
    }

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Book> deleteBookById(@PathVariable long bookId){
        return bookService.deleteBookById(bookId);
    }

    @PutMapping("/books/{bookId}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable long bookId){
        return bookService.updateBook(book, bookId);
    }
}
