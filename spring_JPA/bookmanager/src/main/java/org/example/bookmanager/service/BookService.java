package org.example.bookmanager.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.bookmanager.domain.Author;
import org.example.bookmanager.domain.Book;
import org.example.bookmanager.repository.AuthorRepository;
import org.example.bookmanager.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;
    private final UserService userService;
    private final AuthorRepository authorRepository;

    @Transactional
    public void putBookAndAuthor(){
        Book book = new Book();
        book.setName("jpa 시작");

        bookRepository.save(book);

        Author author = new Author();
        author.setName("martin");

        authorRepository.save(author);
    }

    @Transactional
    public void get(Long id){
        System.out.println(">>>" + bookRepository.findById(id));
        System.out.println(">>>" + bookRepository.findAll());

        System.out.println(">>>" + bookRepository.findById(id));
        System.out.println(">>>" + bookRepository.findAll());


    }

    @Transactional
    public List<Book> getAllBooks(){
        List<Book> books = bookRepository.findAll();
        books.forEach(System.out::println);
        return books;
    }
}
