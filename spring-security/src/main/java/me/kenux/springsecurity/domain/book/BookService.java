package me.kenux.springsecurity.domain.book;

import me.kenux.springsecurity.web.dto.BookResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository = BookRepository.getInstance();

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public List<BookResponse> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(BookResponse::of)
                .collect(Collectors.toList());
    }

    public BookResponse getBook(Long id) {
        final Book book = bookRepository.findById(id);
        return BookResponse.of(book);
    }
}
