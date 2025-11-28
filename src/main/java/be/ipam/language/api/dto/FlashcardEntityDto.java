package be.ipam.language.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link be.ipam.language.dao.entities.FlashcardEntity}
 */
@Data
@NoArgsConstructor
public class FlashcardEntityDto implements Serializable {
    private Long id;
    private String term;
    private VocabularyListEntityDto list;
}
