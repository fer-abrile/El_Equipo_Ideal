# Trabajo Practico 3 - Equipo Ideal

# Participantes:

- Fernando Abrile
- Tomás Ledesma

> Programación 3 - Comisión 1, Universidad Nacional de General Sarmiento.
> 

## Objetivos

El objetivo del trabajo práctico es implementar una aplicación para resolver esta problemática. La
aplicación debe contar con la siguiente funcionalidad:

- Cargar y visualizar las personas disponibles.
- Cargar y visualizar las incompatibilidades.
- Cargar y visualizar los requerimientos para el equipo.
- Resolver el problema y visualizar el equipo resultante.

Se debia resolver el problema por medio de un algoritmo de fuerza bruta o backtracking, que debe
ejecutar en un thread separado de la aplicación principal.

Para conseguir el objetivo, el grupo diseñó una solución en Java con los siguientes paquetes de clases.

- algoritmos
- interfaces
- personas
- principal
- proyecto

En las siguientes secciones se explica el funcionamiento de cada una y los métodos utilizados para alcanzar dicho objetivo.

## Clases, Paquetes, Métodos y Comportamientos.

## Paquete Principal

> main.java
> 

La clase principal es el *main* del proyecto, la misma tendrá la función de iniciar la aplicación y lo hará haciendo un llamado a la clase **MainForm.java.**

Contiene como atributos:

- personas: que es una *****Lista***** ( List<Persona> ) de Personas, la cual se utilizara a lo largo del programa.
- incompatibilidades: que es una *****Lista***** ( List<Persona> ) de Incompatibilidades, la cual se utilizara a lo largo del programa.

La estructura de la misma es la siguiente. 

