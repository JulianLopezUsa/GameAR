package com.gamear.gamear.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gamear.gamear.Modelo.Sesion;

public interface SesionRepository extends JpaRepository<Sesion, Long> {

    Sesion findById(Integer id);
}


