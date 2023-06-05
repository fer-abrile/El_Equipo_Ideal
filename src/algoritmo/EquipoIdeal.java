package algoritmo;

import java.util.*;
import personas.Persona;

public class EquipoIdeal {
	public static List<Persona> encontrarEquipoOptimo(List<Persona> personas, List<String[]> incompatibilidades, Map<String, Integer> roles) {
		int rolesRequeridos = generarRolesRequeridos(roles);
		int personasCreadas = personas.size();
		List<Persona> equipoOptimo = new ArrayList<>();
        List<Persona> equipoActual = new ArrayList<>();
        Map<String, Integer> rolActualCount = new HashMap<>();
		
		if (personasCreadas < rolesRequeridos)
		{
			return equipoOptimo;
		}       
        
        Collections.sort(personas, Comparator.comparingInt(Persona::getCalificacionHistorica).reversed());
        
        for (Persona persona : personas) {
            String rol = persona.getRol();
            rolActualCount.put(rol, 0);
        }

        backtrack(personas, incompatibilidades, roles, equipoOptimo, equipoActual, rolActualCount, 0);
        
        Collections.sort(equipoOptimo, new Comparator<Persona>() {
        	@Override
        	public int compare(Persona persona1, Persona persona2) {
        		return valorRol(persona1.getRol()) - valorRol(persona2.getRol());
        	}        	
        	private int valorRol(String rol) {
        		switch(rol) {
        			case "Líder de proyecto":
        				return 1;
        			case "Arquitecto":
        				return 2;
        			case "Programador":
        				return 3;
        			default:
        				return 4;
        		}
        	}
        	
        });
        if(rolesRequeridos != equipoOptimo.size()) {
        	List<Persona> equipoOptimo2 = new ArrayList<>();
        	return equipoOptimo2;
        }
        return equipoOptimo;
    }

    private static void backtrack(List<Persona> personas, List<String[]> incompatibilidades, Map<String, Integer> cantidadesRoles, List<Persona> equipoOptimo, List<Persona> equipoActual, Map<String, Integer> rolActualCount, int indice) {
        if (indice == personas.size()) {
            if (equipoActual.size() > equipoOptimo.size()) {
                equipoOptimo.clear();
                equipoOptimo.addAll(equipoActual);
            }
            return;
        }

        Persona personaActual = personas.get(indice);
        String rolActual = personaActual.getRol();
        int countActual = rolActualCount.get(rolActual);
        int cantidadRol = cantidadesRoles.get(rolActual);

        if (countActual < cantidadRol) {
            boolean esCompatible = true;

            for (Persona persona : equipoActual) {
                if (sonIncompatibles(personaActual, persona, incompatibilidades)) {
                    esCompatible = false;
                    break;
                }
            }

            if (esCompatible) {
                equipoActual.add(personaActual);
                rolActualCount.put(rolActual, countActual + 1);

                backtrack(personas, incompatibilidades, cantidadesRoles, equipoOptimo, equipoActual, rolActualCount, indice + 1);

                equipoActual.remove(personaActual);
                rolActualCount.put(rolActual, countActual);
            }
        }

        backtrack(personas, incompatibilidades, cantidadesRoles, equipoOptimo, equipoActual, rolActualCount, indice + 1);
    }

    private static boolean sonIncompatibles(Persona persona1, Persona persona2, List<String[]> incompatibilidades) {
        for (String[] parIncompatible : incompatibilidades) {
            if ((parIncompatible[0].equals(persona1.getNombre()) && parIncompatible[1].equals(persona2.getNombre())) ||
                    (parIncompatible[0].equals(persona2.getNombre()) && parIncompatible[1].equals(persona1.getNombre()))) {
                return true;
            }
        }
        return false;
    }
    
    public static int generarRolesRequeridos(Map<String, Integer> roles){
    	int cantidad =0;
    	for (Map.Entry<String, Integer> entry : roles.entrySet()) {
    		cantidad +=entry.getValue();
    	}
		return cantidad;
    	
    }
}