package be.ipam.language.api.dto;

import be.ipam.language.dao.entities.FlashcardEntity;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link be.ipam.language.dao.entities.VocabularyListEntity}
 */

@Data
public class VocabularyListEntityDto implements Serializable {
    Long id;
    String title;
    LanguageEntityDto language;
    UserEntityDto creator;
    boolean official;
    boolean isPublic;
    LocalDate createdAt;
    private List<FlashcardEntityDto> flashcards;
}