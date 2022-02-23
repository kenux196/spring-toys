package me.kenux.springsecurity.web.dto;

import lombok.Builder;
import lombok.Data;
import me.kenux.springsecurity.domain.book.Book;

@Data
@Builder
public class BookResponse {

    private long id;
    private String title;
    private String author;
    private long price;
    private long quantity;

    public static BookResponse of(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .price(book.getPrice())
                .quantity(book.getQuantity())
                .build();
    }
}
