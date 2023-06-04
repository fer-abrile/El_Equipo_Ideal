package personas;
import java.util.ArrayList;
import java.util.List;

public class Persona {
	public static Object personasIncomaptibles;
   
	private String nombre;
    private String rol;
    private int calificacionHistorica;
    private List<String[]> personasIncompatibles =  new ArrayList<>();

    public Persona(String nombre, String rol, int calificacionHistorica) {
    	if(nombre.length() < 1)
        	throw new IllegalArgumentException("La persona debe tener un nombre valido.");
    	this.nombre = nombre;
        if(rol.length() < 1)
        	throw new IllegalArgumentException("La persona debe tener un rol valido.");
        this.rol = rol;
        if(calificacionHistorica > 5 || calificacionHistorica < 1)
        	throw new IllegalArgumentException("La calificacion historica debe ser mayor que 0 y menor o igual que 5.");
        this.calificacionHistorica = calificacionHistorica;
    }

    public Persona() {
		super();
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

	public List<String[]> getPersonasIncompatibles() {
		return personasIncompatibles;
	}

	public void setPersonasIncompatibles(List<String[]> personasIncompatibles) {
		this.personasIncompatibles = personasIncompatibles;
	}
	

	public String getNombre() {
        return nombre;
    }

    public String getRol() {
        return rol;
    }
    
    public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public void setCalificacionHistorica(int calificacionHistorica) {
		this.calificacionHistorica = calificacionHistorica;
	}

   
	public int getCalificacionHistorica() {
        return calificacionHistorica;
    }
}