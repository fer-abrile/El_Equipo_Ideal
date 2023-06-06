package algoritmo;

import java.util.*;
import personas.Persona;

public class EquipoIdealThread implements Runnable {
    private List<Persona> personas;
    private List<String[]> incompatibilidades;
    private Map<String, Integer> roles;
    private List<Persona> equipoIdeal;

    public EquipoIdealThread(List<Persona> personas, List<String[]> incompatibilidades, Map<String, Integer> roles) {
        this.personas = personas;
        this.incompatibilidades = incompatibilidades;
        this.roles = roles;
        this.equipoIdeal = new ArrayList<>();
    }

    public List<Persona> getEquipoIdeal() {
        return equipoIdeal;
    }
    
    public int getRolesRequeridos(){
    	int cantidad =0;
    	for (Map.Entry<String, Integer> entry : roles.entrySet()) {
    		cantidad +=entry.getValue();
    	}
		return cantidad;
    	
    }
    @Override
    public void run() {
        equipoIdeal = EquipoIdeal.encontrarEquipoOptimo(personas, incompatibilidades, roles);
    }
}