public class main {

```java
public class main {

	private static List<Persona> personas = new ArrayList<>();
    private static List<String[]> incompatibilidades = new ArrayList<>();
    
	public static void main(String[] args) {	
		MainForm ventana = new MainForm(personas, incompatibilidades);
		ventana.EquipoIdealForm.setVisible(true);
	}
}
```

## Paquete Algoritmo

El paquete algoritmo, incluye las clases mediante las cuales se buscara resolver el problema para encontar un *Equipo Ideal*, a su vez contedra la clase ******************Thread****************** clase que ejectuara en diferentes hilos los metodos utilizados en la clase *********************************EquipoIdeal********************************* los cuales se ejecutan siguiendo la metodologia **************Fuerza Bruta.************** El paquete incluye las siguientes Clases.

> **Assert.java**
> 

La funcion principal de la clase ***********Assert.java*********** es completentar a las clase de Test EquipoIdealTest.java

public class Assert {

```java
public static void assertPersonasEquals(List<Persona> personas1, List<Persona> personas2)
{
	assertEquals(personas1.size(), personas2.size());

	for(int i = 0; i < personas1.size(); i++)
		assertTrue(personasIguales(personas1.get(i), personas2.get(i)));
}

private static boolean personasIguales(Persona p1, Persona p2)
{
	if(p1.getRol() != p2.getRol())
		return false;
	if(p1.getNombre() != p2.getNombre())
		return false;
	if(p1.getCalificacionHistorica() != p2.getCalificacionHistorica())
		return false;
	return true;
}
}
```

> **EquipoIdeal.java**
> 

La clase *****************EquipoIdeal.java***************** contiene los metodos de ***encontrarEquipoOptimo() ,** **backtrack() y sonIncompatibles(),*** metodos fundamentales para resolver el problema principal del Trabajo Practico.

El metodo ***encontrarEquipoOptimo()*,** recibe por parametro las siguientes variables, *List<Persona> personas, List<String[]> incompatibilidades, Map<String, Integer> roles,* e intentara resolver segun las *********************************************************personas, incompatibilidades********************************************************* y *********************************************************roles,*************************************** cual es el equipo Optimo para el requerimiento cargado previamente. Este metodo hara uso del metodo ***backtrack().***

Su diseño es el siguiente:

```jsx
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


```

El metodo ***backtrack(),*** recibe por parametro *List<Persona> personas, List<String[]> incompatibilidades, Map<String, Integer> cantidadesRoles, List<Persona> equipoOptimo, List<Persona> equipoActual, Map<String, Integer> rolActualCount, int indice* y se encargara de comparar las personas, buscar incompatibilidades y de armar un EquipoOptiomo y otro EquipoActual; a su vez se llamara a si mismo de forma recursiva para comparar estos dos equipo y retornar un **equipoOptimo.**

Su diseño es el siguiente:

```java
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
```

Por ultimo, el metodo **sonIncompatibles(),** tendra como funcion principal hacer conocer si dos personas son compatibles, y de esta menera pueden formar parte del equipoOptimo o no. Recibe por parametro las siguientes variables, Persona persona1, Persona persona2, List<String[]> incompatibilidades.

Su diseño es el siguiente;

```java
private static boolean sonIncompatibles(Persona persona1, Persona persona2, List<String[]> incompatibilidades) {
	for (String[] parIncompatible : incompatibilidades) {
		if ((parIncompatible[0].equals(persona1.getNombre()) && parIncompatible[1].equals(persona2.getNombre())) ||
                (parIncompatible[0].equals(persona2.getNombre()) && parIncompatible[1].equals(persona1.getNombre()))) {
            return true;
        }
    }
    return false;
}
```

> **EquipoIdealTest.java**
> 

La clase *EquipoIdealTest.java* tiene como funcion principal corroborar que los metodos de la clase *EquipoIdeal.java* funcionen correctamente, para ello dentro de esta clase tipo Test se crearon los siguientes metodos.

```java
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
```

> **EquipoIdealThread.java**
> 

La clase ***********************EquipoIdealThread.java*********************** como se menciono anteriormente, se utiliza para ejecutar un codigo especifico, en este caso, los metodos dentro de la clase *EquipoIdeal.java* en un hilo de procesamiento especifico.

Su diseño es el siguiente:

```java
public EquipoIdealThread(List<Persona> personas, List<String[]> incompatibilidades, 
Map<String, Integer> roles) {

	this.personas = personas;
	this.incompatibilidades = incompatibilidades;
	this.roles = roles;
	this.equipoIdeal = new ArrayList<>();
}
public List<Persona> getEquipoIdeal() {
    return equipoIdeal;
}

@Override
public void run() {
   equipoIdeal = EquipoIdeal.encontrarEquipoOptimo(personas, incompatibilidades, roles);
}

```

}

## Paquete Interfaces

El paquete Interfaces, incluye dos clases unicamente, una es nuestro JFrame principal, que es la interfaz principal que utilizara el usuario para crear una nueva persona, crear un requerimiento, generar un equipo ideal o establecer un incompatibilidad entre dos personas, y su segundo clase sera un Controlador, quien llevara a cabo la ejecucion de todas las acciones que realice el usuario.

> **MainFrame.java**
> 

*MainFrame.java* es la clase que contiene toda la estructura del JFrame principal, el diseño de sus paneles, botones de acciones y sus acciones basicas.

Su diseño cuenta con un JFrame principal, 5 JPanel y 1 menuBar, mediante el cual se accedera a los diferentes JPanel dependiendo de la accion que querramos realizar.

La interfaz principal tiene el siguiente diseño.

![Untitled](/ReadMe/Images/Home.png)

Luego utilizandolas opciones de la barra de menu, prodremos realizar diferentes acciones, las cuales son.

- **Nuevo**
    

   ![Untitled](/ReadMe/Images/Nuevo.png)
    
    - **Nueva Persona:** Abrira un formulario con todos los campos requeridos para crear una persona, una vez introducidos los datos minimos de la persona como *********************************************************************************************************Nombre, Rol y Calificacion Historia*********************************************************************************************************, el programa archivara las personas creadas dentro de una tabla asi se tiene constancia de las personas creadas.
        
      ![Untitled](/ReadMe/Images/Nueva_Persona.png)
   
    - **Nueva Persona:** Abrira un formulario con todos los campos requeridos para crear una persona, una vez introducidos los datos minimos de la persona como *********************************************************************************************************Nombre, Rol y Calificacion Historia*********************************************************************************************************, el programa archivara las personas creadas dentro de una tabla asi se tiene constancia de las personas creadas.
              
