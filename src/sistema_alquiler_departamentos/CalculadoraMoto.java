/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema_alquiler_departamentos;

/**
 *
 * @author YOSELY
 */
/**
 * Clase encargada de realizar cálculos relacionados
 * con los repuestos de una motocicleta.
 *
 * Demuestra sobrecarga de métodos y manejo de excepciones.
 *
 * @author TuNombre
 */
public class CalculadoraMoto {

    /**
     * Calcula el costo total de los repuestos.
     *
     * @param precio precio de un repuesto
     * @param cantidad cantidad de repuestos
     * @return costo total
     * @throws IllegalArgumentException si los valores son negativos
     */
    public double calcularCosto(double precio, int cantidad) {

        if (precio < 0 || cantidad < 0) {
            throw new IllegalArgumentException(
                    "El precio y la cantidad no pueden ser negativos."
            );
        }

        return precio * cantidad;
    }

    /**
     * Calcula el costo total aplicando un descuento.
     *
     * Este método es una sobrecarga de calcularCosto().
     *
     * @param precio precio de un repuesto
     * @param cantidad cantidad de repuestos
     * @param descuento porcentaje de descuento
     * @return costo total con descuento
     * @throws IllegalArgumentException si los valores no son válidos
     */
    public double calcularCosto(
            double precio,
            int cantidad,
            double descuento) {

        if (precio < 0 || cantidad < 0 ||
                descuento < 0 || descuento > 100) {

            throw new IllegalArgumentException(
                    "Los valores ingresados no son válidos."
            );
        }

        double subtotal = precio * cantidad;

        return subtotal - (subtotal * descuento / 100);
    }
}
