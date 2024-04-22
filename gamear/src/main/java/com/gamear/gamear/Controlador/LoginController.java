package com.gamear.gamear.Controlador;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.gamear.gamear.Modelo.Usuario;
import com.gamear.gamear.Repository.UsuarioRepository;
import com.gamear.gamear.Security.JwtUtil;
import org.springframework.security.core.userdetails.User;
import io.micrometer.common.util.StringUtils;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String correo = credentials.get("correo");
        String contrasena = credentials.get("contrasena");
        if (StringUtils.isNotEmpty(correo)) {
            Usuario usuario = usuarioRepository.findByCorreo(correo);
            if (usuario != null && passwordEncoder.matches(contrasena, usuario.getContrasena())) {

                UserDetails userDetails = User.builder()
                        .username(usuario.getCorreo())
                        .password(usuario.getContrasena())
                        .authorities(new ArrayList<>())
                        .build();
                String token = jwtUtil.generateToken(userDetails);
                return ResponseEntity.ok().body("Bearer " + token);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Correo o contraseña incorrectos");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Correo electrónico requerido");
        }
    }

}