package be.ipam.language.bll.services.Iservices;

import be.ipam.language.dao.entities.VocabularyListEntity;
import java.util.List;

public interface IVocabularyListService {

    VocabularyListEntity getById(Long id);

    List<VocabularyListEntity> getAll();

    List<VocabularyListEntity> getOfficialLists();

    List<VocabularyListEntity> getPublicLists();

    List<VocabularyListEntity> getListsByLanguage(Long languageId);

    List<VocabularyListEntity> getListsByCreator(Long creatorId);

    VocabularyListEntity createList(VocabularyListEntity list);

    VocabularyListEntity updateList(Long id, VocabularyListEntity list);

    void deleteList(Long id);

    VocabularyListEntity publishList(Long id);
}
