package be.ipam.language.bll.services.Iservices;


import be.ipam.language.dao.entities.LanguageEntity;

import java.util.List;

public interface ILanguageService {
    List<LanguageEntity> getAllLanguages();
    LanguageEntity getLanguageById(Long id);
    LanguageEntity getLanguageByCode(String code);
    LanguageEntity createLanguage(LanguageEntity language);
    LanguageEntity updateLanguage(Long id, LanguageEntity language);
    void deleteLanguage(Long id);
}