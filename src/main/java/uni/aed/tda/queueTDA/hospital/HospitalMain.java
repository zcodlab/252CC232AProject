package uni.aed.tda.queueTDA.hospital;
import java.util.Scanner;

public class HospitalMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        SimuladorClinica simulador = new SimuladorClinica();

        int opcion;
        do {
            System.out.println("\n===== MENU SIMULADOR CLINICA =====");
            System.out.println("1. Registrar tiempo a simular");
            System.out.println("2. Simular atencion medica");
            System.out.println("3. Obtener tiempo de espera promedio (general)");
            System.out.println("4. Obtener tiempo promedio y maximo por prioridad");
            System.out.println("5. Eliminar simulacion");
            System.out.println("6. Visualizar simulacion");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Minutos a simular: ");
                    int minutos = sc.nextInt();
                    System.out.print("Cantidad de medicos: ");
                    int medicos = sc.nextInt();
                    simulador.registrarTiempoSimulacion(minutos, medicos);
                    System.out.println("Configuracion registrada.");
                }
                case 2 -> {
                    simulador.simular();
                    System.out.println("Simulacion completada.");
                }
                case 3 -> {
                    double prom = simulador.getEstadisticas().obtenerPromedioGeneral();
                    System.out.printf("Tiempo promedio general de espera: %.2f min\n", prom);
                }
                case 4 -> System.out.println(simulador.getEstadisticas().mostrarEstadisticas());
                case 5 -> {
                    simulador.eliminarSimulacion();
                    System.out.println("Simulacion eliminada.");
                }
                case 6 -> System.out.println(simulador.visualizarSimulacion());
                case 0 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opcion no valida.");
            }

        } while (opcion != 0);
        sc.close();
    }
}
