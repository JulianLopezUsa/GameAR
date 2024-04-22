package com.gamear.gamear.repository;

import org.springframework.data.repository.CrudRepository;

import com.gamear.gamear.entity.Question;

public interface QuestionRepository extends CrudRepository<Question, Long> {
}
