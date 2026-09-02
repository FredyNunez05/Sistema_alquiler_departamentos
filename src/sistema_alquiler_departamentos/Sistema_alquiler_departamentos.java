/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistema_alquiler_departamentos;

/**
 *
 * @author LENOVO
 */
public class Sistema_alquiler_departamentos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // public Departamento(String cant_habitaciones, String cant_banios, String estado, int id, float area_metros, float precio)
        Departamento depa = new Departamento(4,2,"Disponible",234, 20.5f, 1900.0f);
        depa.mostrarDatos();
    }
    
}
