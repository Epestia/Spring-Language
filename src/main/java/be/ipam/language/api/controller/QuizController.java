package be.ipam.language.api.controller;

import be.ipam.language.api.dto.QuizEntityDto;
import be.ipam.language.bll.services.Iservices.IQuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
public class QuizController {

    private final IQuizService quizService;

    @PostMapping
    public ResponseEntity<QuizEntityDto> createQuiz(@RequestBody QuizEntityDto quizDto) {
        QuizEntityDto createdQuiz = quizService.createQuiz(quizDto);
        return ResponseEntity.ok(createdQuiz);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizEntityDto> getQuizById(@PathVariable Long id) {
        QuizEntityDto quiz = quizService.getQuizById(id);
        return ResponseEntity.ok(quiz);
    }

    @GetMapping
    public ResponseEntity<List<QuizEntityDto>> getAllQuizzes() {
        return ResponseEntity.ok(quizService.getAllQuizzes());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<QuizEntityDto>> getQuizzesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(quizService.getQuizzesByUser(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuizEntityDto> updateQuiz(
            @PathVariable Long id,
            @RequestBody QuizEntityDto quizDto) {

        QuizEntityDto updatedQuiz = quizService.updateQuiz(id, quizDto);
        return ResponseEntity.ok(updatedQuiz);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuiz(id);
        return ResponseEntity.noContent().build();
    }
}
