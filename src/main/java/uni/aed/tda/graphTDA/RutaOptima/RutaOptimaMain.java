package uni.aed.tda.graphTDA.RutaOptima;

import java.io.FileNotFoundException;
import uni.aed.tda.graphTDA.shortestpath.CostPathPair;
import uni.aed.tda.graphTDA.Edge;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.List;

/**
 * Clase principal que implementa el menú de opciones para el sistema
 * de rutas óptimas en ciudades.
 */
public class RutaOptimaMain {
    private final FileManager fileManager;
    private final RutaOptimaManager manager;
    private final Scanner scanner;
    
    // Ruta del archivo de datos
    private static final String ARCHIVO_DATOS = "src/main/java/uni/aed/tda/graphTDA/RutaOptima/rutas.txt";
    
    /**
     * Constructor que inicializa el manager y el scanner
     */
    public RutaOptimaMain() {
        this.fileManager = new FileManager(ARCHIVO_DATOS);
        this.manager = new RutaOptimaManager();
        this.scanner = new Scanner(System.in).useDelimiter("\n");
    }
    
    /**
     * Método principal que inicia la aplicación
     * @param args
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        RutaOptimaMain app = new RutaOptimaMain();
        app.mostrarMenu();
    }
    
    /**
     * Muestra el menú principal y maneja la selección del usuario
     * @throws java.lang.Exception
     */
    public void mostrarMenu() throws Exception {
        int opcion;
        System.out.println("=== PROGRAMA DE RUTAS OPTIMAS ===");
        
        do {
            imprimirMenuOpciones();
            opcion = obtenerOpcionMenu();
            
            switch (opcion) {
                case 1 -> cargaInicial();
                case 2 -> consultarRutaOptima();
                case 3 -> agregarNuevaRuta();
                case 4 -> eliminarRuta();
                case 5 -> modificarDistanciaRuta();
                case 6 -> visualizarGrafo();                
                case 7 -> System.out.println("Saliendo...");
                default -> System.out.println("Opcion no valida. Seleccione una opcion del 0 al 7.");
            }            
            if (opcion != 7)
                esperarUsuario();            
        } while (opcion != 7);        
        scanner.close();
    }
    
    /**
     * Imprime las opciones del menú
     */
    private void imprimirMenuOpciones() {
        System.out.println("\n========================= MENU PRINCIPAL =========================");
        System.out.println("1. Carga inicial desde archivo");
        System.out.println("2. Consultar ruta mas rapida entre dos ciudades");
        System.out.println("3. Anadir nueva ruta");
        System.out.println("4. Eliminar ruta existente");
        System.out.println("5. Modificar distancia de ruta");
        System.out.println("6. Visualizar grafo completo");
        System.out.println("7. Salir");
        System.out.println("================================================================");
        System.out.print("Seleccione una opcion: ");
    }
    
