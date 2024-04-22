package com.gamear.gamear.repository;

import org.springframework.data.repository.CrudRepository;

import com.gamear.gamear.entity.Quiz;

public interface QuizRepository extends CrudRepository<Quiz, Long> {
}
