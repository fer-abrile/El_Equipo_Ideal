package interfaces;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;

import personas.Persona;

public class Controlador implements MouseListener{
	private MainForm ventana;
	private static List<Persona> personas = new ArrayList<>();
    private static List<String[]> incompatibilidades = new ArrayList<>();
    private static Map<String, Integer> rolesCantidades = new HashMap<>();



	public Controlador (MainForm form,List<Persona> _personas, List<String[]> _incompatibilidades) {
		this.ventana = form;
		this.personas = _personas;
		this.incompatibilidades = _incompatibilidades;
		
	}

	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		/*
		 * if (e.getSource() == ventana.frame.) {
		 * System.out.println("LIMPIAR CONTROLADOR");
		 * limpiarPersona(this.ventana.gridCells, ventana); }
		 */

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

	public static Persona crearPersona(Persona persona, String nombre, String rol, int calificaion,DefaultListModel<String> dLM) {
		boolean respuesta = bontonConfirmar();
		if(respuesta ==true) {
			persona = new Persona (nombre, rol, calificaion);
			persona.toString();
			personas.add(persona);
			crearModelPersonas(dLM);
			JOptionPane.showMessageDialog(null, persona.toString(), "Persona Creada",JOptionPane.INFORMATION_MESSAGE);
		}
		return persona;
	}
	
	
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
		
		DefaultListModel<String> DLM_aux = new DefaultListModel<String>();
		Persona personaConsulta = new Persona();
		personaConsulta = personas.get(personaSeleccionada);
		DLM_aux = DLM_IncompatibilidadesPorPersona;

		int tamano = personaConsulta.getPersonasIncompatibles().size();

		final String[] vector = new String[tamano];
		for (int conta = 0; conta < tamano; conta++) {
			vector[conta] = mostrarIncompatibilidad(conta, personaConsulta.getPersonasIncompatibles());
			if (!DLM_aux.contains(vector[conta]))
				DLM_aux.addElement(vector[conta]);
		}
		return DLM_aux;

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

	private static String mostrarIncompatibilidad(int conta, List<String[]> incompatibilidades) {
		
		String incompat = "";
		for (String[] strings : incompatibilidades) {
			incompat = Arrays.toString(strings);	
		}
		return incompat;
	}



	public static void limpiarPersona(JTextField textNombrePersona, JComboBox comboRol, JComboBox comboCalifHistorica) {
		textNombrePersona.setText("");
		comboRol.setSelectedIndex(-1);
		comboCalifHistorica.setSelectedIndex(-1);
		
		
	}



	public static void crearIncompatibilidad(int[] posicionesSeleccionado,DefaultListModel<String> DLM) {
		

		String[] incompatibilidad = new String[2];
		boolean respuesta = bontonConfirmar();

		if (respuesta == true) {
			for (int i = 0; i < incompatibilidad.length; i++) {
				incompatibilidad[i] = personas.get(posicionesSeleccionado[i]).getNombre();
				personas.get(posicionesSeleccionado[i]).getPersonasIncompatibles().add(incompatibilidad);
			}
			incompatibilidades.add(incompatibilidad);					
			crearModelIncompatibilidades(DLM);

		}
	}

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
	



	public static void guardarRequerimiento(int liderEquipo, int arquitecto, int programador, int tester, DefaultTableModel DTM_Requerimientos, String[] data) {
		
		boolean respuesta = bontonConfirmar();
	
			if(respuesta ==true) {
		        rolesCantidades.put("Líder de proyecto", liderEquipo);
		        rolesCantidades.put("Arquitecto", arquitecto);
		        rolesCantidades.put("Programador", programador);
		        rolesCantidades.put("Tester", tester);
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


	public static boolean bontonConfirmar() {		
		
		int respuesta = JOptionPane.showConfirmDialog(null,"Confirme accion", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(respuesta == JOptionPane.YES_OPTION) {
				System.out.println(true);
				return  true;
			}else if (respuesta == JOptionPane.NO_OPTION) {
				System.out.println(false);
				return false;
			}
		
		return false;
		
	}



	public static void ConsultaPersona(int selectedIndex, JTextField textField_ConsultaNombre, JTextField textField_ConsultaRol, JTextField textField_ConsultaCalificacion) {
		Persona personaConsulta = new Persona();
		
		personaConsulta = personas.get(selectedIndex);
		textField_ConsultaNombre.setText(personaConsulta.getNombre());
		textField_ConsultaRol.setText(personaConsulta.getRol());
		textField_ConsultaCalificacion.setText(String.valueOf(personaConsulta.getCalificacionHistorica()));
	
		
	}




	

	
	
	

}
