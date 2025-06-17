package se.lexicon.my_library_part_3_4_5_maven.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.my_library_part_3_4_5_maven.entity.Book;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        Book book = new Book("1234567890", "Book Title", 10);
        bookRepository.save(book);
    }

    @Test
    void findByIsbnIgnoreCase() {
        List<Book> bookList = bookRepository.findByIsbnIgnoreCase("1234567890");
        assertEquals(1, bookList.size());
    }

    @Test
    void findByTitleContains() {
        List<Book> bookList = bookRepository.findByTitleContains("Title");
        assertEquals(1, bookList.size());
        assertEquals("Book Title", bookList.get(0).getTitle());
    }

    @Test
    void findByMaxLoanDaysLessThan() {
        List<Book> bookList = bookRepository.findByMaxLoanDaysLessThan(11);
        assertEquals(1, bookList.size());
        assertEquals("Book Title", bookList.get(0).getTitle());
        assertEquals(10, bookList.get(0).getMaxLoanDays());
        bookList.forEach(book -> System.out.println("Found Book: " + book));

    }
}