/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema_alquiler_departamentos;

import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class Usuario {
    // Atributos
    private String nombres;
    private String apellidos;
    private String tipoDocumento;
    private String nroDocumento;
    private String telefono;

    // Constructor vacío
    public Usuario() {
    }

    // Constructor con parámetros
    public Usuario(String nombres, String apellidos, String tipoDocumento,
                   String nroDocumento, String telefono) {

        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipoDocumento = tipoDocumento;
        this.nroDocumento = nroDocumento;
        this.telefono = telefono;
    }

    // Getters y Setters
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    // Sobrecarga de métodos
    public String mostrarDatos() {
    return nombres + " " + apellidos;
    }

    public String mostrarDatos(boolean incluirDocumento) {
    if (incluirDocumento) {
        return nombres + " " + apellidos + " - " + nroDocumento;
    } else {
        return nombres + " " + apellidos;
    }
  }
    // Manejo de errores
    public void establecerTelefono(String nuevoTelefono) {
    try {
        if (nuevoTelefono.length() != 9) {
            throw new Exception("El teléfono debe tener 9 dígitos.");
        }

        telefono = nuevoTelefono;
        System.out.println("Teléfono registrado correctamente.");

    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
  }
    // Colecciones
     public static void agregarUsuario(ArrayList<Usuario> lista, Usuario usuario) {
    lista.add(usuario);
   }
}