        Los metodos utilizados para los botones ***************************************************Guardar y Limpiar***************************************************, son los siguientes. Ambos estan controlados por la clase *********************Controlador.java*********************
        
        Guardar:
        
        ```java
        btnGuardarPersona.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        try {
        				if(Controlador.formPersonaCompleto(textNombrePersona, comboRol, comboCalifHistorica)) {
        			Persona persona = new Persona();
        			String [] data = {textNombrePersona.getText(),comboRol.getSelectedItem().toString(),comboCalifHistorica.getSelectedItem().toString()};
        			Controlador.crearPersona(persona,textNombrePersona.getText(),comboRol.getSelectedItem().toString(), comboCalifHistorica.getSelectedIndex(),DLM_Personas,DTM_PersonasCreadas,data);
        			Controlador.limpiarPersona(textNombrePersona, comboRol, comboCalifHistorica);
        				}
        				else {
        					JOptionPane.showMessageDialog(null, "Por favor complete todos los campos", "Error!",JOptionPane.ERROR_MESSAGE);
        				}
        			}
        			catch (Exception ex) {
        				JOptionPane.showMessageDialog(null, "Por favor complete todos los campos", "Ooooppss!",JOptionPane.ERROR_MESSAGE);
        			}
        
        		}
        	});
        
        ```
        
        Limpiar:
        
        ```java
        btnLimpiarPersona.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	Controlador.limpiarPersona(textNombrePersona, comboRol, comboCalifHistorica);
        }
        });
        ```
        
    - **Nueva Incompatibilidad:** Desde esta opcionse cargaran las incompatibilades entre las personas, a su vez nos mostrara una lista de todas las personas creadas anteriormente.
        

        ![Untitled](/ReadMe/Images/Alta_Compatibilidad.png)

        
        Para crear la Incompatibilidad, deberemos seleccionar 2(dos) personas y luego clic en ************************Agregar.************************
        
        El metodo utilizado para generar la Incompatibilidad es controlado por la clase **************************Controlador.java**************************i y dede el formulario el boton agregar tiene el siguiente codigo:
        
        ```java
        btnAgregarPersonasIncom.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        int[] seleccionado = listaPersonasCreadas.getSelectedIndices();
        				if(Controlador.getPersonas().size() >= 2) {
        					Controlador.crearIncompatibilidad(seleccionado,DLM_Incompatibilidades);
        				}else {
        					JOptionPane.showMessageDialog(null, "Necesita seleccionar 2 Personas", "Error!",
        							JOptionPane.ERROR_MESSAGE);
        				}
        
        		}
        	});
        
        ```
        
    - **Nuevo Requerimiento:** La opcion *******************Nuevo Requerimiento*******************, tendra como funcion principal la carga de un Proyecto o Equipo, aqui indicaremos cuantas personas por rol necesitamos para un equipo, los roles requeridos son ******************************************************************************************************************************************************Lider de Equipo, Arquitecto, Programador y Tester.******************************************************************************************************************************************************

        ![Untitled](/ReadMe/Images/Nuevo_Requerimiento.png)

        
        Una vez completados el Nombre del Proyecto y las cantidades por roles, tendremos que hacer clic en *********************Guardar********************* y la informacion del requerimiento sera visible en la tabla ******************************************************Proyectos Creados****************************************************** esta tabla dispone de un botn *********Info********* en caso que se quiera ver el detalle del proyecto.
        
        Los metodos utilizados en este formulario para lso botones recien mencionados, son controlados por la clase ********************************Controlador.java**************************************,****** las acciones de estos mismos son:
        
        Guardar:
        
