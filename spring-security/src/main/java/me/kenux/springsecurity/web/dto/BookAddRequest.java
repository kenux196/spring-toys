package me.kenux.springsecurity.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.kenux.springsecurity.domain.book.Book;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookAddRequest {

    private String bookTitle;
    private String author;
    private Long price;
    private Long quantity;

    public Book toEntity() {
        return new Book(bookTitle, author, price, quantity);
    }
}