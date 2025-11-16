package uni.aed.tda.queueTDA.hospital;

import java.util.Random;
import uni.aed.tda.queueTDA.PriorityQueueTDA;
import uni.aed.tda.queueTDA.QueueTDA;
import uni.aed.tda.arraylistTDA.ArrayListTDA;
import uni.aed.tda.listTDA.IteratorTDA;
import uni.aed.tda.listTDA.ListTDA;
import uni.aed.tda.stackTDA.LinkedStackTDA;
import uni.aed.tda.stackTDA.StackTDA;

public class SimuladorClinica {
    public static final int LIM_SUP_PRIORIDAD=5;   
    public static final int LIM_SUP_TATENCION=10;   
    private int minutosSimulacion;    
    private ListTDA<Medico> medicos;
    private QueueTDA<Paciente> colaEspera;    
    private StackTDA<Paciente> pilaAtendidos;    
    private Estadisticas estadisticas;
    private final Random random = new Random();
    // Mantiene registro de pacientes en proceso al finalizar la simulación
    private ListTDA<Paciente> pacientesEnProceso;

    public SimuladorClinica() {
        this.colaEspera = new PriorityQueueTDA<>();
        this.pilaAtendidos = new LinkedStackTDA<>();   
        this.pacientesEnProceso = new ArrayListTDA<>();        
        this.estadisticas = new Estadisticas();
    }

    // a) Registrar tiempo a simular
    public void registrarTiempoSimulacion(int minutos, int cantidadMedicos) {
        this.minutosSimulacion = minutos;
        this.medicos = new ArrayListTDA<>(cantidadMedicos);        
        for (int i = 0; i < cantidadMedicos; i++)
            medicos.add(new Medico());            
    }

    // b) Simular atención médica
    public void simular() {
        int idPaciente = 1;
        for (int minuto = 1; minuto <= minutosSimulacion; minuto++) {
            int prioridad = random.nextInt(LIM_SUP_PRIORIDAD) + 1;
            int tiempoAtencion = random.nextInt(LIM_SUP_TATENCION) + 1;
            Paciente nuevo = new Paciente("P" + idPaciente++, prioridad, tiempoAtencion, minuto);            
            colaEspera.enqueue(nuevo);            

            IteratorTDA<Medico> it = medicos.iterador();
            while(it.hasNext()){
                Medico m = it.next();
                if (m.estaLibre() && !colaEspera.isEmpty()){
                    Paciente p=colaEspera.dequeue();
                    m.asignarPaciente(p, minuto);             
                    estadisticas.registrarPaciente(p);
                }
                Paciente terminado = m.atender(minuto);
                if (terminado != null)
                    pilaAtendidos.push(terminado);      
            }                
        }
        // Guardar pacientes que quedaron siendo atendidos
        pacientesEnProceso.clear();
        IteratorTDA<Medico> it = medicos.iterador();
        while(it.hasNext()){
            Medico m = it.next();
            if (!m.estaLibre())
                pacientesEnProceso.add(m.getPacienteActual());                            
        }
        
        //Cargar los tiempos de espera de los pacientes que se quedaron en cola de espera        
        if(!colaEspera.isEmpty()){
            for (Object o : colaEspera.toArray())
                estadisticas.registrarPaciente((Paciente) o);            
        }
    }

    public Estadisticas getEstadisticas() {
        return estadisticas;
    }

    // e) Eliminar simulacion
    public void eliminarSimulacion() {
        colaEspera = new PriorityQueueTDA<>();
        pilaAtendidos = new LinkedStackTDA<>();
        pacientesEnProceso = new ArrayListTDA<>();
        estadisticas = new Estadisticas();
    }
    
    // f) Visualizar simulacion
    public String visualizarSimulacion() {
        StringBuilder str=new StringBuilder();
        str.append("=== PACIENTES ATENDIDOS ===").append("\n");        
        if (pilaAtendidos.isEmpty())
            str.append("(Ninguno)").append("\n");            
        else {
            while (!pilaAtendidos.isEmpty()) {
                Paciente p = pilaAtendidos.pop();
                str.append("Id:").append(p.getId()).append("\t")
                        .append("Prioridad:").append(p.getPrioridad()).append("\t")
                        .append("tiempoAtencion:").append(p.getTiempoAtencionNecesario()).append("\t")
                        .append("tiempoRestante:").append(p.getTiempoRestante()).append("\t")
                        .append("Espera:").append(p.getTiempoEspera()).append("\n");
            }
        }
        str.append("=== PACIENTES EN PROCESO DE ATENCION ===").append("\n");          
        if (pacientesEnProceso.isEmpty())
            str.append("(Ninguno)").append("\n");            
        else {
            for (Object paciente : pacientesEnProceso.toArray()) {
                Paciente p=(Paciente)paciente;
                str.append("Id:").append(p.getId()).append("\t")
                        .append("Prioridad:").append(p.getPrioridad()).append("\t")
                        .append("tiempoAtencion:").append(p.getTiempoAtencionNecesario()).append("\t")
                        .append("tiempoRestante:").append(p.getTiempoRestante()).append("\t")
                        .append("Espera:").append(p.getTiempoEspera()).append("\n");
            }
        }

        str.append("=== PACIENTES QUE QUEDARON EN COLA ===").append("\n");                  
        if (colaEspera.isEmpty())
            str.append("(Ninguno)").append("\n");             
        else {
            while (!colaEspera.isEmpty()) {
                Paciente p = colaEspera.dequeue();
                str.append("Id:").append(p.getId()).append("\t")
                        .append("Prioridad:").append(p.getPrioridad()).append("\t")
                        .append("tiempoAtencion:").append(p.getTiempoAtencionNecesario()).append("\t")
                        .append("tiempoRestante:").append(p.getTiempoRestante()).append("\t")
                        .append("Espera:").append(p.getTiempoEspera()).append("\n");
            }
        }
        return str.toString();
    }        
}
