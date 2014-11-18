package JUnitTests;

import static org.junit.Assert.*;
import javagui.views.Actor;
import javagui.views.Conexion;
import javagui.views.Oval;
import javax.swing.JLayeredPane;
import org.junit.Test;

public class TestCU {

	@Test
	public void TestCasosdeUso() {
		JLayeredPane desktop = new JLayeredPane();
		int inicio = desktop.getComponentCount();
		Oval oval = new Oval(desktop, 0, 0, "Caso de Uso Prueba");
		Actor actor = new Actor(desktop, 0, 0, "Actor Prueba");
		//Conexion conexion = new Conexion(desktop, "isa", 0, 0, 10, 10);
		int fin = desktop.getComponentCount();
		assertNotEquals("Test de prueba Casos de Uso", inicio, fin);
	}

}
