/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.aed.tda.treeTDA.AVL.CrossReference;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import uni.aed.tda.treeTDA.AVL.AvlTree;

/**
 *
 * @author zemr
 */
public class CrossReferenceService {
    private AvlTree<Palabra> arbol = new AvlTree<>();
    private int numTotalLineas = 1;

    public boolean leerArchivo(String ruta) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(ruta));
            String linea;
            int numeroLinea = 1;
            while ((linea = br.readLine()) != null) {
                procesarLinea(linea, numeroLinea);
                numeroLinea++;
            }
            numTotalLineas = numeroLinea;
            br.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private void procesarLinea(String linea, int numeroLinea) {
        String[] palabras = linea.split(" ");
        for (String s : palabras) {
            if (s.isEmpty()) continue;
            Palabra nuevo = new Palabra(s);
            if (arbol.search(nuevo) == 0) {
                nuevo.agregarNumLinea(numeroLinea);
                arbol.add(nuevo);
            } else {
                arbol.searchNode(nuevo).getKey().agregarNumLinea(numeroLinea);
            }
        }
    }

    public boolean agregarPalabra(String palabra) {
        Palabra p = new Palabra(palabra);
        if (arbol.search(p) == 0) {
            p.agregarNumLinea(numTotalLineas);
            arbol.add(p);
            return true;
        } else {
            arbol.searchNode(p).getKey().agregarNumLinea(numTotalLineas);
            return true;
        }
    }

    public boolean eliminarPalabra(String palabra) {
        Palabra p = new Palabra(palabra);
        if (arbol.search(p) == 0) return false;
        if (arbol.delete(p).equals(String.valueOf(-1))) return false;
        return true;
    }

    public String obtenerInOrder() {
        return arbol.InOrder();
    }

    public String obtenerArbol() {
        return arbol.toString();
    }
}

