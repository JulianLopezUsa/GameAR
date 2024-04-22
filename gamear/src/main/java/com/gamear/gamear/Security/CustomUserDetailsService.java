package com.gamear.gamear.Security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gamear.gamear.Modelo.Usuario;
import com.gamear.gamear.Repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCorreo(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con correo: " + username);
        }
        return new org.springframework.security.core.userdetails.User(usuario.getCorreo(), usuario.getContrasena(), new ArrayList<>());
    }
}
