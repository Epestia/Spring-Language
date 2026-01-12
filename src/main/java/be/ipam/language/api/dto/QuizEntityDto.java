package be.ipam.language.api.dto;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link be.ipam.language.dao.entities.QuizEntity}
 */
@Data
public class QuizEntityDto implements Serializable {
    Long id;
    UserEntityDto user;
    VocabularyListEntityDto list;
    String type;
    Integer score;
    LocalDateTime date;
}