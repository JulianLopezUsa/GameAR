package com.gamear.gamear.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamear.gamear.entity.Quiz;
import com.gamear.gamear.repository.QuizRepository;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;

    public Quiz getQuizById(Long id) {
        return quizRepository.findById(id).orElse(null);
    }

    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

}