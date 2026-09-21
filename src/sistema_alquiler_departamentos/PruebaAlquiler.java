/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema_alquiler_departamentos;

/**
 * Clase de prueba para verificar la funcionalidad de CalculadoraAlquiler.
 *
 * @author YOSELY
 */
public class PruebaAlquiler {

    public static void main(String[] args) {

        CalculadoraAlquiler calculadora = new CalculadoraAlquiler();

        try {
            // 1. Uso del primer método (Calculo básico: S/ 1200 por 6 meses)
            double costo1 = calculadora.calcularCosto(1200, 6);
            System.out.println("Costo total de alquiler (6 meses): S/ " + costo1);

            // 2. Uso del método sobrecargado (Con 10% de descuento por contrato de 12 meses)
            double costo2 = calculadora.calcularCosto(1200, 12, 10);
            System.out.println("Costo total con descuento (12 meses): S/ " + costo2);

            // 3. Provocamos una excepción con un valor inválido (-1500)
            double costo3 = calculadora.calcularCosto(-1500, 6);
            System.out.println("Costo: S/ " + costo3);

        } catch (IllegalArgumentException e) {
            System.out.println("Se produjo un error en la validación: " + e.getMessage());
        } finally {
            System.out.println("Proceso de cálculo finalizado.");
        }
    }
}