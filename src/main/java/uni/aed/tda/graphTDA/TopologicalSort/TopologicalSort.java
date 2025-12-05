package uni.aed.tda.graphTDA.TopologicalSort;

import java.util.ArrayList;
import java.util.List;
import uni.aed.tda.graphTDA.Edge;
import uni.aed.tda.graphTDA.Graph;
import uni.aed.tda.graphTDA.Vertex;
/* Ordenamiento topologico aplica a grafos dirigidos
* considera las aristas salientes en el ordenamiento topologico
*/
public class TopologicalSort {
    public TopologicalSort() {
    }
    public static final List<Vertex<Integer>>
                sort(Graph<Integer> graph){
        if(graph==null)
           throw (new NullPointerException("El Grafo no puede ser Nulo"));         
        //Grafo debe ser dirigido
        if(graph.getType()!=Graph.TYPE.DIRECTED)
            throw (new IllegalArgumentException("El Grafo debe ser Dirigido"));            
        //Realizamos una copia del grafo que ingresa como parametro
        Graph<Integer> clone = new Graph<>(graph);
        //Declara una List de vertices clasificados
        List<Vertex<Integer>> sorted=new ArrayList<>();
        //Declara una List de vertices sin trayectorias(aristas) salientes
        List<Vertex<Integer>> sinTraySaliente=new ArrayList<>();
        //Declara una List de aristas
        List<Edge<Integer>> edges=new ArrayList<>();
        //descargarmos la coleccion de aristas en la edges
        edges.addAll(clone.getAllEdges());
        for(Vertex<Integer> v: clone.getAllVertices()){
            //si no tiene aristas o trayectorias salientes entonces
            //consignamos el vertice en la lista de vertices sinTrayectoriaSaliente
            if(v.getEdges().isEmpty())
                sinTraySaliente.add(v);
        }
        //mientras se tenga vertices sin trayectorias salientes
        while(!sinTraySaliente.isEmpty()){
            Vertex<Integer> current=sinTraySaliente.remove(0);
            //despues de removerlo de la lista de vertices sin trayectoria
            //lo agregamos a la lista de vertices clasificados
            sorted.add(current);
            //eliminar todas aristas del vertices removido
            int i=0;
            while(i<edges.size()){
                //recuperar la arista de la posicion
                Edge<Integer> e=edges.get(i);
                //determinar los vertices origen y destino de la arista
                Vertex<Integer> from= e.getFromVertex();
                Vertex<Integer> to= e.getToVertex();
                if(to.equals(current)){
                    edges.remove(e);
                    from.getEdges().remove(e);
                }else
                    i++;
                //si el numero de aristas del vertice origen(from) se quedo si aristas
                //salientes entonces los agregamos a la lista sinTraySaliente
                if(from.getEdges().isEmpty())
                    sinTraySaliente.add(from);
            }//end while interno
        }//end while externo
        if(!edges.isEmpty())
            return null;
        //retornamos la clasificacion topologica realizada
        return sorted;
    }
    
    
}
