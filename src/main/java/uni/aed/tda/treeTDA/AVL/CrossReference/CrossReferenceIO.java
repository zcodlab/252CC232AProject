/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.aed.tda.treeTDA.AVL.CrossReference;

import java.util.Scanner;

/**
 *
 * @author zemr
 */
public class CrossReferenceIO {
    private Scanner sc = new Scanner(System.in).useDelimiter("\n");

    public String leerPalabra(String mensaje) {
        System.out.println(mensaje);
        return sc.next();
    }

    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }
}
