package me.kenux.springsecurity.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kenux.springsecurity.domain.book.Book;
import me.kenux.springsecurity.domain.book.BookRepository;
import me.kenux.springsecurity.domain.book.BookService;
import me.kenux.springsecurity.web.dto.BookAddRequest;
import me.kenux.springsecurity.web.dto.BookResponse;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostConstruct
    public void init() {
        Book bookA = new Book("BookA", "Kim", 1000L, 20L);
        bookService.addBook(bookA);
        Book bookB = new Book("BookB", "Lee", 1500L, 30L);
        bookService.addBook(bookB);
    }

    @GetMapping
    public String books(Authentication authentication, Model model) {
        List<BookResponse> books = bookService.getAllBooks();
        final String role = authentication.getAuthorities().toString();
        model.addAttribute("role", role.contains("ADMIN"));
        model.addAttribute("books", books);
        return "books";
    }

    //    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @PostAuthorize("hasRole('ROLE_ADMIN')")
    @Secured("ROLE_ADMIN")
    @GetMapping("/{bookId}")
    public String getBook(@PathVariable Long bookId, Model model) {
        BookResponse bookResponse = bookService.getBook(bookId);
        model.addAttribute("book", bookResponse);
        return "book";
    }

    @GetMapping("/add")
    public String addBookPage() {
        return "addBook";
    }

    @PostMapping("/add")
    public String addBook(BookAddRequest bookAddRequest) {
        bookService.addBook(bookAddRequest.toEntity());
        return "redirect:/books";
    }
}
