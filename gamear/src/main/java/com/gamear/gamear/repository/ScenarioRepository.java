package com.gamear.gamear.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gamear.gamear.entity.Scenario;

@Repository
public interface ScenarioRepository extends JpaRepository<Scenario, Long> {
}