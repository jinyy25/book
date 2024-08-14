package kr.res.store.book.service;


import kr.res.store.book.dto.BookRequestDto;
import kr.res.store.book.dto.BookResponseDto;
import kr.res.store.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service("BookService")
public class BookService {

    @Autowired
    private BookRepository bookSearchRepository;

    public List<BookResponseDto> getBookList(BookRequestDto bookRequestDto) {
            return bookSearchRepository.findBookList(bookRequestDto);
    }
    public int addBook(BookRequestDto bookRequestDto) {
        return bookSearchRepository.addBook(bookRequestDto);
    }

    public int updateBook(BookRequestDto bookRequestDto) {
        return bookSearchRepository.updateBook(bookRequestDto);
    }

    public int updateCategory(BookRequestDto bookRequestDto) {
        return bookSearchRepository.updateCategory(bookRequestDto);
    }
}
