package principal;

import java.util.ArrayList;
import java.util.List;
import personas.Persona;
import interfaces.MainForm;

@SuppressWarnings("unused")
public class main {

	private static List<Persona> personas = new ArrayList<>();
    private static List<String[]> incompatibilidades = new ArrayList<>();
    
	public static void main(String[] args) {	
		MainForm ventana = new MainForm(personas, incompatibilidades);
		ventana.EquipoIdealForm.setVisible(true);
	}
}
