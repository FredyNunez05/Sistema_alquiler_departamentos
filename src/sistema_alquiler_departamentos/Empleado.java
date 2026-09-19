/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema_alquiler_departamentos;

/**
 *
 * @author USER
 */
public class Empleado extends Usuario {

    // Atributos
    private String direccion;
    private String cargo;
    private double sueldo;
    private String fechaInicio;
    private String fechaTermino;

    // Constructor vacío
    public Empleado() {
    }

    // Constructor con parámetros
    public Empleado(String nombres, String apellidos,
                    String tipoDocumento, String nroDocumento,
                    String telefono, String direccion,
                    String cargo, double sueldo,
                    String fechaInicio, String fechaTermino) {

        super(nombres, apellidos, tipoDocumento, nroDocumento, telefono);

        this.direccion = direccion;
        this.cargo = cargo;
        this.sueldo = sueldo;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
    }

    // Getters y Setters
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(String fechaTermino) {
        this.fechaTermino = fechaTermino;
    }
}

    

  
