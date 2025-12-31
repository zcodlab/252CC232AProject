/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.aed.tda.treeTDA.AVL.CrossReference;

/**
 *
 * @author zemr
 */
public class CrossReferenceMain {
    public static void main(String[] args) {
        String archivo = "src\\main\\java\\uni\\aed\\tda\\treeTDA\\AVL\\CrossReference\\texto.txt";
        CrossReferenceService service = new CrossReferenceService();
        CrossReferenceIO io = new CrossReferenceIO();

        int opcion;
        do {
            opcion = Integer.parseInt(io.leerPalabra(
                "Menu:\n1-Construir arbol AVL\n2-Anadir palabra\n3-Eliminar palabra\n4-Visualizar arbol AVL\n5-Salir"
            ));

            switch (opcion) {
                case 1:
                    if (service.leerArchivo(archivo)){
                        io.mostrarMensaje("Arbol construido OK");
                        io.mostrarMensaje("Recorrido alfabetico: " + service.obtenerInOrder());
                    }
                    else
                        io.mostrarMensaje("Error al leer archivo");
                    break;
                case 2:
                    service.agregarPalabra(io.leerPalabra("Ingrese palabra a anadir"));
                    io.mostrarMensaje("Palabra agregada OK");
                    break;
                case 3:
                    if (service.eliminarPalabra(io.leerPalabra("Ingrese palabra a eliminar")))
                        io.mostrarMensaje("Eliminada OK");
                    else
                        io.mostrarMensaje("No existe en el arbol");
                    break;
                case 4:
                    io.mostrarMensaje("Recorrido alfabetico: " + service.obtenerInOrder());
                    io.mostrarMensaje("Arbol: " + service.obtenerArbol());
                    break;
                default:
                    break;
            }

        } while (opcion != 5);
    }
}
