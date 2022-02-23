package me.kenux.springsecurity.domain.book;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Book {

    private Long id;
    private String title;
    private String author;
    private Long price;
    private Long quantity;

    @Builder
    public Book(String title, String author, Long price, Long quantity) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.quantity = quantity;
    }

    public Book updateBookInfo(Book book) {
        title = book.getTitle();
        author = book.getAuthor();
        price = book.getPrice();
        quantity = book.getQuantity();
        return this;
    }

    public void updateId(long id) {
        this.id = id;
    }
}
