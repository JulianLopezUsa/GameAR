package com.gamear.gamear.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.gamear.gamear.Usuario;
import com.gamear.gamear.UsuarioRepository;

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
        if (StringUtils.isEmpty(usuario.getNombre()) || StringUtils.isEmpty(usuario.getDocumento())
                || StringUtils.isEmpty(usuario.getApellido()) ||
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
        usuario.setRol(1); // Asignar rol por defecto
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }
}
