package com.gamear.gamear.Controlador;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gamear.gamear.Usuario;
import com.gamear.gamear.UsuarioRepository;

import io.micrometer.common.util.StringUtils;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody Map<String, String> credentials) {
        String correo = credentials.get("correo");
        String contrasena = credentials.get("contrasena");

        // Validar que el correo electrónico no sea nulo ni esté vacío
        if (StringUtils.isNotEmpty(correo)) {
            Usuario usuario = usuarioRepository.findByCorreo(correo);

            if (usuario != null && usuario.getContrasena().equals(contrasena)) {
                return ResponseEntity.ok("Login exitoso");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Correo o contraseña incorrectos");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Correo electrónico requerido");
        }
    }
}
