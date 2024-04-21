package com.gamear.gamear.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gamear.gamear.Modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByCorreo(String correo);

    Usuario findByDocumento(String documento);
}
