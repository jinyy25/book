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
    CATEGORIES 카테고리 중간테이블
    category_id     : CATEGORIES Table key
    book_id         : BOOKS Table key
    category_cd_id  : CATEGORY_CD Table key
*/

@Entity
@Getter
@Table(name = "categories")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_cd_id", nullable = false)
    private CategoryCdEntity categoryCd;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity book;
}
