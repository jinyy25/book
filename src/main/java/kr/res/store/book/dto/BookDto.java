package kr.res.store.book.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private Long bookId;                //key
    private String title;               //책이름
    private String author;              //작가이름
    private Boolean rentalSuspended;    //대여중단여부
}
