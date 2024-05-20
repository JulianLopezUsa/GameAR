package com.gamear.gamear.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "index.html";
    }

    @GetMapping("/menu")
    public String menu() {
        return "navbar.html";
    }

    @GetMapping("/crearSesion")
    public String crearSesion() {
        return "crearSesion.html";
    }

    @GetMapping("/asignarEquipos")
    public String asignarEquipos() {
        return "asignarEquipos.html";
    }

    @GetMapping("/cargarUsuarios")
    public String cargarUsuarios() {
        return "cargarUsuarios.html";
    }

    @GetMapping("/crearEquipos")
    public String crearEquipos() {
        return "crearEquipos.html";
    }
}
