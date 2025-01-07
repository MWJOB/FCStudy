package org.example.bookmanager.repository;

import org.example.bookmanager.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


public interface BookRepository extends JpaRepository<Book, Long> {


    @Modifying
    @Query(value = "update book set category ='name', nativeQuery = true")
    void update();

    List<Book> findByCategoryIsNull();

    //이런식으로 너무 길면 쿼스텀 쿼리를 사용하는게 좋다.
    List<Book> findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(String name, LocalDateTime createdAt, LocalDateTime createdAt2);

    @Query(value = "select b from Book b where name = :name and createdAt >= :createdAt and updatedAt >= :updatedAt and category is null")
    List<Book> findByNameRecently(
            @Param("name") String name,
            @Param("createdAt") LocalDateTime createdAt,
            @Param("updatedAt") LocalDateTime updateAt);

    @Query(value = "select * from book", nativeQuery = true)
    List<Book> findAllCustom();

    @Query(value = "select * from book order by id decc limit 1", nativeQuery = true)
    Map<String, Object> findRawRecord();
}
