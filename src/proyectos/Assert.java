package proyectos;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Map;

public class Assert {
	
	public static void assertRolesEquals(Map<String, Integer> roles1, Map<String, Integer> roles2) {
		assertEquals(roles1.size(), roles2.size()); //verifica tamaño
		assertTrue(roles1.entrySet().stream().allMatch(e ->  e.getValue().equals(roles2.get(e.getKey())))); //verifica contenido
	}
	
	public static void assertRolesNotEquals(Map<String, Integer> roles1, Map<String, Integer> roles2) {
		assertFalse(roles1.entrySet().stream().allMatch(e ->  e.getValue().equals(roles2.get(e.getKey())))); //verifica contenido
	}
}
