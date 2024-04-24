package com.gamear.gamear.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gamear.gamear.Modelo.Puntajes;

public interface PuntajeRepository extends JpaRepository<Puntajes, Long> {
    
}
