package com.gamear.gamear.entity;
import jakarta.persistence.*;


@Entity
public class Option {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String optionText;
    private Boolean isCorrect;
    
    // Foreign Key
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    public void setId(Long id) {
        this.id = id;
    }

    
    
}

