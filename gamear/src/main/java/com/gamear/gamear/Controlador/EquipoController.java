package com.gamear.gamear.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamear.gamear.Modelo.Equipo;
import com.gamear.gamear.Repository.EquipoRepository;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController {

    @Autowired
    private EquipoRepository equipoRepository;
    
    @PostMapping("/crear")
    public ResponseEntity<?> createEquipo(@Validated @RequestBody Equipo equipo) {
        Equipo nuevoEquipo = equipoRepository.save(equipo);
        return ResponseEntity.ok(nuevoEquipo);
    }
}
