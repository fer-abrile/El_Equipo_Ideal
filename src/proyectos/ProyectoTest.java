package proyectos;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProyectoTest {
	Proyecto proyecto; 
	
	@BeforeEach 
	public void initialize() {
		proyecto = new Proyecto("ProyectoPrueba");
		proyecto.getRolesCantidades().put("Líder de proyecto", 1);
		proyecto.getRolesCantidades().put("Arquitecto", 2);
		proyecto.getRolesCantidades().put("Programador", 4);
		proyecto.getRolesCantidades().put("Tester", 5);
	}
	
	@Test
	public void getRolesCantidadesIguales() 
	{
		Map<String, Integer> rolesCantidadesTest = new HashMap<>();
		rolesCantidadesTest.put("Líder de proyecto", 1);
		rolesCantidadesTest.put("Arquitecto", 2);
        rolesCantidadesTest.put("Programador", 4);
        rolesCantidadesTest.put("Tester", 5);
        Assert.assertRolesEquals(proyecto.getRolesCantidades(), rolesCantidadesTest);
	}
	
	@Test
	public void getRolesCantidadesDistintos() 
	{
		Map<String, Integer> rolesCantidadesTest = new HashMap<>();
		rolesCantidadesTest.put("Líder de proyecto", 3);
		rolesCantidadesTest.put("Arquitecto", 1);
        rolesCantidadesTest.put("Tester", 3);
        
        Assert.assertRolesNotEquals(proyecto.getRolesCantidades(), rolesCantidadesTest);
	}
	
	@Test
	public void getRolesCantidadesIgualVacios() 
	{
		proyecto.getRolesCantidades().clear();
		Map<String, Integer> rolesCantidadesTest = new HashMap<>();
		
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
