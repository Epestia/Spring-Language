package be.ipam.language.dao.entities;

import jakarta.persistence.*;
@Entity
public class FlashcardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String term;

    @ManyToOne
    @JoinColumn(name = "list_id", nullable = false)
    private VocabularyListEntity list;
}
