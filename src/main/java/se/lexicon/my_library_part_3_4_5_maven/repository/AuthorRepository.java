package se.lexicon.my_library_part_3_4_5_maven.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import se.lexicon.my_library_part_3_4_5_maven.entity.Author;

import java.util.List;


public interface AuthorRepository extends CrudRepository<Author, Integer> {

    List<Author> findByFirstName(String firstName);
    List<Author> findByLastName(String lastName);
    @Query("SELECT a FROM Author a WHERE a.firstName LIKE CONCAT( '%',:keyword,'%') OR a.lastName LIKE CONCAT( '%',:keyword,'%')")
    List<Author> findByFirstNameContainingOrLastNameContaining(@Param("keyword") String keyword);

    @Query("SELECT a FROM Author a JOIN a.writtenBooks b WHERE b.id = :bookId")
    List<Author> findByWrittenBooks_BookId(@Param("bookId") int bookId);

    /*
    @Query("update Author a set a.firstName = :firstName, a.lastName = :lastName where a.id = :id")
    @Modifying
    Author updateNameById(@Param("id") int id, @Param("firstName") String firstName, @Param("lastName") String lastName);

     */

    @Query("UPDATE Author a set a.firstName = :firstName, a.lastName = :lastName WHERE a.id = :id")
    @Modifying
    void updateAuthorNameById(@Param("id") int id,
                          @Param("firstName") String firstName,
                          @Param("lastName") String lastName);


//void uses in the context that you dont know what rows that will be updated.

    void deleteAuthorById(int authorId);
}
