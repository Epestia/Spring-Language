package be.ipam.language.dao.entities;

import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
public class VocabularyListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "language_id", nullable = false)
    private LanguageEntity language;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private UserEntity creator;

    @Column(nullable = false)
    private boolean official;

    @Column(nullable = false)
    private boolean isPublic;

    private LocalDate createdAt = LocalDate.now();

    @OneToMany(mappedBy = "list", cascade = CascadeType.ALL)
    private List<FlashcardEntity> flashcards = new ArrayList<>();
}
