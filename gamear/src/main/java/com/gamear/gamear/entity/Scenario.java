package com.gamear.gamear.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Scenario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idScenario;

    private String name;
    private String description;
    private double latitude;
    private double longitude;

    @OneToMany(mappedBy = "scenario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Quiz> quizzes;

    // Getters and setters
    public Long getIdScenario() {
        return idScenario;
    }

    public void setIdScenario(Long idScenario) {
        this.idScenario = idScenario;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }
}