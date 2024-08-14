package kr.res.store.book.repository.querydsl.impl;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.res.store.book.dto.BookRequestDto;
import kr.res.store.book.dto.BookResponseDto;
import kr.res.store.book.repository.querydsl.BookRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static kr.res.store.book.entity.QBookEntity.bookEntity;
import static kr.res.store.book.entity.QCategoryEntity.categoryEntity;
import static kr.res.store.book.entity.QCategoryCdEntity.categoryCdEntity;

import java.util.List;


@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {


    @PersistenceContext
    private EntityManager entityManager;

    private final JPAQueryFactory queryFactory;

    public List<BookResponseDto> findBookList(BookRequestDto bookRequestDto) {

        // 1. category_cd.id 값으로 카테고리명 검색
        BooleanBuilder whereBuilder = new BooleanBuilder();
        if (bookRequestDto.getCategoryDto().get(0).getCategoryCdId() != null) {
            whereBuilder.and(categoryEntity.categoryCd.id.like("%" + bookRequestDto.getCategoryDto().get(0).getCategoryCdId() + "%"));
        }

        // 2. books 테이블 제목, 작가명 검색
        List<BookResponseDto> res = queryFactory
                .select(Projections.constructor(BookResponseDto.class,
                        bookEntity.id,
                        bookEntity.title,
                        bookEntity.author,
                        bookEntity.rentalSuspended,
                        categoryCdEntity.categoryName
                ))
                 .from(bookEntity)
                .leftJoin(categoryEntity).on(bookEntity.id.eq(categoryEntity.book.id))
                .leftJoin(categoryCdEntity).on(categoryEntity.categoryCd.id.eq(categoryCdEntity.id))
                .where(
                         (bookEntity.title.like("%" + bookRequestDto.getBookDto().getTitle() + "%"))
                         .and(bookEntity.author.like("%" + bookRequestDto.getBookDto().getAuthor() + "%"))
                         .and(whereBuilder)
                         .and(bookEntity.rentalSuspended.isFalse())     //대여여부 가능한 정보
                 )
                .orderBy(bookEntity.id.asc())
                .fetch();



        return res;
    }

    @Transactional
    public int addBook (BookRequestDto bookRequestDto){
        int res1 = 0, res2 = 0;

        //1. Books 테이블 책정보 INSERT
        res1 = entityManager.createNativeQuery("INSERT INTO Books (title, author, rental_suspended) VALUES (?, ?, ?)")
                    .setParameter(1, bookRequestDto.getBookDto().getTitle())
                    .setParameter(2, bookRequestDto.getBookDto().getAuthor())
                    .setParameter(3, bookRequestDto.getBookDto().getRentalSuspended())
                    .executeUpdate();

        //2. INSERT 후에 key 값 검색
        Long bookId = queryFactory.select(bookEntity.id.max())
                                  .from(bookEntity)
                                  .fetchOne();

        //3. Categoies 카테고리정보 1:N INSERT
        if (res1>0) {
            for(int i=0; i<bookRequestDto.getCategoryDto().size();i++) {
                res2 = entityManager.createNativeQuery("INSERT INTO Categories (book_id, category_cd_id) VALUES (?, ?)")
                         .setParameter(1, bookId)
                         .setParameter(2, bookRequestDto.getCategoryDto().get(i).getCategoryCdId())
                         .executeUpdate();
             }
         }
        return res2;
    }


    @Transactional
    public int updateBook (BookRequestDto bookRequestDto) {

        // 책정보 UPDATE
        int res = entityManager.createNativeQuery("UPDATE Books SET rental_suspended = ? WHERE book_id = ?")
                    .setParameter(1, bookRequestDto.getBookDto().getRentalSuspended())
                    .setParameter(2, bookRequestDto.getBookDto().getBookId())
                    .executeUpdate();
        return res;
    }

    @Transactional
    public int updateCategory (BookRequestDto bookRequestDto) {
        int res1 = 0, res2 = 0;

        //1. 책의 기존 카테고리 정보 DELETE
        res1 = entityManager.createNativeQuery("DELETE FROM Categories WHERE book_id = ? ")
                    .setParameter(1, bookRequestDto.getBookDto().getBookId())
                    .executeUpdate();

        //2. 책의 카테고리 정보 INSERT
        if(res1>0){
            for(int i=0; i<bookRequestDto.getCategoryDto().size();i++) {
                res2 = entityManager.createNativeQuery("INSERT INTO Categories (book_id, category_cd_id) VALUES (?, ?)")
                         .setParameter(1, bookRequestDto.getBookDto().getBookId())
                         .setParameter(2, bookRequestDto.getCategoryDto().get(i).getCategoryCdId())
                         .executeUpdate();
            }
        }

        return res2;
    }

}
