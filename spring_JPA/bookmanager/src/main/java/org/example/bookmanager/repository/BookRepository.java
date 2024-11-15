package org.example.bookmanager.repository;

import org.example.bookmanager.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Long> {
}
