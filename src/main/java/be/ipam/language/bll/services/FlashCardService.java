package be.ipam.language.bll.services;

import be.ipam.language.bll.services.Iservices.IFlashCardService;

import be.ipam.language.dao.entities.FlashcardEntity;
import be.ipam.language.bll.exceptions.FlashcardException;
import be.ipam.language.dao.repositories.FlashcardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Scope("singleton")
public class FlashCardService implements IFlashCardService {

    private final FlashcardRepository flashcardRepository;


    @Override
    public FlashcardEntity getById(Long id) {
        return flashcardRepository.findById(id)
                .orElseThrow(() -> new FlashcardException("Flashcard not found."));
    }

    @Override
    public List<FlashcardEntity> getAll() {
        return flashcardRepository.findAll();
    }

    @Override
    public List<FlashcardEntity> getByListId(Long listId) {
        List<FlashcardEntity> cards = flashcardRepository.findByListId(listId);
        if (cards.isEmpty()) {
            throw new FlashcardException("No flashcards found for this list.");
        }
        return cards;
    }

    @Override
    public FlashcardEntity create(FlashcardEntity flashcard) {
        if (flashcard.getTerm() == null || flashcard.getTerm().isBlank()) {
            throw new FlashcardException("Flashcard term cannot be empty.");
        }
        return flashcardRepository.save(flashcard);
    }

    @Override
    public FlashcardEntity update(Long id, FlashcardEntity flashcard) {
        FlashcardEntity existing = flashcardRepository.findById(id)
                .orElseThrow(() -> new FlashcardException("Flashcard to update not found."));

        if (flashcard.getTerm() == null || flashcard.getTerm().isBlank()) {
            throw new FlashcardException("Flashcard term cannot be empty.");
        }

        existing.setTerm(flashcard.getTerm());
        existing.setList(flashcard.getList());

        return flashcardRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        if (!flashcardRepository.existsById(id)) {
            throw new FlashcardException("Flashcard to delete not found.");
        }
        flashcardRepository.deleteById(id);
    }
}
