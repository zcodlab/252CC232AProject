package uni.aed.tda.graphTDA.recubrimiento;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import uni.aed.tda.graphTDA.Edge;
import uni.aed.tda.graphTDA.Graph;
import uni.aed.tda.graphTDA.Vertex;
import uni.aed.tda.graphTDA.shortestpath.CostPathPair;

public class Prim {
    public Prim() {
    }
    public static CostPathPair<Integer>
    getMinimumSpanningTree(Graph<Integer> graph, Vertex<Integer> start){
        if(graph==null)
           throw (new NullPointerException("El Grafo no puede ser Nulo")); 
        //Kruskal funciona con grafos no dirigidos
        if(graph.getType()==Graph.TYPE.DIRECTED)
            throw (new IllegalArgumentException("El Grafo debe ser No Dirigido")); 
        int cost=0;
        //instanciamos un conjunto(set) de vertices no vistados
        Set<Vertex<Integer>> unvisited = new HashSet<>();
        //descargamos todos los vertices del grafo en el conjunto
        unvisited.addAll(graph.getAllVertices());
        //remover el vertice inicial
        unvisited.remove(start);
        //instancia una lista de aristas que conformaran el arbor de expansion minima
        List<Edge<Integer>> path = new ArrayList<>();
        //declarar cola prioritaria
        Queue<Edge<Integer>> edgesAvailable=new PriorityQueue<>();
        //declaramos un objeto vertice y lo inicializamos con el vertice inicial
        Vertex<Integer> vertex=start;
        //mientras el conjunto no este vacio
        while(!unvisited.isEmpty()){
            for(Edge<Integer> e: vertex.getEdges()){
                //verificar si el vertice adyacente a vertex no esta visitado
                if(unvisited.contains(e.getToVertex()))
                    edgesAvailable.add(e);
            }
            //remueve de la cola prioritaria la arista con el costo mas bajo
            Edge<Integer> e=edgesAvailable.remove();
            cost+=e.getCost();
            path.add(e);
            //actualiza vertex con su vertice adyacente
            vertex=e.getToVertex();
            //remueve de la lista de los no visitados el vertice adyacente
            unvisited.remove(vertex);                
        }
        //retornamos un objeto CostPathPair con el costo la ruta(aristas)
        return (new CostPathPair<>(cost,path)); 
    }
}
