package com.gamear.gamear.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gamear.gamear.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByCorreo(String correo);
}
