/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author USER
 */
public class Inquilino extends Usuario {

    // Constructor vacío
    public Inquilino() {
    }

    // Constructor con parámetros
    public Inquilino(String nombres, String apellidos,
                     String tipoDocumento, String nroDocumento,
                     String telefono) {

        super(nombres, apellidos, tipoDocumento, nroDocumento, telefono);
    }
}

