package com.example.juegoar.Controlador;

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

}
