package interfaces;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;

import algoritmo.EquipoIdeal;
import algoritmo.EquipoIdealThread;
import personas.Persona;
import proyectos.Proyecto;

public class Controlador implements MouseListener{
	private MainForm ventana;
	private static List<Persona> personas = new ArrayList<>();
    private static List<String[]> incompatibilidades = new ArrayList<>();
    private static Map<String, Integer> rolesCantidades = new HashMap<>();
    private static Map<String, Proyecto> proyectosCreados = new HashMap<>();
   


	public Controlador (MainForm form,List<Persona> _personas, List<String[]> _incompatibilidades) {
		this.ventana = form;
		this.personas = _personas;
		this.incompatibilidades = _incompatibilidades;
		
	}

	
	
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * Metodos para 
	 * Personas.
	 * 
	 */

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
	public static void consultaPersona(int selectedIndex, JTextField textField_ConsultaNombre, JTextField textField_ConsultaRol, JTextField textField_ConsultaCalificacion) {
		
		Persona personaConsulta = new Persona();		
		personaConsulta = personas.get(selectedIndex);
		textField_ConsultaNombre.setText(personaConsulta.getNombre());
		textField_ConsultaRol.setText(personaConsulta.getRol());
		textField_ConsultaCalificacion.setText(String.valueOf(personaConsulta.getCalificacionHistorica()));
	
	}	
	public static void limpiarPersona(JTextField textNombrePersona, JComboBox comboRol, JComboBox comboCalifHistorica) {
		textNombrePersona.setText("");
		comboRol.setSelectedIndex(-1);
		comboCalifHistorica.setSelectedIndex(-1);

	}

	/*
	 * Metodos para 
	 * Incompatibilidades.
	 * 
	 */
	
	public static void crearIncompatibilidad(int[] posicionesSeleccionado,DefaultListModel<String> DLM) {
		

		String[] incompatibilidad = new String[2];
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
	private static String mostrarIncompatibilidad(int conta, List<String[]> incompatibilidades) {
		
		String incompat = "";
		for (String[] strings : incompatibilidades) {
			incompat = Arrays.toString(strings);	
		}
		return incompat;
	}
	
	/*
	 * Metodos para 
	 * Requerimientos.
	 * Proyectos.
	 * 
	 */
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
	public static void limpiarRequerimiento( JTextField textFieldNombreProyecto,JTextField textFieldLiderEquipoCant, JTextField textFieldArquitectoCant,
			JTextField textFieldProgramadorCant, JTextField textFieldTesterCant) {
		
		textFieldNombreProyecto.setText("");
		textFieldLiderEquipoCant.setText("");
		textFieldArquitectoCant.setText("");
		textFieldProgramadorCant.setText("");
		textFieldTesterCant.setText("");		
	}
	public static void verInfoProyecto(Object object) {
		JOptionPane.showMessageDialog(null, proyectosCreados.get(object).toString(),"Info Proyecto", JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	/*
	 * Metodos para 
	 * Generar Equipo Ideal.
	 * 
	 */
	
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
            } else {
            	crearModeloTablaEquipoIdeal(DTM_EquipoIdeal,equipoIdeal);
            	panelEquipoIdeal.setVisible(true);
                
            }
        } catch (InterruptedException e) {
            // Manejar la excepcion si ocurre algun problema con el hilo
        }
	}



	/*
	 * Creacion de Modelos para: 
	 * JList de Personas Creadas. 
	 * JList de Incompatibilidades Creadas. 
	 * JTable de Personas Creadas. 
	 * JTable de Requerimientos Creadas.
	 */
	
	public static ListModel<String> crearModelPersonas(DefaultListModel<String> DLM) {
		
		int tamano = personas.size();		
		final String[] vector = new String[tamano];
		for (int conta = 0; conta < tamano; conta++) {
			vector[conta] =  personas.get(conta).getNombre() + " - "
					+ personas.get(conta).getRol() + " - " + personas.get(conta).getCalificacionHistorica();
			if (!DLM.contains(vector[conta]))
				DLM.addElement(vector[conta]);
		}		
		return DLM;
	}	
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
	public static void crearModeloRequerimientos(DefaultTableModel dTM_Requerimientos, String [] data) {
			
		dTM_Requerimientos.addRow(data);

	}
	public static void crearModeloTablaPersonasCreadas(DefaultTableModel DTM_PersonasCreadas, String [] data) {
		
		DTM_PersonasCreadas.addRow(data);

	}
	public static void crearModeloTablaEquipoIdeal(DefaultTableModel DTM_PersonasCreadas, List<Persona> equipoIdeal) {
		

        for (Persona persona : equipoIdeal) {
        	String [] data = {persona.getNombre(),persona.getRol(),Integer.toString(persona.getCalificacionHistorica())};
        	DTM_PersonasCreadas.addRow(data);
            
        }
	}

	/*
	 * Metodos Auxiliares 
	 * 
	 */
	public static boolean formPersonaCompleto(JTextField textNombrePersona, JComboBox comboRol,
			JComboBox comboCalifHistorica) {
			
		if(textNombrePersona.getText()== "" || comboRol.getSelectedItem().toString() == ""|| comboCalifHistorica.getSelectedItem().toString() == "")
			return false;
		
		return true;
	}
	public static boolean bontonConfirmarPersona() {		
		
		int respuesta = JOptionPane.showConfirmDialog(null,"¿Seguro quiere crear esta Persona?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(respuesta == JOptionPane.YES_OPTION) {
				return  true;
			}else if (respuesta == JOptionPane.NO_OPTION) {
				return false;
			}
		
		return false;
		
	}
	public static boolean bontonConfirmarIncompatibilidad() {		
		
		int respuesta = JOptionPane.showConfirmDialog(null,"¿Seguro quiere crear esta Incompatibilidad?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(respuesta == JOptionPane.YES_OPTION) {
				return  true;
			}else if (respuesta == JOptionPane.NO_OPTION) {
				return false;
			}
		
		return false;
		
	}
	public static boolean bontonConfirmarRequerimiento() {		
		
		int respuesta = JOptionPane.showConfirmDialog(null,"¿Seguro quiere crear este Requerimiento?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(respuesta == JOptionPane.YES_OPTION) {
				return  true;
			}else if (respuesta == JOptionPane.NO_OPTION) {
				return false;
			}
		
		return false;
		
	}
	
	/*
	 * Getters & Setters de:
	 * Personas.
	 * Incompatibilidades.
	 */

	public static List<Persona> getPersonas() {
		return personas;
	}
	public static void setPersonas(List<Persona> personas) {
		Controlador.personas = personas;
	}
	public static List<String[]> getIncompatibilidades() {
		return incompatibilidades;
	}
	public static void setIncompatibilidades(List<String[]> incompatibilidades) {
		Controlador.incompatibilidades = incompatibilidades;
	}


	

	

	
	
	

}
