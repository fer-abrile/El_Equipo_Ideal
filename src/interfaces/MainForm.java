package interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class MainForm {

	public JFrame frame;
	private JTextField textNombrePersona;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textPersonaIncompa1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm window = new MainForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 714, 522);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		final JPanel panelNuevaPersona = new JPanel();
		panelNuevaPersona.setBounds(10, 38, 651, 384);
		frame.getContentPane().add(panelNuevaPersona);
		panelNuevaPersona.setLayout(null);
		panelNuevaPersona.setVisible(false);
		
		JLabel lblNuevaPersona = new JLabel("Alta Persona");
		lblNuevaPersona.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNuevaPersona.setBounds(10, 11, 631, 70);
		panelNuevaPersona.add(lblNuevaPersona);
		
		JLabel lblNombrePersona = new JLabel("Nombre");
		lblNombrePersona.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombrePersona.setBounds(10, 149, 60, 14);
		panelNuevaPersona.add(lblNombrePersona);
		
		textNombrePersona = new JTextField();
		textNombrePersona.setBounds(154, 143, 146, 20);
		panelNuevaPersona.add(textNombrePersona);
		textNombrePersona.setColumns(10);
		
		JLabel lblApellidoPersona = new JLabel("Apellido");
		lblApellidoPersona.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblApellidoPersona.setBounds(10, 174, 46, 14);
		panelNuevaPersona.add(lblApellidoPersona);
		
		textField = new JTextField();
		textField.setBounds(154, 168, 146, 20);
		panelNuevaPersona.add(textField);
		textField.setColumns(10);
		
		JLabel lblRol = new JLabel("Rol");
		lblRol.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRol.setBounds(10, 199, 46, 14);
		panelNuevaPersona.add(lblRol);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Lider de Proyecto", "Arquitecto", "Programador", "Tester"}));
		comboBox.setBounds(154, 191, 146, 22);
		panelNuevaPersona.add(comboBox);
		
		JButton btnGuardarPersona = new JButton("Guardar");
		btnGuardarPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Holis");
			}
		});
		btnGuardarPersona.setBounds(68, 350, 89, 23);
		panelNuevaPersona.add(btnGuardarPersona);
		
		JButton btnLimpiarPersona = new JButton("Limpiar");
		btnLimpiarPersona.setBounds(167, 350, 89, 23);
		panelNuevaPersona.add(btnLimpiarPersona);
		
		JLabel lblCalifPersona = new JLabel("Calificación Historica");
		lblCalifPersona.setHorizontalAlignment(SwingConstants.LEFT);
		lblCalifPersona.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCalifPersona.setBounds(10, 224, 138, 14);
		panelNuevaPersona.add(lblCalifPersona);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"", "1", "2", "3", "4", "5"}));
		comboBox_1.setBounds(154, 216, 60, 22);
		panelNuevaPersona.add(comboBox_1);
		
		JButton btnAtrasPersona = new JButton("Atras");
		btnAtrasPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelNuevaPersona.setVisible(false);
			}
		});
		btnAtrasPersona.setBounds(266, 350, 89, 23);
		panelNuevaPersona.add(btnAtrasPersona);
		
		JLabel lblIDPersona = new JLabel("ID");
		lblIDPersona.setEnabled(false);
		lblIDPersona.setBounds(210, 92, 19, 14);
		panelNuevaPersona.add(lblIDPersona);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setEditable(false);
		textField_1.setBounds(239, 89, 46, 20);
		panelNuevaPersona.add(textField_1);
		textField_1.setColumns(10);
		
		final JPanel panelNuevaIncompatibilidad = new JPanel();
		panelNuevaIncompatibilidad.setLayout(null);
		panelNuevaIncompatibilidad.setBounds(10, 38, 651, 384);
		panelNuevaIncompatibilidad.setVisible(false);
		frame.getContentPane().add(panelNuevaIncompatibilidad);
		
		
		JLabel lblAltaIncompatibilidades = new JLabel("Alta Incompatibilidades");
		lblAltaIncompatibilidades.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblAltaIncompatibilidades.setBounds(10, 11, 631, 36);
		panelNuevaIncompatibilidad.add(lblAltaIncompatibilidades);
		
		JList listPersonas = new JList();
		listPersonas.setToolTipText("Seleccione 2 (SOLO DOS) personas");
		listPersonas.setBounds(23, 80, 192, 235);
		panelNuevaIncompatibilidad.add(listPersonas);
		
		textPersonaIncompa1 = new JTextField();
		textPersonaIncompa1.setBounds(357, 78, 253, 20);
		panelNuevaIncompatibilidad.add(textPersonaIncompa1);
		textPersonaIncompa1.setColumns(10);
		
		JLabel lblPersonaInco1 = new JLabel("Persona 1");
		lblPersonaInco1.setBounds(272, 81, 75, 14);
		panelNuevaIncompatibilidad.add(lblPersonaInco1);
		
		JLabel lblPersonaInco2 = new JLabel("Persona 2");
		lblPersonaInco2.setBounds(272, 149, 75, 14);
		panelNuevaIncompatibilidad.add(lblPersonaInco2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(357, 146, 253, 20);
		panelNuevaIncompatibilidad.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnAgregarPersonasIncom = new JButton("Agregar");
		btnAgregarPersonasIncom.setBounds(126, 321, 89, 23);
		panelNuevaIncompatibilidad.add(btnAgregarPersonasIncom);
		
		JButton btnGuardarIncom = new JButton("Guardar");
		btnGuardarIncom.setBounds(330, 224, 89, 23);
		panelNuevaIncompatibilidad.add(btnGuardarIncom);
		
		JButton btnLimpiarIncom = new JButton("Limpiar");
		btnLimpiarIncom.setBounds(429, 224, 89, 23);
		panelNuevaIncompatibilidad.add(btnLimpiarIncom);
		
		JButton btnAtrasIncom = new JButton("Atras");
		btnAtrasIncom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelNuevaIncompatibilidad.setVisible(false);
			}
		});
		btnAtrasIncom.setBounds(528, 224, 89, 23);
		panelNuevaIncompatibilidad.add(btnAtrasIncom);
		
		JPanel panelNuevoRequerimiento = new JPanel();
		panelNuevoRequerimiento.setLayout(null);
		panelNuevoRequerimiento.setBounds(10, 38, 651, 384);
		frame.getContentPane().add(panelNuevoRequerimiento);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewCargar = new JMenu("Cargas");
		mnNewCargar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnNewCargar);
		
		JMenuItem mntmCargarPersona = new JMenuItem("Cargar Persona");		
		mntmCargarPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelNuevaPersona.setVisible(true);
			}
		});
		mnNewCargar.add(mntmCargarPersona);
		JMenuItem mntmCargarIncompatibilidad = new JMenuItem("Cargar Incompatibilidad");
		mntmCargarIncompatibilidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelNuevaIncompatibilidad.setVisible(true);
			}
		});
		mnNewCargar.add(mntmCargarIncompatibilidad);
		
		JMenuItem mntmCargarRequerimientos = new JMenuItem("Cargar Requerimientos");
		mnNewCargar.add(mntmCargarRequerimientos);
		
		JMenu mnVizualizar = new JMenu("Visualizacion");
		mnVizualizar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnVizualizar);
		
		JMenuItem mntmVisualizarPersona = new JMenuItem("Visualizar Persona");
		mnVizualizar.add(mntmVisualizarPersona);
		
		JMenuItem mntmVizualizarIncompatibilidad = new JMenuItem("Visualizar Incompatibilidades");
		mnVizualizar.add(mntmVizualizarIncompatibilidad);
		
		JMenu mnResolver = new JMenu("Resolver");
		mnResolver.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnResolver);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Generar Equipo");
		mnResolver.add(mntmNewMenuItem_4);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
