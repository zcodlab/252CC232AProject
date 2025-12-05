package uni.aed.tda.graphTDA.RutaOptima;

import java.io.File;
import java.util.Scanner;

public class FileManager {
    private final String archivoEntrada;

    public FileManager(String archivoEntrada) {
        this.archivoEntrada = archivoEntrada;
    }

    /**
     * Lee el archivo y carga ciudades y rutas usando el manager
     * @param manager RutaOptimaManager al que se delegan las operaciones
     * @throws Exception si hay errores en el formato o archivo
     */
    public void cargarDatos(RutaOptimaManager manager) throws Exception {
        try (Scanner scanner = new Scanner(new File(archivoEntrada))) {
            boolean leyendoCiudades = true;

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine().trim();
                if (linea.isEmpty()) continue;

                String[] partes = linea.split(",");

                if (partes.length == 2 && leyendoCiudades) {
                    Integer id = Integer.valueOf(partes[0].trim());
                    String nombre = partes[1].trim();
                    manager.agregarCiudadPublica(id, nombre);
                } else if (partes.length == 3) {
                    leyendoCiudades = false;
                    manager.procesarRuta(partes); 
                }
            }
        }
    }
    public boolean archivoExiste() {
        File archivo = new File(archivoEntrada);
        return archivo.exists() && archivo.isFile();
    }
}

