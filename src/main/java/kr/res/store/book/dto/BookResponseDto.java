package kr.res.store.book.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDto {
    private Long bookId;                //key
    private String title;               //책이름
    private String author;              //작가이름
    private Boolean rentalSuspended;    //대여중단여부
    private String categoryName;
}
