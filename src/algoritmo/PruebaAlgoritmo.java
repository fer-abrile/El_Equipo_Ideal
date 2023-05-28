package algoritmo;

import java.util.*;
import personas.Persona;

public class PruebaAlgoritmo {
	public static void main(String[] args) {
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona("Ana", "Líder de proyecto", 3));
		personas.add(new Persona("Santi", "Líder de proyecto", 4));
		personas.add(new Persona("Norbert", "Líder de proyecto", 5));
		personas.add(new Persona("Dani", "Líder de proyecto", 4));

		personas.add(new Persona("Carlos", "Arquitecto", 3));
		personas.add(new Persona("Pepe", "Arquitecto", 2));
		personas.add(new Persona("Robert", "Arquitecto", 3));

		personas.add(new Persona("Juan", "Programador", 4));
		personas.add(new Persona("Rodrigo", "Programador", 5));
		personas.add(new Persona("Elias", "Programador", 4));
		personas.add(new Persona("Leo", "Programador", 5));
		personas.add(new Persona("Tomas", "Programador", 5));

		personas.add(new Persona("Laura", "Tester", 2));
		personas.add(new Persona("Jose", "Tester", 5));
		personas.add(new Persona("Martin", "Tester", 3));
		personas.add(new Persona("Jorge", "Tester", 1));
		personas.add(new Persona("María", "Tester", 2));

        List<String[]> incompatibilidades = new ArrayList<>();
        incompatibilidades.add(new String[]{"Laura", "Martin"});
        incompatibilidades.add(new String[]{"Elias", "Carlos"});
        
        Map<String, Integer> rolesCantidades = new HashMap<>();
        rolesCantidades.put("Líder de proyecto", 1);
        rolesCantidades.put("Arquitecto", 2);
        rolesCantidades.put("Programador", 4);
        rolesCantidades.put("Tester", 5);
        
        EquipoIdealThread equipoIdealThread = new EquipoIdealThread(personas, incompatibilidades, rolesCantidades);
        Thread thread = new Thread(equipoIdealThread);
        thread.start();

        
        try {
            thread.join();
            List<Persona> equipoIdeal = equipoIdealThread.getEquipoIdeal();
            
            if (equipoIdeal.isEmpty()) {
                System.out.println("No se encontro un equipo compatible.");
            } else {
                System.out.println("Equipo ideal encontrado:");
                for (Persona persona : equipoIdeal) {
                    System.out.println(persona);
                }
            }
        } catch (InterruptedException e) {
            // Manejar la excepcion si ocurre algun problema con el hilo
        }
        
        
    }
}
