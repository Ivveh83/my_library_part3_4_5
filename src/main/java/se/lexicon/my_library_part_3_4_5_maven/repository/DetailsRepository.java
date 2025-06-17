package se.lexicon.my_library_part_3_4_5_maven.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.my_library_part_3_4_5_maven.entity.Details;


public interface DetailsRepository extends CrudRepository<Details, Integer> {
    Details findByEmail(String email);
    Details findByNameContaining(String name);
    Details findByNameIgnoreCase(String name);
}
