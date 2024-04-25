import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombres;
    private String documento;
    private String correo;
    private String escuela;
    private Integer rol;
    private String contrasena;
    private boolean estado; // Cambiar el tipo de dato a booleano

    public Usuario(Long id, String nombres, String documento, String correo, String escuela,
            Integer rol, String contrasena, boolean estado) {
        this.id = id;
        this.nombres = nombres;
        this.documento = documento;
        this.correo = correo;
        this.escuela = escuela;
        this.rol = rol;
        this.contrasena = contrasena;
        this.estado = estado;
    }

    public Usuario() {
        this.rol = 1; // Rol por defecto
        this.estado = true; // Estado por defecto a true
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
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

    public Integer getRol() {
        return rol;
    }

    public void setRol(Integer rol) {
        this.rol = rol;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
