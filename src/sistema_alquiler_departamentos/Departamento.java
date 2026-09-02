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
    private int cant_habitaciones;
    private int cant_banios;
    private String estado;
    private int id;
    private float area_metros;
    private float precio;

    public Departamento(int cant_habitaciones, int cant_banios, String estado, int id, float area_metros, float precio) {
        this.cant_habitaciones = cant_habitaciones;
        this.cant_banios = cant_banios;
        this.estado = estado;
        this.id = id;
        this.area_metros = area_metros;
        this.precio = precio;
    }
    
    
    
    public int getCant_habitaciones() {
        return cant_habitaciones;
    }

    public int getCant_baños() {
        return cant_banios;
    }

    public String getEstado() {
        return estado;
    }

    public int getId() {
        return id;
    }

    public float getArea_metros() {
        return area_metros;
    }

    public float getPrecio() {
        return precio;
    }
    
    public void mostrarDatos() {
        System.out.println("DEPARTAMENTO ID: " + id + "| Cantidad de habitaciones: " + cant_habitaciones + "| Cantidad de banios: " + cant_banios + "| Metros Cuadrados: " + area_metros + "| Precio: " + precio);
    }
}
