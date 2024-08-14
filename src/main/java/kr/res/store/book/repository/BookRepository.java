package kr.res.store.book.repository;


import kr.res.store.book.entity.BookEntity;


import kr.res.store.book.repository.querydsl.BookRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long>, QuerydslPredicateExecutor<BookEntity>, BookRepositoryCustom {

}
