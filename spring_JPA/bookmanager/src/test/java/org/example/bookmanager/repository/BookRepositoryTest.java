package org.example.bookmanager.repository;

import jakarta.transaction.Transactional;
import org.example.bookmanager.domain.Book;
import org.example.bookmanager.domain.Member;
import org.example.bookmanager.domain.Publisher;
import org.example.bookmanager.domain.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void bookTest(){
        Book book = new Book();
        book.setName("Jpa 테스트");
        book.setAuthorId(1L);
//        book.setPublisherId(1L);

        bookRepository.save(book);

        System.out.println(bookRepository.findAll());

    }

    @Test
    @Transactional
    void bookRelationTest(){
        givenBookAndReview();

        Member member = givenMember();

        System.out.println("Review : " + member.getReviews());
        System.out.println("Book : " + member.getReviews().get(0).getBook());
        System.out.println("Publisher : " + member.getReviews().get(0).getBook().getPublisher());
    }
    private void givenBookAndReview(){
        givenReview(givenMember(), givenBook(givenPublisher()));
    }

    private Member givenMember(){
        return memberRepository.findByEmail("martin@naver.com");
    }

    private void givenReview(Member member, Book book){
        Review review = new Review();

        review.setTitle("JPA의 모든 것");
        review.setContent("정말 유익해요");
        review.setScore(5.0f);
        review.setMember(member);
        review.setBook(book);

        reviewRepository.save(review);
    }

    private Book givenBook(Publisher publisher){
        Book book = new Book();
        book.setName("JPA 너무 좋아");
        book.setPublisher(publisher);

        return bookRepository.save(book);
    }

    private Publisher givenPublisher(){
        Publisher publisher = new Publisher();
        publisher.setName("이문복");
        return publisherRepository.save(publisher);
    }
}