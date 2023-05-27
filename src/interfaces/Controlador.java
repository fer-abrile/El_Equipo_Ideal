package interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controlador implements MouseListener{
	private MainForm ventana;
	
	public Controlador (MainForm form) {
		this.ventana = form;
		
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

	/*
	 * public void limpiarPersona (MainForm vista) { for (int i = 0; i <
	 * vista.getGrid(); i++) { for (int j = 0; j < vista.getGrid(); j++) {
	 * vista.gridCells[i][j].setText("0"); vista.getForeground();
	 * vista.gridCells[i][j].setBackground(Color.white);
	 * vista.gridPanel.add(gridCells[i][j]); } } }
	 */

	

}
