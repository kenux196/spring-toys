package me.kenux.springsecurity.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kenux.springsecurity.domain.book.Book;
import me.kenux.springsecurity.domain.book.BookRepository;
import me.kenux.springsecurity.domain.book.BookService;
import me.kenux.springsecurity.web.dto.BookResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
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
    public String books(Model model) {
        List<BookResponse> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "books";
    }
}
