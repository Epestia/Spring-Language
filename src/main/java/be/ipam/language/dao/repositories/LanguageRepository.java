package be.ipam.language.dao.repositories;

import be.ipam.language.dao.entities.LanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<LanguageEntity, Long> {
    Optional<LanguageEntity> findByName(String name);
    Optional<LanguageEntity> findByCode(String code);
}
