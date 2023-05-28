package personas;

public class Persona {
    private String nombre;
    private String rol;
    private int calificacionHistorica;

    public Persona(String nombre, String rol, int calificacionHistorica) {
        this.nombre = nombre;
        this.rol = rol;
        if(calificacionHistorica > 5 || calificacionHistorica < 1)
        	throw new IllegalArgumentException("La calificacion historica debe ser mayor que 0 y menor o igual que 5.");
        this.calificacionHistorica = calificacionHistorica;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRol() {
        return rol;
    }
    
    public int getCalificacionHistorica() {
        return calificacionHistorica;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[Nombre = ");
		builder.append(nombre);
		builder.append(", Rol = ");
		builder.append(rol);
		builder.append(", Calificacion Historica = ");
		builder.append(calificacionHistorica);
		builder.append("]");
		return builder.toString();
	}
}