package me.kenux.springsecurity.domain.book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Repository
public class BookRepository {

    private final Map<Long, Book> store = new HashMap<>();
    private long sequence = 0;

    private static final BookRepository instance = new BookRepository();

    public static BookRepository getInstance() {
        return instance;
    }

    private BookRepository() {

    }

    public Book save(Book book) {
        book.updateId(++sequence);
        store.put(book.getId(), book);
        return book;
    }

    public Book findById(Long id) {
        return store.get(id);
    }

    public List<Book> findAll() {
        return new ArrayList<>(store.values());
    }

    public Book update(Long id, Book book) {
        Book findBook = findById(id);
        return findBook.updateBookInfo(book);
    }

    public void clearAll() {
        store.clear();
    }
}
