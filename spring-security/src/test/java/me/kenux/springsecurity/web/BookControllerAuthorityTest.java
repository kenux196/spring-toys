package me.kenux.springsecurity.web;

import me.kenux.springsecurity.domain.WithMockAdmin;
import me.kenux.springsecurity.domain.book.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerAuthorityTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    @DisplayName("1. User 권한은 책을 등록할 수 없다.")
    @WithMockUser
    void test_UserRole_NotAccess_AddBook() throws Exception {
        mockMvc.perform(post("/books/add"))
                .andExpect(status().isForbidden());

    }

    @Test
    @DisplayName("2. Admin 권한은 책을 등록할 수 있다.")
    @WithMockAdmin
    void test_AdminRole_Access_AddBook() throws Exception {
        mockMvc.perform(post("/books/add"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("3. USER 권한은 북 리스트 조회가 가능하다")
    @WithMockUser
    void test_UserRoel_access_Books() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk());
    }


}