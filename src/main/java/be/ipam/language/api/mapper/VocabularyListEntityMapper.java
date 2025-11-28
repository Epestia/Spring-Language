package be.ipam.language.api.mapper;

import be.ipam.language.api.dto.VocabularyListEntityDto;
import be.ipam.language.dao.entities.VocabularyListEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {LanguageEntityMapper.class, UserEntityMapper.class})
public interface VocabularyListEntityMapper {
    VocabularyListEntity toEntity(VocabularyListEntityDto vocabularyListEntityDto);

    @AfterMapping
    default void linkFlashcards(@MappingTarget VocabularyListEntity vocabularyListEntity) {
        vocabularyListEntity.getFlashcards().forEach(flashcard -> flashcard.setList(vocabularyListEntity));
    }

    VocabularyListEntityDto toDto(VocabularyListEntity vocabularyListEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    VocabularyListEntity partialUpdate(VocabularyListEntityDto vocabularyListEntityDto, @MappingTarget VocabularyListEntity vocabularyListEntity);
}