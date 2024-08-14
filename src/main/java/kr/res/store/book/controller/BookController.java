package kr.res.store.book.controller;


import kr.res.store.book.dto.BookRequestDto;
import kr.res.store.book.dto.BookResponseDto;
import kr.res.store.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService bookSearchService;

    //1. 책검색
    @PostMapping("/list")
     public ResponseEntity<List<BookResponseDto>> getBookList(@RequestBody BookRequestDto bookRequestDto) {
         List<BookResponseDto> bookList = bookSearchService.getBookList(bookRequestDto);
         return ResponseEntity.ok(bookList);
     }

    //2. 책추가
     @PostMapping("/add")
     public ResponseEntity<String> addBook(@RequestBody BookRequestDto bookRequestDto) {
         int result = bookSearchService.addBook(bookRequestDto);
         if (result > 0) {
             return ResponseEntity.ok("OK");
         } else {
             return ResponseEntity.status(500).body("Failed to add book");
         }
     }

    //3. 책상태변경 (훼손, 분실로 인한 대여중단)
     @PostMapping("/updateBook")
     public ResponseEntity<String> updateBook(@RequestBody BookRequestDto bookRequestDto) {
         int result = bookSearchService.updateBook(bookRequestDto);
         if (result > 0) {
             return ResponseEntity.ok("OK");
         } else {
             return ResponseEntity.status(500).body("Failed to add book");
         }
     }

    //4. 카테고리 변경
     @PostMapping("/updateCategory")
     public ResponseEntity<String> updateCategory(@RequestBody BookRequestDto bookRequestDto) {
         int result = bookSearchService.updateCategory(bookRequestDto);
         if (result > 0) {
             return ResponseEntity.ok("OK");
         } else {
             return ResponseEntity.status(500).body("Failed to add book");
         }
     }
}
