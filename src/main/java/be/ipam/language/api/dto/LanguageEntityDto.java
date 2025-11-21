package be.ipam.language.api.dto;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link be.ipam.language.dao.entities.LanguageEntity}
 */
@Data
public class LanguageEntityDto implements Serializable {
    Long id;
    String name;
    String code;
}