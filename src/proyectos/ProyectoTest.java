package proyectos;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.jupiter.api.Test;

class ProyectoTest {
	Proyecto proyecto = new Proyecto("ProyectoPrueba"); 
	
	@Before
	public void initialize() throws Exception {
		Map<String, Integer> rolesCantidades = new HashMap<>();
        rolesCantidades.put("Líder de proyecto", 1);
        rolesCantidades.put("Arquitecto", 2);
        rolesCantidades.put("Programador", 4);
        rolesCantidades.put("Tester", 5);
        
        Proyecto.setRolesCantidades(rolesCantidades);
	}
	
	@Test
	public void getRolesCantidades() 
	{
		Map<String, Integer> rolesCantidadesTest = new HashMap<>();
		rolesCantidadesTest.put("Líder de proyecto", 1);
		rolesCantidadesTest.put("Arquitecto", 2);
        rolesCantidadesTest.put("Programador", 4);
        rolesCantidadesTest.put("Tester", 5);
        
        Assert.assertRolesEquals(proyecto.getRolesCantidades(), rolesCantidadesTest);
	}
	
	@Test
	public void getNombre() 
	{
		assertEquals(proyecto.getNombre(), "ProyectoPrueba");
	}
	
	@Test
	public void setNombre() 
	{
		proyecto.setNombre("ProyectoPrueba2");
		assertEquals(proyecto.getNombre(), "ProyectoPrueba2");
	}
	
}