        ```java
        btnRequerimientoGuardar.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        try {
        String [] data = {textFieldNombreProyecto.getText(),textFieldLiderEquipoCant.getText(),textFieldArquitectoCant.getText(),textFieldProgramadorCant.getText(),textFieldTesterCant.getText()};
        Controlador.guardarRequerimiento(textFieldNombreProyecto.getText(),Integer.parseInt(textFieldLiderEquipoCant.getText()),Integer.parseInt(textFieldArquitectoCant.getText()),
        Integer.parseInt(textFieldProgramadorCant.getText()),Integer.parseInt(textFieldTesterCant.getText()),DTM_Requerimientos,data);
        Controlador.limpiarRequerimiento(textFieldNombreProyecto,textFieldLiderEquipoCant,textFieldArquitectoCant,textFieldProgramadorCant,textFieldTesterCant);		} 
        catch (Exception ex) {
        				JOptionPane.showMessageDialog(null, "Por favor, compelte todos los campos", "Error!",JOptionPane.ERROR_MESSAGE);
        			}
        			}
        	});
        ```
        
        Limpiar:
        
        ```java
        btnRequerimientoLimpiar.addActionListener(new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				Controlador.limpiarRequerimiento(textFieldNombreProyecto,textFieldLiderEquipoCant,textFieldArquitectoCant,textFieldProgramadorCant,textFieldTesterCant);
        			}
        		});
        ```
        
        Info:
        
        ```java
        btnRequerimientoInfo.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        try {
        Controlador.verInfoProyecto(tableRequrimientosCreados.getValueAt(tableRequrimientosCreados.getSelectedRow(),
        0));
        } catch (Exception e2) {
        JOptionPane.showMessageDialog(null, "Selección no válida", "Ooooppss!",JOptionPane.ERROR_MESSAGE);
        }	
        }
        	});
        ```
        
- **Consultar**
    - **Consultar Persona:** El formulario Consultar, mostrara informacion sobre una persona, su Nombre, Rol y Calificacion y a su vez si tiene alguna Incompatibilidad con otra persona.
        

         ![Untitled](/ReadMe/Images/Consulta_Persona.png)

        
        Para conocer la informacion de una persona, debemos seleccionarla de la lista, y hacer clic en ************************Ver Info,************************ de esta manera el programa nos mostrara su informacion basica y las personas con la que es Incompatible.
        
        El metodo ************************Ver Info************************ es controlado por la clase ***************************************************Controlador.java*************************************************** y el codigo detras del boton es el siguiente:
        
        ```java
        btnVerInfo.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {		
        try {
        	Controlador.ConsultaPersona(listPersonasCreadas.getSelectedIndex(),textField_ConsultaNombre,textField_ConsultaRol,textField_ConsultaCalificacion);
        	Controlador.crearModelIncompatibilidadesPorPersona(listPersonasCreadas.getSelectedIndex(),DLM_IncompatibilidadesPorPersona);
        }catch (Exception e2) {
        JOptionPane.showMessageDialog(null, "Seleccione al menos una persona", "Error!",JOptionPane.ERROR_MESSAGE);
        	}
        
        		}
        	})
        ```
        
- **Generar**
    - **Generar Equipo:** Por ultimo, la opcion de Generar Equipo, se va a encargar de resolver el problema principal del Trabajo Practico, una vez las personas creadas y cargadas sus incompatibilidades y a su vez los requerimientos, cuando querramos generar un Equipo, el programa nos mostrara una Tabla con los requerimientos creados; tendremos que seleccionar 1(uno) y luego hacer click en ****************************Generar Equipo****************************, entonces el programa nos mostrara una tabla con todas las personas de este equipo, o nos dira que no fue posible encontrar las personas para el equipo que buscamos.
        

       ![Untitled](/ReadMe/Images/Generar_Equipo.png)
        
        **Equipo Encontrado:**
        
        ![Untitled](/ReadMe/Images/Equipo_Generado.png)
        
        **Equipo NO Encontrado:**
        
       ![Untitled](/ReadMe/Images/Error_GenerarEquipo.png)

        
        El codigo de **Generar Equipo** es controlado por la clase ********************************Controlador.java******************************** que a su vez hace uso de las clase *********************************EquipoIdeal.java.********************************* El codigo detras del boton ******************Generar Equipo, es el siguiente:******************
        
