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
    private List<Quiz> quizs;

}
