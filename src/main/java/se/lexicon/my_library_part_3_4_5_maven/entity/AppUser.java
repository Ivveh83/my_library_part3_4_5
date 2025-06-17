package se.lexicon.my_library_part_3_4_5_maven.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "borrower", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    List<BookLoan> bookLoans = new ArrayList<>();


    public AppUser(String userName, String password, Details userDetails) {
        this.username = userName;
        this.password = password;
        this.userDetails = userDetails;
    }

    @PrePersist
    public void prePersist() {
        this.regDate = LocalDate.now();
    }

    @Transactional
    void addBookLoan(BookLoan bookLoan) {
        bookLoan.getBook().setAvailable(false);
        bookLoans.add(bookLoan);
        bookLoan.setBorrower(this);
    }

    @Transactional
    void removeBookLoan(BookLoan bookLoan) {
        bookLoans.remove(bookLoan);
        bookLoan.setBorrower(null);
    }
}