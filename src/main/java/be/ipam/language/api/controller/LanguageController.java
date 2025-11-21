package be.ipam.language.api.controller;

import be.ipam.language.api.dto.LanguageEntityDto;
import be.ipam.language.api.mapper.LanguageEntityMapper;
import be.ipam.language.bll.services.Iservices.ILanguageService;
import be.ipam.language.dao.entities.LanguageEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/languages")
@RequiredArgsConstructor
@Scope("singleton")
public class LanguageController {

    private final ILanguageService languageService;
    private final LanguageEntityMapper languageMapper;

    @GetMapping
    public ResponseEntity<List<LanguageEntityDto>> getAllLanguages() {
        List<LanguageEntityDto> languages = languageService.getAllLanguages()
                .stream()
                .map(languageMapper::toDto)
                .toList();
        return ResponseEntity.ok(languages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LanguageEntityDto> getLanguageById(@PathVariable Long id) {
        LanguageEntity language = languageService.getLanguageById(id);
        return ResponseEntity.ok(languageMapper.toDto(language));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<LanguageEntityDto> getLanguageByCode(@PathVariable String code) {
        LanguageEntity language = languageService.getLanguageByCode(code);
        return ResponseEntity.ok(languageMapper.toDto(language));
    }

    @PostMapping
    public ResponseEntity<LanguageEntityDto> createLanguage(@RequestBody LanguageEntityDto dto) {
        LanguageEntity created = languageService.createLanguage(languageMapper.toEntity(dto));
        return ResponseEntity.ok(languageMapper.toDto(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LanguageEntityDto> updateLanguage(@PathVariable Long id, @RequestBody LanguageEntityDto dto) {
        LanguageEntity updated = languageService.updateLanguage(id, languageMapper.toEntity(dto));
        return ResponseEntity.ok(languageMapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLanguage(@PathVariable Long id) {
        languageService.deleteLanguage(id);
        return ResponseEntity.noContent().build();
    }
}
