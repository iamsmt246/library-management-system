package com.libraryManagement.application.service;

import com.libraryManagement.application.entity.Book;
import com.libraryManagement.application.exception.BookNotFoundException;
import com.libraryManagement.application.repository.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);

        // if book with given bookId is not found then throw custom error for book not found
        if(!book.isPresent()){
            throw new BookNotFoundException("book with bookId:"+ bookId + " not found!");
        }

        return book.get();
    }

    public ResponseEntity<Book> addBook(Book book) {
        Book newBook = bookRepository.save(book);

        // URI for newly created book entity
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{bookId}")
                .buildAndExpand().toUri();

        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<Book> deleteBookById(long bookId) {
        Book bookToBeDeleted = getBookById(bookId);
        bookRepository.delete(bookToBeDeleted);

        return new ResponseEntity<Book>(HttpStatus.OK);
    }

    public ResponseEntity<Book> updateBook(Book book, long bookId) {
        Book updatedBook = new Book();
        Optional<Book> savedBook = bookRepository.findById(bookId);
        Book updateBook = savedBook.get();

        if(savedBook.isPresent()){
            BeanUtils.copyProperties(book, updatedBook);
        }
        updatedBook = bookRepository.save(updateBook);
        return new ResponseEntity<Book>(updatedBook,HttpStatus.OK);
    }
}
