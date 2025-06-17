package se.lexicon.my_library_part_3_4_5_maven.repository;

import jakarta.persistence.Access;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import se.lexicon.my_library_part_3_4_5_maven.entity.Details;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DetailsRepositoryTest {

    @Autowired
    DetailsRepository detailsRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        Details details = new Details("email@test.com", "name", LocalDate.now());
        Details details1 = new Details("email@test.se", "name", LocalDate.of(2025, 6, 16));
        detailsRepository.save(details);
        detailsRepository.save(details1);

    }

    @Test
    void findByEmail() {

        Optional<Details> optionalDetails = detailsRepository.findByEmail("email@test.com");
        assertTrue(optionalDetails.isPresent());
        assertEquals("name", optionalDetails.get().getName());
    }

    @Test
    void findByNameContains() {
        List<Details> detailsList = new ArrayList<>();
        detailsList = detailsRepository.findByNameContains("am");
        assertEquals(2, detailsList.size());
    }

    @Test
    void findByNameIgnoreCase() {
        List<Details> detailsList = new ArrayList<>();
        detailsList = detailsRepository.findByNameIgnoreCase("NAME");
        assertEquals(2, detailsList.size());
        detailsList.forEach(details -> System.out.println("Found Details: " + details));
    }
}