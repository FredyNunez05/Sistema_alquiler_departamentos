/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema_alquiler_departamentos;

/**
 *
 * @author LENOVO
 */
public class Departamento {

    // Atributos
    private int nroDpto;
    private int piso;
    private int nroHabitaciones;
    private int nroBanos;
    private double precioMensual;
    private String estado;
    private String descripcion;

    // Constructor vacío
    public Departamento() {
    }

    // Constructor con parámetros
    public Departamento(int nroDpto, int piso,
                        int nroHabitaciones, int nroBanos,
                        double precioMensual, String estado,
                        String descripcion) {

        this.nroDpto = nroDpto;
        this.piso = piso;
        this.nroHabitaciones = nroHabitaciones;
        this.nroBanos = nroBanos;
        this.precioMensual = precioMensual;
        this.estado = estado;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public int getNroDpto() {
        return nroDpto;
    }

    public void setNroDpto(int nroDpto) {
        this.nroDpto = nroDpto;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public int getNroHabitaciones() {
        return nroHabitaciones;
    }

    public void setNroHabitaciones(int nroHabitaciones) {
        this.nroHabitaciones = nroHabitaciones;
    }

    public int getNroBanos() {
        return nroBanos;
    }

    public void setNroBanos(int nroBanos) {
        this.nroBanos = nroBanos;
    }

    public double getPrecioMensual() {
        return precioMensual;
    }

    public void setPrecioMensual(double precioMensual) {
        this.precioMensual = precioMensual;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}