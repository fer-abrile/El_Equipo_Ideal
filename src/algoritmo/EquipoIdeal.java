package algoritmo;

import java.util.*;
import personas.Persona;

public class EquipoIdeal {
	
	public static List<Persona> encontrarEquipoOptimo(List<Persona> personas, List<String[]> incompatibilidades, Map<String, Integer> roles) {
		
        List<Persona> equipoOptimo = new ArrayList<>();
        List<Persona> equipoActual = new ArrayList<>();
        Map<String, Integer> rolActualCount = new HashMap<>();
        
        Collections.sort(personas, Comparator.comparingInt(Persona::getCalificacionHistorica).reversed());
        System.out.println("Se pasan por parametro: " +roles.size() + " roles " + roles.toString());
        System.out.println("\n");
        System.out.println("Se pasan por parametro: " +personas.size() + " Personas "+"\n" + personas.toString() +"\n");
        for (Persona persona : personas) {
            String rol = persona.getRol();
            rolActualCount.put(rol, 0);
        }
        System.out.println("Se crea rolActualCount: " +rolActualCount.size() + " roles requeridos " + rolActualCount.toString() +"\n");
        backtrack(personas, incompatibilidades, roles, equipoOptimo, equipoActual, rolActualCount, 0);
        
        Collections.sort(equipoOptimo, Comparator.comparing(Persona::getRol));
        
        return equipoOptimo;
    }

    private static void backtrack(List<Persona> personas, List<String[]> incompatibilidades, Map<String, Integer> cantidadesRoles, List<Persona> equipoOptimo, 
    								List<Persona> equipoActual, Map<String, Integer> rolActualCount, int indice) {
    	
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
}