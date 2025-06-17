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

    /**
     * Creates a new BookLoan object.
     *
     * @param borrower the user who borrows the book; must not be null
     * @param book the book being borrowed; must be available
     * @throws IllegalArgumentException if the book is null or not available
     */
    public BookLoan(AppUser borrower, Book book) throws IllegalArgumentException{
        if (borrower == null) throw new IllegalArgumentException("Borrower must not be null");
        this.borrower = borrower;
        if (!book.isAvailable()) throw new IllegalArgumentException("Book is not available");
        this.book = book;
    }

    @PrePersist
    public void prePersist() {
        this.loanDate = LocalDate.now();
        this.dueDate = loanDate.plusDays(book.getMaxLoanDays());
        this.returned = false;
    }

}
