package be.ipam.language.api.mapper;

import be.ipam.language.api.dto.LanguageEntityDto;
import be.ipam.language.dao.entities.LanguageEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LanguageEntityMapper {
    //@Mapping(target = "id", ignore = true)
    LanguageEntity toEntity(LanguageEntityDto languageEntityDto);

    LanguageEntityDto toDto(LanguageEntity languageEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    LanguageEntity partialUpdate(LanguageEntityDto languageEntityDto, @MappingTarget LanguageEntity languageEntity);
}