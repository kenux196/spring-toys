package me.kenux.springsecurity.web.rest;

import lombok.RequiredArgsConstructor;
import me.kenux.springsecurity.domain.book.Book;
import me.kenux.springsecurity.domain.book.BookService;
import me.kenux.springsecurity.web.dto.BookAddRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookRestController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<?> books() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody BookAddRequest bookAddRequest) {
        final Book book = bookService.addBook(bookAddRequest.toEntity());
        return ResponseEntity.ok(book.getId());
    }
}