package be.ipam.language.api.dto;

import be.ipam.language.dao.entities.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link be.ipam.language.dao.entities.UserEntity}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntityDto implements Serializable {
    private Long id;
    private String email;
    private String password;
    private Set<RoleEntityDto> roles;
}
