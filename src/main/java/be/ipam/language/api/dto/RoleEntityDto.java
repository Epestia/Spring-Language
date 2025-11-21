package be.ipam.language.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO pour {@link be.ipam.language.dao.entities.RoleEntity}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntityDto implements Serializable {
    private Long id;
    private String name;
}
