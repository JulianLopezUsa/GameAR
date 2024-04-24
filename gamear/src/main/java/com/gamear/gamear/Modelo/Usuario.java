package com.gamear.gamear.Modelo;

import jakarta.persistence.*;


@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String documento;
    private String correo;
    private String escuela;
    private Rol rol;
    private Integer estado;
    private String numeroCelular;
    private String contrasena;

    public Usuario(Long id, String nombre, String documento, String correo, String escuela,
            Rol rol, Integer estado,
            String numeroCelular, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.documento = documento;
        this.correo = correo;
        this.escuela = escuela;
        this.rol = rol;
        this.numeroCelular = numeroCelular;
        this.contrasena = contrasena;
    }

    public Usuario() {
        this.rol = Rol.ESTUDIANTE; // Rol por defecto
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }


    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contraseña) {
        this.contrasena = contraseña;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

}
