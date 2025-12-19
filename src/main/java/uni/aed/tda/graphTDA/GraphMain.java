package uni.aed.tda.graphTDA;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import uni.aed.tda.graphTDA.DataGraph.DirectedGraph;
import uni.aed.tda.graphTDA.DataGraph.UndirectedGraph;
import uni.aed.tda.graphTDA.TopologicalSort.TopologicalSort;
import uni.aed.tda.graphTDA.recubrimiento.Kruskal;
import uni.aed.tda.graphTDA.recubrimiento.Prim;
import uni.aed.tda.graphTDA.shortestpath.CostPathPair;
import uni.aed.tda.graphTDA.shortestpath.Dijkstra;

public class GraphMain {
    private static Scanner scr;
    private static int opcion=1;
    private static String SEPARADOR="\n";
    private static String Rpta="S";
    public static void main(String[] args) {
         GraphMain g=new GraphMain();
         scr=new Scanner(System.in).useDelimiter(SEPARADOR);
         g.menu();
    }
    private void menu(){        
        try{
            do			
            {	
                System.out.print("Grafos"+SEPARADOR+
                "1.- Grafo No Dirigido "+SEPARADOR+                                            
                "2.- Dijkstra No Dirigido "+SEPARADOR+                                            
                "3.- Kruskal: Arbol de expansion minima "+SEPARADOR+                                            
                "4.- Prim: Arbol de expansion minima "+SEPARADOR+                                            
                "5.- Ordenamiento Topologico "+SEPARADOR+                                            
                "6.- Salir "+SEPARADOR+"Elija una opcion:");                
                opcion =scr.nextInt();            
                switch (opcion)
                {
                    case 1 -> {getUndirectedGraph();}                                                          
                    case 2 -> {getDijkstraUndirected();}                                                          
                    case 3 -> {getKruskal();}                                                          
                    case 4 -> {getPrim();}                                                          
                    case 5 -> {getTopologicalSort();}                                                          
                    default -> {break;}
                }	            
                System.out.print("Para continuar con las operaciones pulsa S; Para finalizar pulse N: ");
                Rpta=scr.next().toUpperCase();			
            }while(Rpta.equals("S")==true);	
        }catch(InputMismatchException ex) {
            System.out.println("Debe ingresar obligatoriamente un número entero como opcion elegida.");
        }
        catch(Exception e) {
            System.out.println(e.toString());
        }finally{            
            scr.close();
        }
    }
    
    private void getUndirectedGraph(){
        System.out.println("Grafo no dirigido");
        DataGraph.UndirectedGraph undirectedGraph=new UndirectedGraph();        
        System.out.println(undirectedGraph.graph.toString());
    }
    private void getDijkstraUndirected(){
        System.out.println("Dijkstra no dirigido");
        DataGraph.UndirectedGraph undirectedGraph=new UndirectedGraph();        
        Vertex<Integer> start = undirectedGraph.v1;
        Vertex<Integer> end = undirectedGraph.v5;
        Dijkstra dijkstra=new Dijkstra();
        Map<Vertex<Integer>,CostPathPair<Integer>> map
                =dijkstra.getShortestPaths(undirectedGraph.graph, start);
        
        CostPathPair<Integer> path;
        System.out.println("Dijkstra: Ruta mas corta, nodo inicial: "+start.getValue());
        for(Vertex<Integer> v: map.keySet()){
            System.out.println("v="+v.getValue());
            path=map.get(v);
            System.out.println("Dijkstra: Ruta mas corta, ruta: "+path);
        }        
    }
    private void getKruskal(){
        System.out.println("Algoritmo de Kruskal");
        DataGraph.UndirectedGraph undirectedGraph=new UndirectedGraph();          
        CostPathPair<Integer> path = Kruskal.getMinimumSpanningTree(undirectedGraph.graph);
        System.out.println("Kruskal: Arbol de Expansion minima: " + path);
    }
    private void getPrim(){
        System.out.println("Algoritmo de Prim");
        DataGraph.UndirectedGraph undirectedGraph=new UndirectedGraph();          
        Vertex<Integer> start=undirectedGraph.v1;
        CostPathPair<Integer> path = Prim.getMinimumSpanningTree(undirectedGraph.graph,start);
        System.out.println("Prim: Arbol de Expansion minima: " + path);
    }
    
    private void getTopologicalSort(){
        System.out.println("Algoritmo de Ordenamiento Topologico");
        DataGraph.DirectedGraph directedGraph=new DirectedGraph(); 
        TopologicalSort<Integer> topologicalSort = new TopologicalSort<>();
        List<Vertex<Integer>> result=topologicalSort.sort(directedGraph.graph);        
        System.out.println("Resultado del Ordenamiento Topologico:\n"+result.toString() );
    }
}
