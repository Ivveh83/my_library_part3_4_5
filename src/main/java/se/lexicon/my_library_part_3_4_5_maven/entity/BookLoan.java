package se.lexicon.my_library_part_3_4_5_maven.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

@Entity
public class BookLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private LocalDate loanDate;

    @Column(nullable = false)
    private LocalDate dueDate;

    private Boolean returned;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn()
    private AppUser borrower;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn()
    private Book book;

    public BookLoan(AppUser borrower, Book book) {
        this.borrower = borrower;
        this.book = book;
    }

    @PrePersist
    public void prePersist() {
        this.loanDate = LocalDate.now();
        this.dueDate = loanDate.plusDays(book.getMaxLoanDays());
        this.returned = false;
    }

}
