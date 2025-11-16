package uni.aed.tda.queueTDA.hospital;

import uni.aed.tda.arraylistTDA.ArrayListTDA;
import uni.aed.tda.listTDA.ListTDA;

public class Estadisticas {
    public static final int LIM_SUP_PRIORIDAD = 5;

    // Arreglo de listas: índice = prioridad
    private final ListTDA<Integer>[] tiemposEsperaPorPrioridad;
    
    public Estadisticas() {
        tiemposEsperaPorPrioridad = new ArrayListTDA[LIM_SUP_PRIORIDAD + 1];
        for (int i = 1; i <= LIM_SUP_PRIORIDAD; i++) {
            tiemposEsperaPorPrioridad[i] = new ArrayListTDA<>();
        }
    }

    // Registrar tiempo de espera según prioridad
    public void registrarPaciente(Paciente p) {
        int prioridad = p.getPrioridad();
        if (prioridad >= 1 && prioridad <= LIM_SUP_PRIORIDAD)
            tiemposEsperaPorPrioridad[prioridad].add(p.getTiempoEspera());        
    }

    // Promedio general
    public double obtenerPromedioGeneral() {
        int total = 0, cantidad = 0;
        for (int i = 1; i <= LIM_SUP_PRIORIDAD; i++) {
            for (Object t : tiemposEsperaPorPrioridad[i].toArray()) {                
                total += (Integer)t;
                cantidad++;
            }
        }
        return cantidad == 0 ? 0 : Math.round((total / (double) cantidad) * 100.0) / 100.0;
    }

    // Promedio por prioridad (2 decimales)
    public double obtenerPromedioPorPrioridad(int prioridad) {
        if (prioridad < 1 || prioridad > LIM_SUP_PRIORIDAD) return 0;
        ListTDA<Integer> lista = tiemposEsperaPorPrioridad[prioridad];
        if (lista.isEmpty()) return 0;
        int suma = 0;
        for (Object t : lista.toArray())
            suma += (Integer)t;
        double promedio = (double) suma / lista.size();
        return Math.round(promedio * 100.0) / 100.0;
    }

    // Máximo por prioridad
    public int obtenerMaximoPorPrioridad(int prioridad) {
        if (prioridad < 1 || prioridad > LIM_SUP_PRIORIDAD) return 0;
        ListTDA<Integer> lista = tiemposEsperaPorPrioridad[prioridad];
        if (lista.isEmpty()) return 0;
        int max = lista.get(0);
        for (Object t : lista.toArray()) {
            if ((Integer)t > max)
                max = (Integer)t;            
        }
        return max;
    }

    // Mostrar estadísticas completas
    public String mostrarEstadisticas() {
        StringBuilder str = new StringBuilder();
        str.append("\n=== Estadisticas de Espera ===\n");
        str.append("Promedio general: ").append(obtenerPromedioGeneral()).append(" min\n\n");

        for (int i = 1; i <= LIM_SUP_PRIORIDAD; i++) {
            str.append("Prioridad ").append(i).append(":\t")
                    .append("Promedio = ").append(obtenerPromedioPorPrioridad(i)).append(" min\t")
                    .append("Maximo = ").append(obtenerMaximoPorPrioridad(i)).append(" min\n");
        }

        return str.toString();
    }
}
