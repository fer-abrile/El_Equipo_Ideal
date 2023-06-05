package proyectos;

import java.util.HashMap;
import java.util.Map;

public class Proyecto {

	private String nombre;
	private static Map<String, Integer> rolesCantidades = new HashMap<>();
	
	public Proyecto (String _nombre){
		this.nombre = _nombre;
		
	}

	public Proyecto() {
		super();
	}
	
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Map<String, Integer> getRolesCantidades() {
		return rolesCantidades;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[Nombre = ");
		builder.append(nombre);
		builder.append(", Rol = ");
		builder.append(rolesCantidades.toString());
		return builder.toString();
	}
}
