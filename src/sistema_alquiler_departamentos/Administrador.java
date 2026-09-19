/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema_alquiler_departamentos;

/**
 *
 * @author lovea
 */
public class Administrador extends Usuario {

    // Atributos propios del administrador
    private String cargo;
    private String usuario;
    private String contrasena;

    // Constructor vacío
    public Administrador() {
        super();
    }

    // Constructor con parámetros
    public Administrador(String nombres, String apellidos,
            String tipoDocumento, String nroDocumento,
            String telefono, String cargo,
            String usuario, String contrasena) {

        super(nombres, apellidos, tipoDocumento, nroDocumento, telefono);

        this.cargo = cargo;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    // Getters y Setters
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
