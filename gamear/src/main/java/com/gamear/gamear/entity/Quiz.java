package com.gamear.gamear.entity;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;


@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String topic;
    private int totalScore;
    
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Question> questions = new ArrayList<>();
    
}

