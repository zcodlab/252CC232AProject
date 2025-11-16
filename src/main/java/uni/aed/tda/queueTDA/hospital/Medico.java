package uni.aed.tda.queueTDA.hospital;

public class Medico {
    private Paciente pacienteActual;

    public Paciente getPacienteActual() {
        return pacienteActual;
    }

    public boolean estaLibre() {
        return pacienteActual == null;
    }

    public void asignarPaciente(Paciente p, int tiempoActual) {
        pacienteActual = p;
        p.registrarInicioAtencion(tiempoActual);
    }

    public Paciente atender(int tiempoActual) {
        if (pacienteActual == null) return null;

        pacienteActual.reducirTiempoAtencion();
        if (pacienteActual.haFinalizado()) {
            pacienteActual.registrarFinalizacion(tiempoActual);
            Paciente terminado = pacienteActual;
            pacienteActual = null;
            return terminado;
        }
        return null;
    }
}
