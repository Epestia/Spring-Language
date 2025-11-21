package be.ipam.language.api.mapper;

import be.ipam.language.api.dto.UserEntityDto;
import be.ipam.language.dao.entities.UserEntity;
import org.mapstruct.*;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {RoleEntityMapper.class}
)
public interface UserEntityMapper {

    @Mapping(target = "id", ignore = true)
    UserEntity toEntity(UserEntityDto userEntityDto);

    UserEntityDto toDto(UserEntity userEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserEntity partialUpdate(UserEntityDto userEntityDto, @MappingTarget UserEntity userEntity);
}
