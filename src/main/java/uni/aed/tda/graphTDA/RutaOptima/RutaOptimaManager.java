package uni.aed.tda.graphTDA.RutaOptima;

import uni.aed.tda.graphTDA.Graph;
import uni.aed.tda.graphTDA.Vertex;
import uni.aed.tda.graphTDA.Edge;
import uni.aed.tda.graphTDA.shortestpath.Dijkstra;
import uni.aed.tda.graphTDA.shortestpath.CostPathPair;

import java.util.*;

public class RutaOptimaManager {
    private final Graph<Ciudad> grafo;
    private final Map<String, Vertex<Ciudad>> mapaVertices;
    private final Dijkstra<Ciudad> dijkstra;

    public RutaOptimaManager() {
        this.grafo = new Graph<>(Graph.TYPE.UNDIRECTED);
        this.mapaVertices = new HashMap<>();
        this.dijkstra = new Dijkstra<>();
    }

    /**
     * Agrega una ciudad al grafo (usado por FileManager)
     * @param id
     * @param nombre
     */
    public void agregarCiudadPublica(int id, String nombre) {
        Ciudad ciudad = new Ciudad(id, nombre);
        Vertex<Ciudad> vertice = new Vertex<>(ciudad);
        grafo.getAllVertices().add(vertice);
        mapaVertices.put(nombre.toLowerCase(), vertice);
    }

    /**
     * Procesa una ruta a partir de los datos del archivo (usado por FileManager)
     * @param partes
     */
    public void procesarRuta(String[] partes) throws NumberFormatException {
        if (partes.length != 3) return;

        String origen = partes[0].trim();
        String destino = partes[1].trim();
        int distancia = Integer.parseInt(partes[2].trim());

        Vertex<Ciudad> vOrigen = mapaVertices.get(origen.toLowerCase());
        Vertex<Ciudad> vDestino = mapaVertices.get(destino.toLowerCase());

        if (vOrigen != null && vDestino != null) {
            agregarRutaInterna(vOrigen, vDestino, distancia);
        }
    }

    private void agregarRutaInterna(Vertex<Ciudad> origen, Vertex<Ciudad> destino, int distancia) {
        Edge<Ciudad> arista = new Edge<>(distancia, origen, destino);
        Edge<Ciudad> aristaInversa = new Edge<>(distancia, destino, origen);

        origen.addEdge(arista);
        destino.addEdge(aristaInversa);

        grafo.getAllEdges().add(arista);
        grafo.getAllEdges().add(aristaInversa);
    }

    public Vertex<Ciudad> buscarCiudad(String nombre) {
        return mapaVertices.get(nombre.toLowerCase());
    }

    public CostPathPair<Ciudad> calcularRutaOptima(String ciudadOrigen, String ciudadDestino) {
        Vertex<Ciudad> origen = buscarCiudad(ciudadOrigen);
        Vertex<Ciudad> destino = buscarCiudad(ciudadDestino);

        if (origen == null || destino == null) {
            throw new IllegalArgumentException("Una o ambas ciudades no existen en el sistema");
        }
        return dijkstra.getShortestPaths(grafo, origen, destino);        
    }

    public Map<Vertex<Ciudad>, CostPathPair<Ciudad>> calcularRutasDesdeOrigen(String ciudadOrigen) {
        Vertex<Ciudad> origen = buscarCiudad(ciudadOrigen);

        if (origen == null) {
            throw new IllegalArgumentException("La ciudad origen no existe en el sistema");
        }

        return dijkstra.getShortestPaths(grafo, origen);
    }

    public boolean agregarRuta(String ciudadOrigen, String ciudadDestino, int distancia) {
        Vertex<Ciudad> origen = buscarCiudad(ciudadOrigen);
        Vertex<Ciudad> destino = buscarCiudad(ciudadDestino);

        if (origen == null || destino == null || distancia < 0 || existeRuta(origen, destino)) {
            return false;
        }

        agregarRutaInterna(origen, destino, distancia);
        return true;
    }

    public boolean eliminarRuta(String ciudadOrigen, String ciudadDestino) {
        Vertex<Ciudad> origen = buscarCiudad(ciudadOrigen);
        Vertex<Ciudad> destino = buscarCiudad(ciudadDestino);

        if (origen == null || destino == null) {
            return false;
        }

        boolean eliminado = false;

        Iterator<Edge<Ciudad>> iterOrigen = origen.getEdges().iterator();
        while (iterOrigen.hasNext()) {
            Edge<Ciudad> arista = iterOrigen.next();
            if (arista.getToVertex().equals(destino)) {
                iterOrigen.remove();
                grafo.getAllEdges().remove(arista);
                eliminado = true;
                break;
            }
        }

        Iterator<Edge<Ciudad>> iterDestino = destino.getEdges().iterator();
        while (iterDestino.hasNext()) {
            Edge<Ciudad> arista = iterDestino.next();
            if (arista.getToVertex().equals(origen)) {
                iterDestino.remove();
                grafo.getAllEdges().remove(arista);
                eliminado = true;
                break;
            }
        }

        return eliminado;
    }

    public boolean modificarDistanciaRuta(String ciudadOrigen, String ciudadDestino, int nuevaDistancia) {
        if (nuevaDistancia < 0) return false;

        Vertex<Ciudad> origen = buscarCiudad(ciudadOrigen);
        Vertex<Ciudad> destino = buscarCiudad(ciudadDestino);

        if (origen == null || destino == null) return false;

        boolean modificado = false;

        for (Edge<Ciudad> arista : origen.getEdges()) {
            if (arista.getToVertex().equals(destino)) {
                arista.setCost(nuevaDistancia);
                modificado = true;
                break;
            }
        }

        for (Edge<Ciudad> arista : destino.getEdges()) {
            if (arista.getToVertex().equals(origen)) {
                arista.setCost(nuevaDistancia);
                modificado = true;
                break;
            }
        }

        return modificado;
    }

    private boolean existeRuta(Vertex<Ciudad> origen, Vertex<Ciudad> destino) {
        for (Edge<Ciudad> arista : origen.getEdges()) {
            if (arista.getToVertex().equals(destino)) {
                return true;
            }
        }
        return false;
    }

    public List<String> obtenerCiudades() {
        List<String> ciudades = new ArrayList<>();
        for (Vertex<Ciudad> vertice : grafo.getAllVertices()) {
            ciudades.add(vertice.getValue().getNombre());
        }
        Collections.sort(ciudades);
        return ciudades;
    }

    @Override
    public String toString() {
        return grafo.toString();
    }

    public String obtenerEstadisticas() {
        StringBuilder stats = new StringBuilder();
        stats.append("=== ESTADISTICAS DEL SISTEMA ===\n");
        stats.append("Numero de ciudades: ").append(grafo.getAllVertices().size()).append("\n");
        stats.append("Numero total de rutas: ").append(grafo.getAllEdges().size() / 2).append("\n");
        return stats.toString();
    }
}
