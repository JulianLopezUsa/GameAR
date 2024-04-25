package com.example.juegoar.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.juegoar.Usuario;
import com.example.juegoar.UsuarioRepository;

import io.micrometer.common.util.StringUtils;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> createUsuario(@Validated @RequestBody Usuario usuario) {

        // Verificar si alguno de los campos obligatorios está vacío
        if (StringUtils.isEmpty(usuario.getNombres()) || StringUtils.isEmpty(usuario.getDocumento())
                ||
                StringUtils.isEmpty(usuario.getCorreo()) || StringUtils.isEmpty(usuario.getContrasena())) {
            // Si hay campos vacíos, devuelve un mensaje de error con código de estado 400
            // Bad Request
            return ResponseEntity.badRequest().body("Todos los campos son obligatorios.");
        }

        // Verificar si el correo ya está registrado
        Usuario existingUsuarioCorreo = usuarioRepository.findByCorreo(usuario.getCorreo());
        if (existingUsuarioCorreo != null) {
            // Si el correo ya está registrado, devuelve un mensaje de error
            return ResponseEntity.badRequest().body("El correo electrónico ya está registrado.");
        }

        // Verificar si el documento ya está registrado
        Usuario existingUsuarioDocumento = usuarioRepository.findByDocumento(usuario.getDocumento());
        if (existingUsuarioDocumento != null) {
            // Si el documento ya está registrado, devuelve un mensaje de error
            return ResponseEntity.badRequest().body("El documento ya está registrado.");
        }

        // Si el correo y el documento no están registrados, guarda el nuevo usuario
        usuario.setEstado(true); // Establecer el estado como activo por defecto
        usuario.setRol(1); // Asignar rol por defecto
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }
}
