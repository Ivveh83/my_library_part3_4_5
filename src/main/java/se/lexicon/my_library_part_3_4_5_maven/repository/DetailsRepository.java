package se.lexicon.my_library_part_3_4_5_maven.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.my_library_part_3_4_5_maven.entity.Details;

import java.util.List;
import java.util.Optional;


public interface DetailsRepository extends CrudRepository<Details, Integer> {
    Optional<Details> findByEmail(String email);

    List<Details> findByNameContains(String name);

    List<Details> findByNameIgnoreCase(String name);
}
