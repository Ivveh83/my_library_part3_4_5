package se.lexicon.my_library_part_3_4_5_maven.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.my_library_part_3_4_5_maven.entity.AppUser;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AppUserRepositoryTest {
    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    DetailsRepository detailsRepository;



    @Test
    void findByUsername() {
        // user
        AppUser appUser = new AppUser();
        appUser.setUsername("AndersAndersson");
        appUser.setPassword("password");
        appUser.setRegDate(LocalDate.now());
        appUser.setUserDetails(null);
        appUserRepository.save(appUser);

        //Act
        Optional<AppUser> foundUser = appUserRepository.findByUsername("AndersAndersson");

        // Assert
        assertTrue(foundUser.isPresent());
        assertEquals(appUser.getUsername(), foundUser.get().getUsername());

    }


    @Test
    void findByRegDateBetween() {
        LocalDate startDate = LocalDate.now().minusDays(1);
        LocalDate endDate = LocalDate.now().plusDays(1);

        AppUser appUser1 = new AppUser("User1", "password1",null);
        AppUser appUser2 = new AppUser("User2", "password2",null);

        appUserRepository.save(appUser1);
        appUserRepository.save(appUser2);

        //Act
        List<AppUser> users = appUserRepository.findByRegDateBetween(startDate, endDate);

        //Assert
        assertFalse(users.isEmpty());
        users.forEach(user -> System.out.println("Found User: " + user));
    }

    @Test
    void findByDetailsId() {
    }

    @Test
    void findByUserDetailsEmailIgnoreCase() {
    }
}