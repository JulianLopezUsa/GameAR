package com.gamear.gamear.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamear.gamear.entity.Scenario;
import com.gamear.gamear.repository.ScenarioRepository;

@Service
public class ScenarioService {
    @Autowired
    private ScenarioRepository scenarioRepository;

    public List<Scenario> getAllScenarios() {
        return scenarioRepository.findAll();
    }

    public Optional<Scenario> getScenarioById(Long id) {
        return scenarioRepository.findById(id);
    }

    public Scenario saveScenario(Scenario scenario) {
        return scenarioRepository.save(scenario);
    }

    public void deleteScenarioById(Long id) {
        scenarioRepository.deleteById(id);
    }

}