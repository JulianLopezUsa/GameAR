package com.gamear.gamear.entity;

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

}
