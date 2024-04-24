package com.gamear.gamear.Controlador;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.gamear.gamear.Modelo.Rol;
import com.gamear.gamear.Modelo.Usuario;
import com.gamear.gamear.Repository.UsuarioRepository;

import io.micrometer.common.util.StringUtils;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")

public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/all")
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUsuario(@Validated @RequestBody Usuario usuario) {
        if (StringUtils.isEmpty(usuario.getNombre()) || StringUtils.isEmpty(usuario.getDocumento()) ||
                StringUtils.isEmpty(usuario.getCorreo()) || StringUtils.isEmpty(usuario.getContrasena())) {
            return ResponseEntity.badRequest().body("Todos los campos son obligatorios.");
        }

        // Verificar si el correo ya está registrado
        if (usuarioRepository.findByCorreo(usuario.getCorreo()) != null) {
            return ResponseEntity.badRequest().body("El correo electrónico ya está registrado.");
        }

        // Verificar si el documento ya está registrado
        if (usuarioRepository.findByDocumento(usuario.getDocumento()) != null) {
            return ResponseEntity.badRequest().body("El documento ya está registrado.");
        }

        // Cifrar la contraseña antes de guardar el usuario
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));

        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {
        // Obtener el usuario actualmente autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Verificar si el usuario tiene el rol de administrador
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMINISTRADOR"))) {
            // Solo los administradores pueden eliminar usuarios
            usuarioRepository.deleteById(id);
            return ResponseEntity.ok("Usuario eliminado correctamente");
        } else {
            // Si no es un administrador, devolver un error de autorización
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No tienes permiso para realizar esta acción");
        }
    }

    @PostMapping("/upload-csv")
    public ResponseEntity<String> uploadCSVFile(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try (Reader reader = new InputStreamReader(file.getInputStream())) {
                Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
                for (CSVRecord record : records) {
                    Usuario usuario = new Usuario();
                    usuario.setNombre(record.get("nombre"));
                    usuario.setEstado(Integer.parseInt(record.get("estado")));
                    usuario.setDocumento(record.get("documento"));
                    usuario.setCorreo(record.get("correo"));
                    usuario.setEscuela(record.get("escuela"));
                    usuario.setRol(Rol.valueOf(record.get("rol"))); // Convertir a mayúsculas y obtener el enum correspondiente
                    usuario.setNumeroCelular(record.get("numeroCelular"));
                    usuario.setContrasena(passwordEncoder.encode(record.get("contrasena")));

                    if (StringUtils.isEmpty(usuario.getNombre()) || StringUtils.isEmpty(usuario.getDocumento()) ||
                            StringUtils.isEmpty(usuario.getCorreo()) || StringUtils.isEmpty(usuario.getContrasena())) {
                        return ResponseEntity.badRequest().body("Todos los campos son obligatorios.");
                    }

                    // Verificar si el correo ya está registrado
                    if (usuarioRepository.findByCorreo(usuario.getCorreo()) != null) {
                        return ResponseEntity.badRequest().body("El correo electrónico ya está registrado.");
                    }

                    // Verificar si el documento ya está registrado
                    if (usuarioRepository.findByDocumento(usuario.getDocumento()) != null) {
                        return ResponseEntity.badRequest().body("El documento ya está registrado.");
                    }
                    usuarioRepository.save(usuario);
                }
                return ResponseEntity.ok("Usuarios cargados exitosamente!");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Error al cargar el archivo: " + e.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Archivo vacío");
    }

}
