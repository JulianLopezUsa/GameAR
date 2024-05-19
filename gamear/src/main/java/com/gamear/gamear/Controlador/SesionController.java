package com.gamear.gamear.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamear.gamear.Modelo.Sesion;
import com.gamear.gamear.Repository.SesionRepository;

@RestController
@RequestMapping("/api/sesion")
public class SesionController {

    @Autowired
    private SesionRepository sesionRepository;

    private boolean checkCoordinatorRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_COORDINADOR"));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllSesions() {
        /*if (!checkCoordinatorRole()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acceso denegado");
        }*/
        return ResponseEntity.ok(sesionRepository.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createSesion(@Validated @RequestBody Sesion sesion) {
        if (!checkCoordinatorRole()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No tienes permiso para realizar esta acción");
        }
        if (sesion.getNombre() == null || sesion.getClave() == null) {
            return ResponseEntity.badRequest().body("Se necesita llenar todos los espacios para iniciar la sala");
        }
        Sesion nuevaSesion = sesionRepository.save(sesion);
        return ResponseEntity.ok(nuevaSesion);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSesion(@PathVariable Long id, @Validated @RequestBody Sesion sesionDetails) {
        if (!checkCoordinatorRole()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No tienes permiso para realizar esta acción");
        }
        return sesionRepository.findById(id)
            .map(sesion -> {
                sesion.setNombre(sesionDetails.getNombre());
                sesion.setClave(sesionDetails.getClave());
                final Sesion updatedSesion = sesionRepository.save(sesion);
                return ResponseEntity.ok(updatedSesion);
            }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSesion(@PathVariable Long id) {
        if (!checkCoordinatorRole()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No tienes permiso para realizar esta acción");
        }
        return sesionRepository.findById(id)
            .map(sesion -> {
                sesionRepository.delete(sesion);
                return ResponseEntity.ok("Sesión eliminada correctamente");
            }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}