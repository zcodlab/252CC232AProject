package uni.aed.tda.treeTDA.experimentalBST;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import uni.aed.tda.treeTDA.BstTDA;

/**
 * Clase para ejecutar experimentos con árboles binarios de búsqueda.
 * Solucionario 4PC
 */
public class ExperimentoBST {

    public static final int ALEATORIO_LIMITE = 1000; // Limite superior para valores aleatorios
    public static final int NCOMBINACIONES = 8;     // Número de combinaciones
    public static final int NOPERACIONES = 1;      // Número de operaciones a realizar        
    private static final int[] ALTURAS = {2,4,6,8}; //8, 16, 32 y 64 Alturas objetivo para los árboles
    private BstTDA<Integer> bstMgr;
    private List<Resultado> tabla=new LinkedList<>();    
    
    public static void main(String[] args) {
        ExperimentoBST exp=new ExperimentoBST();
        exp.Menu();
    }

    private void Menu() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        boolean salir = false;          
        tabla.clear();
        while (!salir) {
            System.out.println("\nMenu de Operaciones");            
            System.out.println("1. Crear BST aleatorio de altura h y Alternar inserciones y eliminaciones");
            System.out.println("2. Visualizar BST y parametros");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opcion: ");
            int opcion = scanner.nextInt();
            switch (opcion) {                
                case 1:
                    tabla.clear();
                    ejecutarCombinaciones();
                    break;
                case 2:
                    visualizarResultados(bstMgr);
                    break;
                case 3:
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        }
    }
    
    public void ejecutarCombinaciones() {
        Random random1 = new Random();
        Random random2 = new Random();        
        tabla.clear();
        for (int altura : ALTURAS) {
            System.out.println("\n--- Ejecutando combinaciones para altura: " + altura + " ---");
            BstTDA<Integer> bstMgr = new BstTDA<>();
            crearArbolCompletoAleatorio(bstMgr, altura);                 
            // Realizar combinaciones de operaciones
            alternarOperacionesAleatorias(bstMgr,random1,random2);
            // Obtener parametros finales            
            actualizarReporte(bstMgr,altura);
            System.out.println(bstMgr.toString());
        }        
        visualizarTabla();
    }

    public void crearArbolCompletoAleatorio(BstTDA<Integer> bstMgr, int hRequerida) {
        long totalNodos = (long) Math.pow(2, hRequerida) - 1; // Número total de nodos en un árbol completo        
        for (int i = 0; i < totalNodos; i++) {            
            int valor= random(i,ALEATORIO_LIMITE);
            bstMgr.add(valor);
        }        
        actualizarReporte(bstMgr,hRequerida);
        visualizarResultados(bstMgr);
    }
    
    private int random(int low, int high) {
        return (int) Math.floor(Math.random() * (high - low + 1)) + low;
    }
    
    private void alternarOperacionesAleatorias(BstTDA<Integer> bstMgr,Random random1,Random random2 ) {
        // Alternar inserciones y eliminaciones aleatorias
        // Realizar combinaciones de operaciones
        for (int combinacion = 0; combinacion < NCOMBINACIONES; combinacion++) {
            System.out.printf("\n--- Combinacion %d ---\n", combinacion + 1);
            for (int i = 0; i < NOPERACIONES; i++) {
                if (i % 2 == 0) { // Inserción
                    int valor = (combinacion % 2 == 0) ? random1.nextInt(ALEATORIO_LIMITE) : random2.nextInt(ALEATORIO_LIMITE);
                    bstMgr.add(valor);
                    System.out.printf("Insertado: %d%n", valor);
                } else { // Eliminación
                    int valorEliminar = (combinacion % 2 == 0) ? random2.nextInt(ALEATORIO_LIMITE) : random1.nextInt(ALEATORIO_LIMITE);                        
                    if(random2.nextBoolean()){
                        bstMgr.deleteByCopying(valorEliminar);
                        System.out.printf("Eliminado Simetrico: %d%n", valorEliminar);
                    }
                    else{
                        bstMgr.deleteByCopyingAsymmetric(valorEliminar);                        
                        System.out.printf("Eliminado Asimetrico: %d%n", valorEliminar);
                    }
                }
            }
        }
    }    

    public void visualizarResultados(BstTDA<Integer> bstMgr) {
        // Visualización de la estructura del árbol
        if(bstMgr==null) return;
        System.out.println("\nEstructura del bstMgr:");
        System.out.println(bstMgr.toString());   
        visualizarTabla();
    }
    private void actualizarReporte(BstTDA<Integer> bstMgr, int hRequerida){ 
        tabla.add(new Resultado(bstMgr.getRoot(),hRequerida,bstMgr.calculateHeight(),bstMgr.calcularIPL(), bstMgr.countNodes(), bstMgr.isBalanced()));        
    }
    
    private void visualizarTabla(){        
        for(Resultado r:tabla){
            System.out.println("bstMgr de h inicial:" + r.getInitialHeight());
            r.getRaiz().toString();
        }
         // Imprime los encabezados de la tabla para los resultados
        System.out.printf("%-12s %-12s %-12s %-12s %-15s\n",
                "h-requerida","h-generada", "IPL", "n-nodos", "arbol-balanceado");        

        // Imprimir los resultados en formato de tabla
        for(Resultado r:tabla)
            System.out.printf("%-12d %-12d %-12d %-12d %-15s\n",
        r.gethRequerida(), r.getInitialHeight(), r.getInitialIPL(), r.getInitialNodeCount(), r.isInitialBalance());
    }
}

