package be.ipam.language.api.mapper;

import be.ipam.language.api.dto.FlashcardEntityDto;
import be.ipam.language.dao.entities.FlashcardEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {VocabularyListEntityMapper.class})
public interface FlashcardEntityMapper {
    FlashcardEntity toEntity(FlashcardEntityDto flashcardEntityDto);

    FlashcardEntityDto toDto(FlashcardEntity flashcardEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FlashcardEntity partialUpdate(FlashcardEntityDto flashcardEntityDto, @MappingTarget FlashcardEntity flashcardEntity);
}