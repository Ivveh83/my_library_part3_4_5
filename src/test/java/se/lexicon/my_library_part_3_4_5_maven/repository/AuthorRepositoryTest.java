package se.lexicon.my_library_part_3_4_5_maven.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.my_library_part_3_4_5_maven.entity.Author;
import se.lexicon.my_library_part_3_4_5_maven.entity.Book;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        Author author = new Author("John", "Doe");
        Author author1 = new Author("Jane", "Doe");
        authorRepository.save(author);
        authorRepository.save(author1);

    }

    @Test
    void findByFirstName() {
        List<Author> authorList = authorRepository.findByFirstName("John");
        Author author = authorList.getFirst();

        assertEquals(1, authorList.size());
        assertEquals("John", author.getFirstName());
    }

    @Test
    void findByLastName() {
        List<Author> authorList = authorRepository.findByLastName("Doe");
        Author authorJohn = authorList.getFirst();
        Author authorJane = authorList.getLast();
        assertEquals(2, authorList.size());
        assertEquals("John", authorJohn.getFirstName());
        assertEquals("Jane", authorJane.getFirstName());
    }

    @Test
    void findByFirstNameContainingOrLastNameContaining() {
        List<Author> authorListLastName = authorRepository.findByFirstNameContainingOrLastNameContaining("oe");
        assertEquals(2, authorListLastName.size());
        List<Author> authorListFirstName = authorRepository.findByFirstNameContainingOrLastNameContaining("oh");
        assertEquals(1, authorListFirstName.size());
        Author author = authorListFirstName.getFirst();
        assertEquals("John", author.getFirstName());
        assertEquals("Doe", author.getLastName());
    }

    @Test
    void findByWrittenBooks_BookId() {
        bookRepository.save(new Book("1234567890", "Book Title", 10))
                .addAuthor(authorRepository.findByFirstName("John").getFirst());
        List<Author> authorList = authorRepository.findByWrittenBooks_BookId(1);
        assertEquals(1, authorList.size());
        Author author = authorList.getFirst();
        assertEquals("John", author.getFirstName());
    }

    @Test
    void updateAuthorNameById() {
        Author author = authorRepository.findByFirstName("John").getFirst();
        author.setFirstName("Johnny");
        authorRepository.updateAuthorNameById(author.getId(), author.getFirstName(), author.getLastName());
        Author updatedAuthor = authorRepository.findById(author.getId()).get();
        assertEquals("Johnny", updatedAuthor.getFirstName());
        assertEquals("Doe", updatedAuthor.getLastName());
        assertEquals(author.getId(), updatedAuthor.getId());
        assertEquals(author.getFirstName(), updatedAuthor.getFirstName());
        assertEquals(author.getLastName(), updatedAuthor.getLastName());
    }

    @Test
    void deleteAuthorById() {
        authorRepository.deleteAuthorById(authorRepository.findByFirstName("John").getFirst().getId());
        assertEquals(1, authorRepository.count());
        assertEquals("Jane", authorRepository.findByFirstName("Jane").getFirst().getFirstName());
        assertEquals("Doe", authorRepository.findByFirstName("Jane").getFirst().getLastName());
        assertEquals(1, authorRepository.findByFirstName("Jane").size());
        assertEquals(0, authorRepository.findByFirstName("John").size());
        authorRepository.deleteAuthorById(authorRepository.findByFirstName("Jane").getFirst().getId());
        assertEquals(0, authorRepository.count());
        assertEquals(0, authorRepository.findByFirstName("Jane").size());
        assertEquals(0, authorRepository.findByFirstName("John").size());

    }
}