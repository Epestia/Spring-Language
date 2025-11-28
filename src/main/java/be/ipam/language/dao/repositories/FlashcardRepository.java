package be.ipam.language.dao.repositories;

import be.ipam.language.dao.entities.FlashcardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlashcardRepository extends JpaRepository<FlashcardEntity, Long> {

    List<FlashcardEntity> findByListId(Long listId);

    List<FlashcardEntity> findTop10ByListIdOrderByIdAsc(Long listId);

    int countByListId(Long listId);
}
