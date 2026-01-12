package be.ipam.language.dao.repositories;

import be.ipam.language.dao.entities.QuizEntity;
import be.ipam.language.dao.entities.UserEntity;
import be.ipam.language.dao.entities.VocabularyListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<QuizEntity, Long> {

    List<QuizEntity> findByUser(UserEntity user);

    List<QuizEntity> findByList(VocabularyListEntity list);

    List<QuizEntity> findByUserAndList(UserEntity user, VocabularyListEntity list);

    List<QuizEntity> findByType(String type);

    List<QuizEntity> findByUserOrderByDateDesc(UserEntity user);
}
