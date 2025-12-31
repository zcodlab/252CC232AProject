/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.aed.tda.graphTDA.Interbloqueo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import uni.aed.tda.graphTDA.Graph;
import uni.aed.tda.graphTDA.Vertex;

/**
 *
 * @author zemr
 */
public class GrafoEspera<T extends Comparable<T>> extends Graph<T> {
    private Set<T> recursosBloqueados = new HashSet<>();
    private Map<T, T> propietarioRecurso = new HashMap<>();

    public GrafoEspera(TYPE tipo) {
        super(tipo);
    }

    public void agregarTransaccion(T nombre) {
        if (searchVertex(nombre) == null) {
            addVertex(nombre);
        }
    }

    public boolean bloquearRecurso(T transaccion, T recurso) {
        if (recursosBloqueados.contains(recurso)) {
            T propietario = propietarioRecurso.get(recurso);
            if (propietario != null && !propietario.equals(transaccion)) {
                addEdge(transaccion, propietario, 1);
            }
            return false;
        }
        recursosBloqueados.add(recurso);
        propietarioRecurso.put(recurso, transaccion);
        return true;
    }

    public void liberarRecursosDe(T transaccion) {
        List<T> liberar = new ArrayList<>();
        for (T r : recursosBloqueados) {
            if (transaccion.equals(propietarioRecurso.get(r))) {
                liberar.add(r);
            }
        }
        for (T r : liberar) {
            recursosBloqueados.remove(r);
            propietarioRecurso.remove(r);
        }
        removeVertex(transaccion);
    }

    public boolean detectarInterbloqueo(List<T> ciclo) {
        Set<T> visitado = new HashSet<>();
        Set<T> pila = new HashSet<>();

        for (Vertex<T> v : super.getAllVertices()) {
            T key = v.getValue();
            if (dfsCiclo(key, visitado, pila, ciclo))
                return true;
        }
        return false;
    }

    private boolean dfsCiclo(T actual, Set<T> visitado, Set<T> pila, List<T> ciclo) {
        if (pila.contains(actual)) {
            ciclo.add(actual);
            return true;
        }

        if (visitado.contains(actual))
            return false;

        visitado.add(actual);
        pila.add(actual);

        for (T vecino : super.searchAdyacentes(actual)) {
            if (dfsVecino(vecino, visitado, pila, ciclo)) {
                ciclo.add(actual);
                return true;
            }
        }

        pila.remove(actual);
        return false;
    }
    private boolean dfsVecino(T vecino, Set<T> visitado, Set<T> pila, List<T> ciclo) {
        return dfsCiclo(vecino, visitado, pila, ciclo);
    }        
    
    public Set<T> obtenerRecursosBloqueados() {
        return recursosBloqueados;
    }

    public Map<T, T> obtenerPropietarios() {
        return propietarioRecurso;
    }
    
}
