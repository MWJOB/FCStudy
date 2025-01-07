package org.example.bookmanager.domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.bookmanager.repository.dto.BookStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Book extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String category;

    private Long authorId;

//    private Long publisherId;

    @OneToOne(mappedBy = "book")
    @ToString.Exclude
    private BookReviewInfo bookReviewInfo;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @ToString.Exclude
    private Publisher publisher;

    @Convert
    private BookStatus status; //판매상태

//    @ManyToMany
    @OneToMany
    @JoinColumn(name ="book_id")
    @ToString.Exclude
    private List<BookAndAuthor> bookAndAuthors = new ArrayList<>();

    public void addBookAndAuthors(BookAndAuthor... bookAndAuthors) {
        Collections.addAll(this.bookAndAuthors, bookAndAuthors);
    }
}
