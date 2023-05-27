package personas;

public class Persona {
	String nombre;
	String apellido;
	String rol;
	int clasif_hisotirca;
	
	public Persona(String _nombre, String _apellido, String _rol, int calificacion) {
		this.nombre = _nombre;
		this.apellido = _apellido;
		this.rol = _rol;
		this.clasif_hisotirca = calificacion;
	}
}
