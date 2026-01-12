package be.ipam.language.api.mapper;

import be.ipam.language.api.dto.QuizEntityDto;
import be.ipam.language.dao.entities.QuizEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserEntityMapper.class, VocabularyListEntityMapper.class})
public interface QuizEntityMapper {
    QuizEntity toEntity(QuizEntityDto quizEntityDto);

    QuizEntityDto toDto(QuizEntity quizEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    QuizEntity partialUpdate(QuizEntityDto quizEntityDto, @MappingTarget QuizEntity quizEntity);
}