package uni.aed.tda.queueTDA.hospital;

public class Paciente implements Comparable<Paciente> {
    private final String id;
    private final int prioridad;
    private final int tiempoAtencionNecesario;
    private int tiempoRestante;
    private int tiempoLlegada;
    private int tiempoInicioAtencion;
    private int tiempoFinalizacion;

    public Paciente(String id, int prioridad, int tiempoAtencionNecesario, int tiempoLlegada) {
        this.id = id;
        this.prioridad = prioridad;
        this.tiempoAtencionNecesario = tiempoAtencionNecesario;
        this.tiempoRestante = tiempoAtencionNecesario;
        this.tiempoLlegada = tiempoLlegada;
    }    

    public String getId() {
        return id;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public int getTiempoEspera() {
        return Math.abs(tiempoInicioAtencion - tiempoLlegada);
    }

    public int getTiempoAtencionNecesario() {
        return tiempoAtencionNecesario;
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }
    
    public void reducirTiempoAtencion() {
        if (tiempoRestante > 0) tiempoRestante--;
    }

    public boolean haFinalizado() {
        return tiempoRestante <= 0;
    }

    public void registrarInicioAtencion(int tiempo) {
        this.tiempoInicioAtencion = tiempo;
    }

    public void registrarFinalizacion(int tiempo) {
        this.tiempoFinalizacion = tiempo;
    }

    @Override
    public int compareTo(Paciente o) {
        // Prioridades mas bajas tienen mayor precedencia
        int comparacionPrioridad = Integer.compare(this.prioridad, o.prioridad);
        if (comparacionPrioridad != 0) return comparacionPrioridad;        
        // Si tienen la misma prioridad, el que llego primero tiene precedencia
        return Integer.compare(this.tiempoLlegada, o.tiempoLlegada);
    }
    
}