        ```java
        btnGenerarEquipo.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        try {
        Controlador.GenerarEquipo(tableEquipoProyectos.getValueAt(tableEquipoProyectos.getSelectedRow(),0),DTM_EquipoIdeal);
        panelEquipoIdeal.setVisible(true);
        } catch (Exception e2) {
        JOptionPane.showMessageDialog(null, "Proyecto no seleccionado", "Error!",
        JOptionPane.ERROR_MESSAGE);
        }	}
        	});
        
        ```
        

> **Controlador.java**
> 

La clase [Controlador.java](http://Controlador.java) contiene los metodos necesarios para todas las acciones que el usuario puede realizar, ***Nueva Persona, Nuevo Requerimiento, Limpiar Campo, Generar Equipo, etc***

A continuacion se detallaran los metodos creados para ******************************************************Persona, Proyecto y Equipo.******************************************************

<aside>
💡 Metodos para la administracion de Personas:

</aside>

- **crearPersona()**

```java
public static Persona crearPersona(Persona persona, String nombre, String rol, int calificaion,DefaultListModel<String> dLM, DefaultTableModel DTM_PersonasCreadas, String[] data) {
boolean respuesta = bontonConfirmarPersona();
if(respuesta ==true) {
persona = new Persona (nombre, rol, calificaion);
persona.toString();
personas.add(persona);
crearModelPersonas(dLM);
crearModeloTablaPersonasCreadas(DTM_PersonasCreadas,data);
}

	return persona;
}

```

- **consultarPersona():** Este metodo muestra los datos de una persona cuando estamos en la ventana *Consulta Persona*.

```java
public static void consultaPersona(int selectedIndex, JTextField textField_ConsultaNombre, JTextField textField_ConsultaRol, JTextField textField_ConsultaCalificacion) {
Persona personaConsulta = new Persona();
	personaConsulta = personas.get(selectedIndex);
	textField_ConsultaNombre.setText(personaConsulta.getNombre());
	textField_ConsultaRol.setText(personaConsulta.getRol());
	textField_ConsultaCalificacion.setText(String.valueOf(personaConsulta.getCalificacionHistorica()));

}
```

- **limpiarPersona():** Este metodo limpia los campos del formulario de *Nueva Persona*

```java
public static void limpiarPersona(JTextField textNombrePersona, JComboBox comboRol, 
JComboBox comboCalifHistorica) {
	textNombrePersona.setText("");
	comboRol.setSelectedIndex(-1);
	comboCalifHistorica.setSelectedIndex(-1);
}

```

<aside>
💡 Metodos para la administracion de Incompatibilidades:

</aside>

- **crearIncompatibilidad()**

```java
	public static void crearIncompatibilidad(int[] posicionesSeleccionado,DefaultListModel<String> DLM) {String[] incompatibilidad = new String[2];
	boolean respuesta = bontonConfirmarIncompatibilidad();

	if (respuesta == true) {
		for (int i = 0; i < incompatibilidad.length; i++) {
			incompatibilidad[i] = personas.get(posicionesSeleccionado[i]).getNombre();
			personas.get(posicionesSeleccionado[i]).getPersonasIncompatibles().add(incompatibilidad);
		}
		incompatibilidades.add(incompatibilidad);
		crearModelIncompatibilidades(DLM);

	}
}

```

- **mostrarIncompatibilidad()**: Este metodo es un metodo auxiliar, utilizado en los metodos que generan los modelos para la listas de Incompatibilidades, dentro de la ventan de **********************Crear Incompatibilidad********************** y tambien en el de *****************Consultar Persona***************** en la lista de Incompatibilidad de la persona seleccionada.

```java
private static String mostrarIncompatibilidad(int conta, List<String[]> incompatibilidades) {
String incompat = "";
	for (String[] strings : incompatibilidades) {
		incompat = Arrays.toString(strings);
	}
	return incompat;
}

```

<aside>
💡 Metodos para la administracion de Requerimientos (Proyectos):

</aside>

- **guardarRequerimiento():**

```java
public static void guardarRequerimiento(String nombre, int liderEquipo, int arquitecto, int programador, int tester, DefaultTableModel DTM_Requerimientos, String[] data) {
boolean respuesta = bontonConfirmarRequerimiento();
	if(respuesta ==true) {
		Proyecto proyectoAux = new Proyecto();
		proyectoAux.setNombre(nombre);
		proyectoAux.getRolesCantidades().put("Líder de Proyecto", liderEquipo);
		proyectoAux.getRolesCantidades().put("Arquitecto", arquitecto);
		proyectoAux.getRolesCantidades().put("Programador", programador);
		proyectoAux.getRolesCantidades().put("Tester", tester);
		proyectosCreados.put(nombre, proyectoAux);
        crearModeloRequerimientos(DTM_Requerimientos,data);
		}

}
```

- **limpiarRequerimiento():**

```java
	public static void limpiarRequerimiento( JTextField textFieldNombreProyecto,JTextField textFieldLiderEquipoCant, JTextField textFieldArquitectoCant,
JTextField textFieldProgramadorCant, JTextField textFieldTesterCant) {
textFieldNombreProyecto.setText("");
	textFieldLiderEquipoCant.setText("");
	textFieldArquitectoCant.setText("");
	textFieldProgramadorCant.setText("");
	textFieldTesterCant.setText("");
}
```

- **verInfoProyecto()**:

```java
public static void verInfoProyecto(Object object) {
JOptionPane.showMessageDialog(null, proyectosCreados.get(object).toString(),"Info Proyecto", JOptionPane.INFORMATION_MESSAGE);
}
```

<aside>
💡 Metodos para la administracion de crear Equipo Ideal:

</aside>

- generarEquipo():

```java
   public static void generarEquipo(Object valorSeleccionado, DefaultTableModel DTM_EquipoIdeal, JPanel panelEquipoIdeal) {
 EquipoIdealThread equipoIdealThread = new EquipoIdealThread(personas, incompatibilidades, proyectosCreados.get(valorSeleccionado).getRolesCantidades());
    Thread thread = new Thread(equipoIdealThread);
    thread.start();

    try {
        thread.join();
        List<Persona> equipoIdeal = equipoIdealThread.getEquipoIdeal();

        if (equipoIdeal.isEmpty()) {

                JOptionPane.showMessageDialog(null, "No se encontro un equipo compatible para el requerimiento seleccionado. \n",
                		"Error!",JOptionPane.ERROR_MESSAGE);
            }else {

        	crearModeloTablaEquipoIdeal(DTM_EquipoIdeal,equipoIdeal);
        	panelEquipoIdeal.setVisible(true);

        }
    } catch (InterruptedException e) {
        // Manejar la excepcion si ocurre algun problema con el hilo
    }
}
```

<aside>
💡 Metodos para la administracion de crear Model, utilizados en JTabley JList:

</aside>

- **crearModelPersonas():**

```java
public static ListModel<String> crearModelPersonas(DefaultListModel<String> DLM) {int tamano = personas.size();
	final String[] vector = new String[tamano];
	for (int conta = 0; conta < tamano; conta++) {
		vector[conta] =  personas.get(conta).getNombre() + " - "
				+ personas.get(conta).getRol() + " - " + personas.get(conta).getCalificacionHistorica();
		if (!DLM.contains(vector[conta]))
			DLM.addElement(vector[conta]);
	}
	return DLM;
}

```

- **crearModelIncompatibilidadesPorPersona():**

```java
public static DefaultListModel<String> crearModelIncompatibilidadesPorPersona(int personaSeleccionada,DefaultListModel<String> DLM_IncompatibilidadesPorPersona) {
DLM_IncompatibilidadesPorPersona.removeAllElements();
	Persona personaConsulta = new Persona();
	personaConsulta = personas.get(personaSeleccionada);

	int tamano = personaConsulta.getPersonasIncompatibles().size();

	final String[] vector = new String[tamano];
	for (int conta = 0; conta < tamano; conta++) {
		vector[conta] = mostrarIncompatibilidad(conta, personaConsulta.getPersonasIncompatibles());
		if (!DLM_IncompatibilidadesPorPersona.contains(vector[conta]))
			DLM_IncompatibilidadesPorPersona.addElement(vector[conta]);
	}
	return DLM_IncompatibilidadesPorPersona;

}
```

- **crearModelIncompatibilidades():**

```java
public static ListModel<String> crearModelIncompatibilidades(DefaultListModel<String> DLM) {
	int tamano = incompatibilidades.size();
	final String[] vector = new String[tamano];

	for (int conta = 0; conta < tamano; conta++) {
		vector[conta] =  mostrarIncompatibilidad(conta,incompatibilidades);
		if (!DLM.contains(vector[conta]))
			DLM.addElement(vector[conta]);
	}
	return DLM;
}

```

- **crearModeloRequerimientos():**

```java
public static void crearModeloRequerimientos(DefaultTableModel dTM_Requerimientos, 
String [] data) {
dTM_Requerimientos.addRow(data);

}
```

- **crearModeloTablaPersonasCreadas():**

```java
public static void crearModeloTablaPersonasCreadas(DefaultTableModel DTM_PersonasCreadas, String [] data) {
DTM_PersonasCreadas.addRow(data);

}
```

- **crearModeloTablaEquipoIdeal():**

```java
public static void crearModeloTablaEquipoIdeal(DefaultTableModel DTM_PersonasCreadas, List<Persona> equipoIdeal) {    
for (Persona persona : equipoIdeal) {
    	String [] data = {persona.getNombre(),persona.getRol(),Integer.toString(persona.getCalificacionHistorica())};
    	DTM_PersonasCreadas.addRow(data);

    }
}

```

<aside>
💡 Metodos auxiliares:

</aside>

- **formPersonaCompleto():** Tiene la funcion de validar que el formulario tiene toda la informacion necesaria.

```java
public static boolean formPersonaCompleto(JTextField textNombrePersona, JComboBox comboRol,
JComboBox comboCalifHistorica) {
if(textNombrePersona.getText()== "" || comboRol.getSelectedItem().toString() == ""|| comboCalifHistorica.getSelectedItem().toString() == "")
		return false;

	return true;
}

```

- **bontonConfirmarPersona():** Muestra por pantalla un pop-up para que el usuario confirme la creacion de la Persona.

```java
public static boolean bontonConfirmarPersona() {
	int respuesta = JOptionPane.showConfirmDialog(null,"¿Seguro quiere crear esta Persona?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(respuesta == JOptionPane.YES_OPTION) {
			return  true;
		}else if (respuesta == JOptionPane.NO_OPTION) {
			return false;
		}

	return false;

}
```

- **bontonConfirmarIncompatibilidad():** Muestra por pantalla un pop-up para que el usuario confirme la creacion de la Incompatibilidad entre dos Personas.

```java
public static boolean bontonConfirmarIncompatibilidad() {
	int respuesta = JOptionPane.showConfirmDialog(null,"¿Seguro quiere crear esta Incompatibilidad?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(respuesta == JOptionPane.YES_OPTION) {
			return  true;
		}else if (respuesta == JOptionPane.NO_OPTION) {
			return false;
		}

	return false;

}
```

- **bontonConfirmarRequerimiento():** Muestra por pantalla un pop-up para que el usuario confirme la creacion de un Requerimiento.

```java
public static boolean bontonConfirmarRequerimiento() {
	int respuesta = JOptionPane.showConfirmDialog(null,"¿Seguro quiere crear este Requerimiento?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(respuesta == JOptionPane.YES_OPTION) {
			return  true;
		}else if (respuesta == JOptionPane.NO_OPTION) {
			return false;
		}

	return false;

}

```

## Paquete Personas

El paquete *********personas*********  incluye dos clases, la clase ************************************Persona.java************************************ mediante la cual se crearan lso objetos tipo ********************Persona******************** y una clase ***************************************************PersonaTest.java***************************************************  la cual se encargara de comprobar el buen funcionamiento de Persona mediante Test Unitarios.

> **Persona.java**
> 

La clase persona cuenta con 2 constructores, su principal contructor tiene el siguiente diseño.

```java
public Persona(String nombre, String rol, int calificacionHistorica) {

if(nombre.length() < 1)
    	throw new IllegalArgumentException("La persona debe tener un nombre valido.");
	this.nombre = nombre;
    if(rol.length() < 1)
    	throw new IllegalArgumentException("La persona debe tener un rol valido.");
    this.rol = rol;
    if(calificacionHistorica > 5 || calificacionHistorica < 1)
    	throw new IllegalArgumentException("La calificacion historica debe ser mayor que 0 y menor o igual que 5.");
    this.calificacionHistorica = calificacionHistorica;
}
```

Su segundo constructor es el siguiente.

```java
public Persona() 
{
	super();
}
```

A su vez encontraremos un *@Override* al metodo **toString()** y lo utilizaremos cuando queramos imprimir informacion de una ****************Persona.****************

```java
@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
		builder.append("[Nombre = ");
		builder.append(nombre);
		builder.append(", Rol = ");
		builder.append(rol);
		builder.append(", Calificacion Historica = ");
		builder.append(calificacionHistorica);
		builder.append("]");
return builder.toString();
}
```

> **PersonaTest.java**
> 

Como se menciono anteriormente, la clase ***********[PersonaTest.java](http://PersonaTest.java)* llevara a cabo Test Unitarios para corroborar el funcionamiento de los objetos *************Persona**,* la misma incluye los siguientes Test.

```java
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

```

## Paquete Proyectos

El paquete **proyectos**, incluye una unica clase que sera utilizada para crear los objetos de tipo ******************Proyecto.******************

> Proyecto.java
> 

Al igual que la clase persona, ************************Proyecto.java************************ tiene como funcion principal crear los objetos de tipo ********************Proyecto.******************** La creacion de los objetos tipo proyectos facilitan el funcionamiento del algoritmo de EquipoIdeal, al crearse un objeto de tipo ******************Proyecto******************, estamos asignado al objeto un nombre y los roles con sus cantidades necesarias, de esta manera la consulta se ejecuta en O(1).

El diseño de los constructores de la clase *****************************Proyecto.java***************************** es el siguiente:

```java
public Proyecto (String _nombre){
	this.nombre = _nombre;
}

public Proyecto() {
	super();
}

```

Proyecto.java al igual que Persona.java tiene un metodo *@Override* para el metodo **toString()**, el cual tiene el siguiente diseño.

```java
@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
		builder.append("[Nombre = ");
		builder.append(nombre);
		builder.append(", Rol = ");
		builder.append(rolesCantidades.toString());
	return builder.toString();
}
```
> ProyectoTest.java
> 

Esta clase contiene los test para el objeto Proyecto.

Los cuales son:

```java
@BeforeEach
public void initialize() {
proyecto = new Proyecto("ProyectoPrueba");
proyecto.getRolesCantidades().put("Líder de proyecto", 1);
proyecto.getRolesCantidades().put("Arquitecto", 2);
proyecto.getRolesCantidades().put("Programador", 4);
proyecto.getRolesCantidades().put("Tester", 5);
}
```

```java
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
```

```java
@Test
public void getRolesCantidadesDistintos()
{
Map<String, Integer> rolesCantidadesTest = new HashMap<>();
rolesCantidadesTest.put("Líder de proyecto", 3);
rolesCantidadesTest.put("Arquitecto", 1);
rolesCantidadesTest.put("Tester", 3);
Assert.assertRolesNotEquals(proyecto.getRolesCantidades(), rolesCantidadesTest);
}
```

```java
@Test
public void getRolesCantidadesIgualVacios()
{
proyecto.getRolesCantidades().clear();
Map<String, Integer> rolesCantidadesTest = new HashMap<>();
Assert.assertRolesEquals(proyecto.getRolesCantidades(), rolesCantidadesTest);
}
```

```java
@Test
public void getNombre()
{
assertEquals(proyecto.getNombre(), "ProyectoPrueba");
}
```

```java
@Test
public void setNombre()
{
proyecto.setNombre("ProyectoPrueba2");
assertEquals(proyecto.getNombre(), "ProyectoPrueba2");
}
```
