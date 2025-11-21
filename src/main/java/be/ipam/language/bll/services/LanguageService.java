package be.ipam.language.bll.services;

import be.ipam.language.bll.exceptions.LanguageException;
import be.ipam.language.bll.services.Iservices.ILanguageService;
import be.ipam.language.dao.entities.LanguageEntity;
import be.ipam.language.dao.repositories.LanguageRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Scope("singleton")
public class LanguageService implements ILanguageService {

    private final LanguageRepository languageRepository;

    @Override
    public List<LanguageEntity> getAllLanguages() {
        return languageRepository.findAll();
    }

    @Override
    public LanguageEntity getLanguageById(Long id) {
        return languageRepository.findById(id)
                .orElseThrow(() -> new LanguageException("Language not found with id: " + id));
    }

    @Override
    public LanguageEntity getLanguageByCode(String code) {
        return languageRepository.findByCode(code)
                .orElseThrow(() -> new LanguageException("Language not found with code: " + code));
    }

    @Override
    public LanguageEntity createLanguage(LanguageEntity language) {
        return languageRepository.save(language);
    }

    @Override
    public LanguageEntity updateLanguage(Long id, LanguageEntity language) {
        LanguageEntity existing = getLanguageById(id);
        existing.setName(language.getName());
        existing.setCode(language.getCode());
        return languageRepository.save(existing);
    }

    @Override
    public void deleteLanguage(Long id) {
        if (!languageRepository.existsById(id)) {
            throw new LanguageException("Language not found with id: " + id);
        }
        languageRepository.deleteById(id);
    }
}
