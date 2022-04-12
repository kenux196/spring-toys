package me.kenux.jpalearn.domain.member.domain;

public class Book extends Item {

    private String author;
    private String isbn;

    public Book(String id, String name, int price, String author, String isbn) {
        super(id, name, price);
        this.author = author;
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }
}