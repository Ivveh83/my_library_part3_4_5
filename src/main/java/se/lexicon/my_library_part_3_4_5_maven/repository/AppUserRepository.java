package se.lexicon.my_library_part_3_4_5_maven.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.lexicon.my_library_part_3_4_5_maven.entity.AppUser;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Integer> {
    Optional<AppUser> findByUsername(String username);

    List<AppUser> findByRegDateBetween(LocalDate startDate, LocalDate endDate);
    @Query("SELECT a FROM AppUser a JOIN a.userDetails d WHERE d.id = :id")
    //"SELECT a FROM AppUser a JOIN a.userDetails d WHERE d.id = :id"
    Optional<AppUser> findByDetailsId(int id);
    @Query("SELECT a FROM AppUser a JOIN a.userDetails d WHERE LOWER(d.email) = LOWER(:email)")
    Optional<AppUser> findByUserDetailsEmailIgnoreCase(@Param("email") String email);

}
