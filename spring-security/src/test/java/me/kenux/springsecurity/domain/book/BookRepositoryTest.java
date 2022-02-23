package me.kenux.springsecurity.domain.book;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

//@DataJpaTest
class BookRepositoryTest {

    //    @Autowired
    private final BookRepository bookRepository = BookRepository.getInstance();

    @AfterEach
    void afterEach() {
        bookRepository.clearAll();
    }

    @Test
    void getInstance() {
        BookRepository instance1 = BookRepository.getInstance();
        BookRepository instance2 = BookRepository.getInstance();

        assertThat(instance1).isEqualTo(instance2);

        System.out.println("instance1 = " + instance1);
        System.out.println("instance2 = " + instance2);
    }

    @Test
    void save() {
        // given
        Book newBook = new Book("bookA", "authorA", 10000L, 10L);

        // when
        Book savedBook = bookRepository.save(newBook);

        // then
        Book findBook = bookRepository.findById(savedBook.getId());
        assertThat(findBook).isEqualTo(savedBook);
        assertThat(findBook.getId()).isEqualTo(savedBook.getId());
    }

    @Test
    void findAll() {
        // given
        Book newBook1 = new Book("bookA", "authorA", 10000L, 10L);
        Book newBook2 = new Book("bookB", "authorB", 12000L, 100L);

        bookRepository.save(newBook1);
        bookRepository.save(newBook2);

        // when
        List<Book> books = bookRepository.findAll();

        // then
        assertThat(books).hasSize(2);
    }

    @Test
    void update() {
        // given
        Book newBook1 = new Book("bookA", "authorA", 10000L, 10L);
        Book savedBook = bookRepository.save(newBook1);

        Book newBook2 = new Book("bookA", "authorA", 20000L, 10L);

        // when
        Book updateBook = bookRepository.update(savedBook.getId(), newBook2);

        // then
        assertThat(updateBook.getPrice()).isEqualTo(20000L);
    }

}