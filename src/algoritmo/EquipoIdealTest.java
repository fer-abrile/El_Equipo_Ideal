package algoritmo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;
import personas.Persona;


class EquipoIdealTest {

	@Test
	public void todoVacio() 
	{
		List<Persona> personasVacio = new ArrayList<>();
		
		List<String[]> incompatibilidadesVacias = new ArrayList<>();
        
        Map<String, Integer> rolesCantidadesVacio = new HashMap<>();
		
        List<Persona> esperado = new ArrayList<>();
        
		assertEquals(esperado, EquipoIdeal.encontrarEquipoOptimo(personasVacio, incompatibilidadesVacias, rolesCantidadesVacio));
	}
	
	@Test
	public void SinIncompatibilidades() 
	{
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona("Persona1", "Líder de proyecto", 4));
		personas.add(new Persona("Persona2", "Líder de proyecto", 3));
		personas.add(new Persona("Persona3", "Arquitecto", 5));
		personas.add(new Persona("Persona4", "Arquitecto", 2));
		personas.add(new Persona("Persona5", "Programador", 4));
		personas.add(new Persona("Persona6", "Programador", 3));
		personas.add(new Persona("Persona7", "Tester", 2));
		personas.add(new Persona("Persona8", "Tester", 5));
		
		List<String[]> incompatibilidadesVacias = new ArrayList<>();
        
        Map<String, Integer> rolesCantidades = new HashMap<>();
        rolesCantidades.put("Líder de proyecto", 1);
        rolesCantidades.put("Arquitecto", 1);
        rolesCantidades.put("Programador", 1);
        rolesCantidades.put("Tester", 1);
		
        List<Persona> esperado = new ArrayList<>();
        esperado.add(new Persona("Persona1", "Líder de proyecto", 4));
        esperado.add(new Persona("Persona3", "Arquitecto", 5));
        esperado.add(new Persona("Persona5", "Programador", 4));
        esperado.add(new Persona("Persona8", "Tester", 5));
        
		Assert.assertPersonasEquals(esperado, EquipoIdeal.encontrarEquipoOptimo(personas, incompatibilidadesVacias, rolesCantidades));
	}
	
	@Test
	public void ConIncompatibilidades() 
	{
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona("Persona1", "Líder de proyecto", 4));
		personas.add(new Persona("Persona2", "Líder de proyecto", 3));
		personas.add(new Persona("Persona3", "Arquitecto", 5));
		personas.add(new Persona("Persona4", "Arquitecto", 2));
		personas.add(new Persona("Persona5", "Programador", 4));
		personas.add(new Persona("Persona6", "Programador", 3));
		personas.add(new Persona("Persona7", "Tester", 2));
		personas.add(new Persona("Persona8", "Tester", 5));
		
		List<String[]> incompatibilidades = new ArrayList<>();
		incompatibilidades.add(new String[]{"Persona1", "Persona3"});
		
        Map<String, Integer> rolesCantidades = new HashMap<>();
        rolesCantidades.put("Líder de proyecto", 1);
        rolesCantidades.put("Arquitecto", 1);
        rolesCantidades.put("Programador", 1);
        rolesCantidades.put("Tester", 1);
		
        List<Persona> esperado = new ArrayList<>();
        esperado.add(new Persona("Persona2", "Líder de proyecto", 3));
        esperado.add(new Persona("Persona3", "Arquitecto", 5));
        esperado.add(new Persona("Persona5", "Programador", 4));
        esperado.add(new Persona("Persona8", "Tester", 5));
        
		Assert.assertPersonasEquals(esperado, EquipoIdeal.encontrarEquipoOptimo(personas, incompatibilidades, rolesCantidades));
	}
	
}