package kr.res.store.book.repository.querydsl;

import kr.res.store.book.dto.BookDto;
import kr.res.store.book.dto.BookRequestDto;
import kr.res.store.book.dto.BookResponseDto;
import kr.res.store.book.dto.CategoryDto;
import kr.res.store.book.entity.BookEntity;
import kr.res.store.book.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

public interface BookRepositoryCustom {
    List<BookResponseDto> findBookList(BookRequestDto bookRequestDto);
    int addBook (BookRequestDto bookRequestDto);
    int updateBook (BookRequestDto bookRequestDto);
    int updateCategory (BookRequestDto bookRequestDto);
}
