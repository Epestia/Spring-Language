package be.ipam.language.bll.services.Iservices;

import be.ipam.language.api.dto.QuizEntityDto;

import java.util.List;

public interface IQuizService {

    QuizEntityDto createQuiz(QuizEntityDto quizDto);

    QuizEntityDto getQuizById(Long id);

    List<QuizEntityDto> getAllQuizzes();

    List<QuizEntityDto> getQuizzesByUser(Long userId);

    QuizEntityDto updateQuiz(Long id, QuizEntityDto quizDto);

    void deleteQuiz(Long id);
}
