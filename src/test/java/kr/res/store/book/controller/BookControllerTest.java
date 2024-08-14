package kr.res.store.book.controller;

import com.google.gson.Gson;
import kr.res.store.book.dto.BookDto;
import kr.res.store.book.dto.BookRequestDto;
import kr.res.store.book.dto.CategoryDto;
import kr.res.store.book.service.BookService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @InjectMocks
    private BookController bookSearchController;

    @Mock
    private BookService bookSearchService;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookSearchController).build();
    }


    @DisplayName("책정보 가져오기")
    @Test
    public void getBookList() throws Exception {

        // given
        BookRequestDto bookRequestDto = bookRequestDto();
        Mockito.doReturn(null).when(bookSearchService).getBookList(Mockito.any(BookRequestDto.class));

        // When
        ResultActions actions = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/book/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(bookRequestDto))
        );

        //then
        MvcResult mvcResult = actions.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        Assertions.assertThat(result).isNotNull();
    }

    @DisplayName("책추가하기")
    @Test
    public void addBook() throws Exception {

        // given
        BookRequestDto bookRequestDto = bookRequestDto();
        Mockito.doReturn(1).when(bookSearchService).addBook(Mockito.any(BookRequestDto.class));

        // When
        ResultActions actions = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/book/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(bookRequestDto))
        );

        //then
        MvcResult mvcResult = actions.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String result = mvcResult.getResponse().getContentAsString();

        Assertions.assertThat(result).isNotNull();
    }



    @DisplayName("책상태 변경")
    @Test
    public void updateBook() throws Exception {

        // given
        BookRequestDto updateBookDto = updateBookDto();
        Mockito.doReturn(1).when(bookSearchService).updateBook(Mockito.any(BookRequestDto.class));

        // When
        ResultActions actions = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/book/updateBook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(updateBookDto))
        );

        //then
        MvcResult mvcResult = actions.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        Assertions.assertThat(result).isNotNull();
    }


    @DisplayName("책 카테고리 변경")
    @Test
    public void updateCategory() throws Exception {

        // given
        BookRequestDto updateCategoryDto = updateCategoryDto();
        Mockito.doReturn(1).when(bookSearchService).updateCategory(Mockito.any(BookRequestDto.class));

        // When
        ResultActions actions = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/book/updateCategory")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(updateCategoryDto))
        );

        //then
        MvcResult mvcResult = actions.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        Assertions.assertThat(result).isNotNull();
    }



    @DisplayName("책 요청정보 예시 (책검색, 책추가)")
    private BookRequestDto bookRequestDto() {
        // bookDto: key, 책이름, 작가명, 대여중단여부(false: 중단x)
        BookDto bookDto = new BookDto(null, "너에게 해주지 못한 말들", "권태영", false);

        // 정의된 categoryDto 코드
        // EX) 1L : 문학, 2L : 경제경영, 3L : 인문학, 4L : IT, 5L : 과학
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        categoryDtoList.add(new CategoryDto(null, null, 1L));
        categoryDtoList.add(new CategoryDto(null, null, 4L));
        BookRequestDto bookRequestDto = new BookRequestDto(bookDto,categoryDtoList);
        return bookRequestDto;
    }


    @DisplayName("책상태 변경 예시")
    private BookRequestDto updateBookDto() {
        // bookDto: key, 책이름, 작가명, 대여중단여부
        BookDto bookDto = new BookDto(4L, null, null, true);

        BookRequestDto bookRequestDto = new BookRequestDto(bookDto,null);
        return bookRequestDto;
    }


    @DisplayName("책카테고리 변경 예시")
    private BookRequestDto updateCategoryDto() {
        // bookDto: key, 책이름, 작가명, 대여중단여부
        BookDto bookDto = new BookDto(1L, null, null, null);

        // 정의된 categoryDto 코드
        // EX) 1L : 문학, 2L : 경제경영, 3L : 인문학, 4L : IT, 5L : 과학
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        categoryDtoList.add(new CategoryDto(null, null, 4L));
        categoryDtoList.add(new CategoryDto(null, null, 5L));
        BookRequestDto bookRequestDto = new BookRequestDto(bookDto,categoryDtoList);
        return bookRequestDto;
    }


}