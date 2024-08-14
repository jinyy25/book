package kr.res.store.book.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;



/*
    BOOKS 책정보
    book_id : key
    title   : 제목
    author  : 작가
    rental_suspended : 대여중단여부
*/

@Entity
@Getter
@Table(name = "books")
public class BookEntity {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "book_id", nullable = false)
     private Long id;

     @Column(name = "title", nullable = false)
     private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "rental_suspended", nullable = false)
    private Boolean rentalSuspended = false;

}
