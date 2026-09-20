/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema_alquiler_departamentos;

/**
 *
 * @author USER
 */
public class Alquiler {
    // Atributos
    private Inquilino inquilino;
    private Departamento departamento;
    private String fechaInicio;
    private String fechaFin;
    private int mesesAlquiler;
    private double monto;
    private Pago pago;

    // Constructor vacío
    public Alquiler() {
    }

    // Constructor con parámetros
    public Alquiler(Inquilino inquilino, Departamento departamento,
                    String fechaInicio, String fechaFin,
                    int mesesAlquiler, double monto, Pago pago) {

        this.inquilino = inquilino;
        this.departamento = departamento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.mesesAlquiler = mesesAlquiler;
        this.monto = monto;
        this.pago = pago;
    }

    // Getters y Setters
    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getMesesAlquiler() {
        return mesesAlquiler;
    }

    public void setMesesAlquiler(int mesesAlquiler) {
        this.mesesAlquiler = mesesAlquiler;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }
    public double calcularMonto(int meses) {
        try {
            if (meses <= 0) {
                throw new IllegalArgumentException("El número de meses debe ser mayor a cero.");
            }
            this.mesesAlquiler = meses;
            this.monto = meses * 500.0;
            return this.monto;
        } catch (IllegalArgumentException e) {
            System.err.println("Error en el cálculo: " + e.getMessage());
            return 0.0;
        }
    }

    public double calcularMonto(int meses, double descuento) {
        try {
            if (meses <= 0) {
                throw new IllegalArgumentException("El número de meses debe ser mayor a cero.");
            }
            if (descuento < 0.0 || descuento > 1.0) {
                throw new IllegalArgumentException("El descuento debe estar entre 0.0 y 1.0.");
            }
            this.mesesAlquiler = meses;
            double subtotal = meses * 500.0;
            this.monto = subtotal - (subtotal * descuento);
            return this.monto;
        } catch (IllegalArgumentException e) {
            System.err.println("Error en el cálculo con descuento: " + e.getMessage());
            return 0.0;
        }
    }
    
}
