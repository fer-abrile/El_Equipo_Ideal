package personas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PersonasTest {

	
	@Test
	public void nombreVacio() {
		Exception exception = assertThrows(Exception.class, () -> new Persona("", "Líder de proyecto", 0));
	    assertEquals("La persona debe tener un nombre valido.", exception.getMessage());
	}
	
	@Test
	public void rolVacio() {
		Exception exception = assertThrows(Exception.class, () -> new Persona("Persona1", "", 0));
	    assertEquals("La persona debe tener un rol valido.", exception.getMessage());
	}
	
	@Test
	public void calificacionMayorA5() {
		Exception exception = assertThrows(Exception.class, () -> new Persona("Persona1", "Líder de proyecto", 6));
	    assertEquals("La calificacion historica debe ser mayor que 0 y menor o igual que 5.", exception.getMessage());
	}
	
	@Test
	public void calificacionMenorA1() {
		Exception exception = assertThrows(Exception.class, () -> new Persona("Persona1", "Líder de proyecto", 0));
	    assertEquals("La calificacion historica debe ser mayor que 0 y menor o igual que 5.", exception.getMessage());
	}
	
	@Test
	public void getNombre() {
		assertEquals(new Persona("PruebaNombre", "Líder de proyecto", 4).getNombre(), "PruebaNombre");
	}
	
	@Test
	public void getRol() {
		assertEquals(new Persona("PruebaNombre", "Líder de proyecto", 4).getRol(), "Líder de proyecto");
	}
	
	@Test
	public void getCalificacionHistorica() {
		assertEquals(new Persona("PruebaNombre", "Líder de proyecto", 5).getCalificacionHistorica(), 5);
	}

}
