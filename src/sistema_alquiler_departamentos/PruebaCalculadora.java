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
 * Clase utilizada para probar CalculadoraMoto.
 *
 * @author TuNombre
 */
public class PruebaCalculadora {

    public static void main(String[] args) {

        CalculadoraMoto calculadora = new CalculadoraMoto();

        try {

            // Uso del primer método.
            double costo1 = calculadora.calcularCosto(1500, 2);

            System.out.println(
                    "Costo total: S/ " + costo1
            );

            // Uso del método sobrecargado.
            double costo2 = calculadora.calcularCosto(
                    1500,
                    2,
                    10
            );

            System.out.println(
                    "Costo con descuento: S/ " + costo2
            );

            // Provocamos una excepción.
            double costo3 = calculadora.calcularCosto(
                    -500,
                    2
            );

            System.out.println(
                    "Costo: S/ " + costo3
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Se produjo un error: "
                    + e.getMessage()
            );

        } finally {

            System.out.println(
                    "Proceso finalizado."
            );
        }
    }
}

