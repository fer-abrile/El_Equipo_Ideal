package interfaces;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListModel;

import personas.Persona;

public class Controlador implements MouseListener{
	private MainForm ventana;
	private static List<Persona> personas = new ArrayList<>();
    private static List<String[]> incompatibilidades = new ArrayList<>();



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

	public static Persona crearPersona(Persona persona, String nombre, String rol, int calificaion) {
		
		persona = new Persona (nombre, rol, calificaion);
		persona.toString();
		personas.add(persona);
		return persona;
	}
	
	
	public static ListModel<String> crearModelPersonas(DefaultListModel<String> dLM) {
		int tamano = personas.size();
		
		final String[] vector = new String[tamano];
		for (int conta = 0; conta < tamano; conta++) {
			vector[conta] =  personas.get(conta).getNombre() + " - "
					+ personas.get(conta).getRol() + " - " + personas.get(conta).getCalificacionHistorica();
			if (!dLM.contains(vector[conta]))
					dLM.addElement(vector[conta]);
		}		
		return dLM;
	}
	
	public static ListModel<String> crearModelIncompatibilidades(DefaultListModel<String> dLM) {
		int tamano = incompatibilidades.size();
		
		final String[] vector = new String[tamano];
		for (int conta = 0; conta < tamano; conta++) {
			vector[conta] =  mostrarIncompatibilidad(conta,incompatibilidades);
			if (!dLM.contains(vector[conta]))
					dLM.addElement(vector[conta]);
		}		
		return dLM;
	}



	private static String mostrarIncompatibilidad(int conta, List<String[]> incompatibilidades2) {
		String incompat = "";
		
		for (String[] strings : incompatibilidades2) {
			incompat = Arrays.toString(strings);
			
		}

		return incompat;
	}



	public static void limpiarPersona(JTextField textNombrePersona, JComboBox comboRol, JComboBox comboCalifHistorica) {
		textNombrePersona.setText("");
		comboRol.setSelectedIndex(-1);
		comboCalifHistorica.setSelectedIndex(-1);
		
		
	}



	public static void crearIncompatibilidad(int[] seleccionado) {
		String[] incompatibilidad = new String[2];		
		for (Integer index : seleccionado) {
			for(int i=0; i<incompatibilidad.length; i++) {
				incompatibilidad[i] = personas.get(index).getNombre();
			}
		}
//		JOptionPane.showMessageDialog(null, LogicaLocalidad.listarLocalidades.get(seleccionado[i]).getNombre() +" conectada con: " + LogicaLocalidad.listarLocalidades.get(seleccionado[i+1]).getNombre(), "Conexión",JOptionPane.INFORMATION_MESSAGE);

		incompatibilidades.add(incompatibilidad);
		incompatibilidades.toString();
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
	
	
	

}
