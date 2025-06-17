package se.lexicon.my_library_part_3_4_5_maven.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString


@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
    private int id;
    @Column(nullable = false, unique = true)
    private String username;
    private String password;
    private LocalDate regDate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "details_id")
    private Details userDetails;


    public AppUser(String userName, String password, Details userDetails) {
        this.username = userName;
        this.password = password;
        this.userDetails = userDetails;
    }

    @PrePersist
    public void prePersist() {
        this.regDate = LocalDate.now();
    }
}