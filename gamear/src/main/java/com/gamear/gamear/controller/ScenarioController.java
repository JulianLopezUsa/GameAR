package com.gamear.gamear.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamear.gamear.entity.Scenario;
import com.gamear.gamear.service.ScenarioService;

@RestController
@RequestMapping("/api/scenarios")
public class ScenarioController {
    @Autowired
    private ScenarioService scenarioService;

    @GetMapping("/")
    public List<Scenario> getAllScenarios() {
        return scenarioService.getAllScenarios();
    }

    @GetMapping("/{id}")
    public Optional<Scenario> getScenarioById(@PathVariable Long id) {
        return scenarioService.getScenarioById(id);
    }

    @PostMapping("/")
    public Scenario createScenario(@RequestBody Scenario scenario) {
        return scenarioService.saveScenario(scenario);
    }

    @PutMapping("/{id}")
    public Scenario updateScenario(@PathVariable Long id, @RequestBody Scenario scenario) {
        scenario.setIdScenario(id); // Set the ID in the object before saving
        return scenarioService.saveScenario(scenario);
    }

    @DeleteMapping("/{id}")
    public void deleteScenario(@PathVariable Long id) {
        scenarioService.deleteScenarioById(id);
    }
}

