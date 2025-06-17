package se.lexicon.my_library_part_3_4_5_maven.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.my_library_part_3_4_5_maven.entity.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {

    Book findByIsbnIgnoreCase(String isbn);
    Book findByTitleContains(String title);
    Book findByMaxLoanDaysLessThan(int maxLoanDays);
}
