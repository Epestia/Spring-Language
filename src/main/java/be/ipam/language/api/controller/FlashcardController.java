package be.ipam.language.api.controller;

import be.ipam.language.api.dto.FlashcardEntityDto;
import be.ipam.language.api.mapper.FlashcardEntityMapper;
import be.ipam.language.bll.exceptions.FlashcardException;
import be.ipam.language.bll.services.Iservices.IFlashCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/flashcards")
@RequiredArgsConstructor
public class FlashcardController {

    private final IFlashCardService flashCardService;
    private final FlashcardEntityMapper mapper;

    @GetMapping
    public ResponseEntity<List<FlashcardEntityDto>> getAll() {
        List<FlashcardEntityDto> dtos = flashCardService.getAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlashcardEntityDto> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(mapper.toDto(flashCardService.getById(id)));
        } catch (FlashcardException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/list/{listId}")
    public ResponseEntity<List<FlashcardEntityDto>> getByList(@PathVariable Long listId) {
        try {
            List<FlashcardEntityDto> dtos = flashCardService.getByListId(listId).stream()
                    .map(mapper::toDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtos);
        } catch (FlashcardException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<FlashcardEntityDto> create(@RequestBody FlashcardEntityDto dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(mapper.toDto(flashCardService.create(mapper.toEntity(dto))));
        } catch (FlashcardException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlashcardEntityDto> update(@PathVariable Long id,
                                                     @RequestBody FlashcardEntityDto dto) {
        try {
            return ResponseEntity.ok(mapper.toDto(flashCardService.update(id, mapper.toEntity(dto))));
        } catch (FlashcardException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            flashCardService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (FlashcardException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
