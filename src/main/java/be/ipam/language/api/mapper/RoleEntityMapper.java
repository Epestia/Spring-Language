package be.ipam.language.api.mapper;

import be.ipam.language.api.dto.RoleEntityDto;
import be.ipam.language.dao.entities.RoleEntity;
import org.mapstruct.*;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface RoleEntityMapper {
    @Mapping(target = "id", ignore = true)
    RoleEntity toEntity(RoleEntityDto roleEntityDto);

    RoleEntityDto toDto(RoleEntity roleEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    RoleEntity partialUpdate(RoleEntityDto roleEntityDto, @MappingTarget RoleEntity roleEntity);
}
