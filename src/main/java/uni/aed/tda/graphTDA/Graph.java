package uni.aed.tda.graphTDA;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Graph<T extends Comparable<T>> {
    public enum TYPE{DIRECTED,UNDIRECTED}
    private List<Vertex<T>> allVertices=new ArrayList<>();//lista de vertices
    private List<Edge<T>> allEdges=new ArrayList<>();//lista de aristas
    private TYPE type=TYPE.UNDIRECTED;//predeterminamos el tipo de grafo
    //constructores
    public Graph() {
    }    
    public Graph(TYPE type) {
        this.type=type;
    }
    public Graph(Graph<T> g) {
        this.type=g.type;
        //descargando vertices en el grafo actual, uno a uno
        for(Vertex<T> v: g.getAllVertices() )
            this.allVertices.add(new Vertex<T>(v));            
        //descargando aristas en el grafo actual, uno a uno
        for(Vertex<T> v: g.getAllVertices() ){
            for(Edge<T> e: v.getEdges() )
                this.allEdges.add(e);
        }  
    }    
    public Graph(Collection<Vertex<T>> vertices,Collection<Edge<T>> edges) {
        this(TYPE.UNDIRECTED,vertices,edges);
    }    
    
    public Graph(TYPE type,Collection<Vertex<T>> vertices,Collection<Edge<T>> edges) {
        this(type);
        this.allVertices.addAll(vertices);
        this.allEdges.addAll(edges);
        //recorremos la coleccion de aristas
        for(Edge<T> e: edges){
            Vertex<T> from=e.getFromVertex();
            Vertex<T> to=e.getToVertex();
            //si no existen los vertices origen y destino en la lista de vertices actual, continuamos evaluando el siguiente
            //si existen entonces los vinculamos
            if(!this.allVertices.contains(from) || !this.allVertices.contains(to)) continue;
            from.addEdge(e);
            if(this.type==TYPE.UNDIRECTED){
              Edge<T> reciproca=new Edge<T>(e.getCost(),to,from); //arista inversa por ser grafo no dirigido o bidireccional
              to.addEdge(reciproca);
              this.allEdges.add(reciproca);
            }
        }
    }

    public List<Vertex<T>> getAllVertices() {
        return allVertices;
    }

    public List<Edge<T>> getAllEdges() {
        return allEdges;
    }

    public TYPE getType() {
        return type;
    }
    
    protected void addVertex(T key) {
        allVertices.add(new Vertex<>(key));
    }

    protected void addVertex(Vertex<T> v) {
        allVertices.add(v);
    }
    
    public boolean removeVertex(T key) {
        Vertex<T> v = searchVertex(key);
        if (v == null) return false;

        // 1. Eliminar todas las aristas donde participa el vertice
        List<Edge<T>> eliminar = new ArrayList<>();
        for (Edge<T> e : allEdges) {
            if (e.getFromVertex().getValue().compareTo(key) == 0 ||
                e.getToVertex().getValue().compareTo(key) == 0) {
                eliminar.add(e);
            }
        }

        for (Edge<T> e : eliminar) {
            allEdges.remove(e);
            e.getFromVertex().removeEdge(e);
            e.getToVertex().removeEdge(e);
        }

        // 2. Eliminar el vertice del grafo
        allVertices.remove(v);
        return true;
    }

   
    public Vertex<T> searchVertex(T key) {
        for (Vertex<T> v : allVertices) {
            if (v.getValue().compareTo(key) == 0)
                return v;            
        }
        return null;
    }

    public void addEdge(T desde, T hacia, int costo) {
        Vertex<T> v1 = searchVertex(desde);
        Vertex<T> v2 = searchVertex(hacia);
        if (v1 == null) {
            v1 = new Vertex<>(desde);
            addVertex(v1);
        }
        if (v2 == null) {
            v2 = new Vertex<>(hacia);
            addVertex(v2);
        }
        Edge<T> e = new Edge<>(costo, v1, v2);
        allEdges.add(e);
        v1.addEdge(e);
    }
    
    public List<T> searchAdyacentes(T key) {
        List<T> adyacentes = new ArrayList<>();
        Vertex<T> v = searchVertex(key);
        if (v == null) return adyacentes;

        for (Edge<T> e : v.getEdges()) {
            adyacentes.add(e.getToVertex().getValue());
        }
        return adyacentes;
    }

    @Override
    public String toString() {
        StringBuilder builder=new StringBuilder();
        for(Vertex<T> v: allVertices )
            builder.append(v.toString());
        return builder.toString();
    }
}