package kr.res.store.book.entity;

import lombok.Getter;

import javax.persistence.*;


/*
    CATEGORY_CD 카테고리 정보
    category_cd_id : CATEGORY_CD Table key
    category_name  : 카테고리명
    ex)   1L : 문학, 2L : 경제경영, 3L : 인문학, 4L : IT, 5L : 과학
*/

@Entity
@Getter
@Table(name = "category_cd")
public class CategoryCdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_cd_id", nullable = false)
    private Long id;

    @Column(name = "category_name", nullable = false)
    private String categoryName;

}
