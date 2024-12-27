package org.example.bookmanager.service;

import org.example.bookmanager.domain.Book;
import org.example.bookmanager.repository.AuthorRepository;
import org.example.bookmanager.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void TransactionTest(){
        bookService.putBookAndAuthor();

        System.out.println("books :" + bookRepository.findAll());
        System.out.println("authors :" + authorRepository.findAll());
    }

    @Test
    void isolationTest(){
        Book book = new Book();
        book.setName("JPA");

        bookRepository.save(book);

        bookService.get(1L);

        System.out.println("books :" + bookRepository.findAll());
    }

}