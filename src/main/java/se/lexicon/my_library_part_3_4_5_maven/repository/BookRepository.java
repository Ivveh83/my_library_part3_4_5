package se.lexicon.my_library_part_3_4_5_maven.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.my_library_part_3_4_5_maven.entity.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

    List<Book> findByIsbnIgnoreCase(String isbn);
    List<Book> findByTitleContains(String title);
    List<Book> findByMaxLoanDaysLessThan(int maxLoanDays);
}