    /**
     * Obtiene la opción seleccionada por el usuario con validación
     */
    private int obtenerOpcionMenu() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Limpiar el buffer
            return -1; // Opción inválida
        } finally {
            scanner.nextLine(); // Consumir el salto de línea
        }
    }
    
    /**
     * Opción 1: Carga inicial del sistema desde archivo
     */
    private void cargaInicial() throws Exception {
        System.out.println("\n=== CARGA INICIAL DEL SISTEMA ===");
        
        if (!fileManager.archivoExiste()) {
            System.out.println("Error: No se encontro el archivo de datos: " + ARCHIVO_DATOS);
            return;
        }
        
        System.out.println("Cargando datos desde: " + ARCHIVO_DATOS);
        try {
            fileManager.cargarDatos(manager);            
            System.out.println("¡Datos cargados exitosamente!");
            System.out.println(manager.obtenerEstadisticas());
            System.out.println("Ciudades disponibles:");
            List<String> ciudades = manager.obtenerCiudades();
            for (int i = 0; i < ciudades.size(); i++)
                System.out.printf("   %2d. %s\n", i + 1, ciudades.get(i));
                
            
        } catch (FileNotFoundException e) {
            System.err.println("No se encontro el archivo de entrada. Verifica la ruta." + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error al procesar distancia en ruta." + e.getMessage());
        } catch (Exception e) {
            System.err.println("Ocurrio un error inesperado al cargar los datos: " + e.getMessage());           
        }
    }
    
    /**
     * Opción 2: Consultar ruta más rápida entre dos ciudades
     */
    private void consultarRutaOptima() {
        System.out.println("\n=== CONSULTAR RUTA MAS RAPIDA ===");
        
        System.out.print("Ingrese la ciudad de origen: ");
        String origen = scanner.nextLine().trim();
        
        System.out.print("Ingrese la ciudad de destino: ");
        String destino = scanner.nextLine().trim();
        
        try {
            CostPathPair<Ciudad> resultado = manager.calcularRutaOptima(origen, destino);
            
            if (resultado != null && resultado.getCost() != Integer.MAX_VALUE) {
                System.out.println("\n¡RUTA OPTIMA ENCONTRADA!");
                System.out.println("Distancia total: " + resultado.getCost() + " km");
                System.out.println("Ruta detallada:");
                
                if (resultado.getPath().isEmpty()) {
                    System.out.println("   " + origen + " (ciudad de origen = destino)");
                } else {
                    // Mostrar ciudad de origen
                    if (!resultado.getPath().isEmpty()) {
                        System.out.println("   INICIO: " + resultado.getPath().get(0).getFromVertex().getValue().getNombre());
                    }
                    
                    // Mostrar cada segmento de la ruta
                    for (int i = 0; i < resultado.getPath().size(); i++) {
                        Edge<Ciudad> arista = resultado.getPath().get(i);
                        System.out.printf("   -> %d km\n", arista.getCost());
                        System.out.printf("   %s %s\n", 
                                         i == resultado.getPath().size() - 1 ? "DESTINO:" : "->",
                                         arista.getToVertex().getValue().getNombre());
                    }
                }
            } else {
                System.out.println("No se encontro una ruta entre " + origen + " y " + destino);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado al calcular la ruta: " + e.getMessage());
        }
    }
    
    /**
     * Opción 3: Añadir nueva ruta
     */
    private void agregarNuevaRuta() {
        System.out.println("\n=== ANADIR NUEVA RUTA ===");
        
        System.out.print("Ingrese la ciudad de origen: ");
        String origen = scanner.nextLine().trim();
        
        System.out.print("Ingrese la ciudad de destino: ");
        String destino = scanner.nextLine().trim();
        
        System.out.print("Ingrese la distancia en kilometros: ");
        try {
            int distancia = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea
            
            if (manager.agregarRuta(origen, destino, distancia)) {
                System.out.println("¡Ruta anadida exitosamente!");
                System.out.printf("   %s <-> %s (%d km)\n", origen, destino, distancia);
            } else {
                System.out.println("No se pudo anadir la ruta. Verifique que:");
                System.out.println("   • Las ciudades existan en el sistema");
                System.out.println("   • La ruta no exista previamente");
                System.out.println("   • La distancia sea un valor positivo");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: La distancia debe ser un numero entero.");
            scanner.nextLine(); // Limpiar buffer
        }
    }
    
    /**
     * Opción 4: Eliminar ruta existente
     */
    private void eliminarRuta() {
        System.out.println("\n=== ELIMINAR RUTA EXISTENTE ===");
        
        System.out.print("Ingrese la ciudad de origen: ");
        String origen = scanner.nextLine().trim();
        
        System.out.print("Ingrese la ciudad de destino: ");
        String destino = scanner.nextLine().trim();
        
        if (manager.eliminarRuta(origen, destino)) {
            System.out.println("¡Ruta eliminada exitosamente!");
            System.out.printf("   Eliminada: %s <-> %s\n", origen, destino);
        } else {
            System.out.println("No se pudo eliminar la ruta. Verifique que:");
            System.out.println("   • Las ciudades existan en el sistema");
            System.out.println("   • La ruta exista actualmente");
        }
    }
    
    /**
     * Opción 5: Modificar distancia de una ruta
     */
    private void modificarDistanciaRuta() {
        System.out.println("\n=== MODIFICAR DISTANCIA DE RUTA ===");
        
        System.out.print("Ingrese la ciudad de origen: ");
        String origen = scanner.nextLine().trim();
        
        System.out.print("Ingrese la ciudad de destino: ");
        String destino = scanner.nextLine().trim();
        
        System.out.print("Ingrese la nueva distancia en kilometros: ");
        try {
            int nuevaDistancia = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea
            
            if (manager.modificarDistanciaRuta(origen, destino, nuevaDistancia)) {
                System.out.println("¡Distancia modificada exitosamente!");
                System.out.printf("   %s <-> %s: nueva distancia = %d km\n", origen, destino, nuevaDistancia);
            } else {
                System.out.println("No se pudo modificar la distancia. Verifique que:");
                System.out.println("   • Las ciudades existan en el sistema");
                System.out.println("   • La ruta exista actualmente");
                System.out.println("   • La nueva distancia sea un valor positivo");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: La distancia debe ser un numero entero.");
            scanner.nextLine(); // Limpiar buffer
        }
    }
    
    /**
     * Opción 6: Visualizar grafo completo
     */
    private void visualizarGrafo() {
        System.out.println("\n=== VISUALIZACION DEL GRAFO ===");
        System.out.println(manager.obtenerEstadisticas());
        System.out.println("ESTRUCTURA COMPLETA DEL GRAFO:");
        System.out.println(manager.toString());
    }
    /**
     * Pausa la ejecución esperando que el usuario presione Enter
     */
    private void esperarUsuario() {
        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
}