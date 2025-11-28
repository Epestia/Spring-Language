package be.ipam.language.bll.services.Iservices;

import be.ipam.language.dao.entities.FlashcardEntity;
import java.util.List;

public interface IFlashCardService {

    FlashcardEntity getById(Long id);

    List<FlashcardEntity> getAll();

    List<FlashcardEntity> getByListId(Long listId);

    FlashcardEntity create(FlashcardEntity flashcard);

    FlashcardEntity update(Long id, FlashcardEntity flashcard);

    void delete(Long id);
}
