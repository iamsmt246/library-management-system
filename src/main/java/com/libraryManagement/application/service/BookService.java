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
                .buildAndExpand(newBook.getBookId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<Book> deleteBookById(long bookId) {
        Book bookToBeDeleted = getBookById(bookId);
        bookRepository.delete(bookToBeDeleted);

        return new ResponseEntity<Book>(HttpStatus.OK);
    }

    public ResponseEntity<Book> updateBook(Book book, long bookId) {
        Book bookToBeUpdated = getBookById(bookId);

        if(bookToBeUpdated != null){
            // Manually updating only non-null fields
            if (book.getTitle() != null) {
                bookToBeUpdated.setTitle(book.getTitle());
            }
            if (book.getAuthor() != null) {
                bookToBeUpdated.setAuthor(book.getAuthor());
            }
            if (book.getIsbn() != null) {
                bookToBeUpdated.setIsbn(book.getIsbn());
            }
            if (book.getGenre() != null) {
                bookToBeUpdated.setGenre(book.getGenre());
            }
            if (book.getTotalQuantity() != null) {
                bookToBeUpdated.setTotalQuantity(book.getTotalQuantity());
            }
            if (book.getAvailableQuantity() != null) {
                bookToBeUpdated.setAvailableQuantity(book.getAvailableQuantity());
            }
        }

        // Saving the updated book
        Book updatedBook = bookRepository.save(bookToBeUpdated);

        // Returning the updated book in the response
        return new ResponseEntity<Book>(updatedBook,HttpStatus.OK);
    }
}
