package be.ipam.language.dao.repositories;

import be.ipam.language.dao.entities.VocabularyListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VocabularyListRepository extends JpaRepository<VocabularyListEntity, Long> {

    List<VocabularyListEntity> findByIsPublicTrue();

    List<VocabularyListEntity> findByLanguageId(Long languageId);

    List<VocabularyListEntity> findByCreatorId(Long creatorId);

    List<VocabularyListEntity> findByOfficialTrue();
}
