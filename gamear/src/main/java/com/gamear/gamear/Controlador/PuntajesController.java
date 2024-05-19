package com.gamear.gamear.Controlador;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamear.gamear.Modelo.Equipo;
import com.gamear.gamear.Modelo.Puntajes;
import com.gamear.gamear.Modelo.Sesion;
import com.gamear.gamear.Modelo.Usuario;
import com.gamear.gamear.Repository.EquipoRepository;
import com.gamear.gamear.Repository.PuntajeRepository;
import com.gamear.gamear.Repository.SesionRepository;
import com.gamear.gamear.Repository.UsuarioRepository;

@RestController
@RequestMapping("/api/puntajes")
public class PuntajesController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EquipoRepository equipoRepository;
    @Autowired
    private SesionRepository sesionRepository;
    @Autowired
    private PuntajeRepository puntajesRepository;

    @PostMapping("/asignar/{sesionId}")
    public ResponseEntity<?> asignarUsuariosAEquipos(@PathVariable Long sesionId, @RequestBody List<Long> usuariosSeleccionados) {
        Sesion sesion = sesionRepository.findById(sesionId).orElse(null);
        if (sesion == null) {
            return ResponseEntity.badRequest().body("Sesión no encontrada");
        }
    
        List<Usuario> usuarios = usuarioRepository.findAllById(usuariosSeleccionados);
        List<Equipo> equipos = equipoRepository.findAll();
    
        if (equipos.isEmpty() || usuarios.isEmpty()) {
            return ResponseEntity.badRequest().body("No hay suficientes equipos o usuarios para asignar");
        }
    
        Random random = new Random();
        for (Usuario usuario : usuarios) {
            Equipo equipoAleatorio = equipos.get(random.nextInt(equipos.size()));
            Puntajes puntaje = new Puntajes();
            puntaje.setUsuario(usuario);
            puntaje.setEquipo(equipoAleatorio);
            puntaje.setSesion(sesion);
            puntaje.setPuntaje(0);  // Inicializar puntaje si es necesario
    
            puntajesRepository.save(puntaje);
        }
        return ResponseEntity.ok("Usuarios asignados aleatoriamente a equipos y a la sesión " + sesionId);
    }
    
}
