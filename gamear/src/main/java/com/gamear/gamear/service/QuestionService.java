package com.gamear.gamear.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamear.gamear.entity.Question;
import com.gamear.gamear.repository.QuestionRepository;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

}
