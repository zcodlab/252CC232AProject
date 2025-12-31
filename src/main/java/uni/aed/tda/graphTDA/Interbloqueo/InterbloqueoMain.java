/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.aed.tda.graphTDA.Interbloqueo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import uni.aed.tda.graphTDA.Graph;

/**
 *
 * @author zemr
 */
public class InterbloqueoMain {
    public static void main(String[] args) {

        // Caso 1: SIN interbloqueo
        System.out.println("=== CASO SIN INTERBLOQUEO ===");
        ejecutarCaso(Arrays.asList(
            "T1,write,A1",
            "T2,read,A2",
            "T1,end",
            "T3,write,A3",
            "T2,end",
            "T3,end"
        ));

        // Caso 2: CON interbloqueo
        System.out.println("=== CASO CON INTERBLOQUEO ===");
        ejecutarCaso(Arrays.asList(
            "T1,write,A1",
            "T2,write,A2",
            "T3,write,A3",
            "T1,read,A2",
            "T2,read,A3",
            "T3,read,A1",
            "end"
        ));
    }

    private static void ejecutarCaso(List<String> entradas) {
        GrafoEspera<String> grafo = new GrafoEspera<>(Graph.TYPE.DIRECTED);

        for (String linea : entradas) {
            if (linea.equals("end")) break;

            String[] t = linea.split(",");
            String nombreT = t[0];
            String operacion = t[1];
            String recurso = (t.length == 3 ? t[2] : null);

            grafo.agregarTransaccion(nombreT);

            if (operacion.equals("read") || operacion.equals("write")) {
                grafo.bloquearRecurso(nombreT, recurso);
            }

            if (operacion.equals("end")) {
                grafo.liberarRecursosDe(nombreT);
            }
        }

        List<String> ciclo = new ArrayList<>();
        if (grafo.detectarInterbloqueo(ciclo)) {
            System.out.println("Interbloqueo detectado: " + ciclo);
        } else {
            System.out.println("No hay interbloqueo");
        }

        System.out.println();
    }

}
