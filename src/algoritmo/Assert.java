package algoritmo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import personas.Persona;

public class Assert {
	
	public static void assertPersonasEquals(List<Persona> personas1, List<Persona> personas2) {
		assertEquals(personas1.size(), personas2.size());
		for(int i = 0; i < personas1.size(); i++) 
			assertTrue(personasIguales(personas1.get(i), personas2.get(i)));
	}
	
	
	private static boolean personasIguales(Persona p1, Persona p2) {
		if(p1.getRol() != p2.getRol())
			return false;
		if(p1.getNombre() != p2.getNombre())
			return false;
		if(p1.getCalificacionHistorica() != p2.getCalificacionHistorica())
			return false;
		return true;
	}
	
}