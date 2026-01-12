package be.ipam.language.bll.services;

import be.ipam.language.api.dto.QuizEntityDto;
import be.ipam.language.api.mapper.QuizEntityMapper;
import be.ipam.language.bll.exceptions.QuizException;
import be.ipam.language.bll.services.Iservices.IQuizService;
import be.ipam.language.dao.entities.QuizEntity;
import be.ipam.language.dao.entities.UserEntity;
import be.ipam.language.dao.repositories.QuizRepository;
import be.ipam.language.dao.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizService implements IQuizService {

    private final QuizRepository quizRepository;
    private final UserRepository userRepository;
    private final QuizEntityMapper quizMapper;

    @Override
    public QuizEntityDto createQuiz(QuizEntityDto quizDto) {
        QuizEntity quiz = quizMapper.toEntity(quizDto);
        quiz.setDate(LocalDateTime.now());

        QuizEntity savedQuiz = quizRepository.save(quiz);
        return quizMapper.toDto(savedQuiz);
    }

    @Override
    public QuizEntityDto getQuizById(Long id) {
        QuizEntity quiz = quizRepository.findById(id)
                .orElseThrow(() -> new QuizException("Quiz not found with id " + id));

        return quizMapper.toDto(quiz);
    }

    @Override
    public List<QuizEntityDto> getAllQuizzes() {
        return quizRepository.findAll()
                .stream()
                .map(quizMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuizEntityDto> getQuizzesByUser(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new QuizException("User not found with id " + userId));

        return quizRepository.findByUserOrderByDateDesc(user)
                .stream()
                .map(quizMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public QuizEntityDto updateQuiz(Long id, QuizEntityDto quizDto) {
        QuizEntity quiz = quizRepository.findById(id)
                .orElseThrow(() -> new QuizException("Quiz not found with id " + id));

        quizMapper.partialUpdate(quizDto, quiz);

        QuizEntity updatedQuiz = quizRepository.save(quiz);
        return quizMapper.toDto(updatedQuiz);
    }

    @Override
    public void deleteQuiz(Long id) {
        if (!quizRepository.existsById(id)) {
            throw new QuizException("Quiz not found with id " + id);
        }
        quizRepository.deleteById(id);
    }
}
