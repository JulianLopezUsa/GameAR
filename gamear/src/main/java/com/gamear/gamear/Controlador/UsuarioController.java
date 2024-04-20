package com.gamear.gamear.Controlador;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.gamear.gamear.Usuario;
import com.gamear.gamear.UsuarioRepository;

import java.io.InputStreamReader;
import java.io.Reader;
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
    public ResponseEntity<Usuario> createUsuario(@Validated @RequestBody Usuario usuario) {
        usuario.setRol(1); // Asignar rol por defecto
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    @PostMapping("/upload-csv")
    public ResponseEntity<String> uploadCSVFile(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try (Reader reader = new InputStreamReader(file.getInputStream())) {
                Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
                for (CSVRecord record : records) {
                    Usuario usuario = new Usuario();
                    usuario.setNombre(record.get("nombre"));
                    usuario.setApellido(record.get("apellido"));
                    usuario.setCorreo(record.get("correo"));
                    usuario.setEscuela(record.get("escuela"));
                    usuario.setRol(Integer.parseInt(record.get("rol")));
                    usuario.setNumeroCelular(Integer.parseInt(record.get("numeroCelular")));
                    usuario.setContraseña(record.get("contraseña"));
                    usuarioRepository.save(usuario);
                }
                return ResponseEntity.ok("Usuarios cargados exitosamente!");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al cargar el archivo: " + e.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Archivo vacío");
    }

}
