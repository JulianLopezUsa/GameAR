package com.example.juegoar.Controlador;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.juegoar.Usuario;
import com.example.juegoar.UsuarioRepository;

import io.micrometer.common.util.StringUtils;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> credentials) {
        String correo = credentials.get("correo");
        String contrasena = credentials.get("contrasena");

        // Validar que el correo electrónico no sea nulo ni esté vacío
        if (StringUtils.isNotEmpty(correo)) {
            Usuario usuario = usuarioRepository.findByCorreo(correo);

            if (usuario != null && usuario.getContrasena().equals(contrasena)) {
                // Crear un mapa para almacenar el rol y el nombre del usuario
                Map<String, Object> response = new HashMap<>();
                response.put("mensaje", "Login exitoso");
                response.put("rol", usuario.getRol());
                response.put("nombre", usuario.getNombres());
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Collections.singletonMap("error", "Correo o contraseña incorrectos"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("error", "Correo electrónico requerido"));
        }
    }
}
