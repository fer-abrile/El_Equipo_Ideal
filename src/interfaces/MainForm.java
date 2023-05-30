package interfaces;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import personas.Persona;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

public class MainForm {

	public JFrame EquipoIdealForm;
	private JTextField textNombrePersona;
	private JTextField textFieldLiderEquipoCant;
	private JTextField textFieldArquitectoCant;
	private JTextField textFieldProgramadorCant;
	private JTextField textFieldTesterCant;
	private JTextField textFieldNombreProyecto;
	private JTable tableRequrimientosCreados;
	static DefaultListModel<String> DLM_Personas = new DefaultListModel<String>();
	static DefaultListModel<String> DLM_Incompatibilidades = new DefaultListModel<String>();
	static DefaultTableModel DTM_Requerimientos = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre del Proyecto", "Lider del Proyecto", "Arquitecto", "Programdor", "Tester"
			}
		);
	
	
	/**
	 * Launch the application.
	 *
	 * Create the application.
	 * @param incompatibilidades 
	 * @param personas 
	 */
	public MainForm(List<Persona> personas, List<String[]> incompatibilidades) {

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		EquipoIdealForm = new JFrame();
		EquipoIdealForm.setTitle("Equipo Ideal 2.0");
		EquipoIdealForm.setResizable(false);
		EquipoIdealForm.setBounds(100, 100, 714, 522);
		EquipoIdealForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		EquipoIdealForm.getContentPane().setLayout(null);
		
		final JPanel panelNuevaPersona = new JPanel();
		panelNuevaPersona.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelNuevaPersona.setBounds(10, 11, 651, 411);
		EquipoIdealForm.getContentPane().add(panelNuevaPersona);
		panelNuevaPersona.setLayout(null);
		panelNuevaPersona.setVisible(false);
		
		JLabel lblNuevaPersona = new JLabel("Alta Persona");
		lblNuevaPersona.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNuevaPersona.setBounds(10, 11, 631, 70);
		panelNuevaPersona.add(lblNuevaPersona);
		
		JLabel lblNombrePersona = new JLabel("Nombre");
		lblNombrePersona.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombrePersona.setBounds(10, 143, 60, 14);
		panelNuevaPersona.add(lblNombrePersona);
		
		textNombrePersona = new JTextField();
		textNombrePersona.setBounds(154, 137, 146, 20);
		panelNuevaPersona.add(textNombrePersona);
		textNombrePersona.setColumns(10);
		
		JLabel lblRol = new JLabel("Rol");
		lblRol.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRol.setBounds(10, 168, 46, 14);
		panelNuevaPersona.add(lblRol);
		
		JComboBox comboRol = new JComboBox();
		comboRol.setModel(new DefaultComboBoxModel(new String[] {"", "Lider de Proyecto", "Arquitecto", "Programador", "Tester"}));
		comboRol.setBounds(154, 160, 146, 22);
		panelNuevaPersona.add(comboRol);
		
		JComboBox comboCalifHistorica = new JComboBox();
		comboCalifHistorica.setModel(new DefaultComboBoxModel(new String[] {"", "1", "2", "3", "4", "5"}));
		comboCalifHistorica.setBounds(154, 185, 60, 22);
		
		panelNuevaPersona.add(comboCalifHistorica);
		
	
		
		JButton btnLimpiarPersona = new JButton("Limpiar");

		btnLimpiarPersona.setBounds(167, 350, 89, 23);
		panelNuevaPersona.add(btnLimpiarPersona);
		
		JLabel lblCalifPersona = new JLabel("Calificación Historica");
		lblCalifPersona.setHorizontalAlignment(SwingConstants.LEFT);
		lblCalifPersona.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCalifPersona.setBounds(10, 193, 138, 14);
		panelNuevaPersona.add(lblCalifPersona);
		

		
		JButton btnAtrasPersona = new JButton("Atras");
		btnAtrasPersona.setBounds(266, 350, 89, 23);
		panelNuevaPersona.add(btnAtrasPersona);
		
		final JPanel panelNuevaIncompatibilidad = new JPanel();
		panelNuevaIncompatibilidad.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelNuevaIncompatibilidad.setLayout(null);
		panelNuevaIncompatibilidad.setBounds(10, 11, 651, 411);
		panelNuevaIncompatibilidad.setVisible(false);
		EquipoIdealForm.getContentPane().add(panelNuevaIncompatibilidad);
		
		
		JLabel lblAltaIncompatibilidades = new JLabel("Alta Incompatibilidades");
		lblAltaIncompatibilidades.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblAltaIncompatibilidades.setBounds(10, 11, 631, 36);
		panelNuevaIncompatibilidad.add(lblAltaIncompatibilidades);
		
		JList listaPersonasCreadas = new JList();
		listaPersonasCreadas.setModel(Controlador.crearModelPersonas(DLM_Personas));
		listaPersonasCreadas.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		listaPersonasCreadas.setToolTipText("Seleccione 2 (SOLO DOS) personas");
		listaPersonasCreadas.setBounds(23, 80, 248, 235);
		panelNuevaIncompatibilidad.add(listaPersonasCreadas);
		
		JButton btnGuardarPersona = new JButton("Guardar");		
		btnGuardarPersona.setBounds(68, 350, 89, 23);
		panelNuevaPersona.add(btnGuardarPersona);
		JButton btnAgregarPersonasIncom = new JButton("Agregar");

		btnAgregarPersonasIncom.setBounds(182, 326, 89, 23);
		panelNuevaIncompatibilidad.add(btnAgregarPersonasIncom);
		
		JButton btnAtrasIncom = new JButton("Atras");

		btnAtrasIncom.setBounds(562, 377, 79, 23);
		panelNuevaIncompatibilidad.add(btnAtrasIncom);
		
		JList listaIncompatibilidadesCreadas = new JList();
		listaIncompatibilidadesCreadas.setModel(DLM_Incompatibilidades);
		listaIncompatibilidadesCreadas.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		listaIncompatibilidadesCreadas.setBounds(328, 80, 274, 235);
		panelNuevaIncompatibilidad.add(listaIncompatibilidadesCreadas);
		
		JLabel lblPersonasIncom = new JLabel("Personas Creadas");
		lblPersonasIncom.setEnabled(false);
		lblPersonasIncom.setBounds(23, 58, 112, 14);
		panelNuevaIncompatibilidad.add(lblPersonasIncom);
		
		JLabel lblIncomCreadas = new JLabel("Incompatibilidades Existentes");
		lblIncomCreadas.setEnabled(false);
		lblIncomCreadas.setBounds(328, 58, 274, 14);
		panelNuevaIncompatibilidad.add(lblIncomCreadas);
		
		JPanel panelNuevoRequerimiento = new JPanel();
		panelNuevoRequerimiento.setLayout(null);
		panelNuevoRequerimiento.setBounds(10, 11, 678, 411);
		panelNuevoRequerimiento.setVisible(false);
		EquipoIdealForm.getContentPane().add(panelNuevoRequerimiento);
		
		JLabel lblNuevoRequerimiento = new JLabel("Nuevo Requerimiento");
		lblNuevoRequerimiento.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNuevoRequerimiento.setBounds(10, 11, 631, 60);
		panelNuevoRequerimiento.add(lblNuevoRequerimiento);
		
		JPanel panelRequerimientoDatos = new JPanel();
		panelRequerimientoDatos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelRequerimientoDatos.setBounds(10, 82, 266, 271);
		panelNuevoRequerimiento.add(panelRequerimientoDatos);
		panelRequerimientoDatos.setLayout(null);
		
		JLabel lblSeleccionRoles = new JLabel("Seleccione los roles necesarios para el equipo");
		lblSeleccionRoles.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblSeleccionRoles.setEnabled(false);
		lblSeleccionRoles.setBounds(10, 36, 276, 14);
		panelRequerimientoDatos.add(lblSeleccionRoles);
		
		JLabel lblRequerimientoLiderEquipo = new JLabel("Lider de Equipo");
		lblRequerimientoLiderEquipo.setBounds(10, 79, 95, 14);
		panelRequerimientoDatos.add(lblRequerimientoLiderEquipo);
		
		JLabel lblRequerimientoArquitecto = new JLabel("Arquitecto");
		lblRequerimientoArquitecto.setBounds(10, 114, 77, 14);
		panelRequerimientoDatos.add(lblRequerimientoArquitecto);
		
		JLabel lblRequerimientoProgramador = new JLabel("Programador");
		lblRequerimientoProgramador.setBounds(10, 151, 87, 14);
		panelRequerimientoDatos.add(lblRequerimientoProgramador);
		
		JLabel lblRequerimientoTester = new JLabel("Tester");
		lblRequerimientoTester.setBounds(10, 187, 46, 14);
		panelRequerimientoDatos.add(lblRequerimientoTester);
		
		textFieldLiderEquipoCant = new JTextField();
		textFieldLiderEquipoCant.setBounds(112, 73, 43, 20);
		panelRequerimientoDatos.add(textFieldLiderEquipoCant);
		textFieldLiderEquipoCant.setColumns(10);
		
		textFieldArquitectoCant = new JTextField();
		textFieldArquitectoCant.setBounds(112, 108, 43, 20);
		panelRequerimientoDatos.add(textFieldArquitectoCant);
		textFieldArquitectoCant.setColumns(10);
		
		textFieldProgramadorCant = new JTextField();
		textFieldProgramadorCant.setBounds(112, 145, 43, 20);
		panelRequerimientoDatos.add(textFieldProgramadorCant);
		textFieldProgramadorCant.setColumns(10);
		
		textFieldTesterCant = new JTextField();
		textFieldTesterCant.setBounds(112, 181, 43, 20);
		panelRequerimientoDatos.add(textFieldTesterCant);
		textFieldTesterCant.setColumns(10);
		
		JLabel lblNombreProyecto = new JLabel("Nombre de proyecto");
		lblNombreProyecto.setBounds(10, 11, 115, 14);
		panelRequerimientoDatos.add(lblNombreProyecto);
		
		textFieldNombreProyecto = new JTextField();
		textFieldNombreProyecto.setBounds(139, 8, 115, 20);
		panelRequerimientoDatos.add(textFieldNombreProyecto);
		textFieldNombreProyecto.setColumns(10);
		
		JButton btnRequerimientoGuardar = new JButton("Guardar");
		btnRequerimientoGuardar.setBounds(66, 241, 89, 23);
		panelRequerimientoDatos.add(btnRequerimientoGuardar);
		
		JButton btnRequerimientoLimpiar = new JButton("Limpiar");
		btnRequerimientoLimpiar.setBounds(172, 241, 82, 23);
		panelRequerimientoDatos.add(btnRequerimientoLimpiar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(286, 82, 382, 271);
		panelNuevoRequerimiento.add(panel);
		panel.setLayout(null);
		
		JLabel lblProyectosCreados = new JLabel("Proyectos Creados");
		lblProyectosCreados.setBounds(10, 11, 133, 14);
		panel.add(lblProyectosCreados);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 362, 224);
		panel.add(scrollPane);
		
		tableRequrimientosCreados = new JTable();
		scrollPane.setViewportView(tableRequrimientosCreados);
		tableRequrimientosCreados.setModel(DTM_Requerimientos);
		tableRequrimientosCreados.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		tableRequrimientosCreados.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		tableRequrimientosCreados.setBackground(Color.WHITE);
		
		JButton btnRequerimientoCancelar = new JButton("Cancelar");

		btnRequerimientoCancelar.setBounds(552, 377, 89, 23);
		panelNuevoRequerimiento.add(btnRequerimientoCancelar);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		EquipoIdealForm.setJMenuBar(menuBar);
		
		JMenu mnNuevo = new JMenu("Nuevo");
		mnNuevo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnNuevo);
		
		JMenuItem mntmNuevaPersona = new JMenuItem("Nueva Persona");		
		mntmNuevaPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelNuevaPersona.setVisible(true);
				panelNuevaIncompatibilidad.setVisible(false);
				panelNuevoRequerimiento.setVisible(false);
			}
		});
		mnNuevo.add(mntmNuevaPersona);
		JMenuItem mntmNuevaIncompatibilidad = new JMenuItem("Nueva Incompatibilidad");
		mntmNuevaIncompatibilidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelNuevaPersona.setVisible(false);
				panelNuevoRequerimiento.setVisible(false);
				panelNuevaIncompatibilidad.setVisible(true);
				Controlador.limpiarPersona(textNombrePersona, comboRol, comboCalifHistorica);
			}
		});
		mnNuevo.add(mntmNuevaIncompatibilidad);
		
		JMenuItem mntmNuevoRequerimientos = new JMenuItem("Nuevo Requerimiento");
		mntmNuevoRequerimientos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelNuevaPersona.setVisible(false);
				panelNuevaIncompatibilidad.setVisible(false);
				panelNuevoRequerimiento.setVisible(true);
				Controlador.limpiarPersona(textNombrePersona, comboRol, comboCalifHistorica);
			}
		});
		mnNuevo.add(mntmNuevoRequerimientos);
		
		JMenu mnConsultar = new JMenu("Consultar");
		mnConsultar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnConsultar);
		
		JMenuItem mntmConsultarrPersona = new JMenuItem("Consultar Persona");
		mnConsultar.add(mntmConsultarrPersona);
		
		JMenu mnGenerarEquipoMenu = new JMenu("Generar");
		menuBar.add(mnGenerarEquipoMenu);
		
		JMenuItem mntmGenerarEquipo = new JMenuItem("Generar Equipo Ideal");
		mnGenerarEquipoMenu.add(mntmGenerarEquipo);
		
		
		
		//Action Listener Paneles		
		//  NUEVA PERSONAS ///
		
		btnGuardarPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
				Persona persona = new Persona();
				Controlador.crearPersona(persona,textNombrePersona.getText(),comboRol.getSelectedItem().toString() , comboCalifHistorica.getSelectedIndex(),DLM_Personas);
				Controlador.limpiarPersona(textNombrePersona, comboRol, comboCalifHistorica);
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Ooooppss!",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		
		btnLimpiarPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controlador.limpiarPersona(textNombrePersona, comboRol, comboCalifHistorica);
			}
		});
		
		btnAtrasPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelNuevaPersona.setVisible(false);
				Controlador.limpiarPersona(textNombrePersona, comboRol, comboCalifHistorica);
			}
		});
		
		
	//  INCOMPATIBILIDADES ///
		
		btnAgregarPersonasIncom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println("Nombre Persona en Personas segun Seleccionado: " +Controlador.getPersonas().get(listaPersonasCreadas.getSelectedIndex()).getNombre());
					int[] seleccionado = listaPersonasCreadas.getSelectedIndices();
	
					if(Controlador.getPersonas().size() >= 2) {
						Controlador.crearIncompatibilidad(seleccionado,DLM_Incompatibilidades);	
					}else {
						JOptionPane.showMessageDialog(null, "Necesita seleccionar 2 Personas", "Error!",
								JOptionPane.ERROR_MESSAGE);
					}
					
					
			
			}
		});
		
		btnAtrasIncom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelNuevaIncompatibilidad.setVisible(false);
				Controlador.limpiarPersona(textNombrePersona, comboRol, comboCalifHistorica);
				
			}
		});
		
		// REQUERIMIENTOS //
		
		btnRequerimientoLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controlador.limpiarRequerimiento(textFieldNombreProyecto,textFieldLiderEquipoCant,textFieldArquitectoCant,textFieldProgramadorCant,textFieldTesterCant);
			}
		});
		
		btnRequerimientoCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelNuevoRequerimiento.setVisible(false);
				Controlador.limpiarPersona(textNombrePersona, comboRol, comboCalifHistorica);
			}
		});
		btnRequerimientoGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String [] data = {textFieldNombreProyecto.getText(),textFieldLiderEquipoCant.getText(),textFieldArquitectoCant.getText(),textFieldProgramadorCant.getText(),textFieldTesterCant.getText()};
					Controlador.guardarRequerimiento(Integer.parseInt(textFieldLiderEquipoCant.getText()),Integer.parseInt(textFieldArquitectoCant.getText()),
							Integer.parseInt(textFieldProgramadorCant.getText()),Integer.parseInt(textFieldTesterCant.getText()),DTM_Requerimientos,data);
					Controlador.limpiarRequerimiento(textFieldNombreProyecto,textFieldLiderEquipoCant,textFieldArquitectoCant,textFieldProgramadorCant,textFieldTesterCant);
				
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Ooooppss!",JOptionPane.ERROR_MESSAGE);
				}
				}
		});
	}
}
