package be.ipam.language.bll.services;

import be.ipam.language.bll.services.Iservices.IVocabularyListService;
import be.ipam.language.dao.entities.VocabularyListEntity;
import be.ipam.language.bll.exceptions.VocabularyListException;
import be.ipam.language.dao.repositories.VocabularyListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@Scope("singleton")
@RequiredArgsConstructor
public class VocabularyListService implements IVocabularyListService {

private final VocabularyListRepository vocabularyListRepository;

@Override
public VocabularyListEntity getById(Long id) {
    return vocabularyListRepository.findById(id)
            .orElseThrow(() -> new VocabularyListException("Vocabulary list not found."));
}

@Override
public List<VocabularyListEntity> getAll() {
    return vocabularyListRepository.findAll();
}

@Override
public List<VocabularyListEntity> getOfficialLists() {
    return vocabularyListRepository.findByOfficialTrue();
}

@Override
public List<VocabularyListEntity> getPublicLists() {
    return vocabularyListRepository.findByIsPublicTrue();
}

@Override
public List<VocabularyListEntity> getListsByLanguage(Long languageId) {
    List<VocabularyListEntity> lists = vocabularyListRepository.findByLanguageId(languageId);
    if (lists.isEmpty()) {
        throw new VocabularyListException("No vocabulary lists found for this language.");
    }
    return lists;
}

@Override
public List<VocabularyListEntity> getListsByCreator(Long creatorId) {
    List<VocabularyListEntity> lists = vocabularyListRepository.findByCreatorId(creatorId);
    if (lists.isEmpty()) {
        throw new VocabularyListException("No vocabulary lists found for this user.");
    }
    return lists;
}

@Override
public VocabularyListEntity createList(VocabularyListEntity list) {
    if (list.getTitle() == null || list.getTitle().isBlank()) {
        throw new VocabularyListException("Title cannot be empty.");
    }

    list.setPublic(false);
    list.setCreatedAt(LocalDate.now());

    return vocabularyListRepository.save(list);
}

@Override
public VocabularyListEntity updateList(Long id, VocabularyListEntity list) {
    VocabularyListEntity existing = vocabularyListRepository.findById(id)
            .orElseThrow(() -> new VocabularyListException("Vocabulary list to update not found."));

    if (list.getTitle() == null || list.getTitle().isBlank()) {
        throw new VocabularyListException("Title cannot be empty.");
    }

    existing.setTitle(list.getTitle());
    existing.setLanguage(list.getLanguage());
    existing.setCreator(list.getCreator());
    existing.setOfficial(list.isOfficial());
    existing.setPublic(list.isPublic());

    return vocabularyListRepository.save(existing);
}

@Override
public void deleteList(Long id) {
    if (!vocabularyListRepository.existsById(id)) {
        throw new VocabularyListException("Vocabulary list to delete not found.");
    }
    vocabularyListRepository.deleteById(id);
}

@Override
public VocabularyListEntity publishList(Long id) {
    VocabularyListEntity list = vocabularyListRepository.findById(id)
            .orElseThrow(() -> new VocabularyListException("Vocabulary list not found."));

    if (!list.isOfficial() && !list.isValidated()) {
        throw new VocabularyListException("This list must be validated by a manager before publication.");
    }

    list.setPublic(true);

    return vocabularyListRepository.save(list);
}
}
