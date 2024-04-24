package com.gamear.gamear.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gamear.gamear.entity.Option;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {
    List<Option> findByOptionText(String optionText);
}
