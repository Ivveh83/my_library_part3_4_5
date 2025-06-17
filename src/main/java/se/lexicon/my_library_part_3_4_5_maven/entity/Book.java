package se.lexicon.my_library_part_3_4_5_maven.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data


@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int id;
    @Column(nullable = false)
    private String isbn;
    @Column(nullable = false)
    private String title;
    private int maxLoanDays;
    @ManyToMany
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors;


}
