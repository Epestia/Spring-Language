package be.ipam.language.api.controller;

import be.ipam.language.api.dto.VocabularyListEntityDto;
import be.ipam.language.api.mapper.VocabularyListEntityMapper;
import be.ipam.language.bll.exceptions.VocabularyListException;
import be.ipam.language.bll.services.Iservices.IVocabularyListService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/vocabulary-lists")
@Scope("singleton")
@RequiredArgsConstructor
public class VocabularyListController {

    private final IVocabularyListService vocabularyListService;
    private final VocabularyListEntityMapper mapper;


    @GetMapping
    public ResponseEntity<List<VocabularyListEntityDto>> getAll() {
        List<VocabularyListEntityDto> dtos = vocabularyListService.getAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VocabularyListEntityDto> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(mapper.toDto(vocabularyListService.getById(id)));
        } catch (VocabularyListException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/official")
    public ResponseEntity<List<VocabularyListEntityDto>> getOfficial() {
        List<VocabularyListEntityDto> dtos = vocabularyListService.getOfficialLists().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/public")
    public ResponseEntity<List<VocabularyListEntityDto>> getPublic() {
        List<VocabularyListEntityDto> dtos = vocabularyListService.getPublicLists().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<VocabularyListEntityDto> create(@RequestBody VocabularyListEntityDto dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(mapper.toDto(vocabularyListService.createList(mapper.toEntity(dto))));
        } catch (VocabularyListException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<VocabularyListEntityDto> update(@PathVariable Long id,
                                                          @RequestBody VocabularyListEntityDto dto) {
        try {
            return ResponseEntity.ok(mapper.toDto(vocabularyListService.updateList(id, mapper.toEntity(dto))));
        } catch (VocabularyListException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            vocabularyListService.deleteList(id);
            return ResponseEntity.noContent().build();
        } catch (VocabularyListException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/{id}/publish")
    public ResponseEntity<VocabularyListEntityDto> publish(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(mapper.toDto(vocabularyListService.publishList(id)));
        } catch (VocabularyListException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
