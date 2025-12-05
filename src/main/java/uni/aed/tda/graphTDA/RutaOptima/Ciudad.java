package uni.aed.tda.graphTDA.RutaOptima;

/**
 * Clase que representa una ciudad en el sistema de transporte .
 * Implementa Comparable para permitir ordenamiento y comparación entre ciudades.
 */
public class Ciudad implements Comparable<Ciudad> {
    private Integer id;
    private String nombre;
    
    /**
     * Constructor principal
     * @param id Identificador único de la ciudad
     * @param nombre Nombre de la ciudad
     */
    public Ciudad(Integer id, String nombre) {
        if (id == null || nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("ID y nombre de ciudad no pueden ser nulos o vacíos");
        }
        this.id = id;
        this.nombre = nombre.trim();
    }
    
    /**
     * Constructor por nombre (para búsquedas)
     * @param nombre Nombre de la ciudad
     */
    public Ciudad(String nombre) {
        this(0, nombre);
    }
    
    /**
     * Constructor copia
     * @param ciudad Ciudad a copiar
     */
    public Ciudad(Ciudad ciudad) {
        this(ciudad.id, ciudad.nombre);
    }
    
    // Getters
    public Integer getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    // Setters
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre.trim();
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Ciudad)) return false;
        
        Ciudad ciudad = (Ciudad) obj;
        return nombre.equalsIgnoreCase(ciudad.nombre);
    }    
    
    @Override
    public int compareTo(Ciudad otra) {
        return this.nombre.compareToIgnoreCase(otra.nombre);
    }
    
    @Override
    public String toString() {
        return nombre;
    }
}