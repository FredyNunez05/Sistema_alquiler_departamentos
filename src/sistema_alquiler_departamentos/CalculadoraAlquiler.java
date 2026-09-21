/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema_alquiler_departamentos;

/**
 * Clase encargada de realizar cálculos relacionados
 * con el costo del alquiler de departamentos.
 *
 * Demuestra sobrecarga de métodos y manejo de excepciones.
 *
 * @author YOSELY
 */
public class CalculadoraAlquiler {

    /**
     * Calcula el costo total del alquiler por un número de meses.
     *
     * @param precioMensual Precio del alquiler por mes.
     * @param meses Cantidad de meses de contrato.
     * @return Costo total del alquiler.
     * @throws IllegalArgumentException si el precio o los meses son menores o iguales a cero.
     */
    public double calcularCosto(double precioMensual, int meses) {

        if (precioMensual <= 0 || meses <= 0) {
            throw new IllegalArgumentException(
                    "El precio del alquiler y la cantidad de meses deben ser mayores a cero."
            );
        }

        return precioMensual * meses;
    }

    /**
     * Calcula el costo total del alquiler aplicando un descuento o costo de mantenimiento.
     *
     * Este método es una sobrecarga de calcularCosto().
     *
     * @param precioMensual Precio del alquiler por mes.
     * @param meses Cantidad de meses de contrato.
     * @param descuentoPorcentaje Porcentaje de descuento por contrato largo (0 a 100).
     * @return Costo total del alquiler con descuento aplicado.
     * @throws IllegalArgumentException si los valores ingresados son inválidos.
     */
    public double calcularCosto(double precioMensual, int meses, double descuentoPorcentaje) {

        if (precioMensual <= 0 || meses <= 0 || descuentoPorcentaje < 0 || descuentoPorcentaje > 100) {
            throw new IllegalArgumentException(
                    "Los datos del alquiler o el porcentaje de descuento no son válidos."
            );
        }

        double subtotal = precioMensual * meses;
        return subtotal - (subtotal * descuentoPorcentaje / 100);
    }
}